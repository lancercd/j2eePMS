package com.j2ee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student")
public class StudentController {

    /**
     * 学生选择导师页面
     * @return String
     */
    @GetMapping("/selectAdviser")
    public String addTeacher(){
        return "student/selectAdviser";
    }



}
