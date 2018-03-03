package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper mapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Brand record) {
        mapper.insert(record);
    }

    @Override
    public Brand selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Brand> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Brand record) {
        mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return new PageResult(qo.getPageSize());
        }
        List<Brand> listData = mapper.queryForList(qo);
        return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }
}
