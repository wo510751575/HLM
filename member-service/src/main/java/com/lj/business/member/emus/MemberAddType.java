package com.lj.business.member.emus;

public enum MemberAddType {
    MEMBERGM_SCAN(1, "导购扫码"),
    MEMBER_SCAN(2, "客户扫码"),
    MANUAL(3, "手动新增"),
    HOOK(4, "微信自动导入"),
    MOBILE_SEARCH(5, "接口搜索手机号添加"),
    WXNO_SEARCH(6, "接口搜索微信号添加"),
    QQNO_SEARCH(7, "接口搜索QQ号添加")
    ;
    
    private Integer code;
    private String name;
    
    private MemberAddType(Integer code, String name) {
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
