package com.sf.alibaba.provider.domain.repository.mapper;


import com.sf.alibaba.dto.TbUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);
}