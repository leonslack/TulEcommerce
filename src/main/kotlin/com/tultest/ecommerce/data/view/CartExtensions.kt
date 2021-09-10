package com.tultest.ecommerce.data.view

import com.tultest.ecommerce.constant.CartStatus
import com.tultest.ecommerce.data.model.Cart
import com.tultest.ecommerce.data.model.Product

fun CartView.toCart(product: Product, price: Double) = Cart(
        product = product,
        quantity=quantity,
        unitPrice = price,
        status = CartStatus.PENDING,
        userId = userId
)