package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.mapper.RoleMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper mapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
        //同时将关系删除
        mapper.deleteRolePermissionRelation(id);
        //删除角色和菜单的关系
        mapper.deleteRoleSystemMenuRelation(id);
    }

    @Override
    public void insert(Role record,Long[] permissionIds,Long[] systemMenuIds) {
        mapper.insert(record);
        //保存角色和权限的关系
        if(permissionIds!=null){
            for (Long permissionId : permissionIds) {
                mapper.saveRolePermissionRelation(record.getId(),permissionId);
            }
        }
        //保存角色和菜单的关系
        if(permissionIds!=null){
            for (Long systemMenuId : systemMenuIds) {
                mapper.saveRoleSystemMenuRelation(record.getId(),systemMenuId);
            }
        }
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Role record,Long[] permissionIds,Long[] systemMenuIds) {
        mapper.updateByPrimaryKey(record);
        //删除旧关系
        mapper.deleteRolePermissionRelation(record.getId());
        //保存新关系
        //保存角色和权限的关系
        if(permissionIds!=null){
            for (Long permissionId : permissionIds) {
                mapper.saveRolePermissionRelation(record.getId(),permissionId);
            }
        }

        mapper.deleteRoleSystemMenuRelation(record.getId());
        //保存角色和菜单的关系
        if(permissionIds!=null){
            for (Long systemMenuId : systemMenuIds) {
                mapper.saveRoleSystemMenuRelation(record.getId(),systemMenuId);
            }
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return new PageResult(qo.getPageSize());
        }
        List<Role> listData = mapper.queryForList(qo);
        return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }
}
