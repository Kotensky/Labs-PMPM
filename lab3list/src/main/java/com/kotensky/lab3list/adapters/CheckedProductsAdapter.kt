package com.kotensky.lab3list.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.kotensky.lab3list.ProductEntity
import com.kotensky.lab3list.R
import kotlinx.android.synthetic.main.product_item.view.*

class CheckedProductsAdapter(private val products: ArrayList<ProductEntity>) : RecyclerView.Adapter<CheckedProductsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return CheckedProductsAdapter.ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(products[position])
    }

    override fun getItemCount(): Int = products.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(productEntity: ProductEntity?) {
            if (productEntity == null)
                return

            itemView?.product_check?.visibility = GONE
            itemView?.id_txt?.text = "#${productEntity.id}"
            itemView?.name_txt?.text = productEntity.name
            itemView?.price_txt?.text = productEntity.price
        }
    }
}