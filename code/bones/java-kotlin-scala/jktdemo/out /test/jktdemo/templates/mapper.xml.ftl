<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
      <#list table.fields as field>
        <#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}"/>
        </#if>
      </#list>
      <!--公共字段-->
<#list table.commonFields as field><#--生成公共字段 -->
    <result column="${field.name}" property="${field.propertyName}"/>
</#list>
      <!--普通字段-->
<#list table.fields as field>
  <#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}"/>
  </#if>
</#list>
    </resultMap>
</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="queryRefid">
      <#list table.commonFields as field>
        ${field.name},
      </#list>
      ${table.fieldNames}
    </sql>
</#if>
<#if cfg["genInsert"]??>

 <!--单体新增-->
  	<insert id="insertEx" parameterType="${package.Entity}.${entity}">
      insert into ${table.name} (
      <trim prefix="" suffixOverrides=",">
        <#list table.fields as field>
          <if test="${field.propertyName} != null"> ${field.name},</if>
        </#list>
      </trim>
      ) values (
      <trim prefix="" suffixOverrides=",">
        <#list table.fields as field>
          <if test="${field.propertyName} != null"> ${"#{"+field.propertyName+"}"},</if>
        </#list>
      </trim>
      )
    </insert>
</#if>
<#if cfg["genInsertBatch"]??>

<!--批量新增,传入的是List-->
<!--性能好，是一条sql语句，整体在一个事务中,一条失败整体回滚,返回的是操作记录的总条数-->
    <insert id="insertBatchEx" parameterType="java.util.List">
      insert into ${table.name} (
      <trim prefix="" suffixOverrides=",">
        <#list table.fields as field>
          ${field.name},
        </#list>
      </trim>
      ) values
      <foreach collection="list" item="item" index="index" separator=",">
        (
        <trim prefix="" suffixOverrides=",">
          <#list table.fields as field>
            <if test="item.${field.propertyName} != null"> ${r'#{'+"item.${field.propertyName}"+r'}'},</if>
          </#list>
        </trim>
        )
      </foreach>
    </insert>
</#if>
<#if cfg["genDelete"]??>

<!--单体删除-->
   <delete id="deleteEx" parameterType="${package.Entity}.${entity}">
     delete from ${table.name} where id = ${r'#{'+"id"+r'}'}
   </delete>
</#if>
<#if cfg["genDeleteBatch"]??>

<!--批量删除-->
   <delete id="deleteBatchEx" parameterType="java.util.List">
     delete from ${table.name} where id in
     <foreach collection="list" item="item" index="index" open="(" separator="," close=")">
       ${r'#{'+"item"+r'}'}
     </foreach>
   </delete>
</#if>
<#if cfg["genUpdate"]??>

<!--单体修改,传入的是bean的属性组装的Map-->
	<update id="updateEx" parameterType="java.util.Map">
    update ${table.name}
    set
    <trim prefix="" suffixOverrides=",">
        <#list table.fields as field>
          <if test="${field.propertyName} != null"> ${field.name} = ${"#{"+field.propertyName+"}"}
            ,
          </if>
        </#list>
    </trim>
    where id = ${r'#{'+"id"+r'}'}
  </update>
</#if>
<#if cfg["genUpdateBatch"]??>

<!--个性化批量修改,每个对象改的值不同,传入的是${"List<"+table.mapperName+">"}-->
<!--性能好,只有一条sql语句,如果该批次一条执行不成功，整批次回滚，整批次是一个事务-->
  <update id="updateBatchDifEx" parameterType="java.util.List">
    update ${table.name}
    set
    <trim suffixOverrides=",">
    <#list table.fields as field>
      <#if field.propertyName !="id">
        <trim prefix="${field.name} = case " suffix="end,">
          <foreach collection="list" index="index" item="item">
            <if test="item.${field.propertyName} !=null ">
              when  ${r'#{'+"item.id"+r'}'} then ${r'#{'+"item.${field.propertyName}"+r'}'}
            </if>
          </foreach>
        </trim>
      </#if>
    </#list>
    </trim>
    where id in
    <foreach collection="list" index="index" item="item" separator="," open="(" close=")">
      ${r'#{'+"item.id"+r'}'}
    </foreach>
  </update>

<!--批量修改,多个ID改为相同的值,传入的是List<PrimaryKey>-->
<update id="updateBatchEx" parameterType="java.util.List">
  update ${table.name} set user_status = '0' where id in
  <foreach collection="list" item="id" open="(" separator="," close=")">
    ${r'#{'+"id"+r'}'}
  </foreach>
</update>
</#if>
<#if cfg["genSelect"]??>

<!--查询-->
  <select id="countEx" resultType="java.lang.Integer" parameterType="java.util.Map">
    select count(1) from ${table.name}
    <trim prefix="WHERE" prefixOverrides="AND|OR">
      <#list table.fields as field>
        <if test="${field.propertyName} != null"> and  ${field.name}
          = ${r'#{'+field.propertyName+r'}'} </if>
      </#list>
    </trim>
  </select>

  <!--传入的参数为Bean的属性组装的map-->
  <select id="selectEx" resultType="${package.Entity}.${entity}" parameterType="java.util.Map">
    select
    <include refid="queryRefid"/>
    from ${table.name}
    <trim prefix="WHERE" prefixOverrides="AND|OR">
      <#list table.fields as field>
        <if test="${field.propertyName} != null"> and  ${field.name}
          = ${"#{"+field.propertyName+"}"} </if>
      </#list>
    </trim>
  </select>
</#if>
</mapper>
