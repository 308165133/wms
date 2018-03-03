package cn.wolfcode.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

//订单
@Getter
@Setter
public class OrderBill extends BaseBillDomain {

	// 供应商
	private Supplier supplier;
	// 订单明细:一对多
	private List<OrderBillItem> items = new ArrayList<>();

}
