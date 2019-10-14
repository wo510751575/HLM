package com.lj.business.member.dto;

import java.io.Serializable;

import com.lj.business.member.emus.ShopStatus;

public class FindShopPromotion implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6604071600942154814L;
    
    /**
     * 商户编号 .
     */
    private String memberNoMerchant;
    
    /**
     * 分店名称 .
     */
    
    
    /**
     * 终端代码（商户自定义）
     */
    private String shopNoMerchant;
    
    /** 销售渠道. */
    private String mecShopChannel;
    
    /**
     * 终端状态
     */
    private ShopStatus status;

    public String getShopNoMerchant() {
		return shopNoMerchant;
	}

	public void setShopNoMerchant(String shopNoMerchant) {
		this.shopNoMerchant = shopNoMerchant;
	}

	public String getMemberNoMerchant() {
        return memberNoMerchant;
    }

    public void setMemberNoMerchant(String memberNoMerchant) {
        this.memberNoMerchant = memberNoMerchant;
    }


    public String getMecShopChannel() {
        return mecShopChannel;
    }

    public void setMecShopChannel(String mecShopChannel) {
        this.mecShopChannel = mecShopChannel;
    }

	/**
	 * @return the status
	 */
	public ShopStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ShopStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindShopPromotion [memberNoMerchant=");
		builder.append(memberNoMerchant);
		builder.append(", shopNoMerchant=");
		builder.append(shopNoMerchant);
		builder.append(", mecShopChannel=");
		builder.append(mecShopChannel);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
