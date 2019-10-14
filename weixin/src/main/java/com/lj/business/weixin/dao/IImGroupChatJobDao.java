package com.lj.business.weixin.dao;

import java.util.List;

import com.lj.business.weixin.domain.ImGroupChatJob;
import com.lj.business.weixin.dto.FindImGroupChatJobPage;
import com.lj.business.weixin.dto.ImGroupChatJobDto;

public interface IImGroupChatJobDao {

	/**   
	 * @Title: insertSelective   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param imGroupChatJob      
	 * @return: void      
	 * @throws   
	 */
	void insertSelective(ImGroupChatJob imGroupChatJob);

	/**   
	 * @Title: selectByPrimaryKey   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param code
	 * @param: @return      
	 * @return: ImGroupChatJob      
	 * @throws   
	 */
	ImGroupChatJob selectByPrimaryKey(String code);

	/**   
	 * @Title: findImGroupChatJobPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param page
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findImGroupChatJobPageCount(FindImGroupChatJobPage page);

	/**   
	 * @Title: findImGroupChatJobPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param page
	 * @param: @return      
	 * @return: List<ImGroupChatJobDto>      
	 * @throws   
	 */
	List<ImGroupChatJobDto> findImGroupChatJobPage(FindImGroupChatJobPage page);
   

}