package com.sf.jkt.k.web.config.mybatis

import com.baomidou.mybatisplus.core.parser.ISqlParser
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser
import net.sf.jsqlparser.expression.Expression
import net.sf.jsqlparser.expression.LongValue
import org.mybatis.spring.mapper.MapperScannerConfigurer
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector
import com.baomidou.mybatisplus.core.injector.ISqlInjector
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser


@Configuration
@MapperScan("com.sf")
class MybatisPlusConfig {
    @Bean
    fun paginationInterceptor(): PaginationInterceptor {
        var pagi = PaginationInterceptor()
        val tenantSqlParser = TenantSqlParser()
        tenantSqlParser.tenantHandler = object : TenantHandler {
            override fun doTableFilter(tableName: String?): Boolean {
                /**
                 * true 表示忽略某个表
                 * false 表示不忽略
                 */
                if ("user".equals(tableName)) {
                    return false
                }
                return true
            }

            override fun getTenantId(): Expression {
                //生产中可以从cookie中获得,比如集团ID,店铺ID之类
                return LongValue(1)
            }

            override fun getTenantIdColumn(): String {
                return "tenant_id"
            }
        }
        var sqlParseList = mutableListOf<ISqlParser>(tenantSqlParser)
        sqlParseList.add(BlockAttackSqlParser())
        pagi.sqlParserList = sqlParseList
        return pagi
    }

    /**
     * 相当于顶部的：
     * `@MapperScan("com.baomidou.springboot.mapper*")`
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
    @Bean
    fun mapperScannerConfigurer(): MapperScannerConfigurer {
        val scannerConfigurer = MapperScannerConfigurer()
        scannerConfigurer.setBasePackage("com.sf")
        return scannerConfigurer
    }

    @Bean
    fun getH2KeyGenerator(): H2KeyGenerator {
        return H2KeyGenerator()
    }

    /**
     * 性能分析拦截器，不建议生产使用
     */
    @Bean
    fun performanceInterceptor(): PerformanceInterceptor {
        return PerformanceInterceptor()
    }




}