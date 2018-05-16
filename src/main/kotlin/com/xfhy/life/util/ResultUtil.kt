package com.xfhy.life.util

import com.xfhy.life.domain.Result
import com.xfhy.life.enums.ResultEnum

/**
 * author xfhy
 * create at 2018/3/23 23:08
 * description：专门用于简化返回数据的 不用每次都去手动封装数据
 */
object ResultUtil {

    /**
     * 需要返回数据的 成功
     */
    @JvmStatic
    fun success(any: Any): Result<out Any> {
        return Result(ResultEnum.SUCCESS.code, ResultEnum.SUCCESS.message, any)
    }

    /**
     * 成功  但不用返回数据
     */
    @JvmStatic
    fun success(): Result<out Any> {
        return success("")
    }

    /**
     * 失败  返回
     */
    @JvmStatic
    fun error(code: Int, msg: String): Result<String> {
        //如果失败 则data传空字符串吧 如果是传null,那么客户端(json数据中没有)收不到该字段
        return Result(code, msg, "")
    }

    /**
     * 失败,返回
     */
    @JvmStatic
    fun error(resultEnum: ResultEnum): Result<String> {
        //如果失败 则data传空字符串吧 如果是传null,那么客户端(json数据中没有)收不到该字段
        return Result(resultEnum.code, resultEnum.message, "")
    }

}