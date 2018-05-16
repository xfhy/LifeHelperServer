package com.xfhy.life.domain

import javax.persistence.*

/**
 * Created by xfhy on 2018/3/31 11:33
 * Description : 用户兴趣
 */
@Entity
data class Interest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //id自增
        var interestId: Int = 0,
        /**
         * 用户id
         */
        @Column(unique = true)  //唯一
        var bmobId: String = "",
        /**
         * 看知乎次数
         */
        var zhCount: Int = 0,
        /**
         * 看视频次数
         */
        var videoCount: Int = 0,
        /**
         * 看新闻次数
         */
        var newsCount: Int = 0,
        /**
         * 看天气次数
         */
        var weatherCount: Int = 0,
        /**
         * 看笑话次数
         */
        var jokeCount: Int = 0,
        /**
         * 看技术文章次数
         */
        var techCount: Int = 0
)