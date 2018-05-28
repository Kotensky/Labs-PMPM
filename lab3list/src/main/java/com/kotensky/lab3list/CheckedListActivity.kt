package com.kotensky.lab3list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.kotensky.lab3list.adapters.CheckedProductsAdapter
import kotlinx.android.synthetic.main.activity_list.*


class CheckedListActivity : AppCompatActivity() {

    private lateinit var adapter: CheckedProductsAdapter
    private val products: ArrayList<ProductEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initRecycler()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecycler() {
        getProductsFromIntent()
        adapter = CheckedProductsAdapter(products)
        main_recycler.layoutManager = LinearLayoutManager(this)
        main_recycler.adapter = adapter
    }

    private fun getProductsFromIntent() {
        products.addAll(intent.getSerializableExtra("selectedProducts") as ArrayList<ProductEntity>)
    }
}