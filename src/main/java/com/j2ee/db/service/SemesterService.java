package com.j2ee.db.service;

import com.j2ee.db.dao.SemesterMapper;
import com.j2ee.db.domain.Semester;
import com.j2ee.db.domain.SemesterExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 学期service
 */
@Service
public class SemesterService {


    /**
     * mapper of semester
     */
    @Resource
    private SemesterMapper semesterMapper;


    /**
     * 通过id查新
     * @param id 学期id
     * @return semester
     */
    public Semester findById(Integer id){
        return semesterMapper.selectByPrimaryKey(id);
    }


    /**
     * 查询所有学期信息
     * @return list of Semester
     */
    public List<Semester> queryAll(){
        SemesterExample example = new SemesterExample();
        return semesterMapper.selectByExample(example);
    }


    /**
     * 添加学期
     * @param semester 学期信息
     * @return int
     */
    public int add(Semester semester){
        return semesterMapper.insert(semester);
    }


    /**
     * 通过id删除学期
     * @param id 学期id
     * @return int
     */
    public int deleteById(Integer id) {
        return semesterMapper.deleteByPrimaryKey(id);
    }


    public int updateById(Semester semester){
        return semesterMapper.updateByPrimaryKey(semester);
    }

    /**
     * 获取当前学期
     * @return int
     */
    public int getSemesterIdNow() {
        SemesterExample example = new SemesterExample();
        example.orderBy("id desc");
        List<Semester> semesters = semesterMapper.selectByExample(example);
        if (semesters.size() == 0) {
            return 0;
        }
        return semesters.get(0).getId();
    }

}
