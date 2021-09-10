package com.tultest.ecommerce.business

import com.tultest.ecommerce.data.model.Cart
import com.tultest.ecommerce.data.view.CartView
import java.util.*

interface ICartBusiness {
    fun addToCart(cartView: CartView)
    fun listCart(userId: Long): List<Cart>
    fun removeFromCart(cartId: UUID)
    fun checkout(userId: Long): Double
    fun updateCart(cart: Cart)
}