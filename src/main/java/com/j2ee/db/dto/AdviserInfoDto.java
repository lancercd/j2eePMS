package com.j2ee.db.dto;


import com.j2ee.db.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 指导老师信息dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdviserInfoDto {

    /**
     * id
     */
    Integer id;

    /**
     * 指导老师
     */
    Teacher teacher;


    /**
     * 学期
     */
    Semester semester;


    /**
     * 资料类型
     */
    DocumentType docType;


    /**
     * 要求信息
     */
    String ReqInfo;


    /**
     * 是否接受
     */
    Byte isAccept;


    /**
     * 是否逻辑删除
     */
    Boolean isDel;


    /**
     * 添加时间
     */
    LocalDateTime addTime;

}
