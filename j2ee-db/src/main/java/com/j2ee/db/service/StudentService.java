package com.j2ee.db.service;


import com.j2ee.db.dao.StudentMapper;
import com.j2ee.db.domain.Student;
import com.j2ee.db.domain.StudentExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 学生Service
 */
@Service
public class StudentService {

    @Resource
    private StudentMapper studentMapper;


    public List<Student> queryAll(){
        StudentExample example = new StudentExample();

        return studentMapper.selectByExample(example);
    }
}
