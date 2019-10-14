package com.lj.business.member.emus;

public enum GmAddFlag {
    INITIATIVE(1, "主动添加"),
    PASSIVE(0, "被动添加")
    ;
    
    private Integer code;
    private String name;
    
    private GmAddFlag(Integer code, String name) {
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
