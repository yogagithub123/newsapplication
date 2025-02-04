package com.yogesh.newsapp.di.component

import com.yogesh.newsapp.NewsApplication
import com.yogesh.newsapp.data.api.NetworkService
import com.yogesh.newsapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
     fun inject(application: NewsApplication)

     fun getNetworkService():NetworkService
}