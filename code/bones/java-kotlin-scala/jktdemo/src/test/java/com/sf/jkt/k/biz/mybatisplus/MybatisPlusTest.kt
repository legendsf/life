package com.sf.jkt.k.biz.mybatisplus

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.sf.jkt.k.auto.entity.User
import com.sf.jkt.k.auto.mapper.UserMapper
import com.sf.jkt.k.util.SpringBootBaseTest
import  org.junit.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class MybatisPlusTest:SpringBootBaseTest() {

    @Autowired
    lateinit var userMapper: UserMapper

    @Test
    fun testMapperCURD(){
        val user=User();
        user.name="fei2000"
        user.sex=1
        var result=userMapper.insert(user)
        assertEquals(1,result)
        val userList=userMapper.selectList(QueryWrapper<User>().eq("name","fei2000"))
        user.id=userList[0].id
        user.sex=0
        result=userMapper.updateById(user)
        assertEquals(1,result)
        val exUser=userMapper.selectById(user.id)
        assertEquals(user.id,exUser.id)
        val delResult=userMapper.deleteById(user.id)
        assertEquals(1,delResult)
        println("END")
    }

    @Test
    fun testAactiveRecordCURD(){
        val user=User();
        user.name="fei2001"
        user.sex=1
        var result=user.insert()
        assertEquals(true,result)
        var userList=user.selectList(QueryWrapper<User>().eq("name","fei2001"))
        val id=userList[0].id
        user.id=id
        user.name="fei2001_update"
        result=user.updateById()
        assertEquals(true,result)
        result=user.deleteById()
        assertEquals(true,result)
        println("END")
    }
}