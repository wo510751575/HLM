package com.lj.business.st.dto.wxPmFollow;

import java.io.Serializable;

public class FindWxPmFollowReportCompany implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2265089477058521247L; 
    
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
        builder.append("DelWxPmFollowReportCompany [code=");
        builder.append(code);
        builder.append("]");
        return builder.toString();
    }

}
