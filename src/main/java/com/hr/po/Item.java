package com.hr.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author lq
 * @date 2020-10-25 - 10:28
 *
 * 商品信息表
 */
@Data
public class Item {

    @TableId
    private int itemId;
    private String itemName;
    private String itemImage;
    private Float itemPrice;
    private String itemType;
    private int itemNumber;
    private Date createTime;
    private int flag;
}
