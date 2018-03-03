package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;


public interface IClientService {
    void deleteByPrimaryKey(Long id);

    void insert(Client record);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    void updateByPrimaryKey(Client record);

    PageResult query(QueryObject qo);
}
