package com.lj.business.st.service;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.MerchantDayOperateDto;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 梅宏博
 * 
 * 
 * CreateDate: 2017-09-27
 */
public interface IMerchantDayOperationService {

	/**
	 * 
	 *
	 * 方法说明：统计商户运营日报表
	 *
	 * @param merchantCode
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年09月27日
	 *
	 */
	public MerchantDayOperateDto generatorMerChantDayOperate(String merchantCode);
}
