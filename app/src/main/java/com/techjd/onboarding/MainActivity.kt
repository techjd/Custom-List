package com.techjd.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var onBoardingAdapter: OnBoardingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2 = findViewById(R.id.pager)
        onBoardingAdapter = OnBoardingAdapter(this, 4)
        viewPager2.adapter = onBoardingAdapter

        viewPager2.isUserInputEnabled = true

//        viewPager2.setCurrentItem(viewPager2.currentItem, true)

    }

    fun nextPage() {
        viewPager2.setCurrentItem(viewPager2.currentItem + 1, true)
    }

    fun lastPage() {
        viewPager2.setCurrentItem(3, true)
    }
}