package com.tultest.ecommerce.data.view

import com.tultest.ecommerce.data.model.Product

fun Product.toProductView() = ProductView(
        id = id,
        name = name,
        description = description,
        sku = sku,
        price = price?.price?.minus(( price.price * price.discount!!)/100),
        discount = price?.discount
)

fun ProductView.toProduct() = Product(
        name = name,
        description = description,
        sku = sku,
)