package com.munki.android_kotlin_mvvm_address_number.ui.main

import android.os.AsyncTask
import android.provider.ContactsContract
import androidx.databinding.ObservableArrayList
import java.lang.ref.WeakReference

/**
 * MainTask
 * @author 나비이쁜이
 * @since 2020.10.05
 */
class MainTask(context: MainActivity, callback: ITaskCallback?) : AsyncTask<Void?, Void?, ObservableArrayList<String?>?>() {

    private val mContext: WeakReference<MainActivity> = WeakReference(context)
    private val mCallback: ITaskCallback? = callback

    /**
     * doInBackground
     */
    override fun doInBackground(vararg voids: Void?): ObservableArrayList<String?>? {
        // Result ArrayList
        val contacts = ObservableArrayList<String?>()


        // Cursor
        mContext.get()!!.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null).use {
            while (it!!.moveToNext()) {
                // Name + Number
                val contactName = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNumber = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                // Result
                contacts.add("$contactName : $phoneNumber")
            }
        }

        // Return
        return contacts
    }

    /**
     * onPostExecute
     */
    override fun onPostExecute(strings: ObservableArrayList<String?>?) {
        super.onPostExecute(strings)

        mCallback?.returnList(strings!!)
    }
}