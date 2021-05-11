package com.sf.netfflix.app.provider.controller;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@WebServlet(value = "/async",asyncSupported = true)
public class MasyncServlet extends HttpServlet {
    TimeUnit unit;
    BlockingQueue workQueue;
    private ThreadPoolExecutor executor=new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1)
            );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext context=req.startAsync();
        AsyncRequestProcessor processor=new AsyncRequestProcessor(context,10);
        long start=System.currentTimeMillis();
        executor.execute(processor);
        long end=System.currentTimeMillis();
        System.out.println("cost*****"+(end-start));
    }
}
