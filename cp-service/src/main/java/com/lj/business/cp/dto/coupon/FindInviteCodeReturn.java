package com.lj.business.cp.dto.coupon;

import java.io.Serializable;

public class FindInviteCodeReturn implements Serializable {
    private static final long serialVersionUID = 2254437830138741981L;

    private String inviteCode;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindInviteCodeReturn [inviteCode=");
        builder.append(inviteCode);
        builder.append("]");
        return builder.toString();
    }

}
