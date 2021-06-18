package com.j2ee.db.service;


import com.j2ee.db.dao.StuTeaChMapper;
import com.j2ee.db.domain.StuTeaCh;
import com.j2ee.db.domain.StuTeaChExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 学生选择导师service
 */
@Service
public class StuTeaChService {


    /**
     * mapper of stuTeaCh
     */
    @Resource
    private StuTeaChMapper stuTeaChMapper;




    /**
     * 通过id查找
     * @param id 学生选择导师信息id
     * @return StuTeaCh
     */
    public StuTeaCh findById(Integer id) {
        return stuTeaChMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }



    /**
     * 通过isAccept查找
     * @param isAccept 接受信息
     * @return list of StuTeaCh
     */
    public List<StuTeaCh> queryByIsAccept(Integer isAccept){
        StuTeaChExample example = new StuTeaChExample();
        StuTeaChExample.Criteria criteria = example.createCriteria();
        criteria.andLogicalDeleted(false);
        criteria.andIsAcceptEqualTo(isAccept.byteValue());

        return stuTeaChMapper.selectByExample(example);
    }




    /**
     * 查询所有学生选择导师信息
     * @return list of StuTeaCh
     */
    public List<StuTeaCh> queryAll(){
        StuTeaChExample example = new StuTeaChExample();
        example.or().andLogicalDeleted(false);

        return stuTeaChMapper.selectByExample(example);
    }




    /**
     * 添加学生选择导师信息
     * @param stuTeaCh 学生选择导师信息
     * @return int
     */
    public int add(StuTeaCh stuTeaCh) {
        stuTeaCh.setAddTime(LocalDateTime.now());
        stuTeaCh.setAppraiseId(null);
        stuTeaCh.setIntro(null);
        stuTeaCh.setSuggestion(null);
        stuTeaCh.setScore(null);
        stuTeaCh.setDocumentId(null);
        stuTeaCh.setIsAccept((byte)0);
        stuTeaCh.setIsDel(false);


        return stuTeaChMapper.insert(stuTeaCh);
    }



    /**
     * 更新学生选择导师信息通过id
     *                  只更新参数中不为空的字段
     * @param stuTeaCh 需要更新的学生选择导师信息
     * @return int
     */
    public int updateSelective(StuTeaCh stuTeaCh){
        Integer id = stuTeaCh.getId();
        if (id == null || id == 0) {
            return 0;
        }

        return stuTeaChMapper.updateByPrimaryKeySelective(stuTeaCh);
    }



    /**
     * 更新学生选择导师信息通过id
     *                  若参数中的某个属性为null 则数据库中相应字段也更新为null
     * @param stuTeaCh 需要更新的学生选择导师信息
     * @return int
     */
    public int update(StuTeaCh stuTeaCh){
        Integer id = stuTeaCh.getId();
        if (id == null || id == 0) {
            return 0;
        }

        return stuTeaChMapper.updateByPrimaryKey(stuTeaCh);
    }
    



    /**
     * 通过id删除
     *      逻辑删除
     * @param id 学生选择导师信息id
     * @return int
     */
    public int delete(Integer id) {
        return stuTeaChMapper.logicalDeleteByPrimaryKey(id);
    }



}
