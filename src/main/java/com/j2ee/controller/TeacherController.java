package com.j2ee.controller;


import com.j2ee.annotation.LoginUid;
import com.j2ee.annotation.TeacherLogin;
import com.j2ee.db.domain.*;
import com.j2ee.db.service.*;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    AppraiseTeacherService appraiseTeacherService;

    @Autowired
    SemesterService semesterService;

    @Autowired
    AdviserInfoService adviserInfoService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StuTeaChService stuTeaChService;

    @Autowired
    StudentService studentService;

    @Autowired
    DocumentTypeService documentTypeService;

    @ResponseBody
    @PostMapping("/agreeAppraiser")
    public Object agreeAppraiser(Integer appraiserId){
        AppraiseTeacher appraiseTeacher = new AppraiseTeacher();
        appraiseTeacher.setId(appraiserId);
        appraiseTeacher.setIsAccept((byte)1);
        int num = appraiseTeacherService.updateSelective(appraiseTeacher);
        if (num==0) return ResponseUtil.updatedDataFailed();
        return ResponseUtil.ok();
    }

    @ResponseBody
    @PostMapping("/disagreeAppraiser")
    public Object disagreeAppraiser(Integer appraiserId){
        AppraiseTeacher appraiseTeacher = new AppraiseTeacher();
        appraiseTeacher.setId(appraiserId);
        appraiseTeacher.setIsAccept((byte)-1);
        int num = appraiseTeacherService.updateSelective(appraiseTeacher);
        if (num==0) return ResponseUtil.updatedDataFailed();
        return ResponseUtil.ok();
    }


    @GetMapping("/guidanceAgree")
    public String guidanceAgree(Model md,Integer teacherId){//ht123
        teacherId = 1;
        Teacher teacher = teacherService.findById(teacherId);
        int semesterIdNow = semesterService.getSemesterIdNow();
        AdviserInfo adviserInfo = adviserInfoService.queryAdviserInfo(teacherId, semesterIdNow);
        List<Semester> semesters = semesterService.queryAll();
        md.addAttribute("semesters",semesters);
        md.addAttribute("teacher",teacher);
        md.addAttribute("adviserInfo",adviserInfo);
        return "/teacher/teacher_confirms";
    }

    @GetMapping("/guidanceSelect")
    public String guidanceSelect(Model md,Integer adviserId){//ht123
        adviserId = 2;
        Integer teacherId = 1;
        List<Semester> semesters = semesterService.queryAll();
        md.addAttribute("semesters",semesters);
        int semesterIdNow = semesterService.getSemesterIdNow();
        List<Integer> ids = stuTeaChService.queryStudentIdByAdviserId(adviserId,semesterIdNow);
        if (ids.size()==0) return "/teacher/teacher_select";
        List<Student> students = studentService.findById(ids);
        md.addAttribute("students",students);
        List<DocumentType> documentTypes = documentTypeService.queryAll();
        md.addAttribute("documentTypes",documentTypes);
        List<Teacher> teachers = teacherService.queryAppraiser(teacherId);
        md.addAttribute("adviserId",adviserId);

        List<Integer> list = new ArrayList<>();
        if (teachers.size()==0)  return "/teacher/teacher_select";

        for (Teacher temp:teachers){
            list.add(temp.getId());
        }
        md.addAttribute("appraiseTeachers",teacherService.findById(list));
        return "/teacher/teacher_select";
    }

//    @TeacherLogin
    @ResponseBody
    @PostMapping("/updateTeacherReq")
    public Object updateTeacherReq(String content,Integer teacherId){//ht123
        teacherId = 1;
        int semesterIdNow = semesterService.getSemesterIdNow();
        Integer res = adviserInfoService.updateReqInfo(teacherId, semesterIdNow, content);
        if (res==0) return ResponseUtil.updatedDataFailed();
        return ResponseUtil.ok();
    }
}
