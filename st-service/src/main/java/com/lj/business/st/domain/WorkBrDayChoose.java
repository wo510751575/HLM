package com.lj.business.st.domain;

import java.util.Date;

public class WorkBrDayChoose {
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 项目CODE .
     */
    private String codeList;

    /**
     * 项目名称 .
     */
    private String nameList;

    /**
     * 项目类型                           .
     */
    private String typeList;

    /**
     * 排序 .
     */
    private Integer seq;
    
    /** The img addr. 
     * 图片地址
     * */
    private String imgAddr;

    public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

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
     * 分店编号 .
     *
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 分店编号 .
     *
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 分店名称 .
     *
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 分店名称 .
     *
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * 导购编号  .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号  .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 项目CODE .
     *
     */
    public String getCodeList() {
        return codeList;
    }

    /**
     * 项目CODE .
     *
     */
    public void setCodeList(String codeList) {
        this.codeList = codeList == null ? null : codeList.trim();
    }

    /**
     * 项目名称 .
     *
     */
    public String getNameList() {
        return nameList;
    }

    /**
     * 项目名称 .
     *
     */
    public void setNameList(String nameList) {
        this.nameList = nameList == null ? null : nameList.trim();
    }

    /**
     * 项目类型                           .
     *
     */
    public String getTypeList() {
        return typeList;
    }

    /**
     * 项目类型                           .
     *
     */
    public void setTypeList(String typeList) {
        this.typeList = typeList == null ? null : typeList.trim();
    }

    /**
     * 排序 .
     *
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 排序 .
     *
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
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

    @Override
	public String toString() {
		return "WorkBrDayChoose [code=" + code + ", merchantNo=" + merchantNo
				+ ", shopNo=" + shopNo + ", shopName=" + shopName
				+ ", memberNoGm=" + memberNoGm + ", memberNameGm="
				+ memberNameGm + ", codeList=" + codeList + ", nameList="
				+ nameList + ", typeList=" + typeList + ", seq=" + seq
				+ ", imgAddr=" + imgAddr + ", createDate=" + createDate + "]";
	}
}