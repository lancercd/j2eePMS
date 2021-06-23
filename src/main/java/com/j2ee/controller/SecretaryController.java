package com.j2ee.controller;


import com.j2ee.annotation.TeachingSecretaryLogin;
import com.j2ee.db.dao.TeacherMapper;

import com.j2ee.db.domain.*;

import com.j2ee.db.domain.Teacher;
import com.j2ee.db.domain.TeachingSecretary;
import com.j2ee.db.dto.AdviserInfoDto;
import com.j2ee.db.service.AdviserInfoService;

import com.j2ee.db.service.DocumentTypeService;
import com.j2ee.db.service.SemesterService;
import com.j2ee.db.service.TeacherService;
import com.j2ee.service.StudentTeacherChoiceService;
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


    @Autowired
    private StudentTeacherChoiceService studentTeacherChoiceService;

    @GetMapping("/teachingSecretary")
    public String teachingSecretary(){
        return "/teachingSecretary";
    }

    @GetMapping("/setAdviserManager")
    public String setAdviserManager(Model model){

        List<AdviserInfoDto> allStuTeaChAll = studentTeacherChoiceService.getAllStuTeaChAll();
        model.addAttribute("allStuTeaChAll",allStuTeaChAll);
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
    public String addAdviserTeacher(Integer semesterId, Integer documentTypeId, Integer teacherId, HttpServletRequest request){

        Semester semesters = semesterService.findById(semesterId);
        DocumentType documentTypes = documentTypeService.findById(documentTypeId);
        Teacher teacher = teacherService.findById(teacherId);


        AdviserInfo adviserInfo = new AdviserInfo();
        adviserInfo.setTeacherId(teacherId);
        adviserInfo.setDocTypeId(documentTypeId);
        adviserInfo.setSemesterId(semesterId);
        String reqInfo = request.getParameter("reqInfo");
        Byte isAcceptNum = Byte.valueOf(request.getParameter("isAcceptNum"));
//        System.out.println(isAcceptNum);

        adviserInfo.setReqInfo(reqInfo);
        adviserInfo.setIsAccept(isAcceptNum);

        if (isAcceptNum == 0|| isAcceptNum==-1){
            adviserInfo.setIsDel(false);
        }
        if(isAcceptNum==1){
            adviserInfo.setIsDel(true);
        }

        adviserInfoService.add(adviserInfo);
        return "redirect:/secretary/setAdviserManager";
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
