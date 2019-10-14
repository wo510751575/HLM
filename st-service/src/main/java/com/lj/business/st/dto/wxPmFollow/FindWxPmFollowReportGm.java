package com.lj.business.st.dto.wxPmFollow;

import java.io.Serializable;

public class FindWxPmFollowReportGm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 864021262917761040L; 
    
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
        builder.append("DelWxPmFollowReportGm [code=");
        builder.append(code);
        builder.append("]");
        return builder.toString();
    }

}
