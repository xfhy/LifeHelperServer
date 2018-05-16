package com.xfhy.life.domain

/**
 * author xfhy
 * create at 2018/3/23 21:23
 * description：http请求的最外层json对象  结果
 */
data class Result<T>(
        /**
         * 状态码
         */
        var code: Int,
        /**
         * 提示信息
         */
        var msg: String,
        /**
         * 数据
         */
        var data: T)