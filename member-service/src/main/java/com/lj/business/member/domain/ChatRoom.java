package com.lj.business.member.domain;

import java.util.Date;

public class ChatRoom implements java.io.Serializable{
	private static final long serialVersionUID = 8974674687146294048L;
    /**
     * CODE .
     */
    private String code;
    
    private String pmCode;
    
    private String pmName;

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
     * 导购编号 .
     */
    private String memberNameGm;
    
    /**
     * 设置消息置顶的用户，用逗号开始逗号结束，例如：,aaaa,
     */
    private String setUpUser;
    
    private String noWxGm;
    /**
     * 免打扰，0：未开启，1：开启
     * 默认未开启
     */
    private Integer noDisturb=0;
    
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
    
    public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	public String getSetUpUser() {
		return setUpUser;
	}

	public void setSetUpUser(String setUpUser) {
		this.setUpUser = setUpUser;
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
    
    public String getPmCode() {
		return pmCode;
	}

	public void setPmCode(String pmCode) {
		this.pmCode = pmCode;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
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
		StringBuilder builder = new StringBuilder();
		builder.append("ChatRoom [code=");
		builder.append(code);
		builder.append(", noWxZk=");
		builder.append(noWxZk);
		builder.append(", chatRoomName=");
		builder.append(chatRoomName);
		builder.append(", roomNickName=");
		builder.append(roomNickName);
		builder.append(", roomOwner=");
		builder.append(roomOwner);
		builder.append(", headUrl=");
		builder.append(headUrl);
		builder.append(", status=");
		builder.append(status);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", version=");
		builder.append(version);
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
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append("]");
		return builder.toString();
	}
}