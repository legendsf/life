package com.sf.jkt.k.auto.mapper;

import com.sf.jkt.k.auto.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper
import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
  * </p>
 *
 * @author songfei
 * @since 2019-08-13
 */
@Mapper
interface UserMapper : BaseMapper<User>{
     fun insertEx(user: User):Int
     fun deleteEx(user: User):Int
     fun updateEx(map: Map<String, Any?>):Int
     fun selectEx(map: Map<String,Any?>):List<User>
     fun insertBatchEx(users: List<User>): Int
     fun deleteBatchEx(list: List<Long>): Int
     fun updateBatchEx(list: List<Long>): Int
     fun updateBatchDifEx(users: List<User>): Int
     fun countEx(map: Map<String, Any>): Int
}
