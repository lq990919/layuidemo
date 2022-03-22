package com.hr.controller;

import com.hr.po.R;
import com.hr.po.User;
import com.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lq
 * @date 2021-07-05 - 17:42
 */
@RestController
@RequestMapping("/info")
public class DemoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public R list(){

        List<User> list = userService.list();
//        User user = userService.find();
        R r = new R();
        r.setCode(20000);
        r.setMessage("成功");
        r.setSuccess(true);
        r.data("list",list);

        return r;
    }

}
