package com.j2ee.controller;


import com.j2ee.db.domain.News;
import com.j2ee.db.service.NewsService;
import com.j2ee.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {


    @Autowired
    private NewsService newsService;

    @GetMapping("/index")
    public Object index(String title, Model model){
        List<News> lists;
        if(StringUtils.isEmpty(title)){
            model.addAttribute("title", "");
            lists = newsService.queryByIsActive(true);
        }else{
            model.addAttribute("title", title);
            lists = newsService.queryByTitleIsActive(title.trim());
        }

        model.addAttribute("lists", lists);

        return "index";
    }

}
