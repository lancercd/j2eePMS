package com.j2ee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 权限管理
 *      登录等...
 *      JWT
 */
@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(){
        return "/auth/login";
    }

    @PostMapping("/login")
    public String loginApi(){


        return null;
    }
}
