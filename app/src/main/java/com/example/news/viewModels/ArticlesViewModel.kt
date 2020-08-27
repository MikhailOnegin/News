package com.example.news.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.news.pojo.topHeadlines.Article
import com.example.news.repositoryes.ArticleRepository

class ArticlesViewModel(application: Application):AndroidViewModel(application) {

    private val articleRepository = ArticleRepository(application)

    fun getLiveDataArticlesFromDb(): LiveData<List<Article>> {
        return articleRepository.getAllArticles()
    }

    fun deleteFromDb(item: Article){
        articleRepository.delete(item)
    }

    fun deleteAllFromDb(){
        articleRepository.deleteAll()
    }

    fun update(){
        articleRepository.update()
    }

    fun insertToDb(item: Article){
        articleRepository.insert(item)
    }

    fun insertToDb(items:List<Article>){
        items.forEach {
            articleRepository.insert(it)
        }
    }
}