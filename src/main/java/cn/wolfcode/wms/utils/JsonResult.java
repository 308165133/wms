package cn.wolfcode.wms.utils;

import lombok.Getter;

@Getter
public class JsonResult {
    private boolean success=true;
    private String message;

    //当出错的时候使用该方法封装JsonResult对象
    public void mark(String message){
        this.success=false;
        this.message=message;
    }
}
