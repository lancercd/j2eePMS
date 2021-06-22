package com.j2ee.controller;


import com.j2ee.annotation.TeachingSecretaryLogin;
import com.j2ee.db.domain.News;
import com.j2ee.db.service.NewsService;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;


/**
 * 新闻控制器
 */
@Controller
@RequestMapping("/news")
public class NewsController {


    @Autowired
    private NewsService newsService;


    @GetMapping("/detail")
    public Object detail(Integer id, Model model){
        if(id == null || id == 0){
            return "redirect:index";
        }
        News news = newsService.findById(id);

        if(news == null){
            return "redirect:index";
        }


        model.addAttribute("item", news);

        return "news/viewDetail";
    }



//    @GetMapping("/setNews")
//    public String setNews(){
//        return "/setNews";
//    }


//    @ResponseBody
//    @PostMapping("/addOrUpdate")
//    public Object addOrUpdate(News news){
//        if(StringUtils.isEmpty(news.getTitle())){
//            return ResponseUtil.fail(400, "请填写标题");
//        }
//
//        if(StringUtils.isEmpty(news.getContent())){
//            return ResponseUtil.fail(400, "请填写内容");
//        }
//
//
//        if(news.getId() == null){
//            newsService.insert(news);
//        }
//        else {
//            newsService.update(news);
//        }
//
//        return ResponseUtil.ok("成功");
//    }
//
//    @GetMapping("/indexNews")
//    public String indexNews(){
//        return "/indexNews";
//    }
//
//    @GetMapping("/browseNews")
//    public Object browseNews(String title,String content){
//        List<News> news;
//
//        return "";
//    }








    @TeachingSecretaryLogin
    @GetMapping("/admin/list")
    public Object AdminNewsList(String title, Model model){

        List<News> lists;
        if(StringUtils.isEmpty(title)){
            model.addAttribute("title", "");
            lists = newsService.queryByIsActive(true);
        }else{
            model.addAttribute("title", title);
            lists = newsService.queryByTitleIsActive(title.trim());
        }

        for (News item : lists) {

            //去除掉html标签 后截取
            String content = ((String)item.getContent()).replaceAll("<[^>]+>", "");
            if(content.length() > 100){
                String text = content.substring(0, 100) + "...";
                item.setContent(text);
            }
        }

        model.addAttribute("lists", lists);
        return "news/newsAdminList";
    }



    @TeachingSecretaryLogin
    @PostMapping("/addOrUpdate")
    public Object addOrUpdateNews(News news){


        if(news.getId() == null){
            newsService.insert(news);
        }else{
            newsService.update(news);
        }

        return "redirect:/news/admin/list";
    }


    @TeachingSecretaryLogin
    @GetMapping("/add")
    public Object detail(Model model){

        return "news/add";
    }

    @TeachingSecretaryLogin
    @GetMapping("/edit/{id}")
    public Object edit(@PathVariable("id") Integer id, Model model){

        if(id == null || id == 0){
            return "redirect:/news/admin/list";
        }
        News news = newsService.findById(id);

        if(news == null){
            return "redirect:/news/admin/list";
        }

        model.addAttribute("item", news);

        return "news/edit";
    }


    @TeachingSecretaryLogin
    @GetMapping("/delete/{id}")
    public Object delete(@PathVariable("id") Integer id){
        newsService.delete(id);
        return "redirect:/news/admin/list";
    }
}
