package com.example.news.domain.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "channels")
data class NewsChannel (

    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id:String = "",
    @SerializedName("name")
    @Expose
    val name:String? = null,
    @SerializedName("description")
    @Expose
    val description:String? = null,
    @SerializedName("url")
    @Expose
    val url:String? = null,
    @SerializedName("category")
    @Expose
    val category:String? = null,
    @SerializedName("language")
    @Expose
    val language:String? = null,
    @SerializedName("country")
    @Expose
    val country:String? = null

)