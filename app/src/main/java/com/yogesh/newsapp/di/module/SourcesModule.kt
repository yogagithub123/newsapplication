package com.yogesh.newsapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.yogesh.newsapp.data.repository.SourcesRepository
import com.yogesh.newsapp.di.ActivityContext
import com.yogesh.newsapp.ui.sources.SourcesActivity
import com.yogesh.newsapp.ui.sources.SourcesAdapter
import com.yogesh.newsapp.ui.sources.SourcesViewModel
import dagger.Module
import dagger.Provides
import com.yogesh.newsapp.ui.common.ViewModelProviderFactory

@Module
class SourcesModule(private val activity: SourcesActivity) {
    @ActivityContext
    @Provides
    fun getContext()=activity

    @Provides
    fun getSourcesAdapter()=SourcesAdapter(ArrayList())

    @Provides
    fun provideSourcesListViewModel(sourcesRepository:SourcesRepository): SourcesViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(SourcesViewModel::class) {
                SourcesViewModel(sourcesRepository)
            })[SourcesViewModel::class.java]
    }
}