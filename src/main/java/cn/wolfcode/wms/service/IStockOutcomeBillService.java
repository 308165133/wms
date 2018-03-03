package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IStockOutcomeBillService {
    void deleteByPrimaryKey(Long id);

    void insert(StockOutcomeBill record);

    StockOutcomeBill selectByPrimaryKey(Long id);

    List<StockOutcomeBill> selectAll();

    void updateByPrimaryKey(StockOutcomeBill record);

    PageResult query(QueryObject qo);

    void audit(Long id);

}
