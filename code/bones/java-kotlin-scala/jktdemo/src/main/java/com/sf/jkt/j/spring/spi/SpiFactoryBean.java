package com.sf.jkt.j.spring.spi;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SpiFactoryBean<T> implements FactoryBean<T> {

    private Class<? extends ISpi> spiClz;
    private List<ISpi> list;

    /**
     * 顺序
     * printAutoConfig->SpiFactoryBean->constructor-> getBeansOfType(Iprint)-> list(ConsolePrint,LogPrint)
     * ->printProxy->SpiFactoryBean.getObject()->
     * dynamicProxy.execute(int,msg)
     * ->invocationHandler.invoke
     * ->choose by int, if <= 0 is consolePrint else is LogPrint
     * ->print(msg)
     * @param applicationContext
     * @param spiClz
     */
    public SpiFactoryBean(ApplicationContext applicationContext, Class<? extends ISpi> spiClz) {
        this.spiClz = spiClz;
        Map<String, ? extends ISpi> map = applicationContext.getBeansOfType(spiClz);
        list = new ArrayList<>(map.values());
        list.sort(Comparator.comparingInt(ISpi::order));
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                for (ISpi spi : list) {
                    if (spi.verify(args[0])) {
                        //系统内注入了 consolePrint 和 logPrint
                        return method.invoke(spi, args);
                    }
                }
                throw new IllegalArgumentException("no spi server found spilist: " + list);
            }
        };
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{spiClz}, invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return spiClz;
    }
}
