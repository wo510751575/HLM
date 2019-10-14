package com.lj.business.supcon.netty.message;	


/**
 * 
 * 
 * 类说明：业务结果编码及说明
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月20日
 */
public class ResponseCode {

	/**
	 * 业务编码
	 */
	private String code;
	
	/**
	 * 业务说明
	 */
	private String message;
	
	public ResponseCode() {
		super();
	}
	
	public ResponseCode(String code) {
		super();
		this.code = code;
	}
	
	public ResponseCode(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
