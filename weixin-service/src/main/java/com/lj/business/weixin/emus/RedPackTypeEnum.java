package com.lj.business.weixin.emus;

public enum RedPackTypeEnum {
   //1普通红包，2加好友红包, 8收取客户红包，9收取客户转账，10发送转账 等
	NORMAL("1", "普通红包"),
	ADD("2", "加好友红包"),
	GETREDPACK("8", "收取客户红包"),
	GETTRANS("9", "收取客户转账"),
	TRANS("10", "发送转账");

	private String type;

	private String lable;

	private RedPackTypeEnum(String type, String lable) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

}
