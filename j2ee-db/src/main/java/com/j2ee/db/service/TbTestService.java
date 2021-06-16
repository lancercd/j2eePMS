package com.j2ee.db.service;


import com.j2ee.db.dao.TbTestMapper;
import com.j2ee.db.domain.TbTest;
import com.j2ee.db.domain.TbTestExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbTestService {

    @Resource
    private TbTestMapper tbTestMapper;


    public int add(TbTest el) {
        return tbTestMapper.insert(el);
    }


    public List<TbTest> querySelect(){
        TbTestExample example = new TbTestExample();

        TbTestExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(1);

        return tbTestMapper.selectByExample(example);
    }


}
