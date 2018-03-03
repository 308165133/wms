package cn.wolfcode.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

//出库单
@Getter
@Setter
public class StockOutcomeBill extends BaseBillDomain {
	// 仓库
	private Depot depot;
	// 所属客户
	private Client client;
	// 出库单明细:一对多
	private List<StockOutcomeBillItem> items = new ArrayList<>();

}
