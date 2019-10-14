package com.lj.business.member.domain;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

public class BatchSendMessage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8940294008423777356L; 

	  private String code;
	  private String roomCodes;    //群code
	  private String roomPersons;  //群成员
	  private String memberNo;     //单聊客户号
	  private String noWxGm;        //终端微信
      private String content;
	  private String imgAddr;
	  private String type;
	  private Date createDate;
	  private Date sendDate;
	  private String merchantNo;
	  

	  private String startTime;
	  private String endTime;
	  
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getNoWxGm() {
		return noWxGm;
	}
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRoomCodes() {
		return roomCodes;
	}
	public void setRoomCodes(String roomCodes) {
		this.roomCodes = roomCodes;
	}
	public String getRoomPersons() {
		return roomPersons;
	}
	public void setRoomPersons(String roomPersons) {
		this.roomPersons = roomPersons;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	  
}
