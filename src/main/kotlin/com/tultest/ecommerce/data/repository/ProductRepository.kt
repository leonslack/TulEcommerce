package com.tultest.ecommerce.data.repository

import com.tultest.ecommerce.data.model.Product
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository: CrudRepository<Product, UUID> {

    @Modifying
    @Query("UPDATE product SET description= :description, name= :name, sku=:sku WHERE id= :id", nativeQuery = true)
    fun updateProduct(@Param("description") description:String, @Param("name") name:String,
                      @Param("sku") sku:String, @Param("id") id:UUID)

    @Modifying
    @Query("UPDATE product SET deleted=true WHERE id= :id", nativeQuery = true)
    fun logicDeleteProduct(@Param("id") id:UUID)

    fun findProductsByDeletedFalse(): List<Product>
}