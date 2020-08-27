package com.example.news.data.repositoryes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.data.database.channel.ChannelDataBase
import com.example.news.data.database.channel.ChannelDao
import com.example.news.domain.pojo.NewsChannel
import com.example.news.domain.repositories.NewsChannelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsChannelsRepositoryImpl(context: Context):NewsChannelRepository {

    private var channelDao: ChannelDao
    private var channels: MutableLiveData<List<NewsChannel>> = MutableLiveData()

    init {
        val db = ChannelDataBase.getInstance(context)
        channelDao = db.favoriteChannelsDao()
        CoroutineScope(Dispatchers.IO).launch {
            val channels = channelDao.getChannels()
            this@NewsChannelsRepositoryImpl.channels.postValue(channels)
        }
    }

    override fun insert(channel: NewsChannel) {
        CoroutineScope(Dispatchers.IO).launch {
            channelDao.insert(channel)
        }
    }

    override fun update() {
        CoroutineScope(Dispatchers.IO).launch {
            val channels = channelDao.getChannels()
            this@NewsChannelsRepositoryImpl.channels.postValue(channels)
        }
    }

    override fun delete(channel: NewsChannel) {
        CoroutineScope(Dispatchers.IO).launch {
            channelDao.delete(channel)
        }
    }

    override fun deleteChannels(channels: List<NewsChannel>) {
        CoroutineScope(Dispatchers.IO).launch {
            channels.forEach { channelDao.delete(it) }
        }
    }

    override fun getAllChannels(): LiveData<List<NewsChannel>> {
        return channels
    }
}