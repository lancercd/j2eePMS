package com.j2ee.db.dao;

import com.j2ee.db.domain.StuTeaCh;
import com.j2ee.db.domain.StuTeaChExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuTeaChMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    long countByExample(StuTeaChExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    int deleteByExample(StuTeaChExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    int insert(StuTeaCh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    int insertSelective(StuTeaCh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    StuTeaCh selectOneByExample(StuTeaChExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    StuTeaCh selectOneByExampleSelective(@Param("example") StuTeaChExample example, @Param("selective") StuTeaCh.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<StuTeaCh> selectByExampleSelective(@Param("example") StuTeaChExample example, @Param("selective") StuTeaCh.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    List<StuTeaCh> selectByExample(StuTeaChExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    StuTeaCh selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") StuTeaCh.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    StuTeaCh selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    StuTeaCh selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StuTeaCh record, @Param("example") StuTeaChExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StuTeaCh record, @Param("example") StuTeaChExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StuTeaCh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StuTeaCh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") StuTeaChExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu_tea_ch
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}