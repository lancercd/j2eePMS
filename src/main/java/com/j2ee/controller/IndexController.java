package com.j2ee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 首页控制器
 *      该控制器只有一个方法
 *                  index()
 *      TODO 其余方法请写在对应模块
 *      TODO 如果是测试  请随意  单后期请删除
 */
@Controller
public class IndexController {

    @GetMapping("/setTeacher")
    public String setTeacher() {

        return "setTeacher";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/setNews")
    public String setNews(){
        return "setNews";
    }

    @GetMapping("/teachingSecretary")
    public String teachingSecretary(){
        return "teachingSecretary";
    }


}
