package com.tultest.ecommerce.data.model

import com.tultest.ecommerce.constant.CartStatus
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Min

@Entity
@Table(name = "cart")
data class Cart(
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "product_id")
        val product: Product ?= null,
        @field:Min(0, message = "Cant be negative")
        val quantity: Int = 0,
        @field:Min(0, message = "Cant be negative")
        val unitPrice: Double = 0.0,
        val status: CartStatus = CartStatus.PENDING,
        @field:Min(0L, message = "Cant be negative")
        val userId: Long = 0L
){
    @Id
    var id: UUID = UUID.randomUUID()
}
