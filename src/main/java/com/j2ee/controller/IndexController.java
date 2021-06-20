package com.j2ee.web.controller;


import com.j2ee.core.utils.ResponseUtil;
import com.j2ee.db.dao.NewsMapper;
import com.j2ee.db.domain.News;
import com.j2ee.db.service.NewsService;
import com.j2ee.db.service.StudentService;
import com.j2ee.web.service.StudentTeacherChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    private StudentTeacherChoiceService STCS;

    @Autowired
    private StudentService studentService;

    /**
     * 戴林赵毅负责代码
     * 开始区域
     */
    @Autowired
    private NewsService newsService;

    @GetMapping("/setTeacher")
    public String setTeacher() {
        return "/setTeacher";
    }

    @GetMapping("/setNews")
    public String setNews(){
        return "/setNews";
    }

    @GetMapping("/teachingSecretary")
    public String teachingSecretary(){
        return "/teachingSecretary";
    }

    @PostMapping("/addOrUpdate")
    public Object addOrUpdate(News news){
        if(news.getId() == null){
            newsService.insert(news);
        }
        else {
            newsService.update(news);
        }
        return "/setNews";
    }

    /**
     * 戴林赵毅
     * 结束区域
     */



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

    @ResponseBody
    @GetMapping("/STC")
    public Object querySTC(){

        return ResponseUtil.ok(STCS.findDtoById(1));
    }

}
