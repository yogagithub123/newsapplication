package com.yogesh.newsapp.di.module

import android.content.Context
import com.yogesh.newsapp.NewsApplication
import com.yogesh.newsapp.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: NewsApplication) {

    @ApplicationContext
    @Provides
    fun getApplicationContext():Context{
        return application
    }
}