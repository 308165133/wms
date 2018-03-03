package cn.wolfcode.wms.service;

import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.query.SaleChartsQueryObject;

import java.util.List;
import java.util.Map;


public interface IChartsService {
    List<Map<String, Object>> selectOrderCharts(QueryObject qo);

    List<Map<String, Object>> selectSaleCharts(SaleChartsQueryObject qo);
}
