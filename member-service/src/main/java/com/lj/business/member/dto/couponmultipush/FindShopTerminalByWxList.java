package com.lj.business.member.dto.couponmultipush;

import java.io.Serializable;
import java.util.List;

public class FindShopTerminalByWxList implements Serializable {
    private static final long serialVersionUID = -8286767101548477531L;

    private List<String> noWxList;

    public List<String> getNoWxList() {
        return noWxList;
    }

    public void setNoWxList(List<String> noWxList) {
        this.noWxList = noWxList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindShopTerminalByWxList [noWxList=");
        builder.append(noWxList);
        builder.append("]");
        return builder.toString();
    }
    
}
