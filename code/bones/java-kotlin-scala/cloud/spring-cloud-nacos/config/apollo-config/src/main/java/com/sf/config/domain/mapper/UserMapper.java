package com.sf.config.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sf.config.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("<script>"
            +"select * from user where name = #{name} and password = #{password}"
            +"</script>")
    public User selectByNameAndPassword(@Param("name") String name,@Param("password") String password);
}
