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
    public StuTeaCh findStuTeachById(@NotNull Integer id) {
        return stuTeaChService.findById(id);
    }


    /**
     * 通过id查询
     * @param id id
     * @return StuTeaChDto
     */
    public StuTeaChDto findStuTeachDtoById(@NotNull Integer id) {
        StuTeaCh stc = this.findStuTeachById(id);
        return convertor.convertToStuTeaChDto(stc);
    }



    public int delStuTeaCh(@NotNull Integer stuTeaChId){
        return stuTeaChService.delete(stuTeaChId);
    }


    /**
     * 通过学生id查询
     * @param stuId 学生id
     * @return list of StuTeaChDto
     */
    public List<StuTeaChDto> selectStuTeachDtoByStuId(@NotNull Integer stuId) {
        List<StuTeaCh> stuTeaChes = stuTeaChService.queryByStuId(stuId);
        List<StuTeaChDto> stuTeaChDtos = new ArrayList<StuTeaChDto>(stuTeaChes.size());
        for (StuTeaCh ch : stuTeaChes) {
            stuTeaChDtos.add(convertor.convertToStuTeaChDto(ch));
        }

        return stuTeaChDtos;
    }



    /**
     * 通过学期id 文档id查询
     * @param semesterId 学期id
     * @param docId      文档
     * @return list of StuTeaChDto
     */
    public List<StuTeaChDto> selectStuTeachDtoBySemesterIdAndDocId(@NotNull Integer semesterId, @NotNull Integer docId) {
        List<StuTeaCh> stuTeaChes = stuTeaChService.queryBySemesterIdDocId(semesterId, docId);
        List<StuTeaChDto> stuTeaChDtos = new ArrayList<StuTeaChDto>(stuTeaChes.size());
        for (StuTeaCh ch : stuTeaChes) {
            stuTeaChDtos.add(convertor.convertToStuTeaChDto(ch));
        }

        return stuTeaChDtos;
    }


    /**
     * 通过学生id查询
     * @param stuId 学生id
     * @return list of StuTeaChDto
     */
    public List<StuTeaChDto> selectStuTeachDtoByStuIdAndSemester(@NotNull Integer stuId, @NotNull Integer semesterId) {
        List<StuTeaCh> stuTeaChes = stuTeaChService.queryByStuIdAndSemester(stuId, semesterId);
        List<StuTeaChDto> stuTeaChDtos = new ArrayList<StuTeaChDto>(stuTeaChes.size());
        for (StuTeaCh ch : stuTeaChes) {
            stuTeaChDtos.add(convertor.convertToStuTeaChDto(ch));
        }

        return stuTeaChDtos;
    }


    /**
     * 通过指导老师id查找
     * @param adviserId 指导老师id
     * @return 学生选择指导老师信息
     */
    public StuTeaCh findStuTeachByStuIdAndTeaId(Integer adviserId) {
        return stuTeaChService.queryByAdviserId(adviserId);
    }



    /**
     * 添加学生选择指导老师信息
     * @param adviserInfo   指导老师信息
     * @param stuId         学生id
     * @return int
     */
    public int addSelectInfo(AdviserInfo adviserInfo, @NotNull Integer stuId){
        StuTeaCh stuTeaCh = new StuTeaCh();
        stuTeaCh.setTeacherId(adviserInfo.getTeacherId());
        stuTeaCh.setStudentId(stuId);
        stuTeaCh.setAdviserInfo(adviserInfo.getId());
        stuTeaCh.setSemesterId(adviserInfo.getSemesterId());

        return stuTeaChService.add(stuTeaCh);
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
     * @return list of adviserInfo
     */
    public List<AdviserInfoDto> getAllStuTeaChAll(){
        List<AdviserInfo> adviserInfos = adviserInfoService.queryAll();
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
