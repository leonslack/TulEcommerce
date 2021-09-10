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
import org.springframework.transaction.annotation.Transactional
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
            val savedProduct = productRepository.save(productView.toProduct(createPrice(productView)))
            productView.id = savedProduct.id
            return productView;
        } catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    private fun createPrice(productView: ProductView):Price{
        val price = Price(productView.price,productView.discount)
        return priceRepository.save(price)
    }

    @Throws(BusinessException::class)
    @Transactional
    override fun updateProduct(productView: ProductView): ProductView {
        try {
            var productToUpdate = findProductById(productView.id)
            updateProductFields(productToUpdate, productView)
            updateProductPrice(productView, productToUpdate.price!!)
            return productView
        } catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    private fun updateProductPrice(productView: ProductView, price: Price){
        priceRepository.updatePrice(productView.discount!!, productView.price!!, price.id)
    }

    private fun updateProductFields(product: Product, productView: ProductView){
        productRepository.updateProduct(productView.description,productView.name,productView.sku,product.id)
    }

    @Throws(NotFoundException::class)
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

    @Transactional
    override fun deleteProduct(productId: UUID) {
        val productToDelete = findProductById(productId)
        try {
            productRepository.logicDeleteProduct(productToDelete.id)
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
}