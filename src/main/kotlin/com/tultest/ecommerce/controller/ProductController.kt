package com.tultest.ecommerce.controller

import com.tultest.ecommerce.business.IProductBusiness
import com.tultest.ecommerce.constant.Constants
import com.tultest.ecommerce.data.model.Product
import com.tultest.ecommerce.data.view.ProductView
import com.tultest.ecommerce.exception.BusinessException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping( Constants.URL_API_PRODUCTS)
class ProductController @Autowired constructor(
        private val productBusiness: IProductBusiness
){
    @GetMapping()
    fun listProducts(): ResponseEntity<List<ProductView>>{
        return try {
            ResponseEntity(productBusiness!!.listAllProducts(), HttpStatus.OK)
        } catch (e: Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping
    fun createProduct(@RequestBody product: ProductView): ResponseEntity<ProductView>{
        return try {
            ResponseEntity(productBusiness.createProduct(product), HttpStatus.CREATED)
        } catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}