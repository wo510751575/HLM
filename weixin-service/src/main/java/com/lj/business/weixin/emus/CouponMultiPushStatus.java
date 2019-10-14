package com.lj.business.weixin.emus;

public enum CouponMultiPushStatus {

    INIT(1,"创建"),
    SENDING(2,"执行中"),
    SUCCESS(3,"执行完成"),
    BREAK(4,"中断");
    
    CouponMultiPushStatus(Integer code,String name){
        this.code = code;
        this.name = name;
    }
    
    private Integer code;
    private String name;
    
    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
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
