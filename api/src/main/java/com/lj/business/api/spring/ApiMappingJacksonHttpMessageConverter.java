package com.lj.business.api.spring;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.lj.business.api.domain.GeneralResponse;

/**
 * 
 * 
 * 类说明：自定义转换器，将带有注解@ResponseBody的controller方法返回值全部转为GeneralResponse类对应的json字符串
 * <p/>
 * <br><b style="color: red">注意:</b><br/>
 * <br> 1、正常情况下，action方法必须定义{@link ResponseBody}注解，且返回数据类型必须为text、json，见{@link RequestMapping}属性produces定义
 * <br> 2、特殊情况下，如上传或下载文件，则可按实际业务逻辑定义action
 * <br> 3、 action方法定义返回对象为void或者返回空对象null时，
 * 		   spring mvc框架不会进入消息转换逻辑中，即不会调用各类型的HttpMessageConverter，所以在使用@ResponseBody时action方法不能返回void或null，如果方法无返回值时应返回{@link GeneralResponse}。
 *         <br>&nbsp;&nbsp;&nbsp;&nbsp;详情代码请见{@link RequestResponseBodyMethodProcessor#handleReturnValue(Object, org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest)}
 * 
 * <p/>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class ApiMappingJacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {
	
	private static final Logger log = LoggerFactory.getLogger(ApiMappingJacksonHttpMessageConverter.class);
	
	/**
	 * 处理响应格式为json或text的
	 */
	public ApiMappingJacksonHttpMessageConverter() {
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(new MediaType("application", "json", DEFAULT_CHARSET)); 
		list.add(new MediaType("application", "*+json", DEFAULT_CHARSET));
		list.add(new MediaType("application", "text", DEFAULT_CHARSET));
		list.add(new MediaType("text", "plain", DEFAULT_CHARSET));
		list.add(new MediaType("text", "html", DEFAULT_CHARSET));
		setSupportedMediaTypes(list);
	}

	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		if(object instanceof GeneralResponse) {
			log.info("object instanceof GeneralResponse HttpResponse： " + object);
			super.writeInternal(object, type, outputMessage);
		} else {
			GeneralResponse response = GeneralResponse.generateSuccessResponse(object);
			log.info("generateSuccessResponse HttpResponse： " + response);
			super.writeInternal(response,type, outputMessage);
		}
	}
	
}
