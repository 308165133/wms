package cn.wolfcode.wms.query;

import cn.wolfcode.wms.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 0024.
 */
@Getter
@Setter
public class OrderChartsQueryObject extends QueryObject {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private long supplierId = -1L;
    private int brandId = -1;

    //分组查询的类型,默认按照订货人员查询
    private String groupByType="inputUser.name";

    //封装页面中需要的分组条件
    //当该类被加载之后立即执行该集合的初始化
    public static Map<String,String> groupByTypes = new LinkedHashMap<>();
    static {
        groupByTypes.put("inputUser.name","订货人员");
        groupByTypes.put("p.name","商品名称");
        groupByTypes.put("s.name","供应商");
        groupByTypes.put("p.brand_name","品牌");
        groupByTypes.put("DATE_FORMAT(ob.vdate,'%Y-%m')","订货日期(月)");
        groupByTypes.put("DATE_FORMAT(ob.vdate,'%Y-%m-%d')","订货日期(日)");
    }



    // 获取当天的最后一秒
    public Date getEndDate() {
        if (endDate != null) {
            return DateUtil.getEndDate(endDate);
        }
        return null;
    }

}
