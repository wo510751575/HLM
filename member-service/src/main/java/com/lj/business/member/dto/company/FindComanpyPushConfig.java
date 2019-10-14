package com.lj.business.member.dto.company;

import java.io.Serializable;

public class FindComanpyPushConfig implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8356712177342155771L; 

    /**
     * CODE .
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
        builder.append("DelComanpyPushConfig [code=");
        builder.append(code);
        builder.append("]");
        return builder.toString();
    }
    
}
