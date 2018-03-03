package cn.wolfcode.wms.domain;


import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

//入库单
@Getter
@Setter
public class StockIncomeBill extends BaseBillDomain{
    //仓库
    private Depot depot;
    //入库单明细:一对多
    private List<StockIncomeBillItem> items = new ArrayList<>();

}
