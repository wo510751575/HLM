package com.lj.oms.entity.dto;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：卖客星球DTO
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2018年1月29日
 */
public class Maike51DtoReturn implements Serializable{

	public static final String MOBILE_ERROR_CODE = "MK_0001";
	public static final String USER_EXIST_CODE = "MK_0002";
	public static final String ROLE_ERROR_CODE = "MK_0003";
	public static final String SECRET_ERROR_CODE = "MK_0000";
	/**
	 * 
	 */
	private static final long serialVersionUID = 5153838375314080161L;
	/*** 操作是否成功.true 成功 false失败 */
	private boolean result;

	/** * 返回结果Code. */
	private String code;

	/** * 返回结果信息. */
	private String msg;

	/** * 返回数据的对象（操作成功才有）. */
	private Object data;

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public static Maike51DtoReturn successResp(Object respData) {
		return createResp(Boolean.TRUE, "", "", respData);
	}
	
	public static Maike51DtoReturn failResp(String errorCode,
			String errorMessage) {
		return createResp(Boolean.FALSE,errorCode, errorMessage, "");
	}

	private static Maike51DtoReturn createResp(boolean isSucess, String errorCode,
			String errorMessage, Object respData) {
		Maike51DtoReturn r = new Maike51DtoReturn();
		r.setResult(isSucess);
		r.setCode(errorCode);
		r.setMsg(errorMessage);
		r.setData(respData);
		return r;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Maike51DtoReturn [result=" + result + ", code=" + code
				+ ", msg=" + msg + ", data=" + data + "]";
	}
}
