package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

//销售帐
@Getter@Setter
public class SaleAccount extends BaseDomain{
   
    private Date vdate;
    private BigDecimal number;
    private BigDecimal costPrice;
    private BigDecimal costAmount;
    private BigDecimal salePrice;
    private BigDecimal saleAmount;
    private Product product;
    private Employee saleman;
    private Client client;


}