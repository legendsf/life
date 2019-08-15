package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import java.util.List;
import java.util.Map;

/**
 * <p>
  * ${table.comment!} Mapper 接口
  * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>{
 <#if cfg["genInsert"]??>
     fun insertEx(${entity?uncap_first}: ${entity}):Int
 </#if>
 <#if cfg["genDelete"]??>
     fun deleteEx(${entity?uncap_first}: ${entity}):Int
 </#if>
 <#if cfg["genUpdate"]??>
     fun updateEx(map: Map<String, Any?>):Int
 </#if>
 <#if cfg["genSelect"]??>
     fun selectEx(map: Map<String,Any?>):List<${entity}>
     fun countEx(map: Map<String, Any>): Int
 </#if>
 <#if cfg["genInsertBatch"]??>
     fun insertBatchEx(${entity?uncap_first}s: List<${entity}>): Int
 </#if>
 <#if cfg["gendeleteBatch"]??>
     fun deleteBatchEx(list: List<Long>): Int
 </#if>
 <#if cfg["genUpdateBatch"]??>
     fun updateBatchEx(list: List<Long>): Int
     fun updateBatchDifEx(${entity?uncap_first}s: List<${entity}>): Int
</#if>
}
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
 <#if cfg["genInsert"]??>
    int insertEx(${entity} ${entity?uncap_first});
  </#if>
 <#if cfg["genDelete"]??>
    int deleteEx(${entity} ${entity?uncap_first});
  </#if>
 <#if cfg["genUpdate"]??>
    int updateEx(Map<String, Object> map);
  </#if>
 <#if cfg["genSelect"]??>
    List<${entity}> selectEx(Map<String, Object> map);
    int countEx(Map<String,Object> map);
  </#if>
 <#if cfg["genInsertBatch"]??>
    int insertBatchEx(List<${entity}> ${entity?uncap_first}s);
  </#if>
 <#if cfg["genDeleteBatch"]??>
    int deleteBatchEx(List<Long> list);
  </#if>
 <#if cfg["genUpdateBatch"]??>
    int updateBatchEx(List<Long> list);
    int updateBatchDifEx(List<${entity}> ${entity?uncap_first}s);
  </#if>
}
</#if>