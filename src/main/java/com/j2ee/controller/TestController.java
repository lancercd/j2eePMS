package com.j2ee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController {


    @RequestMapping("/test")
    public String index(){
        return "访问成功";
    }
    
}
