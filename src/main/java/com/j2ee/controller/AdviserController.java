package com.j2ee.controller;

import com.j2ee.db.domain.AdviserInfo;
import com.j2ee.db.domain.Student;
import com.j2ee.db.service.AdviserInfoService;
import com.j2ee.db.service.SemesterService;
import com.j2ee.db.service.StuTeaChService;
import com.j2ee.db.service.StudentService;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/adviser")
public class AdviserController {

    @Autowired
    AdviserInfoService adviserInfoService;

    @Autowired
    StuTeaChService stuTeaChService;

    @Autowired
    StudentService studentService;

    @Autowired
    SemesterService semesterService;

    @ResponseBody
    @PostMapping("/agreeAdviser")
    public Object agreeAdviser(Integer adviserId){
        int num = adviserInfoService.agreeAdviser(adviserId);
        if (num==0) return ResponseUtil.updatedDataFailed();
        return ResponseUtil.ok();
    }

    @ResponseBody
    @PostMapping("/disagreeAdviser")
    public Object disagreeAdviser(Integer adviserId){
        int num = adviserInfoService.disagreeAdviser(adviserId);
        if (num==0) return ResponseUtil.updatedDataFailed();
        return ResponseUtil.ok();
    }

    @ResponseBody
    @PostMapping("/queryAdviserInfo")
    public Object queryAdviserInfo(Integer semesterId){
        List<AdviserInfo> adviserInfos = adviserInfoService.queryAdviserInfoBySemesterId(semesterId);
        return ResponseUtil.ok(adviserInfos);
    }

    @ResponseBody
    @PostMapping("/queryStudentInfoByAdviserId")
    public Object queryStudentInfoByAdviserId(Integer adviserId){
        int semesterIdNow = semesterService.getSemesterIdNow();
        List<Integer> ids = stuTeaChService.queryStudentIdByAdviserId(adviserId,semesterIdNow);
        List<Student> students = studentService.findById(ids);
        return ResponseUtil.ok(students);
    }

    @ResponseBody
    @PostMapping("/agreeStudent")
    public Object agreeStudent(Integer stuTeaId,Boolean isAccept){
            return stuTeaChService.agreeStudent(stuTeaId,isAccept);
    }
}
