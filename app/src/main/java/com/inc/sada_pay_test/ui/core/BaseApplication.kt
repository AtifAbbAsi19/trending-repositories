package com.inc.sada_pay_test.ui.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication @Inject constructor() : Application(){

    override fun onCreate() {
        super.onCreate()

    }

}