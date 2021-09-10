package com.tultest.ecommerce.data.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "price")
data class Price(
        val price: Double? = 0.0,
        val discount: Int? = 0,
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "product_id")
        val product: Product ?= null
){
    @Id
    var id: UUID = UUID.randomUUID()
}
