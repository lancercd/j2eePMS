package com.j2ee.controller;


import com.j2ee.annotation.LoginUid;
import com.j2ee.annotation.TeacherLogin;
import com.j2ee.db.domain.*;
import com.j2ee.db.dto.StuTeaChDto;
import com.j2ee.db.service.*;
import com.j2ee.service.AppraiseTeacherDtoService;
import com.j2ee.service.StudentTeacherChoiceService;
import com.j2ee.utils.ResponseUtil;
import com.j2ee.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    AppraiseTeacherDtoService appraiseTeacherDtoService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StuTeaChService stuTeaChService;

    @Autowired
    StudentService studentService;

    @Autowired
    DocumentTypeService documentTypeService;


    @Autowired
    private StudentTeacherChoiceService studentTeacherChoiceService;

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
    public String guidanceAgree(Model md,@LoginUid Integer teacherId,Integer semesterId){//指导教师确认页面

        md.addAttribute("semesters", studentTeacherChoiceService.getAllSemester());
        md.addAttribute("semesterId", semesterId);

        md.addAttribute("lists",adviserInfoService.queryAdviserInfoByTeachId(teacherId, semesterId));;
        return "/teacher/teacher_confirms";
    }

    @TeacherLogin
    @GetMapping("/guidanceAgree/agree/edit/{id}")
    public String guidanceAgreeEdit(@LoginUid Integer teacherId, @PathVariable Integer id, Model model){//指导教师确认页面

        model.addAttribute("info", adviserInfoService.findByIdDto(id));

        return "/teacher/teacher_confirms_edit";
    }


    @TeacherLogin
    @ResponseBody
    @PostMapping("/guidanceAgree/agree/edit/{id}")
    public Object guidanceAgreeEditApi(@PathVariable Integer id, String reqInfo){//指导教师确认页面

        if(StringUtils.isEmpty(reqInfo)){
            return ResponseUtil.fail("请填写要求!");
        }
        AdviserInfo info = adviserInfoService.findById(id);
        info.setIsAccept((byte) 1);
        info.setReqInfo(reqInfo);
        adviserInfoService.update(info);

        return ResponseUtil.ok();
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
    @TeacherLogin
    @GetMapping("/guidanceInfo") //指导教师确认信息页面
    public String guidanceInfo(Model md,Integer semesterId){
        if (semesterId==null) semesterId = 0;
        List<Semester> semesters = semesterService.queryAll();
        md.addAttribute("semesters",semesters);
        List<AdviserInfo> adviserInfos = adviserInfoService.queryBySemesterId(semesterId);
        List<Map<String,Object>> list = new ArrayList<>();
        for (AdviserInfo temp:adviserInfos){
            Map<String,Object> map = new HashMap<>();
            Teacher teacher = teacherService.findById(temp.getTeacherId());
            map.put("name",teacher.getName());
            List<Integer> ids = stuTeaChService.queryStudentIdByAdviserId(teacher.getId(),semesterService.getSemesterIdNow());
            String studentName = studentService.queryStudentName(ids);
            map.put("studentName",studentName);
            map.put("semesterInfo",semesterService.findById(temp.getSemesterId()));
            list.add(map);
        }
        md.addAttribute("semesterId",semesterId);
        md.addAttribute("infos",list);
        return "/teacher/teacher_adviserInfo";
    }
    @TeacherLogin
    @GetMapping("/gudianceQuery") //指导教师查询页面
    public String gudianceQuery(Model md,Integer semesterId) {
        if (semesterId == null) semesterId = 0;
        List<Semester> semesters = semesterService.queryAll();
        md.addAttribute("semesters", semesters);
        List<AdviserInfo> adviserInfos = adviserInfoService.queryAdviserInfoBySemesterId(semesterId);
        List<Map<String, Object>> list = new ArrayList<>();
        for (AdviserInfo temp : adviserInfos) {
            List<Integer> ids = stuTeaChService.queryStudentIdByAdviserId(temp.getId(), semesterId);
            Map<String, Object> map = new HashMap<>();
            map.put("name", teacherService.findById(temp.getTeacherId()).getName());
            map.put("semesterInfo",semesterService.findById(temp.getSemesterId()));
            map.put("studentName", studentService.queryStudentName(ids));
            list.add(map);
        }
        md.addAttribute("semesterId",semesterId);
        md.addAttribute("infos", list);
        return "/teacher/teacher_adviserQuery";
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
