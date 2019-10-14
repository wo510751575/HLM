package com.lj.business.api.domain;	


/**
 * 
 * 
 * 类说明：消息代码与消息内容
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年7月11日
 */
public class ResponseCode {

	/**
	 * 消息代码
	 */
	private String code;
	
	/**
	 * 消息内容
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
