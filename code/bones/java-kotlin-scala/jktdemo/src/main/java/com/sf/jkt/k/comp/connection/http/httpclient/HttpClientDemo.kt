package com.sf.jkt.k.comp.connection.http.httpclient

import com.alibaba.fastjson.JSONObject
import org.apache.http.HeaderElement
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpPost
import org.apache.http.conn.ConnectionKeepAliveStrategy
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.message.BasicHeaderElementIterator
import org.apache.http.protocol.HTTP
import org.apache.http.protocol.HttpContext
import org.apache.http.util.EntityUtils
import org.hibernate.exception.ExceptionUtils
import org.slf4j.LoggerFactory

class HttpClientDemo {
    companion object {
        private val log = LoggerFactory.getLogger(this.javaClass)
        val timeOut = 10
        val cm = PoolingHttpClientConnectionManager()

        init {
            cm.maxTotal = 200
            cm.defaultMaxPerRoute = 20
        }

        val httpClient = createHttpClient()
        val keepAliveStrategy = object : ConnectionKeepAliveStrategy {
            override fun getKeepAliveDuration(response: HttpResponse, context: HttpContext): Long {
                val iterator = BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE))
                //代码很简单，就是通过Keep-Alive头信息中，获得timeout的值，作为超时时间；单位毫秒；
                //如请求头中 Keep-Alive: timeout=5, max=100
                iterator.forEach {
                    val he = it as HeaderElement
                    val name = he.name
                    val value = he.value
                    if (value != null && name.equals("timeout", true)) {
                        return value.toLong() * 1000
                    }
                }
                return 60 * 1000
            }
        }
        val requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut).setConnectTimeout(timeOut)
                .setSocketTimeout(timeOut).build()

        fun createHttpClient(): CloseableHttpClient {
            val httpClient = HttpClients.custom().setConnectionManager(cm).setConnectionManagerShared(true)
                    .setDefaultRequestConfig(requestConfig).setKeepAliveStrategy(keepAliveStrategy)
                    .build()
            return httpClient
        }

        fun doPostInJsonOutJson(url: String, jsonReq: JSONObject): JSONObject? {
            val post = HttpPost(url)
            post.config = requestConfig
            var jsonResult: JSONObject? = null
            try {
                val s = StringEntity(jsonReq.toString(), "UTF-8")
                s.setContentType("application/json")
                post.entity = s
                val httpResponse = httpClient.execute(post)
                val result = EntityUtils.toString(httpResponse.entity, "UTF-8")
                if (httpResponse.statusLine.statusCode == HttpStatus.SC_OK) {
                    jsonResult = JSONObject.parseObject(result)
                } else {
                    log.info("http error code:" + httpResponse.statusLine.statusCode)
                }
            } catch (e: Exception) {
                log.info("doPostInJsonOutJson错误:" + ExceptionUtils.getStackTrace(e))
            }
            return jsonResult
        }

    }
}