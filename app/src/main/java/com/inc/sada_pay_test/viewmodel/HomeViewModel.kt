package com.inc.sada_pay_test.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inc.sada_pay_test.data.model.reposotryitem.RepositoryItem
import com.inc.sada_pay_test.network.networkstates.ApiState
import com.inc.sada_pay_test.repositories.homerepository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {


    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<ApiState<List<RepositoryItem?>?>>(ApiState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<ApiState<List<RepositoryItem?>?>> = _uiState

    val _repositoryList = MutableLiveData<List<RepositoryItem?>?>()


    init {
        fetchRepositories()
    }


    fun fetchRepositories() {
        viewModelScope.launch {
            repository.fetchData().collect() {

                when (it) {

                    is ApiState.Loading -> {
                        _uiState.value = it
                    }

                    is ApiState.Success -> {
                        it.response?.let { res ->

                            Log.i("TAGGGG", it.response.toString())


                            val list = res.items?.map { it?.toRepositoryItem() }

                            if (null != list && list.isNotEmpty()) {

                                list[0]?.expand = View.VISIBLE
                                _repositoryList.value = list

                                _uiState.value = ApiState.Success(list)
                            }
                        }
                    }

                    is ApiState.Failure -> {
                        _uiState.value = it
                    }

                    else -> {

                    }
                }

            }
        }
    }


    fun updateItem(repositoryItem: RepositoryItem) {


        _repositoryList.value.let {


            it.let {
/*

                for (item in it!!) {
                    if (repositoryItem == item) {
                        item.expand = View.VISIBLE
                    } else {
                        item?.expand = View.GONE
                    }
                    //it.find { repositoryItem == it }?.expand = true
                }
                _repositoryList.value = (it)
            }

*/

                it?.onEach {

                    if (repositoryItem == it) {
                        it.expand = View.VISIBLE
                    } else {
                        it?.expand = View.GONE
                    }

                }

                _uiState.value = ApiState.Success(it)

            }

        }
    }

    override fun onCleared() {
        super.onCleared()

        _repositoryList.value = null

    }

}