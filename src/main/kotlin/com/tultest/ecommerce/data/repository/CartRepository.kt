package com.tultest.ecommerce.data.repository

import com.tultest.ecommerce.constant.CartStatus
import com.tultest.ecommerce.data.model.Cart
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartRepository: CrudRepository<Cart, UUID> {

    fun findCartByUserIdEqualsAndStatusEquals
            (userId: Long,status: CartStatus):List<Cart>

    @Query("FROM Cart where product.id = :productId AND status = :status AND userId = :userId")
    fun getCartByStatusAndProductId(@Param("productId") productId: UUID, @Param("status") status: CartStatus,
                                    @Param("userId") userId: Long): Cart

    @Modifying
    @Query("UPDATE cart SET quantity= :quantity WHERE id = :id", nativeQuery = true)
    fun updateCart(@Param("quantity") quantity: Int, @Param("id") id: UUID)

    @Modifying
    @Query("UPDATE cart SET status= :status WHERE id = :id", nativeQuery = true)
    fun changeCartStatus(@Param("status") status: Int, @Param("id") id: UUID)
}