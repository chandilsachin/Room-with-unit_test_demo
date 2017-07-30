package com.sachinchandil.roomwithunittestinkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ace.diettracker.ui.FoodListFragment
import com.ace.diettracker.util.loadFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(R.id.frameLayoutFragment, FoodListFragment.getInstance(activity = this))
    }
}
