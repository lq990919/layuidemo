package com.hr.controller;

import com.hr.po.Activity;
import com.hr.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lq
 * @date 2021-06-10 - 11:46
 */
@Controller
@RequestMapping("/act")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/add")
    public String add(Activity act){

        System.out.println(act);
        try {
            activityService.save(act);
            return  "scuess";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }
     return "";
    }


}
