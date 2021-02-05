package com.sf.biz.web.spring;

import com.sf.biz.web.annotation.MyScan;
import com.sf.biz.web.mapper.BirdMapper;
import com.sf.biz.web.spring.proxy.mybatis.MapperFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * map 里面存放的 beandefinition
 */
public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyScan.class.getName());
        String o=(String)annotationAttributes.get("value");


        List<Class> mapperList=new ArrayList<>();
        mapperList.add(BirdMapper.class);
        for(Class mapper:mapperList){
            BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition();
            AbstractBeanDefinition bd= builder.getBeanDefinition();
            //spring 根据 bd 创建对象时，可以找到对应的构造参数，构造参数推断¡
            bd.getConstructorArgumentValues().addGenericArgumentValue(mapper);
            bd.setBeanClass(MapperFactoryBean.class);
            registry.registerBeanDefinition(StringUtils.uncapitalize(mapper.getSimpleName()),bd);
        }
    }
}
