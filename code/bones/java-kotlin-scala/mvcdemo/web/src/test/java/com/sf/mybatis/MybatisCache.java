package com.sf.mybatis;

/**
 *  mybatis 本身默认开启一级缓存级别是 sqlsession
 *  springboot 整合 mybatis,的一级缓存在事务中有效，没有事务失效
 *       没有事务每次操作都会关闭 sqlsession 所以一级缓存没用
 *       在事务里面，由于没有最终 commit，所以两次同样条件的查询会用到一级缓存
 * https://www.cnblogs.com/zhengxl5566/p/11868656.html
 *
 * https://blog.csdn.net/xingxiupaioxue/article/details/52998211?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control
 *
 *
 */
public class MybatisCache {
}
