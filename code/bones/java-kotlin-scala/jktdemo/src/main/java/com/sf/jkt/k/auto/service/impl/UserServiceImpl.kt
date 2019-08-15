package com.sf.jkt.k.auto.service.impl;

import com.sf.jkt.k.auto.entity.User;
import com.sf.jkt.k.auto.mapper.UserMapper;
import com.sf.jkt.k.auto.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songfei
 * @since 2019-08-14
 */
@Service
open class UserServiceImpl : ServiceImpl<UserMapper, User>(), IUserService {

}
