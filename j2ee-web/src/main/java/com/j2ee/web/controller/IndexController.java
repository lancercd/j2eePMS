package com.j2ee.web.controller;


import com.j2ee.core.utils.ResponseUtil;
import com.j2ee.db.service.StudentService;
import com.j2ee.web.service.StudentTeacherChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private StudentService studentService;

<<<<<<< HEAD
    @GetMapping("/setTeacher")
    public String setTeacher(){

        return "setTeacher";
=======
    @Autowired
    private StudentTeacherChoiceService STCS;

    @ResponseBody
    @GetMapping("/index")
    public String index(){
        return "ok";
>>>>>>> 92b246029ca2d258b917918f240089b6050c9f1c
    }

    @GetMapping("/setNews")
    public String setNews(){
        return "setNews";
    }

    @GetMapping("/teachingSecretary")
    public String teachingSecretary(){
        return "teachingSecretary";
    }
    @ResponseBody
    @GetMapping("/query")
    public Object queryAll(){

        return ResponseUtil.ok(studentService.queryAll());
    }

    @ResponseBody
    @GetMapping("/STC")
    public Object querySTC(){

        return ResponseUtil.ok(STCS.findDtoById(1));
    }





}
