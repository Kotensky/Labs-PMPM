package com.kotensky.lab3list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kotensky.lab3list.adapters.ProductsAdapter
import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AppCompatActivity(), OnProductItemClickListener {


    private lateinit var adapter: ProductsAdapter
    private val products: ArrayList<ProductEntity> = ArrayList()
    private val selectedProducts: ArrayList<ProductEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initRecycler()
    }

    private fun initRecycler() {
        generateProducts()
        adapter = ProductsAdapter(products, selectedProducts, this)
        main_recycler.layoutManager = LinearLayoutManager(this)
        main_recycler.adapter = adapter
    }

    private fun generateProducts() {
        products.clear()
        val rand = Random()
        for (i in 1..30) {
            products.add(ProductEntity(i.toLong(), "Стілець №$i", "${rand.nextInt(200)} грн."))
        }
    }

    override fun onShowSelectedClick() {
        val intent = Intent(ListActivity@ this, CheckedListActivity::class.java)
        intent.putExtra("selectedProducts", selectedProducts)
        startActivity(intent)
    }

    override fun onCheckedChangeListener(isChecked: Boolean, productEntity: ProductEntity) {
        if (isChecked) {
            selectedProducts.add(productEntity)
        } else {
            selectedProducts.remove(productEntity)
        }
        adapter.notifyItemChanged(adapter.itemCount - 1)
    }
}
