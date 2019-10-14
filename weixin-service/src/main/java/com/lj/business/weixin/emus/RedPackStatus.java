package com.lj.business.weixin.emus;

public enum RedPackStatus {
	NOSEND("0","未发送"),
	SENDING("1","发送中"),
	SUCCESS("2","发送成功"),
	REVICE("4","已领取"),
	BACK("5","已退回"),
	FAIL("3","发送失败");
	
	private String status;
	
	private String lable;

	private RedPackStatus(String status, String lable) {
		this.status = status;
		this.lable = lable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}
	
	
	

}
