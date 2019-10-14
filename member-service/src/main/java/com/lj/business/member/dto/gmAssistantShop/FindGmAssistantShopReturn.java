package com.lj.business.member.dto.gmAssistantShop;

import java.io.Serializable;
import java.util.Date;

import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;

public class FindGmAssistantShopReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 2535739058135073230L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 助手编号 .
     */
    private String assistantNo;

    /**
     * 助手名称 .
     */
    private String assistantName;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;
    /**
     * 终端名称
     */
    private String shopName;

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
     * name
     */
    private String name;

    
    /**
     * tidCode .
     */
    private String tidCode;
    private String terminalCode;
    private String noWx;
    private String wxNickname;
    private String loginName;
    
    /**
     * 来源：下属微信
     */
    private String source;
    /**
     * 头像
     */
    private String photo;
    
    public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
    /**
     * findShopTerminalReturn 需查询出来
     */
    private FindShopTerminalReturn findShopTerminalReturn;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAssistantNo() {
		return assistantNo;
	}

	public void setAssistantNo(String assistantNo) {
		this.assistantNo = assistantNo;
	}

	public String getAssistantName() {
		return assistantName;
	}

	public void setAssistantName(String assistantName) {
		this.assistantName = assistantName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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
	
	

	public String getTidCode() {
		return tidCode;
	}

	public void setTidCode(String tidCode) {
		this.tidCode = tidCode;
	}
	
	

	public FindShopTerminalReturn getFindShopTerminalReturn() {
		return findShopTerminalReturn;
	}

	public void setFindShopTerminalReturn(FindShopTerminalReturn findShopTerminalReturn) {
		this.findShopTerminalReturn = findShopTerminalReturn;
	}

	@Override
	public String toString() {
		return "FindGmAssistantShopReturn [code=" + code + ", assistantNo=" + assistantNo + ", assistantName="
				+ assistantName + ", merchantNo=" + merchantNo + ", merchantName=" + merchantName + ", shopName="
				+ shopName + ", createId=" + createId + ", createDate=" + createDate + ", remark=" + remark
				+ ", remark2=" + remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + ", name=" + name
				+ ", tidCode=" + tidCode + ", terminalCode=" + terminalCode + ", noWx=" + noWx + ", wxNickname="
				+ wxNickname + ", loginName=" + loginName + ", source=" + source + ", photo=" + photo
				+ ", findShopTerminalReturn=" + findShopTerminalReturn + "]";
	}

}
