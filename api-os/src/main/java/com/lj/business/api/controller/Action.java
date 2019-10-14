package com.lj.business.api.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.lj.base.core.util.StringUtils;
import com.lj.base.mvc.web.util.DateEditor;
import com.lj.base.mvc.web.util.IntegerEditor;
import com.lj.base.mvc.web.util.LongEditor;

/**
 * 
 * 
 * 类说明：Action基础类
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
public abstract class Action {
	
	public static final String CURRENT_MEMBER_NO_GM = "currentMemberNoGm";
	
	public static final String CURRENT_MERCHENT_NO = "currentMerchentNo";
	
	public static final String APP_KEY= "appKey";
	
	public static final String API_TOKENTIMEOUT = "tokenTimeout";
	
	/**
	 * 
	 *
	 * 方法说明：获取当前登录会员编号
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @return
	 */
	public static Long getCurrentMemberNo(HttpServletRequest request){
		String memberNo = request.getParameter(CURRENT_MEMBER_NO_GM);
		return StringUtils.isEmpty(memberNo) ? null : Long.valueOf(memberNo);
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取当前登录会员信息
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param request
	 * @return
	 *//*
	public FindPersonMemberReturn getCurrentMember(HttpServletRequest request) {
		Long memberNo = getCurrentMemberNo(request);
		if(memberNo != null) {
			return memberQueryService.findPersonMemberByMemeberNo(memberNo);
		}
		return null;
	}
	
	*//**
	 * 
	 *
	 * 方法说明：获取当前登录会员信息、账户信息
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param request
	 * @return
	 *//*
	public PersonAccountInfo getCurrentMemberAccount(HttpServletRequest request) {
		Long memberNo = getCurrentMemberNo(request);
		if(memberNo != null) {
			return memberQueryService.findPersonAccountInfoByMemberNo(memberNo);
		}
		return null;
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：绑定日期型字符串的处理
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder  
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {  
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理  
	    binder.registerCustomEditor(Date.class, new DateEditor()); 
	    binder.registerCustomEditor(int.class,new IntegerEditor());
	    binder.registerCustomEditor(long.class,new LongEditor());
	  //  binder.registerCustomEditor(ApproveMemberInfo.class, new ApproveMemberInfoEditor()); 
	}
	
}
