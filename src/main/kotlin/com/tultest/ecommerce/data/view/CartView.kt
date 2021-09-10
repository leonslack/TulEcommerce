package com.tultest.ecommerce.data.view

import java.util.*
import javax.validation.constraints.Min

data class CartView(
        val productId: UUID,
        @field:Min(0, message = "Cant be negative")
        val quantity: Int,
        @field:Min(0L, message = "Cant be negative")
        val userId: Long
)
