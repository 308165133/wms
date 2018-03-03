package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.query.SaleChartsQueryObject;

import java.util.List;
import java.util.Map;

public interface ChartsMapper {
    /**
     * 查询订货报表
     *
     * @param qo 查询条件
     * @return 查询的结果, 将每条数据封装到Map集合中
     */
    List<Map<String, Object>> selectOrderCharts(QueryObject qo);

    /**
     * 查询销售报表
     * @param qo 查询条件
     * @return 查询的结果, 将每条数据封装到Map集合中
     */
    List<Map<String, Object>> selectSaleCharts(SaleChartsQueryObject qo);
}