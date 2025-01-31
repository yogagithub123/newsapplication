package com.yogesh.newsapp

import android.app.Application
import com.yogesh.newsapp.di.component.ApplicationComponent
import com.yogesh.newsapp.di.component.DaggerApplicationComponent
import com.yogesh.newsapp.di.module.ApplicationModule

class NewsApplication:Application() {
   lateinit var applicationComponent:ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        getDependencies()
    }

    private fun getDependencies() {
       applicationComponent=DaggerApplicationComponent
           .builder()
           .applicationModule(ApplicationModule(this))
           .build()
        applicationComponent.inject(this)
    }
}