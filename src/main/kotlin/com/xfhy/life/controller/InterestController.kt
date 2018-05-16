package com.xfhy.life.controller

import com.xfhy.life.domain.Interest
import com.xfhy.life.domain.Result
import com.xfhy.life.enums.ResultEnum
import com.xfhy.life.exception.LifeException
import com.xfhy.life.service.InterestService
import com.xfhy.life.service.UserService
import com.xfhy.life.util.ResultUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

/**
 * Created by xfhy on 2018/3/31 11:50
 * Description : 用户兴趣
 */
@RestController
@RequestMapping(value = ["/interest"])
class InterestController {
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var interestService: InterestService

    /**
     * 增加知乎阅读次数
     */
    @PostMapping(value = ["/zh"])
    @Transactional
    fun addZhCount(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        val interest = commonJudg(bmobId)
        //增加阅读知乎次数
        return if (interestService.addZHCount(interest) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 增加视频阅读次数
     */
    @PostMapping(value = ["/video"])
    @Transactional
    fun addVideoCount(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        val interest = commonJudg(bmobId)
        //增加阅读视频次数
        return if (interestService.addVideoCount(interest) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 增加新闻阅读次数
     */
    @PostMapping(value = ["/news"])
    @Transactional
    fun addNewsCount(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        val interest = commonJudg(bmobId)
        //增加阅读新闻次数
        return if (interestService.addNewsCount(interest) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 增加天气阅读次数
     */
    @PostMapping(value = ["/weather"])
    @Transactional
    fun addWeatherCount(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        val interest = commonJudg(bmobId)
        //增加阅读天气次数
        return if (interestService.addWeatherCount(interest) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 增加笑话阅读次数
     */
    @PostMapping(value = ["/joke"])
    @Transactional
    fun addJokeCount(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        val interest = commonJudg(bmobId)
        //增加阅读笑话次数
        return if (interestService.addJokeCount(interest) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 增加技术阅读次数
     */
    @PostMapping(value = ["/tech"])
    @Transactional
    fun addTechCount(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        val interest = commonJudg(bmobId)
        //增加技术阅读次数
        return if (interestService.addTechCount(interest) == null) {
            ResultUtil.error(ResultEnum.ERROR)
        } else {
            ResultUtil.success()
        }
    }

    /**
     * 最喜欢什么
     */
    @GetMapping(value = ["/max"])
    fun maxInterest(@RequestParam("bmobId") bmobId: String): Result<out Any> {
        val interest = commonJudg(bmobId)
        interest?.let {
            var max = interest.jokeCount
            var result = 0
            if (interest.newsCount > max) {
                max = interest.newsCount
                result = 1
            }
            if (interest.techCount > max) {
                max = interest.techCount
                result = 2
            }
            if (interest.videoCount > max) {
                max = interest.videoCount
                result = 3
            }
            if (interest.weatherCount > max) {
                max = interest.weatherCount
                result = 4
            }
            if (interest.zhCount > max) {
                result = 5
            }
            return when (result) {
                0 -> {
                    ResultUtil.success("笑话")
                }
                1 -> {
                    ResultUtil.success("新闻")
                }
                2 -> {
                    ResultUtil.success("技术")
                }
                3 -> {
                    ResultUtil.success("视频")
                }
                4 -> {
                    ResultUtil.success("天气")
                }
                5 -> {
                    ResultUtil.success("知乎")
                }
                else -> {
                    ResultUtil.success("新闻")
                }
            }
        }
        return ResultUtil.error(ResultEnum.ERROR)
    }

    /**
     * 通用判断
     */
    private fun commonJudg(bmobId: String): Interest? {
        //如果是没有这个用户,则更新失败
        userService.findOneUserById(bmobId) ?: throw LifeException(ResultEnum.USER_DOES_NOT_EXIST)
        //如果这个云端没有该兴趣数据,就创建一个呗
        return interestService.findOneInterestById(bmobId) ?: interestService.addInterest(bmobId)
    }

}