package com.yogesh.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yogesh.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cardViewTopHeadlines.setOnClickListener{ view ->

        }
        binding.cardViewNewsResources.setOnClickListener{ view ->

        }
        binding.cardViewCountries.setOnClickListener{ view ->

        }
        binding.cardViewLanguages.setOnClickListener{ view ->

        }
        binding.cardViewSearch.setOnClickListener{ view ->

        }

    }
}