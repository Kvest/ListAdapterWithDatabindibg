package com.kvest.listadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class EasyMultiItemAdapter<T : Any>(
    context: Context,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, EasyMultiItemAdapter.ViewHolder<T>>(diffCallback) {
    private val layoutInflater = LayoutInflater.from(context)

    protected abstract val variableId: Int
    var holderInit = mutableMapOf<Int, (ViewDataBinding.() -> Unit)>()

    protected abstract fun getItemLayoutId(item: T) : Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        //layout id is used as viewType, so use it to inflate View
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        holderInit[viewType]?.let {
            action -> binding.action()
        }

        return EasyMultiItemAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EasyMultiItemAdapter.ViewHolder<T>, position: Int) = holder.bind(variableId, getItem(position))

    override fun getItemViewType(position: Int) = getItemLayoutId(getItem(position))

    class ViewHolder<in T>(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(variableId: Int, item: T) {
            binding.setVariable(variableId, item)
            binding.executePendingBindings()
        }
    }
}