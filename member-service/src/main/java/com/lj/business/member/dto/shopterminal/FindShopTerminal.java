package com.lj.business.member.dto.shopterminal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FindShopTerminal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3981359506022152349L; 

	/**
     * CODE .
     */
    private String code;

    /**
     * 终端编码 .
     */
    private String terminalCode;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 分店编号 .
     */
    

    /**
     * 分店名称 .
     */
    

    /**
     * 手机名称 .
     */
    private String mobileName;

    /**
     * 绑定微信号 .
     */
    private String noWx;

    /**
     * 绑定微信昵称 .
     */
    private String wxNickname;

    /**
     * 绑定微信头像地址 .
     */
    private String headAddress;

    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 状态：0未激活、1正常、2注销 .
     */
    private Integer status;

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
     * 绑定微信性别:
             男：MALE
             女：FEMALE
             未知：UNKNOWN .
     */
    private String sex;
    
    private List<String> shopNos;
    
    List<String> noWxList;
    
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	/**
	 * @return the mobileName
	 */
	public String getMobileName() {
		return mobileName;
	}

	/**
	 * @param mobileName the mobileName to set
	 */
	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

    /**
     * 绑定微信号 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 绑定微信号 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the createId
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * @param createId the createId to set
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the remark2
	 */
	public String getRemark2() {
		return remark2;
	}

	/**
	 * @param remark2 the remark2 to set
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * @return the remark3
	 */
	public String getRemark3() {
		return remark3;
	}

	/**
	 * @param remark3 the remark3 to set
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	/**
	 * @return the remark4
	 */
	public String getRemark4() {
		return remark4;
	}

	/**
	 * @param remark4 the remark4 to set
	 */
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}
	
	

	public List<String> getShopNos() {
		return shopNos;
	}

	public void setShopNos(List<String> shopNos) {
		this.shopNos = shopNos;
	}

	public List<String> getNoWxList() {
		return noWxList;
	}

	public void setNoWxList(List<String> noWxList) {
		this.noWxList = noWxList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindShopTerminal [code=");
		builder.append(code);
		builder.append(", terminalCode=");
		builder.append(terminalCode);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", mobileName=");
		builder.append(mobileName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", wxNickname=");
		builder.append(wxNickname);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", status=");
		builder.append(status);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append(", sex=");
		builder.append(sex);
		builder.append("]");
		return builder.toString();
	}
    
}
