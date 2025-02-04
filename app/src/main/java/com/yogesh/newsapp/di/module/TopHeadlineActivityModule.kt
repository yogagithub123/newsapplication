package com.yogesh.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.yogesh.newsapp.data.repository.TopHeadlineRepository
import com.yogesh.newsapp.di.ActivityContext
import com.yogesh.newsapp.ui.topheadlines.TopHeadlineAdapter
import com.yogesh.newsapp.ui.topheadlines.TopHeadlineViewModel
import dagger.Module
import dagger.Provides
import me.amitshekhar.newsapp.ui.base.ViewModelProviderFactory
import java.util.ArrayList

@Module
class TopHeadlineActivityModule(private val activity: AppCompatActivity) {
    @ActivityContext
    @Provides
    fun getActivityContext(): Context {
        return activity
    }

    @Provides
    fun provideTopHeadlineAdapter():TopHeadlineAdapter{
        return TopHeadlineAdapter(ArrayList())
    }

    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

}