package com.techjd.onboarding.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techjd.onboarding.data.CustomData
import com.techjd.onboarding.data.finalData.CustomStoreData
import com.techjd.onboarding.databinding.ItemRowBinding
import com.techjd.onboarding.databinding.ItemRowWithCategoryBinding

class MainRecyclerViewAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {

    var list: MutableList<CustomStoreData> = mutableListOf()

    inner class MainRecyclerViewHolder(val itemRowWithCategoryBinding: ItemRowWithCategoryBinding) :
        RecyclerView.ViewHolder(itemRowWithCategoryBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val view = ItemRowWithCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        val data = list[position]

        if (data.title == "Health & Beauty") {
            Log.d("DATA ", " ${data} ")
        }

        with(holder) {
            itemRowWithCategoryBinding.categoryName.text = data.title

            val innerRecyclerViewAdapter =
                InnerRecyclerViewAdapter(context, data.data.size as Integer)

            innerRecyclerViewAdapter.list = data.data

            itemRowWithCategoryBinding.recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemRowWithCategoryBinding.recyclerView.adapter = innerRecyclerViewAdapter
            itemRowWithCategoryBinding.recyclerView.setHasFixedSize(true)

            (itemRowWithCategoryBinding.mainLinearLayout.layoutParams as ViewGroup.MarginLayoutParams).apply {
                if(position == 0) {
                    topMargin = 0
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}