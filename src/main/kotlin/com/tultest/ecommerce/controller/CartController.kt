package com.tultest.ecommerce.controller

import com.tultest.ecommerce.business.ICartBusiness
import com.tultest.ecommerce.constant.Constants
import com.tultest.ecommerce.data.model.Cart
import com.tultest.ecommerce.data.view.CartView
import com.tultest.ecommerce.data.view.ProductView
import com.tultest.ecommerce.exception.BusinessException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping( Constants.URL_API_CARTS)
class CartController  @Autowired constructor(
        private val cartBusiness: ICartBusiness
){
    @GetMapping("/{userId}")
    fun listCart(@PathVariable userId: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity(cartBusiness.listCart(userId), HttpStatus.OK)
        } catch (e: Exception){
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping
    fun addToCart(@RequestBody cartView: CartView): ResponseEntity<Any>{
        return try {
            cartBusiness.addToCart(cartView)
            ResponseEntity(HttpStatus.CREATED)
        } catch (e: BusinessException){
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{cartId}")
    fun removeFromCart(@PathVariable cartId: UUID): ResponseEntity<Any>{
        return try {
            cartBusiness.removeFromCart(cartId)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException){
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping
    fun modifyCart(@RequestBody cart:Cart): ResponseEntity<Any>{
        return try {
            cartBusiness.updateCart(cart)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException){
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}