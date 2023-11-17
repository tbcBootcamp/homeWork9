package com.example.homwork9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homwork9.databinding.ListItemBinding

class ListItemAdapter : RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {

    private var list: List<ListItem> = emptyList()

    fun submitList(list: List<ListItem>) {
        this.list = list
        notifyDataSetChanged()
    }
    inner class ListItemViewHolder(binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image = binding.ivPhoto
        private val title = binding.tvTitle
        private val price = binding.tvPrice
        fun bind(model: ListItem) {
            image.setImageResource(model.image)
            title.text = model.title
            price.text = model.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }
}