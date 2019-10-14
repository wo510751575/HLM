package com.lj.business.member.domain;

import java.util.Date;

public class FitUpInfo {
    /**
     * CODE .
     */
    private String code;

    /**
     * 风格             北欧：BEI_OU             新中式：XIN_ZHONG_SHI              .
     */
    private String style;

    /**
     * 小区全称 .
     */
    private String fullName;

    /**
     * 户型 .
     */
    private String houseType;

    /**
     * 手机号 .
     */
    private String mobile;

    /**
     * 图片地址，逗号分隔 .
     */
    private String imgAddr;

    /**
     * 状态              NORMAL正常              .
     */
    private String status;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
     * 风格             北欧：BEI_OU             新中式：XIN_ZHONG_SHI              .
     *
     */
    public String getStyle() {
        return style;
    }

    /**
     * 风格             北欧：BEI_OU             新中式：XIN_ZHONG_SHI              .
     *
     */
    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    /**
     * 小区全称 .
     *
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 小区全称 .
     *
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     * 户型 .
     *
     */
    public String getHouseType() {
        return houseType;
    }

    /**
     * 户型 .
     *
     */
    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
    }

    /**
     * 手机号 .
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号 .
     *
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 图片地址，逗号分隔 .
     *
     */
    public String getImgAddr() {
        return imgAddr;
    }

    /**
     * 图片地址，逗号分隔 .
     *
     */
    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
    }

    /**
     * 状态              NORMAL正常              .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态              NORMAL正常              .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FitUpInfo [code=").append(code);
        builder.append(",style=").append(style); 
        builder.append(",fullName=").append(fullName); 
        builder.append(",houseType=").append(houseType); 
        builder.append(",mobile=").append(mobile); 
        builder.append(",imgAddr=").append(imgAddr); 
        builder.append(",status=").append(status); 
        builder.append(",remark=").append(remark); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}