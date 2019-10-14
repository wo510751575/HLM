package com.lj.business.member.domain;

import java.util.Date;

public class ComanpyPushConfig {
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
        this.code = code == null ? null : code.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }
}