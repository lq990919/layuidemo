package com.hr.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.po.Item;
import com.hr.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lq
 * @date 2020-11-07 - 15:05
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @ResponseBody
    @RequestMapping("/list")
    public JSONObject find(@RequestParam("page") int pageNum, @RequestParam("limit") int pageSize){
        JSONObject result = new JSONObject();
        try {
            PageHelper.startPage(pageNum,pageSize);
            PageInfo<Item> pageInfo = new PageInfo<>(itemService.list(null));

            result.put("code",200);
            result.put("msg","操作成功");
            result.put("data", pageInfo.getList());
            result.put("count", pageInfo.getTotal());
        }catch (Exception e){
            result.put("code",500);
            result.put("msg","查询异常");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/find")
    public List<Item> result(){
        return itemService.list(null);
    }


}
