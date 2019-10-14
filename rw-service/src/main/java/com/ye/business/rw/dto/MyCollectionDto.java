package com.ye.business.rw.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MyCollectionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//////////////////////////////////////////////
	private String memberNoGuidSource;
	private String shopNoSource;
	private String merchantNoSource;

	private List<String> codeList;
	//////////////////////////////////////////////

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 员工编号 .
	 */
	private String memberNoGuid;

	/**
	 * 员工姓名 .
	 */
	private String memberNameGuid;

	/**
	 * 门诊编号 .
	 */
	private String shopNo;

	/**
	 * 门诊名称 .
	 */
	private String shopName;

	/**
	 * 商户编号 .
	 */
	private String merchantNo;

	/**
	 * 商户名称 .
	 */
	private String merchantName;

	/**
	 * 文章类型：HOT-热文；MY-用户；OTHER：其它 .
	 */
	private String type;

	/**
	 * 文章分类code
	 */
	private String typeCode;

	/**
	 * 文章主图 .
	 */
	private String mainImage;

	/**
	 * 文章标题 .
	 */
	private String title;

	/**
	 * 文章来源： .
	 */
	private String source;

	/**
	 * 备注 .
	 */
	private String remark;

	/**
	 * 阅读数 .
	 */
	private Integer readNum;

	/**
	 * 点赞数 .
	 */
	private Integer likeNum;

	/**
	 * 创建人 .
	 */
	private String createId;

	/**
	 * 创建时间 .
	 */
	private Date createDate;

	/**
	 * 用户编号 .
	 */
	private String memberNo;

	/**
	 * 状态：normal-正常；forbid-禁用；review-审核 .
	 */
	private String rwState;

	/**
	 * 访问路径 .
	 */
	private String webUrl;

	/**
	 * 文章内容 .
	 */
	private String articleHtml;

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
	 * 员工编号 .
	 *
	 */
	public String getMemberNoGuid() {
		return memberNoGuid;
	}

	/**
	 * 员工编号 .
	 *
	 */
	public void setMemberNoGuid(String memberNoGuid) {
		this.memberNoGuid = memberNoGuid == null ? null : memberNoGuid.trim();
	}

	/**
	 * 员工姓名 .
	 *
	 */
	public String getMemberNameGuid() {
		return memberNameGuid;
	}

	/**
	 * 员工姓名 .
	 *
	 */
	public void setMemberNameGuid(String memberNameGuid) {
		this.memberNameGuid = memberNameGuid == null ? null : memberNameGuid.trim();
	}

	/**
	 * 门诊编号 .
	 *
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * 门诊编号 .
	 *
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo == null ? null : shopNo.trim();
	}

	/**
	 * 门诊名称 .
	 *
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * 门诊名称 .
	 *
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
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
	 * 文章类型：HOT-热文；MY-用户；OTHER：其它 .
	 *
	 */
	public String getType() {
		return type;
	}

	/**
	 * 文章类型：HOT-热文；MY-用户；OTHER：其它 .
	 *
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * 文章主图 .
	 *
	 */
	public String getMainImage() {
		return mainImage;
	}

	/**
	 * 文章主图 .
	 *
	 */
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage == null ? null : mainImage.trim();
	}

	/**
	 * 文章标题 .
	 *
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 文章标题 .
	 *
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * 文章来源： .
	 *
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 文章来源： .
	 *
	 */
	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
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
	 * 阅读数 .
	 *
	 */
	public Integer getReadNum() {
		return readNum;
	}

	/**
	 * 阅读数 .
	 *
	 */
	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	/**
	 * 点赞数 .
	 *
	 */
	public Integer getLikeNum() {
		return likeNum;
	}

	/**
	 * 点赞数 .
	 *
	 */
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
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
	 * 用户编号 .
	 *
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * 用户编号 .
	 *
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo == null ? null : memberNo.trim();
	}

	/**
	 * 状态：normal-正常；forbid-禁用；review-审核 .
	 *
	 */
	public String getRwState() {
		return rwState;
	}

	/**
	 * 状态：normal-正常；forbid-禁用；review-审核 .
	 *
	 */
	public void setRwState(String rwState) {
		this.rwState = rwState == null ? null : rwState.trim();
	}

	/**
	 * 访问路径 .
	 *
	 */
	public String getWebUrl() {
		return webUrl;
	}

	/**
	 * 访问路径 .
	 *
	 */
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl == null ? null : webUrl.trim();
	}

	/**
	 * 文章内容 .
	 *
	 */
	public String getArticleHtml() {
		return articleHtml;
	}

	/**
	 * 文章内容 .
	 *
	 */
	public void setArticleHtml(String articleHtml) {
		this.articleHtml = articleHtml == null ? null : articleHtml.trim();
	}

	public String getMemberNoGuidSource() {
		return memberNoGuidSource;
	}

	public void setMemberNoGuidSource(String memberNoGuidSource) {
		this.memberNoGuidSource = memberNoGuidSource;
	}

	public String getShopNoSource() {
		return shopNoSource;
	}

	public void setShopNoSource(String shopNoSource) {
		this.shopNoSource = shopNoSource;
	}

	public String getMerchantNoSource() {
		return merchantNoSource;
	}

	public void setMerchantNoSource(String merchantNoSource) {
		this.merchantNoSource = merchantNoSource;
	}

	/**
	 * 输出BEAN数据信息
	 * 
	 * @author LeoPeng
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Article [code=").append(code);
		builder.append(",memberNoGuid=").append(memberNoGuid);
		builder.append(",memberNameGuid=").append(memberNameGuid);
		builder.append(",shopNo=").append(shopNo);
		builder.append(",shopName=").append(shopName);
		builder.append(",merchantNo=").append(merchantNo);
		builder.append(",merchantName=").append(merchantName);
		builder.append(",type=").append(type);
		builder.append(",mainImage=").append(mainImage);
		builder.append(",title=").append(title);
		builder.append(",source=").append(source);
		builder.append(",remark=").append(remark);
		builder.append(",readNum=").append(readNum);
		builder.append(",likeNum=").append(likeNum);
		builder.append(",createId=").append(createId);
		builder.append(",createDate=").append(createDate);
		builder.append(",articleHtml=").append(articleHtml);
		builder.append("]");
		return builder.toString();
	}

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

}
