package com.example.news.data.database.channel

import androidx.room.*
import com.example.news.domain.pojo.NewsChannel

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channels")
    suspend fun getChannels(): List<NewsChannel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(channel: NewsChannel)

    @Delete
    suspend fun delete(channel: NewsChannel)

}