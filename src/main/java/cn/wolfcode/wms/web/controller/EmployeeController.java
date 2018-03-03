package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService service;
    @Autowired
    private IDepartmentService deptService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("list")
    @RequiredPermission("员工列表")
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo) {
        model.addAttribute("result", service.query(qo));
        //查询出所有的部门
        model.addAttribute("departments", deptService.selectAll());

        return "employee/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermission("员工删除")
    public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                service.deleteByPrimaryKey(id);
            }
        }catch(Exception e){
            e.printStackTrace();
            result.mark("删除失败");
        }
        return result;
    }
    @RequestMapping("batchDelete")
    @ResponseBody
    @RequiredPermission("员工批量删除")
    public JsonResult batchDelete(Long[] ids) {
        JsonResult result = new JsonResult();
        try {
            if (ids != null) {
                service.batchDelete(ids);
            }
        }catch(Exception e){
            e.printStackTrace();
            result.mark("删除失败");
        }
        return result;
    }

    @RequestMapping("input")
    @RequiredPermission("员工编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            Employee employee = service.selectByPrimaryKey(id);
            model.addAttribute("employee", employee);
        }
        //查询出所有的部门
        model.addAttribute("departments", deptService.selectAll());
        model.addAttribute("roles", roleService.selectAll());
        return "employee/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermission("员工保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Employee employee,Long[] roleIds) {
        JsonResult result = new JsonResult();
        try {
            if (employee.getId() != null) {
                service.updateByPrimaryKey(employee,roleIds);
            } else {
                service.insert(employee,roleIds);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.mark("保存失败");
        }

        return result;
    }
}
