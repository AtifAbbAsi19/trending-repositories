package com.inc.sada_pay_test.viewmodel

import androidx.lifecycle.MutableLiveData
import com.inc.sada_pay_test.database.DataStoreManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class ToolbarViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {

    var title = MutableLiveData("")

    var isDarkModeEnabled = MutableLiveData(false)


    fun getCheckedState(): Boolean {

        if (isDarkModeEnabled.value!!) {
            isDarkModeEnabled.value = false
            return false
        }
        isDarkModeEnabled.value = true
        return true

    }


    @DelicateCoroutinesApi
    fun saveDarkModeSetting(isDarkModeEnabled: Boolean) {

        GlobalScope.launch {
            dataStoreManager.saveSetting(isDarkModeEnabled)
        }
    }

    fun getDarkModeSetting(): Flow<Boolean> {
        return dataStoreManager.isDarkModeEnabled
    }

    /* init {
         title.postValue("")
     }*/

}