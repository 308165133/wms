package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Role {
    private Long id;

    private String name;

    private String sn;

    //多对多
    private List<Permission> permissions = new ArrayList<>();
    //多对多
    private List<SystemMenu> systemMenus = new ArrayList<>();

}