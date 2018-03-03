package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.ProductQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IProductService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermission;
import cn.wolfcode.wms.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService service;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private ServletContext application;

    @RequestMapping("list")
    @RequiredPermission("商品列表")
    public String list(Model model, @ModelAttribute("qo") ProductQueryObject qo) {
        model.addAttribute("result", service.query(qo));
        model.addAttribute("brands", brandService.selectAll());
        return "product/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermission("商品删除")
    public JsonResult delete(Long id, String imagePath) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                service.deleteByPrimaryKey(id);
                //删除图片
                UploadUtil.deleteFile(application, imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败");
        }
        return result;
    }

    @RequestMapping("input")
    @RequiredPermission("商品编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            Product product = service.selectByPrimaryKey(id);
            model.addAttribute("product", product);
        }
        //查询所有的品牌
        model.addAttribute("brands", brandService.selectAll());
        return "product/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermission("商品保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Product product, MultipartFile pic) {

        //如果在编辑的时候,用户修改了图片,将之前的图片删除
        if (pic != null && StringUtils.hasLength(product.getImagePath())) {
            //删除图片
            UploadUtil.deleteFile(application, product.getImagePath());
        }

        if (pic != null) {
            //将上传文件保存到/upload文件夹中
            String realPath = application.getRealPath("/upload");
            String imagePath = UploadUtil.upload(pic, realPath);
            product.setImagePath(imagePath);
        }

        JsonResult result = new JsonResult();
        try {
            if (product.getId() != null) {
                service.updateByPrimaryKey(product);
            } else {
                service.insert(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("保存失败");
        }

        return result;
    }

    @RequestMapping("selectProducts")
    public String selectProducts(Model model, @ModelAttribute("qo") ProductQueryObject qo) {
        model.addAttribute("result", service.query(qo));
        model.addAttribute("brands", brandService.selectAll());
        return "product/selectProductList";
    }
}
