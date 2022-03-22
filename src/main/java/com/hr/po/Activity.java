package com.hr.po;

import lombok.Data;

import java.util.List;


/**
 * @author lq
 * @date 2021-06-08 - 10:20
 */
@Data
public class Activity {

    //活动编号
    private int id;

    //活动名称
    private String name;
    //活动区域
    private String region;

    private String date1;
    private String date2;
    //活动性质
    private List<String> arrytype;
    //特殊资源
    private String resource;
    //活动形式
    private String desc;

}
