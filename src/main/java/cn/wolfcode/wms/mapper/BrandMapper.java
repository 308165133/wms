package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.query.QueryObject;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand record);

    int queryForCount(QueryObject qo);

    List<Brand> queryForList(QueryObject qo);
}