package com.lj.business.cm.dto;

import java.io.Serializable;

import com.lj.business.cm.emus.SendType;

public class FindGreetClientReturnDto implements Serializable{

	 /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = -5298598431444661933L;
	
    /**
     * 消息标题 .
     */
    private String title;

    /**
     * 消息内容 .
     */
    private String content;

    /**
     * 发送类型
       SINGLE：单人
       MULTI：多人 暂作废
       ALL：所有人 .
     */
    private SendType sendType;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SendType getSendType() {
		return sendType;
	}

	public void setSendType(SendType sendType) {
		this.sendType = sendType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindGreetClientReturnDto [title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", sendType=");
		builder.append(sendType);
		builder.append("]");
		return builder.toString();
	}

}
