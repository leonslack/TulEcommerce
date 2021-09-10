package com.tultest.ecommerce.data.view

import java.util.*

data class CartView(
        val productId: UUID,
        val quantity: Int,
        val userId: Long
)
