package com.lj.business.api.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

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
 * 类说明：继承HttpServletRequestWrapper，主要有以下两个作用：
 * 	1、将由客户端提交的业务参数JSON格式字符串转化为key-value参数， 再设置到HttpServletRequest中，这样可以由action方法直接以getParameter方法获取参数值
 * 	2、业务前置检查失败时，将错误信息注入HttpServletRequest中
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
public class ApiHttpServletRequest extends HttpServletRequestWrapper {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	private final Map<String, String[]> parameters = new TreeMap<String, String[]>();

	/**
	 * 构造一个HttpServletRequest，将业务参数paramJson分解
	 * @param request
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ApiHttpServletRequest(HttpServletRequest request) throws JsonMappingException, IOException {
		super(request);
		parameters.putAll(super.getParameterMap());
		this.buildParamJson();
	}
	
	/**
	 * 构造一个HttpServletRequest，将错误信息进行注入
	 * @param request
	 * @param e
	 */
	@SuppressWarnings("unchecked")
	public ApiHttpServletRequest(HttpServletRequest request, Throwable e) {
		super(request);
		parameters.putAll(super.getParameterMap());
		ResponseCode responseCode = GeneralResponse.getErrorResponse(e);
		parameters.put("errorCode", new String[]{responseCode.getCode()});
		parameters.put("errorMessage", new String[]{responseCode.getMessage()});
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
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void buildParamJson() throws JsonMappingException, IOException {
		String [] params = parameters.get(ApiAccessConstants.PARAM_JSON);
		if(params == null || params.length == 0) {
			return;
		}
		String paramJson = params[0];
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
        }
    }
	
	@Override
    public String getParameter(String name) {
        String[] strings = getParameterMap().get(name);
        if (strings != null) {
            return strings[0];
        }
        return super.getParameter(name);
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
		parameters.put(name, new String[] {value});
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
		this.parameters.putAll(parameters);
	}
	
    @Override
    public Map<String, String[]> getParameterMap() {
        return Collections.unmodifiableMap(parameters);
    }
    
    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(getParameterMap().keySet());
    }
    
    @Override
    public String[] getParameterValues(final String name) {
        return getParameterMap().get(name);
    }
}
