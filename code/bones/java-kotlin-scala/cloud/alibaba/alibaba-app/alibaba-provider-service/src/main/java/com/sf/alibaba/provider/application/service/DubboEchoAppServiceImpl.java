package com.sf.alibaba.provider.application.service;

import com.sf.alibaba.api.DubboEchoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * Created by yu.hb on 2019-10-30
 */
@Service
public class DubboEchoAppServiceImpl implements DubboEchoService {

    @Override
    public String echo(String name) {
        return "DubboEchoServiceImpl#echo hi " + name ;
    }
}
