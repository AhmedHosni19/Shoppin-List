package com.example.shoppinglist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
abstract class ShoppingItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addShoppingItem(item:ShoppingItem)

    @Query("select * from `shopping_items` ")
    abstract fun getAllItems(): Flow<List<ShoppingItem>>

    @Delete
    abstract suspend fun deleteItem(shoppingItemEntity: ShoppingItem)

    @Update
    abstract suspend fun updateShoppingItem(shoppingItemEntity: ShoppingItem)

}