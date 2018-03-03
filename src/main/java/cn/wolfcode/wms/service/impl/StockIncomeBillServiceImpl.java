package cn.wolfcode.wms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.domain.StockIncomeBillItem;
import cn.wolfcode.wms.mapper.StockIncomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockIncomeBillMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.service.IStockIncomeBillService;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.UserContext;

@Service
public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
    @Autowired
    private StockIncomeBillMapper mapper;
    @Autowired
    private StockIncomeBillItemMapper stockIncomeBillItemMapper;
    @Autowired
    private IProductStockService productStockService;

    @Override
    public void deleteByPrimaryKey(Long id) {
        //删除当前入库中所有的明细
        stockIncomeBillItemMapper.deleteByBillId(id);
        //删除入库单
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(StockIncomeBill record) {
        //封装单据相关的数据
        record.setStatus(StockIncomeBill.STATUS_NORMAL);
        record.setInputUser(UserContext.getCurrentEmp());
        record.setInputTime(new Date());
        //计算总额和总数量
        List<StockIncomeBillItem> items = record.getItems();
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (StockIncomeBillItem item : items) {
            totalNumber = totalNumber.add(item.getNumber());
            totalAmount = totalAmount.add(item.getNumber().multiply(item.getCostPrice()));
        }

        //设置总额:保留2位小数
        record.setTotalAmount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
        record.setTotalNumber(totalNumber);

        mapper.insert(record);

        //保存明细
        for (StockIncomeBillItem item : items) {
            item.setBillId(record.getId());
            item.setAmount(item.getCostPrice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
            stockIncomeBillItemMapper.insert(item);
        }
    }

    @Override
    public StockIncomeBill selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockIncomeBill> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(StockIncomeBill record) {
        //如果单据没有审核,可以执行更新
        StockIncomeBill stockIncomeBill = mapper.selectByPrimaryKey(record.getId());
        if (stockIncomeBill.getStatus() == StockIncomeBill.STATUS_NORMAL) {
            //删除当前入库单的明细
            stockIncomeBillItemMapper.deleteByBillId(record.getId());
            //保存明细
            //重新计算明细的总额和总数量
            //计算总数量和总额
            List<StockIncomeBillItem> items = record.getItems();
            //总数量
            BigDecimal totalNumber = BigDecimal.ZERO;
            //总额
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (StockIncomeBillItem item : items) {
                totalNumber = totalNumber.add(item.getNumber());
                totalAmount = totalAmount.add(item.getNumber().multiply(item.getCostPrice()));

                //保存明细
                //设值当前明细所属的订单编号
                item.setBillId(record.getId());
                //计算当前明细的小计
                item.setAmount(item.getNumber().multiply(item.getCostPrice()));
                //保存
                stockIncomeBillItemMapper.insert(item);
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
        List<StockIncomeBill> listData = mapper.queryForList(qo);
        return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void audit(Long id) {
        StockIncomeBill bill = mapper.selectByPrimaryKey(id);
        //如果该订单没有审核,执行审核操作
        if (bill.getStatus() == StockIncomeBill.STATUS_NORMAL) {
            bill.setStatus(StockIncomeBill.STATUS_AUDIT);
            bill.setAuditor(UserContext.getCurrentEmp());
            bill.setAuditTime(new Date());
            mapper.audit(bill);

            productStockService.stockIncome(bill);
        }
    }
}
