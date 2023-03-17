package com.hakancevik.moviesapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.hakancevik.moviesapp.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "Top Rated"
                }

                1 -> {
                    tab.text = "Coming Up"
                }
            }
        }.attach()


    }
}

