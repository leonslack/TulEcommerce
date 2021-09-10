package com.tultest.ecommerce.data.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "product")
data class Product(
        val name: String = "",
        val description: String = "",
        val sku: String = "",
        @OneToOne(mappedBy = "product")
        val price: Price? = null
) {
    @Id
    var id: UUID = UUID.randomUUID()
}
