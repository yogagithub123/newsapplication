package com.yogesh.newsapp.ui.sources

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
import com.yogesh.newsapp.data.model.Sources
import com.yogesh.newsapp.databinding.ActivitySourcesBinding
import com.yogesh.newsapp.di.component.DaggerSourceActivityComponent
import com.yogesh.newsapp.di.component.SourceActivityComponent
import com.yogesh.newsapp.di.module.SourcesModule
import com.yogesh.newsapp.ui.topheadlines.TopHeadlineActivity
import com.yogesh.newsapp.utils.Constant.RESOURCES
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import com.yogesh.newsapp.ui.common.UiState

class SourcesActivity : AppCompatActivity() {
    companion object{
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SourcesActivity::class.java)
        }
    }
    private lateinit var binding:ActivitySourcesBinding
    private lateinit var sourcesComponent: SourceActivityComponent
    @Inject
    lateinit var adapter: SourcesAdapter
    @Inject
    lateinit var viewModel: SourcesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        binding=ActivitySourcesBinding.inflate(layoutInflater)
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
        adapter.itemClickListener={
            val intent= TopHeadlineActivity.getStartIntent(this,RESOURCES,it.name)
            startActivity(intent)
        }
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
                        Toast.makeText(this@SourcesActivity, it.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    private fun renderList(sourcesList: List<Sources>) {
        adapter.addData(sourcesList)
        adapter.notifyDataSetChanged()
    }
    private fun getDependencies() {
        sourcesComponent=DaggerSourceActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .sourcesModule(SourcesModule(this))
            .build()
        sourcesComponent.inject(this)
    }
}