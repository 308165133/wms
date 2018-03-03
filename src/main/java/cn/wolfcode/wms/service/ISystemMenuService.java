package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;
import java.util.Map;


public interface ISystemMenuService {
    void deleteByPrimaryKey(Long id);

    void insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    void updateByPrimaryKey(SystemMenu record);

    PageResult query(QueryObject qo);

    /**
     * 加载系统菜单
     * @param parentSn 对应的父菜单的编码
     * @return
     */
    List<Map<String,Object>> loadMenByParentSn(String parentSn);
}
