package com.lj.business.member.domain;

import java.util.Date;

public class GuidCardCustomer {
    /**
     * CODE .
     */
    private String code;

    /**
     * 导购名片编号 .
     */
    private String guidCardCode;

    /**
     * 客户OPEN_ID .
     */
    private String openId;

    /**
     * 类型 .
     */
    private String type;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
     * 导购名片编号 .
     *
     */
    public String getGuidCardCode() {
        return guidCardCode;
    }

    /**
     * 导购名片编号 .
     *
     */
    public void setGuidCardCode(String guidCardCode) {
        this.guidCardCode = guidCardCode == null ? null : guidCardCode.trim();
    }

    /**
     * 客户OPEN_ID .
     *
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 客户OPEN_ID .
     *
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 类型 .
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 类型 .
     *
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GuidCardCustomer [code=").append(code);
        builder.append(",guidCardCode=").append(guidCardCode); 
        builder.append(",openId=").append(openId); 
        builder.append(",type=").append(type); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}