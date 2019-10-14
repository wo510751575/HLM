package com.lj.business.st.domain;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class UnContactTotal.
 */
public class UnContactTotal {
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
     * 1周到1月未联系 .
     */
    private Integer yizhouYiyue;

    /**
     * 1月到3月未联系 .
     */
    private Integer yiyueSanyue;

    /**
     * 3月到6月未联系 .
     */
    private Integer sanyueLiuyue;

    /**
     * 6月以上未联系 .
     */
    private Integer liuyueEnd;

    /**
     * 创建时间 .
     */
    private Date createDate;
    
    /** 统计纬度. */
    private String dimensionSt;
    

    /**
     * CODE .
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号 .
     *
     * @return the merchant no
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     * @param merchantNo the new merchant no
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 分店编号 .
     *
     * @return the shop no
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 分店编号 .
     *
     * @param shopNo the new shop no
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 分店名称 .
     *
     * @return the shop name
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 分店名称 .
     *
     * @param shopName the new shop name
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * 导购编号  .
     *
     * @return the member no gm
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号  .
     *
     * @param memberNoGm the new member no gm
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     * @return the member name gm
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     * @param memberNameGm the new member name gm
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 1周到1月未联系 .
     *
     * @return the yizhou yiyue
     */
    public Integer getYizhouYiyue() {
        return yizhouYiyue;
    }

    /**
     * 1周到1月未联系 .
     *
     * @param yizhouYiyue the new yizhou yiyue
     */
    public void setYizhouYiyue(Integer yizhouYiyue) {
        this.yizhouYiyue = yizhouYiyue;
    }

    /**
     * 1月到3月未联系 .
     *
     * @return the yiyue sanyue
     */
    public Integer getYiyueSanyue() {
        return yiyueSanyue;
    }

    /**
     * 1月到3月未联系 .
     *
     * @param yiyueSanyue the new yiyue sanyue
     */
    public void setYiyueSanyue(Integer yiyueSanyue) {
        this.yiyueSanyue = yiyueSanyue;
    }

    /**
     * 3月到6月未联系 .
     *
     * @return the sanyue liuyue
     */
    public Integer getSanyueLiuyue() {
        return sanyueLiuyue;
    }

    /**
     * 3月到6月未联系 .
     *
     * @param sanyueLiuyue the new sanyue liuyue
     */
    public void setSanyueLiuyue(Integer sanyueLiuyue) {
        this.sanyueLiuyue = sanyueLiuyue;
    }

    /**
     * 6月以上未联系 .
     *
     * @return the liuyue end
     */
    public Integer getLiuyueEnd() {
        return liuyueEnd;
    }

    /**
     * 6月以上未联系 .
     *
     * @param liuyueEnd the new liuyue end
     */
    public void setLiuyueEnd(Integer liuyueEnd) {
        this.liuyueEnd = liuyueEnd;
    }

    /**
     * 创建时间 .
     *
     * @return the creates the date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     * @param createDate the new creates the date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /**
     * Gets the dimension st.
     *
     * @return the dimension st
     */
    public String getDimensionSt() {
		return dimensionSt;
	}

	/**
	 * Sets the dimension st.
	 *
	 * @param dimensionSt the new dimension st
	 */
	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnContactTotal [code=").append(code)
				.append(", merchantNo=").append(merchantNo).append(", shopNo=")
				.append(shopNo).append(", shopName=").append(shopName)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", memberNameGm=").append(memberNameGm)
				.append(", yizhouYiyue=").append(yizhouYiyue)
				.append(", yiyueSanyue=").append(yiyueSanyue)
				.append(", sanyueLiuyue=").append(sanyueLiuyue)
				.append(", liuyueEnd=").append(liuyueEnd)
				.append(", createDate=").append(createDate)
				.append(", dimensionSt=").append(dimensionSt).append("]");
		return builder.toString();
	}

}