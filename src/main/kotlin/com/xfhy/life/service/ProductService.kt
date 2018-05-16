package com.xfhy.life.service

import com.xfhy.life.domain.Product
import com.xfhy.life.domain.ReturnProduct
import com.xfhy.life.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * author xfhy
 * create at 2018/3/24 8:31
 * description：产品 用来操作的
 */
@Service
class ProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository
    @Autowired
    private lateinit var commentService: CommentService

    /**
     * 查询产品 by id
     */
    fun findOneProductById(productId: Int): Product? {
        val optional = productRepository.findById(productId)
        return if (optional.isPresent) {
            optional.get()
        } else {
            null
        }
    }

    /**
     * 增加产品点赞数量
     */
    fun addLikeCount(product: Product): Product? {
        product.likeCount++
        return productRepository.save(product)
    }

    /**
     * 添加一个产品
     */
    fun addProduct(productId: Int): Product {
        val product = Product(productId)
        return productRepository.save(product)
    }

    /**
     * 增加产品分享数量
     */
    fun addShareCount(product: Product): Product? {
        product.shareCount++
        return productRepository.save(product)
    }

    /**
     * 获取产品信息  返参的
     */
    fun getReturnProduct(productId: Int): ReturnProduct {
        val optional = productRepository.findById(productId)
        val returnProduct = ReturnProduct()
        return if (optional.isPresent) {
            val product = optional.get()
            returnProduct.likeCount = product.likeCount
            returnProduct.shareCount = product.shareCount
            returnProduct.commentCount = commentService.getCommentCount(productId)
            returnProduct
        } else {
            returnProduct
        }
    }

}