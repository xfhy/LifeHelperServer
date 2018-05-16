package com.xfhy.life.enums

/**
 * author xfhy
 * create at 2018/3/23 21:25
 * description： 统一一下错误的枚举,以免错乱.  所有的错误码,全放在这里.
 *
 * 优雅的编码
 */
enum class ResultEnum(val code: Int, val message: String) {
    UNKNOWED_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    USER_DOES_NOT_EXIST(2, "用户不存在"),
    PRODUCT_DOES_NOT_EXIST(3, "产品不存在"),
    MISSING_PARAMETER_ERROR(4, "参数缺失"),
    NOT_COLLECTED(5, "用户未收藏"),
    ALREADY_COLLECTED(6, "用户已经收藏过"),
    FAILED_TO_COLLECT(7, "收藏失败"),
    FAILED_TO_COMMENT(8, "评论失败"),

}



