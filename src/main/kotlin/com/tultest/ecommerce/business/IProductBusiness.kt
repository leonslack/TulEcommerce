package com.tultest.ecommerce.business

import com.tultest.ecommerce.data.model.Product
import com.tultest.ecommerce.data.view.ProductView
import java.util.*

interface IProductBusiness {
    fun createProduct(product: ProductView):ProductView
    fun updateProduct(product: ProductView):ProductView
    fun deleteProduct(productId:UUID)
    fun listAllProducts(): List<ProductView>
}