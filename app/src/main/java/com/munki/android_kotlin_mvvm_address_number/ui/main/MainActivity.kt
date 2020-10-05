package com.munki.android_kotlin_mvvm_address_number.ui.main

import android.Manifest
import android.os.Bundle
import androidx.databinding.ObservableArrayList
import com.munki.android_kotlin_mvvm_address_number.BR
import com.munki.android_kotlin_mvvm_address_number.R
import com.munki.android_kotlin_mvvm_address_number.databinding.ActivityMainBinding
import com.munki.android_kotlin_mvvm_address_number.ui.base.BaseActivity
import com.munki.android_kotlin_mvvm_address_number.util.permission.PermissionUtil
import javax.inject.Inject

/**
 * MainActivity
 * @author 나비이쁜이
 * @since 2020.10.05
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    // - Databinding & Viewmodel
    private var mBinding: ActivityMainBinding ? = null
    @Inject internal lateinit var mViewModel : MainViewModel

    /************************************************************************************************************************************************/

    /**
     * Binding variable
     */
    override val bindingVariable: Int get() = BR.main

    /**
     * Resource Layout
     */
    override val layoutId: Int get() = R.layout.activity_main

    /**
     * ViewModel
     */
    override val viewModel: MainViewModel
        get() {
        mViewModel.setNavigation(this)
        return mViewModel
    }

    /************************************************************************************************************************************************/

    /**
     * onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Databinding & Navigation Binding
        mBinding = mViewDataBinding

        // init
        init()
    }

    /**
     * init
     */
    override fun init() {
        super.init()

        // 권한 확인

        // 권한 확인
        val isCONTACTS = PermissionUtil.checkAndRequestPermission(this, 1, Manifest.permission.READ_CONTACTS)

        if (isCONTACTS)
            MainTask(this, object : ITaskCallback {
                override fun returnList(resultList: ObservableArrayList<String?>) {
                    mViewModel.contactList = resultList
                    MainViewModel.bindContactListItem(mBinding!!.rvWord, mViewModel.contactList)
                }
            }).execute()
    }

    /**
     * onRequestPermissionsResult
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (PermissionUtil.verifyPermissions(grantResults)) {
            when(requestCode) {
                1 -> MainTask(this, object : ITaskCallback {
                    override fun returnList(resultList: ObservableArrayList<String?>) {
                        mViewModel.contactList = resultList
                        MainViewModel.bindContactListItem(mBinding!!.rvWord, mViewModel.contactList)
                    }
                }).execute()
            }
        }
    }
}

