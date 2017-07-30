package com.ace.diettracker.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.ace.diettracker.adapters.FoodListAdapter
import com.ace.diettracker.database.entities.Food
import com.ace.diettracker.util.BaseFragment
import com.ace.diettracker.util.annotation.RequiresTagName
import com.ace.diettracker.util.initViewModel
import com.ace.diettracker.util.setSupportActionBar
import com.ace.diettracker.view_model.FoodListViewModel
import com.sachinchandil.roomwithunittestinkotlin.R
import kotlinx.android.synthetic.main.fragment_add_food.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.jetbrains.anko.doAsync

@RequiresTagName("FoodListFragment")
class FoodListFragment : BaseFragment() {

    val model: FoodListViewModel by lazy {
        initViewModel(FoodListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_add_food, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        init()
        prepareFoodItemList()
        super.onViewCreated(view, savedInstanceState)
    }

    fun init() {
        setSupportActionBar(my_toolbar)
        activity.setTitle(R.string.foodList)
        recyclerViewFoodList.layoutManager = LinearLayoutManager(context)
        setupInitDbItems()
    }

    val observer: Observer<List<Food>> = Observer { list ->
        list?.let {
            if (list.isNotEmpty()) {
                adapter?.foodList = list
                adapter?.filter?.filter("")
            }
        }
    }

    var adapter: FoodListAdapter? = null
    fun prepareFoodItemList() {
        adapter = FoodListAdapter(context)
        recyclerViewFoodList.adapter = adapter

        model.allFoodList.observe(this, observer)
        doAsync {
            model.getAllFoodList()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    companion object {

        val SELECTED_FOOD_ID = "selectedIndex"
        val CODE_FOOD_SELECTION = 1

        fun getInstance(activity: FragmentActivity? = null): Fragment {
            var fragment = findInstance(activity!!)
            if (fragment == null) {
                fragment = FoodListFragment()
            }
            return fragment
        }
    }

    fun setupInitDbItems() {
        doAsync {
            val list = ArrayList<Food>()
            list.add(Food(foodId = 1, foodName = "Egg", foodDesc = ""))
            list.add(Food(foodId = 2, foodName = "Banana", foodDesc = ""))
            list.add(Food(foodId = 3, foodName = "Milk", foodDesc = ""))
            list.add(Food(foodId = 4, foodName = "Cashew", foodDesc = ""))
            list.add(Food(foodId = 5, foodName = "Almonds", foodDesc = ""))
            model.insertFoodList(list)
        }

    }
}