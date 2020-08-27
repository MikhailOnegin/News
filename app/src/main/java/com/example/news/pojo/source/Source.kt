package com.example.news.pojo.source

import com.example.news.pojo.source.NewsChannel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("status")
    @Expose
    val status: String? = null,
    @SerializedName("sources")
    @Expose
    val sources: List<NewsChannel>? = null
)