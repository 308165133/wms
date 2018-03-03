package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
public class StockOutcomeBillItem {
    private Long id;
    private BigDecimal salePrice;//零售价
    private BigDecimal number;
    private BigDecimal amount;
    private String remark;
    private Product product;
    //出库单编号
    private Long billId;
}
