package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Item(val name : String, val address : String)

class MyViewModel : ViewModel() {

    val itemListData = MutableLiveData<ArrayList<Item>>()
    val items = ArrayList<Item>()

    val itemClickEvent = MutableLiveData<Int>()
    var itemLongClick = -1

    fun addItem(item : Item){
        items.add(item)
        itemListData.value = items
    }

    fun updateItem(pos:Int, item:Item){
        items[pos] = item
        itemListData.value = items
    }

    fun deleteItem(pos : Int){
        items.removeAt(pos)
        itemListData.value = items
    }
}