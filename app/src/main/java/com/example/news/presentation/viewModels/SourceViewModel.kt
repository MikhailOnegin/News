package com.example.news.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.news.domain.pojo.NewsChannel
import com.example.news.domain.pojo.Source
import com.example.news.data.repositoryes.NewsChannelsRepositoryImpl
import com.example.thecats.api.ApiFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class SourceViewModel(application: Application) : AndroidViewModel(application) {

    private val liveDataSource: MutableLiveData<Source> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun loadSource() {

        val disposable = ApiFactory.apiService.getSources().subscribeOn(
            Schedulers.io()
        ).subscribe(
            {
                liveDataSource.postValue(it)
            },
            {
                Log.i("eeee", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun getLiveDataSource(): LiveData<Source> {
        return liveDataSource
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}