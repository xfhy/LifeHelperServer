package com.xfhy.life.exception

import com.xfhy.life.enums.ResultEnum

/**
 * author xfhy
 * create at 2018/3/23 21:30
 * description：自定义的异常  多了个code   方便到时候查找
 */
class LifeException(resultEnum: ResultEnum) : RuntimeException(resultEnum.message) {

    /**
     * 错误码
     */
    val code = resultEnum.code

}