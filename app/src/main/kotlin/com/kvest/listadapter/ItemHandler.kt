package com.kvest.listadapter

interface ItemHandler {
    fun onItemSelected(item: Item)
    fun onInc(itemId: Int)
    fun onDec(itemId: Int)
}