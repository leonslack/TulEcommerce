package com.tultest.ecommerce.business

import com.tultest.ecommerce.constant.CartStatus
import com.tultest.ecommerce.data.model.Cart
import com.tultest.ecommerce.data.repository.CartRepository
import com.tultest.ecommerce.data.view.ProductView
import com.tultest.ecommerce.data.view.toProductView
import com.tultest.ecommerce.exception.BusinessException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartBusiness @Autowired constructor(
        private val cartRepository: CartRepository
):ICartBusiness {

    override fun addToCart(userId: Long, productView: ProductView) {
        TODO("Not yet implemented")
    }

    override fun listCart(userId: Long): List<Cart> {
        try {
            return cartRepository.findCartByUserIdEqualsAndStatusEquals(
                    userId, CartStatus.PENDING
            )
        } catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    override fun removeFromCart(productId: UUID) {
        TODO("Not yet implemented")
    }

    override fun checkout(userId: Long): Double {
        TODO("Not yet implemented")
    }
}