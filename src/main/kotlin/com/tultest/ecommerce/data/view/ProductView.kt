package com.tultest.ecommerce.data.view

import java.util.*

data class ProductView(
        val id: UUID,
        val name: String,
        val description: String,
        val sku: String,
        val price: Double?,
        val discount: Int?
)