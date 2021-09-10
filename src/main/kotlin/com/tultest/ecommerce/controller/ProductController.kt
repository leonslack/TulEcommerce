package com.tultest.ecommerce.controller

import com.tultest.ecommerce.business.IProductBusiness
import com.tultest.ecommerce.constant.Constants
import com.tultest.ecommerce.data.view.ProductView
import com.tultest.ecommerce.exception.BusinessException
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

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
    fun createProduct(@Valid @RequestBody product: ProductView): ResponseEntity<ProductView>{
        return try {
            ResponseEntity(productBusiness.createProduct(product), HttpStatus.CREATED)
        } catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable("productId") productId:UUID):ResponseEntity<Any>{
        return try {
            productBusiness.deleteProduct(productId)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping
    fun updateProduct(@Valid @RequestBody product: ProductView): ResponseEntity<ProductView>{
        return try {
            ResponseEntity(productBusiness.updateProduct(product), HttpStatus.OK)
        } catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}