package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class OrthodonticsPlanDto implements Serializable { 

    /**
     * CODE .
     */
    private String code;

    /**
     * 类型:1-主诉;2-问题;3-矫治目标;4-治疗步骤 .
     */
    private Integer type;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
     * 内容 .
     */
    private String content;

    /**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 类型:1-主诉;2-问题;3-矫治目标;4-治疗步骤 .
     *
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型:1-主诉;2-问题;3-矫治目标;4-治疗步骤 .
     *
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 内容 .
     *
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容 .
     *
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrthodonticsPlan [code=").append(code);
        builder.append(",type=").append(type); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",content=").append(content); 
        builder.append("]");
        return builder.toString();
    }
}
