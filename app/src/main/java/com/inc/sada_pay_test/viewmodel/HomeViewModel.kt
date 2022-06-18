package com.inc.sada_pay_test.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inc.sada_pay_test.network.networkstates.ApiState
import com.inc.sada_pay_test.ui.fragment.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

                       // val value = it.response?.toRepositoryItem()

                        //value.to

                        Log.i("TAGGGG", it.response.toString())

                    }

                    is ApiState.Failure -> {

                    }

                }

            }
        }
    }


    override fun onCleared() {
        super.onCleared()

    }

}