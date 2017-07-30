package com.ace.diettracker.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.ace.diettracker.database.Constants

/**
 * Created by sachin on 22/5/17.
 */
@Entity(tableName = Constants.TABLE_ALL_FOOD_LIST)
class Food(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = Constants.FOOD_ID) var foodId: Long = 0,
           @ColumnInfo(name = Constants.FOOD_NAME) var foodName: String = "",
           @ColumnInfo(name = Constants.FOOD_DESC) var foodDesc: String = "")