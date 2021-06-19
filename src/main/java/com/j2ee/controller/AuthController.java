package com.j2ee.controller;


import com.j2ee.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;


/**
 * 权限管理
 *      登录等...
 *      JWT
 */
@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }


    @ResponseBody
    @PostMapping("/login")
    public Object loginApi(@NotNull String username, @NotNull String pwd, HttpSession session){

        String tmpUsername = "123";
        String tmpPwd = "123456";

        if(tmpUsername.equals(username)){
            if(tmpPwd.equals(pwd)){
                session.setAttribute("uid", 1);
                session.setAttribute("type", 1);
                session.setAttribute("username", username);
                return ResponseUtil.ok("登录成功");
            }
            return ResponseUtil.fail(-1, "密码错误");
        }


        return ResponseUtil.fail(-1, "用户名不存在");

    }
}
