package com.lj.business.api.dto;

public class WxAuthDto {
	
	
	private String redirectUri;
	
	
	
	private String memberNo;
	
	private String parmas;
	
	

	public String getParmas() {
		return parmas;
	}

	public void setParmas(String parmas) {
		this.parmas = parmas;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxAuthDto [redirectUri=");
		builder.append(redirectUri);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", parmas=");
		builder.append(parmas);
		builder.append("]");
		return builder.toString();
	}

}
