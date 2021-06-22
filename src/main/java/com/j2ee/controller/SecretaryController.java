package com.j2ee.controller;


import com.j2ee.db.dao.TeacherMapper;
import com.j2ee.db.domain.AdviserInfo;
import com.j2ee.db.domain.Teacher;
import com.j2ee.db.domain.TeachingSecretary;
import com.j2ee.db.service.AdviserInfoService;
import com.j2ee.db.service.SemesterService;
import com.j2ee.db.service.TeacherService;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;


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


    @GetMapping("/setTeacher")
    public String setTeacher() {
        return "/setTeacher";
    }


    @GetMapping("/teachingSecretary")
    public String teachingSecretary(){
        return "/teachingSecretary";
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
