package com.sf.hoxton.api;


import com.sf.hoxton.dto.ResultVo;
import com.sf.hoxton.request.UserRequest;
import com.sf.hoxton.response.UserResponse;

public interface UserService {
    ResultVo<UserResponse> insert(UserRequest user);
    ResultVo<UserResponse> select(UserRequest user);
    ResultVo<UserResponse> update(UserRequest user);
    ResultVo<UserResponse> delete(UserRequest user);
}
