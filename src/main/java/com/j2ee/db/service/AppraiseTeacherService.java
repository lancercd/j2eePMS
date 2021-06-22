package com.j2ee.db.service;


import com.j2ee.db.dao.AppraiseTeacherMapper;
import com.j2ee.db.domain.AppraiseTeacher;
import com.j2ee.db.domain.AppraiseTeacherExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 评阅老师信息service
 */
@Service
public class AppraiseTeacherService {


    /**
     * mapper of appraiseTeacher
     */
    @Resource
    private AppraiseTeacherMapper appraiseTeacherMapper;




    /**
     * 通过id查找
     * @param id 评阅老师信息id
     * @return AppraiseTeacher
     */
    public AppraiseTeacher findById(Integer id) {
        return appraiseTeacherMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }



    /**
     * 通过isAccept查找
     * @param isAccept 接受信息
     * @return list of AppraiseTeacher
     */
    public List<AppraiseTeacher> queryByIsAccept(Integer isAccept){
        AppraiseTeacherExample example = new AppraiseTeacherExample();
        AppraiseTeacherExample.Criteria criteria = example.createCriteria();
        criteria.andLogicalDeleted(false);
        criteria.andIsAcceptEqualTo(isAccept.byteValue());

        return appraiseTeacherMapper.selectByExample(example);
    }




    /**
     * 查询所有评阅老师信息
     * @return list of AppraiseTeacher
     */
    public List<AppraiseTeacher> queryAll(){
        AppraiseTeacherExample example = new AppraiseTeacherExample();

        return appraiseTeacherMapper.selectByExample(example);
    }

    public List<AppraiseTeacher> queryAll(Integer id){
        AppraiseTeacherExample example = new AppraiseTeacherExample();
        example.createCriteria().andTeacherIdNotEqualTo(id);
        return appraiseTeacherMapper.selectByExample(example);
    }



    /**
     * 添加评阅老师信息
     * @param appraiseTeacher 评阅老师信息
     * @return int
     */
    public int add(AppraiseTeacher appraiseTeacher) {
        appraiseTeacher.setAddTime(LocalDateTime.now());
        appraiseTeacher.setIsAccept((byte)0);
        appraiseTeacher.setIsDel(false);
        appraiseTeacher.setSuggestion(null);
        appraiseTeacher.setScore(null);


        return appraiseTeacherMapper.insert(appraiseTeacher);
    }



    /**
     * 更新评阅老师信息通过id
     *                  只更新参数中不为空的字段
     * @param appraiseTeacher 需要更新的评阅老师信息
     * @return int
     */
    public int updateSelective(AppraiseTeacher appraiseTeacher){
        Integer id = appraiseTeacher.getId();
        if (id == null || id == 0) {
            return 0;
        }

        return appraiseTeacherMapper.updateByPrimaryKeySelective(appraiseTeacher);
    }



    /**
     * 更新评阅老师信息通过id
     *                  若参数中的某个属性为null 则数据库中相应字段也更新为null
     * @param appraiseTeacher 需要更新的评阅老师信息
     * @return int
     */
    public int update(AppraiseTeacher appraiseTeacher){
        Integer id = appraiseTeacher.getId();
        if (id == null || id == 0) {
            return 0;
        }

        return appraiseTeacherMapper.updateByPrimaryKey(appraiseTeacher);
    }



    /**
     * 通过id删除
     *      逻辑删除
     * @param id 评阅老师信息id
     * @return int
     */
    public int delete(Integer id) {
        return appraiseTeacherMapper.logicalDeleteByPrimaryKey(id);
    }



}
