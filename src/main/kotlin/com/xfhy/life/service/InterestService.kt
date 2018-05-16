package com.xfhy.life.service

import com.xfhy.life.domain.Interest
import com.xfhy.life.repository.InterestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by xfhy on 2018/3/31 11:53
 * Description : 用户兴趣Service
 */
@Service
class InterestService {

    @Autowired
    private lateinit var interestRepository: InterestRepository

    /**
     * 添加一条新数据
     */
    fun addInterest(bmobId: String): Interest? {
        val interest = Interest(bmobId = bmobId)
        return interestRepository.save(interest)
    }

    /**
     * 查询一条数据
     */
    fun findOneInterestById(bmobId: String): Interest? {
        return interestRepository.findByBmobId(bmobId)
    }

    /**
     * 增加看知乎次数
     */
    fun addZHCount(interest: Interest?): Interest? {
        interest?.let {
            interest.zhCount++
            return interestRepository.save(interest)
        }
        return null
    }

    /**
     * 增加看视频次数
     */
    fun addVideoCount(interest: Interest?): Interest? {
        interest?.let {
            interest.videoCount++
            return interestRepository.save(interest)
        }
        return null
    }

    /**
     * 增加看天气次数
     */
    fun addWeatherCount(interest: Interest?): Interest? {
        interest?.let {
            interest.weatherCount++
            return interestRepository.save(interest)
        }
        return null
    }

    /**
     * 增加看新闻次数
     */
    fun addNewsCount(interest: Interest?): Interest? {
        interest?.let {
            interest.newsCount++
            return interestRepository.save(interest)
        }
        return null
    }

    /**
     * 增加看笑话次数
     */
    fun addJokeCount(interest: Interest?): Interest? {
        interest?.let {
            interest.jokeCount++
            return interestRepository.save(interest)
        }
        return null
    }

    /**
     * 增加看笑话次数
     */
    fun addTechCount(interest: Interest?): Interest? {
        interest?.let {
            interest.techCount++
            return interestRepository.save(interest)
        }
        return null
    }

}