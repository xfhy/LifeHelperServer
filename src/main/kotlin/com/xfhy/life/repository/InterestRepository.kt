package com.xfhy.life.repository

import com.xfhy.life.domain.Interest
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by xfhy on 2018/3/31 11:52
 * Description : 用户兴趣
 */
interface InterestRepository : JpaRepository<Interest, Int> {
    fun findByBmobId(bmobId: String): Interest?
}