package com.example.news.domain.pojo

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