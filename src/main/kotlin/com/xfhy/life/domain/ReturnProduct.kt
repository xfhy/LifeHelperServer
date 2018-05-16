package com.xfhy.life.domain

/**
 * Created by xfhy on 2018/3/25 10:36
 * Description : 产品信息  返参
 */
data class ReturnProduct(

        /**
         * 点赞数量
         */
        var likeCount: Int = 0,
        /**
         * 分享数量
         */
        var shareCount: Int = 0,
        /**
         * 评论数量
         */
        var commentCount: Int = 0

)