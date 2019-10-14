/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.friends;

import java.io.Serializable;

import com.lj.business.supcon.netty.message.BaseDto;

/**
 * 
 * 类说明：发送朋友圈
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月21日
 */
public class SendFriendsInfo extends BaseDto implements Serializable {

	private static final long serialVersionUID = 8137686593662594429L;
	
	/**
	 * 朋友圈CODE，服务器朋友圈CODE
	 */
	private String code;
	
	/**
	 * 终端微信号
	 */
	private String noWx;
	
	/**
	 * 消息类型：1图文、2纯文字、3分享、4歌曲分享、15视频
	 */
	private String type;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 资源路径：语音、图片、视频
	 */
	private String resources;
	
	/**
	 * 分享标题
	 */
	private String shareTitle;
	
	/**
	 * 分享链接
	 */
	private String shareUrl;
	
    /**
     * 谁可以看类型
     * 1.公开
     * 2.私密
     * 3.部分可见
     * 4.不给谁看
     */
    private String whoType;
    /**
     * 当whoType 为3和4的时候改字段不为空
     * 传客户微信集合以英文','分隔
     */
    private String whoNoWxs;
    /**
     * 提醒谁看集合以英文','分隔
     */
    private String remindNoWxs;
    
	public String getWhoType() {
		return whoType;
	}

	public void setWhoType(String whoType) {
		this.whoType = whoType;
	}

	public String getWhoNoWxs() {
		return whoNoWxs;
	}

	public void setWhoNoWxs(String whoNoWxs) {
		this.whoNoWxs = whoNoWxs;
	}

	public String getRemindNoWxs() {
		return remindNoWxs;
	}

	public void setRemindNoWxs(String remindNoWxs) {
		this.remindNoWxs = remindNoWxs;
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
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the resources
	 */
	public String getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(String resources) {
		this.resources = resources;
	}

	/**
	 * @return the shareTitle
	 */
	public String getShareTitle() {
		return shareTitle;
	}

	/**
	 * @param shareTitle the shareTitle to set
	 */
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	/**
	 * @return the shareUrl
	 */
	public String getShareUrl() {
		return shareUrl;
	}

	/**
	 * @param shareUrl the shareUrl to set
	 */
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SendFriendsInfo [code=");
		builder.append(code);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", type=");
		builder.append(type);
		builder.append(", content=");
		builder.append(content);
		builder.append(", resources=");
		builder.append(resources);
		builder.append(", shareTitle=");
		builder.append(shareTitle);
		builder.append(", shareUrl=");
		builder.append(shareUrl);
		builder.append(", whoType=");
		builder.append(whoType);
		builder.append(", whoNoWxs=");
		builder.append(whoNoWxs);
		builder.append(", remindNoWxs=");
		builder.append(remindNoWxs);
		builder.append("]");
		return builder.toString();
	}

}
