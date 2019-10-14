package com.lj.business.api.dto;

import java.io.Serializable;

public class WxDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7856430660308536961L;
	
	
	

    private String merchantNo;
	
	
	private String wxCode;
	
	
	public String getWxCode() {
		return wxCode;
	}
	public void setWxCode(String wxCode) {
		this.wxCode = wxCode;
	}

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxDto [merchantNo=");
		builder.append(merchantNo);
		builder.append(", wxCode=");
		builder.append(wxCode);
		builder.append("]");
		return builder.toString();
	}

}
