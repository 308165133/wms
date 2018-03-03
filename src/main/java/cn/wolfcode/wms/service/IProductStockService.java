package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

public interface IProductStockService {
	void stockIncome(StockIncomeBill bill);

	void stockOutcome(StockOutcomeBill bill);

	PageResult query(QueryObject qo);
}
