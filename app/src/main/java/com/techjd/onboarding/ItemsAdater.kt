package com.techjd.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techjd.onboarding.data.DataItem
import com.techjd.onboarding.databinding.ItemCardShopBinding

class ItemsAdapter(
    private val data: ArrayList<DataItem>,
    private val context: Context
) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {


    inner class ItemsViewHolder(val itemCardShopBinding: ItemCardShopBinding) :
        RecyclerView.ViewHolder(itemCardShopBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = ItemCardShopBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item = data[position]
        with(holder.itemCardShopBinding) {
            brand.text = item.merchant
            uptoDiscount.text = "Upto ${item.payout} Gold"
            Glide.with(context).load(item.logo).into(logo);
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}