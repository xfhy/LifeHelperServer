package com.xfhy.life.controller

import com.xfhy.life.domain.Result
import com.xfhy.life.enums.ResultEnum
import com.xfhy.life.exception.LifeException
import com.xfhy.life.service.ProductService
import com.xfhy.life.service.UserService
import com.xfhy.life.util.ResultUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

/**
 * author xfhy
 * create at 2018/3/23 21:02
 * description：文章,视频 controller
 */

@RestController
@RequestMapping(value = ["/product"])
class ProductController {

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var userService: UserService

    /**
     * 点赞
     */
    @PostMapping(value = ["/like"])
    @Transactional
    fun likeProduct(@RequestParam("bmobId") bmobId: String,
                    @RequestParam("productId") productId: Int): Result<out Any> {
        //如果是没有这个用户,则点赞失败
        userService.findOneUserById(bmobId) ?: throw LifeException(ResultEnum.USER_DOES_NOT_EXIST)
        //如果这个云端没有该产品,就创建一个呗
        val product = productService.findOneProductById(productId) ?: productService.addProduct(productId)
        //增加点赞数量
        return if (productService.addLikeCount(product) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 分享
     */
    @PostMapping(value = ["/share"])
    @Transactional
    fun shareProduct(@RequestParam("productId") productId: Int): Result<out Any> {
        //如果这个云端没有改产品,就创建一个呗
        val product = productService.findOneProductById(productId) ?: productService.addProduct(productId)
        //增加点赞数量
        return if (productService.addShareCount(product) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 获取产品信息
     */
    @GetMapping(value = ["/info"])
    fun getCommentCount(@RequestParam("productId") productId: Int): Result<out Any> {
        val returnProduct = productService.getReturnProduct(productId)
        return ResultUtil.success(returnProduct)
    }

}