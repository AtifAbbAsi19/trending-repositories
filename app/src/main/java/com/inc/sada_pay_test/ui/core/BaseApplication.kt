package com.inc.sada_pay_test.ui.core

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.inc.sada_pay_test.database.DataStoreManager
import com.inc.sada_pay_test.viewmodel.ToolbarViewModel
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication @Inject constructor() : Application() {


    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate() {
        super.onCreate()



        GlobalScope.launch {

            val isDarkModeEnabled = dataStoreManager.isDarkModeEnabled.first()

            val mode = if (isDarkModeEnabled) {
                AppCompatDelegate
                    .MODE_NIGHT_YES
            } else {
                AppCompatDelegate
                    .MODE_NIGHT_NO
            }

            AppCompatDelegate
                .setDefaultNightMode(
                    mode
                )

        }

    }


}

