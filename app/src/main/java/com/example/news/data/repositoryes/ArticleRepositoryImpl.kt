package com.example.news.data.repositoryes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.data.database.article.ArticleDao
import com.example.news.data.database.article.ArticleDb
import com.example.news.domain.repositories.ArticleRepository
import com.example.news.domain.pojo.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleRepositoryImpl(context: Context):
    ArticleRepository {

    private var articleDao: ArticleDao
    private val articles: MutableLiveData<List<Article>> = MutableLiveData()

    init {
        val db = ArticleDb.getInstance(context)
        articleDao = db.articleDao()
        CoroutineScope(Dispatchers.IO).launch {
            val articles = articleDao.getArticles()
            this@ArticleRepositoryImpl.articles.postValue(articles)
        }
    }

    override fun insert(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            articleDao.insert(article)
        }
    }

    override fun update() {
        CoroutineScope(Dispatchers.IO).launch {
            val articles = articleDao.getArticles()
            this@ArticleRepositoryImpl.articles.postValue(articles)
        }
    }

    override fun delete(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            articleDao.delete(article)
        }
    }


    override fun deleteAll() {
        articles.value?.forEach {
            CoroutineScope(Dispatchers.IO).launch {
                articleDao.delete(it)
            }
        }
    }

    override fun getAllArticles(): LiveData<List<Article>> {
        return articles
    }

}