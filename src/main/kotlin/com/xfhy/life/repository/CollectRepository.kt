package com.xfhy.life.repository

import com.xfhy.life.domain.Collect
import org.springframework.data.jpa.repository.JpaRepository

/**
 * author xfhy
 * create at 2018/3/24 10:45
 * description：收藏
 */
interface CollectRepository : JpaRepository<Collect, Int> {

    /**
     * 查询 by 用户id和产品id
     */
    fun findByBmobIdAndProductId(bmobId: String, productId: Int): Collect?

    /**
     * 查询该用户所有收藏
     */
    fun findAllByBmobId(bmobId: String): MutableList<Collect>?

}