package com.lj.business.cm.domain;

import java.util.Date;

public class WordsInfo {
    /**
     * CODE .
     */
    private String code;

    /**
     * 内容 .
     */
    private String content;

    /**
     * 是否默认：0非默认话术、1默认话术 .
     */
    private Integer defaultFlag;

    /**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
    * 导购号
    */
    private String memberNoGm;

    /**
     * 类型CODE .
     */
    private String typeCode;

    /**
     * 类型名称 .
     */
    private String typeName;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新时间 .
     */
    private Date updateTime;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;

    
    
    public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

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
     * 是否默认：0非默认话术、1默认话术 .
     *
     */
    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    /**
     * 是否默认：0非默认话术、1默认话术 .
     *
     */
    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 类型CODE .
     *
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 类型CODE .
     *
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * 类型名称 .
     *
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 类型名称 .
     *
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
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
     * 更新时间 .
     *
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间 .
     *
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
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
        builder.append("WordsInfo [code=").append(code);
        builder.append(",content=").append(content); 
        builder.append(",defaultFlag=").append(defaultFlag); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",typeCode=").append(typeCode); 
        builder.append(",typeName=").append(typeName); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",updateTime=").append(updateTime); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}