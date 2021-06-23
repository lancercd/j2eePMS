package com.j2ee.controller;

import com.j2ee.annotation.LoginUid;
import com.j2ee.annotation.TeacherLogin;
import com.j2ee.db.domain.*;
import com.j2ee.db.dto.StuTeaChDto;
import com.j2ee.db.service.*;
import com.j2ee.service.StudentTeacherChoiceService;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    AppraiseTeacherService appraiseTeacherService;


    @Autowired
    private TeacherService teacherService;


    @Autowired
    private StudentTeacherChoiceService studentTeacherChoiceService;

    @ResponseBody
    @PostMapping("/agreeAdviser")
    public Object agreeAdviser(Integer adviserId,Integer studentId){
        int num = adviserInfoService.agreeAdviser(adviserId);
        if (num==0) return ResponseUtil.updatedDataFailed();
        return ResponseUtil.ok();
    }

    @ResponseBody
    @PostMapping("/disagreeAdviser")
    public Object disagreeAdviser(Integer adviserId,Integer studentId){
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

    @ResponseBody
    @PostMapping("/selectAppraiser")
    public Object selectAppraiser(Integer teacherId,Integer stuTeaChId){
        AppraiseTeacher teacher = new AppraiseTeacher();
        teacher.setTeacherId(teacherId);
        teacher.setStuTeaCh(stuTeaChId);
        int num = appraiseTeacherService.add(teacher);
        if (num==0) return ResponseUtil.fail();
        return ResponseUtil.ok();
    }






    /**
     * 指导老师确认学生列表 (等待确认的 列表)
     * @return page
     */
    @TeacherLogin
    @GetMapping("/confirm/student")
    public String adviserConfirmStudent(@LoginUid Integer uid, Integer semesterId, Model model){

        model.addAttribute("semesters", studentTeacherChoiceService.getAllSemester());
        model.addAttribute("semesterId", semesterId);

        List<AppraiseTeacher> byTeacherId = appraiseTeacherService.findByTeacherId(uid, 0);


        if (semesterId != null && semesterId != 0) {
            model.addAttribute("lists", studentTeacherChoiceService.selectStuTeachDtoByTeaIdAndSemester(uid, semesterId, (byte) 0));
        }else{
            model.addAttribute("lists", studentTeacherChoiceService.selectStuTeachDtoByTeaId(uid, (byte) 0));
        }



        return "teacher/teaConfirmStu";
    }


    @ResponseBody
    @PostMapping("/confirm/student/unAccept/{id}")
    public Object confirmStudentUnAccept(@LoginUid Integer uid, @PathVariable Integer id){
        StuTeaCh info = stuTeaChService.findById(id);

        if(info == null || !info.getTeacherId().equals(uid)){
            return ResponseUtil.fail("未找到该记录!");
        }

        info.setIsAccept((byte)-1);

        if (stuTeaChService.update(info) == 0) {
            return ResponseUtil.fail("更新失败!");
        }

        return ResponseUtil.ok("成功!");
    }


    /**
     * 指导老师同意学生(信息编辑页面)
     * @param uid  teaId
     * @param id    stuTeaChId
     * @return page
     */
    @TeacherLogin
    @GetMapping("/confirm/student/edit/{id}")
    public String editConfirm(@LoginUid Integer uid, @PathVariable Integer id, Model model){
        StuTeaChDto info = studentTeacherChoiceService.findStuTeachDtoById(id);
        System.out.println(id);
        System.out.println(info);


        List<Teacher> teachers = teacherService.queryAll();
        Integer appTeaId = null;
        AppraiseTeacher appraiseTeacher = info.getAppraiseTeacher();
        if (appraiseTeacher != null) {
            appTeaId = appraiseTeacher.getTeacherId();
        }

        model.addAttribute("info", info);
        model.addAttribute("teaId", uid);
        model.addAttribute("teachers", teachers);
        model.addAttribute("appTeaId", appTeaId);

        return "teacher/teaConfirmStuEdit";
    }


    /**
     *  老师同意该学生  并填写信息
     * @param uid       该老师id
     * @param id        该记录id
     * @param teacherId 指导老师id
     * @param suggestion 意见
     * @return page
     */
    @ResponseBody
    @PostMapping("/confirm/student/edit/{id}")
    public Object editConfirmApi(@LoginUid Integer uid, @PathVariable Integer id, Integer teacherId, String suggestion){
        if (teacherId == null) {
            return ResponseUtil.fail("请选择评阅老师!");
        }
        StuTeaChDto infoDto = studentTeacherChoiceService.findStuTeachDtoById(id);

        AppraiseTeacher appTea = new AppraiseTeacher();
        appTea.setTeacherId(teacherId);
        appTea.setStuTeaCh(id);
        appTea.setIsAccept((byte)0);
        appTea.setIsDel(false);

        int appId = appraiseTeacherService.add(appTea);

        StuTeaCh info = stuTeaChService.findById(id);
        info.setIsAccept((byte) 1);
        info.setAppraiseId(teacherId);
        info.setSuggestion(suggestion);
        info.setAppraiseId(appId);
        stuTeaChService.update(info);

        return ResponseUtil.ok("成功!");
    }




    /**
     * 已经同意的学生列表
     * @return page
     */
    @TeacherLogin
    @GetMapping("/selected/student")
    public String adviserSelectedStudent(@LoginUid Integer uid, Integer semesterId, Model model){

        model.addAttribute("semesters", studentTeacherChoiceService.getAllSemester());
        model.addAttribute("semesterId", semesterId);


        if (semesterId != null && semesterId != 0) {
            model.addAttribute("lists", studentTeacherChoiceService.selectStuTeachDtoByTeaIdAndSemester(uid, semesterId, (byte)1));
        }else{
            model.addAttribute("lists", studentTeacherChoiceService.selectStuTeachDtoByTeaId(uid, (byte) 1));
        }

        return "teacher/teaSelectedStu";
    }



    /**
     * 指导老师打分 编辑信息 (信息编辑页面)
     * @param uid   teaId
     * @param id    stuTeaChId
     * @return page
     */
    @TeacherLogin
    @GetMapping("/selected/student/edit/{id}")
    public String editSelected(@LoginUid Integer uid, @PathVariable Integer id, Model model){
        StuTeaChDto info = studentTeacherChoiceService.findStuTeachDtoById(id);
        System.out.println(id);
        System.out.println(info);


        List<Teacher> teachers = teacherService.queryAll();
        Integer appTeaId = null;
        AppraiseTeacher appraiseTeacher = info.getAppraiseTeacher();
        if (appraiseTeacher != null) {
            appTeaId = appraiseTeacher.getTeacherId();
        }

        model.addAttribute("info", info);
        model.addAttribute("teaId", uid);
        model.addAttribute("teachers", teachers);
        model.addAttribute("appTeaId", appTeaId);

        return "teacher/teaSelectedStuEdit";
    }


    /**
     *  老师同意该学生  并填写信息
     * @param uid       该老师id
     * @param id        该记录id
     * @param teacherId 指导老师id
     * @param suggestion 意见
     * @return page
     */
    @ResponseBody
    @PostMapping("/selected/student/edit/{id}")
    public Object editSelectedApi(@LoginUid Integer uid, @PathVariable Integer id, Integer score, Integer teacherId, String suggestion){
        if (teacherId == null) {
            return ResponseUtil.fail("请选择评阅老师!");
        }
        StuTeaChDto infoDto = studentTeacherChoiceService.findStuTeachDtoById(id);

        AppraiseTeacher appTea = new AppraiseTeacher();
        appTea.setTeacherId(teacherId);
        appTea.setStuTeaCh(id);
        appTea.setIsAccept((byte)0);
        appTea.setIsDel(false);

        int appId = appraiseTeacherService.add(appTea);

        StuTeaCh info = stuTeaChService.findById(id);
        info.setIsAccept((byte) 1);
        info.setSuggestion(suggestion);
        info.setAppraiseId(appId);
        info.setScore(score);
        stuTeaChService.update(info);

        return ResponseUtil.ok("成功!");
    }
}
