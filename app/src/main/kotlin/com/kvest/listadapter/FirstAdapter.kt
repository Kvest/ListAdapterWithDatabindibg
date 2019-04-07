package com.kvest.listadapter

import android.content.Context
import androidx.recyclerview.widget.DiffUtil

class FirstAdapter(context: Context) : EasyAdapter<String>(context, DIFF_CALLBACK) {
    override val layoutId = R.layout.item_simple
    override val variableId = BR.item

    private object DIFF_CALLBACK : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }
}