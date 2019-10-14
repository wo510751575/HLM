package com.lj.business.supcon.dto.contacts;

import java.io.Serializable;

/**
 * 类说明：添加微信好友的对外实体
 * <p>
 * 详细描述：可以通过手机号搜索客户的微信信息
 * @author 李端强
 * CreateDate: 2018年1月3日
 * @Company: 扬恩科技有限公司
 */
public class FindWxInfoRequestDto implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3641984970778965622L;
	
	/** 导购编号，非空. */
	private String memberNoGm;

	/** 客户手机号或其他搜索条件，非空. */
	private String wxQrCode;
	
	/**
	 * 中控微信号
	 */
	private String noWxGM;
	
	/**
	 * add_friends表主键
	 */
	private String addCode;

	/**
	 * Gets the member no gm.
	 * @return the member no gm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the member no gm.
	 *
	 * @param memberNoGm the new member no gm
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the wx qr code.
	 *
	 * @return the wx qr code
	 */
	public String getWxQrCode() {
		return wxQrCode;
	}

	/**
	 * Sets the wx qr code.
	 *
	 * @param wxQrCode the new wx qr code
	 */
	public void setWxQrCode(String wxQrCode) {
		this.wxQrCode = wxQrCode;
	}
	
	/**
	 * 中控微信号
	 */
	public String getNoWxGM() {
		return noWxGM;
	}
	
	/**
	 * 中控微信号
	 */
	public void setNoWxGM(String noWxGM) {
		this.noWxGM = noWxGM;
	}
	/**
	 * add_friends表主键
	 */
	public String getAddCode() {
		return addCode;
	}
	/**
	 * add_friends表主键
	 */
	public void setAddCode(String addCode) {
		this.addCode = addCode;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWxInfoRequestDto [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", wxQrCode=");
		builder.append(wxQrCode);
		builder.append(", addCode=");
		builder.append(addCode);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
