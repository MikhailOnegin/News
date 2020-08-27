package com.example.news.domain.repositories

import androidx.lifecycle.LiveData
import com.example.news.domain.pojo.NewsChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface NewsChannelRepository {
    fun insert(channel: NewsChannel)

    fun update()

    fun delete(channel: NewsChannel)

    fun deleteChannels(channels: List<NewsChannel>)

    fun getAllChannels(): LiveData<List<NewsChannel>>

}