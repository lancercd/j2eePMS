package com.j2ee.controller;


import com.j2ee.annotation.TeachingSecretaryLogin;
import com.j2ee.db.dao.TeacherMapper;

import com.j2ee.db.domain.*;

import com.j2ee.db.domain.Teacher;
import com.j2ee.db.domain.TeachingSecretary;
import com.j2ee.db.service.AdviserInfoService;

import com.j2ee.db.service.DocumentTypeService;
import com.j2ee.db.service.SemesterService;
import com.j2ee.db.service.TeacherService;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 关于教学秘书的controller
 */
@Controller
@RequestMapping("/secretary")
public class SecretaryController {

    @Autowired
    private TeacherService teacherService;



    @Autowired
    private AdviserInfoService adviserInfoService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private DocumentTypeService documentTypeService;

//    @TeachingSecretaryLogin
    @GetMapping("/setTeacher")
    public String setTeacher() {
        return "/setTeacher";
    }



    @GetMapping("/teachingSecretary")
    public String teachingSecretary(){
        return "/teachingSecretary";
    }

    @GetMapping("/setAdviserManager")
    public String setAdviserManager(Model model){

        /*List<Semester> semesters = semesterService.queryAll();
        List<DocumentType> documentTypes = documentTypeService.queryAll();
        List<Teacher> teachers = teacherService.queryAll();*/

        List<AdviserInfo> adviserInfos = adviserInfoService.queryAll();
//        semesterService.findById(adviserInfos);
        ArrayList<String> list = new ArrayList<>();



        return "/setAdviserManager";
    }

    @TeachingSecretaryLogin
    @GetMapping("/showSemester")
    public Object showSemester(Model model){

        List<Semester> semesters = semesterService.queryAll();
        List<DocumentType> documentTypes = documentTypeService.queryAll();
        List<Teacher> teachers = teacherService.queryAll();

        model.addAttribute("semesters",semesters);
        model.addAttribute("documentTypes",documentTypes);
        model.addAttribute("teachers",teachers);


        return "/setTeacher";
    }

    @RequestMapping("/addAdviserTeacher")
    public String addAdviserTeacher(Integer semesterId, Integer documentTypeId, Integer teacherId){

        Semester semesters = semesterService.findById(semesterId);
        DocumentType documentTypes = documentTypeService.findById(documentTypeId);
        Teacher teacher = teacherService.findById(teacherId);

        AdviserInfo adviserInfo = new AdviserInfo();
        adviserInfo.setTeacherId(teacherId);
        adviserInfo.setDocTypeId(documentTypeId);
        adviserInfo.setSemesterId(semesterId);

        adviserInfoService.add(adviserInfo);
        return "";
    }




    @ResponseBody
    @PostMapping("/setTeacherForm")
    public Object setTeacherForm(Teacher teacher){
        if(StringUtils.isEmpty(teacher.getNumber())){
            return ResponseUtil.fail(400, "请填写教师编号");
        }
        if(StringUtils.isEmpty(teacher.getName())){
            return ResponseUtil.fail(400, "请填写教师姓名");
        }
        if(StringUtils.isEmpty(teacher.getPwd())){
            return ResponseUtil.fail(400, "请填写教师密码");
        }
        if(teacher.getId() == null){
            teacherService.insert(teacher);
        }
        return ResponseUtil.ok("成功");
    }



    @ResponseBody
    @PostMapping("/setAdviser")
    public Object setAdviser(Integer teacherId){
        AdviserInfo adviserInfo = new AdviserInfo();
        adviserInfo.setTeacherId(teacherId);
        adviserInfo.setSemesterId(semesterService.getSemesterIdNow());
        int num = adviserInfoService.add(adviserInfo);
        if (num==0) return ResponseUtil.updatedDataFailed();
        return ResponseUtil.ok();
    }

    @ResponseBody
    @PostMapping("/cancelAdviser")
    public Object cancelAdviser(Integer adviserId){
        int num = adviserInfoService.delete(adviserId);
        if (num==0) return ResponseUtil.updatedDataFailed();
        return ResponseUtil.ok();
    }

}
