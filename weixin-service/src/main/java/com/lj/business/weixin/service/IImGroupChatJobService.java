package com.lj.business.weixin.service;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.FindImGroupChatJobPage;
import com.lj.business.weixin.dto.ImGroupChatJobDto;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
public interface IImGroupChatJobService {

	/**   
	 * @Title: addImGroupChatJob   
	 * @Description: TODO(新增群发任务)   
	 * @param: @param imGroupChatJobDto
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	String addImGroupChatJob(ImGroupChatJobDto imGroupChatJobDto)throws TsfaServiceException;
	
	/**
	 * 
	 * @Title: doImGroupChat   
	 * @Description: TODO(执行群发任务)   
	 * @param: @param code
	 * @param: @return
	 * @param: @throws TsfaServiceException      
	 * @return: String      
	 * @throws
	 */
	void doImGroupChat(String code)throws TsfaServiceException;

	/**   
	 * @Title: findImGroupChatJobPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param page
	 * @param: @return      
	 * @return: Page<ImGroupChatJobDto>      
	 * @throws   
	 */
	Page<ImGroupChatJobDto> findImGroupChatJobPage(FindImGroupChatJobPage page)throws TsfaServiceException;
	

}
