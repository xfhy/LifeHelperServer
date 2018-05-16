package com.xfhy.life.handle

import com.xfhy.life.domain.Result
import com.xfhy.life.enums.ResultEnum
import com.xfhy.life.exception.LifeException
import com.xfhy.life.util.ResultUtil
import org.slf4j.LoggerFactory
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import kotlin.reflect.KClass

/**
 * author xfhy
 * create at 2018/3/23 23:04
 * description： 全局异常处理类
 */
@ControllerAdvice //注解定义全局异常处理类
class ExceptionHandle {

    companion object {
        val logger by lazy {
            LoggerFactory.getLogger(ExceptionHandle::class.java)
        }
    }

    @ExceptionHandler(value = [java.lang.Exception::class])
    @ResponseBody    //返回
    fun handle(e: Exception): Result<String> {
        //判断一下是不是自己定义的异常
        return when (e) {
            is LifeException -> {  //我自己定义的异常
                ResultUtil.error(e.code, e.message ?: "")
            }
            is MissingServletRequestParameterException -> {   //可能是参数缺失
                logger.error("[参数缺失] {}", e)
                ResultUtil.error(ResultEnum.MISSING_PARAMETER_ERROR.code, ResultEnum.MISSING_PARAMETER_ERROR.message)
            }
            else -> {
                logger.error("[系统异常] {}", e)
                ResultUtil.error(ResultEnum.UNKNOWED_ERROR.code, ResultEnum.UNKNOWED_ERROR.message)
            }
        }
    }

}