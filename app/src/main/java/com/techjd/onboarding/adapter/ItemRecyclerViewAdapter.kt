package com.techjd.onboarding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.techjd.onboarding.data.CustomData
import com.techjd.onboarding.data.DataItem
import com.techjd.onboarding.data.finalData.SocialStoreItem
import com.techjd.onboarding.databinding.CardBinding
import com.techjd.onboarding.databinding.ItemHorizontalLayoutBinding
import com.techjd.onboarding.databinding.ItemRowWithCategoryBinding
import java.util.ArrayList

class ItemRecyclerViewAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemRecyclerViewHolder>() {

    var list: ArrayList<SocialStoreItem> = arrayListOf()

    inner class ItemRecyclerViewHolder(val cardBinding: CardBinding) :
        RecyclerView.ViewHolder(cardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        val view = CardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
        val data = list[position]
        with(holder.cardBinding) {
            brandName.text = data.merchant
            val discountTextToSet = "Upto ${data.payout} Gold"

            uptoDiscount.text = discountTextToSet

            if (data.customUrl == null) {
                logo.load(data.logo)
            } else {
                logo.load(data.customUrl)
                logo.scaleType = ImageView.ScaleType.FIT_XY
            }

            (mainLayout.layoutParams as ViewGroup.MarginLayoutParams).apply {
                if (position == 0) {
                    leftMargin = 0
                }
                if (position == list.size - 1) {
                    rightMargin = 16
                }
            }

            (cardView.layoutParams as ViewGroup.MarginLayoutParams).apply {
                if (position == 0) {
                    leftMargin = 0
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}