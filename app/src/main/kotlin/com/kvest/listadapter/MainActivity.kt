package com.kvest.listadapter

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { FirstAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()
    }

    private fun initList() {

        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val list = mutableListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

        adapter.submitList(list)

        Handler().postDelayed({
            Toast.makeText(this, "change", Toast.LENGTH_SHORT).show()
            adapter.submitList(list + "Item 7")
        }, 5000)
    }
}