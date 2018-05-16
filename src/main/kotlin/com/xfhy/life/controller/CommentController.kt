package com.xfhy.life.controller

import com.xfhy.life.domain.Result
import com.xfhy.life.domain.User
import com.xfhy.life.enums.ResultEnum
import com.xfhy.life.service.CommentService
import com.xfhy.life.service.ProductService
import com.xfhy.life.service.UserService
import com.xfhy.life.util.ResultUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

/**
 * Created by xfhy on 2018/3/24 14:55
 * Description :评论
 */
@RestController
@RequestMapping(value = ["/comment"])
class CommentController {

    @Autowired
    private lateinit var commentService: CommentService
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var productService: ProductService

    /**
     * 添加评论
     */
    @PostMapping(value = ["/addComment"])
    @Transactional
    fun addComment(@RequestParam("productId") productId: Int,
                   @RequestParam("bmobId") bmobId: String,
                   @RequestParam("content") content: String): Result<out Any> {
        //先判断用户是否存在
        if (userService.findOneUserById(bmobId) == null) {
            return ResultUtil.error(ResultEnum.USER_DOES_NOT_EXIST)
        }
        //判断是否存在该产品,不存在则添加
        val product = productService.findOneProductById(productId)
        if (product == null) {
            productService.addProduct(productId)
        }
        //添加评论
        return if (commentService.addComment(productId, content, bmobId) == null) {
            ResultUtil.error(ResultEnum.FAILED_TO_COMMENT)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 获取评论列表
     */
    @GetMapping(value = ["/comments"])
    fun getComments(@RequestParam("productId") productId: Int): Result<out Any> {
        return ResultUtil.success(commentService.getComments(productId) as Any)
    }

}