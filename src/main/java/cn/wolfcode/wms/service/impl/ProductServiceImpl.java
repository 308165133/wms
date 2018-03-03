package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.mapper.ProductMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductService;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper mapper;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Product record) {
        //查询品牌的名称
        Brand brand = brandMapper.selectByPrimaryKey(record.getBrandId());
        record.setBrandName(brand.getName());
        mapper.insert(record);
    }

    @Override
    public Product selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Product record) {
        //查询品牌的名称
        Brand brand = brandMapper.selectByPrimaryKey(record.getBrandId());
        record.setBrandName(brand.getName());
        mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return new PageResult(qo.getPageSize());
        }
        List<Product> listData = mapper.queryForList(qo);
        return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }
}
