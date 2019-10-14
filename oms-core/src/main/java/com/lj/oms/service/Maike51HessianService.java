package com.lj.oms.service;

import com.lj.oms.entity.dto.Maike51Dto;
import com.lj.oms.entity.dto.Maike51DtoReturn;

/**
 * 
 * 
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2018年1月29日
 */
public interface Maike51HessianService {
	
	/**
	 * 
	 *
	 * 方法说明：创建商户
	 * 建立商户，OMS商户管理员账号，账号授权防止重复调用
	 *
	 * @param maike51Dto
	 *
	 * @author 段志鹏 CreateDate: 2018年1月29日
	 *
	 */
	public Maike51DtoReturn createUserToKen(Maike51Dto maike51Dto);
	
}
