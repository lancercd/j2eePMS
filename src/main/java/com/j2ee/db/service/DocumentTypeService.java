package com.j2ee.db.service;


import com.j2ee.db.dao.DocumentTypeMapper;
import com.j2ee.db.domain.DocumentType;
import com.j2ee.db.domain.DocumentTypeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 文档类型service  基础操作
 */
@Service
public class DocumentTypeService {


    /**
     * mapper of docType
     */
    @Resource
    private DocumentTypeMapper documentTypeMapper;


    /**
     * 通过id查询
     * @param id 文档资料id
     * @return DocumentType
     */
    public DocumentType findById(Integer id) {
        return documentTypeMapper.selectByPrimaryKey(id);
    }

    public List<DocumentType> findByIdLike(String id) {
        DocumentTypeExample documentTypeExample = new DocumentTypeExample();
        documentTypeExample.createCriteria().andNameLike("%"+id+"%");
        return documentTypeMapper.selectByExample(documentTypeExample);
    }


    /**
     * 查询所有类型信息
     * @return list of DocumentType
     */
    public List<DocumentType> queryAll(){
        DocumentTypeExample example = new DocumentTypeExample();

        return documentTypeMapper.selectByExample(example);
    }



    /**
     * 添加类型
     * @param doc document
     * @return int
     */
    public int add(DocumentType doc) {

        return documentTypeMapper.insert(doc);
    }


    /**
     * 删除(逻辑删除)
     * @param id DocumentType id
     * @return int
     */
    public int delete(Integer id) {
        return documentTypeMapper.deleteByPrimaryKey(id);
    }

    public int updateById(DocumentType type){
        return documentTypeMapper.updateByPrimaryKey(type);
    }
}
