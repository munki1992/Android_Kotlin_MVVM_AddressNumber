package com.munki.android_kotlin_mvvm_address_number.ui.main

import androidx.databinding.ObservableArrayList

/**
 * ITaskCallback
 * @author 나비이쁜이
 * @since 2020.10.05
 */
interface ITaskCallback {
    fun returnList(resultList: ObservableArrayList<String?>)
}