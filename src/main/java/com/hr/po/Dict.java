package com.hr.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lq
 * @date 2021-07-10 - 21:43
 *      数据字典类
 */
@Data
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private long id;

    @TableField("parent_id")
    private long parentId;

    private String name;

    private Integer value;

    @TableField("dict_code")
    private String dictCode;

    @TableField("create_Time")
    private LocalDateTime createTime;

    @TableField("update_Time")
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic  //逻辑删除注解
    private Boolean deleted;

    @TableField(exist = false) //当前字段并不存在物理表中  逻辑概念属性
    private Boolean hasChildren;

}
