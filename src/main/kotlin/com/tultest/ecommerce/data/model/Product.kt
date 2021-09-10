package com.tultest.ecommerce.data.model

import org.hibernate.annotations.Where
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product")
@Where(clause = "deleted = 0")
data class Product(
        val name: String = "",
        val description: String = "",
        val sku: String = "",
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "price_id")
        val price: Price? = null,
        val deleted: Boolean = false
) {
    @Id
    var id: UUID = UUID.randomUUID()
}
