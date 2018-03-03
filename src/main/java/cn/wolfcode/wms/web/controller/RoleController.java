package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService service;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private ISystemMenuService systemMenuService;

    @RequestMapping("list")
    @RequiredPermission("角色列表")
    public String list(Model model, QueryObject qo) {
        model.addAttribute("result", service.query(qo));
        return "role/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermission("角色删除")
    public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                service.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败");
        }
        return result;
    }

    @RequestMapping("input")
    @RequiredPermission("角色编辑")
    public String input(Long id, Model model) {
        //查询出所有的权限
        model.addAttribute("permissions", permissionService.selectAll());
        //查询出所有的菜单
        model.addAttribute("systemMenus", systemMenuService.selectAll());
        if (id != null) {
            Role role = service.selectByPrimaryKey(id);
            model.addAttribute("role", role);
        }
        return "role/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermission("角色保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Role role, Long[] permissionIds,Long[] systemMenuIds) {
        JsonResult result = new JsonResult();
        try {
            if (role.getId() != null) {
                service.updateByPrimaryKey(role, permissionIds,systemMenuIds);
            } else {
                service.insert(role, permissionIds,systemMenuIds);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.mark("保存失败");
        }

        return result;
    }
}
