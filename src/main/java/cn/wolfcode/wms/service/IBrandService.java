package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IBrandService {
    void deleteByPrimaryKey(Long id);

    void insert(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    void updateByPrimaryKey(Brand record);

    PageResult query(QueryObject qo);
}
