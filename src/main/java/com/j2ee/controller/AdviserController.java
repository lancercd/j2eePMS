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

import java.util.ArrayList;
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
    public Object disagreeAdviser(Integer id){
        int num = adviserInfoService.disagreeAdviser(id);
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

        List<AppraiseTeacher> appraiseTeachers = appraiseTeacherService.findByTeacherId(uid, 0);
        model.addAttribute("lists", new ArrayList<List<StuTeaChDto>>());
        if (appraiseTeachers.size() != 0) {
            List<Integer> ids = new ArrayList<>();
            for (AppraiseTeacher app :appraiseTeachers ) {
                ids.add(app.getId());
            }

            model.addAttribute("lists", studentTeacherChoiceService.selectStuTeachDtoByIdsAndSemester(ids, semesterId));
        }


        return "adviser/teaConfirmStu";
    }


    @TeacherLogin
    @GetMapping("/confirm/student/unAccept/{id}")
    public String confirmStudentUnAccept(@LoginUid Integer uid, @PathVariable Integer id){

        AppraiseTeacher info = appraiseTeacherService.findById(id);

        if(info != null){
            info.setIsAccept((byte)-1);
            appraiseTeacherService.update(info);
        }

        return "redirect:/adviser/confirm/student";
    }


    /**
     * 同意担任评阅老师
     * @param uid   teaId
     * @param id    app
     * @return page
     */
    @TeacherLogin
    @GetMapping("/confirm/student/accept/{id}")
    public String editConfirm(@LoginUid Integer uid, @PathVariable Integer id){
        AppraiseTeacher info = appraiseTeacherService.findById(id);

        if(info != null){
            info.setIsAccept((byte)1);
            appraiseTeacherService.update(info);
        }


        return "redirect:/adviser/selected/student";
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

        List<AppraiseTeacher> appraiseTeachers = appraiseTeacherService.findByTeacherId(uid, 1);
        model.addAttribute("lists", new ArrayList<List<StuTeaChDto>>(0));
        if (appraiseTeachers.size() != 0) {
            List<Integer> ids = new ArrayList<>();
            for (AppraiseTeacher app :appraiseTeachers ) {
                ids.add(app.getId());
            }

            model.addAttribute("lists", studentTeacherChoiceService.selectStuTeachDtoByIdsAndSemester(ids, semesterId));
        }

        return "adviser/teaSelectedStu";
    }



    /**
     * 老师打分 编辑信息 (信息编辑页面)
     * @param uid   teaId
     * @param id    stuTeaChId
     * @return page
     */
    @TeacherLogin
    @GetMapping("/selected/student/edit/{id}")
    public String editSelected(@LoginUid Integer uid, @PathVariable Integer id, Model model){
        StuTeaChDto info = studentTeacherChoiceService.findStuTeachDtoById(id);

        Teacher teacher = teacherService.findById(uid);
        Integer appTeaId = null;
        AppraiseTeacher appraiseTeacher = info.getAppraiseTeacher();
        if (appraiseTeacher != null) {
            appTeaId = appraiseTeacher.getTeacherId();
        }

        model.addAttribute("info", info);
        model.addAttribute("teaId", uid);
        model.addAttribute("teacher", teacher);
        model.addAttribute("appTeaId", appTeaId);

        return "adviser/teaSelectedStuEdit";
    }


    /**
     *  老师同意该学生  并填写信息
     * @param uid       该老师id
     * @param id        该记录id
     * @param suggestion 意见
     * @return page
     */
    @ResponseBody
    @PostMapping("/selected/student/edit/{id}")
    public Object editSelectedApi(@LoginUid Integer uid, @PathVariable Integer id, Integer score, String suggestion){

        StuTeaCh stuTeaCh = stuTeaChService.findById(id);
        AppraiseTeacher appTea = appraiseTeacherService.findById(stuTeaCh.getAppraiseId());

        appTea.setIsAccept((byte)1);
        appTea.setScore(score);
        appTea.setSuggestion(suggestion);
        appraiseTeacherService.update(appTea);

        return ResponseUtil.ok("成功!");
    }
}
