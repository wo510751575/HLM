package com.lj.business.weixin.dto;

import java.io.Serializable;

public class ToFriendsInfosDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7535009118027750421L;
	
	/**
     * 朋友圈ID .
     */
    private String friendsId;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 终端微信 .
     */
    private String noWxShop;

    /**
     * 导购编号
     */
    private String memberNoGm;

    /**
     * 微信昵称 .
     */
    private String nickName;

    /**
     * 发朋友圈时间戳 .
     */
    private String timestamp;

    /**
     * 资源类型 .
     */
    private String sourcetype;

    /**
     * 类型1图文 2纯文字 3分享 4歌曲分享 15视频
              .
     */
    private String type;

    /**
     *  .
     */
    private String shareurl;

    /**
     *  .
     */
    private String sharetitle;

    /**
     * 图片地址（多个时以逗号分隔） .
     */
    private String imgAddr;

    /**
     * 操作人标识：1自己、2客户 .
     */
    private Integer optFlag;

 

    /**
     * 消息状态：1 发送中、2发送成功、3发送失败 .
     */
    private Integer status;

    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 发送朋友圈任务CODE .
     */
    private String jobCode;

    /**
     * 自动评论内容 .
     */
    private String autoContent;
    /**
     * 内容 .
     */
    private String content;
    
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

	public String getFriendsId() {
		return friendsId;
	}



	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}



	public String getMerchantNo() {
		return merchantNo;
	}



	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}



	public String getMerchantName() {
		return merchantName;
	}



	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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



	public String getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}



	public String getSourcetype() {
		return sourcetype;
	}



	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getShareurl() {
		return shareurl;
	}



	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}



	public String getSharetitle() {
		return sharetitle;
	}



	public void setSharetitle(String sharetitle) {
		this.sharetitle = sharetitle;
	}



	public String getImgAddr() {
		return imgAddr;
	}



	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}



	public Integer getOptFlag() {
		return optFlag;
	}



	public void setOptFlag(Integer optFlag) {
		this.optFlag = optFlag;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public String getImei() {
		return imei;
	}



	public void setImei(String imei) {
		this.imei = imei;
	}



	public String getJobCode() {
		return jobCode;
	}



	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}



	public String getAutoContent() {
		return autoContent;
	}



	public void setAutoContent(String autoContent) {
		this.autoContent = autoContent;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
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
		builder.append("ToFriendsInfosDto [friendsId=");
		builder.append(friendsId);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", sourcetype=");
		builder.append(sourcetype);
		builder.append(", type=");
		builder.append(type);
		builder.append(", shareurl=");
		builder.append(shareurl);
		builder.append(", sharetitle=");
		builder.append(sharetitle);
		builder.append(", imgAddr=");
		builder.append(imgAddr);
		builder.append(", optFlag=");
		builder.append(optFlag);
		builder.append(", status=");
		builder.append(status);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", jobCode=");
		builder.append(jobCode);
		builder.append(", autoContent=");
		builder.append(autoContent);
		builder.append(", content=");
		builder.append(content);
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
