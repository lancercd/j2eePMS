package com.j2ee.web.controller;


import com.j2ee.db.domain.News;
import com.j2ee.db.service.NewsService;
import com.j2ee.db.service.StudentService;
import com.j2ee.utils.ResponseUtil;
import com.j2ee.web.service.StudentTeacherChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

}
