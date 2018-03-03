package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.mapper.SystemMenuMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper mapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        //删除子菜单
        mapper.deleteByParentId(id);
        //删除当前菜单
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(SystemMenu record) {
        mapper.insert(record);
    }

    @Override
    public SystemMenu selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemMenu> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(SystemMenu record) {
        mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return new PageResult(qo.getPageSize());
        }
        List<SystemMenu> listData = mapper.queryForList(qo);
        return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public List<Map<String, Object>> loadMenByParentSn(String parentSn) {
        //获取到当前登录的用户:记得登录
        Employee currentEmp = UserContext.getCurrentEmp();
        if (currentEmp.isAdmin()) {
            return mapper.loadMenByParentSn(parentSn);
        }else{
            //不是超级管理员,那么根据当前用户去查询对应的菜单
            return mapper.loadMenByParentSnAndEmpId(parentSn,currentEmp.getId());
        }
    }
}
