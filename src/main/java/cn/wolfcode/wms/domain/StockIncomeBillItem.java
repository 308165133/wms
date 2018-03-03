package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
public class StockIncomeBillItem extends BaseDomain{
    private BigDecimal costPrice;
    private BigDecimal number;
    private BigDecimal amount;
    private String remark;
    private Product product;
    //入库单编号
    private Long billId;
}
