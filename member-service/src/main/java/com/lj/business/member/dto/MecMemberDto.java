package com.lj.business.member.dto;

import java.io.Serializable;

public class MecMemberDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2614510968671306443L;
	
	/**
	 * 微信昵称
	 */
	private String nickNameWx;
	
	/**
	 * 微信头像
	 */
	private String headImage;
	
	/**
	 * 手机号码(必填)
	 */
	private String mobile;
	
	/**
	 * 终端编号(必填)
	 */
	
	
	/**
	 * 微信号
	 */
	private String noWx;
	/**
	 * 会员编号
	 */
	private String memberNo;
	
	/**
	 * openId
	 */
	private String openId;
	
	/**
	 * 如果根据上述参数没有找到客户信息，是否新建客户，默认为不新建
	 */
	private boolean addMbrBool = Boolean.FALSE;

	public String getNickNameWx() {
		return nickNameWx;
	}

	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the addMbrBool
	 */
	public boolean isAddMbrBool() {
		return addMbrBool;
	}

	/**
	 * @param addMbrBool the addMbrBool to set
	 */
	public void setAddMbrBool(boolean addMbrBool) {
		this.addMbrBool = addMbrBool;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MecMemberDto [nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", headImage=");
		builder.append(headImage);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", openId=");
		builder.append(openId);
		builder.append(", addMbrBool=");
		builder.append(addMbrBool);
		builder.append("]");
		return builder.toString();
	}

}
