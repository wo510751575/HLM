package com.lj.business.cf.emus;

public enum CallStatusEmus {
	
	CALL_IN("1", "来电"),
	CALL_TO("2", "已播"),
	MISSED("3", "未接"),
	VOICE("4", "语音"),
	REFUSE("5", "拒接"),
	BLOCK("6", "阻塞")
	;
	
	CallStatusEmus(String status, String name){
		this.status = status;
		this.name = name;
	}
	
	private String status;
	
	private String name;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
