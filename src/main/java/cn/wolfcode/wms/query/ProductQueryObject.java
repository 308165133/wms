package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductQueryObject extends QueryObject{
    private String keywords;
    private long brandId = -1L;

    public String getKeywords() {
        return empty2null(keywords);
    }
}
