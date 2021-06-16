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

}
