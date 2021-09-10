package com.tultest.ecommerce.data.repository

import com.tultest.ecommerce.constant.CartStatus
import com.tultest.ecommerce.data.model.Cart
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartRepository: CrudRepository<Cart, UUID> {
    fun findCartByUserIdEqualsAndStatusEquals
            (userId: Long,status: CartStatus):List<Cart>
}