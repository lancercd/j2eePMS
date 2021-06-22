package com.j2ee.db.service;

import com.j2ee.db.dao.DocumentMapper;
import com.j2ee.db.dao.UserTypeMapper;
import com.j2ee.db.domain.Document;
import com.j2ee.db.domain.DocumentExample;
import com.j2ee.db.domain.UserType;
import com.j2ee.db.domain.UserTypeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * userType基础操作
 */
@Service
public class UserTypeService {
    /**
     * mapper of userType
     */
    @Resource
    private UserTypeMapper userTypeMapper;

    /**
     * 通过id查询
     * @param id userTypeid
     * @return Document
     */
    public UserType findById(Integer id) {
        return userTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有用户信息
     * @return list of Document
     */
    public List<UserType> queryAll(){
        UserTypeExample example = new UserTypeExample();
        return userTypeMapper.selectByExample(example);
    }

    /**
     * 删除(逻辑删除)
     * @param id UserType id
     * @return int
     */
    public int delete(Integer id) {
        return userTypeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新userType（by id）
     * @param userType
     * @return
     */
    public int updateById(UserType userType){
        return userTypeMapper.updateByPrimaryKey(userType);
    }

    /**
     * 添加类型
     * @param userType 用户类型
     * @return int
     */
    public int add(UserType userType) {
        return userTypeMapper.insert(userType);
    }

}
