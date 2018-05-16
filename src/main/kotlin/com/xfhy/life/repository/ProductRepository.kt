package com.xfhy.life.repository

import com.xfhy.life.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

/**
 * author xfhy
 * create at 2018/3/24 8:10
 * description：数据库操作  SpringData
 * 继承JpaRepository  bean类名,id类型
 */
interface ProductRepository : JpaRepository<Product, Int> {
}