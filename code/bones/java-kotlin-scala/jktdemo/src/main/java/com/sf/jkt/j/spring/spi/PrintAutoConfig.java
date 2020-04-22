package com.sf.jkt.j.spring.spi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PrintAutoConfig {

    @Bean
    public SpiFactoryBean printSpiProxy(ApplicationContext applicationContext) {
        return new SpiFactoryBean(applicationContext, Iprint.class);
    }

    /**
     * 用来生成第三方jar包配置的bean,不好在自己系统内用bean属性注入的bean
     *
     * @param spiFactoryBean
     * @return
     * @throws Exception
     * @primary,根据type 选择bean时候，优先选择这个bean
     */
    @Bean
    @Primary
    public Iprint printProxy(SpiFactoryBean spiFactoryBean) throws Exception {
        return (Iprint) spiFactoryBean.getObject();
    }

    @Bean
    public EmptyObj eptObj(  Iprint consolePrint) {
        System.out.println(consolePrint.getClass());
        return new EmptyObj();
    }

    @Bean
    public EmptyObj eptObj2(Iprint logPrint) {
        System.out.println(logPrint.getClass());
        return new EmptyObj();
    }

    @Bean
    public EmptyObj eptObj3( @Qualifier(value = "logPrint") Iprint iprint){
        System.out.println(iprint.getClass());
        return new EmptyObj();
    }



}
