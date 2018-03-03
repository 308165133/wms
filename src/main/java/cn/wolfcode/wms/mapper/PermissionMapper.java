package cn.wolfcode.wms.mapper;

import java.util.List;
import java.util.Set;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.QueryObject;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    List<Permission> selectAll();

    int queryForCount(QueryObject qo);

    List<Permission> queryForList(QueryObject qo);

    List<String> getAllExpressions();

    Set<String> getExpressionsByEmployeeId(Long id);
}