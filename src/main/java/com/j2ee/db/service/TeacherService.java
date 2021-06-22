package com.j2ee.db.service;


import com.j2ee.db.dao.TeacherMapper;
import com.j2ee.db.domain.Teacher;
import com.j2ee.db.domain.TeacherExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 老师Service
 */
@Service
public class TeacherService {


    /**
     * mapper of teacher
     */
    @Resource
    private TeacherMapper teacherMapper;


    /**
     * 通过id查询老师
     * @param id 老师id
     * @return teacher
     */
    public Teacher findById(Integer id) {
        return teacherMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }


    /**
     * 通过用户名查找
     * @param name 用户名
     * @return Teacher
     */
    public Teacher findByName(String name) {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();

        criteria.andLogicalDeleted(false);
        criteria.andNameEqualTo(name);

        return teacherMapper.selectOneByExample(example);
    }


    /**
     * 通过编号查找
     * @param number 编号
     * @return Teacher
     */
    public Teacher findByNumber(String number) {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();

        criteria.andLogicalDeleted(false);
        criteria.andNumberEqualTo(number);

        return teacherMapper.selectOneByExample(example);
    }


    /**
     * 查询所有老师信息(未删除的)
     * @return list of teacher
     */
    public List<Teacher> queryAll(){
        TeacherExample example = new TeacherExample();
        example.createCriteria().andIsDelEqualTo(false);

        return teacherMapper.selectByExample(example);
    }



    /**
     * 添加老师
     * @param teacher Teacher
     * @return int
     */
    public int add(Teacher teacher) {
        teacher.setAddTime(LocalDateTime.now());

        return teacherMapper.insert(teacher);
    }



    /**
     * 添加老师
     * @param teacher Teacher
     * @return int
     */
    public int insert(Teacher teacher) {
        return add(teacher);
    }



    /**
     * 添加老师
     * @param teacher Teacher
     * @return int
     */
    public int save(Teacher teacher) {
        return add(teacher);
    }



    /**
     * 删除(逻辑删除)
     * @param id teacher id
     * @return int
     */
    public int delete(Integer id) {
        return teacherMapper.logicalDeleteByPrimaryKey(id);
    }

    /**
<<<<<<< HEAD
     * 修改教师信息（by id）
     * @param teacher
     * @return
     */
    public int updateById(Teacher teacher){
        return teacherMapper.updateByPrimaryKey(teacher);
=======
     * 查询评阅老师
     * @param id 指导老师id
     * @return int
     */
    public List<Teacher> queryAppraiser(Integer id){
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andLogicalDeleted(false).andIdNotEqualTo(id);
        return teacherMapper.selectByExample(teacherExample);
>>>>>>> c1a0e8d0f002553964b231aa9bb1ffcb5c25bc42
    }
}
