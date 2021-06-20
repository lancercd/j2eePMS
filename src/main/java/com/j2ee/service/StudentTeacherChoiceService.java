package com.j2ee.service;


import com.j2ee.db.domain.AdviserInfo;
import com.j2ee.db.domain.Semester;
import com.j2ee.db.domain.StuTeaCh;
import com.j2ee.db.dto.AdviserInfoDto;
import com.j2ee.db.dto.StuTeaChDto;
import com.j2ee.db.service.*;
import com.j2ee.db.utils.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
 * 学生选择指导老师
 */
@Service
public class StudentTeacherChoiceService {


    @Autowired
    private Convertor convertor;

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
        return convertor.convertToStuTeaChDto(stc);
    }



    /**
     * 查询所有学期信息
     * @return list of Semester
     */
    public List<Semester> getAllSemester(){
        return semesterService.queryAll();
    }


    /**
     * 获取所有已接受的导师信息
     * @return list of adviserInfo
     */
    public List<AdviserInfoDto> getAllStuTeaCh(){
        List<AdviserInfo> adviserInfos = adviserInfoService.queryByIsAccept(1);
        return this.convertAdviserInfo(adviserInfos);
    }


    /**
     * 获取所有已接受的导师信息
     * @return list of adviserInfoDto
     */
    public List<AdviserInfoDto> getAllStuTeaChBySemester(Integer semesterId){
        List<AdviserInfo> adviserInfos = adviserInfoService.queryBySemesterId(semesterId);

        return this.convertAdviserInfo(adviserInfos);
    }

    /**
     * list转换
     * @param adviserInfos 指导老师信息
     * @return list of adviserInfoDto
     */
    private List<AdviserInfoDto> convertAdviserInfo(List<AdviserInfo> adviserInfos) {
        if(adviserInfos == null) return null;

        ArrayList<AdviserInfoDto> dtoInfos = new ArrayList<>(adviserInfos.size());
        for (AdviserInfo info : adviserInfos) {
            dtoInfos.add(convertor.convertToAdviserInfoDto(info));
        }
        return dtoInfos;
    }



}
