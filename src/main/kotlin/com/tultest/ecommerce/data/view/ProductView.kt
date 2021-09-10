package com.tultest.ecommerce.data.view

import java.util.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class ProductView(
        var id: UUID,
        @field:NotBlank(message = "name is missing")
        val name: String,
        @field:NotBlank(message = "description is missing")
        val description: String,
        @field:NotBlank(message = "SKU is missing")
        val sku: String,
        @field:Min(0L, message = "Cant be negative")
        val price: Double?,
        @field:Min(0, message = "Cant be negative")
        val discount: Int?
)
