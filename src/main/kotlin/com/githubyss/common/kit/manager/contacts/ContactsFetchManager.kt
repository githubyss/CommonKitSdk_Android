package com.githubyss.common.kit.manager.contacts

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.os.AsyncTask
import android.provider.ContactsContract
import android.text.TextUtils
import com.githubyss.common.kit.util.isCellphone
import com.githubyss.common.kit.util.logE
import java.lang.ref.WeakReference

/**
 * ContactsFetchManager
 *
 * @author Ace Yan
 * @github githubyss
 */
class ContactsFetchManager private constructor() {
    companion object {
        var instance = Holder.INSTANCE

        private val TAG: String = ContactsFetchManager::class.java.simpleName
    }

    private object Holder {
        val INSTANCE = ContactsFetchManager()
    }


    interface OnContactsFetchListener {
        fun onContactsFetch(list: List<ContactsModel>)
    }


    private val TABLE_RAW_CONTACTS_COLUMNS_ARRAY = arrayOf(ContactsContract.RawContacts._ID, ContactsContract.RawContacts.CONTACT_ID, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY)

    private val TABLE_DATA_COLUMNS_ARRAY = arrayOf(ContactsContract.Data._ID, ContactsContract.Data.RAW_CONTACT_ID, ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER)

    private var onContactsFetchListener: OnContactsFetchListener? = null
    private var contactsFetchAsyncTask: ContactsFetchAsyncTask? = null
    private var beFetching = false


    private class ContactsFetchAsyncTask constructor(private val contactsFetchManagerWeakRef: WeakReference<ContactsFetchManager>, private val contextWeakRef: WeakReference<Context>) : AsyncTask<String, Int, List<ContactsModel>>() {
        override fun doInBackground(vararg params: String?): List<ContactsModel> {
            if (isCancelled) {
                return emptyList()
            }

            val contactsModelList = ArrayList<ContactsModel>()
            val cellphoneSet = HashSet<String>()

            contactsFetchManagerWeakRef.get()
                ?.getDeviceContacts(contextWeakRef, contactsModelList, cellphoneSet)

            return contactsModelList
        }

        override fun onPostExecute(result: List<ContactsModel>) {
            super.onPostExecute(result)

            contactsFetchManagerWeakRef.get()?.beFetching = false
            contactsFetchManagerWeakRef.get()?.onContactsFetchListener?.onContactsFetch(result)
        }

        override fun onCancelled() {
            super.onCancelled()
            contactsFetchManagerWeakRef.get()?.beFetching = false
        }
    }


    fun startFetch(application: Application?, onContactsFetchListener: OnContactsFetchListener) {
        when {
            beFetching -> return
            else -> {
                beFetching = true
                this@ContactsFetchManager.onContactsFetchListener = onContactsFetchListener
                contactsFetchAsyncTask = ContactsFetchAsyncTask(WeakReference(this@ContactsFetchManager), WeakReference(application ?: return))
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

    /**
     * ContactsFetchManager.getDeviceContacts(contextWeakRef, contactsModelList, cellphoneSet)
     * Fetch contacts in cellphone device.
     *     Traverse table [raw_contacts] to obtain all "display_name" and "_id" in cellphone device.
     *     Query table [data] by "raw_contact_id"("_id" above) and "mimetype_id" and find out all number("data1") according to "display_name"
     *
     *     "raw_contact_id" relate to "display_name" one-to-one;
     *     "raw_contact_id" relate to "data1" one-to-many.
     *
     *     You can obtain the contacts database file "contact2.db" from a rooted cellphone device to get more information.
     *
     * @param contextWeakRef
     * @param contactsModelList
     * @param cellphoneSet
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    private fun getDeviceContacts(contextWeakRef: WeakReference<Context>, contactsModelList: MutableList<ContactsModel>, cellphoneSet: MutableSet<String>) {
        try {
            val contentResolver = contextWeakRef.get()?.contentResolver ?: return
            var tableRawContactsCursor: Cursor? = null
            try {
                tableRawContactsCursor = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI /* content://com.android.contacts/raw_contacts */, TABLE_RAW_CONTACTS_COLUMNS_ARRAY, null, null, null)
            }
            catch (e: Exception) {
                logE(TAG, msg = e.toString())
            }
            tableRawContactsCursor ?: return

            while (contactsFetchAsyncTask?.isCancelled == false && tableRawContactsCursor.moveToNext()) {
                val cellphoneList = ArrayList<String>()

                val idStr = tableRawContactsCursor.getString(tableRawContactsCursor.getColumnIndex(ContactsContract.RawContacts._ID))
                val displayNameStr = tableRawContactsCursor.getString(tableRawContactsCursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY))

                var tableDataCursor: Cursor? = null
                try {
                    tableDataCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI /* content://com.android.contacts/data */, TABLE_DATA_COLUMNS_ARRAY, "${ContactsContract.Data.RAW_CONTACT_ID} =? AND ${ContactsContract.Data.MIMETYPE} =?", arrayOf(idStr, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE), null)
                }
                catch (e: Exception) {
                    logE(TAG, e.toString())
                }
                tableDataCursor ?: return

                while (contactsFetchAsyncTask?.isCancelled == false && tableDataCursor.moveToNext()) {
                    val cellphoneStr = formatCellphone(tableDataCursor.getString(tableDataCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)))

                    if (isCellphone(cellphoneStr)) {
                        cellphoneList.add(cellphoneStr)
                        cellphoneSet.add(cellphoneStr)
                    }
                }
                tableDataCursor.close()

                val contactsModel = ContactsModel(idStr, displayNameStr, cellphoneList)
                contactsModelList.add(contactsModel)
            }
            tableRawContactsCursor.close()
        }
        catch (e: SecurityException) {
            logE(TAG, e.toString())
        }
    }

    private fun formatCellphone(input: String): String {
        val cellphone: String
        return when {
            !TextUtils.isEmpty(input) -> {
                cellphone = input.replace("-", "")
                    .replace(" ", "")
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
