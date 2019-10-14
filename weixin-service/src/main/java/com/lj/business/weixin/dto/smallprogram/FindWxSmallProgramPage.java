package com.lj.business.weixin.dto.smallprogram;

import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindWxSmallProgramPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3341808674212809821L; 

    /**
     * 中控微信号 .
     */
    private String noWxZk;

    /**
     * 类型 .
     */
    private String type;

    /**
     * 小程序名称 .
     */
    private String spName;

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
     * 类型 .
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 类型 .
     *
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
	 * @return the spName
	 */
	public String getSpName() {
		return spName;
	}

	/**
	 * @param spName the spName to set
	 */
	public void setSpName(String spName) {
		this.spName = spName;
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

	/**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindWxSmallProgramPage [noWxZk=").append(noWxZk); 
        builder.append(",type=").append(type); 
        builder.append(",spName=").append(spName); 
        builder.append(",status=").append(status); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",shopNos=").append(shopNos); 
        builder.append("]");
        return builder.toString();
    }
}
