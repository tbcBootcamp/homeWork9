package com.example.homwork9

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homwork9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var clothList = mutableListOf<ListItem>()
    private lateinit var itemListAdapter: ListItemAdapter
    private lateinit var filterAdapter: FilterItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapters()
    }


    private fun getFilterList(): MutableList<FilterItem> {
        return mutableListOf(
            FilterItem(R.drawable.ic_empty, "All", R.color.darkBlue, R.color.txtGray, true),
            FilterItem(R.drawable.ic_party, "Party", R.color.darkBlue, R.color.txtGray),
            FilterItem(R.drawable.ic_camping, "Camping", R.color.darkBlue, R.color.txtGray),
            FilterItem(R.drawable.ic_party, "Category1", R.color.darkBlue, R.color.txtGray),
            FilterItem(R.drawable.ic_camping, "Category2", R.color.darkBlue, R.color.txtGray),
            FilterItem(R.drawable.ic_camping, "Category3", R.color.darkBlue, R.color.txtGray)
        )
    }

    private fun clothList(): MutableList<ListItem> {
        return mutableListOf(
            ListItem(R.drawable.photo_red_clothes, "red Clothes", "$120", getFilterList()[0]),
            ListItem(R.drawable.photo_black_clothes, "black Clothes", "$120", getFilterList()[1]),
            ListItem(R.drawable.photo_yellow_clothes, "yellow Clothes", "$140", getFilterList()[2]),
            ListItem(R.drawable.photo_blue_clothes, "blue Clothes", "$160", getFilterList()[3]),
            ListItem(R.drawable.photo_red_clothes, "red Clothes", "$180", getFilterList()[4]),
            ListItem(R.drawable.photo_black_clothes, "black Clothes", "$220", getFilterList()[5]),
            ListItem(R.drawable.photo_red_clothes, "red Clothes", "$120", getFilterList()[0]),
            ListItem(R.drawable.photo_black_clothes, "black Clothes", "$120", getFilterList()[1]),
            ListItem(R.drawable.photo_yellow_clothes, "yellow Clothes", "$140", getFilterList()[2]),
            ListItem(R.drawable.photo_blue_clothes, "blue Clothes", "$160", getFilterList()[3]),
            ListItem(R.drawable.photo_red_clothes, "red Clothes", "$180", getFilterList()[4]),
            ListItem(R.drawable.photo_black_clothes, "black Clothes", "$220", getFilterList()[5]),
            ListItem(R.drawable.photo_yellow_clothes, "red Clothes", "$120", getFilterList()[0]),
            ListItem(R.drawable.photo_black_clothes, "black Clothes", "$120", getFilterList()[1]),
            ListItem(R.drawable.photo_yellow_clothes, "yellow Clothes", "$140", getFilterList()[2]),
            ListItem(R.drawable.photo_blue_clothes, "blue Clothes", "$160", getFilterList()[3]),
            ListItem(R.drawable.photo_red_clothes, "red Clothes", "$180", getFilterList()[4]),
            ListItem(R.drawable.photo_black_clothes, "black Clothes", "$220", getFilterList()[5]))
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupAdapters() {
        itemListAdapter = ListItemAdapter()

        with(binding.rvListItems) {
            adapter = itemListAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }

        itemListAdapter.submitList(clothList())

        with(binding.rvFilters) {
            filterAdapter = FilterItemAdapter { filter ->
                val newFilterList = getFilterList()
                if (!filter.isActive) {
                    newFilterList.find { it.filterName == getFilterList()[0].filterName }?.isActive =
                        false
                    newFilterList.find { it == filter }?.isActive = true
                    filterAdapter.submitList(newFilterList)
                    filterAdapter.notifyDataSetChanged()
                    if (filter.filterName == getFilterList()[0].filterName) {
                        itemListAdapter.submitList(clothList())
                    } else {
                        itemListAdapter.submitList(filterItems(filter))
                    }
                }
            }
            adapter = filterAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            filterAdapter.submitList(getFilterList())
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun filterItems(filter: FilterItem): MutableList<ListItem> {
        val list = clothList()
            list.removeIf { filter.filterName != it.categoryType.filterName }
        return list
    }
 }

