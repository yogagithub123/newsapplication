package com.yogesh.newsapp.ui.topheadlines

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogesh.newsapp.data.model.Article
import com.yogesh.newsapp.databinding.TopHeadlineRecyclerItemBinding

class TopHeadlineAdapter(private val arrayList: ArrayList<Article>):RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>() {


    class DataViewHolder(private val binding: TopHeadlineRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.urlToImage)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(article.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
       return DataViewHolder(
            TopHeadlineRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    fun addData(list: List<Article>) {
        arrayList.clear()
        arrayList.addAll(list)
    }
}