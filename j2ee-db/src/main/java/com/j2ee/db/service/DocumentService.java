package com.j2ee.db.service;


import com.j2ee.db.dao.DocumentMapper;
import com.j2ee.db.domain.Document;
import com.j2ee.db.domain.DocumentExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 文档service  基础操作
 */
@Service
public class DocumentService {


    /**
     * mapper of document
     */
    @Resource
    private DocumentMapper documentMapper;


    /**
     * 通过id查询
     * @param id 文档id
     * @return Document
     */
    public Document findById(Integer id) {
        return documentMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }



    /**
     * 查询所有文档信息
     * @return list of Document
     */
    public List<Document> queryAll(){
        DocumentExample example = new DocumentExample();
        example.or().andLogicalDeleted(false);

        return documentMapper.selectByExample(example);
    }



    /**
     * 添加类型
     * @param doc document
     * @return int
     */
    public int add(Document doc) {
        doc.setStatus((byte)0);
        doc.setAddTime(LocalDateTime.now());

        return documentMapper.insert(doc);
    }


    /**
     * 删除(逻辑删除)
     * @param id Document id
     * @return int
     */
    public int delete(Integer id) {
        return documentMapper.logicalDeleteByPrimaryKey(id);
    }



}
