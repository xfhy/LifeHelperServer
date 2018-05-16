package com.xfhy.life.domain

import javax.persistence.*

/**
 * author xfhy
 * create at 2018/3/24 10:41
 * description：收藏表
 */

//收藏表:该收藏id,用户id,收藏的产品id,收藏的产品类型
@Entity
data class Collect(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //id自增
        var collectId: Int = 0,
        /**
         * 用户id
         */
        var bmobId: String = "",
        /**
         * 收藏的产品id
         */
        var productId: Int = 0,
        /**
         * 收藏的产品类型   1:知乎    2:视频
         */
        var productType: Int = 0
)