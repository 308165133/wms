package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockOutcomeBillQueryObject extends BaseBillQueryObject {

	private long depotId = -1L;
	private long clientId = -1L;

}
