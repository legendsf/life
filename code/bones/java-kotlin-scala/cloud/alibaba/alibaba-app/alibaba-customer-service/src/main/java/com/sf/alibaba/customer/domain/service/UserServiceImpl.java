package com.sf.alibaba.customer.domain.service;

import com.sf.alibaba.customer.domain.repository.mapper.TbUserMapper;
import com.sf.alibaba.dto.TbUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl {
    @Autowired
    private TbUserMapper userMapper;

    public void add(TbUser user) {
        log.info("add user:{}", user);

        user.setName("provider");
        userMapper.insert(user);
    }
}
