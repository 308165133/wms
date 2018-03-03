package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IOrderBillService {
    void deleteByPrimaryKey(Long id);

    void insert(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    List<OrderBill> selectAll();

    void updateByPrimaryKey(OrderBill record);

    PageResult query(QueryObject qo);

    void audit(Long id);

}
