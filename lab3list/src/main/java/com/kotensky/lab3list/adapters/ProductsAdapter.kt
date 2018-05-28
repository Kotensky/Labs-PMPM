package com.kotensky.lab3list.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.kotensky.lab3list.OnProductItemClickListener
import com.kotensky.lab3list.ProductEntity
import com.kotensky.lab3list.R
import kotlinx.android.synthetic.main.footer_product_item.view.*
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter(private val products: ArrayList<ProductEntity>,
                      private val selectedProducts: ArrayList<ProductEntity>,
                      private val onProductItemClickListener: OnProductItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_HEADER = 1
    private val VIEW_TYPE_FOOTER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.header_product_item, parent, false)
                HeaderViewHolder(view)
            }
            VIEW_TYPE_FOOTER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.footer_product_item, parent, false)
                FooterViewHolder(view, onProductItemClickListener)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
                ItemViewHolder(view, onProductItemClickListener)
            }
        }
    }

    override fun getItemCount(): Int = products.size + 2

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FooterViewHolder) {
            holder.bindView(selectedProducts.size)
        }
        if (holder is ItemViewHolder) {
            holder.bindView(products[position - 1], selectedProducts.contains(products[position - 1]))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_HEADER
            itemCount - 1 -> VIEW_TYPE_FOOTER
            else -> VIEW_TYPE_ITEM
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    class ItemViewHolder(itemView: View,
                         private val onClickListener: OnProductItemClickListener?) : RecyclerView.ViewHolder(itemView) {

        private var isOnBind = true

        fun bindView(productEntity: ProductEntity?, isChecked: Boolean) {
            if (productEntity == null)
                return

            isOnBind = true
            itemView?.product_check?.isChecked = isChecked
            isOnBind = false

            itemView?.product_check?.setOnCheckedChangeListener({ compoundButton: CompoundButton, isChecked: Boolean ->
                if (!isOnBind) {
                    onClickListener?.onCheckedChangeListener(isChecked, productEntity)
                }
            })

            itemView?.id_txt?.text = "#${productEntity.id}"
            itemView?.name_txt?.text = productEntity.name
            itemView?.price_txt?.text = productEntity.price
        }
    }

    class FooterViewHolder(itemView: View,
                           private val onClickListener: OnProductItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        fun bindView(checkedCount: Int) {
            itemView?.show_btn?.setOnClickListener {
                onClickListener?.onShowSelectedClick()
            }
            itemView?.show_btn?.visibility = if (checkedCount == 0) View.GONE else View.VISIBLE
            itemView?.count_txt?.text = "Вибрано $checkedCount шт."
        }
    }
}