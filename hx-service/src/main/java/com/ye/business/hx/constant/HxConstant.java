/**
 * 
 */
package com.ye.business.hx.constant;

/**
 * 
 * 
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年3月8日
 */
public interface HxConstant {

	public static final String OFFICE_ROOT_ID = "1";
	
	public static final String systemAliasName = "hx";
	public static final String group_h5 = "h5";
	public static final String host_h5 = "h5Host";
	
	public static final String group_gd = "gd";
	public static final String host_gd = "gdHost";
	
	/**cc:好乐美预约确认等H5URL配置*/
	public static final String GROUP_PATIENT_SERVICE_URL = "ps_url";
	/**cc:焕新支付账号配置分组*/
	public static final String GROUP_PAY_ACCT = "pay_acct";
	/**cc:焕新支付账号：微信二维码key */
	public static final String PARAM_KEY_WXPAYPIC = "wxPayPic";
	/**cc:焕新支付账号：支付宝二维码key */
	public static final String PARAM_KEY_ALIPAYPIC = "aliPayPic";
	/**cc:焕新支付账号：银行名称key */
	public static final String PARAM_KEY_BANKNAME = "bankName";
	/**cc:焕新支付账号：银行账号 */
	public static final String PARAM_KEY_BANKACCTNO = "bankAcctNo";
	/**cc:焕新支付账号：联系电话 */
	public static final String PARAM_KEY_PAYPHONE = "payPhone";
	/**cc:焕新支付账号：支行名称 */
	public static final String PARAM_KEY_BRANCH_NAME="branchName";
	/**cc:焕新支付账号：户名 */
	public static final String PARAM_KEY_BANKACCT_NAME="bankAcctName";
	
	
	/** 客户类型 */
	public static final String CONFIG_LABLE_NO_USER_TYPE = "user_type";
	/** 患者来源 */
	public static final String CONFIG_LABLE_NO_USER_SOURCE = "user_source";
	/** 预约项目 */
	public static final String CONFIG_LABLE_NO_PATIENT_PROJECT = "patient_project";
	/** 收费项目 */
	public static final String CONFIG_LABLE_NO_PROJECT_PRICE = "project_price";
	/** 门诊支付方式  */
	public static final String CONFIG_LABLE_NO_PAY_TYPE = "pay_type";
}
