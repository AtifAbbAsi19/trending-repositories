package com.inc.sada_pay_test.ui.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inc.sada_pay_test.network.networkstates.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    fun fetchRepositories(){
        viewModelScope.launch {
            repository.fetchData().collect(){

                when(it){

                    is ApiState.Loading -> {

                    }

                    is ApiState.Success -> {

                        Log.i("TAGGGG", it.response.toString())

                    }

                    is ApiState.Failure -> {

                    }

                }

            }
        }
    }

}