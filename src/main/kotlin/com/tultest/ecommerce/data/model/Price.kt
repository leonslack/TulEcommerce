package com.tultest.ecommerce.data.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "price")
data class Price(
        val price: Double? = 0.0,
        val discount: Int? = 0,
){
    @Id
    var id: UUID = UUID.randomUUID()
}
