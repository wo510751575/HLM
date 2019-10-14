package com.lj.business.member.dto.service.person;

import java.io.Serializable;


public class FindServicePersonAppReturn implements Serializable{

	private static final long serialVersionUID = 1868863959762766697L;

	/**
     * 人员编号 .
     */
    private String personNo;

    /**
     * 人员姓名 .
     */
    private String personName;

    /**
     * 职称 .
     */
    private String title;

    /**
     * 服务价格 .
     */
    private Long servicePrice;
    
    /**
     * 头像地址
     */
    private String headAddress;

    /**
     * 标签，多个用英文逗号,隔开 .
     */
    private String hcLabel;

    /**
     * 简介 .
     */
    private String summary;

    /**
     * 人员编号 .
     *
     */
    public String getPersonNo() {
        return personNo;
    }

    /**
     * 人员编号 .
     *
     */
    public void setPersonNo(String personNo) {
        this.personNo = personNo == null ? null : personNo.trim();
    }

    /**
     * 人员姓名 .
     *
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 人员姓名 .
     *
     */
    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    /**
     * 职称 .
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * 职称 .
     *
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 服务价格 .
     *
     */
    public Long getServicePrice() {
        return servicePrice;
    }

    /**
     * 服务价格 .
     *
     */
    public void setServicePrice(Long servicePrice) {
        this.servicePrice = servicePrice;
    }

    /**
     * 头像地址
     */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
     * 头像地址
     */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
     * 标签，多个用英文逗号,隔开 .
     *
     */
    public String getHcLabel() {
        return hcLabel;
    }

    /**
     * 标签，多个用英文逗号,隔开 .
     *
     */
    public void setHcLabel(String hcLabel) {
        this.hcLabel = hcLabel == null ? null : hcLabel.trim();
    }

    /**
     * 简介 .
     *
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 简介 .
     *
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindServicePersonAppReturn [personNo=");
		builder.append(personNo);
		builder.append(", personName=");
		builder.append(personName);
		builder.append(", title=");
		builder.append(title);
		builder.append(", servicePrice=");
		builder.append(servicePrice);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", hcLabel=");
		builder.append(hcLabel);
		builder.append(", summary=");
		builder.append(summary);
		builder.append("]");
		return builder.toString();
	}

}