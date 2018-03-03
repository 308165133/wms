package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.PermissionUtils;
import cn.wolfcode.wms.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper mapper;
    @Autowired
    private ApplicationContext ctx;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Permission record) {
        mapper.insert(record);
    }


    @Override
    public List<Permission> selectAll() {
        return mapper.selectAll();
    }


    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return new PageResult(qo.getPageSize());
        }
        List<Permission> listData = mapper.queryForList(qo);
        return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void loadPermission() {
        //获取到表中所有的权限表达式
        List<String> allExpressions = mapper.getAllExpressions();

        //获取到容器中所有的Controller对象
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> controllerBeans = beansWithAnnotation.values();
        //获取到所有的方法
        for (Object controllerBean : controllerBeans) {
            Method[] methods = controllerBean.getClass().getMethods();
            for (Method method : methods) {
                //判断方法是否有RequiredPermission注解
                if (method.isAnnotationPresent(RequiredPermission.class)) {
                    //有,拼接权限表达式
                    String expression = PermissionUtils.getExpression(method);
                    //如果数据库中不存在该表达式,执行保存
                    if(!allExpressions.contains(expression)){
                        //获取到注解中传递的参数,作为权限的名称
                        RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                        String name = annotation.value();
                        //执行保存
                        Permission p = new Permission();
                        p.setExpression(expression);
                        p.setName(name);
                        mapper.insert(p);
                    }

                }
            }
        }


    }
}
