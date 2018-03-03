package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.*;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.SaleAccountMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.utils.LogicException;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ProductStockServiceImpl implements IProductStockService {

    @Autowired
    private ProductStockMapper productStockMapper;
    @Autowired
    private SaleAccountMapper saleAccountMapper;

    @Override
    public void stockIncome(StockIncomeBill bill) {
        // 修改库存信息
        List<StockIncomeBillItem> items = bill.getItems();
        for (StockIncomeBillItem item : items) {
            // 判断当前明细中对应的商品是否存在
            Long depotId = bill.getDepot().getId();
            Long productId = item.getProduct().getId();
            ProductStock ps = productStockMapper.selectByProductIdAndDepotId(productId, depotId);

            if (ps == null) {
                // 不存在,保存一条新的数据
                ps = new ProductStock();
                ps.setPrice(item.getCostPrice());
                ps.setStoreNumber(item.getNumber());
                ps.setAmount(item.getNumber().multiply(item.getCostPrice()));
                ps.setDepot(bill.getDepot());
                ps.setProduct(item.getProduct());
                productStockMapper.insert(ps);
            } else {
                // 存在,修改当前的库存信息
                ps.setStoreNumber(ps.getStoreNumber().add(item.getNumber()));
                BigDecimal amount = ps.getAmount().add(item.getAmount());
                ps.setAmount(amount);
                BigDecimal price = ps.getAmount().divide(ps.getStoreNumber(), 2, BigDecimal.ROUND_HALF_UP);
                ps.setPrice(price);
                productStockMapper.updateByPrimaryKey(ps);
            }
        }
    }

    @Override
    public void stockOutcome(StockOutcomeBill bill) {
        // 查询库存信息
        List<StockOutcomeBillItem> items = bill.getItems();
        for (StockOutcomeBillItem item : items) {
            ProductStock ps = productStockMapper.selectByProductIdAndDepotId(item.getProduct().getId(),
                    bill.getDepot().getId());
            if (ps == null) {
                // 商品不存在
                throw new LogicException("亲,该商品[" + item.getProduct().getName() + "]不存在");
            }

            // 判断商品的数量是否足够
            // BigDecimal的大小的判断:compareTo
            // a.compareTo(b);
            // >0 a>b
            // =0 a=b
            // <0 a<b
            if (item.getNumber().compareTo(ps.getStoreNumber()) > 0) {
                // 数量不够
                throw new LogicException("亲,该商品[" + item.getProduct().getName() + "]的数量[" + ps.getStoreNumber() + "]不足["
                        + item.getNumber() + "]");
            }

            // 存在,数量够
            ps.setStoreNumber(ps.getStoreNumber().subtract(item.getNumber()));
            ps.setAmount(ps.getPrice().multiply(ps.getStoreNumber()));
            productStockMapper.updateByPrimaryKey(ps);


            //生成销售帐
            SaleAccount sa = new SaleAccount();
            sa.setClient(bill.getClient());
            sa.setCostPrice(ps.getPrice());
            sa.setNumber(item.getNumber());
            sa.setCostAmount(sa.getCostPrice().multiply(sa.getNumber()));
            sa.setProduct(item.getProduct());
            sa.setSalePrice(item.getSalePrice());
            sa.setSaleAmount(item.getAmount());
            sa.setVdate(new Date());
            sa.setSaleman(bill.getInputUser());
            //保存销售帐数据
            saleAccountMapper.insert(sa);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = productStockMapper.queryForCount(qo);
        if (totalCount == 0) {
            return new PageResult(qo.getPageSize());
        }
        List<ProductStock> listData = productStockMapper.queryForList(qo);
        return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }

}
