package com.ye.business.rw.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ArticleShareDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3497395210510606676L;

///////////////////////////////
	private String beginDate;
	private String endDate;

	// 阅读次数
	private Integer readNum;
	// 查询条件不为空
	private String hasMemberNoGuidViewNotEmpty;

	private String hasGroupArticleCode;

	private ArticleDto article;

	/**
	 * 昵称 .
	 */
	private String nickName;

	/**
	 * 头像地址 .
	 */
	private String headAddress;

	private List<String> memberNoGuidList;

	/** 开始时间. */
	private Date startTime;

	/** 结束时间. */
	private Date endTime;

	/**
	 * 广告显示次数
	 */
	private Integer adShowNum;
	/**
	 * 广告显示时间
	 */
	private Date adShowDate;
	
	/**
	 * 显示量
	 */
	private Integer showNum;
	
	private Date showDate;
	
	/**
	 * 文章主图 .
	 */
	private String mainImage;

///////////////////////////////

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
	 * 文章code .
	 */
	private String articleCode;

	private String title;

	/**
	 * 查看员工编号 .
	 */
	private String memberNoGuidView;

	/**
	 * 查看员工姓名 .
	 */
	private String memberNameGuidView;

	/**
	 * 查看门诊编号 .
	 */
	private String shopNoView;

	/**
	 * 查看门诊名称 .
	 */
	private String shopNameView;

	/**
	 * 查看商户编号 .
	 */
	private String merchantNoView;

	/**
	 * 查看商户名称 .
	 */
	private String merchantNameView;

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
	 * 文章code .
	 *
	 */
	public String getArticleCode() {
		return articleCode;
	}

	/**
	 * 文章code .
	 *
	 */
	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode == null ? null : articleCode.trim();
	}

	/**
	 * 查看员工编号 .
	 *
	 */
	public String getMemberNoGuidView() {
		return memberNoGuidView;
	}

	/**
	 * 查看员工编号 .
	 *
	 */
	public void setMemberNoGuidView(String memberNoGuidView) {
		this.memberNoGuidView = memberNoGuidView == null ? null : memberNoGuidView.trim();
	}

	/**
	 * 查看员工姓名 .
	 *
	 */
	public String getMemberNameGuidView() {
		return memberNameGuidView;
	}

	/**
	 * 查看员工姓名 .
	 *
	 */
	public void setMemberNameGuidView(String memberNameGuidView) {
		this.memberNameGuidView = memberNameGuidView == null ? null : memberNameGuidView.trim();
	}

	/**
	 * 查看门诊编号 .
	 *
	 */
	public String getShopNoView() {
		return shopNoView;
	}

	/**
	 * 查看门诊编号 .
	 *
	 */
	public void setShopNoView(String shopNoView) {
		this.shopNoView = shopNoView == null ? null : shopNoView.trim();
	}

	/**
	 * 查看门诊名称 .
	 *
	 */
	public String getShopNameView() {
		return shopNameView;
	}

	/**
	 * 查看门诊名称 .
	 *
	 */
	public void setShopNameView(String shopNameView) {
		this.shopNameView = shopNameView == null ? null : shopNameView.trim();
	}

	/**
	 * 查看商户编号 .
	 *
	 */
	public String getMerchantNoView() {
		return merchantNoView;
	}

	/**
	 * 查看商户编号 .
	 *
	 */
	public void setMerchantNoView(String merchantNoView) {
		this.merchantNoView = merchantNoView == null ? null : merchantNoView.trim();
	}

	/**
	 * 查看商户名称 .
	 *
	 */
	public String getMerchantNameView() {
		return merchantNameView;
	}

	/**
	 * 查看商户名称 .
	 *
	 */
	public void setMerchantNameView(String merchantNameView) {
		this.merchantNameView = merchantNameView == null ? null : merchantNameView.trim();
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

	/**
	 * 输出BEAN数据信息
	 * 
	 * @author LeoPeng
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleShare [code=").append(code);
		builder.append(",memberNoGuid=").append(memberNoGuid);
		builder.append(",memberNameGuid=").append(memberNameGuid);
		builder.append(",shopNo=").append(shopNo);
		builder.append(",shopName=").append(shopName);
		builder.append(",merchantNo=").append(merchantNo);
		builder.append(",merchantName=").append(merchantName);
		builder.append(",articleCode=").append(articleCode);
		builder.append(",memberNoGuidView=").append(memberNoGuidView);
		builder.append(",memberNameGuidView=").append(memberNameGuidView);
		builder.append(",shopNoView=").append(shopNoView);
		builder.append(",shopNameView=").append(shopNameView);
		builder.append(",merchantNoView=").append(merchantNoView);
		builder.append(",merchantNameView=").append(merchantNameView);
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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	public String getHasMemberNoGuidViewNotEmpty() {
		return hasMemberNoGuidViewNotEmpty;
	}

	public void setHasMemberNoGuidViewNotEmpty(String hasMemberNoGuidViewNotEmpty) {
		this.hasMemberNoGuidViewNotEmpty = hasMemberNoGuidViewNotEmpty;
	}

	public ArticleDto getArticle() {
		return article;
	}

	public void setArticle(ArticleDto article) {
		this.article = article;
	}

	public String getHasGroupArticleCode() {
		return hasGroupArticleCode;
	}

	public void setHasGroupArticleCode(String hasGroupArticleCode) {
		this.hasGroupArticleCode = hasGroupArticleCode;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public List<String> getMemberNoGuidList() {
		return memberNoGuidList;
	}

	public void setMemberNoGuidList(List<String> memberNoGuidList) {
		this.memberNoGuidList = memberNoGuidList;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAdShowNum() {
		return adShowNum;
	}

	public void setAdShowNum(Integer adShowNum) {
		this.adShowNum = adShowNum;
	}

	public Date getAdShowDate() {
		return adShowDate;
	}

	public void setAdShowDate(Date adShowDate) {
		this.adShowDate = adShowDate;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

}
