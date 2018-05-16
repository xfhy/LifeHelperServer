package com.xfhy.life.controller

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 * author xfhy
 * create at 2018/3/24 9:36
 * description：产品操作  测试
 */
@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun likeProduct() {
        mvc.perform(MockMvcRequestBuilders.post("/product/like"))
                .andExpect(MockMvcResultMatchers.status().isOk) //状态码是200
    }
}