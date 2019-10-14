package com.lj.business.cm.friendsLinkMaterial;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindFriendsLinkMaterialPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8608246674137921769L; 
	
	/**
	 * 商户编号
	 */
	private String merchantNo;

	/**
	 * 素材类型
	 */
	private String materialType;
	
	/**
	 * 标题
	 */
	private String title;
	 
	/**
	 * code
	 */
	private String code;
	
	/**
	 * 搜索框查询条件
	 */
	private String conditionStr;
    
	/**
	 * 删除标识
	 */
    private String deleteFlag;
    
    /**
     * 开始时间
     */
    private Date startDate; 
	
    /**
     * 结束时间
     */
    private Date endDate;
    
    
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getConditionStr() {
		return conditionStr;
	}

	public void setConditionStr(String conditionStr) {
		this.conditionStr = conditionStr;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindFriendsLinkMaterialPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", materialType=");
		builder.append(materialType);
		builder.append(", title=");
		builder.append(title);
		builder.append(", code=");
		builder.append(code);
		builder.append(", conditionStr=");
		builder.append(conditionStr);
		builder.append(", deleteFlag=");
		builder.append(deleteFlag);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append("]");
		return builder.toString();
	}
}
