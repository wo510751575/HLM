package com.lj.business.api.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lj.base.core.util.StringUtils;
import com.lj.business.api.common.ApiAccessConstants;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.domain.ResponseCode;

/**
 * 
 * 
 * 类说明：继承DefaultMultipartHttpServletRequest，主要有以下三个作用：
 * 	1、将由客户端提交的业务参数JSON格式字符串转化为key-value参数， 再设置到HttpServletRequest中，这样可以由action方法直接以getParameter方法获取参数值
 * 	2、接收文件上传至服务器
 *  3、业务前置检查失败时，将错误信息注入HttpServletRequest中
 *  
 * 
 * <p/>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class ApiMultipartHttpServletRequest extends DefaultMultipartHttpServletRequest {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 构造一个HttpServletRequest，将业务参数paramJson分解
	 * @param request
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public ApiMultipartHttpServletRequest(DefaultMultipartHttpServletRequest request) throws JsonMappingException, IOException {
		super(request);
		setMultipartFiles(request.getMultiFileMap());
		setMultipartParameters(request.getParameterMap());
//		setMultipartParameterContentTypes(request.getcon);	// 未实现 TODO
        this.buildParamJson();
	}
	
	/**
	 * 构造一个HttpServletRequest，将错误信息进行注入
	 * @param request
	 * @param e
	 */
	public ApiMultipartHttpServletRequest(HttpServletRequest request, Throwable e) {
		super(request);
		ResponseCode responseCode = GeneralResponse.getErrorResponse(e);
		Map<String, String[]> parameters = new TreeMap<String, String[]>();
		parameters.put("errorCode", new String[]{responseCode.getCode()});
		parameters.put("errorMessage", new String[]{responseCode.getMessage()});
		super.setMultipartParameters(parameters);
	}
	
	/**
	 * 
	 *
	 * 方法说明：将业务参数从JSON格式的字符串转换为表单参数
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void buildParamJson() throws JsonParseException, JsonMappingException, IOException {
		String paramJson = super.getParameter(ApiAccessConstants.PARAM_JSON);
    	if(StringUtils.isEmpty(paramJson)) {
    		return;
    	}
    	
        Map<String, Object> req = objectMapper.readValue(paramJson, new TypeReference<Map<String, Object>>() {});
        if (req != null) {
            addMapEntries(req);
        }
	}
	
	private void addMapEntries(Map<String, Object> req) {
        if (req != null && !req.isEmpty()) {
    		Map<String, String[]> parameters = new TreeMap<String, String[]>();
            for (Map.Entry<String, Object> prop : req.entrySet()) {
                Object value = prop.getValue();
                if (value != null) {
                    if (value instanceof Collection<?>) {
                        Collection<?> a = (Collection<?>) value;
                        List<String> v = new ArrayList<String>();
                        for (Object e : a) {
                            v.add(e.toString());
                        }
                        parameters.put(prop.getKey(), v.toArray(new String[v.size()]));
                    } else {
                        String svalue = value.toString();
                        parameters.put(prop.getKey(), new String[] { svalue });
                    }
                }
            }
            super.setMultipartParameters(parameters);
        }
    }
	
	/**
	 * 
	 *
	 * 方法说明：设置参数
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param name
	 * @param value
	 */
	public void setParameter(String name, String value) {
		Map<String, String[]> parameters = new TreeMap<String, String[]>();
		parameters.put(name, new String[] {value});
		super.setMultipartParameters(parameters);
	}
	
	/**
	 * 
	 *
	 * 方法说明：设置参数
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param parameters
	 */
	public void setParameters(Map<String, String[]> parameters) {
		super.setMultipartParameters(parameters);
	}
}
