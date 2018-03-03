package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IRoleService {
    void deleteByPrimaryKey(Long id);

    void insert(Role record,Long[] permissionIds,Long[] systemMenuIds);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    void updateByPrimaryKey(Role record,Long[] permissionIds,Long[] systemMenuIds);

    PageResult query(QueryObject qo);
}
