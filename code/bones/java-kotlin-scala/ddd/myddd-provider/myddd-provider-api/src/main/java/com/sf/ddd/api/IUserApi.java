package com.sf.ddd.api;

import com.sf.ddd.dto.UserInfoDto;
import com.sf.ddd.request.UserCommandRequest;
import com.sf.ddd.request.UserQueryRequest;

public interface IUserApi {
    UserInfoDto queryUser(UserQueryRequest req);
    UserInfoDto createUser(UserCommandRequest req);
}
