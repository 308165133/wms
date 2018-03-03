package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IProductService {
    void deleteByPrimaryKey(Long id);

    void insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    void updateByPrimaryKey(Product record);

    PageResult query(QueryObject qo);
}
