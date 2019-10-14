package com.ye.business.ad.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AdvertiseViewDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3680073175614359266L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 商户编号 .
	 */
	private String merchantNo;

	/**
	 * 广告code .
	 */
	private String advertiseCode;

	/**
	 * 创建人 .
	 */
	private String createId;

	/**
	 * 创建人名称 .
	 */
	private String cretaeName;

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
	 * 最后更新时间 .
	 */
	private Date updateTime;

	private Date checkTime;
	private Date startTime;
	private Date endTime;
	/**
	 * 展示单价 .
	 */
	private Integer priceView;

	private List<String> advertiseCodeList;

	/**
	 * 数量
	 */
	private Integer num;
	

    /**
     * 文章code
     */
    private String articleCode;

	public Integer getPriceView() {
		return priceView;
	}

	public void setPriceView(Integer priceView) {
		this.priceView = priceView;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	 * 广告code .
	 *
	 */
	public String getAdvertiseCode() {
		return advertiseCode;
	}

	/**
	 * 广告code .
	 *
	 */
	public void setAdvertiseCode(String advertiseCode) {
		this.advertiseCode = advertiseCode == null ? null : advertiseCode.trim();
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
	 * 创建人名称 .
	 *
	 */
	public String getCretaeName() {
		return cretaeName;
	}

	/**
	 * 创建人名称 .
	 *
	 */
	public void setCretaeName(String cretaeName) {
		this.cretaeName = cretaeName == null ? null : cretaeName.trim();
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
	 * 最后更新时间 .
	 *
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 最后更新时间 .
	 *
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<String> getAdvertiseCodeList() {
		return advertiseCodeList;
	}

	public void setAdvertiseCodeList(List<String> advertiseCodeList) {
		this.advertiseCodeList = advertiseCodeList;
	}

	/**
	 * 输出BEAN数据信息
	 * 
	 * @author LeoPeng
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdvertiseShow [code=").append(code);
		builder.append(",merchantNo=").append(merchantNo);
		builder.append(",advertiseCode=").append(advertiseCode);
		builder.append(",createId=").append(createId);
		builder.append(",cretaeName=").append(cretaeName);
		builder.append(",createDate=").append(createDate);
		builder.append(",remark=").append(remark);
		builder.append(",remark2=").append(remark2);
		builder.append(",remark3=").append(remark3);
		builder.append(",remark4=").append(remark4);
		builder.append(",updateTime=").append(updateTime);
		builder.append("]");
		return builder.toString();
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

}
