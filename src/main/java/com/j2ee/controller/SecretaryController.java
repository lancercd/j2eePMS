package com.j2ee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 关于教学秘书的controller
 */
@Controller
@RequestMapping("/secretary")
public class SecretaryController {

    @GetMapping("/setTeacher")
    public String setTeacher() {
        return "/setTeacher";
    }


    @GetMapping("/teachingSecretary")
    public String teachingSecretary(){
        return "/teachingSecretary";
    }
}
