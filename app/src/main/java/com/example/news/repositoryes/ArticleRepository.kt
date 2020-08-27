package com.example.news.repositoryes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.database.article.ArticleDao
import com.example.news.database.article.ArticleDb
import com.example.news.pojo.topHeadlines.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleRepository(context: Context) {
    private var articleDao: ArticleDao
    private val articles: MutableLiveData<List<Article>> = MutableLiveData()

    init {
        val db = ArticleDb.getInstance(context)
        articleDao = db.articleDao()
        CoroutineScope(Dispatchers.IO).launch {
            val articles = articleDao.getArticles()
            this@ArticleRepository.articles.postValue(articles)
        }
    }

    fun insert(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            articleDao.insert(article)
        }
    }

    fun update() {
        CoroutineScope(Dispatchers.IO).launch {
            val articles = articleDao.getArticles()
            this@ArticleRepository.articles.postValue(articles)
        }
    }

    fun delete(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            articleDao.delete(article)
        }
    }


    fun deleteAll() {
        articles.value?.forEach {
            CoroutineScope(Dispatchers.IO).launch {
                articleDao.delete(it)
            }
        }
    }

    fun getAllArticles(): LiveData<List<Article>> {
        return articles
    }

}