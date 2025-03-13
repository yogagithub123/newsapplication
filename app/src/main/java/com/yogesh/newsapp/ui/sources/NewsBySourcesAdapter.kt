package com.yogesh.newsapp.ui.sources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogesh.newsapp.data.model.Article
import com.yogesh.newsapp.databinding.SourceHeadlineRecyclerItemBinding
import com.yogesh.newsapp.utils.ItemClickListener


class NewsBySourcesAdapter (private val arrayList: ArrayList<Article>): RecyclerView.Adapter<NewsBySourcesAdapter.DataViewHolder>()  {
   lateinit var itemClickListener: ItemClickListener<Article>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            SourceHeadlineRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(arrayList[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun addData(list: List<Article>) {
        arrayList.clear()
        arrayList.addAll(list)
    }

    class DataViewHolder(private val binding: SourceHeadlineRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article, itemClickListener: ItemClickListener<Article>) {
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
}