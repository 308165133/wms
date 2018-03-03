package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);

    int queryForCount(QueryObject qo);

    List<SystemMenu> queryForList(QueryObject qo);

    void deleteByParentId(Long parentId);

    List<Map<String,Object>> loadMenByParentSn(String parentSn);

    List<Map<String,Object>> loadMenByParentSnAndEmpId(@Param("parentSn") String parentSn, @Param("empId") Long empId);
}