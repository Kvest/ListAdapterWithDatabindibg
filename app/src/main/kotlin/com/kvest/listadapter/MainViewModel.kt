package com.kvest.listadapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(), ItemHandler {
    private val itemsMutable: MutableLiveData<List<Item>> = MutableLiveData()
    val items: LiveData<List<Item>>
        get() = itemsMutable

    init {
        //generate initial
        itemsMutable.value = List(50) {
            Item(it, "Item ${it + 1}")
        }
    }

    override fun onItemSelected(item: Item) {
        Log.d("MainViewModel", "item selected: $item")
    }

    override fun onInc(itemId: Int) {
        updateList(itemId, 1)
    }

    override fun onDec(itemId: Int) {
        updateList(itemId, -1)
    }

    private fun updateList(itemId: Int, delta: Int) {
        itemsMutable.value = itemsMutable.value?.map {
            if (it.id == itemId) {
                it.copy(count = it.count + delta)
            } else {
                it
            }
        }
        ?.sortedByDescending { it.count }
    }
}