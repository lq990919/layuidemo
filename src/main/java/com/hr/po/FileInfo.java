package com.hr.po;

import lombok.Data;

import java.util.Date;

/**
 * @author lq
 * @date 2021-06-18 - 19:55
 * 文件信息类
 */
@Data
public class FileInfo {

    private int id;

    //原文件名
    private String originalFileName;

    //新
    private String realFileName;


    private Date createTime;

    private String ablatePath;
}
