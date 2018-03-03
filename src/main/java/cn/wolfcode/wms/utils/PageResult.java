package cn.wolfcode.wms.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class PageResult {
    private List<?> listData;
    private Integer totalCount;

    private Integer currentPage;
    private Integer pageSize;

    private Integer prevPage;
    private Integer nextPage;
    private Integer totalPage;

    public PageResult(List<?> listData, Integer totalCount, Integer currentPage, Integer pageSize) {
        this.listData = listData;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
        this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
        this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;
        if (currentPage > totalPage) {
            totalPage = 1;
        }
    }

    //当总条数为0的时候,使用该构造器创建一个默认的结果集对象
    public PageResult(Integer pageSize) {
        this(Collections.EMPTY_LIST, 0, 1, pageSize);
    }


}
