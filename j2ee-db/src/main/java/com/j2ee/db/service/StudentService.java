package com.j2ee.db.service;


import com.j2ee.db.dao.StudentMapper;
import com.j2ee.db.domain.Student;
import com.j2ee.db.domain.StudentExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 学生Service
 */
@Service
public class StudentService {


    /**
     * mapper of student
     */
    @Resource
    private StudentMapper studentMapper;


    /**
     * 通过id查询学生
     * @param id 学生id
     * @return stu
     */
    public Student findById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }



    /**
     * 查询所有学生信息(未删除的)
     * @return list of student
     */
    public List<Student> queryAll(){
        StudentExample example = new StudentExample();
        example.createCriteria().andIsDelEqualTo(false);

        return studentMapper.selectByExample(example);
    }


    /**
     * 添加学生
     * @param student Student
     * @return int
     */
    public int add(Student student) {
        student.setAddTime(LocalDateTime.now());

        return studentMapper.insert(student);
    }



    /**
     * 添加学生
     * @param student Student
     * @return int
     */
    public int insert(Student student) {
        return add(student);
    }



    /**
     * 添加学生
     * @param student Student
     * @return int
     */
    public int save(Student student) {
        return add(student);
    }


    public int delete(Integer id) {
        return studentMapper.logicalDeleteByPrimaryKey(id);
    }
}
