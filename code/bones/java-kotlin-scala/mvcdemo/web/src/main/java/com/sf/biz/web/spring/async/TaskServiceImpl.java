package com.sf.biz.web.spring.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.*;

@RestController
@Slf4j
class BlockingController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MyTaskService myTaskService;

    @Autowired
    public BlockingController(MyTaskService myTaskService) {
        this.myTaskService = myTaskService;
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET, produces = "text/html")
    public String executeSlowTask() {
        logger.info("Request received");
        String result = myTaskService.execute();
        logger.info("Servlet thread released");
        helloAsync();
        return result;
    }

    @Async
    public void helloAsync(){
        try {
            System.out.println("hello");
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception e){
            log.error("helloAsync",e);
        }
    }
}

@RestController
class AsyncCallableController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MyTaskService myTaskService;
    ExecutorService exs= Executors.newCachedThreadPool();

    @Autowired
    public AsyncCallableController(MyTaskService myTaskService) {
        this.myTaskService = myTaskService;
    }

    @RequestMapping(value = "/deffered1")
    public DeferredResult<String> defered1(){
        StopWatch watch= StopWatch.createStarted();
        logger.info("defered1.Request received");
        DeferredResult<String> result = new DeferredResult<String>(20000L,"default Timeout");
         result.onCompletion(()->{
                 logger.info("deffered1 调用成功");
         });
         result.onTimeout(()-> logger.info("deffered1调用超时"));
         exs.execute(()->{
             try {
                 TimeUnit.SECONDS.sleep(10);
                 result.setResult("hello after 10000ms");
             }catch (Exception e){
                 e.printStackTrace();
             }
         });
        watch.split();
        logger.info("defered1.Servlet thread released:"+watch.getTime());
        return  result;
    }

    @RequestMapping(value = "/callable")
    public Callable<String> callableTest() {
        StopWatch watch= StopWatch.createStarted();
        logger.info("Request received");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(10);
                return "hello callable after 10000ms";
            }
        };
        watch.split();
        logger.info("Servlet thread released:"+watch.getTime());

        return callable;
    }
}

@RestController
 class AsyncDeferredController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MyTaskService myTaskService;

    @Autowired
    public AsyncDeferredController(MyTaskService myTaskService) {
        this.myTaskService = myTaskService;
    }

    @RequestMapping(value = "/deferred")
    public DeferredResult<String> executeSlowTask() {
        logger.info("Request received");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(myTaskService::execute)
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
        logger.info("Servlet thread released");

        return deferredResult;
    }


    @RequestMapping(value = "/email/servletReq")
    public String servletReq (HttpServletRequest request, HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();
        //设置监听器:可设置其开始、完成、异常、超时等事件的回调处理
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                System.out.println("超时了...");
                //做一些超时后的相关操作...
            }
            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                System.out.println("线程开始");
            }
            @Override
            public void onError(AsyncEvent event) throws IOException {
                System.out.println("发生错误："+event.getThrowable());
            }
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                System.out.println("执行完成");
                //这里可以做一些清理资源的操作...
            }
        });
        //设置超时时间
        asyncContext.setTimeout(20000);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    System.out.println("内部线程：" + Thread.currentThread().getName());
                    asyncContext.getResponse().setCharacterEncoding("utf-8");
                    asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                    asyncContext.getResponse().getWriter().println("这是异步的请求返回");
                } catch (Exception e) {
                    System.out.println("异常："+e);
                }
                //异步请求完成通知
                //此时整个请求才完成
                asyncContext.complete();
            }
        });
        //此时之类 request的线程连接已经释放了
        System.out.println("主线程：" + Thread.currentThread().getName());
        return "hello 异步 email 请求已收到";
    }



}


@Component
 class TaskServiceImpl implements MyTaskService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public String execute() {
        try {
            Thread.sleep(5000);
            logger.info("Slow task executed");
            return "Task finished";
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}

interface MyTaskService {
    String execute();
}