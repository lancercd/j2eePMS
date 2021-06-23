package com.j2ee.service;


import com.j2ee.db.domain.AppraiseTeacher;
import com.j2ee.db.service.AppraiseTeacherService;
import com.j2ee.db.service.StuTeaChService;
import com.j2ee.db.service.TeacherService;
import com.j2ee.db.utils.Convertor;
import com.j2ee.dto.AppraiseTeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppraiseTeacherDtoService {

    @Autowired
    private AppraiseTeacherService appraiseTeacherService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StuTeaChService stuTeaChService;

    @Autowired
    private Convertor convertor;



    public AppraiseTeacherDto findById(Integer id) {
        return convertor.convertToAppraiseTeacherDto(appraiseTeacherService.findById(id));
    }

}
