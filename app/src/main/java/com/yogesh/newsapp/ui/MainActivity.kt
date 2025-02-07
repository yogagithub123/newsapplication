package com.yogesh.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yogesh.newsapp.databinding.ActivityMainBinding
import com.yogesh.newsapp.ui.sources.SourcesActivity
import com.yogesh.newsapp.ui.topheadlines.TopHeadlineActivity
import com.yogesh.newsapp.utils.Constant

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cardViewTopHeadlines.setOnClickListener{ view ->
            val intent=TopHeadlineActivity.getStartIntent(this,"COUNTRY","us")
            startActivity(intent)
        }
        binding.cardViewNewsResources.setOnClickListener{ view ->
            val intent=SourcesActivity.getStartIntent(this)
            startActivity(intent)
        }
        binding.cardViewCountries.setOnClickListener{ view ->

        }
        binding.cardViewLanguages.setOnClickListener{ view ->

        }
        binding.cardViewSearch.setOnClickListener{ view ->

        }

    }
}