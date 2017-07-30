package com.sachinchandil.roomwithunittestinkotlin

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.ace.diettracker.database.FoodDao
import com.ace.diettracker.database.FoodDatabase
import com.ace.diettracker.database.entities.Food
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by sachin on 30/7/17.
 */
@RunWith(AndroidJUnit4::class)
class FoodDatabaseTest {

    private var foodDao: FoodDao? = null

    @Before
    fun setup() {
        FoodDatabase.TEST_MODE = true
        foodDao = FoodDatabase.getInstance(InstrumentationRegistry.getTargetContext())
    }

    @After
    fun tearDown() {

    }

    @Test
    fun should_Insert_Food_Item() {
        val food = Food(1, "Banana", "Desc")
        foodDao?.insertFood(food)
        val foodTest = getValue(foodDao?.getFood(food.foodId)!!)
        Assert.assertEquals(food.foodName, foodTest.foodName)
    }

    @Test
    fun should_Flush_All_Data(){
        foodDao?.flushFoodData()
        Assert.assertEquals(foodDao?.getFoodsCount(), 0)
    }

    // Copied from stackoverflow
    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}