package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface ISupplierService {
    void deleteByPrimaryKey(Long id);

    void insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    void updateByPrimaryKey(Supplier record);

    PageResult query(QueryObject qo);
}
