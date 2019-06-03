package com.kvest.listadapter.multiitem

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.kvest.listadapter.BR
import com.kvest.listadapter.EasyMultiItemAdapter
import com.kvest.listadapter.R

class MultiItemAdapter(context: Context) : EasyMultiItemAdapter<Item>(context, DIFF_CALLBACK) {
    override fun getItemLayoutId(item: Item) = when (item) {
        is ItemCountry -> R.layout.item_country
        is ItemCheese -> R.layout.item_cheese
    }

    override val variableId = BR.item

    private object DIFF_CALLBACK : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return when {
                oldItem is ItemCountry && newItem is ItemCountry -> oldItem.countryName == newItem.countryName
                oldItem is ItemCheese && newItem is ItemCheese -> oldItem.id == newItem.id
                else -> false
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }
}