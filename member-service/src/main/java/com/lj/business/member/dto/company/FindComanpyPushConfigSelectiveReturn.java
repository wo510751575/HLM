package com.lj.business.member.dto.company;

import java.io.Serializable;
import java.util.Date;

public class FindComanpyPushConfigSelectiveReturn implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7813590913247282906L;

    /**
     * CODE .
     */
    private String code;

    /**
     * 经销商编号 .
     */
    private String companyNo;

    /**
     * 经销商名称 .
     */
    private String companyName;

    /**
     * 状态：USE有效、STOP失效 .
     */
    private String status;

    /**
     * 排序（如果有多条，推送的顺序从小到大） .
     */
    private Integer sort;

    /**
     * 类型：SHOP_LINK商城链接  GREET问候语 IMAGE图片 MGR_CARD店长名片 .
     */
    private String type;

    /**
     * 内容 .
     */
    private String content;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     * 备注 .
     */
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindComanpyPushConfigSelectiveReturn [code=");
        builder.append(code);
        builder.append(", companyNo=");
        builder.append(companyNo);
        builder.append(", companyName=");
        builder.append(companyName);
        builder.append(", status=");
        builder.append(status);
        builder.append(", sort=");
        builder.append(sort);
        builder.append(", type=");
        builder.append(type);
        builder.append(", content=");
        builder.append(content);
        builder.append(", merchantNo=");
        builder.append(merchantNo);
        builder.append(", createDate=");
        builder.append(createDate);
        builder.append(", updateDate=");
        builder.append(updateDate);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }
    
}
