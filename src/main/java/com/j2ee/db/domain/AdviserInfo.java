package com.j2ee.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class AdviserInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table adviser_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static final Boolean NOT_DELETED = false;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table adviser_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static final Boolean IS_DELETED = true;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column adviser_info.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column adviser_info.teacher_id
     *
     * @mbg.generated
     */
    private Integer teacherId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column adviser_info.semester_id
     *
     * @mbg.generated
     */
    private Integer semesterId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column adviser_info.doc_type_id
     *
     * @mbg.generated
     */
    private Integer docTypeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column adviser_info.req_info
     *
     * @mbg.generated
     */
    private String reqInfo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column adviser_info.is_accept
     *
     * @mbg.generated
     */
    private Byte isAccept;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column adviser_info.is_del
     *
     * @mbg.generated
     */
    private Boolean isDel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column adviser_info.add_time
     *
     * @mbg.generated
     */
    private LocalDateTime addTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column adviser_info.id
     *
     * @return the value of adviser_info.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column adviser_info.id
     *
     * @param id the value for adviser_info.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column adviser_info.teacher_id
     *
     * @return the value of adviser_info.teacher_id
     *
     * @mbg.generated
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column adviser_info.teacher_id
     *
     * @param teacherId the value for adviser_info.teacher_id
     *
     * @mbg.generated
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column adviser_info.semester_id
     *
     * @return the value of adviser_info.semester_id
     *
     * @mbg.generated
     */
    public Integer getSemesterId() {
        return semesterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column adviser_info.semester_id
     *
     * @param semesterId the value for adviser_info.semester_id
     *
     * @mbg.generated
     */
    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column adviser_info.doc_type_id
     *
     * @return the value of adviser_info.doc_type_id
     *
     * @mbg.generated
     */
    public Integer getDocTypeId() {
        return docTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column adviser_info.doc_type_id
     *
     * @param docTypeId the value for adviser_info.doc_type_id
     *
     * @mbg.generated
     */
    public void setDocTypeId(Integer docTypeId) {
        this.docTypeId = docTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column adviser_info.req_info
     *
     * @return the value of adviser_info.req_info
     *
     * @mbg.generated
     */
    public String getReqInfo() {
        return reqInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column adviser_info.req_info
     *
     * @param reqInfo the value for adviser_info.req_info
     *
     * @mbg.generated
     */
    public void setReqInfo(String reqInfo) {
        this.reqInfo = reqInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column adviser_info.is_accept
     *
     * @return the value of adviser_info.is_accept
     *
     * @mbg.generated
     */
    public Byte getIsAccept() {
        return isAccept;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column adviser_info.is_accept
     *
     * @param isAccept the value for adviser_info.is_accept
     *
     * @mbg.generated
     */
    public void setIsAccept(Byte isAccept) {
        this.isAccept = isAccept;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column adviser_info.is_del
     *
     * @return the value of adviser_info.is_del
     *
     * @mbg.generated
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column adviser_info.is_del
     *
     * @param isDel the value for adviser_info.is_del
     *
     * @mbg.generated
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column adviser_info.add_time
     *
     * @return the value of adviser_info.add_time
     *
     * @mbg.generated
     */
    public LocalDateTime getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column adviser_info.add_time
     *
     * @param addTime the value for adviser_info.add_time
     *
     * @mbg.generated
     */
    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adviser_info
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", semesterId=").append(semesterId);
        sb.append(", docTypeId=").append(docTypeId);
        sb.append(", reqInfo=").append(reqInfo);
        sb.append(", isAccept=").append(isAccept);
        sb.append(", isDel=").append(isDel);
        sb.append(", addTime=").append(addTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adviser_info
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AdviserInfo other = (AdviserInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getSemesterId() == null ? other.getSemesterId() == null : this.getSemesterId().equals(other.getSemesterId()))
            && (this.getDocTypeId() == null ? other.getDocTypeId() == null : this.getDocTypeId().equals(other.getDocTypeId()))
            && (this.getReqInfo() == null ? other.getReqInfo() == null : this.getReqInfo().equals(other.getReqInfo()))
            && (this.getIsAccept() == null ? other.getIsAccept() == null : this.getIsAccept().equals(other.getIsAccept()))
            && (this.getIsDel() == null ? other.getIsDel() == null : this.getIsDel().equals(other.getIsDel()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adviser_info
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getSemesterId() == null) ? 0 : getSemesterId().hashCode());
        result = prime * result + ((getDocTypeId() == null) ? 0 : getDocTypeId().hashCode());
        result = prime * result + ((getReqInfo() == null) ? 0 : getReqInfo().hashCode());
        result = prime * result + ((getIsAccept() == null) ? 0 : getIsAccept().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adviser_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public void andLogicalDeleted(boolean deleted) {
        setIsDel(deleted ? IS_DELETED : NOT_DELETED);
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table adviser_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        teacherId("teacher_id", "teacherId", "INTEGER", false),
        semesterId("semester_id", "semesterId", "INTEGER", false),
        docTypeId("doc_type_id", "docTypeId", "INTEGER", false),
        reqInfo("req_info", "reqInfo", "VARCHAR", false),
        isAccept("is_accept", "isAccept", "TINYINT", false),
        isDel("is_del", "isDel", "BIT", false),
        addTime("add_time", "addTime", "TIMESTAMP", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table adviser_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }
    }
}