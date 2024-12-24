package com.example.shoppinglist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo("shopping-item-name")
    var name:String="",
    @ColumnInfo("shopping-item-quantity")
    var quantity:Int=0,
    val isEditing:Boolean=false
)