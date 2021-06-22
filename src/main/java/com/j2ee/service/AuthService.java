package com.j2ee.service;



import com.j2ee.db.domain.Admin;
import com.j2ee.db.domain.Student;
import com.j2ee.db.domain.Teacher;
import com.j2ee.db.domain.TeachingSecretary;
import com.j2ee.db.service.AdminService;
import com.j2ee.db.service.StudentService;
import com.j2ee.db.service.TeacherService;
import com.j2ee.db.service.TeachingSecretaryService;
import com.j2ee.dto.LoginType;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


/**
 * 登录判断用户名密码
 */
@Service
public class AuthService {


    @Autowired
    private StudentService studentService;


    @Autowired
    private TeacherService teacherService;


    @Autowired
    private TeachingSecretaryService secretaryService;


    @Autowired
    private AdminService adminService;


    public Object checkUser(LoginType type, String username, String pwd, HttpSession session){
        if (LoginType.STUDENT.equals(type)) {
            Student user = studentService.findByNumber(username);
            if (user == null) {
                return ResponseUtil.fail(-1, "用户不存在");
            }

            if (!user.getPassword().equals(pwd)) {
                return ResponseUtil.fail(-1, "密码错误");
            }
            this.setSession(user.getId(), LoginType.STUDENT, user.getName(), session);
            return ResponseUtil.ok("登录成功");




        }else if (LoginType.TEACHER.equals(type)) {
            Teacher user = teacherService.findByNumber(username);
            if (user == null) {
                return ResponseUtil.fail(-1, "用户不存在");
            }
            if (!user.getPwd().equals(pwd)) {
                return ResponseUtil.fail(-1, "密码错误");
            }
            this.setSession(user.getId(), LoginType.TEACHER, user.getName(), session);
            return ResponseUtil.ok("登录成功");




        }else if (LoginType.SECRETARY.equals(type)) {
            TeachingSecretary user = secretaryService.findByName(username);
            if (user == null) {
                return ResponseUtil.fail(-1, "用户不存在");
            }
            if (!user.getPwd().equals(pwd)) {
                return ResponseUtil.fail(-1, "密码错误");
            }
            this.setSession(user.getId(), LoginType.SECRETARY, user.getUsername(), session);
            return ResponseUtil.ok("登录成功");




        }else if (LoginType.ADMIN.equals(type)) {
            Admin user = adminService.findByName(username);
            if (user == null) {
                return ResponseUtil.fail(-1, "用户不存在");
            }
            if (!user.getPwd().equals(pwd)) {
                return ResponseUtil.fail(-1, "密码错误");
            }
            this.setSession(user.getId(), LoginType.ADMIN, user.getUsername(), session);
            return ResponseUtil.ok("登录成功");
        }
        return ResponseUtil.fail(-1, "请选择登录类型");
    }


    private void setSession(Integer uid, LoginType type, String username, HttpSession session) {
        session.setAttribute("uid", uid);
        session.setAttribute("type", type);
        session.setAttribute("username", username);
    }
}
