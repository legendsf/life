package com.sf;

import com.sf.biz.web.service.MyChildService;
import com.sf.biz.web.service.MyService;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

import java.util.Arrays;

/***
 * log4j2 异步日志 有坑，所以用 logback 最稳妥最简单
 * https://www.cnblogs.com/yangfeiORfeiyang/p/9775863.html
 *
 * https://blog.csdn.net/seanxwq/article/details/103977487
 *
 * https://blog.csdn.net/u011493218/article/details/86607172
 * ***************************
 * spring cache
 * https://blog.csdn.net/qq_32448349/article/details/101696892
 */
@SpringBootApplication
@EnableCaching
@MapperScan
@EnableAsync
@EnableTransactionManagement
@EnableAdminServer
@EnableHystrixDashboard
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
public class Application implements ApplicationContextAware {
//    @Autowired
//   static  AnnotationConfigApplicationContext ctx;

    @Autowired
    static AnnotationConfigServletWebServerApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx= (AnnotationConfigServletWebServerApplicationContext) applicationContext;
    }

    public  void save$class(){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        final String path = this.getClass().getResource("").getPath();
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);
    }

    public static void main(String[] args) {
        new Application().save$class();
        //下面语句使得日志输出使用异步处理，减小输出日志对性能的影响
//        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        try {
            SpringApplication.run(Application.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        ctx.getBean("birdService");
        //不允许刷新两次
//        ctx.refresh();
//        ctx.getAutowireCapableBeanFactory().applyBeanPostProcessorsAfterInitialization(bd,"birdservice");
        testBeanRegistry();
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);

    }

    private static void testBeanRegistry() {
        DefaultListableBeanFactory beanFactory= new DefaultListableBeanFactory();
        beanFactory.setParentBeanFactory(ctx);
        BeanDefinitionBuilder builder= BeanDefinitionBuilder.genericBeanDefinition(MyService.class);
//        添加参数
//        builder.getBeanDefinition().getConstructorArgumentValues().addArgumentValues();

        beanFactory.registerBeanDefinition("myService",builder.getBeanDefinition());
        MyService myService=beanFactory.getBean(MyService.class);
        // 父亲容器获取不到子容器注册的bean
//        MyService myService1=ctx.getBean(MyService.class);
        System.out.println("parent容器： "+ctx.getParent());
        //销毁原来的实例，但是不销毁beandefinition
        beanFactory.destroySingleton("myService");
        //再次用beandefinition创建出新的bean
        MyService myService2=beanFactory.getBean(MyService.class);
        final BeanDefinition myServicebd = beanFactory.getBeanDefinition("myService");
        //销毁beandefinition并且销毁实例
        beanFactory.removeBeanDefinition("myService");
        //由于beandefinition被销毁所以创建不了新bean
//        MyService myService3=beanFactory.getBean(MyService.class);
        MyChildService childService=ctx.getBean(MyChildService.class);
        MyService childService2=ctx.getBean(MyChildService.class);
    }
}
