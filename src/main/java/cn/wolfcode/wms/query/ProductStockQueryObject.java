package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStockQueryObject extends QueryObject {
    private String keywords;
    private long brandId = -1L;
    private long depotId = -1L;
    //阈值:查询库存量小于该值的库存
    private Integer limitNumber;

    public String getKeywords() {
        return empty2null(keywords);
    }
}
