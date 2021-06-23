package com.j2ee.db.service;


import com.j2ee.db.dao.AdviserInfoMapper;
import com.j2ee.db.domain.AdviserInfo;
import com.j2ee.db.domain.AdviserInfoExample;
import com.j2ee.db.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 指导老师信息service
 */
@Service
public class AdviserInfoService {


    /**
     * mapper of adviserInfo
     */
    @Resource
    private AdviserInfoMapper adviserInfoMapper;




    /**
     * 通过id查找
     * @param id 指导老师信息id
     * @return AdviserInfo
     */
    public AdviserInfo findById(Integer id) {
        return adviserInfoMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }



    /**
     * 通过isAccept查找
     * @param isAccept 接受信息
     * @return list of AdviserInfo
     */
    public List<AdviserInfo> queryByIsAccept(Integer isAccept){
        AdviserInfoExample example = new AdviserInfoExample();
        AdviserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andLogicalDeleted(false);
        criteria.andIsAcceptEqualTo(isAccept.byteValue());
        example.orderBy("add_time DESC");

        return adviserInfoMapper.selectByExample(example);
    }



    /**
     * 通过学期id查找已接受的信息
     * @return list of AdviserInfo
     */
    public List<AdviserInfo> queryBySemesterId(Integer semesterId){
        AdviserInfoExample example = new AdviserInfoExample();
        AdviserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andLogicalDeleted(false);
        criteria.andIsAcceptEqualTo((byte)1);
        criteria.andSemesterIdEqualTo(semesterId);
        example.orderBy("add_time DESC");

        return adviserInfoMapper.selectByExample(example);
    }




    /**
     * 查询所有指导老师信息
     * @return list of AdviserInfo
     */
    public List<AdviserInfo> queryAll(){
        AdviserInfoExample example = new AdviserInfoExample();
        example.createCriteria().andIsDelEqualTo(false);
        example.orderBy("add_time DESC");

        return adviserInfoMapper.selectByExample(example);
    }




    /**
     * 添加指导老师信息
     * @param adviserInfo 指导老师信息
     * @return int
     */
    public int add(AdviserInfo adviserInfo) {
        adviserInfo.setAddTime(LocalDateTime.now());
        adviserInfo.setIsAccept((byte)0);
        adviserInfo.setReqInfo(adviserInfo.getReqInfo());
        adviserInfo.setIsDel(false);


        return adviserInfoMapper.insert(adviserInfo);
    }



    /**
     * 更新指导老师信息通过id
     *                  只更新参数中不为空的字段
     * @param adviserInfo 需要更新的指导老师信息
     * @return int
     */
    public int updateSelective(AdviserInfo adviserInfo){
        Integer id = adviserInfo.getId();
        if (id == null || id == 0) {
            return 0;
        }

        return adviserInfoMapper.updateByPrimaryKeySelective(adviserInfo);
    }



    /**
     * 更新指导老师信息通过id
     *                  若参数中的某个属性为null 则数据库中相应字段也更新为null
     * @param adviserInfo 需要更新的指导老师信息
     * @return int
     */
    public int update(AdviserInfo adviserInfo){
        Integer id = adviserInfo.getId();
        if (id == null || id == 0) {
            return 0;
        }

        return adviserInfoMapper.updateByPrimaryKey(adviserInfo);
    }

    /**
     * 通过id删除
     *      逻辑删除
     * @param id 指导老师信息id
     * @return int
     */
    public int delete(Integer id) {
        return adviserInfoMapper.logicalDeleteByPrimaryKey(id);
    }


    public int agreeAdviser(Integer adviserId){
        AdviserInfo adviserInfo = new AdviserInfo();
        adviserInfo.setIsAccept((byte)1);
        adviserInfo.setId(adviserId);
        return adviserInfoMapper.updateByPrimaryKeySelective(adviserInfo);
    }

    public int disagreeAdviser(Integer adviserId){
        AdviserInfo adviserInfo = new AdviserInfo();
        adviserInfo.setIsAccept((byte)-1);
        adviserInfo.setId(adviserId);
        return adviserInfoMapper.updateByPrimaryKeySelective(adviserInfo);
    }

    public List<AdviserInfo> queryAdviserInfoBySemesterId(Integer semesterId){
        AdviserInfoExample example = new AdviserInfoExample();
        AdviserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andSemesterIdEqualTo(semesterId);
        criteria.andLogicalDeleted(false);
        example.orderBy("add_time DESC");
        return adviserInfoMapper.selectByExample(example);
    }

    public Integer updateReqInfo(Integer teacherId,Integer semesterId,String reqInfo){
        AdviserInfoExample example = new AdviserInfoExample();
        AdviserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andSemesterIdEqualTo(semesterId).andTeacherIdEqualTo(teacherId);
        AdviserInfo adviserInfo = new AdviserInfo();
        adviserInfo.setReqInfo(reqInfo);
        return adviserInfoMapper.updateByExampleSelective(adviserInfo,example);
    }
    public AdviserInfo queryAdviserInfo(Integer teacherId,Integer semesterId){
        AdviserInfoExample example = new AdviserInfoExample();
        AdviserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andTeacherIdEqualTo(teacherId).andSemesterIdEqualTo(semesterId);
        return adviserInfoMapper.selectOneByExample(example);

    }
}
