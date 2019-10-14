package com.lj.business.member.dto.wxPmFollow;

import java.io.Serializable;

public class FindWxPmByGmReturn implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4552057863649600338L;
    
    /**
     * 对应统计的客户数
     */
    private Long num;

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindWxPmByGmReturn [num=");
        builder.append(num);
        builder.append("]");
        return builder.toString();
    }

}
