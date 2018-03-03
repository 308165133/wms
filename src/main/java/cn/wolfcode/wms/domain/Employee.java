package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Employee {
    private Long id;

    private String name;

    private String password;

    private String email;

    private Integer age;

    private boolean admin=false;

    //多对一
    private Department department;

    //多对多
    private List<Role> roles = new ArrayList<>();


}