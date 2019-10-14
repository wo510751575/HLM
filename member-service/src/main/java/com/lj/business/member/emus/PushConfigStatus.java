package com.lj.business.member.emus;

public enum PushConfigStatus {
    USE("有效"),
    STOP("失效");

    PushConfigStatus(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
