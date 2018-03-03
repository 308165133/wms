package cn.wolfcode.wms.domain;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Product {
    private Long id;

    private String name;

    private String sn;

    private BigDecimal costPrice;

    private BigDecimal salePrice;

    private String imagePath;

    private String intro;

    private Long brandId;

    private String brandName;

    //获取压缩图片的路径
    //imagePath:/upload/3c3af737-e22a-4e6a-ac67-70d89af6f241.jpg
    public String getSmallImagePath() {
        if (!StringUtils.isEmpty(imagePath)) {
            int index = imagePath.lastIndexOf(".");
            return imagePath.substring(0, index) + "_small"
                        + imagePath.substring(index);
        }
        return null;
    }

    //准备页面上需要的商品的JSON数据
    public String getProductData(){
        Map<String,Object> jsonObject = new HashMap<>();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("brandName",brandName);
        jsonObject.put("costPrice",costPrice);
        jsonObject.put("salePrice",salePrice);
        return JSON.toJSONString(jsonObject);
    }


}