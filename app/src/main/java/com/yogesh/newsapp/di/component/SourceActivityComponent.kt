package com.yogesh.newsapp.di.component

import com.yogesh.newsapp.di.ActivityScope
import com.yogesh.newsapp.di.module.SourcesModule
import com.yogesh.newsapp.ui.sources.SourcesActivity
import dagger.Component
@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [SourcesModule::class])
interface SourceActivityComponent {
    fun inject(activity: SourcesActivity)
}