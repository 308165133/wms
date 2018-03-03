package cn.wolfcode.wms.query;

import com.alibaba.druid.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {
    private Integer currentPage = 1;
    private Integer pageSize = 5;

    //获取到分页的开始索引
    public int getStart() {
        return (currentPage - 1) * pageSize;
    }

    protected String empty2null(String str) {
        return StringUtils.isEmpty(str) ? null : str;
    }
}