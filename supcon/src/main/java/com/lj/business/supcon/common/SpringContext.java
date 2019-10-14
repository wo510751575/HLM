package com.lj.business.supcon.common;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * 类说明：spring容器
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
@Component
public class SpringContext implements ApplicationContextAware {
	
	/** spring容器上下文 */
	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContext.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public final static <T> T getBean(Class<T> clazz) {
		return null == applicationContext ? null : applicationContext.getBean(clazz);
	}
	
	public final static <T> Collection<T> getBeansOfType(Class<T> clazz) {
		return null == applicationContext ? null : applicationContext.getBeansOfType(clazz).values();
	}
	
	public final static <T> T getBean(String name, Class<T> requiredType) {
		return null == applicationContext ? null : applicationContext.getBean(name, requiredType);
	}

	public static Object getBean(String name) {
		return null == applicationContext ? null : applicationContext.getBean(name);
	}

	
}
