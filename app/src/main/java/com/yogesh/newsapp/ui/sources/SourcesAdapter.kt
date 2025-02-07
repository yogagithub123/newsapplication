package com.yogesh.newsapp.ui.sources


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yogesh.newsapp.data.model.Sources
import com.yogesh.newsapp.databinding.SourcesRecyclerItemBinding
import com.yogesh.newsapp.utils.ItemClickListener


class SourcesAdapter(private val arrayList: ArrayList<Sources>):RecyclerView.Adapter<SourcesAdapter.DataViewHolder>()  {
    lateinit var itemClickListener: ItemClickListener<Sources>
    class DataViewHolder(private val binding: SourcesRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sources: Sources,itemClickListener: ItemClickListener<Sources>) {
            binding.textViewSource.text = sources.name
            binding.cardViewSource.setOnClickListener{
                itemClickListener(sources)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            SourcesRecyclerItemBinding.inflate(
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
        holder.bind(arrayList[position],itemClickListener)
    }

    fun addData(list: List<Sources>) {
        arrayList.addAll(list)
    }

}