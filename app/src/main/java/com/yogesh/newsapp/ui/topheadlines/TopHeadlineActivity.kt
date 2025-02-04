package com.yogesh.newsapp.ui.topheadlines

import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogesh.newsapp.NewsApplication
import com.yogesh.newsapp.data.model.Article
import com.yogesh.newsapp.databinding.ActivityTopHeadlineBinding
import com.yogesh.newsapp.di.component.DaggerTopHeadlineActivityComponent
import com.yogesh.newsapp.di.component.TopHeadlineActivityComponent
import com.yogesh.newsapp.di.module.TopHeadlineActivityModule
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.ui.base.UiState
import javax.inject.Inject

class TopHeadlineActivity : AppCompatActivity() {
    companion object{
        private val EXTRAS_COUNTRY="country"
        fun getStartIntent(context: Context,country:String):Intent{
            return Intent(context,TopHeadlineActivity::class.java).apply {
                putExtra(EXTRAS_COUNTRY,country)
            }
        }
    }
    private lateinit var binding:ActivityTopHeadlineBinding
    private lateinit var topHeadlineActivityComponent: TopHeadlineActivityComponent
    @Inject
    lateinit var adapter:TopHeadlineAdapter
    @Inject
    lateinit var viewModel: TopHeadlineViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        binding=ActivityTopHeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        setupObserver()
    }

    private fun setUpUi() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            Log.d("Data***","${it.data.size}")
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@TopHeadlineActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
    }

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    private fun getDependencies() {
        topHeadlineActivityComponent=DaggerTopHeadlineActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .topHeadlineActivityModule(TopHeadlineActivityModule(this))
            .build()
        topHeadlineActivityComponent.inject(this)
    }
}