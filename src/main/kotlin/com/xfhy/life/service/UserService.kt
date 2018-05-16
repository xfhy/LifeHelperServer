package com.xfhy.life.service

import com.xfhy.life.domain.User
import com.xfhy.life.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * author xfhy
 * create at 2018/3/24 8:54
 * description：用户表  操作数据
 */
@Service
class UserService {

    companion object {
        /**
         * 一次签到的积分
         */
        val ONE_SIGN_IN_INTEGRAL = 2
    }

    @Autowired
    private lateinit var userRepository: UserRepository

    /**
     * 查询用户通过id
     */
    fun findOneUserById(bmobId: String): User? {
        return userRepository.findByBmobId(bmobId)
    }

    /**
     * 添加用户
     */
    fun addUser(bmobId: String, userName: String, password: String, phone: String): User? {
        val user = User()
        user.bmobId = bmobId
        user.userName = userName
        user.password = password
        user.phone = phone
        return userRepository.save(user)
    }

    /**
     * 更新用户头像
     */
    fun updateAvatarUrl(bmobId: String, avatarUrl: String): User? {
        val oldUser = userRepository.findByBmobId(bmobId)
        return if (oldUser != null) {
            oldUser.userAvatarUrl = avatarUrl
            userRepository.save(oldUser)
        } else {
            null
        }
    }

    /**
     * 用户签到
     */
    fun signInIntegral(bmobId: String): User? {
        val oldUser = userRepository.findByBmobId(bmobId)
        return if (oldUser != null) {
            oldUser.integral += ONE_SIGN_IN_INTEGRAL
            oldUser.signInTime = System.currentTimeMillis()
            userRepository.save(oldUser)
        } else {
            null
        }
    }

    /**
     * 查询该用户今天是否已经签到
     */
    fun isSignEd(user: User): Boolean {
        val currentCalendar = Calendar.getInstance()
        val userCalendar = Calendar.getInstance()
        userCalendar.timeInMillis = user.signInTime
        return currentCalendar.get(Calendar.YEAR) == userCalendar.get(Calendar.YEAR) &&
                currentCalendar.get(Calendar.MONTH) == userCalendar.get(Calendar.MONTH) &&
                currentCalendar.get(Calendar.DAY_OF_MONTH) == userCalendar.get(Calendar.DAY_OF_MONTH)
    }

}