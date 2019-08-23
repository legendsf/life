package com.sf.jkt.k.web.config.mybatis

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import com.sf.jkt.k.Util.Log
import org.apache.ibatis.reflection.MetaObject
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class MyMetaObjectHandler:MetaObjectHandler {
    override fun insertFill(metaObject: MetaObject?) {
        Log.log.info("start insert fill")
        this.setFieldValByName("createTime",LocalDateTime.now(),metaObject)
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject)
    }

    override fun updateFill(metaObject: MetaObject?) {
        Log.log.info("start update fill")
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject)
    }
}