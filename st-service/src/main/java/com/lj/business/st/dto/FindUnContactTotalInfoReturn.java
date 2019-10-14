package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindUnContactTotalInfoReturn.
 */
public class FindUnContactTotalInfoReturn implements Serializable {


    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7478474474791509783L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

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

    /**
     * CODE .
     *
     * @return the cODE 
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     * @param code the new cODE 
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号 .
     *
     * @return the 商户编号 
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     * @param merchantNo the new 商户编号 
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 导购编号  .
     *
     * @return the 导购编号  
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号  .
     *
     * @param memberNoGm the new 导购编号  
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     * @return the 导购姓名 
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     * @param memberNameGm the new 导购姓名 
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 1周到1月未联系 .
     *
     * @return the 1周到1月未联系 
     */
    public Integer getYizhouYiyue() {
        return yizhouYiyue;
    }

    /**
     * 1周到1月未联系 .
     *
     * @param yizhouYiyue the new 1周到1月未联系 
     */
    public void setYizhouYiyue(Integer yizhouYiyue) {
        this.yizhouYiyue = yizhouYiyue;
    }

    /**
     * 1月到3月未联系 .
     *
     * @return the 1月到3月未联系 
     */
    public Integer getYiyueSanyue() {
        return yiyueSanyue;
    }

    /**
     * 1月到3月未联系 .
     *
     * @param yiyueSanyue the new 1月到3月未联系 
     */
    public void setYiyueSanyue(Integer yiyueSanyue) {
        this.yiyueSanyue = yiyueSanyue;
    }

    /**
     * 3月到6月未联系 .
     *
     * @return the 3月到6月未联系 
     */
    public Integer getSanyueLiuyue() {
        return sanyueLiuyue;
    }

    /**
     * 3月到6月未联系 .
     *
     * @param sanyueLiuyue the new 3月到6月未联系 
     */
    public void setSanyueLiuyue(Integer sanyueLiuyue) {
        this.sanyueLiuyue = sanyueLiuyue;
    }

    /**
     * 6月以上未联系 .
     *
     * @return the 6月以上未联系 
     */
    public Integer getLiuyueEnd() {
        return liuyueEnd;
    }

    /**
     * 6月以上未联系 .
     *
     * @param liuyueEnd the new 6月以上未联系 
     */
    public void setLiuyueEnd(Integer liuyueEnd) {
        this.liuyueEnd = liuyueEnd;
    }

    /**
     * 创建时间 .
     *
     * @return the 创建时间 
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     * @param createDate the new 创建时间 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindUnContactTotalPageReturn [code=").append(code)
				.append(", merchantNo=").append(merchantNo)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", memberNameGm=").append(memberNameGm)
				.append(", yizhouYiyue=").append(yizhouYiyue)
				.append(", yiyueSanyue=").append(yiyueSanyue)
				.append(", sanyueLiuyue=").append(sanyueLiuyue)
				.append(", liuyueEnd=").append(liuyueEnd)
				.append(", createDate=").append(createDate).append("]");
		return builder.toString();
	}
}
