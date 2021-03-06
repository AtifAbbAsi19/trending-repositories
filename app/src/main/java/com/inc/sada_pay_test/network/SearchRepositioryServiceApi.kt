package com.inc.sada_pay_test.network

import com.inc.sada_pay_test.data.dto.searchrepo.SearchRepositoriesDto
import retrofit2.Response
import retrofit2.http.GET

interface SearchRepositioryServiceApi {

    @GET(EndPoints.REPOSITIORIES_URL)
    suspend fun getRepositiories() : Response<SearchRepositoriesDto>


}