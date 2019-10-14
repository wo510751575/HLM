package com.lj.business.weixin.dto.imemoji;

import java.io.Serializable;
import java.util.Date;

public class AddImEmojiPackage implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 2212960602320221232L;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddImEmojiPackage [code=");
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
