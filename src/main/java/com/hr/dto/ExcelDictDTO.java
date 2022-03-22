package com.hr.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.sql.Timestamp;


/**
 * @author lq
 * @date 2021-07-10 - 21:34
 */
@Data
public class ExcelDictDTO {


    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("上级id")
    private Long parentId;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("值")
    private Integer value;

    @ExcelProperty("编码")
    private String dictCode;

//    @ExcelProperty("创建时间")
//    private Timestamp createTime;
//
//    @ExcelProperty("更新时间")
//    private Timestamp updateTime;
//
//    @ExcelProperty("逻辑删除")
//    private int deleted;



}
