package com.lj.business.weixin.dto.smallprogram;

import java.io.Serializable;
import java.util.Date;

public class FindWxSmallProgramReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 8550861455607101229L;
    /**
     * CODE .
     */
    private String code;

    /**
     * 中控微信号 .
     */
    private String noWxZk;

    /**
     * 类型 .
     */
    private String type;

    /**
     * 小程序用户名 .
     */
    private String spAppid;

    /**
     * 小程序名称 .
     */
    private String spName;

    /**
     * 小程序LOGO .
     */
    private String spLogo;

    /**
     * 小程序描述 .
     */
    private String spDesc;

    /**
     * 小程序地址 .
     */
    private String spUrl;

    /**
     * 小程序页面地址 .
     */
    private String spPagePath;

    /**
     * 微信参数 .
     */
    private String wxParam;

    /**
     * 状态：0无效、1有效 .
     */
    private Integer status;

    /**
     * 终端编号 .
     */
    

    /**
     * 终端名称 .
     */
    

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;

    /**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 中控微信号 .
     *
     */
    public String getNoWxZk() {
        return noWxZk;
    }

    /**
     * 中控微信号 .
     *
     */
    public void setNoWxZk(String noWxZk) {
        this.noWxZk = noWxZk == null ? null : noWxZk.trim();
    }

    /**
     * 类型 .
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 类型 .
     *
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 小程序用户名 .
     *
     */
    public String getSpAppid() {
        return spAppid;
    }

    /**
     * 小程序用户名 .
     *
     */
    public void setSpAppid(String spAppid) {
        this.spAppid = spAppid == null ? null : spAppid.trim();
    }

    /**
     * 小程序名称 .
     *
     */
    public String getSpName() {
        return spName;
    }

    /**
     * 小程序名称 .
     *
     */
    public void setSpName(String spName) {
        this.spName = spName == null ? null : spName.trim();
    }

    /**
     * 小程序LOGO .
     *
     */
    public String getSpLogo() {
        return spLogo;
    }

    /**
     * 小程序LOGO .
     *
     */
    public void setSpLogo(String spLogo) {
        this.spLogo = spLogo == null ? null : spLogo.trim();
    }

    /**
     * 小程序描述 .
     *
     */
    public String getSpDesc() {
        return spDesc;
    }

    /**
     * 小程序描述 .
     *
     */
    public void setSpDesc(String spDesc) {
        this.spDesc = spDesc == null ? null : spDesc.trim();
    }

    /**
     * 小程序地址 .
     *
     */
    public String getSpUrl() {
        return spUrl;
    }

    /**
     * 小程序地址 .
     *
     */
    public void setSpUrl(String spUrl) {
        this.spUrl = spUrl == null ? null : spUrl.trim();
    }

    /**
     * 小程序页面地址 .
     *
     */
    public String getSpPagePath() {
        return spPagePath;
    }

    /**
     * 小程序页面地址 .
     *
     */
    public void setSpPagePath(String spPagePath) {
        this.spPagePath = spPagePath == null ? null : spPagePath.trim();
    }

    /**
     * 微信参数 .
     *
     */
    public String getWxParam() {
        return wxParam;
    }

    /**
     * 微信参数 .
     *
     */
    public void setWxParam(String wxParam) {
        this.wxParam = wxParam == null ? null : wxParam.trim();
    }

    /**
     * 状态：0无效、1有效 .
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：0无效、1有效 .
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 商户名称 .
     *
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindWxSmallProgramReturn [code=").append(code);
        builder.append(",noWxZk=").append(noWxZk); 
        builder.append(",type=").append(type); 
        builder.append(",spAppid=").append(spAppid); 
        builder.append(",spName=").append(spName); 
        builder.append(",spLogo=").append(spLogo); 
        builder.append(",spDesc=").append(spDesc); 
        builder.append(",spUrl=").append(spUrl); 
        builder.append(",spPagePath=").append(spPagePath); 
        builder.append(",wxParam=").append(wxParam); 
        builder.append(",status=").append(status); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}
