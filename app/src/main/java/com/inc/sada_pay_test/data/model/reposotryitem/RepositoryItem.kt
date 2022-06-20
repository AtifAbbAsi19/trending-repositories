package com.inc.sada_pay_test.data.model.reposotryitem

import android.view.View
import com.google.gson.annotations.SerializedName

data class RepositoryItem(

    @SerializedName("imageUrl")
    val imageUrl: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("user_name")
    val userName: String? = null,

    @SerializedName("repository_description")
    val repositoryDescription: String? = null,

    @SerializedName("repository_language")
    val language: String? = null,

    @SerializedName("total_rating")
    val totalRatingCount: Int? = null,

    @SerializedName("expand")
    var expand: Int = View.GONE


)