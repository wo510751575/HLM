package com.lj.business.member.dto.chatroom;

import java.io.Serializable;
import java.util.Date;

public class UpdateChatRoom implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -6173765336841114306L;

    /**
     * CODE .
     */
    private String code;

    /**
     * 中控微信号 .
     */
    private String noWxZk;

    /**
     * 群名 .
     */
    private String chatRoomName;

    /**
     * 群昵称 .
     */
    private String roomNickName;

    /**
     * 群主用户名 .
     */
    private String roomOwner;

    /**
     * 群头像 .
     */
    private String headUrl;

    /**
     * 状态：0初始化、1有效、2无效（删除） .
     */
    private Integer status;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 版本号 .
     */
    private Long version;

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
     * 导购编号 .
     */
    private String memberNoGm;
    /**
     * 导购名称 .
     */
    private String memberNameGm;
    private String setUpUser;
    /**
     * 免打扰，0：未开启，1：开启
     * 默认未开启
     */
    private Integer noDisturb;
    
    public Integer getNoDisturb() {
		return noDisturb;
	}

	public void setNoDisturb(Integer noDisturb) {
		this.noDisturb = noDisturb;
	}
    /**
     * 群二维码
     */
    private String QRCode;
    
    
    public String getQRCode() {
		return QRCode;
	}

	public void setQRCode(String qRCode) {
		QRCode = qRCode;
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
	
    public String getSetUpUser() {
		return setUpUser;
	}

	public void setSetUpUser(String setUpUser) {
		this.setUpUser = setUpUser;
	}
    /**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 中控微信号 .
     *
     */
    public String getNoWxZk() {
        return noWxZk;
    }

    /**
     * 中控微信号 .
     *
     */
    public void setNoWxZk(String noWxZk) {
        this.noWxZk = noWxZk == null ? null : noWxZk.trim();
    }

    /**
     * 群名 .
     *
     */
    public String getChatRoomName() {
        return chatRoomName;
    }

    /**
     * 群名 .
     *
     */
    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName == null ? null : chatRoomName.trim();
    }

    /**
     * 群昵称 .
     *
     */
    public String getRoomNickName() {
        return roomNickName;
    }

    /**
     * 群昵称 .
     *
     */
    public void setRoomNickName(String roomNickName) {
        this.roomNickName = roomNickName == null ? null : roomNickName.trim();
    }

    /**
     * 群主用户名 .
     *
     */
    public String getRoomOwner() {
        return roomOwner;
    }

    /**
     * 群主用户名 .
     *
     */
    public void setRoomOwner(String roomOwner) {
        this.roomOwner = roomOwner == null ? null : roomOwner.trim();
    }

    /**
     * 群头像 .
     *
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * 群头像 .
     *
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    /**
     * 状态：0初始化、1有效、2无效（删除） .
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：0初始化、1有效、2无效（删除） .
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名称 .
     *
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 版本号 .
     *
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号 .
     *
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

	@Override
	public String toString() {
		return "UpdateChatRoom [code=" + code + ", noWxZk=" + noWxZk + ", chatRoomName=" + chatRoomName
				+ ", roomNickName=" + roomNickName + ", roomOwner=" + roomOwner + ", headUrl=" + headUrl + ", status="
				+ status + ", merchantNo=" + merchantNo + ", merchantName=" + merchantName + ", version=" + version
				+ ", createId=" + createId + ", createDate=" + createDate + ", remark=" + remark + ", remark2="
				+ remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + ", memberNoGm=" + memberNoGm
				+ ", memberNameGm=" + memberNameGm + ", setUpUser=" + setUpUser + "]";
	}

}
