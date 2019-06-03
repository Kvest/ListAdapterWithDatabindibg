package com.kvest.listadapter.multiitem

sealed class Item
data class ItemCountry(val countryName: String) : Item()
data class ItemCheese(val id: String, val name: String, val selected: Boolean) : Item()