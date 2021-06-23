package com.j2ee.controller;

import com.j2ee.db.domain.*;
import com.j2ee.db.dto.AdviserInfoDto;
import com.j2ee.db.dto.StuTeaChDto;
import com.j2ee.db.service.*;
import com.j2ee.service.StudentTeacherChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/queryStatistics")
public class SearchStatistics {

    @Autowired
    private AdviserInfoService adviserInfoService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private StudentTeacherChoiceService studentTeacherChoiceService;


    @RequestMapping("/showStatistics")
    public Object showSemester(Integer semesterId,Integer docId,Model model){

        List<Semester> semesters = semesterService.queryAll();
        List<Document> documents = documentService.queryAll();
        List<Teacher> teachers = teacherService.queryAll();
        List<Student> students = studentService.queryAll();
        List<DocumentType> documentTypes = documentTypeService.queryAll();

        model.addAttribute("semesters",semesters);
        model.addAttribute("documents",documents);
        model.addAttribute("teachers",teachers);
        model.addAttribute("students",students);
        model.addAttribute("documentTypes",documentTypes);
        return "/searchStatistics";
    }

    @RequestMapping ("/queryStatisticsByCondition")
    public String queryStatisticsByCondition(Integer semesterId,Integer documentTypeId,Model model){
        Document serviceById = documentService.findById(documentTypeId);
        int docId = serviceById.getId();

        List<StuTeaChDto> stuTeaChDtos = studentTeacherChoiceService.selectStuTeachDtoBySemesterIdAndDocId(semesterId,docId);


        model.addAttribute("stuTeaChDtos",stuTeaChDtos);
        return "/queryStatisticsByCondition";
    }
}
