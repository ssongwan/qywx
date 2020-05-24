package com.test.qywx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String studentnum; //学号

    private String userid;

    private String name;

    private String mobile;

    private int gender;

    private String email;

    private int status; //1 已激活   2 已禁用 4 未激活 5退出企业

    private String dormnum; //寝室楼号

    private String errcode;

}
