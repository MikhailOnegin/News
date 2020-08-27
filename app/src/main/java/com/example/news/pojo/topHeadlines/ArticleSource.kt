package com.example.news.pojo.topHeadlines

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