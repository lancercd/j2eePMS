package com.j2ee.controller;

import com.j2ee.db.domain.*;
import com.j2ee.db.service.*;
import com.j2ee.service.StudentTeacherChoiceService;
import com.j2ee.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

/**
 * 管理员界面
 */
@Controller
@RequestMapping("admin")
@SuppressWarnings({"all"})
public class AdminController {
    @Autowired
    private StudentTeacherChoiceService studentTeacherChoiceService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TeachingSecretaryService teachingSecretaryService;

    @Autowired
    private UserTypeService userTypeService;

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
     * 用户类型管理界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/adUserType")
    public String userType(Integer id, Model model){
        if(id != null && id != 0){
            model.addAttribute("userTypes", userTypeService.findById(id));
        } else {
            model.addAttribute("userTypes", userTypeService.queryAll());
        }
        return "admin/adUserType";
    }

    /**
     * 学生管理界面
     * @param number
     * @param model
     * @return
     */
    @GetMapping("/adUser/student")
    public String studentType(String number, Model model){
        if(number != null && number != ""){
            model.addAttribute("students", studentService.findByNumber(number));
        } else {
            model.addAttribute("students", studentService.queryAll());
        }
        return "admin/adUser/student";
    }

    /**
     * 老师管理界面
     * @param number
     * @param model
     * @return
     */
    @GetMapping("/adUser/teacher")
    public String teacherType(String number, Model model){
        if(number != null && number != ""){
            model.addAttribute("teachers", teacherService.findByNumber(number));
        } else {
            model.addAttribute("teachers", teacherService.queryAll());
        }
        return "admin/adUser/teacher";
    }

    /**
     * 教学秘书管理界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/adUser/secretary")
    public String secretaryType(Integer id, Model model){
        if(id != null && id != 0){
            model.addAttribute("secretaries", teachingSecretaryService.findById(id));
        } else {
            model.addAttribute("secretaries", teachingSecretaryService.queryAll());
        }
        return "admin/adUser/secretary";
    }

    /**
     * 管理员管理界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/adUser/admin")
    public String adminType(Integer id, Model model){
        if(id != null && id != 0){
            model.addAttribute("admins", adminService.findById(id));
        } else {
            model.addAttribute("admins", adminService.queryAll());
        }
        return "admin/adUser/admin";
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
     * 删除用户类型
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/user/del/{id}")
    public Object delUserType(@PathVariable Integer id){
        UserType byId = userTypeService.findById(id);
        if(byId == null){
            return ResponseUtil.fail("未找到该记录！");
        }
        userTypeService.delete(id);
        return ResponseUtil.ok("删除成功！");
    }

    /**
     * 删除学生类型
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/adUser/del/student/{id}")
    public Object delStudent(@PathVariable Integer id){
        Student byId = studentService.findById(id);
        if(byId == null){
            return ResponseUtil.fail("未找到该记录！");
        }
        studentService.delete(id);
        return ResponseUtil.ok("删除成功！");
    }

    /**
     * 删除老师类型
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/adUser/del/teacher/{id}")
    public Object delTeacher(@PathVariable Integer id){
        Teacher byId = teacherService.findById(id);
        if(byId == null){
            return ResponseUtil.fail("未找到该记录！");
        }
        teacherService.delete(id);
        return ResponseUtil.ok("删除成功！");
    }

    /**
     * 删除管理员类型
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/adUser/del/admin/{id}")
    public Object delAdmin(@PathVariable Integer id){
        Admin byId = adminService.findById(id);
        if(byId == null){
            return ResponseUtil.fail("未找到该记录！");
        }
        adminService.delete(id);
        return ResponseUtil.ok("删除成功！");
    }

    /**
     * 删除教学助理
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/adUser/del/secretary/{id}")
    public Object delSecretary(@PathVariable Integer id){
        TeachingSecretary byId = teachingSecretaryService.findById(id);
        if(byId == null){
            return ResponseUtil.fail("未找到该记录！");
        }
        teachingSecretaryService.delete(id);
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
     * 修改资料类型
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/doc/edit/{id}")
    public String docEdit(@PathVariable Integer id, Model model){
        model.addAttribute("documentType", documentTypeService.findById(id));
        return "admin/doc_edit";
    }

    /**
     * 修改用户类型
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user/edit/{id}")
    public String userEdit(@PathVariable Integer id, Model model){
        model.addAttribute("documentType", userTypeService.findById(id));
        return "admin/user_edit";
    }

    /**
     * 修改学生类型
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/adUser/edit/student/{id}")
    public String studentEdit(@PathVariable Integer id, Model model){
        model.addAttribute("student", studentService.findById(id));
        return "admin/adUser/student_edit";
    }

    /**
     * 修改教师类型
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/adUser/edit/teacher/{id}")
    public String teacherEdit(@PathVariable Integer id, Model model){
        model.addAttribute("teacher", teacherService.findById(id));
        return "admin/adUser/teacher_edit";
    }

    /**
     * 修改管理员类型
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/adUser/edit/admin/{id}")
    public String adminEdit(@PathVariable Integer id, Model model){
        model.addAttribute("admin", adminService.findById(id));
        return "admin/adUser/admin_edit";
    }

    /**
     * 修改教学助理类型
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/adUser/edit/secretary/{id}")
    public String secretaryEdit(@PathVariable Integer id, Model model){
        model.addAttribute("secretary", teachingSecretaryService.findById(id));
        return "admin/adUser/secretary_edit";
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
    @GetMapping("/doc/add")
    public String doc_edit(Model model){
        return "admin/doc_add";
    }

    /**
     * 添加用户类型
     * @param model
     * @return
     */
    @GetMapping("/user/add")
    public String user_edit(Model model){
        return "admin/user_add";
    }

    /**
     * 添加学生信息
     * @param model
     * @return
     */
    @GetMapping("/adUser/addStudent")
    public String student_edit(Model model){
        return "admin/adUser/student_add";
    }

    /**
     * 添加教师类型
     * @param model
     * @return
     */
    @GetMapping("/adUser/addTeacher")
    public String teacherEdit(Model model){
        return "admin/adUser/teacher_add";
    }

    /**
     * 添加管理员类型
     * @param model
     * @return
     */
    @GetMapping("/adUser/addAdmin")
    public String adminEdit(Model model){
        return "admin/adUser/admin_add";
    }

    /**
     * 添加教学助理类型
     * @param model
     * @return
     */
    @GetMapping("/adUser/addSecretary")
    public String secretaryEdit(Model model){
        return "admin/adUser/secretary_add";
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
     * 修改用户类型
     * @param id
     * @param name
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/user/edit")
    public Object docUserSubmit(Integer id, String name) throws IOException {
        UserType byId = userTypeService.findById(id);
        if(byId == null){
            return ResponseUtil.fail("修改失败，记录不存在！");
        }
        byId.setName(name);
        userTypeService.updateById(byId);
        return ResponseUtil.ok("修改成功！");
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/adUser/edit/student")
    public Object studentEditSubmit(Student student) throws IOException {
        Student byId = studentService.findById(student.getId());
        student.setIsDel(byId.getIsDel());
        student.setAddTime(byId.getAddTime());
        studentService.updateById(student);
        return ResponseUtil.ok("修改成功！");
    }

    /**
     * 修改教师信息
     * @param teacher
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/adUser/edit/teacher")
    public Object teacherEditSubmit(Teacher teacher) throws IOException{
        Teacher byId = teacherService.findById(teacher.getId());
        teacher.setIsDel(byId.getIsDel());
        teacher.setAddTime(byId.getAddTime());
        teacherService.updateById(teacher);
        return ResponseUtil.ok("修改成功！");
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/adUser/edit/admin")
    public Object adminEditSubmit(Admin admin) throws IOException{
        Admin byId = adminService.findById(admin.getId());
        admin.setIsDel(byId.getIsDel());
        admin.setAddTime(byId.getAddTime());
        adminService.updateById(admin);
        return ResponseUtil.ok("修改成功！");
    }

    /**
     * 修改教学助理信息
     * @param teachingSecretary
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/adUser/edit/secretary")
    public Object secretaryEditSubmit(TeachingSecretary teachingSecretary) throws IOException{
        TeachingSecretary byId = teachingSecretaryService.findById(teachingSecretary.getId());
        teachingSecretary.setIsDel(byId.getIsDel());
        teachingSecretary.setAddTime(byId.getAddTime());
        teachingSecretaryService.updateById(teachingSecretary);
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

    /**
     * 添加用户类型
     * @param name
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/user/add")
    public Object userAddSubmit(String name) throws IOException{
        UserType userType = new UserType();
        userType.setName(name);
        userTypeService.add(userType);
        return ResponseUtil.ok("修改成功！");
    }

    /**
     * 添加学生
     * @param student
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/adUser/addStudent")
    public Object studentAddSubmit(Student student) throws IOException{
        student.setIsDel(false);
        studentService.add(student);
        return ResponseUtil.ok("修改成功");
    }

    /**
     * 添加教师
     * @param teacher
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/adUser/addTeacher")
    public Object studentAddSubmit(Teacher teacher) throws IOException{
        teacher.setIsDel(false);
        teacherService.add(teacher);
        return ResponseUtil.ok("修改成功");
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/adUser/addAdmin")
    public Object adminAddSubmit(Admin admin) throws IOException{
        admin.setIsDel(false);
        adminService.add(admin);
        return ResponseUtil.ok("修改成功");
    }

    /**
     * 添加教学助理
     * @param teachingSecretary
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/adUser/addSecretary")
    public Object secretaryAddSubmit(TeachingSecretary teachingSecretary) throws IOException{
        teachingSecretary.setIsDel(false);
        teachingSecretaryService.add(teachingSecretary);
        return ResponseUtil.ok("修改成功");
    }
}
