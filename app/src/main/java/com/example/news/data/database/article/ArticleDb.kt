package com.example.news.data.database.article

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news.domain.pojo.Article
import com.example.news.utils.ArticleSourceConverter


@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(ArticleSourceConverter::class)
abstract class ArticleDb : RoomDatabase() {

    companion object {
        private var db: ArticleDb? = null
        private const val DB_NAME = "articles.db"
        private val LOCK = Any()

        fun getInstance(context: Context): ArticleDb {
            db?.let {
                return it
            }

            synchronized(LOCK) {
                val instance = Room.databaseBuilder(
                    context,
                    ArticleDb::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }

        }
    }

    abstract fun articleDao(): ArticleDao
}