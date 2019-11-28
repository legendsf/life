package com.sf.jkt.k.biz.mybatisplus

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.sf.jkt.k.Util.AgeEnum
import com.sf.jkt.k.Util.GradeEnum
import com.sf.jkt.k.auto.entity.User
import com.sf.jkt.k.auto.mapper.UserMapper
import com.sf.jkt.k.util.SpringBootBaseTest
import  org.junit.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class MybatisPlusTest : SpringBootBaseTest() {

    @Autowired
    lateinit var userMapper: UserMapper

    @Test
    fun testMapperCURD() {
        val user = User();
        user.name = "fei2000"
        user.sex = 1
        var result = userMapper.insert(user)
        assertEquals(1, result)
        val userList = userMapper.selectList(QueryWrapper<User>().eq("name", "fei2000"))
        user.id = userList[0].id
        user.sex = 0
        result = userMapper.updateById(user)
        assertEquals(1, result)
        val exUser = userMapper.selectById(user.id)
        assertEquals(user.id, exUser.id)
        val delResult = userMapper.deleteById(user.id)
        assertEquals(1, delResult)
        println("END")
    }

    @Test
    fun testAactiveRecordCURD() {
        val user = User();
        user.name = "fei2001"
        user.sex = 1
        user.age = AgeEnum.THREE
        user.grade = GradeEnum.HIGH
        var result = user.insert()
        assertEquals(true, result)
        var userList = user.selectList(QueryWrapper<User>().eq("name", "fei2001"))
        val id = userList[0].id
        user.id = id
        user.name = "fei2001_update"
        result = user.updateById()
        assertEquals(true, result)
        result = user.deleteById()
        assertEquals(true, result)
        println("END")
    }

    @Test
    fun testPagination() {
        var page = Page<User>(0, 20)
        page.setSearchCount(false)
        var wrapper = QueryWrapper<User>().eq("name", "fei")
        var result = userMapper.selectPage(page, wrapper)
        println(result)
    }

    @Test
    fun testLogicDelete() {
        var result = userMapper.deleteById(10)
        var user = userMapper.selectById(10)
        println("END")
    }
}