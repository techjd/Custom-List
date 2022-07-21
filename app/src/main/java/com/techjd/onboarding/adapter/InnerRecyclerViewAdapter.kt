package com.techjd.onboarding.adapter

import android.content.Context
import android.content.Intent
import android.icu.number.IntegerWidth
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techjd.onboarding.data.CustomData
import com.techjd.onboarding.data.DataItem
import com.techjd.onboarding.data.finalData.SocialStoreItem
import com.techjd.onboarding.databinding.ItemHorizontalLayoutBinding
import com.techjd.onboarding.databinding.ItemRowBinding
import java.util.ArrayList

class InnerRecyclerViewAdapter(
    private val context: Context, private val size: Integer
) :
    RecyclerView.Adapter<InnerRecyclerViewAdapter.InnerRecyclerViewHolder>() {

    var list: Array<ArrayList<SocialStoreItem>?> = arrayOfNulls(size as Int)

    inner class InnerRecyclerViewHolder(val itemRowBinding: ItemRowBinding) :
        RecyclerView.ViewHolder(itemRowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerRecyclerViewHolder {
        val view = ItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InnerRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: InnerRecyclerViewHolder, position: Int) {
        val data = list[position]
//        Log.d("INNER DATA ", "onBindViewHolder: $data \n position $position")

        with(holder.itemRowBinding) {
            val itemRecyclerViewAdapter = ItemRecyclerViewAdapter(context)
            if (data != null) {
                itemRecyclerViewAdapter.list = data
            }

            rowRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rowRecyclerView.adapter = itemRecyclerViewAdapter
            rowRecyclerView.setHasFixedSize(true)

            (rowRecyclerView.layoutParams as ViewGroup.MarginLayoutParams).apply {
                if(position == 0) {
                    topMargin = 0
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return size as Int
    }
}