package com.yogesh.newsapp.ui.sources

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogesh.newsapp.NewsApplication
import com.yogesh.newsapp.R
import com.yogesh.newsapp.data.model.Article
import com.yogesh.newsapp.databinding.ActivityNewsBySourcesBinding
import com.yogesh.newsapp.di.component.DaggerNewsBySourcesActivityComponent
import com.yogesh.newsapp.di.component.NewsBySourcesActivityComponent
import com.yogesh.newsapp.di.module.NewsBySourceModule
import com.yogesh.newsapp.ui.common.UiState
import jakarta.inject.Inject
import kotlinx.coroutines.launch

class NewsBySourcesActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: NewsBySourceViewModel
    @Inject
    lateinit var adapter: NewsBySourcesAdapter
    private lateinit var binding:ActivityNewsBySourcesBinding
    private lateinit var newsBySourcesActivityComponent: NewsBySourcesActivityComponent
    private val EXTRAS_SOURCE_NAME="source"

    fun getStartIntent(context: Context, source:String): Intent {
        return Intent(context, NewsBySourcesActivity::class.java).apply {
            putExtra(EXTRAS_SOURCE_NAME,source)

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        binding= ActivityNewsBySourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        setupObserver()
        viewModel.fetchNewsByResources(intent.getStringExtra(EXTRAS_SOURCE_NAME)!!)
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
        adapter.itemClickListener={
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(it.url))
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                when (it) {
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        if(it.data.isEmpty()){
                            Toast.makeText(this@NewsBySourcesActivity, R.string.no_record_found, Toast.LENGTH_LONG)
                                .show()
                        }else {
                            renderList(it.data)
                            Log.d("Data***", "${it.data.size}")
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                    }
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@NewsBySourcesActivity, it.message, Toast.LENGTH_LONG)
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
        newsBySourcesActivityComponent= DaggerNewsBySourcesActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .newsBySourceModule(NewsBySourceModule(this))
            .build()
        newsBySourcesActivityComponent.inject(this)
    }
}