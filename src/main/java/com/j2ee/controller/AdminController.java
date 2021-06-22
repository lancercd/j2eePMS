package com.j2ee.controller;


import com.j2ee.db.domain.DocumentType;
import com.j2ee.db.domain.News;
import com.j2ee.db.domain.Semester;
import com.j2ee.db.service.DocumentTypeService;
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

    @Autowired
    private DocumentTypeService documentTypeService;

    /**
     * 学期管理界面
     * @param model
     * @return
     */
    @GetMapping("/adSemester")
    public String semesterType(Integer semesterId, Model model){
        if(semesterId != null && semesterId != 0){
            model.addAttribute("semesters",semesterService.findById(semesterId));
        } else {
            model.addAttribute("semesters", studentTeacherChoiceService.getAllSemester());
        }

        return "admin/adSemester";
    }

    /**
     * 资料类型管理界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/adDocumentType")
    public String documentType(Integer id, Model model){
        if(id != null && id != 0){
            model.addAttribute("documentTypes", documentTypeService.findById(id));
        } else {
            model.addAttribute("documentTypes", documentTypeService.queryAll());
        }

        return "admin/adDocumentType";
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
     * 删除资料类型
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/doc/del/{id}")
    public Object delDocumentType(@PathVariable Integer id){
        DocumentType byId = documentTypeService.findById(id);
        if(byId == null){
            return ResponseUtil.fail("未找到该记录！");
        }
        documentTypeService.delete(id);
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

    @GetMapping("/doc/edit/{id}")
    public String docEdit(@PathVariable Integer id, Model model){
        model.addAttribute("documentType", documentTypeService.findById(id));
        return "admin/doc_edit";
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
     * 添加资料管理
     * @param model
     * @return
     */
    @GetMapping("doc/add")
    public String doc_edit(Model model){
        return "admin/doc_add";
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
     * 修改资料类型
     * @param id
     * @param name
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/doc/edit")
    public Object docEditSubmit(Integer id, String name) throws IOException {
        DocumentType byId = documentTypeService.findById(id);
        if(byId == null){
            return ResponseUtil.fail("修改失败，记录不存在！");
        }
        byId.setName(name);
        documentTypeService.updateById(byId);
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

    /**
     * 添加资料类型
     * @param name
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/doc/add")
    public Object docAddSubmit(String name) throws IOException{
        DocumentType documentType = new DocumentType();
        documentType.setName(name);
        documentTypeService.add(documentType);
        return ResponseUtil.ok("修改成功！");
    }
}
