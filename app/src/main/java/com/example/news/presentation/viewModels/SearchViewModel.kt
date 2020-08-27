package com.example.news.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.domain.pojo.Everything
import com.example.thecats.api.ApiFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val liveDataEverything = MutableLiveData<Everything>()
    private val compositeDisposable = CompositeDisposable()

    fun loadSource(title: String) {

        val disposable = ApiFactory.apiService.getEverything(title).subscribeOn(
            Schedulers.io()
        ).subscribe(
            {
                liveDataEverything.postValue(it)
            },
            {
                Log.i("eeee", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun getLiveDataEverything(): LiveData<Everything> {
        return liveDataEverything
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}