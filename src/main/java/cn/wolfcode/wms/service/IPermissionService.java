package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IPermissionService {
    void deleteByPrimaryKey(Long id);

    void insert(Permission record);


    List<Permission> selectAll();

    PageResult query(QueryObject qo);

    /**
     * 加载权限
     */
    void loadPermission();
}
