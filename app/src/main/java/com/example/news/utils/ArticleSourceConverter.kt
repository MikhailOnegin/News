package com.example.news.utils

import androidx.room.TypeConverter
import com.example.news.domain.pojo.ArticleSource

class ArticleSourceConverter {

                                                              // need to finish

    @TypeConverter
    fun fromSource(source: ArticleSource):String{
        return source.toString()

    }
    @TypeConverter
    fun toSource(source:String): ArticleSource {
        return ArticleSource("1", "1")
    }
}