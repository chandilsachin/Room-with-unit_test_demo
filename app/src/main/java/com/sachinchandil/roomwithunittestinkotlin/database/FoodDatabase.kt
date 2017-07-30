package com.ace.diettracker.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.ace.diettracker.database.entities.Food

/**
 * Created by sachin on 22/5/17.
 */

@Database(entities = arrayOf(Food::class), version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        var TEST_MODE = false
        private val databaseName = "database_name"

        private var db: FoodDatabase? = null
        private var dbInstance: FoodDao? = null

        fun getInstance(context: Context): FoodDao {
            if (dbInstance == null) {
                if (TEST_MODE) {
                    db = Room.inMemoryDatabaseBuilder(context, FoodDatabase::class.java).allowMainThreadQueries().build()
                    dbInstance = db?.foodDao()
                } else {
                    db = Room.databaseBuilder(context, FoodDatabase::class.java, databaseName).build()
                    dbInstance = db?.foodDao()
                }
            }
            return dbInstance!!;
        }

        private fun close() {
            db?.close()
        }
    }
}
