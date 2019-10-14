package com.lj.business.weixin.dto.publicaccount;

import java.io.Serializable;
import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

public class FindWxPublicAccountPageReturn extends PageParamEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -719863222387065035L; 
    /**
     * CODE .
     */
    private String code;

    /**
     * 中控微信号 .
     */
    private String noWxZk;

    /**
     * 公众号用户名 .
     */
    private String paUsername;

    /**
     * 公众号别名 .
     */
    private String paAlias;

    /**
     * 公众号名称 .
     */
    private String paName;

    /**
     * 公众号LOGO .
     */
    private String paLogo;

    /**
     * 公众号描述 .
     */
    private String paDesc;

    /**
     * 公众号类型 .
     */
    private String paCertflag;

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
     * 微信参数 .
     */
    private String wxParam;

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
     * 公众号用户名 .
     *
     */
    public String getPaUsername() {
        return paUsername;
    }

    /**
     * 公众号用户名 .
     *
     */
    public void setPaUsername(String paUsername) {
        this.paUsername = paUsername == null ? null : paUsername.trim();
    }

    /**
     * 公众号别名 .
     *
     */
    public String getPaAlias() {
        return paAlias;
    }

    /**
     * 公众号别名 .
     *
     */
    public void setPaAlias(String paAlias) {
        this.paAlias = paAlias == null ? null : paAlias.trim();
    }

    /**
     * 公众号名称 .
     *
     */
    public String getPaName() {
        return paName;
    }

    /**
     * 公众号名称 .
     *
     */
    public void setPaName(String paName) {
        this.paName = paName == null ? null : paName.trim();
    }

    /**
     * 公众号LOGO .
     *
     */
    public String getPaLogo() {
        return paLogo;
    }

    /**
     * 公众号LOGO .
     *
     */
    public void setPaLogo(String paLogo) {
        this.paLogo = paLogo == null ? null : paLogo.trim();
    }

    /**
     * 公众号描述 .
     *
     */
    public String getPaDesc() {
        return paDesc;
    }

    /**
     * 公众号描述 .
     *
     */
    public void setPaDesc(String paDesc) {
        this.paDesc = paDesc == null ? null : paDesc.trim();
    }

    /**
     * 公众号类型 .
     *
     */
    public String getPaCertflag() {
        return paCertflag;
    }

    /**
     * 公众号类型 .
     *
     */
    public void setPaCertflag(String paCertflag) {
        this.paCertflag = paCertflag == null ? null : paCertflag.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindWxPublicAccountPageReturn [code=").append(code);
        builder.append(",noWxZk=").append(noWxZk); 
        builder.append(",paUsername=").append(paUsername); 
        builder.append(",paAlias=").append(paAlias); 
        builder.append(",paName=").append(paName); 
        builder.append(",paLogo=").append(paLogo); 
        builder.append(",paDesc=").append(paDesc); 
        builder.append(",paCertflag=").append(paCertflag); 
        builder.append(",status=").append(status); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",wxParam=").append(wxParam); 
        builder.append("]");
        return builder.toString();
    }

}
