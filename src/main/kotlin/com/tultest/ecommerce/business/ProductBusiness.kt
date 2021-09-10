package com.tultest.ecommerce.business

import com.tultest.ecommerce.data.model.Price
import com.tultest.ecommerce.data.model.Product
import com.tultest.ecommerce.data.repository.PriceRepository
import com.tultest.ecommerce.data.repository.ProductRepository
import com.tultest.ecommerce.data.view.ProductView
import com.tultest.ecommerce.data.view.toProduct
import com.tultest.ecommerce.data.view.toProductView
import com.tultest.ecommerce.exception.BusinessException
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws

@Service
class ProductBusiness @Autowired constructor(
        private val productRepository: ProductRepository,
        private val priceRepository: PriceRepository
): IProductBusiness {

    @Throws(BusinessException::class)
    override fun createProduct(productView: ProductView): ProductView {
        try {
            val savedProduct = productRepository.save(productView.toProduct())
            createPrice(savedProduct, productView)
            return productView;
        } catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    private fun createPrice(product: Product, productView: ProductView){
        val price = Price(productView.price,productView.discount, product)
        priceRepository.save(price)
    }

    @Throws(BusinessException::class)
    override fun updateProduct(product: Product): Product {
        try {
            return productRepository.save(product)
        } catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    private fun findProductById(productId: UUID): Product{
        val optional: Optional<Product>
        try {
            optional = productRepository.findById(productId)
        } catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!optional.isPresent){
            throw NotFoundException("Product with id: $productId not found")
        } else {
            return optional.get()
        }
    }

    @Throws(BusinessException::class)
    override fun deleteProduct(productId: UUID) {
        val productToDelete = findProductById(productId)
        try {
            productRepository.delete(productToDelete)
        } catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun listAllProducts(): List<ProductView> {
        try {
            return productRepository.findAll().map{product -> product.toProductView()}
        } catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun listProductAdmin(): List<Product> {
        try {
            return productRepository.findAll()
        } catch (e: Exception){
            throw BusinessException(e.message)
        }
    }
}