package cn.wolfcode.wms.test;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceImplTest {
    @Autowired
    private IDepartmentService service;
    @Test
    public void deleteByPrimaryKey() throws Exception {
        service.deleteByPrimaryKey(1L);
    }

    @Test
    public void insert() throws Exception {
        Department dept = new Department();
        dept.setName("研发部");
        dept.setSn("DEPT001");
        service.insert(dept);
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        Department dept = service.selectByPrimaryKey(1L);
        System.out.println(dept);
    }

    @Test
    public void selectAll() throws Exception {
        List<Department> depts = service.selectAll();
        for (Department dept : depts) {
            System.out.println(dept);
        }
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
        Department dept = new Department();
        dept.setId(1L);
        dept.setName("销售部");
        dept.setSn("DEPT002");
        service.updateByPrimaryKey(dept);
    }

}