package com.yogesh.newsapp.ui.topheadlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogesh.newsapp.data.model.Article
import com.yogesh.newsapp.databinding.TopHeadlineRecyclerItemBinding
import com.yogesh.newsapp.utils.ItemClickListener

class TopHeadlineAdapter(private val arrayList: ArrayList<Article>):RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<Article>
    class DataViewHolder(private val binding: TopHeadlineRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article,itemClickListener: ItemClickListener<Article>) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.urlToImage)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                itemClickListener(article)
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
        holder.bind(arrayList[position], itemClickListener)
    }

    fun addData(list: List<Article>) {
        arrayList.clear()
        arrayList.addAll(list)
    }
}