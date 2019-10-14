package com.lj.business.cm.dto.wordsType;

import java.io.Serializable;
import java.util.Date;

public class FindWordsTypePageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1826161002653332565L; 

	/**
     * CODE .
     */
    private String code;
    
    /**
     * 类型数量
     */
    private int typeCount;
    
    /**
     * 导购号
     */
    private String memberNoGm;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 类型名称 .
     */
    private String typeName;

    /**
     * 排序 .
     */
    private Integer seq;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
    /**
     * 机构名
     */
    private String officeName;

    
    
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public int getTypeCount() {
		return typeCount;
	}

	public void setTypeCount(int typeCount) {
		this.typeCount = typeCount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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
		return "FindWordsTypePageReturn [code=" + code + ", typeCount=" + typeCount + ", memberNoGm=" + memberNoGm
				+ ", merchantNo=" + merchantNo + ", typeName=" + typeName + ", seq=" + seq + ", createId=" + createId
				+ ", createDate=" + createDate + ", remark=" + remark + ", remark2=" + remark2 + ", remark3=" + remark3
				+ ", remark4=" + remark4 + ", officeName=" + officeName + "]";
	}

	
    
}
