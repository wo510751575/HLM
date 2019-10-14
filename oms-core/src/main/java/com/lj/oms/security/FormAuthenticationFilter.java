package com.lj.oms.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import com.ape.common.mapper.JsonMapper;
import com.ape.common.utils.StringUtils;
import com.lj.oms.security.SystemAuthorizingRealm.Principal;
import com.lj.oms.utils.UserUtils;

/**
 * 表单验证（包含验证码）过滤类
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
	public static final String DEFAULT_MESSAGE_PARAM = "message";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password==null){
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
		String captcha = getCaptcha(request);
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}


	public String getMessageParam() {
		return messageParam;
	}
	
	/**
	 * 登录成功之后跳转URL
	 */
	public String getSuccessUrl() {
		return super.getSuccessUrl();
	}
	
	@Override
	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse response) throws Exception {
		Principal p = UserUtils.getPrincipal();
		if (p != null){
			 WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
		}else{
			super.issueSuccessRedirect(request, response);
		}
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request, ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		if (IncorrectCredentialsException.class.getName().equals(className)
				|| UnknownAccountException.class.getName().equals(className)){
			message = "用户或密码错误, 请重试.";
		}
		else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")){
			message = StringUtils.replace(e.getMessage(), "msg:", "");
		}
		else{
			message = "系统出现点问题，请稍后再试！";
			e.printStackTrace(); // 输出到控制台
		}
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(getMessageParam(), message);
        return true;
	}
	
	boolean isAjax(HttpServletRequest request){  
        return  (request.getHeader("X-Requested-With") != null  && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString())   ) ;  
    }  
    /** 
     * 重写该方法  session超时 ajax请求 返回json  
     */  
    @Override  
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {  
        // TODO Auto-generated method stub  
        HttpServletRequest httpRequest = (HttpServletRequest) request;    
        HttpServletResponse httpResponse = (HttpServletResponse) response;    
          
        Subject subject = getSubject(request, response);   
          
        if (subject.getPrincipal() == null && isAjax(httpRequest)) {   
            httpResponse.reset();  
            httpResponse.setContentType("application/json");  
            httpResponse.setCharacterEncoding("utf-8");  
            httpResponse.setHeader("sessionstatus", "timeout");  
            httpResponse.sendError(518, "session timeout.");  
            Map<String, String> map = new HashMap<String, String>();  
            map.put("code", "-1");  
            map.put("msg", "session timeOut"); 
            /** 
             * 由于Web端使用iframe嵌套, 因此直接重定向到登录页面并不能总是完成地很完美, 比如HTTP请求来自 
             * iframe对象的时候, 只能让iframe加载到index.html, 体验不够好; 所以在这里将直接重定向改为向 
             * 页面输出一段JS代码来实现使顶部window跳转到默认的登录页面. 
             */  
//            String jsCode = "<script type='text/javascript'>" +  
//                    "var p=window;while(p!=p.parent){p=p.parent; } p.location.href='" +  
//                    httpRequest.getContextPath() +   
//                    "/index.html'</script>";  
            httpResponse.getWriter().print(JsonMapper.toJsonString(map));  
            return false;  
        }  
        return super.onAccessDenied(request, response);  
    } 

	
}