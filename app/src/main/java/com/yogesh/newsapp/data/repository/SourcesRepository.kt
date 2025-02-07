package com.yogesh.newsapp.data.repository

import com.yogesh.newsapp.data.api.NetworkService
import com.yogesh.newsapp.data.model.Sources
import com.yogesh.newsapp.di.ActivityScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityScope
class SourcesRepository @Inject constructor(private val networkService: NetworkService){
    fun getSources(): Flow<List<Sources>> {
        return flow {
            emit(networkService.getSources())
        }.map { it.sources }
    }
}