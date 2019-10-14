package com.lj.business.member.dto;

import java.io.Serializable;

public class FindUnContactPmTypeReturn implements Serializable {
    private static final long serialVersionUID = -8733326701035385498L;
    
    /**
     * 客户分类CODE .
     */
    private String code;

    /**
     * 客户分类名称 .
     */
    private String typeName;
    
    /**
     * 客户分类类型
                 成单客户：SUCCESS
                 已放弃客户：GIVE_UP
                 紧急客户：URGENCY
                 交叉客户：REPEAT
                 意向客户：INTENTION
                 其他：OTHER .
     */
    private String pmTypeType;

    /**
     * 客户数量 .
     */
    private Long numClient;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPmTypeType() {
        return pmTypeType;
    }

    public void setPmTypeType(String pmTypeType) {
        this.pmTypeType = pmTypeType;
    }

    public Long getNumClient() {
        return numClient;
    }

    public void setNumClient(Long numClient) {
        this.numClient = numClient;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindUnContactPmTypeReturn [code=");
        builder.append(code);
        builder.append(", typeName=");
        builder.append(typeName);
        builder.append(", pmTypeType=");
        builder.append(pmTypeType);
        builder.append(", numClient=");
        builder.append(numClient);
        builder.append("]");
        return builder.toString();
    }
    
}
