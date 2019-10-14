package com.lj.business.weixin.dto.publicaccount;

import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindWxPublicAccountPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8435096463801381182L; 

    /**
     * 中控微信号 .
     */
    private String noWxZk;

    /**
     * 公众号名称 .
     */
    private String paName;

    /**
     * 状态：0无效、1有效 .
     */
    private Integer status;

    /**
     * 终端编号 .
     */
    

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /** 终端编号集合. */
    private List<String> shopNos;
    
    private String startTime;
    
    private String endTime;



	/**
     * 中控微信号 .
     *
     */
    public String getNoWxZk() {
        return noWxZk;
    }

    /**
     * 中控微信号 .
     *
     */
    public void setNoWxZk(String noWxZk) {
        this.noWxZk = noWxZk == null ? null : noWxZk.trim();
    }

    /**
     * 公众号名称 .
     *
     */
    public String getPaName() {
        return paName;
    }

    /**
     * 公众号名称 .
     *
     */
    public void setPaName(String paName) {
        this.paName = paName == null ? null : paName.trim();
    }

    /**
     * 状态：0无效、1有效 .
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：0无效、1有效 .
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
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
	 * @return the shopNos
	 */
	public List<String> getShopNos() {
		return shopNos;
	}

	/**
	 * @param shopNos the shopNos to set
	 */
	public void setShopNos(List<String> shopNos) {
		this.shopNos = shopNos;
	}

	
    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindWxPublicAccountPage [noWxZk=").append(noWxZk); 
        builder.append(",paName=").append(paName); 
        builder.append(",status=").append(status); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",shopNos=").append(shopNos); 
        builder.append("]");
        return builder.toString();
    }
}
