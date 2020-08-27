package com.example.news.repositoryes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.database.channel.ChannelDataBase
import com.example.news.database.channel.ChannelDao
import com.example.news.pojo.source.NewsChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsChannelsRepository(context: Context) {
    private var channelDao: ChannelDao
    private var channels: MutableLiveData<List<NewsChannel>> = MutableLiveData()

    init {
        val db = ChannelDataBase.getInstance(context)
        channelDao = db.favoriteChannelsDao()
        CoroutineScope(Dispatchers.IO).launch {
            val channels = channelDao.getChannels()
            this@NewsChannelsRepository.channels.postValue(channels)
        }
    }

    fun insert(channel: NewsChannel) {
        CoroutineScope(Dispatchers.IO).launch {
            channelDao.insert(channel)
        }
    }

    fun update() {
        CoroutineScope(Dispatchers.IO).launch {
            val channels = channelDao.getChannels()
            this@NewsChannelsRepository.channels.postValue(channels)
        }
    }

    fun delete(channel: NewsChannel) {
        CoroutineScope(Dispatchers.IO).launch {
            channelDao.delete(channel)
        }
    }

    fun deleteChannels(channels: List<NewsChannel>) {
        CoroutineScope(Dispatchers.IO).launch {
            channels.forEach { channelDao.delete(it) }
        }
    }

    fun getAllChannels(): LiveData<List<NewsChannel>> {
        return channels
    }

}