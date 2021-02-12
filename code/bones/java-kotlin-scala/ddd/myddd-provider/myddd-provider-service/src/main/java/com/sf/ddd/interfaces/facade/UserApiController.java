package com.sf.ddd.interfaces.facade;

import com.sf.ddd.api.IUserApi;
import com.sf.ddd.dto.UserInfoDto;
import com.sf.ddd.request.UserCommandRequest;
import com.sf.ddd.request.UserQueryRequest;
import org.springframework.stereotype.Controller;

/***
 * 协议 http
 *  openFeign
 * 也可以是 grpc
 * 也可以是 dubbo
 *
 * netfix 停更，替换组件
 * https://zhuanlan.zhihu.com/p/161824919
 */
@Controller
public class UserApiController implements IUserApi {
    @Override
    public UserInfoDto queryUser(UserQueryRequest req) {
        return null;
    }

    @Override
    public UserInfoDto createUser(UserCommandRequest req) {
        return null;
    }
}
