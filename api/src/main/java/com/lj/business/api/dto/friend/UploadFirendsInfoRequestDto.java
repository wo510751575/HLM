package com.lj.business.api.dto.friend;

import java.util.List;

/**
 * 上传朋友圈requestDTO
 * 
 * @author ldq
 *
 */
public class UploadFirendsInfoRequestDto {
	// 商户号
	private String merchantNo;

	private String shopNo;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 设备登录用户
	 */
	private String memberNo;
	
	
	private String memberName;
	/**
	 * 微信号
	 */
	private String noWx;
	/**
	 * 朋友圈信息列表
	 */
	private List<AddFriendsInfoRequestDto> friendsInfos;
	
	/**
	 * 评论数据
	 */
	private List<AddCommentRequestDto> comments;
	
	/**
	 * 点赞数据
	 */
	private List<AddLikesCommentRequestDto> likeComments;
	
	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	

	public List<AddCommentRequestDto> getComments() {
		return comments;
	}

	public void setComments(List<AddCommentRequestDto> comments) {
		this.comments = comments;
	}

	 
	public List<AddLikesCommentRequestDto> getLikeComments() {
		return likeComments;
	}

	public void setLikeComments(List<AddLikesCommentRequestDto> likeComments) {
		this.likeComments = likeComments;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public List<AddFriendsInfoRequestDto> getFriendsInfos() {
		return friendsInfos;
	}

	public void setFriendsInfos(List<AddFriendsInfoRequestDto> friendsInfos) {
		this.friendsInfos = friendsInfos;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadFirendsInfoRequestDto [merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", friendsInfos=");
		builder.append(friendsInfos);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", likeComments=");
		builder.append(likeComments);
		builder.append("]");
		return builder.toString();
	}

}
