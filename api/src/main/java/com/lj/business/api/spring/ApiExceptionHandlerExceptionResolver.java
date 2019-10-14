package com.lj.business.api.spring;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.domain.GeneralResponse;

/**
 * 
 * 
 * 类说明：{@link Controller} 异常处理公共逻辑
 *  1、如果action方法定义了{@link ResponseBody}注解，则发生异常时按{@link GeneralResponse}对象生成json格式字符串并返回
 *  2、否则原样返回{@link ModelAndView}
 * 注：
 *  1、正常情况下，action方法必须定义{@link ResponseBody}注解，且返回数据类型必须为text、json，见{@link RequestMapping}属性produces定义
 *  2、特殊情况下，如上传或下载文件，则可按实际业务逻辑定义action
 * <p/>
 * 详细描述：
 * @Company: 扬恩科技有限公司
 * 
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class ApiExceptionHandlerExceptionResolver extends ExceptionHandlerExceptionResolver {
	
	private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandlerExceptionResolver.class);

	private String defaultErrorView;  
    
    public String getDefaultErrorView() {  
        return defaultErrorView;  
    }  
  
    public void setDefaultErrorView(String defaultErrorView) {  
        this.defaultErrorView = defaultErrorView;  
    }  
  
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {  
        log.error( "【抛出异常】异常路径为：" + request.getServletPath(), exception);
        
    	if (handlerMethod == null) {  
            return null;  
        }  
          
        // 获取action方法
        Method method = handlerMethod.getMethod();  
        if (method == null) {  
            return null;  
        }
        ResponseBody responseBody = AnnotationUtils.findAnnotation(method, ResponseBody.class);  
        // 如果action方法定义了ResponseBody注解，则发生异常时返回json格式字符串
		if (responseBody != null) {
			PrintWriter writer = null;
			try {
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
                writer = response.getWriter();
                String json = JsonUtils.jsonFromObject_AllToString(GeneralResponse.generateFailureResponse(exception));
                log.info("Error HttpResponse： " + json);
//                writer.write(json);
                writer.print(json);
            } catch (IOException e) {
                log.error("异常公共处理逻辑发生异常：", e);
            } finally {
            	if(writer != null) {
            		writer.flush();
            		writer.close();
            	}
            }
        	return null;
        }
          
		// 否则返回view
        ModelAndView returnValue = super.doResolveHandlerMethodException(request, response, handlerMethod, exception);  
        if(returnValue.getViewName() == null){  
            returnValue.setViewName(defaultErrorView);  
        }  
          
        return returnValue;  
    }  
    
    
}
