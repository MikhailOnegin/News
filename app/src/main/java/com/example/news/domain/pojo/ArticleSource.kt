package com.example.news.domain.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticleSource(

    @SerializedName("id")
    @Expose
    val id: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null

)