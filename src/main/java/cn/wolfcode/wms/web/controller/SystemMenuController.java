package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.SystemMenuQueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("systemMenu")
public class SystemMenuController {

    @Autowired
    private ISystemMenuService service;

    @RequestMapping("list")
    @RequiredPermission("系统菜单列表")
    public String list(Model model, @ModelAttribute("qo") SystemMenuQueryObject qo) {
        model.addAttribute("result", service.query(qo));

        //获取到所有的父级菜单,共享到页面
        List<Map<String, Object>> parents = new ArrayList<>();
        //获取到当前菜单所在的父级菜单
        SystemMenu parent = service.selectByPrimaryKey(qo.getParentId());
        while (parent != null) {
            //将页面上需要的id/name封装起来
            Map<String, Object> parentMap = new HashMap<>();
            parentMap.put("id", parent.getId());
            parentMap.put("name", parent.getName());
            parents.add(parentMap);
            //触发延迟加载,执行额外SQL去查询父级菜单
            parent = parent.getParent();
        }

        //将集合中的元素颠倒顺序
        Collections.reverse(parents);

        model.addAttribute("parents", parents);

        return "systemMenu/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermission("系统菜单删除")
    public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                service.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("请先干掉你的所有后代,然后再自杀");
        }
        return result;
    }

    @RequestMapping("input")
    @RequiredPermission("系统菜单编辑")
    public String input(Long id, Model model, Long parentId) {
        if (parentId == null) {
            //如果没有传值,说明是在一级菜单,设定默认值
            model.addAttribute("parentId", null);
            model.addAttribute("parentName", "根目录");
        } else {
            SystemMenu parent = service.selectByPrimaryKey(parentId);
            model.addAttribute("parentId", parent.getId());
            model.addAttribute("parentName", parent.getName());
        }
        if (id != null) {
            SystemMenu systemMenu = service.selectByPrimaryKey(id);
            model.addAttribute("systemMenu", systemMenu);
        }
        return "systemMenu/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermission("系统菜单保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(SystemMenu systemMenu) {
        JsonResult result = new JsonResult();
        try {
            if (systemMenu.getId() != null) {
                service.updateByPrimaryKey(systemMenu);
            } else {
                service.insert(systemMenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("保存失败");
        }

        return result;
    }

    @RequestMapping("loadMenByParentSn")
    @ResponseBody
    public List<Map<String, Object>>  loadMenByParentSn(String parentSn) {
        List<Map<String, Object>> map = service.loadMenByParentSn(parentSn);
        return map;
    }
}
