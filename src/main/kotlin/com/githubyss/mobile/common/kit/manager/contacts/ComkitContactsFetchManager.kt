package com.githubyss.mobile.common.kit.manager.contacts

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.os.AsyncTask
import android.provider.ContactsContract
import android.text.TextUtils
import com.githubyss.mobile.common.kit.logcat.LogcatUtils
import com.githubyss.mobile.common.kit.processor.ComkitSociologicalNumberProcessor
import java.lang.Exception
import java.lang.ref.WeakReference

/**
 * ComkitContactsFetchManager
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComkitContactsFetchManager private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }
    
    private object Holder {
        val INSTANCE = ComkitContactsFetchManager()
    }
    
    
    interface OnContactsFetchListener {
        fun onContactsFetch(list: List<ComkitContactsModel>)
    }
    
    
    private val TABLE_RAW_CONTACTS_COLUMNS_ARRAY = arrayOf(ContactsContract.RawContacts._ID, ContactsContract.RawContacts.CONTACT_ID, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY)
    
    private val TABLE_DATA_COLUMNS_ARRAY = arrayOf(ContactsContract.Data._ID, ContactsContract.Data.RAW_CONTACT_ID, ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER)
    
    private var onContactsFetchListener: OnContactsFetchListener? = null
    private var contactsFetchAsyncTask: ContactsFetchAsyncTask? = null
    private var beFetching = false
    
    
    private class ContactsFetchAsyncTask constructor(private val contactsFetchManagerWeakRef: WeakReference<ComkitContactsFetchManager>, private val contextWeakRef: WeakReference<Context>) : AsyncTask<String, Int, List<ComkitContactsModel>>() {
        override fun doInBackground(vararg params: String?): List<ComkitContactsModel> {
            if (isCancelled) {
                return emptyList()
            }
            
            val contactsModelList = ArrayList<ComkitContactsModel>()
            val cellphoneSet = HashSet<String>()
            
            contactsFetchManagerWeakRef.get()
                    ?.getDeviceContacts(contextWeakRef, contactsModelList, cellphoneSet)
            
            return contactsModelList
        }
        
        override fun onPostExecute(result: List<ComkitContactsModel>) {
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
                this@ComkitContactsFetchManager.onContactsFetchListener = onContactsFetchListener
                contactsFetchAsyncTask = ContactsFetchAsyncTask(WeakReference(this@ComkitContactsFetchManager), WeakReference(application ?: return))
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
     * ComkitContactsFetchManager.getDeviceContacts(contextWeakRef, contactsModelList, cellphoneSet)
     * <Description> Fetch contacts in cellphone device.
     * <Details>
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
    private fun getDeviceContacts(contextWeakRef: WeakReference<Context>, contactsModelList: MutableList<ComkitContactsModel>, cellphoneSet: MutableSet<String>) {
        try {
            val contentResolver = contextWeakRef.get()?.contentResolver ?: return
            var tableRawContactsCursor: Cursor? = null
            try {
                tableRawContactsCursor = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI /* content://com.android.contacts/raw_contacts */, TABLE_RAW_CONTACTS_COLUMNS_ARRAY, null, null, null)
            } catch (e: Exception) {
                LogcatUtils.e(msg = e.toString())
            }
            tableRawContactsCursor ?: return
            
            while (contactsFetchAsyncTask?.isCancelled == false && tableRawContactsCursor.moveToNext()) {
                val cellphoneList = ArrayList<String>()
                
                val idStr = tableRawContactsCursor.getString(tableRawContactsCursor.getColumnIndex(ContactsContract.RawContacts._ID))
                val displayNameStr = tableRawContactsCursor.getString(tableRawContactsCursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY))
                
                var tableDataCursor: Cursor? = null
                try {
                    tableDataCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI /* content://com.android.contacts/data */, TABLE_DATA_COLUMNS_ARRAY, "${ContactsContract.Data.RAW_CONTACT_ID} =? AND ${ContactsContract.Data.MIMETYPE} =?", arrayOf(idStr, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE), null)
                } catch (e: Exception) {
                    LogcatUtils.e(msg = e.toString())
                }
                tableDataCursor ?: return
                
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
            LogcatUtils.e(msg = e.toString())
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
