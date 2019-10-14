package com.lj.business.weixin.domain;

import java.util.Date;

public class ImEmojiPackage {
    /**
     * CODE .
     */
    private String code;

    /**
     * 表情包名称 .
     */
    private String packageName;
    
    /**
     * 表情包LOGO .
     */
    private String packageLogo;

    /**
     * 版本号 .
     */
    private Long version;

    /**
     * 状态：0无效、1有效 .
     */
    private Integer status;

    /**
     * 显示序号 .
     */
    private Integer showIndex;

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
	 * @return the packageLogo
	 */
	public String getPackageLogo() {
		return packageLogo;
	}

	/**
	 * @param packageLogo the packageLogo to set
	 */
	public void setPackageLogo(String packageLogo) {
		this.packageLogo = packageLogo;
	}

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
     * 表情包名称 .
     *
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * 表情包名称 .
     *
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    /**
     * 版本号 .
     *
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号 .
     *
     */
    public void setVersion(Long version) {
        this.version = version;
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
     * 显示序号 .
     *
     */
    public Integer getShowIndex() {
        return showIndex;
    }

    /**
     * 显示序号 .
     *
     */
    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImEmojiPackage [code=");
		builder.append(code);
		builder.append(", packageName=");
		builder.append(packageName);
		builder.append(", packageLogo=");
		builder.append(packageLogo);
		builder.append(", version=");
		builder.append(version);
		builder.append(", status=");
		builder.append(status);
		builder.append(", showIndex=");
		builder.append(showIndex);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append("]");
		return builder.toString();
	}
    
}