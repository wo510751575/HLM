package com.lj.business.member.emus;

public enum WxFriends {
    YES(1, "是"),
    NO(2, "否")
    ;
    
    private Integer code;
    private String name;
    
    private WxFriends(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
