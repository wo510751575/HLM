package com.lj.business.member.dto.shopterminal;

import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindShopTerminalPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8507017523346883331L; 

	/**
     * 商户编号 .
     */
    private String merchantNo;


    /**
     * 终端编码 .
     */
    private String terminalCode;

    /**
     * 绑定微信号 .
     */
    private String noWx;

    /**
     * 绑定微信昵称 .
     */
    private String wxNickname;

    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 状态：0未激活、1正常、2注销 .
     */
    private Integer status;
    /**
     * 终端编号集合
     */
    private List<String> shopNos;
    /**
     * 助手编号
     */
    private String assistantNo;
    
    

	public List<String> getShopNos() {
		return shopNos;
	}

	public void setShopNos(List<String> shopNos) {
		this.shopNos = shopNos;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	/**
	 * @return the terminalCode
	 */
	public String getTerminalCode() {
		return terminalCode;
	}

	/**
	 * @param terminalCode the terminalCode to set
	 */
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	/**
	 * @return the wxNickname
	 */
	public String getWxNickname() {
		return wxNickname;
	}

	/**
	 * @param wxNickname the wxNickname to set
	 */
	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	/**
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAssistantNo() {
		return assistantNo;
	}

	public void setAssistantNo(String assistantNo) {
		this.assistantNo = assistantNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindShopTerminalPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", terminalCode=");
		builder.append(terminalCode);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", wxNickname=");
		builder.append(wxNickname);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", status=");
		builder.append(status);
		builder.append(", shopNos=");
		builder.append(shopNos);
		builder.append("]");
		return builder.toString();
	}


}
