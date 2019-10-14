package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;

public class FindAutoAnswerChatInfoReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4200856505991091030L; 

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
     * 内容 .
     */
    private String content;

	public Integer getSenderFlag() {
		return senderFlag;
	}

	public void setSenderFlag(Integer senderFlag) {
		this.senderFlag = senderFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindAutoAnswerChatInfoReturn [senderFlag=");
		builder.append(senderFlag);
		builder.append(", type=");
		builder.append(type);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}

}
