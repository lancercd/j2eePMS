package com.j2ee.controller;


import com.j2ee.db.domain.News;
import com.j2ee.db.domain.Semester;
import com.j2ee.db.service.SemesterService;
import com.j2ee.service.StudentTeacherChoiceService;
import com.j2ee.utils.ResponseUtil;
import com.j2ee.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 管理员界面
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private StudentTeacherChoiceService studentTeacherChoiceService;

    @Autowired
    private SemesterService semesterService;

    /**
     * 学期管理界面
     * @param model
     * @return
     */
    @GetMapping("/adSemester")
    public String userType(Integer semesterId, Model model){
        if(semesterId != null && semesterId != 0){
            model.addAttribute("semesters",semesterService.findById(semesterId));
        } else {
            model.addAttribute("semesters", studentTeacherChoiceService.getAllSemester());
        }

        return "admin/adSemester";
    }

    /**
     * 删除学期
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/del/{id}")
    public Object delSemester(@PathVariable Integer id){
        Semester byId = semesterService.findById(id);
        if(byId == null){
            return ResponseUtil.fail("未找到该记录！");
        }
        semesterService.deleteById(id);
        return ResponseUtil.ok("删除成功！");
    }

    /**
     * 修改学期
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("semester", semesterService.findById(id));
        return "admin/edit";
    }

    /**
     * 添加学期
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String edit(Model model){
        return "admin/add";
    }

    /**
     * 修改学期
     * @param id
     * @param name
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/edit")
    public Object editSubmit(Integer id, String name) throws IOException {
        Semester byId = semesterService.findById(id);
        if(byId == null){
            return ResponseUtil.fail("修改失败，记录不存在！");
        }
        byId.setName(name);
        semesterService.updateById(byId);
        return ResponseUtil.ok("修改成功！");
    }

    /**
     * 添加学期
     * @param name
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/add")
    public Object addSubmit(String name) throws IOException {
        Semester semester = new Semester();
        semester.setName(name);
        semesterService.add(semester);
        return ResponseUtil.ok("修改成功！");
    }
}
