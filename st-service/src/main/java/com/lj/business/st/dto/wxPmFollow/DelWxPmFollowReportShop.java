package com.lj.business.st.dto.wxPmFollow;

import java.io.Serializable;

public class DelWxPmFollowReportShop implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6174468035185120007L; 
    
    /**
     * CODE
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DelWxPmFollowReportShop [code=");
        builder.append(code);
        builder.append("]");
        return builder.toString();
    }

}
