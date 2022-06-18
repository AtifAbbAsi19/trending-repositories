package com.inc.sada_pay_test.data.model.reposotryitem

import com.google.gson.annotations.SerializedName

data class RepositoryItem(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("user_name")
    val userName: String? = null,

    @SerializedName("repository_language")
    val language: String? = null,


    @SerializedName("total_count")
    val totalCount: Int? = null,


    )