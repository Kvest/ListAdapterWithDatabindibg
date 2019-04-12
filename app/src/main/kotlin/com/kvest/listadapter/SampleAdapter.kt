package com.kvest.listadapter

import android.content.Context
import androidx.recyclerview.widget.DiffUtil

class SampleAdapter(context: Context) : EasyAdapter<Item>(context, DIFF_CALLBACK) {
    override val layoutId = R.layout.item_sample
    override val variableId = BR.item

    private object DIFF_CALLBACK : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }
}