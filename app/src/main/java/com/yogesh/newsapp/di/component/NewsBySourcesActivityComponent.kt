package com.yogesh.newsapp.di.component

import com.yogesh.newsapp.di.ActivityScope
import com.yogesh.newsapp.di.module.NewsBySourceModule
import com.yogesh.newsapp.ui.sources.NewsBySourcesActivity
import dagger.Component
@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [NewsBySourceModule::class])
interface NewsBySourcesActivityComponent {
    fun inject(activity: NewsBySourcesActivity)
}