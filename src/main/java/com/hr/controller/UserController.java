package com.hr.controller;


import com.hr.po.R;
import com.hr.utils.VerifyCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lq
 * @date 2021-06-12 - 16:10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成验证码，6位
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        //验证码放入session
//        session.setAttribute("verifyCode", verifyCode);
        request.getServletContext().setAttribute("verifyCode", verifyCode);

        System.out.println(verifyCode);

        //验证码存入图片
        response.setContentType("image/png");

        ServletOutputStream outputStream = response.getOutputStream();

        VerifyCodeUtil.outputImage(100, 39, outputStream, verifyCode);
    }

    @GetMapping("/checkVerifyCode")
    public R checkVerifyCode(String code, HttpServletRequest request) {
        //获取存在前端的验证码
        String tmp = (String) request.getServletContext().getAttribute("verifyCode");
//        System.out.println("requset中获取的code："+tmp);
//        System.out.println("前端传过来的code"+code);
        if (tmp == null || tmp.length() == 0) {
            return R.fail();
        }
        //获取前端输入的验证码进行比对
        if (tmp.equalsIgnoreCase(code)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}
