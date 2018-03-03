package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IEmployeeService {
    void deleteByPrimaryKey(Long id);

    void insert(Employee record,Long[] roleIds);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    void updateByPrimaryKey(Employee record,Long[] roleIds);

    PageResult query(QueryObject qo);

    /**
     * 批量删除
     * @param  ids 要删除数据的id
     */
    void batchDelete(Long[] ids);

    void login(String name, String password);

}
