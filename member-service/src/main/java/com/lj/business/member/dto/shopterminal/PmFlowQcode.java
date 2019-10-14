package com.lj.business.member.dto.shopterminal;

import java.io.Serializable;
import java.util.Date;
/**
 *分流二维码 
 *
 */
public class PmFlowQcode implements Serializable { 
	
	private static final long serialVersionUID = 7514525115135534322L;

	private String code;
	private String merchantNo; //商户号
	private String pmName;     //活动二维码名称
	private String qcodeContent; //二维码内容
	private String flowQcode;  //分流二维码图片存储地址
	private String flowWxNo;     //分流二维码组（多个二维码,号隔开）
	private Integer num;         //改组微信终端个数

	private Date createDate;
	
	
	public String getQcodeContent() {
		return qcodeContent;
	}
	public void setQcodeContent(String qcodeContent) {
		this.qcodeContent = qcodeContent;
	}
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getFlowQcode() {
		return flowQcode;
	}
	public void setFlowQcode(String flowQcode) {
		this.flowQcode = flowQcode;
	}
	
	public String getFlowWxNo() {
		return flowWxNo;
	}
	public void setFlowWxNo(String flowWxNo) {
		this.flowWxNo = flowWxNo;
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
	public String getPmName() {
		return pmName;
	}
	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

}
