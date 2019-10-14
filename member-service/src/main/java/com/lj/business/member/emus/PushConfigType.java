package com.lj.business.member.emus;

public enum PushConfigType {
	
	/**
	 * 话术、问候语
	 */
    GREET("话术、问候语"),
    
    /**
     * 图片
     */
    IMAGE("图片"),
    
    /**
     * 店长名片
     */
//    MGR_CARD("店长名片"),
    
    /**
     * 导购名片（只能认领后推送）
     */
    GM_CARD("导购名片"),
    
    /**
     * 分享
     */
    SHARE("分享"),
    
    /**
     * 公众号
     */
    PA("公众号"),
    
    /**
     * 小程序
     */
    SP("小程序"),
    /**
	 *问候语(帶动态链接)
	 */
    LINK("问候语(帶动态链接)"),
    ;

    PushConfigType(String name){
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
