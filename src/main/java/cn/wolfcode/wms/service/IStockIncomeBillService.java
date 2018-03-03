package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IStockIncomeBillService {
    void deleteByPrimaryKey(Long id);

    void insert(StockIncomeBill record);

    StockIncomeBill selectByPrimaryKey(Long id);

    List<StockIncomeBill> selectAll();

    void updateByPrimaryKey(StockIncomeBill record);

    PageResult query(QueryObject qo);

    void audit(Long id);

}
