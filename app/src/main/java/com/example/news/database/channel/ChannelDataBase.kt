package com.example.news.database.channel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.news.pojo.source.NewsChannel

@Database(entities = [NewsChannel::class], version = 1, exportSchema = false)
abstract class ChannelDataBase : RoomDatabase() {


    companion object {
        private var db: ChannelDataBase? = null
        private const val DB_NAME = "channels.db"
        private val LOCK = Any()

        fun getInstance(context: Context): ChannelDataBase {
            db?.let {
                return it
            }

            synchronized(LOCK) {
                val instance = Room.databaseBuilder(
                    context,
                    ChannelDataBase::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }

        }
    }

    abstract fun favoriteChannelsDao(): ChannelDao
}