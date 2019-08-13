package com.sf.jkt.k.comp.mq.rabbitmq

import com.alibaba.fastjson.JSON
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.support.converter.AbstractMessageConverter
import org.springframework.amqp.support.converter.DefaultClassMapper
import java.nio.charset.Charset

class RabbitMqFastJsonConverter : AbstractMessageConverter() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    companion object {
//        val classMaper = DefaultClassMapper()
        val classMaper=RabbitMqFastJsonClassMapper()
        val DEFAULT_CHARSET = "UTF-8"
    }

    override fun createMessage(any: Any, messageProperties: MessageProperties): Message {
        var bytes: ByteArray? = null
        val jsonString = JSON.toJSONString(any)
        bytes = jsonString.toByteArray(Charsets.UTF_8)
        messageProperties.contentType = MessageProperties.CONTENT_TYPE_JSON
        if (bytes != null) {
            messageProperties.contentLength = bytes.size.toLong()
        }
        classMaper.fromClass(any.javaClass, messageProperties)
        return Message(bytes, messageProperties)
    }

    override fun fromMessage(message: Message): Any {
        var content: Any? = null
        val properties = message.messageProperties
        if (properties != null) {
            val contentType = properties.contentType
            if (contentType != null && contentType.contains("json")) {
                var encoding = properties.contentEncoding
                if (encoding == null) {
                    encoding = DEFAULT_CHARSET
                }
                var targetClass = classMaper.toClass(properties)
                content = convertBytesToObject(message.body, encoding, targetClass)
            } else {
                log.info("不能转换不识别content-type:" + contentType)
            }
        }
        if (content == null) {
            content = message.body
        }
        return content!!
    }

    fun convertBytesToObject(body: ByteArray, encoding: String, clazz: Class<*>): Any {
        return JSON.parseObject(String(body, Charset.forName(encoding)), clazz)
    }
}

class RabbitMqFastJsonClassMapper:DefaultClassMapper{
   constructor(){
       setTrustedPackages("*")
   }
}