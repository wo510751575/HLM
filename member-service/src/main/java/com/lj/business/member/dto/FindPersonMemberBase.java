package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.List;

/**
 * The Class FindPersonMemberBase.
 */
public class FindPersonMemberBase implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2770571931218201266L; 
	
	/** The code. */
	private String code;
	
	/** The member no. */
	private String memberNo;
	
	/** The no wx. */
	private String noWx;
	/**手机号码 */
	private String mobile;



	/**
	 * 客户QQ号
	 */
	private String noQQ;
	
	/**商户编号*/
	private String merchantNo;
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	/**
     * 微信OPENID,存储用于对外接口返回
     */
    private String wxOpenId;
	
    /** 旺旺账号*/
    private String noWw;
    /**
     * 公众号openId
     */
    private String openIdGzhWx;
    
    List<String> memberNos;
    
	public String getOpenIdGzhWx() {
		return openIdGzhWx;
	}

	public void setOpenIdGzhWx(String openIdGzhWx) {
		this.openIdGzhWx = openIdGzhWx;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the member no.
	 *
	 * @return the member no
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the member no.
	 *
	 * @param memberNo the member no
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the no wx.
	 *
	 * @return the no wx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * Sets the no wx.
	 *
	 * @param noWx the no wx
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}
	/**
     * 微信OPENID,存储用于对外接口返回
     */
	public String getWxOpenId() {
		return wxOpenId;
	}
	/**
     * 微信OPENID,存储用于对外接口返回
     */
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	/**
     *  客户QQ号
     */
    public String getNoQQ() {
        return noQQ;
    }

    /**
     *  客户QQ号
     */
    public void setNoQQ(String noQQ) {
        this.noQQ = noQQ;
    }

    public String getNoWw() {
		return noWw;
	}

	public void setNoWw(String noWw) {
		this.noWw = noWw;
	}

	public List<String> getMemberNos() {
		return memberNos;
	}

	public void setMemberNos(List<String> memberNos) {
		this.memberNos = memberNos;
	}

	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindPersonMemberBase [code=");
        builder.append(code);
        builder.append(", memberNo=");
        builder.append(memberNo);
        builder.append(", noWx=");
        builder.append(noWx);
        builder.append(", mobile=");
        builder.append(mobile);
        builder.append(", noQQ=");
        builder.append(noQQ);
        builder.append(", merchantNo=");
        builder.append(merchantNo);
        builder.append(", memberNoGm=");
        builder.append(memberNoGm);
        builder.append(", wxOpenId=");
        builder.append(wxOpenId);
        builder.append(", noWw=");
        builder.append(noWw);
        builder.append("]");
        return builder.toString();
    }

}
