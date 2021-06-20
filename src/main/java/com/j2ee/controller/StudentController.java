package com.j2ee.controller;



import com.j2ee.annotation.LoginUid;
import com.j2ee.annotation.StudentLogin;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.j2ee.service.StudentTeacherChoiceService;


@Controller
@RequestMapping("student")
public class StudentController {


    @Autowired
    private StudentTeacherChoiceService studentTeacherChoiceService;



    /**
     * 学生选择导师页面
     * @return String
     */
    @StudentLogin
    @GetMapping("/selectAdviser")
    public String adviserList(Integer semesterId, Model model){


        model.addAttribute("semesters", studentTeacherChoiceService.getAllSemester());
        model.addAttribute("semesterId", semesterId);

        if (semesterId != null && semesterId != 0) {
            model.addAttribute("lists", studentTeacherChoiceService.getAllStuTeaChBySemester(semesterId));
        }else{
            model.addAttribute("lists", studentTeacherChoiceService.getAllStuTeaCh());
        }

        return "student/selectAdviser";
    }



    @StudentLogin
    @PostMapping("/adviser/add")
    public Object selectAdviser(@LoginUid Integer uid, Integer adviserId){


        return ResponseUtil.ok("uid:" + uid + "  adviserId:" + adviserId);
    }



}
