package com.kvest.listadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { SampleAdapter(this) }
    private val viewModel by lazy(LazyThreadSafetyMode.NONE) { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()
        initViewModel()
    }

    private fun initList() {
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        adapter.holderInit = {
            setVariable(BR.handler, viewModel)
        }
    }

    private fun initViewModel() {
        observe(viewModel.items) {
            it?.let { items ->
                adapter.submitList(items)
            }
        }
    }
}