package com.example.thecats.api

import com.example.news.domain.pojo.Everything
import com.example.news.domain.pojo.Source
import com.example.news.domain.pojo.TopHeadlines
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("sources")
    fun getSources(
        @Query(COUNTRY) country: String = "us",
        @Query(LANGUAGE) language: String = "en",
        @Query(API_KEY) apiKey: String = "4c72e2adddf14f639c682a7e33bc3fb6"

    ): Single<Source>

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query(SOURCES) source: String,
        @Query(PAGE_SIZE) pageSize: String = "50",
        @Query(API_KEY) apiKey: String = "4c72e2adddf14f639c682a7e33bc3fb6"
    ): Single<TopHeadlines>

    @GET("everything")
    fun getEverything(
        @Query(TITLE) title: String,
        @Query(API_KEY) apiKey: String ="4c72e2adddf14f639c682a7e33bc3fb6"
    ):Single<Everything>


    companion object {
        private const val TITLE ="q"
        private const val LANGUAGE = "language"
        private const val API_KEY = "apiKey"
        private const val SOURCES = "sources"
        private const val COUNTRY = "country"
        private const val PAGE_SIZE = "pageSize"
    }
}