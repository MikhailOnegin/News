package com.example.news.domain.repositories

import androidx.lifecycle.LiveData
import com.example.news.domain.pojo.Article

interface ArticleRepository {
    fun insert(article: Article)

    fun update()

    fun delete(article: Article)

    fun deleteAll()

    fun getAllArticles(): LiveData<List<Article>>
}