package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @RequestMapping("list")
    @RequiredPermission("部门列表")
    public String list(Model model, QueryObject qo) {
        model.addAttribute("result", service.query(qo));
        return "department/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermission("部门删除")
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

    @RequestMapping("input")
    @RequiredPermission("部门编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            Department department = service.selectByPrimaryKey(id);
            model.addAttribute("department", department);
        }
        return "department/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermission("部门保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Department department) {
        JsonResult result = new JsonResult();
        try {
            if (department.getId() != null) {
                service.updateByPrimaryKey(department);
            } else {
                service.insert(department);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.mark("保存失败");
        }

        return result;
    }
}
