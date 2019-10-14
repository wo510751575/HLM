package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;

public class FindImChatInfoPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4200856505991091030L; 

    /**
     * 消息ID .
     */
    private String code;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购微信号 .
     */
    private String noWxGm;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户微信 .
     */
    private String noWx;
	
	/**
	 * 客户微信别名
	 */
	private String alias;

    /**
     * 发送标识：1导购发送、2客户发送
              .
     */
    private Integer senderFlag;

    /**
     * 消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息 .
     */
    private String type;

    /**
     * 消息状态：1 未发送、2发送成功、3发送失败 .
     */
    private String status;

    /**
     * 聊天时间 .
     */
    private Date chatTime;

    /**
     * 资源路径:语音、图片、视频 .
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
     * 内容 .
     */
    private String content;
    
    /**
     * 手机串号
     */
    private String imei;
    
    /**
     * 发送失败编码
     */
    private String errorCode;
    
    /**
     * 发送失败描述
     */
    private String errorMessage;
    
    /**
     * 导购头像
     */
    private String gmPhoto;
    /**
     * 会员头像
     */
    private String pmPhoto;
    /**
     * 会员昵称
     */
    private String pmNickName;
    
    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 客户名称 .
     */
    private String memberName;
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
    /**
	 * @return the gmPhoto
	 */
	public String getGmPhoto() {
		return gmPhoto;
	}

	/**
	 * @param gmPhoto the gmPhoto to set
	 */
	public void setGmPhoto(String gmPhoto) {
		this.gmPhoto = gmPhoto;
	}

	/**
	 * @return the pmPhoto
	 */
	public String getPmPhoto() {
		return pmPhoto;
	}

	/**
	 * @param pmPhoto the pmPhoto to set
	 */
	public void setPmPhoto(String pmPhoto) {
		this.pmPhoto = pmPhoto;
	}

	/**
	 * @return the pmNickName
	 */
	public String getPmNickName() {
		return pmNickName;
	}

	/**
	 * @param pmNickName the pmNickName to set
	 */
	public void setPmNickName(String pmNickName) {
		this.pmNickName = pmNickName;
	}

	/**
     * 消息ID .
     */
	public String getCode() {
		return code;
	}

	/**
     * 消息ID .
     */
	public void setCode(String code) {
		this.code = code;
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
	 * 客户微信别名
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * 客户微信别名
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
     * 发送标识：1导购发送、2客户发送
              .
     *
     */
    public Integer getSenderFlag() {
        return senderFlag;
    }

    /**
     * 发送标识：1导购发送、2客户发送
              .
     *
     */
    public void setSenderFlag(Integer senderFlag) {
        this.senderFlag = senderFlag;
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
     * 聊天时间 .
     *
     */
    public Date getChatTime() {
        return chatTime;
    }

    /**
     * 聊天时间 .
     *
     */
    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }

    /**
     * 资源路径:语音、图片、视频 .
     *
     */
    public String getResourcesPath() {
        return resourcesPath;
    }

    /**
     * 资源路径:语音、图片、视频 .
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
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImChatInfoPageReturn [code=");
		builder.append(code);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", senderFlag=");
		builder.append(senderFlag);
		builder.append(", type=");
		builder.append(type);
		builder.append(", status=");
		builder.append(status);
		builder.append(", chatTime=");
		builder.append(chatTime);
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
		builder.append(", content=");
		builder.append(content);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", gmPhoto=");
		builder.append(gmPhoto);
		builder.append(", pmPhoto=");
		builder.append(pmPhoto);
		builder.append(", pmNickName=");
		builder.append(pmNickName);
		builder.append("]");
		return builder.toString();
	}

}
