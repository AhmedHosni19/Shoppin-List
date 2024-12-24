package com.example.shoppinglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.ShoppingDatabase
import com.example.shoppinglist.data.ShoppingItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ShoppingListViewModel(application: Application):AndroidViewModel (application){

    private val shoppingItemDao =ShoppingDatabase.getDatabase(application).shoppingItemDao()
    private val _shoppingItems= MutableStateFlow<List<ShoppingItem>>(emptyList())
    val shoppingItems:StateFlow<List<ShoppingItem>> get() = _shoppingItems

    init {
        viewModelScope.launch {
            shoppingItemDao.getAllItems().collect{ items ->
                _shoppingItems.value=items
            }
        }
    }

    fun addItem(name:String,quantity:Int){
        viewModelScope.launch {
            val newItem=ShoppingItem(name=name,quantity=quantity)
            shoppingItemDao.addShoppingItem(newItem)
        }
    }

    fun editItem(id:Int,name:String ,quantity: Int){
        viewModelScope.launch {
            val updatedItem=ShoppingItem(id,name,quantity)
            shoppingItemDao.updateShoppingItem(updatedItem)
        }
    }

    fun deleteItem(id: Int){
        viewModelScope.launch {
            val itemToDelete=_shoppingItems.value.find { it.id ==id}
            itemToDelete?.let {
                shoppingItemDao.deleteItem(it)
            }
        }
    }
    fun startEditing(id: Int) {
        _shoppingItems.value = _shoppingItems.value.map {
            it.copy(isEditing = it.id == id)
        }
    }

}