package com.lj.business.supcon.common;

/**
 * 
 * 
 * 类说明：错误编码
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月13日
 */
public interface ErrorCode {
	
	/**
	 * 非法请求
	 */
	public static final String ACCESS_INVAILD = "access_invaild";
	
	/**
	 * token为空
	 */
	public static final String TOKEN_IS_EMPTY = "token_is_empty";
	
	/**
	 * token超时
	 */
	public static final String TOKEN_TIMEOUT = "token_timeout";
	
	/**
	 * 请求参数错误
	 */
	public static final String PARAM_ERROR = "param_error";
	
	/**
	 * 请求参数为空
	 */
	public static final String PARAM_IS_EMPTY = "param_is_empty";
	
	/**
	 * 会话失效，请重新登录
	 */
	public static final String SESSION_INVAILD = "session_invaild";
	
	/**
	 * 中控客户端已离线
	 */
	public static final String ZKCLIENT_OFFLINE = "zkclient_offline";
	
	/**
	 * 导购客户端已离线
	 */
	public static final String GMCLIENT_OFFLINE = "gmclient_offline";
	
	/**
	 * 账户没有绑定微信
	 */
	public static final String ACCOUNT_NOT_BIND_WX = "account_not_bind_wx";
	
	/**
	 * 通知导购客户新客户失败
	 */
	public static final String NOTIFY_GM_NEW_FRIEND_ERROR_STRING = "notify_gm_new_friend_error_string";
	
	/**
	 * 发送聊天记录错误
	 */
	public static final String SEND_CHAT_ERROR = "send_chat_error";
	
	/**
	 * 发送报文错误
	 */
	public static final String SEND_SOCKET_MESSAGE_ERROR = "send_socket_message_error";
	
	/**
	 * 搜索客户微信失败
	 */
	public static final String FIND_WX_INFO_ERROR = "find_wx_info_error";
	
	/**
	 * 由于对方的隐私设置，你无法通过扫描二维码将添加至通讯录
	 */
	public static final String FIND_WX_INFO_ERROR_BY_PRIVACY = "find_wx_info_error_by_privacy";
	
	/**
	 * 没有找到客户微信
	 */
	public static final String FIND_WX_INFO_NOT_FOUND = "find_wx_info_not_found";
	
	/**
	 * 发送消息失败，对方未添加你为好友
	 */
	public static final String SEND_CHAT_ERROR_BY_FRIENDS = "send_chat_error_by_friends";
	/**
	 * token刷新失败
	 */
	public static final String TOKEN_REFRESH_ERROR = "api_token_refresh_error";
}
