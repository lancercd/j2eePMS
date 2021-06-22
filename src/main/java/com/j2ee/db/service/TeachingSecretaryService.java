package com.j2ee.db.service;


import com.j2ee.db.dao.TeachingSecretaryMapper;
import com.j2ee.db.domain.TeachingSecretary;
import com.j2ee.db.domain.TeachingSecretaryExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 教学秘书Service
 */
@Service
public class TeachingSecretaryService {


    /**
     * mapper of teachingSecretary
     */
    @Resource
    private TeachingSecretaryMapper teachingSecretaryMapper;


    /**
     * 通过id查询教学秘书
     * @param id 教学秘书id
     * @return stu
     */
    public TeachingSecretary findById(Integer id) {
        return teachingSecretaryMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }


    /**
     * 通过用户名查找
     * @param username 用户名
     * @return TeachingSecretary
     */
    public TeachingSecretary findByName(String username) {
        TeachingSecretaryExample example = new TeachingSecretaryExample();
        TeachingSecretaryExample.Criteria criteria = example.createCriteria();

        criteria.andLogicalDeleted(false);
        criteria.andUsernameEqualTo(username);

        return teachingSecretaryMapper.selectOneByExample(example);
    }



    /**
     * 查询所有教学秘书信息
     * @return list of teachingSecretary
     */
    public List<TeachingSecretary> queryAll(){
        TeachingSecretaryExample example = new TeachingSecretaryExample();
        example.createCriteria().andIsDelEqualTo(false);

        return teachingSecretaryMapper.selectByExample(example);
    }


    /**
     * 添加教学秘书
     * @param teachingSecretary TeachingSecretary
     * @return int
     */
    public int add(TeachingSecretary teachingSecretary) {
        teachingSecretary.setAddTime(LocalDateTime.now());

        return teachingSecretaryMapper.insert(teachingSecretary);
    }



    /**
     * 添加教学秘书
     * @param teachingSecretary TeachingSecretary
     * @return int
     */
    public int insert(TeachingSecretary teachingSecretary) {
        return add(teachingSecretary);
    }



    /**
     * 添加教学秘书
     * @param teachingSecretary TeachingSecretary
     * @return int
     */
    public int save(TeachingSecretary teachingSecretary) {
        return add(teachingSecretary);
    }


    /**
     * 删除(逻辑删除)
     * @param id teachingSecretary id
     * @return int
     */
    public int delete(Integer id) {
        return teachingSecretaryMapper.logicalDeleteByPrimaryKey(id);
    }

    /**
     * 更新教学助理（by id）
     * @param teachingSecretary
     * @return
     */
    public int updateById(TeachingSecretary teachingSecretary){
        return teachingSecretaryMapper.updateByPrimaryKey(teachingSecretary);
    }
}
