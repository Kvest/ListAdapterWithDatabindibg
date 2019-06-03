package com.kvest.listadapter.multiitem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.kvest.listadapter.BR
import com.kvest.listadapter.R
import kotlinx.android.synthetic.main.activity_main.*

class MultiItemActivity : AppCompatActivity(), ItemCheeseHandler, ItemCountryHandler {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MultiItemActivity::class.java))
        }
    }

    private var items = listOf(
        ItemCountry("Italian"),
        ItemCheese("it_ch_0", "Abbamar", false),
        ItemCheese("it_ch_1", "Accasciato", false),
        ItemCheese("it_ch_2", "Acceglio", false),
        ItemCheese("it_ch_3", "Acidino", false),
        ItemCheese("it_ch_4", "Agrì di Valtorta", false),
        ItemCheese("it_ch_5", "Ainuzzi", false),
        ItemCheese("it_ch_6", "Algunder Butterkäse", false),
        ItemCheese("it_ch_7", "Almkäse", false),
        ItemCheese("it_ch_8", "Alpepiana", false),

        ItemCountry("Denmark"),
        ItemCheese("dm_ch_0", "Danbo", false),
        ItemCheese("dm_ch_1", "Danish Blue", false),
        ItemCheese("dm_ch_2", "Esrom", false),
        ItemCheese("dm_ch_3", "Havarti", false),
        ItemCheese("dm_ch_4", "Maribo", false),
        ItemCheese("dm_ch_5", "Saga", false),
        ItemCheese("dm_ch_6", "Samsø cheese", false),

        ItemCountry("French "),
        ItemCheese("fr_ch_0", "Beaufort", false),
        ItemCheese("fr_ch_1", "Brie de Meaux", false),
        ItemCheese("fr_ch_2", "Époisses de Bourgogne", false),
        ItemCheese("fr_ch_3", "Langres", false),
        ItemCheese("fr_ch_4", "Munster or Munster-Géromé", false)
    )
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { MultiItemAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.multi_item_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.select_all) {
            items = items.map {
                if (it is ItemCheese) {
                it.copy(selected = true)
                } else {
                    it
                }
            }

            adapter.submitList(items)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(item: ItemCheese) {
        items = items.map {
            if (it is ItemCheese && it.id == item.id) {
                it.copy(selected = !it.selected)
            } else {
                it
            }
        }

        adapter.submitList(items)
    }

    override fun onItemClicked(item: ItemCheese) {
        Log.d("KVEST_TAG", "onItemClicked $item")
    }

    override fun onHeaderClicked(item: ItemCountry) {
        Log.d("KVEST_TAG", "onHeaderClicked $item")
    }

    private fun initList() {
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        adapter.holderInit[R.layout.item_cheese] = {
            setVariable(BR.handler, this@MultiItemActivity)
        }
        adapter.holderInit[R.layout.item_country] = {
            setVariable(BR.handler, this@MultiItemActivity)
        }

        adapter.submitList(items)
    }
}