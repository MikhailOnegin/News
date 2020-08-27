package com.example.news.utils

import androidx.room.TypeConverter
import com.example.news.pojo.topHeadlines.ArticleSource

class ArticleSourceConverter {

                                                              // Нужно переписать нормально

    @TypeConverter
    fun fromSource(source: ArticleSource):String{
        return source.toString()

    }
    @TypeConverter
    fun toSource(source:String):ArticleSource{
        return ArticleSource("1","1")
    }
}