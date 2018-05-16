package com.xfhy.life.domain

/**
 * Created by xfhy on 2018/3/25 9:04
 * Description : 评论  返参
 */
data class ReturnComment(
        /**
         * 评论内容
         */
        var content: String? = "",
        /**
         * 评论时间
         */
        var commentTime: Long = 0L,
        /**
         * 用户id
         */
        var bmobId: String = "",
        /**
         * 用户名
         */
        var userName: String = "",
        /**
         * 用户头像地址
         */
        var userAvatarUrl: String? = ""
)