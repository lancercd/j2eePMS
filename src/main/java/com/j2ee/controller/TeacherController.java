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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @TeacherLogin
    @GetMapping("/guidanceAgree")
    public String guidanceAgree(Model md,@LoginUid Integer teacherId){//指导教师确认页面
        Teacher teacher = teacherService.findById(teacherId);
        int semesterIdNow = semesterService.getSemesterIdNow();
        AdviserInfo adviserInfo = adviserInfoService.queryAdviserInfo(teacherId, semesterIdNow);
        Semester semester = semesterService.findById(semesterIdNow);
        md.addAttribute("semester",semester);
        md.addAttribute("teacher",teacher);
        if (adviserInfo==null) return "redirect:/teacher/error";
        md.addAttribute("adviserInfo",adviserInfo);
        return "/teacher/teacher_confirms";
    }

    @ResponseBody
    @GetMapping("/error")
    public Object error(){
        return "<script>alert('您未被教学秘书指派为指导教师');window.history.go(-1)</script>";
    }


    @TeacherLogin
    @ResponseBody
    @PostMapping("/updateTeacherReq")
    public Object updateTeacherReq(String content,@LoginUid Integer teacherId){//ht123
        int semesterIdNow = semesterService.getSemesterIdNow();
        Integer res = adviserInfoService.updateReqInfo(teacherId, semesterIdNow, content);
        if (res==0) return ResponseUtil.updatedDataFailed();
        return ResponseUtil.ok();
    }

    @GetMapping("/guidanceInfo") //指导教师确认信息页面
    public String guidanceInfo(Model md,Integer semesterId){
        if (semesterId==null) semesterId = semesterService.getSemesterIdNow();
        List<Semester> semesters = semesterService.queryAll();
        md.addAttribute("semesters",semesters);
        List<AdviserInfo> adviserInfos = adviserInfoService.queryBySemesterId(semesterId);
        List<Map<String,Object>> list = new ArrayList<>();
        System.out.println(adviserInfos);
        for (AdviserInfo temp:adviserInfos){
            Map<String,Object> map = new HashMap<>();
            Teacher teacher = teacherService.findById(temp.getTeacherId());
            map.put("name",teacher.getName());
            List<Integer> ids = stuTeaChService.queryStudentIdByAdviserId(teacher.getId(),semesterService.getSemesterIdNow());
            String studentName = studentService.queryStudentName(ids);
            map.put("studentName",studentName);
            list.add(map);
        }
        md.addAttribute("infos",list);
        return "/teacher/teacher_adviserInfo";
    }

    @GetMapping("/gudianceQuery") //指导教师查询页面
    public String gudianceQuery(Model md,Integer semesterId){
        if (semesterId==null) semesterId=semesterService.getSemesterIdNow();
        List<Semester> semesters = semesterService.queryAll();
        md.addAttribute("semesters",semesters);
        List<AdviserInfo> adviserInfos = adviserInfoService.queryAdviserInfoBySemesterId(semesterId);
        List<Map<String,Object>> list = new ArrayList<>();
        for (AdviserInfo temp:adviserInfos){
            List<Integer> ids = stuTeaChService.queryStudentIdByAdviserId(temp.getId(), semesterId);
            Map<String,Object> map= new HashMap<>();
            map.put("name",teacherService.findById(temp.getTeacherId()).getName());
            if (temp.getIsAccept()==(byte)1){
                map.put("condition","已同意");
            }else if(temp.getIsAccept()==(byte)0){
                map.put("condition","未答复");
            }else {
                map.put("condition","已拒绝");
            }
            map.put("studentName",studentService.queryStudentName(ids));
            list.add(map);
        }
        md.addAttribute("infos",list);
        return "/teacher/teacher_adviserQuery";
    }
}
