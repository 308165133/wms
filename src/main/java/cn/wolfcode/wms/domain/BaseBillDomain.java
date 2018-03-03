package cn.wolfcode.wms.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class BaseBillDomain extends BaseDomain{
	public static final int STATUS_NORMAL = 0;
	public static final int STATUS_AUDIT = 1;

	private String sn;
	// 业务发生的时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date vdate;

	// 0:待审核 1:已审核
	private int status = STATUS_NORMAL;
	private BigDecimal totalAmount;
	private BigDecimal totalNumber;
	private Date auditTime;
	private Date inputTime;
	// 录入人
	private Employee inputUser;
	// 审核人
	private Employee auditor;
}
