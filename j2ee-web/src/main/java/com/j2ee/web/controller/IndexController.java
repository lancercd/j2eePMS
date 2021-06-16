package com.j2ee.web.controller;


import com.j2ee.core.utils.ResponseUtil;
import com.j2ee.db.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @GetMapping("/index")
    public String index(){
        return "ok";
    }

    @ResponseBody
    @GetMapping("/query")
    public Object queryAll(){

        return ResponseUtil.ok(studentService.queryAll());
    }



}
