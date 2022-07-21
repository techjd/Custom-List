package com.techjd.onboarding

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.gson.Gson
import com.techjd.onboarding.adapter.MainRecyclerViewAdapter
import com.techjd.onboarding.data.CustomData
import com.techjd.onboarding.data.Data
import com.techjd.onboarding.data.DataItem
import com.techjd.onboarding.data.finalData.CustomStoreData
import com.techjd.onboarding.data.finalData.SocialStore
import com.techjd.onboarding.data.finalData.SocialStoreItem


class ShopActivity : AppCompatActivity() {

    private lateinit var itemsAdapter: ItemsAdapter
    private lateinit var mainRecyclerViewAdapter: MainRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var fab: ExtendedFloatingActionButton
    private val map: MutableMap<String, MutableList<SocialStoreItem>> = mutableMapOf()
    private val recyclerViewData: MutableList<CustomStoreData> = mutableListOf()

    private val priorityMap: Map<String, Int> = mutableMapOf(
        "Food & Groceries" to 1,
        "Movies & Events" to 2,
        "Shopping" to 3,
        "Fashion" to 4,
        "Commute" to 5,
        "Tickets & Travel" to 6,
        "Hostels & Accomodation" to 7,
        "Education" to 8,
        "Beauty & Cosmetics" to 9,
        "Gifts & Flowers" to 10,
        "Accessories" to 11,
        "Electronics" to 12,
        "Furniture" to 13,
        "Fitness" to 14,
        "Jewellery" to 15,
        "Subscriptions" to 16,
        "Software" to 17,
        "Baby & Kids" to 18,
        "Others" to 19
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)


        recyclerView = findViewById(R.id.recyclerView)

        val text = resources.openRawResource(R.raw.data).bufferedReader().use {
            it.readText()
        }


        val list = Gson().fromJson(text, SocialStore::class.java)

        Log.d("Shop Activity", "onCreate: $list")

        for (data in list) {
            if (map.containsKey(data.category)) {
                map[data.category]!!.add(data)
            } else {
                val list = mutableListOf(data)
                map[data.category] = list
            }
        }

        var cnt = 0
        var currRunningIdx = 0

        for (data in map) {
            val key = data.key // Current Key
            val dataSize = data.value.size // Current List Size of Key
            val individualCustomDataSize = (dataSize / 4) + 1 // List Size of Custom Data List
            val customDataList =
                arrayOfNulls<ArrayList<SocialStoreItem>>(individualCustomDataSize) // List of List

            for (i in 0 until data.value.size) {

                Log.d(
                    "INDEX ",
                    "${i} - ${cnt} -  ${currRunningIdx} - ${individualCustomDataSize} - ${key} - ${dataSize}"
                )

                if (cnt == 3) {
                    if (customDataList[currRunningIdx] == null) {
                        val innerList = arrayListOf<SocialStoreItem>()
                        innerList.add(data.value[i])
                        customDataList[currRunningIdx] = innerList
                    } else {
                        customDataList[currRunningIdx]!!.add(data.value[i])
                    }
                    currRunningIdx += 1
                    cnt = 0
                } else {
                    if (customDataList[currRunningIdx] == null) {
                        val innerList = arrayListOf<SocialStoreItem>()
                        innerList.add(data.value[i])
                        customDataList[currRunningIdx] = innerList
                    } else {
                        customDataList[currRunningIdx]!!.add(data.value[i])
                    }
                    cnt += 1
                }
            }

            cnt = 0
            currRunningIdx = 0

            if (priorityMap[key] != null) {
                recyclerViewData.add(
                    CustomStoreData(
                        priorityMap[key]!!,
                        key,
                        customDataList
                    )
                )
            }
        }

        recyclerViewData.sortedWith(object : Comparator<CustomStoreData> {
            override fun compare(p0: CustomStoreData?, p1: CustomStoreData?): Int {
                if (p0!!.priority < p1!!.priority) {
                    return 1
                }
                return 0
            }
        })


        mainRecyclerViewAdapter = MainRecyclerViewAdapter(this)
        mainRecyclerViewAdapter.list = recyclerViewData

        recyclerView.adapter = mainRecyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        fab = findViewById(R.id.floatingActionButton)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    fab.extend()
                }
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    fab.shrink()
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        })

        fab.setOnClickListener {
            Toast.makeText(this, "Show Categories", Toast.LENGTH_SHORT).show()
        }

    }
}