package com.example.homwork9

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.homwork9.databinding.FilterItemBinding

class FilterItemAdapter (private val click: (FilterItem) -> Unit) :
    RecyclerView.Adapter<FilterItemAdapter.FilterListVH>() {
    private var list: List<FilterItem> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<FilterItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class FilterListVH(binding: FilterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val img = binding.ivFilter
        private val filterType = binding.tvFilter
        private val cv = binding.cvFilterItem
        fun bind(model: FilterItem, click: (FilterItem) -> Unit) {
            img.setImageResource(model.img)
            filterType.text = model.filterName
            filterType.setTextColor(model.txtColor)
            itemView.setOnClickListener {
                click(model)
            }
            if (model.isActive) {
                filterType.setTextColor(ContextCompat.getColor(filterType.context, R.color.white))
                cv.setBackgroundColor(ContextCompat.getColor(filterType.context, R.color.green))
            } else {
                filterType.setTextColor(ContextCompat.getColor(filterType.context, R.color.txtGray))
                cv.setBackgroundColor(ContextCompat.getColor(filterType.context, R.color.darkBlue))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterListVH {
        val binding = FilterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterListVH(binding)
    }

    override fun onBindViewHolder(holder: FilterListVH, position: Int) {
        val item = list[position]
        holder.bind(item, click)
    }

    override fun getItemCount(): Int = list.size
}