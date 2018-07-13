package com.githubyss.mobile.common.kit.fetcher.contacts

import android.content.Context
import android.os.AsyncTask
import android.provider.ContactsContract
import android.text.TextUtils
import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils
import com.githubyss.mobile.common.kit.processor.ComkitSociologicalNumberProcessor
import java.lang.ref.WeakReference

/**
 * ComkitContactsFetcher
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComkitContactsFetcher private constructor() {
    companion object {
        var instance = Holder.INSTANCE

        private val TABLE_RAW_CONTACTS_COLUMNS_ARRAY = arrayOf(
                ContactsContract.RawContacts._ID,
                ContactsContract.RawContacts.CONTACT_ID,
                ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY)

        private val TABLE_DATA_COLUMNS_ARRAY = arrayOf(
                ContactsContract.Data._ID,
                ContactsContract.Data.RAW_CONTACT_ID,
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER)
    }

    private object Holder {
        val INSTANCE = ComkitContactsFetcher()
    }


    interface OnContactsFetchListener {
        fun onFetchComplete(list: List<ComkitContactsModel>)
    }


    private var contactsFetchAsyncTask: ContactsFetchAsyncTask? = null
    private var onContactsFetchListener: OnContactsFetchListener? = null
    private var beFetching = false


    private class ContactsFetchAsyncTask(private val context: WeakReference<Context>, private val comkitContactsFetcherWeakRef: WeakReference<ComkitContactsFetcher>) : AsyncTask<String, Int, List<ComkitContactsModel>>() {
        override fun doInBackground(vararg params: String?): List<ComkitContactsModel> {
            if (isCancelled) {
                return emptyList()
            }

            val contactsModelList = ArrayList<ComkitContactsModel>()
            val cellphoneSet = HashSet<String>()

            comkitContactsFetcherWeakRef.get()?.getDeviceContacts(context, contactsModelList, cellphoneSet)

            return contactsModelList
        }

        override fun onPostExecute(result: List<ComkitContactsModel>) {
            super.onPostExecute(result)

            comkitContactsFetcherWeakRef.get()?.beFetching = false
            comkitContactsFetcherWeakRef.get()?.onContactsFetchListener?.onFetchComplete(result)
        }

        override fun onCancelled() {
            super.onCancelled()
            comkitContactsFetcherWeakRef.get()?.beFetching = false
        }
    }


    fun startFetch(context: Context, onContactsFetchListener: OnContactsFetchListener) {
        when {
            beFetching -> return
            else -> {
                beFetching = true
                this@ComkitContactsFetcher.onContactsFetchListener = onContactsFetchListener
                contactsFetchAsyncTask = ContactsFetchAsyncTask(WeakReference(context), WeakReference(this@ComkitContactsFetcher))
                contactsFetchAsyncTask?.execute()
            }
        }
    }

    fun stopFetch() {
        if (contactsFetchAsyncTask?.status == AsyncTask.Status.RUNNING) {
            contactsFetchAsyncTask?.cancel(true)
            contactsFetchAsyncTask = null
        }
    }

    private fun getDeviceContacts(context: WeakReference<Context>, contactsModelList: MutableList<ComkitContactsModel>, cellphoneSet: MutableSet<String>) {
        try {
            val contentResolver = context.get()?.contentResolver ?: return

            val tableRawContactsCursor = contentResolver.query(
                    ContactsContract.RawContacts.CONTENT_URI /* content://com.android.contacts/raw_contacts */,
                    TABLE_RAW_CONTACTS_COLUMNS_ARRAY,
                    null, null, null)
            while (contactsFetchAsyncTask?.isCancelled == false && tableRawContactsCursor.moveToNext()) {
                val cellphoneList = ArrayList<String>()

                val idStr = tableRawContactsCursor.getString(tableRawContactsCursor.getColumnIndex(ContactsContract.RawContacts._ID))
                val displayNameStr = tableRawContactsCursor.getString(tableRawContactsCursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY))

                val tableDataCursor = contentResolver.query(
                        ContactsContract.Data.CONTENT_URI /* content://com.android.contacts/data */,
                        TABLE_DATA_COLUMNS_ARRAY,
                        "${ContactsContract.Data.RAW_CONTACT_ID} =? AND ${ContactsContract.Data.MIMETYPE} =?",
                        arrayOf(idStr, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE), null)
                while (contactsFetchAsyncTask?.isCancelled == false && tableDataCursor.moveToNext()) {
                    val cellphoneStr = formatCellphone(tableDataCursor.getString(tableDataCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)))

                    if (ComkitSociologicalNumberProcessor.checkChineseCellphone(cellphoneStr)) {
                        cellphoneList.add(cellphoneStr)
                        cellphoneSet.add(cellphoneStr)
                    }
                }
                tableDataCursor.close()

                val contactsModel = ComkitContactsModel(idStr, displayNameStr, cellphoneList)
                contactsModelList.add(contactsModel)
            }
            tableRawContactsCursor.close()
        } catch (e: SecurityException) {
            ComkitLogcatUtils.e(msg = e.toString())
        }
    }

    private fun formatCellphone(input: String): String {
        val cellphone: String
        return when {
            !TextUtils.isEmpty(input) -> {
                cellphone = input.replace("-", "").replace(" ", "")
                when {
                    cellphone.startsWith("+86") -> cellphone.substring(3)
                    cellphone.startsWith("86") -> cellphone.substring(2)
                    else -> cellphone
                }
            }
            else -> ""
        }
    }
}
