package com.j2ee.controller;


import com.j2ee.db.domain.News;
import com.j2ee.db.service.NewsService;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;


/**
 * 新闻控制器
 */
@Controller
@RequestMapping("/news")
public class NewsController {


    @Autowired
    private NewsService newsService;



    @GetMapping("/setNews")
    public String setNews(){
        return "/setNews";
    }



    @ResponseBody
    @PostMapping("/addOrUpdate")
    public Object addOrUpdate(News news){
        if(StringUtils.isEmpty(news.getTitle())){
            return ResponseUtil.fail(400, "请填写标题");
        }

        if(StringUtils.isEmpty(news.getContent())){
            return ResponseUtil.fail(400, "请填写内容");
        }


        if(news.getId() == null){
            newsService.insert(news);
        }
        else {
            newsService.update(news);
        }

        return ResponseUtil.ok("成功");
    }


}
