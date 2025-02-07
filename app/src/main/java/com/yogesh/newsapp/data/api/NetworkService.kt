package com.yogesh.newsapp.data.api

import com.yogesh.newsapp.data.model.SourceResponse
import com.yogesh.newsapp.data.model.TopHeadlines
import com.yogesh.newsapp.utils.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {
    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadline(@Query("country") country: String): TopHeadlines

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getSources(): SourceResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getHeadlineByResources(@Query("sources") sources: String): TopHeadlines
}