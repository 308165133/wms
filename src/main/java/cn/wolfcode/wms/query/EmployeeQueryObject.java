package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 0024.
 */
@Getter
@Setter
public class EmployeeQueryObject extends QueryObject {
    private String keywords;
    private long deptId = -1;


    public String getKeywords(){
        return empty2null(keywords);
    }
}
