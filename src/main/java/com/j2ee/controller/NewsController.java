package com.j2ee.controller;


import com.j2ee.db.domain.News;
import com.j2ee.db.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 新闻控制器
 */
@Controller
public class NewsController {


    @Autowired
    private NewsService newsService;



    @GetMapping("/setNews")
    public String setNews(){
        return "/setNews";
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


}
