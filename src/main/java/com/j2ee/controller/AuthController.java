package com.j2ee.controller;


import com.j2ee.annotation.*;
import com.j2ee.dto.LoginType;
import com.j2ee.dto.UserLoginInfo;
import com.j2ee.service.AuthService;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;


/**
 * 权限管理
 *      登录等...
 *      JWT
 */
@Controller
public class AuthController {


    @Autowired
    private AuthService authService;



    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }


    /**
     * 学生登录
     * @return login
     */
    @GetMapping("/login/student")
    public String loginStudent(Model model){
        model.addAttribute("type", LoginType.STUDENT);
        return "auth/login";
    }


    /**
     * 老师登录
     * @return login
     */
    @GetMapping("/login/teacher")
    public String loginTeacher(Model model){
        model.addAttribute("type", LoginType.TEACHER);
        return "auth/login";
    }


    /**
     * 教学秘书登录
     * @return login
     */
    @GetMapping("/login/secretary")
    public String loginSecretary(Model model){
        model.addAttribute("type", LoginType.SECRETARY);
        return "auth/login";
    }


    /**
     * 管理员登录
     * @return login
     */
    @GetMapping("/login/admin")
    public String loginAdmin(Model model){
        model.addAttribute("type", LoginType.ADMIN);
        return "auth/login";
    }


    @ResponseBody
    @PostMapping("/login")
    public Object loginApi(LoginType type, @NotNull String username, @NotNull String pwd, HttpSession session){

        return authService.checkUser(type, username, pwd, session);
    }


    /**
     * 登录注解测试
     *        参数解析测试
     *        完成
     * @param uid       uid参数解析测试
     * @param userInfo  userInfo参数解析测试成功
     * @return String
     */
//    @StudentLogin
    @Login(type = {LoginType.ADMIN})
    @RequestMapping("/auth/test")
    public String authTest(@LoginUid Integer uid, @LoginInfo UserLoginInfo userInfo){
        System.out.println("---------------------");
        System.out.println(uid);
        System.out.println(userInfo);
        System.out.println("---------------------");



        return "setNews";
    }
}
