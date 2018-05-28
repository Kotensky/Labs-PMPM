package com.kotensky.lab3list

interface OnProductItemClickListener {

    fun onShowSelectedClick()

    fun onCheckedChangeListener(isChecked: Boolean, productEntity: ProductEntity)
}