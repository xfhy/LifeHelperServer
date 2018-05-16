package com.xfhy.life.repository

import com.xfhy.life.domain.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * author xfhy
 * create at 2018/3/24 8:52
 * description：用户表  数据库操作
 */
//继承JpaRepository  bean类名,id类型
interface UserRepository : JpaRepository<User, Int> {

    fun findByBmobId(bmobId: String): User?
    fun findAllByBmobId(ids: MutableList<String>): MutableList<User>

}