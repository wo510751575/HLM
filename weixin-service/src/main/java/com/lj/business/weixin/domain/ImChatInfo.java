package com.lj.business.weixin.domain;

import java.io.Serializable;
import java.util.Date;

public class ImChatInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5130832312642734759L;
    /**
     * CODE .
     */
    private String code;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 导购微信号 .
     */
    private String noWxGm;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 客户微信 .
     */
    private String noWx;

    /**
     * 客户微信别名 .
     */
    private String alias;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 发送人标识：1导购发送、2客户发送 .
     */
    private Integer senderFlag;

    /**
     * 发送人同步状态：0未同步、1已同步。从导购客户端、中控客户端（客户微信）外第三方发送给客户，则需将消息同步到导购客户端 .
     */
    private Integer senderSyncStatus;

    /**
     * 消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息 .
     */
    private String type;

    /**
     * 消息状态：1 未发送、2发送成功、3发送失败 .
     */
    private String status;

    /**
     * 聊天时间，发送端发送时间 .
     */
    private Date chatTime;

    /**
     * 接收时间，接收端接收时间 .
     */
    private Date receivedTime;

    /**
     * 资源路径：语音、图片、视频 .
     */
    private String resourcesPath;

    /**
     * 分享标题 .
     */
    private String shareTitle;

    /**
     * 分享描述 .
     */
    private String shareDes;

    /**
     * 分享链接 .
     */
    private String shareUrl;
    
    /**
     * 群聊类型：1单聊、2群聊
     */
    private Integer chatroomType;
    
    /**
     * 群聊消息发送人微信
     */
    private String chatroomNoWx;

    /**
     * 消息来源：1导购、2中控、3系统 .
     */
    private Integer msgSource;

    /**
     * 第三方已读标识：0未读、1已读 .
     */
    private Integer thirdReadFlag;

    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 聊天助手编号,msgSource=3时不为空 .
     */
    private String chatAssistantCode;

    /**
     * 发送失败编码 .
     */
    private String errorCode;

    /**
     * 发送失败说明 .
     */
    private String errorMessage;

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
     * 内容 .
     */
    private String content;
    /**
	 * 群头像
	 */
	private String headUrl;
	
	/**
	 * 群昵称
	 */
	private String roomNickName;
	
	/**
	 * 发送客户昵称
	 */
	private String memberNickName;
	/**
	 * 发送客户头像
	 */
	private String memberHeadUrl;
	/**
     * 免打扰，0：未开启，1：开启
     * 默认未开启
     */
    private Integer noDisturb=0;
    /**
     * 大图
     */
    private String bigImg;
    /**
     * 中图
     */
    private String midImg;
    
    
	public String getMidImg() {
		return midImg;
	}

	public void setMidImg(String midImg) {
		this.midImg = midImg;
	}
	public String getBigImg() {
		return bigImg;
	}

	public void setBigImg(String bigImg) {
		this.bigImg = bigImg;
	}
    public Integer getNoDisturb() {
		return noDisturb;
	}

	public void setNoDisturb(Integer noDisturb) {
		this.noDisturb = noDisturb;
	}
	public String getMemberHeadUrl() {
		return memberHeadUrl;
	}

	public void setMemberHeadUrl(String memberHeadUrl) {
		this.memberHeadUrl = memberHeadUrl;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getRoomNickName() {
		return roomNickName;
	}

	public void setRoomNickName(String roomNickName) {
		this.roomNickName = roomNickName;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
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
     * 导购编号 .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 导购微信号 .
     *
     */
    public String getNoWxGm() {
        return noWxGm;
    }

    /**
     * 导购微信号 .
     *
     */
    public void setNoWxGm(String noWxGm) {
        this.noWxGm = noWxGm == null ? null : noWxGm.trim();
    }

    /**
     * 客户编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 客户编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 客户名称 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 客户名称 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 客户微信 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 客户微信 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     * 客户微信别名 .
     *
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 客户微信别名 .
     *
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
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
     * 发送人标识：1导购发送、2客户发送 .
     *
     */
    public Integer getSenderFlag() {
        return senderFlag;
    }

    /**
     * 发送人标识：1导购发送、2客户发送 .
     *
     */
    public void setSenderFlag(Integer senderFlag) {
        this.senderFlag = senderFlag;
    }

    /**
     * 发送人同步状态：0未同步、1已同步。从导购客户端、中控客户端（客户微信）外第三方发送给客户，则需将消息同步到导购客户端 .
     *
     */
    public Integer getSenderSyncStatus() {
        return senderSyncStatus;
    }

    /**
     * 发送人同步状态：0未同步、1已同步。从导购客户端、中控客户端（客户微信）外第三方发送给客户，则需将消息同步到导购客户端 .
     *
     */
    public void setSenderSyncStatus(Integer senderSyncStatus) {
        this.senderSyncStatus = senderSyncStatus;
    }

    /**
     * 消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息 .
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息 .
     *
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 消息状态：1 未发送、2发送成功、3发送失败 .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 消息状态：1 未发送、2发送成功、3发送失败 .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 聊天时间，发送端发送时间 .
     *
     */
    public Date getChatTime() {
        return chatTime;
    }

    /**
     * 聊天时间，发送端发送时间 .
     *
     */
    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }

    /**
     * 接收时间，接收端接收时间 .
     *
     */
    public Date getReceivedTime() {
        return receivedTime;
    }

    /**
     * 接收时间，接收端接收时间 .
     *
     */
    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    /**
     * 资源路径：语音、图片、视频 .
     *
     */
    public String getResourcesPath() {
        return resourcesPath;
    }

    /**
     * 资源路径：语音、图片、视频 .
     *
     */
    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath == null ? null : resourcesPath.trim();
    }

    /**
     * 分享标题 .
     *
     */
    public String getShareTitle() {
        return shareTitle;
    }

    /**
     * 分享标题 .
     *
     */
    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle == null ? null : shareTitle.trim();
    }

    /**
     * 分享描述 .
     *
     */
    public String getShareDes() {
        return shareDes;
    }

    /**
     * 分享描述 .
     *
     */
    public void setShareDes(String shareDes) {
        this.shareDes = shareDes == null ? null : shareDes.trim();
    }

    /**
     * 分享链接 .
     *
     */
    public String getShareUrl() {
        return shareUrl;
    }

    /**
     * 分享链接 .
     *
     */
    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl == null ? null : shareUrl.trim();
    }

    /**
	 * @return the chatroomType
	 */
	public Integer getChatroomType() {
		return chatroomType;
	}

	/**
	 * @param chatroomType the chatroomType to set
	 */
	public void setChatroomType(Integer chatroomType) {
		this.chatroomType = chatroomType;
	}

	/**
	 * @return the chatroomNoWx
	 */
	public String getChatroomNoWx() {
		return chatroomNoWx;
	}

	/**
	 * @param chatroomNoWx the chatroomNoWx to set
	 */
	public void setChatroomNoWx(String chatroomNoWx) {
		this.chatroomNoWx = chatroomNoWx;
	}

	/**
     * 消息来源：1导购、2中控、3系统 .
     *
     */
    public Integer getMsgSource() {
        return msgSource;
    }

    /**
     * 消息来源：1导购、2中控、3系统 .
     *
     */
    public void setMsgSource(Integer msgSource) {
        this.msgSource = msgSource;
    }

    /**
     * 第三方已读标识：0未读、1已读 .
     *
     */
    public Integer getThirdReadFlag() {
        return thirdReadFlag;
    }

    /**
     * 第三方已读标识：0未读、1已读 .
     *
     */
    public void setThirdReadFlag(Integer thirdReadFlag) {
        this.thirdReadFlag = thirdReadFlag;
    }

    /**
     * 手机串号 .
     *
     */
    public String getImei() {
        return imei;
    }

    /**
     * 手机串号 .
     *
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
	 * @return the chatAssistantCode
	 */
	public String getChatAssistantCode() {
		return chatAssistantCode;
	}

	/**
	 * @param chatAssistantCode the chatAssistantCode to set
	 */
	public void setChatAssistantCode(String chatAssistantCode) {
		this.chatAssistantCode = chatAssistantCode;
	}

	/**
     * 发送失败编码 .
     *
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 发送失败编码 .
     *
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    /**
     * 发送失败说明 .
     *
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 发送失败说明 .
     *
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
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

    /**
     * 内容 .
     *
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容 .
     *
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImChatInfo [code=");
		builder.append(code);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", senderFlag=");
		builder.append(senderFlag);
		builder.append(", senderSyncStatus=");
		builder.append(senderSyncStatus);
		builder.append(", type=");
		builder.append(type);
		builder.append(", status=");
		builder.append(status);
		builder.append(", chatTime=");
		builder.append(chatTime);
		builder.append(", receivedTime=");
		builder.append(receivedTime);
		builder.append(", resourcesPath=");
		builder.append(resourcesPath);
		builder.append(", shareTitle=");
		builder.append(shareTitle);
		builder.append(", shareDes=");
		builder.append(shareDes);
		builder.append(", shareUrl=");
		builder.append(shareUrl);
		builder.append(", chatroomType=");
		builder.append(chatroomType);
		builder.append(", chatroomNoWx=");
		builder.append(chatroomNoWx);
		builder.append(", msgSource=");
		builder.append(msgSource);
		builder.append(", thirdReadFlag=");
		builder.append(thirdReadFlag);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", chatAssistantCode=");
		builder.append(chatAssistantCode);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
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
		builder.append(", content=");
		builder.append(content);
		builder.append(", headUrl=");
		builder.append(headUrl);
		builder.append(", roomNickName=");
		builder.append(roomNickName);
		builder.append(", memberNickName=");
		builder.append(memberNickName);
		builder.append("]");
		return builder.toString();
	}

}