package com.lj.business.supcon.netty.message.redpacket;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 封装发送红包的content字段为json格式
 */

public class RedPacketContentResponse extends BaseResponse {


    /**
     * 红包code
     */
    private String code;

    /**
     * 红包类型
     */
    private String type;

    /**
     * 红包金额（分）
     */
    private long amt;

    /**
     * 红包描述
     */
    private String des;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAmt() {
        return amt;
    }

    public void setAmt(long amt) {
        this.amt = amt;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
