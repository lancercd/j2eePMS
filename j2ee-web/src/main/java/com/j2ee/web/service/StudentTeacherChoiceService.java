package com.j2ee.web.service;


import com.j2ee.db.domain.StuTeaCh;
import com.j2ee.db.dto.StuTeaChDto;
import com.j2ee.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;


/**
 * 学生选择指导老师
 */
@Service
public class StudentTeacherChoiceService {


    @Autowired
    private StuTeaChService stuTeaChService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdviserInfoService adviserInfoService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private AppraiseTeacherService appraiseTeacherService;

    @Autowired
    private DocumentService documentService;



    /**
     * 通过id查询
     * @param id id
     * @return StuTeaCh
     */
    public StuTeaCh findById(@NotNull Integer id) {
        return stuTeaChService.findById(id);
    }


    /**
     * 通过id查询
     * @param id id
     * @return StuTeaChDto
     */
    public StuTeaChDto findDtoById(@NotNull Integer id) {
        StuTeaCh stc = this.findById(id);
        return this.convertToDto(stc);
    }


    /**
     * entity convert to Dto
     * @param target StuTeaCh
     * @return StuTeaChDto
     */
    private StuTeaChDto convertToDto(StuTeaCh target){
        if(target == null) return null;

        StuTeaChDto el = new StuTeaChDto();
        el.setId(target.getId());
        el.setTeacher(teacherService.findById(target.getTeacherId()));
        el.setStudent(studentService.findById(target.getStudentId()));
        el.setAdviserInfo(adviserInfoService.findById(target.getAdviserInfo()));
        el.setSemester(semesterService.findById(target.getSemesterId()));
        el.setAppraiseTeacher(appraiseTeacherService.findById(target.getAppraiseId()));
        el.setIntro(target.getIntro());
        el.setSuggestion(target.getSuggestion());
        el.setScore(target.getScore());
        el.setDocument(documentService.findById(target.getDocumentId()));
        el.setIsAccept(target.getIsAccept());
        el.setIsDel(target.getIsDel());
        el.setAddTime(target.getAddTime());

        return el;
    }
}
