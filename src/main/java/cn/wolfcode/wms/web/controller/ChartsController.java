package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.query.OrderChartsQueryObject;
import cn.wolfcode.wms.query.SaleChartsQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IChartsService;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.utils.RequiredPermission;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("charts")
public class ChartsController {

    @Autowired
    private IChartsService service;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private IBrandService brandService;

    @RequestMapping("orderCharts")
    @RequiredPermission("订货报表")
    public String orderCharts(Model model, @ModelAttribute("qo") OrderChartsQueryObject qo) {
        model.addAttribute("orderCharts", service.selectOrderCharts(qo));
        model.addAttribute("suppliers", supplierService.selectAll());
        model.addAttribute("brands", brandService.selectAll());
        model.addAttribute("groupByTypes", OrderChartsQueryObject.groupByTypes);
        return "charts/orderCharts";
    }

    @RequestMapping("saleCharts")
    @RequiredPermission("销售报表")
    public String saleCharts(Model model, @ModelAttribute("qo") SaleChartsQueryObject qo) {
        model.addAttribute("saleCharts", service.selectSaleCharts(qo));
        model.addAttribute("clients", clientService.selectAll());
        model.addAttribute("brands", brandService.selectAll());
        model.addAttribute("groupByTypes", SaleChartsQueryObject.groupByTypes);
        return "charts/saleCharts";
    }

    @RequestMapping("saleChartsByBar")
    public String saleChartsByBar(Model model, @ModelAttribute("qo") SaleChartsQueryObject qo) {
        //获取分组的类型
        List<Map<String, Object>> saleCharts = service.selectSaleCharts(qo);
        List<String> groupTypes = new ArrayList<>();
        List<Object> saleAmount = new ArrayList<>();
        for (Map<String, Object> saleChart : saleCharts) {
            String groupType = saleChart.get("groupType").toString();
            groupTypes.add(groupType);
            Object totalAmount = saleChart.get("totalAmount");
            saleAmount.add(totalAmount);
        }

        System.out.println(JSON.toJSONString(groupTypes));
        //共享数据
        model.addAttribute("groupTypes", JSON.toJSONString(groupTypes));
        model.addAttribute("saleAmount", JSON.toJSONString(saleAmount));
        model.addAttribute("groupTypeName", SaleChartsQueryObject.groupByTypes.get(qo.getGroupByType()));
        return "charts/saleChartsByBar";
    }

    @RequestMapping("saleChartsByPie")
    public String saleChartsByPie(Model model, @ModelAttribute("qo") SaleChartsQueryObject qo) {
        model.addAttribute("groupTypeName", SaleChartsQueryObject.groupByTypes.get(qo.getGroupByType()));
        List<Map<String, Object>> saleCharts = service.selectSaleCharts(qo);
        List<String> groupTypes = new ArrayList<>();
        List<Map<String,Object>> groupByTypes = new ArrayList<>();
        BigDecimal maxSaleAmount = BigDecimal.ZERO;
        for (Map<String, Object> saleChart : saleCharts) {
            String groupType = saleChart.get("groupType").toString();
            groupTypes.add(groupType);

            Map<String, Object> groupByType = new HashMap<>();
            //封装需要的分组的数据
            groupByType.put("value",saleChart.get("totalAmount"));
            groupByType.put("name",saleChart.get("groupType"));
            groupByTypes.add(groupByType);

            //获取到最大的销售总额
            BigDecimal totalAmount = new BigDecimal(saleChart.get("totalAmount").toString());
            if(totalAmount.compareTo(maxSaleAmount)>0){
                maxSaleAmount=totalAmount;
            }
        }
        model.addAttribute("groupTypes", JSON.toJSONString(groupTypes));
        model.addAttribute("groupByTypes", JSON.toJSONString(groupByTypes));
        model.addAttribute("maxSaleAmount", maxSaleAmount);
        return "charts/saleChartsByPie";
    }
}
