package com.example.news.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.news.domain.pojo.Article
import com.example.news.data.repositoryes.ArticleRepositoryImpl

class ArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val articleRepository = ArticleRepositoryImpl(application)
    private val liveDataArticles = articleRepository.getAllArticles()

    fun getLiveDataArticlesFromDb(): LiveData<List<Article>> {
        return liveDataArticles
    }

    fun deleteFromDb(item: Article) {
        articleRepository.delete(item)
    }

    fun deleteAllFromDb() {
        articleRepository.deleteAll()
    }

    fun update() {
        articleRepository.update()
    }

    fun insertToDb(item: Article) {
        articleRepository.insert(item)
    }

    fun insertToDb(items: List<Article>) {
        items.forEach {
            articleRepository.insert(it)
        }
    }
}