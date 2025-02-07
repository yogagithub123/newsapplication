package com.yogesh.newsapp.data.repository

import com.yogesh.newsapp.data.api.NetworkService
import com.yogesh.newsapp.data.model.Article
import com.yogesh.newsapp.di.ActivityScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityScope
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadline(country))
        }.map { it.articles }
    }

    fun getHeadlinesByResources(resources: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getHeadlineByResources(resources))
        }.map { it.articles }
    }
}