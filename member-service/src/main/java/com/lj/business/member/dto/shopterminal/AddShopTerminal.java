package com.lj.business.member.dto.shopterminal;

import java.io.Serializable;
import java.util.Date;

import com.lj.business.common.CommonConstant;

public class AddShopTerminal implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 7514525115135534311L;
    
    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 绑定微信名(微信username) .
     */
    private String usernameWx;
    /**
     * 绑定微信号 .
     */
    private String noWx;
    
    /**
     * 绑定微信昵称
     */
    private String wxNickname;

    /**
     * 绑定微信头像地址 .
     */
    private String headAddress;

    /**
     * 绑定微信二维码地址 .
     */
    private String qcord;

    /**
     * 绑定微信性别:
             男：MALE
             女：FEMALE
             未知：UNKNOWN .
     */
    private String sex;

    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 状态：0未激活、1正常、2注销 .
     */
    private Integer status;
    
    /**
     * 是否上传朋友圈信息：0不上传、1上传（默认）
     */
    private Integer uploadFriendsFlag = CommonConstant.I_YES;

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
    private String headurl;
    private String nickname;
    private String alias;
    
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsernameWx() {
		return usernameWx;
	}

	public void setUsernameWx(String usernameWx) {
		this.usernameWx = usernameWx;
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
	 * @return the wxNickname
	 */
	public String getWxNickname() {
		return wxNickname;
	}

	/**
	 * @param wxNickname the wxNickname to set
	 */
	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	/**
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * @return the qcord
	 */
	public String getQcord() {
		return qcord;
	}

	/**
	 * @param qcord the qcord to set
	 */
	public void setQcord(String qcord) {
		this.qcord = qcord;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
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
	 * @return the uploadFriendsFlag
	 */
	public Integer getUploadFriendsFlag() {
		return uploadFriendsFlag;
	}

	/**
	 * @param uploadFriendsFlag the uploadFriendsFlag to set
	 */
	public void setUploadFriendsFlag(Integer uploadFriendsFlag) {
		this.uploadFriendsFlag = uploadFriendsFlag;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddShopTerminal [merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", usernameWx=");
		builder.append(usernameWx);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", wxNickname=");
		builder.append(wxNickname);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", qcord=");
		builder.append(qcord);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", status=");
		builder.append(status);
		builder.append(", uploadFriendsFlag=");
		builder.append(uploadFriendsFlag);
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
		builder.append(", headurl=");
		builder.append(headurl);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append("]");
		return builder.toString();
	}

}
