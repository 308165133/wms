package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.domain.OrderBillItem;
import cn.wolfcode.wms.mapper.OrderBillItemMapper;
import cn.wolfcode.wms.mapper.OrderBillMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IOrderBillService;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderBillServiceImpl implements IOrderBillService {
    @Autowired
    private OrderBillMapper mapper;
    @Autowired
    private OrderBillItemMapper orderBillItemMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        //删除当前订单中所有的明细
        orderBillItemMapper.deleteByBillId(id);
        //删除订单
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(OrderBill record) {
        //封装订单的信息
        //状态值
        record.setStatus(OrderBill.STATUS_NORMAL);
        //录入时间
        record.setInputTime(new Date());
        //录入人
        Employee currentEmp = UserContext.getCurrentEmp();
        record.setInputUser(currentEmp);

        //计算总数量和总额
        List<OrderBillItem> items = record.getItems();
        //总数量
        BigDecimal totalNumber = BigDecimal.ZERO;
        //总额
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderBillItem item : items) {
            totalNumber = totalNumber.add(item.getNumber());
            totalAmount = totalAmount.add(item.getNumber().multiply(item.getCostPrice()));
        }

        record.setTotalNumber(totalNumber);
        record.setTotalAmount(totalAmount);

        mapper.insert(record);

        //保存明细
        for (OrderBillItem item : items) {
            //设值当前明细所属的订单编号
            item.setBillId(record.getId());
            //计算当前明细的小计
            item.setAmount(item.getNumber().multiply(item.getCostPrice()));
            //保存
            orderBillItemMapper.insert(item);
        }
    }

    @Override
    public OrderBill selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderBill> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(OrderBill record) {
        //删除当前订单的明细
        orderBillItemMapper.deleteByBillId(record.getId());

        //保存明细

        //重新计算明细的总额和总数量
        //计算总数量和总额
        List<OrderBillItem> items = record.getItems();
        //总数量
        BigDecimal totalNumber = BigDecimal.ZERO;
        //总额
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderBillItem item : items) {
            totalNumber = totalNumber.add(item.getNumber());
            totalAmount = totalAmount.add(item.getNumber().multiply(item.getCostPrice()));

            //保存明细
            //设值当前明细所属的订单编号
            item.setBillId(record.getId());
            //计算当前明细的小计
            item.setAmount(item.getNumber().multiply(item.getCostPrice()));
            //保存
            orderBillItemMapper.insert(item);
        }
        record.setTotalNumber(totalNumber);
        record.setTotalAmount(totalAmount);
        mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return new PageResult(qo.getPageSize());
        }
        List<OrderBill> listData = mapper.queryForList(qo);
        return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void audit(Long id) {
        OrderBill bill = mapper.selectByPrimaryKey(id);
        //如果该订单没有审核,执行审核操作
        if(bill.getStatus()==OrderBill.STATUS_NORMAL){
            bill.setStatus(OrderBill.STATUS_AUDIT);
            bill.setAuditor(UserContext.getCurrentEmp());
            bill.setAuditTime(new Date());
            mapper.audit(bill);
        }
    }
}
