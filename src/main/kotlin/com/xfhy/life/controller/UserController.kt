package com.xfhy.life.controller

import com.xfhy.life.domain.Result
import com.xfhy.life.enums.ResultEnum
import com.xfhy.life.service.UserService
import com.xfhy.life.util.ResultUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

/**
 * Created by xfhy on 2018/3/25 16:03
 * Description :
 */
@RestController
@RequestMapping(value = ["/user"])
class UserController {

    @Autowired
    private lateinit var userService: UserService

    /**
     * 添加用户
     */
    @PostMapping(value = ["/add"])
    @Transactional
    fun addUser(@RequestParam("bmobId") bmobId: String,
                @RequestParam("userName") userName: String,
                @RequestParam("password") password: String,
                @RequestParam("phone") phone: String): Result<out Any> {
        //已存在该用户
        if (userService.findOneUserById(bmobId) != null) {
            return ResultUtil.error(ResultEnum.ERROR)
        }
        return if (userService.addUser(bmobId, userName, password, phone) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 更新用户头像
     */
    @PutMapping(value = ["/updateAvatar"])
    fun updateAvatarUrl(@RequestParam("bmobId") bmobId: String,
                        @RequestParam("userAvatarUrl") userAvatarUrl: String): Result<out Any> {
        //用户不存在
        if (userService.findOneUserById(bmobId) == null) {
            return ResultUtil.error(ResultEnum.USER_DOES_NOT_EXIST)
        }
        return if (userService.updateAvatarUrl(bmobId, userAvatarUrl) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 查询用户今天是否已经签到
     */
    @GetMapping(value = ["/isSign"])
    fun isSignEd(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        //用户不存在
        val user = userService.findOneUserById(bmobId) ?: return ResultUtil.error(ResultEnum.USER_DOES_NOT_EXIST)
        return if (userService.isSignEd(user)) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            //返回成功  则  用户今天未签到
            ResultUtil.success()
        }
    }

    /**
     * 查询该用户积分
     */
    @GetMapping(value = ["/integral"])
    fun userIntegral(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        //用户不存在
        val user = userService.findOneUserById(bmobId) ?: return ResultUtil.error(ResultEnum.USER_DOES_NOT_EXIST)
        return ResultUtil.success(user.integral)
    }

    /**
     * 签到
     */
    @PostMapping(value = ["/sign"])
    fun signInIntegral(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        //用户不存在
        val user = userService.findOneUserById(bmobId) ?: return ResultUtil.error(ResultEnum.USER_DOES_NOT_EXIST)
        //查询今天是否已经签到
        if (userService.isSignEd(user)) {
            return ResultUtil.error(ResultEnum.ERROR)
        }
        return if (userService.signInIntegral(bmobId) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

}