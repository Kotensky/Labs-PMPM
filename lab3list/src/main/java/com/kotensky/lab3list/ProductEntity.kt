package com.kotensky.lab3list

import java.io.Serializable

data class ProductEntity (val id: Long,
                          val name: String,
                          val price: String) : Serializable