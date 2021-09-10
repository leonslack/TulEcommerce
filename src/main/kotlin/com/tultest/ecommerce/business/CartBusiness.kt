package com.tultest.ecommerce.business

import com.tultest.ecommerce.constant.CartStatus
import com.tultest.ecommerce.data.model.Cart
import com.tultest.ecommerce.data.repository.CartRepository
import com.tultest.ecommerce.data.view.CartView
import com.tultest.ecommerce.data.view.toCart
import com.tultest.ecommerce.data.view.toProductView
import com.tultest.ecommerce.exception.BusinessException
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CartBusiness @Autowired constructor(
        private val cartRepository: CartRepository,
        private val productBusiness: ProductBusiness
):ICartBusiness {

    override fun addToCart(cartView: CartView) {
        try {
            val product = productBusiness.findProductById(cartView.productId)
            val cart = cartView.toCart(product, product.toProductView().price!!)
            cartRepository.save(cart)
        } catch (e: Exception){
            throw BusinessException(e.message)
        }
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

    private fun findCartById(cartId: UUID): Cart{
        val optional: Optional<Cart>
        try {
            optional = cartRepository.findById(cartId)
        } catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!optional.isPresent){
            throw NotFoundException("Cart with id: $cartId not found")
        } else {
            return optional.get()
        }
    }

    @Transactional
    override fun removeFromCart(cartId: UUID) {
        val cartToDelete = findCartById(cartId)
        try {
            cartRepository.delete(cartToDelete)
        } catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    override fun checkout(userId: Long): Double {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateCart(cart: Cart) {
        try {
            if(cart.quantity == 0){
                removeFromCart(cart.id)
            } else {
                cartRepository.updateCart(cart.quantity, cart.id)
            }

        } catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

}