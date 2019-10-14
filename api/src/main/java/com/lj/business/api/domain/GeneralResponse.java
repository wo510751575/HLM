package com.lj.business.api.domain;

import java.io.Serializable;

import com.lj.base.exception.TsfaContextServiceException;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.common.ErrorCode;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class GeneralResponse implements Serializable {

	private static final long serialVersionUID = -2924560860709094403L;

	/*** 操作是否成功. */
	private boolean result; 

	/** * 返回结果Code. */
	private String errorCode;

	/** * 返回结果信息. */
	private String errorMessage;

	/** * 返回数据的对象（操作成功才有）. */
	private Object returnObject;

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the returnObject
	 */
	public Object getReturnObject() {
		return returnObject;
	}

	/**
	 * @param returnObject the returnObject to set
	 */
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	/**
	 * 
	 *
	 * 方法说明：将对象转为json格式字符串
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @return
	 */
	public String toJson() {
		return JsonUtils.jsonFromObject_LongToString(this);
	}

	/**
	 * 
	 *
	 * 方法说明：
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param isSucess
	 * @param errorCode
	 * @param errorMessage
	 * @param returnObject
	 * @return
	 */
	public static GeneralResponse generateResponse(boolean isSucess, String errorCode, String errorMessage, Object returnObject) {
		GeneralResponse gr = new GeneralResponse();
		gr.setResult(isSucess);
		gr.setErrorCode(errorCode);
		gr.setErrorMessage(errorMessage);
		gr.setReturnObject(returnObject);
		return gr;
	}

	/**
	 * 
	 *
	 * 方法说明：操作成功返回
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @return
	 */
	public static GeneralResponse generateSuccessResponse(){
		return generateResponse(Boolean.TRUE, "", "", "");
	} 
	
	/**
	 * 
	 *
	 * 方法说明：操作成功返回
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param returnObject
	 * @return
	 */
	public static GeneralResponse generateSuccessResponse(Object returnObject){
		return generateResponse(Boolean.TRUE, "", "", returnObject);
	}
	
	public static GeneralResponse generateSuccessResponse(String msg,Object returnObject){
		return generateResponse(Boolean.TRUE, "", msg, returnObject);
	}

	/**
	 * 
	 *
	 * 方法说明：操作失败返回
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @return
	 */
	public static GeneralResponse generateFailureResponse() {
		return generateResponse(Boolean.FALSE, "", "", null);
	}

	/**
	 * 
	 *
	 * 方法说明：操作失败返回
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param e
	 * @return
	 */
	public static GeneralResponse generateFailureResponse(Throwable e){
		ResponseCode responseCode = null; 
		if(e instanceof TsfaContextServiceException) {
			TsfaContextServiceException et = (TsfaContextServiceException)e;
			responseCode = getErrorResponseByContext(et);
		}else if(e instanceof IllegalArgumentException) {
			IllegalArgumentException et = (IllegalArgumentException)e;
			responseCode = new ResponseCode("000001", et.getMessage());
		}
		else if(e.getCause() != null && e.getCause() instanceof TsfaContextServiceException) {
			TsfaContextServiceException et = (TsfaContextServiceException)e.getCause();
			responseCode = getErrorResponseByContext(et);
		} else if(e instanceof TsfaServiceException){
			TsfaServiceException et = (TsfaServiceException)e;
			if(et.getExceptionInfo()!=null && et.getExceptionInfo().startsWith(ErrorCode.MEC_ERROR)) { //mec_异常单独处理 linjinquan
				responseCode = new ResponseCode();
				responseCode.setCode(ErrorCode.MEC_ERROR);
				responseCode.setMessage(et.getExceptionInfo().replace(ErrorCode.MEC_ERROR, ""));
				
			} else if (e.getCause() != null && e.getCause().getMessage()!=null && e.getCause().getMessage().indexOf(ErrorCode.MEC_ERROR)!=-1) {
				responseCode = new ResponseCode();
				responseCode.setCode(ErrorCode.MEC_ERROR);
				responseCode.setMessage(e.getCause().getMessage().split(ErrorCode.MEC_ERROR)[1]);
				
			} else {
				
				responseCode = getErrorResponse(et);
			}
		} else if(e.getCause() != null && e.getCause() instanceof TsfaServiceException) {
			TsfaServiceException et = (TsfaServiceException)e.getCause();
			responseCode = getErrorResponse(et);
		} else if(e instanceof IllegalArgumentException){
//			responseCode = getErrorResponseByBusinessCode(ErrorCode.PARAM_ERROR);
			responseCode = new ResponseCode(ErrorCode.PARAM_ERROR,"请求参数错误");
		} else if(e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
//			responseCode = getErrorResponseByBusinessCode(ErrorCode.PARAM_ERROR);
			responseCode = new ResponseCode(ErrorCode.PARAM_ERROR,"请求参数错误");
		} else{
			responseCode = new ResponseCode("000000", "系统异常，请稍后再试！");
		}
		return generateResponse(Boolean.FALSE, responseCode.getCode(), responseCode.getMessage(), null);
	}

	/**
	 * 
	 *
	 * 方法说明：操作失败返回
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-11-14
	 * 
	 * @param e
	 * @return
	 */
	public static GeneralResponse generateFailureResponse(String errorCode,String errorMsg){
//		ResponseCode responseCode = null; 
//		responseCode = getErrorResponseByBusinessCode(businessErrorCode);
		return generateResponse(Boolean.FALSE, errorCode, errorMsg, null);
	}
//	private static Map<String, ResponseCode> ERROR_MAP = new HashMap<String, ResponseCode>();

	/**
	 * 100001 - 199999 系统级异常
	 * 200001 - 200099 普通会员异常
	 * 200300 - 200399 CF系统
	 * 600000 - 600100 敏华校招系统
	 */
	/*static{
		ERROR_MAP.put(ErrorCode.TIMESTAMP_IS_EMPTY,																			new ResponseCode("100001", "客户端时间戳为空，请重新登录"));
		ERROR_MAP.put(ErrorCode.TIMESTAMP_ERROR,																			new ResponseCode("100002", "客户端时间戳错误，请重新登录"));
		ERROR_MAP.put(ErrorCode.PLATFORM_NO_IS_EMPTY,																		new ResponseCode("100003", "平台商编号为空"));
		ERROR_MAP.put(ErrorCode.APP_KEY_IS_EMPTY,																			new ResponseCode("100004", "KEY为空"));
		ERROR_MAP.put(ErrorCode.APP_KEY_ERROR,																				new ResponseCode("100005", "KEY非法"));
		ERROR_MAP.put(ErrorCode.APP_KEY_EXPIRED,																			new ResponseCode("100006", "KEY过期"));
		ERROR_MAP.put(ErrorCode.APP_SECRET_IS_EMPTY,																		new ResponseCode("100007", "密钥为空"));
		ERROR_MAP.put(ErrorCode.APP_SECRET_ERROR,																			new ResponseCode("100008", "密钥非法"));
		ERROR_MAP.put(ErrorCode.TERMINAL_ID_IS_EMPTY,																		new ResponseCode("100009", "终端标识为空"));
		ERROR_MAP.put(ErrorCode.TOKEN_IS_EMPTY,																				new ResponseCode("100010", "超时请重新登录"));
		ERROR_MAP.put(ErrorCode.TOKEN_TIMEOUT,																				new ResponseCode("100011", "超时请重新登录"));
		ERROR_MAP.put(ErrorCode.TOKEN_REFRESH_ERROR,																		new ResponseCode("100012", "令牌刷新失败"));
		ERROR_MAP.put(ErrorCode.SIGNATURE_IS_EMPTY,																			new ResponseCode("100013", "签名为空"));
		ERROR_MAP.put(ErrorCode.SIGNATURE_ERROR,																			new ResponseCode("100014", "签名错误"));
		ERROR_MAP.put(ErrorCode.ACCESS_VALID,																				new ResponseCode("100015", "非法请求"));
		ERROR_MAP.put(ErrorCode.PARAM_ERROR,																				new ResponseCode("100016", "参数错误"));
		ERROR_MAP.put(ErrorCode.PLATFORM_NO_ERROR,																			new ResponseCode("100017", "平台商编号错误，请重新登录"));
		ERROR_MAP.put(ErrorCode.MERCHANT_NO_IS_EMPTY,																		new ResponseCode("100018", "商户编号为空"));
		ERROR_MAP.put(ErrorCode.MERCHANT_NO_ERROR,																			new ResponseCode("100019", "商户编号错误"));
		ERROR_MAP.put(ErrorCode.PARAM_IS_EMPTY,																				new ResponseCode("100020", "参数为空"));
		ERROR_MAP.put(ErrorCode.ENCRYPT_KEY_IS_EMPTY,																		new ResponseCode("100021", "密钥为空"));
		ERROR_MAP.put(ErrorCode.UNSUPPORTED_FUNCTION,																		new ResponseCode("100080", "当前版本过低，请升级到最新版本"));
		ERROR_MAP.put(ErrorCode.SYSTEM_UPGRADE,																				new ResponseCode("100100", "系统升级中，暂停服务..."));
		ERROR_MAP.put(ErrorCode.SMS_CODE_EXIST, 																			new ResponseCode("100200", "亲，短信费用好贵哦，请不要频繁发送！"));
		ERROR_MAP.put(ErrorCode.SMS_CODE_ERROR, 																			new ResponseCode("100201", "对不起，您输入的验证码有误"));
		ERROR_MAP.put(ErrorCode.UNSUPPORTED_FILE_FORMAT, 																	new ResponseCode("100210", "不支持的文件格式"));

		ERROR_MAP.put(ErrorCode.REGISTER_ERROR, 																			new ResponseCode("200001", "注册失败，请重新注册"));
		ERROR_MAP.put(ErrorCode.RESET_LOGIN_PASSWORD_ERROR, 																new ResponseCode("200002", "亲，登录密码重置不成功，再来一次吧！"));
		ERROR_MAP.put(ErrorCode.HEAD_IMAGE_IS_EMPTY, 																		new ResponseCode("200006", "头像文件为空"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MBR_MEMBER_STATUS_IS_INIT, 						new ResponseCode("200007", "会员未审核"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MBR_MEMBER_STATUS_IS_UNPASS, 						new ResponseCode("200008", "会员审核未通过"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MBR_MEMBER_STATUS_IS_CANCEL, 						new ResponseCode("200009", "会员已注销"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MBR_MEMBER_STATUS_IS_FREEZE, 						new ResponseCode("200010", "会员已冻结"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_LOGIN_ERROR_FREEZE, 						new ResponseCode("200010", "会员已冻结"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MBR_MEMBER_NOT_BEEN_NAME_AUTHENTICATION, 			new ResponseCode("200011", "会员未实名"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_LOGIN_ERROR_FREEZE,						new ResponseCode("200020", "登录失败：会员被冻结"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_LOGIN_ERROR_AUTOLOCK,						new ResponseCode("200021", "登录失败：登录失败次数过多，登录已锁定"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_LOGIN_ERROR_PSW_FREEZE,					new ResponseCode("200022", "登录失败：错误次数已超过限制，账户冻结"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_LOGIN_ERROR_PSW,							new ResponseCode("200023", "登录失败：会员不存在或密码错误"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MBR_MEMBER_HAVE_BEEN_NAME_AUTHENTICATION,			new ResponseCode("200034", "会员已实名"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_ERROR_NOT_EXIST,							new ResponseCode("200039", "会员不存在"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_LOGIN_ERROR_CANCEL,							new ResponseCode("200040", "登录失败：不是本人手机"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_NOT_EXIST_ERROR, 						new ResponseCode("200041", "顾客不存在！"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MERCHANT_PROBATION_ERROR_END, 						new ResponseCode("200042", "商户试用期结束，请及时续费！"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MEMBER_REPERAT_UNDER_SHOP_ERROR, 					new ResponseCode("200043", "客户已经关联过该终端下的导购！"));

		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MBR_MERCHANT_MEMBER_STATUS_IS_INIT, 				new ResponseCode("200100", "非法商户"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MBR_MERCHANT_MEMBER_STATUS_IS_UNPASS, 			new ResponseCode("200100", "非法商户"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MBR_MERCHANT_MEMBER_STATUS_IS_FREEZE, 			new ResponseCode("200101", "非法商户：商户被冻结"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MBR_MERCHANT_MEMBER_STATUS_IS_CANCEL, 			new ResponseCode("200102", "非法商户：商户已注销"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_LOGIN_ERROR_PSW_LOGIN, 			new ResponseCode("200103", "需要密码登录"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MEMBER_GM_REPERAT_ERROR, 			new ResponseCode("200104", "手机号码已存在"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_MOBILE_EXIST, 			new ResponseCode("200105", "该手机号已存在！"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_GROUP_ERROR, 			new ResponseCode("200105", "成单客户和暂停客户禁止分组！"));
		
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_REPEATEDLY_ERROR,							new ResponseCode("202214", "重复请求添加微信好友"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.MEMBER_REPERAT_ERROR,										new ResponseCode("202215", "已添加该客户微信好友"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_HAND_DISTRIBUTION_ERROR,					new ResponseCode("202215", "其他导购已认领该客户"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_DISTRIBUTION_ERROR,							new ResponseCode("202216", "只能认领同终端客户"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_NOT_CLAIMED_ERROR,							new ResponseCode("202217", "终端微信已添加该客户"));
	
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, 							new ResponseCode("202210", "终端不存在"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_NORMAL_ERROR, 							new ResponseCode("202211", "终端状态非法"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_BIND_WX, 								new ResponseCode("202212", "终端没有绑定微信"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_SAME_WX, 								new ResponseCode("202213", "登录账号绑定微信与终端绑定微信不一致"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.FRIENDS_SYNCING_ERROR, 									new ResponseCode("202221", "终端正在同步微信通讯录"));

		*//**
		 * 客户认领相关
		 *//*
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_NOT_EXIST_ERROR, 									new ResponseCode("202222", "没有找到客户添加记录"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_UPDATE_ERROR, 									new ResponseCode("202223", "客户未验证通过添加微信好友请求"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_DISTRIBUTION_ERROR, 									new ResponseCode("202224", "只能将客户分配给同一终端的导购"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_REPEATEDLY_ERROR, 									new ResponseCode("202225", "导购已添加该客户"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_REPEATEDLY_OTHER_ERROR, 									new ResponseCode("202226", "终端其它导购已认领"));
		ERROR_MAP.put(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_HAND_DISTRIBUTION_ERROR, 									new ResponseCode("202227", "已有导购认领该客户"));
		
		
		*//**
		 * cf
		 *//*
		ERROR_MAP.put(com.lj.business.cf.constant.ErrorCode.PM_ABANDON_NOCHECK_DATA_ERROR, 			new ResponseCode("200300", "存在未审批的暂停记录,不能暂停！"));
		ERROR_MAP.put(com.lj.business.cf.constant.ErrorCode.CLIENT_FOLLOW_DATA_ERROR, 			new ResponseCode("200301", "原因长度超过200！"));
		ERROR_MAP.put(com.lj.business.cf.constant.ErrorCode.PM_ABANDON_CHECK_TIME_ERROR, 			new ResponseCode("200302", "客户暂停需要跟进3次以上！"));
		ERROR_MAP.put(com.lj.business.cf.constant.ErrorCode.CLIENT_FOLLOW_DATA_TIME_LOW_ERROR, 			new ResponseCode("200303", "跟进时间需要小于下次跟进时间！"));
		ERROR_MAP.put(com.lj.business.cf.constant.ErrorCode.CLIENT_FOLLOW_DATA_COMMON_ERROR, 			new ResponseCode("200304", "跟进数据错误！"));
		ERROR_MAP.put(com.lj.business.cf.constant.ErrorCode.TASK_SET_SHOP_NUM_OVERSIZE, 			new ResponseCode("200305", "店长设置任务数据过大！"));
		ERROR_MAP.put(com.lj.business.cf.constant.ErrorCode.NOT_MANAGER_MEMBER_DATA_ERROR, 			new ResponseCode("200306", "跟进已经结束，不能再次跟进！"));
		ERROR_MAP.put(com.lj.business.cf.constant.ErrorCode.PM_ABANDON_CHECK_SHOP_ERROR, 			new ResponseCode("200307", "终端不存在店长,不能进行暂停客户！"));
	

		ERROR_MAP.put(com.lj.business.st.constant.ErrorCode.TODAY_MEMBER_SUMMARY_HAS_EXIST_ERROR, 						new ResponseCode("202230", "该导购已添加客户汇总记录"));
	    
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.PRODUCT_NOT_EXIST_ERROR, 			new ResponseCode("300100", "商品不存在"));
		
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_NOT_EXIST_ERROR, 						new ResponseCode("310000", "请录入正确的优惠券号"));
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_FIND_ERROR, 								new ResponseCode("310000", "请录入正确的优惠券号"));
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_STATUS_UNCLAIMED_ERROR, 				new ResponseCode("310001", "请使用有效的优惠券"));
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_STATUS_USED_ERROR, 						new ResponseCode("310002", "优惠券已使用"));
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_STATUS_INVAILD_ERROR, 					new ResponseCode("310003", "优惠券已过期"));
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_MEMBER_RECEIVED_ERROR, 					new ResponseCode("310004", "您已经领取过了！"));
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_RECEIVE_NOT_EXIST_ERROR, 				new ResponseCode("310005", "抱歉来晚啦，已经抢光光了！"));
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_RECEIVE_ERROR, 							new ResponseCode("310006", "领取失败，请稍候再试！"));
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_RECEIVER_MOBILE_ERROR, 					new ResponseCode("310007", "下单手机号与优惠券领取手机号不一致"));
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_ORDER_MONEY_ERROR, 						new ResponseCode("310009", "订单总额未达优惠券使用门槛"));
//		ERROR_MAP.put(com.lj.business.mec.constant.ErrorCode.VOUCHER_ORDER_PRODUCT_ERROR, 					new ResponseCode("310010", "您选购的商品不能使用该优惠券！"));
		
//		ERROR_MAP.put(com.lj.business.comment.constant.ErrorCode.PRODUCT_REVIEW_CONTENT_ADD_ERROR, 			new ResponseCode("400103", "您已经评论"));
//		ERROR_MAP.put(com.lj.business.comment.constant.ErrorCode.PRODUCT_REVIEW_CONTENT_ADD_ERROR, 			new ResponseCode("400103", "您已经评论"));
		
//		ERROR_MAP.put(com.lj.business.ord.constant.ErrorCode.SHOP_CAR_PROMOTION_EXIST, 							new ResponseCode("500100", "一个商品不能同时参加两个活动"));
//		ERROR_MAP.put(com.lj.business.ord.constant.ErrorCode.ORDER_ACTIVITIES_MORE_THAN_ONCE_ERROR, 			new ResponseCode("500101", "很抱歉，一个订单只能参与一个活动"));
		
		
		*//**
		 * 600000 - 600100 敏华校招系统*企业直通车
		 *//*
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.INTERVIEWER_NOT_EXIST_ERROR, 			new ResponseCode("600000", "面试官信息不存在."));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.STUDENT_NOT_EXIST_ERROR, 			new ResponseCode("600001", "应聘学生信息信息不存在"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.SCHOOL_NOT_EXIST_ERROR, 			new ResponseCode("600002", "宣讲院校信息不存在"));
//		ERROR_MAP.put(ErrorCode.RETEST_STOP, 			new ResponseCode("600003", "考试结束"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.JOB_APPLY_INFO_EXIST_ERROR,new ResponseCode("600004", "已申请岗位"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.JOB_APPLY_INFO_NOT_EXIST_ERROR,new ResponseCode("600005", "求职信息不存在"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.JOB_APPLY_REPEATED_RESPONSE_ERROR,new ResponseCode("600006", "你已经提交"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.JOB_APPLY_NO_PASS,new ResponseCode("600007", "没有通过面试"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.JOB_APPLY_NO_EMPLOY,new ResponseCode("600008", "没有录用"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.EMPLOYEE_EXIST_ERROR,new ResponseCode("600009", "企业员工信息已存在"));
		
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.JOB_APPLY_INFO_MAX_ERROR,new ResponseCode("600010", "今日申请岗位次数已用完"));
		
//		ERROR_MAP.put(com.lj.business.iem.constant.ErrorCode.GOODS_UNDER_STOCK_ERROR, new ResponseCode("700001", "商品库存不足"));

//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.EMPLOYEE_NOT_ADDRESS_CODE_ERROR,new ResponseCode("600010", "没有绑定打卡地点"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.CLOCK_INVALID_ERROR,new ResponseCode("600010", "打卡地点不匹配,打卡无效"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.EMPLOYEE_NOT_WORK_CODE_ERROR,new ResponseCode("600010", "没有绑定班次,请绑定班次"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.EMPLOYEE_NOT_WORK_CODE_ERROR,new ResponseCode("600010", "打卡距离无效,该打卡无效"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.EMPLOYEE_EXIST_ERROR,new ResponseCode("600010", "无法识别该员工号"));
//		ERROR_MAP.put(com.lj.business.recruit.constant.ErrorCode.CLOCK_IN_WORK_SET_NOT_EXIST_ERROR,new ResponseCode("600010", "没有绑定打卡班次！"));
		
		*//**
		 * 群聊  300000 - 300100
		 *//*
		ERROR_MAP.put(com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE,new ResponseCode("300000", "微信已离线，发送失败！"));
	 }*/
	
	/**
	 * 
	 *
	 * 方法说明：通过业务错误编码获取异常描述对象
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param businessErrorCode
	 * @return
	 */
//	public static ResponseCode getErrorResponseByBusinessCode(String businessErrorCode){  
//		if(ERROR_MAP.containsKey(businessErrorCode))
//			return ERROR_MAP.get(businessErrorCode);
//		return new ResponseCode("000000", "系统异常，请稍后再试！");
//	}

	/**
	 * 
	 *
	 * 方法说明：获取异常描述对象
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param e
	 * @return
	 */
	public static ResponseCode getErrorResponse(Throwable e){  
		if(e instanceof TsfaServiceException){
			TsfaServiceException et = (TsfaServiceException) e;
			String errorCode = et.getExceptionCode();
			String errorMsg = et.getExceptionInfo();
//			if(ERROR_MAP.containsKey(errorCode))
//				return ERROR_MAP.get(errorCode);
			return new ResponseCode(errorCode, errorMsg);
		}
		return new ResponseCode("000000", "系统异常，请稍后再试！");
	}

	/**
	 * 
	 *
	 * 方法说明：根据异常的上下文参数返回获取异常描述对象
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param e
	 * @return
	 */
	public static ResponseCode getErrorResponseByContext(TsfaContextServiceException e) {  
			return getErrorResponse(e);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeneralResponse [result=");
		builder.append(result);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", returnObject=");
		builder.append(returnObject);
		builder.append("]");
		return builder.toString();
	}

}
