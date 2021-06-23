package com.j2ee.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;


@Configuration
@Controller
@RequestMapping()
public class FileCheckController {
    @RequestMapping("/checkFile")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取文件的绝对路径
        File directory = new File("D:\\");//设定为当前文件夹
        String parentDir = directory.getAbsolutePath();

        System.out.println("parentDir:" + parentDir);
        //获取输入流对象（用于读文件）
        FileInputStream fis = new FileInputStream(new File(parentDir, fileName));
        //获取文件后缀（.txt）
        String extendFileName = fileName.substring(fileName.lastIndexOf(".doc"));
        //动态设置响应类型，根据前台传递文件类型设置响应类型
        response.setContentType(request.getSession().getServletContext().getMimeType(extendFileName));
        //设置响应头,attachment表示以附件的形式下载，inline表示在线打开
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        //获取输出流对象（用于写文件）
        ServletOutputStream os = response.getOutputStream();
        //下载文件,使用spring框架中的FileCopyUtils工具
        FileCopyUtils.copy(fis, os);

    }
}