package com.j2ee.controller;



import com.j2ee.annotation.LoginUid;
import com.j2ee.annotation.StudentLogin;
import com.j2ee.db.domain.StuTeaCh;
import com.j2ee.db.service.AdviserInfoService;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.j2ee.service.StudentTeacherChoiceService;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("student")
public class StudentController {


    @Autowired
    private StudentTeacherChoiceService studentTeacherChoiceService;


    @Autowired
    private AdviserInfoService adviserInfoService;



    /**
     * 学生选择导师页面(根据学期选择)
     * @param semesterId 学期id
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


    /**
     * 学生选择指导老师 api
     * @param uid       uid
     * @param adviserId 指导老师信息
     * @return Json
     */
    @StudentLogin
    @ResponseBody
    @PostMapping("/adviser/add")
    public Object selectAdviser(@LoginUid Integer uid, Integer adviserId){

        //写的好**乱啊  不想改了  就这样吧

        if (adviserId == null) {
            return ResponseUtil.fail(400, "id不能为空!");
        }

        StuTeaCh choiceInfo = studentTeacherChoiceService.findStuTeachByStuIdAndTeaId(adviserId);

        if (choiceInfo == null) {
            //没选择过该指导老师

            int res = studentTeacherChoiceService.addSelectInfo(adviserInfoService.findById(adviserId),uid);

            if(res == 0){
                return ResponseUtil.fail("添加数据库失败");
            }

        }else{
            //选择过该指导老师
            Byte isAccept = choiceInfo.getIsAccept();
            String msg = null;
            if(isAccept == 0){
                msg = ",请耐心等待老师回复哦";
            }else if (isAccept == 1){
                msg = "!";
            }else{
                msg = ",但老师位同意!";
            }

            return ResponseUtil.fail("您已选择过该老师了" + msg);
        }

        return ResponseUtil.ok("添加成功!");
    }



}
