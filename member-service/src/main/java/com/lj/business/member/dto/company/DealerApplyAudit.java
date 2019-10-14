package com.lj.business.member.dto.company;

import java.io.Serializable;
import java.util.Map;

public class DealerApplyAudit implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1507905498967729239L;

    /**
     * CODE
     */
    private String code;
    
    /**
     * 审核结果（true审核通过，false审核不通过）
     */
    private Boolean auditResult;
    
    /**
     * 审核人编号
     */
    private String auditNo;

    /**
     * 审核人名称
     */
    private String auditName;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 销售渠道
     */
    private Map<String, Object> mecShopChannelMap;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Boolean auditResult) {
        this.auditResult = auditResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuditNo() {
        return auditNo;
    }

    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public Map<String, Object> getMecShopChannelMap() {
        return mecShopChannelMap;
    }

    public void setMecShopChannelMap(Map<String, Object> mecShopChannelMap) {
        this.mecShopChannelMap = mecShopChannelMap;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DealerApplyAudit [code=");
        builder.append(code);
        builder.append(", auditResult=");
        builder.append(auditResult);
        builder.append(", auditNo=");
        builder.append(auditNo);
        builder.append(", auditName=");
        builder.append(auditName);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", mecShopChannelMap=");
        builder.append(mecShopChannelMap);
        builder.append("]");
        return builder.toString();
    }

}
