package com.lj.business.api.domain.member;

import java.io.Serializable;


/**
 * 
 * 
 * 类说明：缓存API接入详情
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年7月1日
 */
public class EnterpriseApiCacheReturn implements Serializable {

	private static final long serialVersionUID = 1684199702038753871L;
	
	/**
	 * ID
	 */
	private Long id;

	/**
     * 商户编号 .
     */
    private Long merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;
    
    /**
     * 接入应用名称
     */
    private String appName;

    /**
     * 接入应用KEY .
     */
    private String appKey;

    /**
     * 接入应用密钥 .
     */
    private String appSecret;


    /**
     * 签名扩展参数 .
     */
    private String signParam;

    /**
     * 用户访问登陆时效（分钟） .
     */
    private int tokenTimeout;

    /**
     * 是否有效 .
     */
    private boolean valid = Boolean.TRUE;
    
   /* public static EnterpriseApiCacheReturn buildCacheApi(EnterpriseApi api) {
    	EnterpriseApiCacheReturn cache = new EnterpriseApiCacheReturn();
    	cache.setId(api.getId());
    	cache.setMerchantNo(api.getMerchantNo());
    	cache.setMerchantName(api.getMerchantName());
    	cache.setAppName(api.getAppName());
    	cache.setAppKey(api.getAppKey());
    	cache.setAppSecret(api.getAppSecret());
    	cache.setAccessType(api.getAccessType());
    	cache.setSignType(api.getSignType());
    	cache.setSignParam(api.getSignParam());
    	cache.setTokenTimeout(api.getTokenTimeout());
    	return cache;
    }*/

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the merchantNo
	 */
	public Long getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(Long merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return the appKey
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * @param appKey the appKey to set
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * @return the appSecret
	 */
	public String getAppSecret() {
		return appSecret;
	}

	/**
	 * @param appSecret the appSecret to set
	 */
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}



	/**
	 * @return the signParam
	 */
	public String getSignParam() {
		return signParam;
	}

	/**
	 * @param signParam the signParam to set
	 */
	public void setSignParam(String signParam) {
		this.signParam = signParam;
	}

	/**
	 * @return the tokenTimeout
	 */
	public int getTokenTimeout() {
		return tokenTimeout;
	}

	/**
	 * @param tokenTimeout the tokenTimeout to set
	 */
	public void setTokenTimeout(int tokenTimeout) {
		this.tokenTimeout = tokenTimeout;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnterpriseApiCacheReturn [id=");
		builder.append(id);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", appName=");
		builder.append(appName);
		builder.append(", appKey=");
		builder.append(appKey);
		builder.append(", appSecret=");
		builder.append(appSecret);
		builder.append(", accessType=");
		builder.append(", signParam=");
		builder.append(signParam);
		builder.append(", tokenTimeout=");
		builder.append(tokenTimeout);
		builder.append(", valid=");
		builder.append(valid);
		builder.append("]");
		return builder.toString();
	}
}
