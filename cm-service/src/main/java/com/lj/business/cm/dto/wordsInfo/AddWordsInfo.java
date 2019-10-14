package com.lj.business.cm.dto.wordsInfo;

import java.io.Serializable;
import java.util.Date;

public class AddWordsInfo implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -4334665781340899006L;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddWordsInfo [code=");
		builder.append(code);
		builder.append(", content=");
		builder.append(content);
		builder.append(", defaultFlag=");
		builder.append(defaultFlag);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", typeCode=");
		builder.append(typeCode);
		builder.append(", typeName=");
		builder.append(typeName);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append("]");
		return builder.toString();
	}
    
}
