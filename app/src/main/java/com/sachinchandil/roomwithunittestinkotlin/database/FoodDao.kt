package com.ace.diettracker.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.ace.diettracker.database.Constants.Companion.FOOD_ID
import com.ace.diettracker.database.Constants.Companion.TABLE_ALL_FOOD_LIST
import com.ace.diettracker.database.entities.Food

/**
 * Created by sachin on 22/5/17.
 */
@Dao
interface FoodDao {

    @Query("SELECT * FROM $TABLE_ALL_FOOD_LIST")
    fun getAllFoodList(): LiveData<List<Food>>

    @Query("SELECT count(*) FROM $TABLE_ALL_FOOD_LIST")
    fun getFoodsCount(): Int

    @Query("SELECT * FROM $TABLE_ALL_FOOD_LIST WHERE $FOOD_ID=:arg0")
    fun getFood(food_id: Long): LiveData<Food>

    @Query("DELETE FROM ${Constants.TABLE_ALL_FOOD_LIST}")
    fun flushFoodData()

    /**
     * Inserts food items in all_food_list
     */
    @Insert(onConflict = REPLACE)
    fun insertFoodList(list: List<Food>)

    @Insert(onConflict = REPLACE)
    fun insertFood(item: Food)

}