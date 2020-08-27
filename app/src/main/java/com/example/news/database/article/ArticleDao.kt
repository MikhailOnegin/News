package com.example.news.database.article

import androidx.room.*
import com.example.news.pojo.topHeadlines.Article

@Dao
interface ArticleDao {


    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

}