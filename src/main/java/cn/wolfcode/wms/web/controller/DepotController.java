package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("depot")
public class DepotController {

    @Autowired
    private IDepotService service;

    @RequestMapping("list")
    @RequiredPermission("仓库列表")
    public String list(Model model, QueryObject qo) {
        model.addAttribute("result", service.query(qo));
        return "depot/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermission("仓库删除")
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
    @RequiredPermission("仓库编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            Depot depot = service.selectByPrimaryKey(id);
            model.addAttribute("depot", depot);
        }
        return "depot/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermission("仓库保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Depot depot) {
        JsonResult result = new JsonResult();
        try {
            if (depot.getId() != null) {
                service.updateByPrimaryKey(depot);
            } else {
                service.insert(depot);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.mark("保存失败");
        }

        return result;
    }
}
