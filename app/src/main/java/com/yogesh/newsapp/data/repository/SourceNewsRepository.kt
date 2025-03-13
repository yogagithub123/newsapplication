package com.yogesh.newsapp.data.repository

import com.yogesh.newsapp.data.api.NetworkService
import com.yogesh.newsapp.data.model.Article
import com.yogesh.newsapp.di.ActivityScope
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@ActivityScope
class SourceNewsRepository @Inject constructor(private val networkService: NetworkService) {
     fun getNewsByResources(source:String):Flow<List<Article>>{
         return flow{
             emit(networkService.getHeadlineByResources(source))
         }.map { it.articles }
     }

}
