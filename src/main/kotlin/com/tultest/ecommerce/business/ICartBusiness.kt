package com.tultest.ecommerce.business

import com.tultest.ecommerce.data.model.Cart
import com.tultest.ecommerce.data.view.ProductView
import java.util.*

interface ICartBusiness {
    fun addToCart(userId:Long,productView: ProductView)
    fun listCart(userId: Long): List<Cart>
    fun removeFromCart(productId: UUID)
    fun checkout(userId: Long): Double
}