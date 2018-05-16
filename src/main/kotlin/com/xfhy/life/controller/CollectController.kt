package com.xfhy.life.controller

import com.xfhy.life.domain.Result
import com.xfhy.life.enums.ResultEnum
import com.xfhy.life.service.CollectService
import com.xfhy.life.service.ProductService
import com.xfhy.life.service.UserService
import com.xfhy.life.util.ResultUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

/**
 * author xfhy
 * create at 2018/3/24 12:25
 * description： 收藏controller
 */
@RestController
@RequestMapping(value = ["/collect"])
class CollectController {

    @Autowired
    private lateinit var collectService: CollectService
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var productService: ProductService


    /**
     * 查询是否已经收藏
     */
    @GetMapping(value = ["/isCollect"])
    fun isCollected(@RequestParam("bmobId") bmobId: String,
                    @RequestParam("productId") productId: Int): Result<out Any> {
        return if (collectService.isCollected(bmobId, productId) != null) {
            ResultUtil.success()
        } else {
            ResultUtil.error(ResultEnum.NOT_COLLECTED)
        }
    }

    /**
     * 收藏产品
     */
    @PostMapping(value = ["/collectProduct"])
    @Transactional
    fun collectProduct(@RequestParam("bmobId") bmobId: String,
                       @RequestParam("productId") productId: Int,
                       @RequestParam("productType") productType: Int): Result<out Any> {
        //先判断用户是否存在
        if (userService.findOneUserById(bmobId) == null) {
            return ResultUtil.error(ResultEnum.USER_DOES_NOT_EXIST)
        }
        //判断是否存在该产品,不存在则添加
        val product = productService.findOneProductById(productId)
        if (product == null) {
            productService.addProduct(productId)
        }
        //判断用户是否已经收藏过了
        if (collectService.isCollected(bmobId, productId) != null) {
            return ResultUtil.error(ResultEnum.ALREADY_COLLECTED)
        }
        //用户未收藏过,则去收藏
        return if (collectService.collectProduct(bmobId, productId, productType) != null) {
            ResultUtil.success()
        } else {
            ResultUtil.error(ResultEnum.FAILED_TO_COLLECT)
        }
    }

    /**
     * 取消收藏产品
     */
    @PostMapping(value = ["/cancelCollect"])
    @Transactional
    fun cancelCollect(@RequestParam("bmobId") bmobId: String,
                      @RequestParam("productId") productId: Int): Result<out Any> {
        //先判断用户是否存在
        if (userService.findOneUserById(bmobId) == null) {
            return ResultUtil.error(ResultEnum.USER_DOES_NOT_EXIST)
        }
        //先判断用户是否已经收藏过  未收藏则返回失败
        val collected = collectService.isCollected(bmobId, productId) ?: return ResultUtil.error(ResultEnum.NOT_COLLECTED)
        //去取消收藏产品
        collectService.cancelCollectProduct(collected)
        return ResultUtil.success()
    }

    /**
     * 查询用户所有的收藏
     */
    @GetMapping(value = ["/collects"])
    fun getAllCollected(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        val allCollects = collectService.getAllCollected(bmobId)
        return if (allCollects == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success(allCollects)
        }
    }

}