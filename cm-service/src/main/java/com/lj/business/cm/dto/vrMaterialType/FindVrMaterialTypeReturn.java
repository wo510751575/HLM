package com.lj.business.cm.dto.vrMaterialType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryReturn;

public class FindVrMaterialTypeReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -2311273238113970582L;


	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 类型名称 .
     */
    private String typeName;

    /**
     * 类型总数量 .
     */
    private Integer typeCount;

    /**
     * 图片地址 .
     */
    private String imgAddr;

    /**
     * 客户关注度 .
     */
    private Double customerAttentionRate;

    /**
     * 素材维度
             商户：MERCHANT
             终端：SHOP .
     */
    private String materialDimension;

    /**
     * 显示序号 .
     */
    private Integer showIndex;

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
    

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeCount() {
		return typeCount;
	}

	public void setTypeCount(Integer typeCount) {
		this.typeCount = typeCount;
	}

	public Double getCustomerAttentionRate() {
		return customerAttentionRate;
	}

	public void setCustomerAttentionRate(Double customerAttentionRate) {
		this.customerAttentionRate = customerAttentionRate;
	}

	public String getMaterialDimension() {
		return materialDimension;
	}

	public void setMaterialDimension(String materialDimension) {
		this.materialDimension = materialDimension;
	}

	public Integer getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

	@Override
	public String toString() {
		return "FindVrMaterialTypeReturn [code=" + code + ", merchantNo="
				+ merchantNo + ", memberNoGm=" + memberNoGm + ", memberNameGm="
				+ memberNameGm + ", typeName=" + typeName + ", typeCount="
				+ typeCount + ", imgAddr=" + imgAddr
				+ ", customerAttentionRate=" + customerAttentionRate
				+ ", materialDimension=" + materialDimension + ", showIndex="
				+ showIndex + ", createId=" + createId + ", createDate="
				+ createDate + ", remark=" + remark + ", remark2=" + remark2
				+ ", remark3=" + remark3 + ", remark4=" + remark4 + "]";
	}

    
}
