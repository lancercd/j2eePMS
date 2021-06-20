package com.j2ee.db.utils;

import com.j2ee.db.domain.AdviserInfo;
import com.j2ee.db.domain.StuTeaCh;
import com.j2ee.db.dto.AdviserInfoDto;
import com.j2ee.db.dto.StuTeaChDto;
import com.j2ee.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Convertor {

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

    @Autowired
    private DocumentTypeService documentTypeService;


    /**
     * entity convert to Dto
     * @param target StuTeaCh
     * @return StuTeaChDto
     */
    public StuTeaChDto convertToStuTeaChDto(StuTeaCh target){
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



    public AdviserInfoDto convertToAdviserInfoDto(AdviserInfo adviserInfo) {
        if(adviserInfo == null) return null;

        AdviserInfoDto el = new AdviserInfoDto();
        el.setId(adviserInfo.getId());
        el.setTeacher(teacherService.findById(adviserInfo.getTeacherId()));
        el.setSemester(semesterService.findById(adviserInfo.getSemesterId()));
        el.setDocType(documentTypeService.findById(adviserInfo.getDocTypeId()));
        el.setReqInfo(adviserInfo.getReqInfo());
        el.setIsAccept(adviserInfo.getIsAccept());
        el.setIsDel(adviserInfo.getIsDel());
        el.setAddTime(adviserInfo.getAddTime());
        return el;
    }
}
