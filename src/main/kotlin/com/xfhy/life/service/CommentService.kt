package com.xfhy.life.service

import com.xfhy.life.domain.Comment
import com.xfhy.life.domain.ReturnComment
import com.xfhy.life.repository.CommentRepository
import com.xfhy.life.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * Created by xfhy on 2018/3/24 14:35
 * Description : 评论Service
 */
@Service
class CommentService {

    @Autowired
    private lateinit var commentRepository: CommentRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    /**
     * 添加评论
     */
    fun addComment(productId: Int, content: String, bmobId: String): Comment? {
        val comment = Comment(productId = productId, content = content,
                bmobId = bmobId, commentTime = System.currentTimeMillis() / 1000)
        return commentRepository.save(comment)
    }

    /**
     * 获取该产品所有评论数据
     */
    fun getComments(productId: Int): MutableList<ReturnComment>? {
        //该产品的评论列表
        val commentList = commentRepository.findAllByProductId(productId)
        //需要返回给客户端的评论列表
        val returnCommentList = mutableListOf<ReturnComment>()
        //遍历评论列表
        commentList?.forEach {
            val returnComment = ReturnComment()
            returnComment.content = it.content ?: ""
            returnComment.commentTime = it.commentTime
            returnCommentList.add(returnComment)
        }
        commentList?.forEachIndexed { index, comment ->
            //寻找某条评论对应的user数据
            val user = userRepository.findByBmobId(comment.bmobId)
            returnCommentList[index].bmobId = user?.bmobId ?: ""
            returnCommentList[index].userName = user?.userName ?: ""
            returnCommentList[index].userAvatarUrl = user?.userAvatarUrl ?: ""
        }

        return returnCommentList
    }

    /**
     * 获取产品评论数量
     */
    fun getCommentCount(productId: Int): Int {
        return commentRepository.countByProductId(productId)?.toInt() ?: 0
    }

}