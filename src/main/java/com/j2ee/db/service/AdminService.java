package com.j2ee.db.service;


import com.j2ee.db.dao.AdminMapper;
import com.j2ee.db.domain.Admin;
import com.j2ee.db.domain.AdminExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 管理员Service
 */
@Service
public class AdminService {


    /**
     * mapper of admin
     */
    @Resource
    private AdminMapper adminMapper;


    /**
     * 通过id查询管理员
     * @param id 管理员id
     * @return stu
     */
    public Admin findById(Integer id) {
        return adminMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }


    /**
     * 通过用户名查找
     * @param username 用户名
     * @return Admin
     */
    public Admin findByName(String username) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();

        criteria.andLogicalDeleted(false);
        criteria.andUsernameEqualTo(username);

        return adminMapper.selectOneByExample(example);
    }



    /**
     * 查询所有管理员信息
     * @return list of admin
     */
    public List<Admin> queryAll(){
        AdminExample example = new AdminExample();
        example.createCriteria().andIsDelEqualTo(false);

        return adminMapper.selectByExample(example);
    }


    /**
     * 添加管理员
     * @param admin Admin
     * @return int
     */
    public int add(Admin admin) {
        admin.setAddTime(LocalDateTime.now());

        return adminMapper.insert(admin);
    }



    /**
     * 添加管理员
     * @param admin Admin
     * @return int
     */
    public int insert(Admin admin) {
        return add(admin);
    }



    /**
     * 添加管理员
     * @param admin Admin
     * @return int
     */
    public int save(Admin admin) {
        return add(admin);
    }


    /**
     * 删除(逻辑删除)
     * @param id admin id
     * @return int
     */
    public int delete(Integer id) {
        return adminMapper.logicalDeleteByPrimaryKey(id);
    }

    
}
