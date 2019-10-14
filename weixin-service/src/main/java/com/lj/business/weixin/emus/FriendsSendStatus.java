package com.lj.business.weixin.emus;

public enum FriendsSendStatus {
	SENDING(1, "发送中"), SEND_SUCCESS(2, "发送成功"), SEND_FAIL(3, "发送失败");

	private Integer status;
	private String name;

	private FriendsSendStatus(Integer status, String name) {
		this.status = status;
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
