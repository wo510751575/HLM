package com.lj.business.member.domain;

import java.util.Date;

public class TerminalImStatus {
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;
    /**
     * 终端类型：GM导购、ZK中控 .
     */
    private String terminalType;

    /**
     * 终端编码 .
     */
    private String terminalCode;

    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 是否在线：0离线、1在线 .
     */
    private Integer onlineFlag;

    /**
     * 登录微信号 .
     */
    private String noWx;

    /**
     * 登录微信昵称 .
     */
    private String wxNickname;

    /**
     * 登录导购编号 .
     */
    private String memberNoGm;

    /**
     * 登录导购姓名 .
     */
    private String memberName;

    /**
     * 登录时间 .
     */
    private Date loginTime;

    /**
     * 终端电量（百分比） .
     */
    private Integer batteryLevel;

    /**
     * 创建人 .
     */
    private String createId;

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
     * 终端类型：GM导购、ZK中控 .
     *
     */
    public String getTerminalType() {
        return terminalType;
    }

    /**
     * 终端类型：GM导购、ZK中控 .
     *
     */
    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType == null ? null : terminalType.trim();
    }

    /**
     * 终端编码 .
     *
     */
    public String getTerminalCode() {
        return terminalCode;
    }

    /**
     * 终端编码 .
     *
     */
    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode == null ? null : terminalCode.trim();
    }

    /**
     * 手机串号 .
     *
     */
    public String getImei() {
        return imei;
    }

    /**
     * 手机串号 .
     *
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     * 是否在线：0离线、1在线 .
     *
     */
    public Integer getOnlineFlag() {
        return onlineFlag;
    }

    /**
     * 是否在线：0离线、1在线 .
     *
     */
    public void setOnlineFlag(Integer onlineFlag) {
        this.onlineFlag = onlineFlag;
    }

    /**
     * 登录微信号 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 登录微信号 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     * 登录微信昵称 .
     *
     */
    public String getWxNickname() {
        return wxNickname;
    }

    /**
     * 登录微信昵称 .
     *
     */
    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname == null ? null : wxNickname.trim();
    }

    /**
     * 登录导购编号 .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 登录导购编号 .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 登录导购姓名 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 登录导购姓名 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 登录时间 .
     *
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 登录时间 .
     *
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 终端电量（百分比） .
     *
     */
    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * 终端电量（百分比） .
     *
     */
    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
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
        builder.append("TerminalImStatus [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",terminalType=").append(terminalType); 
        builder.append(",terminalCode=").append(terminalCode); 
        builder.append(",imei=").append(imei); 
        builder.append(",onlineFlag=").append(onlineFlag); 
        builder.append(",noWx=").append(noWx); 
        builder.append(",wxNickname=").append(wxNickname); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",loginTime=").append(loginTime); 
        builder.append(",batteryLevel=").append(batteryLevel); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}