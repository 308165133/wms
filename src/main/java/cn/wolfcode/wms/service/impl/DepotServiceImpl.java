package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.mapper.DepotMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepotServiceImpl implements IDepotService {
    @Autowired
    private DepotMapper mapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Depot record) {
        mapper.insert(record);
    }

    @Override
    public Depot selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Depot> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Depot record) {
        mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return new PageResult(qo.getPageSize());
        }
        List<Depot> listData = mapper.queryForList(qo);
        return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }
}
