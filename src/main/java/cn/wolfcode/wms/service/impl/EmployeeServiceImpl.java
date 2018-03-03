package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.mapper.EmployeeMapper;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.utils.LogicException;
import cn.wolfcode.wms.utils.MD5;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper mapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);

        mapper.deleteEmployeeRoleRelation(id);
    }

    @Override
    public void insert(Employee record, Long[] roleIds) {
        //给密码使用MD5加密
        record.setPassword(MD5.encode(record.getPassword()));
        mapper.insert(record);
        if (roleIds != null) {
            for (Long roleId : roleIds) {
                mapper.saveEmployeeRoleRelation(record.getId(), roleId);
            }
        }
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Employee record, Long[] roleIds) {
        mapper.updateByPrimaryKey(record);

        mapper.deleteEmployeeRoleRelation(record.getId());

        if (roleIds != null) {
            for (Long roleId : roleIds) {
                mapper.saveEmployeeRoleRelation(record.getId(), roleId);
            }
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return new PageResult(qo.getPageSize());
        }
        List<Employee> listData = mapper.queryForList(qo);
        return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void batchDelete(Long[] ids) {
        mapper.batchDelete(ids);
    }

    @Override
        public void login(String name, String password) {
            Employee currentEmp = mapper.login(name, MD5.encode(password));
            if (currentEmp == null) {
                //失败
                throw new LogicException("亲,账号或者密码错误");
        }
        //将当前用户共享到session中
        UserContext.setCurrentEmp(currentEmp);
        //将当前用户拥有所有的权限表达式共享到session
        Set<String> expressions = permissionMapper.getExpressionsByEmployeeId(currentEmp.getId());
        UserContext.setExpressions(expressions);
    }
}
