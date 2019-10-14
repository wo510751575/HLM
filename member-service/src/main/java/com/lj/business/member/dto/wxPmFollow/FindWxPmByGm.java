package com.lj.business.member.dto.wxPmFollow;

import java.io.Serializable;
import java.util.Date;

public class FindWxPmByGm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2960055555286654738L;
    
    /**
     * 导购号.
     */
    private String memberNoGm;
    
    /**
     * 客户新增时间-从
     */
    private Date addBeginTime;
    
    /**
     * 客户新增时间-到
     */
    private Date addEndTime;
    
    public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm;
    }

    public Date getAddBeginTime() {
        return addBeginTime;
    }

    public void setAddBeginTime(Date addBeginTime) {
        this.addBeginTime = addBeginTime;
    }

    public Date getAddEndTime() {
        return addEndTime;
    }

    public void setAddEndTime(Date addEndTime) {
        this.addEndTime = addEndTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindWxPmByGm [memberNoGm=");
        builder.append(memberNoGm);
        builder.append(", addBeginTime=");
        builder.append(addBeginTime);
        builder.append(", addEndTime=");
        builder.append(addEndTime);
        builder.append("]");
        return builder.toString();
    }

}
