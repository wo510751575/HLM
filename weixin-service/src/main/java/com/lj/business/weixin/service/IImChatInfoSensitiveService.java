package com.lj.business.weixin.service;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.FindImChatInfoSensitivePage;
import com.lj.business.weixin.dto.FindImChatInfoSensitivePageReturn;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2018年1月11日
 */
public interface IImChatInfoSensitiveService {
	
	/**
	 * 
	 *
	 * 方法说明：分页查询IM聊天记录信息
	 *
	 * @param findImChatInfoSensitivePage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月11日
	 *
	 */
	public Page<FindImChatInfoSensitivePageReturn> findImChatInfoSensitivePage(FindImChatInfoSensitivePage findImChatInfoSensitivePage)  throws TsfaServiceException;
	

}
