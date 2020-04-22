package com.sf.jkt.j.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DynamicClassLoader implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadBean();
    }

    public void loadBean() {

    }
}


class InMemoryResource extends AbstractResource {

    private static final String DESCRIPTION = "InMemoryResource";

    private byte[] source;

    public InMemoryResource(byte[] source) {
        this.source = source;
    }

    public byte[] getSource() {
        return source;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(source);
    }

    @Override
    public boolean equals(Object res) {
        if (!(res instanceof InMemoryResource)) {
            return false;
        }
        return Arrays.equals(source, ((InMemoryResource) res).getSource());
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(source);
    }
}

class DataSourceBeanDefinitionReader extends AbstractBeanDefinitionReader {
    private final ThreadLocal<Set<InMemoryResource>> resourceCurrentlyBeingLoaded =
            new NamedThreadLocal<Set<InMemoryResource>>("data source bean definition resources currently being loaded");

    protected DataSourceBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        Set<InMemoryResource> currentResources = this.resourceCurrentlyBeingLoaded.get();
        if (currentResources == null) {
            currentResources = new HashSet<InMemoryResource>();
            this.resourceCurrentlyBeingLoaded.set(currentResources);
        }
        byte[] classBytes=new byte[0];
        InMemoryResource inMemoryResource = new InMemoryResource(classBytes);
        if (!currentResources.add(inMemoryResource)) {
            throw new BeanDefinitionStoreException("Detected cyclic loading of" + inMemoryResource +
                    " - check your import definitions");
        }



        return 0;
    }
}