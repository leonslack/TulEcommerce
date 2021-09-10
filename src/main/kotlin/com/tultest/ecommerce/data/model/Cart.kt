package com.tultest.ecommerce.data.model

import com.tultest.ecommerce.constant.CartStatus
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "cart")
data class Cart(
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "product_id")
        val product: Product ?= null,
        val quantity: Int = 0,
        val unitPrice: Double = 0.0,
        val status: CartStatus = CartStatus.PENDING,
        val userId: Long = 0L
){
    @Id
    var id: UUID = UUID.randomUUID()
}
