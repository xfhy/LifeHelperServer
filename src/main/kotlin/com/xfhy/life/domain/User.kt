package com.xfhy.life.domain

import org.hibernate.validator.constraints.Length
import java.io.Serializable
import javax.persistence.*

/**
 * author xfhy
 * create at 2018/3/23 21:03
 * description： 用户表
 */

@Entity
class User : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id自增
    var userId = 0

    /**
     * bmob上的id
     */
    @Column(unique = true)  //唯一
    var bmobId: String = ""

    /**
     * 用户名
     */
    @Column(nullable = false)
    var userName = ""

    /**
     * 用户密码
     */
    @Column(nullable = false)
    var password = ""

    /**
     * 用户手机号码
     */
    @Column(nullable = false)
    @Length(max = 11)
    var phone = ""

    /**
     * 用户头像地址
     */
    var userAvatarUrl: String? = ""

    /**
     * 上次签到时间
     */
    var signInTime: Long = 0

    /**
     * 积分
     */
    var integral = 0

}