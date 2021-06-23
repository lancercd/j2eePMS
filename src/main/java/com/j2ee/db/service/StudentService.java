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
        return studentMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }
    /**
     * 通过id查询学生
     * @param list 学生id的list
     * @return stu
     */
    public List<Student> findById(List<Integer> list) {
        StudentExample example = new StudentExample();
        example.createCriteria().andIdIn(list).andLogicalDeleted(false);
        return studentMapper.selectByExample(example);
    }


    /**
     * 通过用户名查找
     * @param name 用户名
     * @return Student
     */
    public Student findByName(String name) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();

        criteria.andLogicalDeleted(false);
        criteria.andNameEqualTo(name);

        return studentMapper.selectOneByExample(example);
    }


    /**
     * 通过学号查找
     * @param number 学号
     * @return Student
     */
    public Student findByNumber(String number) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();

        criteria.andLogicalDeleted(false);
        criteria.andNumberEqualTo(number);

        return studentMapper.selectOneByExample(example);
    }

    public List<Student> findByNumberLike(String number) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();

        criteria.andLogicalDeleted(false);
        criteria.andNumberLike("%"+number+"%");

        return studentMapper.selectByExample(example);
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


    /**
     * 删除(逻辑删除)
     * @param id student id
     * @return int
     */
    public int delete(Integer id) {
        return studentMapper.logicalDeleteByPrimaryKey(id);
    }

    /**
     * 更新（按id）
     * @param student
     * @return
     */
    public int updateById(Student student){
        return studentMapper.updateByPrimaryKey(student);
    }

    public String queryStudentName(List<Integer> ids){
        String res = "";
        if(ids.size()==0) return res;
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andIdIn(ids);
        List<Student> students = studentMapper.selectByExample(studentExample);
        boolean flag  = false;
        for (Student temp:students){
            if (flag) res+=" , ";
            res+=temp.getName();
            flag = true;
        }
        return res;
    }
}
