package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class OrthodonticsTemplateDto implements Serializable { 

    /**
     *  .
     */
    private String code;

    /**
     * 名称 .
     */
    private String name;

    /**
     * 父节点code .
     */
    private String parentCode;

    /**
     *  .
     */
    private String parentCodes;

    /**
     * 父节点名称 .
     */
    private String parentName;

    /**
     * 排序:数字越大越靠前 .
     */
    private Integer orderNo;

    /**
     * 创建人 .
     */
    private String creater;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 层级code .
     */
    private Integer levelCode;

    /**
     * 类型:1-模板目录;2-模板' .
     */
    private Integer type;

    /**
     * 检查 .
     */
    private String check;

    /**
     * 处置 .
     */
    private String management;

    /**
     * 医嘱 .
     */
    private String doctorAdvice;

    /**
     *  .
     */
    private String remark;

    /**
     *  .
     */
    private String remark2;

    /**
     *  .
     */
    private String remark3;

    /**
     *  .
     */
    private String remark4;

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
     * 名称 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 名称 .
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
     *  .
     *
     */
    public String getParentCodes() {
        return parentCodes;
    }

    /**
     *  .
     *
     */
    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes == null ? null : parentCodes.trim();
    }

    /**
     * 父节点名称 .
     *
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * 父节点名称 .
     *
     */
    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    /**
     * 排序:数字越大越靠前 .
     *
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * 排序:数字越大越靠前 .
     *
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 创建人 .
     *
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
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
     * 层级code .
     *
     */
    public Integer getLevelCode() {
        return levelCode;
    }

    /**
     * 层级code .
     *
     */
    public void setLevelCode(Integer levelCode) {
        this.levelCode = levelCode;
    }

    /**
     * 类型:1-模板目录;2-模板' .
     *
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型:1-模板目录;2-模板' .
     *
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 检查 .
     *
     */
    public String getCheck() {
        return check;
    }

    /**
     * 检查 .
     *
     */
    public void setCheck(String check) {
        this.check = check == null ? null : check.trim();
    }

    /**
     * 处置 .
     *
     */
    public String getManagement() {
        return management;
    }

    /**
     * 处置 .
     *
     */
    public void setManagement(String management) {
        this.management = management == null ? null : management.trim();
    }

    /**
     * 医嘱 .
     *
     */
    public String getDoctorAdvice() {
        return doctorAdvice;
    }

    /**
     * 医嘱 .
     *
     */
    public void setDoctorAdvice(String doctorAdvice) {
        this.doctorAdvice = doctorAdvice == null ? null : doctorAdvice.trim();
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
     *  .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     *  .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     *  .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     *  .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     *  .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     *  .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrthodonticsTemplate [code=").append(code);
        builder.append(",name=").append(name); 
        builder.append(",parentCode=").append(parentCode); 
        builder.append(",parentCodes=").append(parentCodes); 
        builder.append(",parentName=").append(parentName); 
        builder.append(",orderNo=").append(orderNo); 
        builder.append(",creater=").append(creater); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",levelCode=").append(levelCode); 
        builder.append(",type=").append(type); 
        builder.append(",check=").append(check); 
        builder.append(",management=").append(management); 
        builder.append(",doctorAdvice=").append(doctorAdvice); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}
