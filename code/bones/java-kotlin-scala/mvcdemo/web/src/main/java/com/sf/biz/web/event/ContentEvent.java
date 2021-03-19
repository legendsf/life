package com.sf.biz.web.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author yule
 * @desc 定义监听事件
 * @create 2019/1/25
 **/
public class ContentEvent extends ApplicationEvent {
    //消息体，需要异步处理的数据
    private ScanData content;

    public ContentEvent(Object source, ScanData content) {
        super(source);
        this.content = content;
    }

    public ScanData getData() {
        return content;
    }
}
