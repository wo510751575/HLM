package com.lj.business.supcon.netty.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.lj.base.exception.TsfaContextServiceException;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.supcon.common.ErrorCode;

/**
 * 
 * 
 * 类说明：响应参数工具类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月19日
 */
public class ResponseUtils implements Serializable {

	private static final long serialVersionUID = -6076454467648800478L;
	
	private static Map<String, ResponseCode> ERROR_MAP = new HashMap<String, ResponseCode>();

	/**
	 * 100001 - 199999 系统级异常
	 * 200001 - 200099 普通会员异常
	 */

	static{
		ERROR_MAP.put(ErrorCode.TOKEN_IS_EMPTY,																			new ResponseCode("100010", "超时请重新登录"));
		ERROR_MAP.put(ErrorCode.TOKEN_TIMEOUT,																				new ResponseCode("100010", "超时请重新登录"));
		ERROR_MAP.put(ErrorCode.SESSION_INVAILD,																			new ResponseCode("100011", "超时请重新登录"));
		ERROR_MAP.put(ErrorCode.ACCESS_INVAILD,																			new ResponseCode("100015", "非法请求"));
		ERROR_MAP.put(ErrorCode.PARAM_ERROR,																				new ResponseCode("100016", "参数错误"));
		ERROR_MAP.put(ErrorCode.PARAM_IS_EMPTY,																			new ResponseCode("100020", "参数为空"));
		
		ERROR_MAP.put(ErrorCode.ZKCLIENT_OFFLINE,																			new ResponseCode("102001", "微信已离线，发送失败！"));
		ERROR_MAP.put(ErrorCode.ACCOUNT_NOT_BIND_WX,																		new ResponseCode("102002", "登录账户没有绑定微信！"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.GM_NOT_BIND_WX,											new ResponseCode("102002", "登录账户没有绑定微信！"));
		ERROR_MAP.put(com.lj.business.weixin.constant.ErrorCode.INCLUDE_SENSITIVE_WORDS,									new ResponseCode("102010", "当前信息含有敏感词无法发送"));
		ERROR_MAP.put(ErrorCode.SEND_CHAT_ERROR,																			new ResponseCode("102011", "发送消息失败"));
		ERROR_MAP.put(ErrorCode.GMCLIENT_OFFLINE,																			new ResponseCode("102012", "导购客户端已离线，发送失败！"));
		ERROR_MAP.put(com.lj.business.weixin.constant.ErrorCode.IM_CHAT_TYPE_INVAILD,										new ResponseCode("102013", "不支持的消息类型"));
		ERROR_MAP.put(ErrorCode.SEND_CHAT_ERROR_BY_FRIENDS,																new ResponseCode("102014", "发送消息失败，对方未添加你为好友。"));


		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_NOT_EXIST_ERROR,							new ResponseCode("200041", "客户不存在"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_MOBILE_EXIST, 								new ResponseCode("200105", "该手机号已存在！"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.GUID_MEMBER_NOT_EXIST_ERROR, 							new ResponseCode("202200", "导购不存在"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, 							new ResponseCode("202210", "终端不存在"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_NORMAL_ERROR, 							new ResponseCode("202211", "终端状态非法"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_BIND_WX, 								new ResponseCode("202212", "终端没有绑定微信"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_SAME_WX, 								new ResponseCode("202213", "登录账号绑定微信与终端绑定微信不一致"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_REPEATEDLY_ERROR,							new ResponseCode("202214", "重复请求添加微信好友"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_REPEATEDLY_OTHER_ERROR,						new ResponseCode("202214", "同终端其他导购已添加该客户"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MEMBER_REPERAT_ERROR,										new ResponseCode("202215", "已添加该客户微信好友"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_HAND_DISTRIBUTION_ERROR,					new ResponseCode("202215", "其他导购已认领该客户"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_DISTRIBUTION_ERROR,							new ResponseCode("202216", "只能认领同终端客户"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_NOT_CLAIMED_ERROR,							new ResponseCode("202217", "终端微信已添加该客户"));
		ERROR_MAP.put(ErrorCode.FIND_WX_INFO_ERROR_BY_PRIVACY,															new ResponseCode("202218", "无法获取客户微信信息，有可能对方开启了隐私设置"));
		ERROR_MAP.put(ErrorCode.FIND_WX_INFO_ERROR,																		new ResponseCode("202219", "获取客户微信失败"));
		ERROR_MAP.put(ErrorCode.FIND_WX_INFO_NOT_FOUND,																	new ResponseCode("202222", "没有找到客户微信"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_COLLEAGUE_ERROR,								new ResponseCode("202220", "不能添加终端其他微信为好友！"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_WORK_KEY_ERROR,							new ResponseCode("202230", "工作密钥非法"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_CONFIG_PWD,							new ResponseCode("202231", "终端没有配置密钥"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_SIGN_ERROR,								new ResponseCode("202232", "签到失败"));
	}

	/**
	 * 
	 *
	 * 方法说明：构建返回参数
	 *
	 * @param isSucess
	 * @param errorCode
	 * @param errorMessage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static BaseResponse generateResponse(boolean isSucess, String errorCode, String errorMessage) {
		BaseResponse gr = new BaseResponse();
		gr.setResult(isSucess);
		gr.setCode(errorCode);
		gr.setMessage(errorMessage);
		return gr;
	}

	/**
	 * 
	 *
	 * 方法说明：操作成功返回
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static BaseResponse generateSuccessResponse(){
		return generateResponse(Boolean.TRUE, "", "");
	} 

	/**
	 * 
	 *
	 * 方法说明：操作失败返回
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static BaseResponse generateFailureResponse() {
		return generateResponse(Boolean.FALSE, "", "");
	}

	/**
	 * 
	 *
	 * 方法说明：操作失败返回
	 *
	 * @param e
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static BaseResponse generateFailureResponse(Throwable e){
		ResponseCode responseCode = null; 
		if(e instanceof TsfaContextServiceException) {
			TsfaContextServiceException et = (TsfaContextServiceException)e;
			responseCode = getErrorResponseByContext(et);
		} else if(e.getCause() != null && e.getCause() instanceof TsfaContextServiceException) {
			TsfaContextServiceException et = (TsfaContextServiceException)e.getCause();
			responseCode = getErrorResponseByContext(et);
		} else if(e instanceof TsfaServiceException){
			TsfaServiceException et = (TsfaServiceException)e;
			responseCode = getErrorResponse(et);
		} else if(e.getCause() != null && e.getCause() instanceof TsfaServiceException) {
			TsfaServiceException et = (TsfaServiceException)e.getCause();
			responseCode = getErrorResponse(et);
		} else if(e instanceof IllegalArgumentException){
			responseCode = getErrorResponseByBusinessCode(ErrorCode.PARAM_ERROR);
		} else if(e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
			responseCode = getErrorResponseByBusinessCode(ErrorCode.PARAM_ERROR);
		} else{
			responseCode = new ResponseCode("000000", "系统异常，请稍后再试！");
		}
		return generateResponse(Boolean.FALSE, responseCode.getCode(), responseCode.getMessage());
	}

	/**
	 * 
	 *
	 * 方法说明：通过业务错误编码获取异常描述对象
	 *
	 * @param businessErrorCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static ResponseCode getErrorResponseByBusinessCode(String businessErrorCode){  
		if(ERROR_MAP.containsKey(businessErrorCode)) {
			return ERROR_MAP.get(businessErrorCode);
		}
		return new ResponseCode("000000", "系统异常，请稍后再试！");
	}

	/**
	 * 
	 *
	 * 方法说明：获取异常描述对象
	 *
	 * @param e
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static ResponseCode getErrorResponse(Throwable e){  
		if(e instanceof TsfaServiceException){
			TsfaServiceException et = (TsfaServiceException) e;
			String errorCode = et.getExceptionCode(); 
			if(ERROR_MAP.containsKey(errorCode)) {
				return ERROR_MAP.get(errorCode);
			}
		}
		return new ResponseCode("000000", "系统异常，请稍后再试！");
	}

	/**
	 * 
	 *
	 * 方法说明：根据异常的上下文参数返回获取异常描述对象
	 *
	 * @param e
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static ResponseCode getErrorResponseByContext(TsfaContextServiceException e) {  
		return getErrorResponse(e);
	}

}
