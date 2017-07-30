package com.ace.diettracker.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.ace.diettracker.database.FoodDatabase
import com.ace.diettracker.database.entities.Food

/**
 * Created by sachin on 27/5/17.
 */
class LocalFoodRepository {

    fun getAllFodList(context: Context): LiveData<List<Food>> {
        return FoodDatabase.getInstance(context).getAllFoodList()
    }

    fun insertFood(context: Context, food: Food) {
        FoodDatabase.getInstance(context).insertFood(food)
    }

    fun insertFoodList(context: Context, foodList: List<Food>){
        FoodDatabase.getInstance(context).insertFoodList(foodList)
    }
}