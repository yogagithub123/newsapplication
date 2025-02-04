package com.yogesh.newsapp.di.component

import com.yogesh.newsapp.di.ActivityScope
import com.yogesh.newsapp.di.module.TopHeadlineActivityModule
import com.yogesh.newsapp.ui.topheadlines.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [TopHeadlineActivityModule::class] )
interface TopHeadlineActivityComponent {
    fun inject(activity: TopHeadlineActivity)
}