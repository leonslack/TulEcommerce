package com.tultest.ecommerce.data.repository

import com.tultest.ecommerce.constant.CartStatus
import com.tultest.ecommerce.data.model.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartRepository: JpaRepository<Cart, UUID> {

    fun findByStatusAndUserId(@Param("status") status: CartStatus, @Param("userId") userId: Long):List<Cart>
}