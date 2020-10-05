package com.munki.android_kotlin_mvvm_address_number.ui.main

import com.munki.android_kotlin_mvvm_address_number.GlobalApplication
import dagger.Module
import dagger.Provides

/**
 * Inject with ViewModel
 * @author 나비이쁜이
 * @since 2020.10.05
 */
@Module
class MainModule {

    @Provides
    fun createViewModel(application: GlobalApplication): MainViewModel {
        return MainViewModel(application)
    }
}