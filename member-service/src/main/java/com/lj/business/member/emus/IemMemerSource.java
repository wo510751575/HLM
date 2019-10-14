package com.lj.business.member.emus;

public enum IemMemerSource {
	
	CRM(0, "CRM"),
	JK(1, "聚客"),
	REGISTER(2, "注册"),
	;
	
	IemMemerSource(Integer code, String value){
		this.setCode(code);
		this.value = value;
	}
	
	private Integer code;
	
	private String value;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
	
}
