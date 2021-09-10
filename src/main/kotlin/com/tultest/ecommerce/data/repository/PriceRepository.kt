package com.tultest.ecommerce.data.repository

import com.tultest.ecommerce.data.model.Price
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PriceRepository: CrudRepository<Price, UUID> {

    @Modifying
    @Query("UPDATE price SET discount= :discount, price= :price WHERE id= :id", nativeQuery = true)
    fun updatePrice(@Param("discount") discount:Int, @Param("price") price:Double, @Param("id") id:UUID)
}