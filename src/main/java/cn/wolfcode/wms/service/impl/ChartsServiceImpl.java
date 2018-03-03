package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.mapper.ChartsMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.query.SaleChartsQueryObject;
import cn.wolfcode.wms.service.IChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChartsServiceImpl implements IChartsService {
    @Autowired
    private ChartsMapper mapper;

    @Override
    public List<Map<String, Object>> selectOrderCharts(QueryObject qo) {
        return mapper.selectOrderCharts(qo);
    }

    @Override
    public List<Map<String, Object>> selectSaleCharts(SaleChartsQueryObject qo) {
        return mapper.selectSaleCharts(qo);
    }
}
