package com.lj.business.member.emus;

public enum CompanyPushConfigType {
    GREET("话术、问候语"),
    IMAGE("图片"),
    MGR_CARD("店长名片");

    CompanyPushConfigType(String name){
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
