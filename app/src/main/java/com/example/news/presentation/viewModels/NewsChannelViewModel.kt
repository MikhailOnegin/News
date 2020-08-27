package com.example.news.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.news.data.repositoryes.NewsChannelsRepositoryImpl
import com.example.news.domain.pojo.NewsChannel
import kotlinx.coroutines.launch

class NewsChannelViewModel(application: Application):AndroidViewModel(application) {

    private val newsChannelsRepository = NewsChannelsRepositoryImpl(application)
    private val liveDataNewsChannels = newsChannelsRepository.getAllChannels()

    fun getLiveDataChannels(): LiveData<List<NewsChannel>> {
        return liveDataNewsChannels
    }

    fun deleteFromDb(channel: NewsChannel) {
        newsChannelsRepository.delete(channel)
    }

    fun update(){
        newsChannelsRepository.update()
    }

    fun insertToDb(channel: NewsChannel) {
        viewModelScope.launch {
            newsChannelsRepository.insert(channel)
        }
    }
}