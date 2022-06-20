package com.inc.sada_pay_test.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow
import java.util.prefs.Preferences
import javax.inject.Inject

class DataStoreManager@Inject constructor(val context: Context) {


    private val Context.dataStore by preferencesDataStore(
        name = APP_PREFERENCES_SETTING
    )


    suspend fun saveSetting(isDarkModeEnabled: Boolean)  {
        context.dataStore.edit {
            it[DARK_MODE_SETTING] = isDarkModeEnabled
        }
    }

    val isDarkModeEnabled
        get() = context.dataStore.data.map { it[DARK_MODE_SETTING] ?: false }


    companion object {

        val APP_PREFERENCES_SETTING = "app_setting_preferences"

        val DARK_MODE_SETTING = booleanPreferencesKey("dark-mode-setting")
    }
}