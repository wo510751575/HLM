package com.lj.business.member.dto.addfriends;

import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindAddFriendsAllotPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8940294008423777354L; 

	/**
	 * 商户编号 .
	 */
	private String merchantNo;
	
    /**
     * 客户名称 .
     */
    private String memberName;

    /**
	 * 客户微信号
	 */
	private String noWx;
	
	/**
	 * 客户微信别名
	 */
	private String alias;
	
	/**
	 * 终端微信号
	 */
	private String noWxGm;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 所属名店
	 */
	
	
	/** 开始时间. */
	private Date startTime;			
	
	/** 结束时间. */
	private Date endTime;
	/**
	 * 终端集合
	 */
    private List<String> shopNos;
    
	/**
	 * 认领标识：true已认领，false未认领
	 */
	private Boolean flag;
    
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public List<String> getShopNos() {
		return shopNos;
	}

	public void setShopNos(List<String> shopNos) {
		this.shopNos = shopNos;
	}

	/**
	 * @return the noWxGm
	 */
	public String getNoWxGm() {
		return noWxGm;
	}

	/**
	 * @param noWxGm the noWxGm to set
	 */
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindAddFriendsAllotPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", shopNos=");
		builder.append(shopNos);
		builder.append(", flag=");
		builder.append(flag);
		builder.append("]");
		return builder.toString();
	}
	
}
