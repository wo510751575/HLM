package com.lj.business.weixin.emus;

/**
 * 
 * 
 * 类说明：消息来源
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月13日
 */
public enum MessageSource {
	
	/**
	 * 导购客户端
	 */
	GM(1,"导购"),
	
	/**
	 * 客户微信发送
	 */
	ZK(2,"客户"),
	
	/**
	 * 导购助手或系统自动发送
	 */
	SA(3,"导购助手"),
	;
	
	MessageSource(Integer code,String name){
		this.code = code;
		this.name = name;
	}
	
	private Integer code;
	private String name;
	
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
