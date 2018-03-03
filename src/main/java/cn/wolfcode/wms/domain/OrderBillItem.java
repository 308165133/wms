package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
public class OrderBillItem {
    private Long id;
    private BigDecimal costPrice;
    private BigDecimal number;
    private BigDecimal amount;
    private String remark;
    private Product product;
    //订单编号
    private Long billId;
}
