package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    int queryForCount(QueryObject qo);

    List<Employee> queryForList(QueryObject qo);

    void batchDelete(Long[] ids);

    void deleteEmployeeRoleRelation(Long id);

    void saveEmployeeRoleRelation(@Param("employeeId") Long id, @Param("roleId") Long roleId);

    Employee login(@Param("name") String name, @Param("password") String password);
}