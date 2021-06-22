package com.j2ee.controller;



import com.j2ee.annotation.LoginUid;
import com.j2ee.annotation.StudentLogin;
import com.j2ee.db.domain.AdviserInfo;
import com.j2ee.db.domain.Document;
import com.j2ee.db.domain.StuTeaCh;
import com.j2ee.db.dto.StuTeaChDto;
import com.j2ee.db.service.AdviserInfoService;
import com.j2ee.db.service.DocumentService;
import com.j2ee.db.service.StuTeaChService;
import com.j2ee.utils.FileUploadUtil;
import com.j2ee.utils.ResponseUtil;
import com.j2ee.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.j2ee.service.StudentTeacherChoiceService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;


@Controller
@RequestMapping("student")
public class StudentController {


    @Autowired
    private StudentTeacherChoiceService studentTeacherChoiceService;


    @Autowired
    private StuTeaChService stuTeaChService;


    @Autowired
    private AdviserInfoService adviserInfoService;


    @Autowired
    private DocumentService documentService;



    /**
     * 学生选择导师页面(根据学期选择)
     *            导师列表
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


    /**
     * 学生查看  已选择信息
     * @param uid   学生id 用户id
     * @param model model
     * @return  page
     */
    @StudentLogin
    @GetMapping("/selected")
    public String selectedAdviserList(@LoginUid Integer uid, Integer semesterId, Model model){

        model.addAttribute("semesters", studentTeacherChoiceService.getAllSemester());
        model.addAttribute("semesterId", semesterId);


        if (semesterId != null && semesterId != 0) {
            model.addAttribute("lists", studentTeacherChoiceService.selectStuTeachDtoByStuIdAndSemester(uid, semesterId));
        }else{
            model.addAttribute("lists", studentTeacherChoiceService.selectStuTeachDtoByStuId(uid));
        }


        return "student/selected";
    }



    /**
     * 删除学生选择信息
     * @param uid 学生id
     * @param id  选择表id
     * @return Json
     */
    @ResponseBody
    @StudentLogin
    @PostMapping("/del/{id}")
    public Object delAdviser(@LoginUid Integer uid, @PathVariable Integer id){
        StuTeaCh stuTeachById = studentTeacherChoiceService.findStuTeachById(id);
        if (!stuTeachById.getStudentId().equals(uid)) {
            return ResponseUtil.fail("未找到该记录!");
        }

        studentTeacherChoiceService.delStuTeaCh(id);
        return ResponseUtil.ok("删除成功!");
    }


    @StudentLogin
    @GetMapping("/edit/{id}")
    public String edit(@LoginUid Integer uid, @PathVariable Integer id, Model model){
        StuTeaChDto info = studentTeacherChoiceService.findStuTeachDtoById(id);
        System.out.println(id);
        System.out.println(info);

        model.addAttribute("id", id);
        model.addAttribute("info", info);

        return "student/edit";
    }


    @ResponseBody
    @StudentLogin
    @PostMapping("/edit")
    public Object editSubmit(@LoginUid Integer uid, Integer id, String intro, MultipartFile file) throws IOException {

        if (file.getSize() == 0) {
            return ResponseUtil.uploadFail("请选择文件");
        }

        if(StringUtils.isEmpty(intro)){
            return ResponseUtil.fail("请输入自我介绍");
        }

        StuTeaCh info = studentTeacherChoiceService.findStuTeachById(id);
        if(info == null || !info.getStudentId().equals(uid)){
            return ResponseUtil.fail("未找到该记录!");
        }

        Integer docId = info.getDocumentId();
        Document doc = null;
        if(docId != null){
            doc = documentService.findById(docId);
            if(doc.getStatus() == (byte)1){
                return ResponseUtil.fail("已审核,无法修改");
            }
        }

        String path = null;

        try {
            path = FileUploadUtil.save(file);
        } catch (Exception e) {
            return ResponseUtil.uploadFail("上传文件失败!");
        }


        //编辑
        if(doc != null){
            doc.setPath(path);
            doc.setStatus((byte)0);
            documentService.update(doc);
        }else{
            doc = new Document();
            AdviserInfo adv = adviserInfoService.findById(info.getAdviserInfo());
            doc.setTypeId(adv.getDocTypeId() == null? 1 : adv.getDocTypeId());
            doc.setStudentId(uid);
            doc.setStatus((byte)0);
            doc.setPath(path);
            docId = documentService.add(doc);
        }

        info.setDocumentId(docId);
        info.setIntro(intro);


        if(stuTeaChService.update(info) == 0){
            return ResponseUtil.fail("添加失败!");
        }


        return ResponseUtil.ok("添加成功!");
    }

}
