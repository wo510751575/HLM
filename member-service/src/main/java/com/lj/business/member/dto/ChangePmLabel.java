package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.List;

public class ChangePmLabel implements Serializable {
    private static final long serialVersionUID = 5814382241935429189L;

    /**
     * 客户编号(必填 ) .
     */
    private String memberNo;
    
    /**
     * 商户编号(必填) .
     */
    private String merchantNo;
    /**
     * 终端微信
     */
    private String shopWx;
    /**
     * 选中标签的列表 .
     */
    private List<PmLabelDto> labels;
    
    /**
     * 客户编号集合
     */
    private List<String> memberNoArr;

    public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public List<PmLabelDto> getLabels() {
        return labels;
    }

    public void setLabels(List<PmLabelDto> labels) {
        this.labels = labels;
    }
    
    /**
     * 客户编号集合
     */
    public List<String> getMemberNoArr() {
		return memberNoArr;
	}
    /**
     * 客户编号集合
     */
	public void setMemberNoArr(List<String> memberNoArr) {
		this.memberNoArr = memberNoArr;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangePmLabel [memberNo=");
		builder.append(memberNo);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", shopWx=");
		builder.append(shopWx);
		builder.append(", labels=");
		builder.append(labels);
		builder.append(", memberNoArr=");
		builder.append(memberNoArr);
		builder.append("]");
		return builder.toString();
	}

}
