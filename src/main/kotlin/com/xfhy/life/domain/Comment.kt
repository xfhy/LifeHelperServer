package com.xfhy.life.domain

import javax.persistence.*

/**
 * author xfhy
 * create at 2018/3/24 8:19
 * description： 评论表
 */
@Entity
data class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //id自增
        val commentId: Int = 0,
        /**
         * 文章的id
         */
        @Column(nullable = false)
        var productId: Int = 0,
        /**
         * 用户id
         */
        var bmobId: String = "",
        /**
         * 评论内容
         */
        var content: String? = "",
        /**
         * 评论时间
         */
        val commentTime: Long = 0L
)