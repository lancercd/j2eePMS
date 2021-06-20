package com.j2ee.db.service;


import com.j2ee.db.dao.AdviserInfoMapper;
import com.j2ee.db.domain.AdviserInfo;
import com.j2ee.db.domain.AdviserInfoExample;
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

        return adviserInfoMapper.selectByExample(example);
    }




    /**
     * 查询所有指导老师信息
     * @return list of AdviserInfo
     */
    public List<AdviserInfo> queryAll(){
        AdviserInfoExample example = new AdviserInfoExample();

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
        adviserInfo.setReqInfo(null);
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



}
