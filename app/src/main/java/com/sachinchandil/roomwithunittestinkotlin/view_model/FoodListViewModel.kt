package com.ace.diettracker.view_model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.ace.diettracker.database.entities.Food
import com.ace.diettracker.repository.LocalFoodRepository

/**
 * Created by sachin on 24/5/17.
 */
class FoodListViewModel(application: Application):AndroidViewModel(application){
    private val repo = LocalFoodRepository()
    var allFoodList:LiveData<List<Food>> = MutableLiveData()

    init {
        getAllFoodList()
    }

    fun getAllFoodList(){
        allFoodList = repo.getAllFodList(getApplication())
    }

    fun insertFoodNameDesc(food: Food){
        repo.insertFood(getApplication(), food)
    }

    fun insertFoodList(foodList: List<Food>){
        repo.insertFoodList(getApplication(), foodList)
    }
}