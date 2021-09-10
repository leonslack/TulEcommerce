package com.tultest.ecommerce.data.repository

import com.tultest.ecommerce.data.model.Price
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PriceRepository: JpaRepository<Price, UUID> {
}