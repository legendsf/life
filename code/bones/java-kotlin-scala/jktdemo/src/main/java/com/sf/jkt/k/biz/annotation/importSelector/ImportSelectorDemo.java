package com.sf.jkt.k.biz.annotation.importSelector;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
public class ImportSelectorDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfigConfig.class);
        AppConfig appConfig= applicationContext.getBean(AppConfig.class);
        System.out.println(appConfig.toString());
    }
}

@Configuration //表明此类是配置类
@PropertySource("") // 读取application.properties
//@MapperScan("") //扫描Mybatis的Mapper接口
@EnableTransactionManagement //开启事务管理
@EnableSpringStudy
@ComponentScan("com.sf.jkt.k.biz.annotation")
class AppConfigConfig {

}
class AppConfig{

}
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SpringStudySelector.class)
@interface EnableSpringStudy {

}

class SpringStudySelector implements ImportSelector, BeanFactoryAware {
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        importingClassMetadata.getAnnotationTypes().forEach(System.out::println);
        System.out.println(beanFactory);
        return new String[]{AppConfig.class.getName()};
    }
}
