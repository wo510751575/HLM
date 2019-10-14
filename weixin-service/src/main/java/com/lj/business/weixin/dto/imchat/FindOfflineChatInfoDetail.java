package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * 类说明：导购手机客户端返回离线聊天记录明细
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月30日
 */
public class FindOfflineChatInfoDetail implements Serializable {

	private static final long serialVersionUID = 3821748896111377213L;

	/**
     * 消息ID .
     */
    private String msgId;

    /**
     * 发送人标识：1导购发送、2客户发送
     */
    private Integer senderFlag;

    /**
     * 消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息 .
     */
    private String type;

    /**
     * 聊天时间 返回给中控的.
     */
    private Long createTime;
    /**
     * 用来update的
     */
    private Date chatTime;
    /**
     * 资源路径:语音、图片、视频 .
     */
    private String resources;

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
     * 群聊消息发送人微信
     */
    private String groupUserName;

    /**
     * 内容 .
     */
    private String content;
    
    /**
	 * 发送客户昵称
	 */
	private String memberNickName;
	/**
	 * 发送客户头像
	 */
	private String memberHeadUrl;
	
	public Date getChatTime() {
		return chatTime;
	}

	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public String getMemberHeadUrl() {
		return memberHeadUrl;
	}

	public void setMemberHeadUrl(String memberHeadUrl) {
		this.memberHeadUrl = memberHeadUrl;
	}
    /**
     * 消息ID .
     */
	public String getMsgId() {
		return msgId;
	}

	/**
     * 消息ID .
     */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
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
     * 聊天时间 .
     */
	public Long getCreateTime() {
		return createTime;
	}

	/**
     * 聊天时间 .
     */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
     * 资源路径:语音、图片、视频 .
     *
     */
    public String getResources() {
        return resources;
    }

    /**
     * 资源路径:语音、图片、视频 .
     *
     */
    public void setResources(String resources) {
        this.resources = resources == null ? null : resources.trim();
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
	 * @return the groupUserName
	 */
	public String getGroupUserName() {
		return groupUserName;
	}

	/**
	 * @param groupUserName the groupUserName to set
	 */
	public void setGroupUserName(String groupUserName) {
		this.groupUserName = groupUserName;
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
		builder.append("FindOfflineChatInfoDetail [msgId=");
		builder.append(msgId);
		builder.append(", senderFlag=");
		builder.append(senderFlag);
		builder.append(", type=");
		builder.append(type);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", resources=");
		builder.append(resources);
		builder.append(", shareTitle=");
		builder.append(shareTitle);
		builder.append(", shareDes=");
		builder.append(shareDes);
		builder.append(", shareUrl=");
		builder.append(shareUrl);
		builder.append(", groupUserName=");
		builder.append(groupUserName);
		builder.append(", content=");
		builder.append(content);
		builder.append(", memberNickName=");
		builder.append(memberNickName);
		builder.append(", memberHeadUrl=");
		builder.append(memberHeadUrl);
		builder.append("]");
		return builder.toString();
	}

}
