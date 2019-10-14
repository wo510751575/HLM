package com.lj.business.api.dto.friend;

public class FindFriendsRequestDto {

	

	private String memberNo;

	private String firendsCode;

	private String commentCode;
		
 

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getFirendsCode() {
		return firendsCode;
	}

	public void setFirendsCode(String firendsCode) {
		this.firendsCode = firendsCode;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindFriendsRequestDto [");
		builder.append(" memberNo=");
		builder.append(memberNo);
		builder.append(", firendsCode=");
		builder.append(firendsCode);
		builder.append(", commentCode=");
		builder.append(commentCode);
		builder.append("]");
		return builder.toString();
	}

}
