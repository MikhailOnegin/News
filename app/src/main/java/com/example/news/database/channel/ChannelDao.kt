package com.example.news.database.channel

import androidx.room.*
import com.example.news.pojo.source.NewsChannel

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channels")
    suspend fun getChannels(): List<NewsChannel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(channel: NewsChannel)

    @Delete
    suspend fun delete(channel: NewsChannel)

}