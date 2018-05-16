package com.xfhy.life.service

import com.xfhy.life.domain.Collect
import com.xfhy.life.enums.ProductType
import com.xfhy.life.repository.CollectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * author xfhy
 * create at 2018/3/24 10:46
 * description：收藏Service
 */
@Service
class CollectService {

    @Autowired
    private lateinit var collectRepository: CollectRepository

    /**
     * 判断该用户是否已经收藏
     */
    fun isCollected(bmobId: String, productId: Int): Collect? {
        return collectRepository.findByBmobIdAndProductId(bmobId, productId)
    }

    /**
     * 收藏产品
     */
    fun collectProduct(bmobId: String, productId: Int, productType: Int): Collect? {
        if (ProductType.zhihu != productType && ProductType.video != productType) {
            return null
        }
        val collect = Collect(bmobId = bmobId, productId = productId, productType = productType)
        return collectRepository.save(collect)
    }

    /**
     * 取消收藏产品
     */
    fun cancelCollectProduct(collect: Collect) {
        collectRepository.delete(collect)
    }

    /**
     * 查询用户收藏的所有产品
     */
    fun getAllCollected(bmobId: String): MutableList<Collect>? {
        return collectRepository.findAllByBmobId(bmobId)
    }

}