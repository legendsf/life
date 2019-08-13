package com.sf.jkt.k.web

import com.alibaba.fastjson.support.config.FastJsonConfig
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
import com.sf.jkt.k.Util.Log
import com.sf.jkt.k.biz.token.LogInterceptor
import com.sf.jkt.k.biz.token.TokenInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig {
    @Autowired
    lateinit var logInterceptor: LogInterceptor

    @Bean
    fun webMvcConfigure(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addInterceptors(registry: InterceptorRegistry) {
                registry.addInterceptor(logInterceptor).addPathPatterns("/**")
                registry.addInterceptor(TokenInterceptor()).addPathPatterns("/admin/**")
            }

            /**
             * 方法一，已验证可行
             * configure 会替换convertors 列表,原有列表清空
             */
//            override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
//                var builder = Jackson2ObjectMapperBuilder().indentOutput(true)
//                        .dateFormat(SimpleDateFormat("yyyy-MM-dd"))
//                        .modulesToInstall(ParameterNamesModule(), KotlinModule())
//                converters.add(MappingJackson2HttpMessageConverter(builder.build()))
//                converters.add(MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()))
//            }

            /***
             * extend 继承原默认列表
             * 方法二：
             */
//            override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
//                val convertor = FastJsonHttpMessageConverter()
//                val config = FastJsonConfig()
//                config.charset = Charsets.UTF_8
//                config.dateFormat = "yyyyMMdd HH:mm:ss"
//                convertor.fastJsonConfig = config
//                convertor.supportedMediaTypes = listOf<MediaType>(MediaType.APPLICATION_JSON_UTF8)
//                for (i in 0 until  converters.size) {
//                    if (converters[i] is MappingJackson2HttpMessageConverter) {
//                        converters[i] = convertor
//                    }
//                }
//                Log.log.info("************convertors:{}",converters)
//            }
        }
    }

}

