package com.lj.business.weixin.dto;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindWxJobRedPackInfoPage extends PageParamEntity { 
	 /**
     *  .
     */
    private String code;

    /**
     * 微信红包ID .
     */
    private String redPackId;

    /**
     * 批量红包CODE .
     */
    private String batchCode;

    /**
     * 终端微信号 .
     */
    private String noWxShop;

    /**
     * 客户导购名称 .
     */
    private String memberNameGm;

    /**
     * 客户导购号 .
     */
    private String memberNoGm;

    /**
     * 终端编号 .
     */
    

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 客户微信号 .
     */
    private String noWx;

    /**
     * 红包文字内容 .
     */
    private String content;

    /**
     * 红包金额 .
     */
    private Long amount;

    /**
     * 红包状态 .
     */
    private String status;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 发送时间 .
     */
    private Date sendDate;

    /**
     * 领取时间 .
     */
    private Date receiveDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRedPackId() {
		return redPackId;
	}

	public void setRedPackId(String redPackId) {
		this.redPackId = redPackId;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getNoWxShop() {
		return noWxShop;
	}

	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWxJobRedPackInfoPage [code=");
		builder.append(code);
		builder.append(", redPackId=");
		builder.append(redPackId);
		builder.append(", batchCode=");
		builder.append(batchCode);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", content=");
		builder.append(content);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", status=");
		builder.append(status);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", sendDate=");
		builder.append(sendDate);
		builder.append(", receiveDate=");
		builder.append(receiveDate);
		builder.append("]");
		return builder.toString();
	}
    
}
