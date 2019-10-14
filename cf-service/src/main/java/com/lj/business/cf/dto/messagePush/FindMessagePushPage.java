package com.lj.business.cf.dto.messagePush;

import com.lj.base.core.pagination.PageParamEntity;

public class FindMessagePushPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3770244164336480803L;
	
	/**
	 * 消息编号
	 */
	private String code;
	
	/**
	 * 商户编号
	 */
	private String merchantNo;
	
	/**
	 * 门店编号
	 */
	private String shopNo;
	
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	/**
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 消息状态                          有效：VALID\r\n            无效：INVALID
	 */
	private String msgStatus;
	
	/**
	 * 开始时间
	 */
	private String beginDate;
	
	/**
	 * 结束时间
	 */
	private String endDate;
	
	/**
	 * 是否分页
	 */
	private boolean isPage = false;

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

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean getIsPage() {
		return isPage;
	}

	public void setIsPage(boolean isPage) {
		this.isPage = isPage;
	}

	@Override
	public String toString() {
		return "FindMessagePushPage [code=" + code + ", merchantNo="
				+ merchantNo + ", shopNo=" + shopNo + ", memberNoGm="
				+ memberNoGm + ", memberNo=" + memberNo + ", msgStatus="
				+ msgStatus + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", isPage=" + isPage + "]";
	}

}
