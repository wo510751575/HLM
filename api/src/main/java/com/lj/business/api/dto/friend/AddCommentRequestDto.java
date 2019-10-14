package com.lj.business.api.dto.friend;

public class AddCommentRequestDto {

	/**
	 * 朋友圈ID .
	 */
	private String friendsId;
	/**
	 * 评论人微信号
	 */
	private String noWx;
	/**
	 * 评论人昵称
	 */
	private String nickName;

	/**
	 * 评论内容
	 */
	private String content;

	/**
	 * 时间搓
	 */
	private String createTime;

	/**
	 * 回复人微信号 .
	 */
	private String tousername;

	/**
	 * 回复人昵称 .
	 */
	private String tonickname;


	private String commentCode;


	private String noWxShop;

	public String getNoWxShop() {
		return noWxShop;
	}

	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}
	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public String getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	public String getTonickname() {
		return tonickname;
	}

	public void setTonickname(String tonickname) {
		this.tonickname = tonickname;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AddCommentRequestDto{");
		sb.append("friendsId='").append(friendsId).append('\'');
		sb.append(", noWx='").append(noWx).append('\'');
		sb.append(", nickName='").append(nickName).append('\'');
		sb.append(", content='").append(content).append('\'');
		sb.append(", createTime='").append(createTime).append('\'');
		sb.append(", tousername='").append(tousername).append('\'');
		sb.append(", tonickname='").append(tonickname).append('\'');
		sb.append(", commentCode='").append(commentCode).append('\'');
		sb.append(", noWxShop='").append(noWxShop).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
