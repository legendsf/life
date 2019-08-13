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
     fun insertEx(${entity?uncap_first}: ${entity}):Int
     fun deleteEx(${entity?uncap_first}: ${entity}):Int
     fun updateEx(map: Map<String, Any?>):Int
     fun selectEx(map: Map<String,Any?>):List<${entity}>
     fun insertBatchEx(${entity?uncap_first}s: List<${entity}>): Int
     fun deleteBatchEx(list: List<Long>): Int
     fun updateBatchEx(list: List<Long>): Int
     fun updateBatchDifEx(${entity?uncap_first}s: List<${entity}>): Int
     fun countEx(map: Map<String, Any>): Int
}
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    int insertEx(${entity} ${entity?uncap_first});
    int deleteEx(${entity} ${entity?uncap_first});
    int updateEx(Map<String, Object> map);
    List<${entity}> selectEx(Map<String, Object> map);
    int insertBatchEx(List<${entity}> ${entity?uncap_first}s);
    int deleteBatchEx(List<Long> list);
    int updateBatchEx(List<Long> list);
    int updateBatchDifEx(List<${entity}> ${entity?uncap_first}s);
    int countEx(Map<String, Object> map);
}
</#if>