package cn.wolfcode.wms.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.wolfcode.wms.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BaseBillQueryObject extends QueryObject {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	private int status = -1;

	// 获取当天的最后一秒
	public Date getEndDate() {
		if (endDate != null) {
			return DateUtil.getEndDate(endDate);
		}
		return null;
	}
}
