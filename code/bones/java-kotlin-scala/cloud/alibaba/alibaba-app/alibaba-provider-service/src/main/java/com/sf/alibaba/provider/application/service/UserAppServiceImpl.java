package com.sf.alibaba.provider.application.service;


import com.sf.alibaba.api.UserService;
import com.sf.alibaba.dto.TbUser;
import com.sf.alibaba.provider.domain.repository.mapper.TbUserMapper;
import com.sf.alibaba.provider.domain.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Application 层的 service 是薄薄的一层
 * 主要逻辑在领域服务的 service 中处理
 *
 */
@Service
@Slf4j
public class UserAppServiceImpl implements UserService {
    @Autowired
    UserServiceImpl userService;

    @Override
    public void add(TbUser user) {
        userService.add(user);
    }
}
