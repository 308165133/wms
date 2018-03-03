package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SystemMenu {
    private Long id;

    private String name;

    private String url;

    private String sn;

    //关联父菜单的信息:多对一
    private SystemMenu parent;


}