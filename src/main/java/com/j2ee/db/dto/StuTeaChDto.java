package com.j2ee.db.dto;


import com.j2ee.db.domain.*;
import com.j2ee.dto.AppraiseTeacherDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//非数据库表实体

/**
 * 学生选择指导老师dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuTeaChDto {

    /**
     * id
     */
    Integer id;

    /**
     * 指导老师
     */
    Teacher teacher;

    /**
     * 学生
     */
    Student student;

    /**
     * 指导老师信息
     */
    AdviserInfo adviserInfo;

    /**
     * 学期
     */
    Semester semester;

    /**
     * 评阅老师
     */
    AppraiseTeacher appraiseTeacher;

    /**
     * 评阅老师
     */
    AppraiseTeacherDto appTeacherDto;


    /**
     * 学生自我介绍
     */
    String intro;


    /**
     * 评阅建议
     */
    String suggestion;


    /**
     * 评阅分数
     */
    Integer score;


    /**
     * 学生上传的资料
     */
    Document document;


    /**
     * 老师是否接受成为导师
     *      0等待回复  1同意  -1不同意
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
