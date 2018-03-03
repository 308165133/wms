package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IDepotService {
    void deleteByPrimaryKey(Long id);

    void insert(Depot record);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    void updateByPrimaryKey(Depot record);

    PageResult query(QueryObject qo);
}
