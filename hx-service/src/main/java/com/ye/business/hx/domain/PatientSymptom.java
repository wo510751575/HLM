package com.ye.business.hx.domain;

import java.util.Date;

public class PatientSymptom {
    /**
     *  .
     */
    private String code;

    /**
     * 症状 .
     */
    private String name;

    /**
     * 父节点code .
     */
    private String parentCode;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     *  .
     */
    private String remark;

    /**
     * 层级 .
     */
    private Integer level;

    /**
     *  .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     *  .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 症状 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 症状 .
     *
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 父节点code .
     *
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 父节点code .
     *
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *  .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     *  .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 层级 .
     *
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 层级 .
     *
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PatientSymptom [code=").append(code);
        builder.append(",name=").append(name); 
        builder.append(",parentCode=").append(parentCode); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",level=").append(level); 
        builder.append("]");
        return builder.toString();
    }
}