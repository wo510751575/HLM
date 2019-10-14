package com.lj.business.api.dto.friend;

import java.io.Serializable;
import java.util.List;

/**
 * 上传朋友圈信息
 * 
 * @author ldq
 *
 */
public class AddFriendsInfoRequestDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 朋友圈ID .
	 */
	private String snsId;

	private String rawXML;

	/**
	 * 微信号 .
	 */
	private String userName;

	/**
	 * 微信昵称 .
	 */
	private String nickName;

	/**
	 * 发朋友圈时间戳 .
	 */
	private String createTime;

	private String subType;
	/**
	 * 点赞数
	 */
	private String likeNum;
	/**
	 * 评论数
	 */
	private String commentsNum;
	/**
	 * 资源类型 .
	 */
	private String sourcetype;

	/**
	 * 类型1图文 2纯文字 3分享 4歌曲分享 5MV视频分享 15视频 .
	 */
	private String type;
	/**
	 * .分享URL
	 */
	private String shareUrl;

	/**
	 * .分享标题
	 */
	private String shareTitle;

	/**
	 * 图片地址（多个时以逗号分隔） .
	 */
//	private String imgAddr;

	/**
	 * 操作人标识：1自己、2客户 .
	 */
	private Integer optFlag;
	/**
	 * 手机串号 .
	 */
	private String imei;
	/**
	 * 内容 .
	 */
	private String content;
	
	/**
	 * 1 导购发送朋友圈 2 客户发送
	 */
	private String direction ;
	/**
	 * 资源信息
	 */
	private String mediasUrl;
	
	private String imgUrls;
	
	private String imgStatus;

	private String memberNo;
	
	
	private String commentsJson;
	
	private String likesJson;
	
	
	private String enckey;
	
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

	public String getEnckey() {
		return enckey;
	}

	public void setEnckey(String enckey) {
		this.enckey = enckey;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	private List<AddLikesCommentRequestDto> comments;

	public String getSnsId() {
		return snsId;
	}

	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}

	public String getRawXML() {
		return rawXML;
	}

	public void setRawXML(String rawXML) {
		this.rawXML = rawXML;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(String likeNum) {
		this.likeNum = likeNum;
	}

	public String getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(String commentsNum) {
		this.commentsNum = commentsNum;
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

 
	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public Integer getOptFlag() {
		return optFlag;
	}

	public void setOptFlag(Integer optFlag) {
		this.optFlag = optFlag;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	 
	public List<AddLikesCommentRequestDto> getComments() {
		return comments;
	}

	public void setComments(List<AddLikesCommentRequestDto> comments) {
		this.comments = comments;
	}

	 

	public String getMediasUrl() {
		return mediasUrl;
	}

	public void setMediasUrl(String mediasUrl) {
		this.mediasUrl = mediasUrl;
	}
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	 

	public String getCommentsJson() {
		return commentsJson;
	}

	public void setCommentsJson(String commentsJson) {
		this.commentsJson = commentsJson;
	}

	public String getLikesJson() {
		return likesJson;
	}

	public void setLikesJson(String likesJson) {
		this.likesJson = likesJson;
	}

	 

 

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

 

	public String getImgStatus() {
		return imgStatus;
	}

	public void setImgStatus(String imgStatus) {
		this.imgStatus = imgStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddFriendsInfoRequestDto [snsId=");
		builder.append(snsId);
		builder.append(", rawXML=");
		builder.append(rawXML);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", subType=");
		builder.append(subType);
		builder.append(", likeNum=");
		builder.append(likeNum);
		builder.append(", commentsNum=");
		builder.append(commentsNum);
		builder.append(", sourcetype=");
		builder.append(sourcetype);
		builder.append(", type=");
		builder.append(type);
		builder.append(", shareUrl=");
		builder.append(shareUrl);
		builder.append(", shareTitle=");
		builder.append(shareTitle);
		builder.append(", optFlag=");
		builder.append(optFlag);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", content=");
		builder.append(content);
		builder.append(", direction=");
		builder.append(direction);
		builder.append(", mediasUrl=");
		builder.append(mediasUrl);
		builder.append(", imgUrls=");
		builder.append(imgUrls);
		builder.append(", imgStatus=");
		builder.append(imgStatus);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", commentsJson=");
		builder.append(commentsJson);
		builder.append(", likesJson=");
		builder.append(likesJson);
		builder.append(", enckey=");
		builder.append(enckey);
		builder.append(", whoType=");
		builder.append(whoType);
		builder.append(", whoNoWxs=");
		builder.append(whoNoWxs);
		builder.append(", remindNoWxs=");
		builder.append(remindNoWxs);
		builder.append(", comments=");
		builder.append(comments);
		builder.append("]");
		return builder.toString();
	}

}
