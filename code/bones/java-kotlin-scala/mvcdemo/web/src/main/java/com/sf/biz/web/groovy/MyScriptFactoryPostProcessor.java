package com.sf.biz.web.groovy;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.scripting.support.ScriptFactoryPostProcessor;

public class MyScriptFactoryPostProcessor  extends ScriptFactoryPostProcessor {

    /****
     *
     删除定义同时执行以下操作：删除定义并销毁(删除该bean上的所有容器引用)相应的Singleton：

     ((BeanDefinitionRegistry) beanFactory).removeBeanDefinition("myBean");
     如果你只需要删除单例，那么：
     ((DefaultListableBeanFactory) beanFactory).destroySingleton("myBean");
     如果您只注册单例但未定义任何bean定义，则后一种方法可能特别有用，即
     ((SingletonBeanRegistry) beanFactory).registerSingleton("myBean", myBeanInstance);
     * @param singletonName
     */
    public void destroySingleton(String singletonName){


//        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(
//                (BeanDefinitionRegistry) this.applicationContext.getBeanFactory());
//        beanDefinitionReader.setResourceLoader(this.applicationContext);
//        beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this.applicationContext));
//        beanDefinitionReader.loadBeanDefinitions(new InMemoryResource(contextString));
//        beanDefinitionReader.setBeanClassLoader(GroovyDynamicLoader.class.getClassLoader());

//        String scriptFactoryBeanName = super.SCRIPT_FACTORY_NAME_PREFIX + singletonName;
//        String scriptedObjectBeanName = super.SCRIPTED_OBJECT_NAME_PREFIX + singletonName;
//        super.scriptBeanFactory.removeBeanDefinition(scriptFactoryBeanName);
//        super.scriptBeanFactory.removeBeanDefinition(scriptedObjectBeanName);
    }
}
