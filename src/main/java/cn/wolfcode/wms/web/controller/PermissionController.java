package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private IPermissionService service;

    @RequestMapping("list")
    public String list(Model model, QueryObject qo) {
        model.addAttribute("result", service.query(qo));
        return "permission/list";
    }

    @RequestMapping("delete")
    @ResponseBody
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
    @RequestMapping("loadPermission")
    @ResponseBody
    public JsonResult loadPermission() {
        JsonResult result = new JsonResult();
        try {
           service.loadPermission();
        }catch(Exception e){
            e.printStackTrace();
            result.mark("加载失败");
        }
        return result;
    }

}
