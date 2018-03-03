package cn.wolfcode.wms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.domain.StockOutcomeBillItem;
import cn.wolfcode.wms.mapper.StockOutcomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockOutcomeBillMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.service.IStockOutcomeBillService;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.UserContext;

@Service
public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
	@Autowired
	private StockOutcomeBillMapper mapper;
	@Autowired
	private StockOutcomeBillItemMapper stockOutcomeBillItemMapper;
	@Autowired
	private IProductStockService productStockService;

	@Override
	public void deleteByPrimaryKey(Long id) {
		// 删除当前出库中所有的明细
		stockOutcomeBillItemMapper.deleteByBillId(id);
		// 删除
		// 出库单
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(StockOutcomeBill record) {
		// 封装单据相关的数据
		record.setStatus(StockOutcomeBill.STATUS_NORMAL);
		record.setInputUser(UserContext.getCurrentEmp());
		record.setInputTime(new Date());
		// 计算总额和总数量
		List<StockOutcomeBillItem> items = record.getItems();
		BigDecimal totalNumber = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;

		for (StockOutcomeBillItem item : items) {
			totalNumber = totalNumber.add(item.getNumber());
			totalAmount = totalAmount.add(item.getNumber().multiply(item.getSalePrice()));
		}

		// 设置总额:保留2位小数
		record.setTotalAmount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		record.setTotalNumber(totalNumber);

		mapper.insert(record);

		// 保存明细
		for (StockOutcomeBillItem item : items) {
			item.setBillId(record.getId());
			item.setAmount(item.getSalePrice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
			stockOutcomeBillItemMapper.insert(item);
		}
	}

	@Override
	public StockOutcomeBill selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<StockOutcomeBill> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(StockOutcomeBill record) {
		// 如果单据没有审核,可以执行更新
		StockOutcomeBill stockOutcomeBill = mapper.selectByPrimaryKey(record.getId());
		if (stockOutcomeBill.getStatus() == StockOutcomeBill.STATUS_NORMAL) {
			// 删除当前入库单的明细
			stockOutcomeBillItemMapper.deleteByBillId(record.getId());
			// 保存明细
			// 重新计算明细的总额和总数量
			// 计算总数量和总额
			List<StockOutcomeBillItem> items = record.getItems();
			// 总数量
			BigDecimal totalNumber = BigDecimal.ZERO;
			// 总额
			BigDecimal totalAmount = BigDecimal.ZERO;
			for (StockOutcomeBillItem item : items) {
				totalNumber = totalNumber.add(item.getNumber());
				totalAmount = totalAmount.add(item.getNumber().multiply(item.getSalePrice()));

				// 保存明细
				// 设值当前明细所属的订单编号
				item.setBillId(record.getId());
				// 计算当前明细的小计
				item.setAmount(item.getNumber().multiply(item.getSalePrice()));
				// 保存
				stockOutcomeBillItemMapper.insert(item);
			}
			record.setTotalNumber(totalNumber);
			record.setTotalAmount(totalAmount);
			mapper.updateByPrimaryKey(record);
		}
	}

	@Override
	public PageResult query(QueryObject qo) {
		int totalCount = mapper.queryForCount(qo);
		if (totalCount == 0) {
			return new PageResult(qo.getPageSize());
		}
		List<StockOutcomeBill> listData = mapper.queryForList(qo);
		return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public void audit(Long id) {
		StockOutcomeBill bill = mapper.selectByPrimaryKey(id);
		if (bill.getStatus() == StockOutcomeBill.STATUS_NORMAL) {
			bill.setStatus(StockOutcomeBill.STATUS_AUDIT);
			bill.setAuditor(UserContext.getCurrentEmp());
			bill.setAuditTime(new Date());
			mapper.audit(bill);

			// 修改库存信息
			productStockService.stockOutcome(bill);
		}
	}
}
