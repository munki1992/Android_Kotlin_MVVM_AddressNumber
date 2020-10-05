package com.munki.android_kotlin_mvvm_address_number.ui.main

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.munki.android_kotlin_mvvm_address_number.GlobalApplication
import com.munki.android_kotlin_mvvm_address_number.ui.base.BaseViewModel

/**
 * MainViewModel
 * @author 나비이쁜이
 * @since 2020.10.05
 */
class MainViewModel internal constructor(application : GlobalApplication) : BaseViewModel<MainNavigator>(application) {

    val adapter: MainAdapter = MainAdapter(getApplication())
    var contactList: ObservableArrayList<String?> = ObservableArrayList()

    companion object {
        // [Binding] setAdapter
        @JvmStatic
        @BindingAdapter("setContactListAdapter")
        fun bindContactListAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
            recyclerView.adapter = adapter
        }

        // [Binding] setItem
        @JvmStatic
        @BindingAdapter("setContactListName")
        fun bindContactListItem(recyclerView: RecyclerView, dataList: ObservableArrayList<String?>) {
            (recyclerView.adapter as MainAdapter?)?.setItem(dataList)
        }
    }
}