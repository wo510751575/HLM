package com.lj.business.supcon.dto.friends;

import java.io.Serializable;

public class AddFriendsInfoMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4919898144695373671L;

	private String friendsCode;

	/**
	 * 终端号
	 */
	

	/**
	 * 设备号
	 */
	private String imei;

	/**
	 * 点赞导购
	 */
	private String memberNoGm;

	private String noWxShop;

	private String nickName;

	private String type;

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

	private String headImg;
	private String state;

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
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
 

	public String getFriendsCode() {
		return friendsCode;
	}

	public void setFriendsCode(String friendsCode) {
		this.friendsCode = friendsCode;
	}

	public String getNoWxShop() {
		return noWxShop;
	}

	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}


	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddFriendsInfoMessage [friendsCode=");
		builder.append(friendsCode);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", nickName=");
		builder.append(nickName);
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
		builder.append(", headImg=");
		builder.append(headImg);
		builder.append(", state=");
		builder.append(state);
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
