package com.yogesh.newsapp.di.module


import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.yogesh.newsapp.data.repository.SourceNewsRepository
import com.yogesh.newsapp.di.ActivityContext
import com.yogesh.newsapp.ui.common.ViewModelProviderFactory
import com.yogesh.newsapp.ui.sources.NewsBySourceViewModel
import com.yogesh.newsapp.ui.sources.NewsBySourcesAdapter
import com.yogesh.newsapp.ui.sources.NewsBySourcesActivity
import dagger.Module
import dagger.Provides


@Module
class NewsBySourceModule(private val activity: NewsBySourcesActivity) {

    @ActivityContext
    @Provides
    fun getActivityContext():Context=activity

    @Provides
    fun getAdapter()=NewsBySourcesAdapter(ArrayList())

    @Provides
    fun provideNewsBySourcesListViewModel(sourceNewsRepository: SourceNewsRepository): NewsBySourceViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsBySourceViewModel::class) {
                NewsBySourceViewModel(sourceNewsRepository)
            })[NewsBySourceViewModel::class.java]
    }
}