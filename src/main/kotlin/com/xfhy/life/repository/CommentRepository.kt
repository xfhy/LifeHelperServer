package com.xfhy.life.repository

import com.xfhy.life.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by xfhy on 2018/3/24 14:09
 * Description : 评论
 */
interface CommentRepository : JpaRepository<Comment, Int> {

    /**
     * 查询所有productId对应的评论
     */
    fun findAllByProductId(productId: Int): MutableList<Comment>?

    /**
     * 查询所有productId对应的评论的数量
     */
    fun countByProductId(productId: Int): Long?
}