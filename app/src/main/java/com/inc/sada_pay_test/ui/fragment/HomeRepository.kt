package com.inc.sada_pay_test.ui.fragment

import com.inc.sada_pay_test.network.SearchRepositioryServiceApi
import com.inc.sada_pay_test.network.baseApiResultHandler
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val searchRepositoryServiceApi: SearchRepositioryServiceApi
){

    fun fetchData() = baseApiResultHandler{
        searchRepositoryServiceApi.getRepositiories()
    }
}