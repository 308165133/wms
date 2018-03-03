package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IDepartmentService {
    void deleteByPrimaryKey(Long id);

    void insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    void updateByPrimaryKey(Department record);

    PageResult query(QueryObject qo);
}
