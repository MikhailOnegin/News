package com.example.news.domain.pojo

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class Article(

    @PrimaryKey(autoGenerate = true)
    @Expose
    var id: Int,

    @SerializedName("source")
    @Expose
    var source: ArticleSource?,

    @SerializedName("author")
    @Expose
    var author: String?,

    @SerializedName("title")
    @Expose
    var title: String?,

    @SerializedName("description")
    @Expose
    var description: String?,

    @SerializedName("url")
    @Expose
    var url: String?,

    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String?,

    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String?,

    @SerializedName("content")
    @Expose
    var content: String?

) {
    @Ignore
    constructor(
        source: ArticleSource,
        author: String,
        title: String,
        description: String,
        url: String,
        urlToImage: String,
        publishedAt: String,
        content: String
    ) : this(
        description.hashCode(),
        source,
        author,
        title,
        description,
        url,
        urlToImage,
        publishedAt,
        content
    )

}