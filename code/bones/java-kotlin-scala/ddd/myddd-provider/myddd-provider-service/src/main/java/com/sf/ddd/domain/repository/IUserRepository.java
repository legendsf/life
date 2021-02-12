package com.sf.ddd.domain.repository;

import com.sf.ddd.domain.entity.UserEntity;

public interface IUserRepository {
    int save(UserEntity userEntity);
    UserEntity query(UserEntity userEntity);
}
