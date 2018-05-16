package com.xfhy.life.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * author xfhy
 * create at 2018/3/24 8:11
 * description：产品:文章或视频 bean
 */
@Entity
data class Product(
        @Id
        var productId: Int = 0,   //这个id是非自增的,因为知乎文章id和视频的id是不固定的
        /**
         * 点赞数量
         */
        var likeCount: Int = 0,
        /**
         * 分享数量
         */
        var shareCount: Int = 0)