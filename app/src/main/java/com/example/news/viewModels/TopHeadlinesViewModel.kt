package com.example.news.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.pojo.topHeadlines.TopHeadlines
import com.example.thecats.api.ApiFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder

class TopHeadlinesViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val liveDataTopHeadlines: MutableLiveData<TopHeadlines?> = MutableLiveData()

    fun getLiveDataTopHeadlines(): LiveData<TopHeadlines?> {
        return liveDataTopHeadlines
    }

    fun loadTopHeadlines(sourceId: List<String>) {
        val stringBuilderSources = StringBuilder()
        sourceId.forEach {
            if (it != sourceId.last())
                stringBuilderSources.append("$it,")
            else {
                stringBuilderSources.append(it)
            }
        }

        if (stringBuilderSources.isNotEmpty()) {
            val disposable =
                ApiFactory.apiService.getTopHeadlines(stringBuilderSources.toString()).subscribeOn(
                    Schedulers.io()
                ).subscribe(
                    { topHeadlines ->
                        liveDataTopHeadlines.postValue(topHeadlines)
                    },
                    { throwable ->
                        Log.i("eeee", throwable.message.toString())
                        liveDataTopHeadlines.postValue(null)
                    })
            compositeDisposable.add(disposable)
        }


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}