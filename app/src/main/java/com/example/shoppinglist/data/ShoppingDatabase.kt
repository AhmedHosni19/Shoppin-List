package com.example.shoppinglist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities =[ShoppingItem::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingDatabase:RoomDatabase() {
    abstract fun shoppingItemDao():ShoppingItemDao

    companion object{
        @Volatile
        private var INSTANCE:ShoppingDatabase?=null

        fun getDatabase(context: Context):ShoppingDatabase{
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingDatabase::class.java,
                    "shopping-database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}