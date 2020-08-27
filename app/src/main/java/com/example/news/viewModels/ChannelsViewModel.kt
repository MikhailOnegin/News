package com.example.news.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.news.pojo.source.NewsChannel
import com.example.news.pojo.source.Source
import com.example.news.repositoryes.NewsChannelsRepository
import com.example.thecats.api.ApiFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ChannelsViewModel(application: Application) : AndroidViewModel(application) {

    private val newsChannelsRepository = NewsChannelsRepository(application)
    val liveDataSource:MutableLiveData<Source> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()


    fun loadSource() {

        val disposable = ApiFactory.apiService.getSources().subscribeOn(
            Schedulers.io()).subscribe(
            {
                liveDataSource.postValue(it)
            },
            {
                Log.i("eeee",it.message.toString())
            })
    compositeDisposable.add(disposable)}

    fun getLiveDataChannels():LiveData<List<NewsChannel>>{
        return newsChannelsRepository.getAllChannels()
    }

    fun deleteFromDb(channel: NewsChannel){
        newsChannelsRepository.delete(channel)
    }

    fun insertToDb(channel: NewsChannel) {
        viewModelScope.launch {
            newsChannelsRepository.insert(channel)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}