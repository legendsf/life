package com.sf.jkt.k.web.config.secruity.xsscors

import cn.hutool.http.HtmlUtil
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.sf.jkt.k.entity.Form5
import org.apache.commons.lang.ArrayUtils
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.jackson.JsonComponent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.util.StreamUtils
import org.springframework.web.util.HtmlUtils
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import javax.servlet.http.HttpUtils

@WebFilter(filterName = "xssFilter", urlPatterns = ["/**"], asyncSupported = true)
class XssFilter : Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val xssHttpServletRequestWraper = XssHttpServletRequestWraper(request as HttpServletRequest)
        chain!!.doFilter(xssHttpServletRequestWraper, response)
    }

}

class XssHttpServletRequestWraper : HttpServletRequestWrapper {
    lateinit var orgRequest: HttpServletRequest

    constructor(request: HttpServletRequest) : super(request) {
        orgRequest = request
    }

    override fun getParameter(name: String?): String? {
        var param = super.getParameter(name)
        return htmlEscape(param)
    }

    override fun getParameterValues(name: String?): Array<String>? {
        var params = super.getParameterValues(name)
        if (ArrayUtils.isEmpty(params)) {
            return null
        }
        for (i in 0 until params.size) {
            params[i] = htmlEscape(params[i])
        }
        return params
    }

    override fun getParameterMap(): MutableMap<String, Array<String>> {
        val paramMap = super.getParameterMap()
        val iterator = paramMap.entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            val values = entry.value as Array<String>
            for (i in 0 until values.size) {
                if (values[i] is String) {
                    values[i] = htmlEscape(values[i])!!
                }
            }
            entry.setValue(values)
        }
        return paramMap
    }


    override fun getHeader(name: String?): String? {
        val param = super.getHeader(name)
        return htmlEscape(param)
    }


    fun toStringOrNull(bytes: ByteArray): String? {
        try {
            return String(bytes, Charsets.UTF_8)
        } catch (e: Exception) {
            return null
        }
    }
}

fun htmlEscape(param: String?): String? {
    if (param != null) {
        return HtmlUtils.htmlEscape(param, "UTF-8")
    }
    return param
}




//@Configuration
//@JsonComponent
//class XssStringJsonSerializer : JsonSerializer<Form5>() {
//    override fun handledType(): Class<Form5> {
//        return Form5::class.java
//    }
//
//    @Bean
//    @Primary
//    fun objectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
//        val objectMapper = builder.createXmlMapper(false).build<ObjectMapper>()
//        val xssModule = SimpleModule("XssStringJsonSerializer")
//        xssModule.addSerializer(XssStringJsonSerializer())
////        xssModule.addDeserializer()
//        objectMapper.registerModule(xssModule)
//        return objectMapper
//    }
//
//    override fun serialize(value: Form5?, gen: JsonGenerator?, serializers: SerializerProvider?) {
//        if (value != null) {
//            gen!!.writeString(HtmlUtils.htmlEscape(value.text))
//        }
//    }
//
//}


class WrappedServletInputStream : ServletInputStream {
    var stream: InputStream

    constructor(stream: InputStream) : super() {
        this.stream = stream
    }

    override fun read(): Int {
        return stream.read()
    }

    override fun isReady(): Boolean {
        return true
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun setReadListener(listener: ReadListener?) {
    }
}