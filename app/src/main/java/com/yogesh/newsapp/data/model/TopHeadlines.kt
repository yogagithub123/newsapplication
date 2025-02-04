package com.yogesh.newsapp.data.model

import com.google.gson.annotations.SerializedName


data class TopHeadlines(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults:Int=0,
    @SerializedName("articles")
    val articles: ArrayList<Article>
)
