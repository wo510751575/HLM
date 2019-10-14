package com.lj.business.weixin.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.FindMerchantSendFriendsJobPage;
import com.lj.business.weixin.dto.MerchantSendFriendsJobDto;
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
public interface IMerchantSendFriendsJobService {
	
	/**
	 * 
	 *
	 * 方法说明：添加商户发朋友圈任务信息
	 *
	 * @param merchantSendFriendsJobDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addMerchantSendFriendsJob(MerchantSendFriendsJobDto merchantSendFriendsJobDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找商户发朋友圈任务信息
	 *
	 * @param findMerchantSendFriendsJob
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public MerchantSendFriendsJobDto findMerchantSendFriendsJob(MerchantSendFriendsJobDto merchantSendFriendsJobDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询商户发朋友圈任务信息
	 *
	 * @param findMerchantSendFriendsJobPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<MerchantSendFriendsJobDto>  findMerchantSendFriendsJobs(FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改商户发朋友圈任务信息
	 *
	 * @param updateMerchantSendFriendsJob
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateMerchantSendFriendsJob(MerchantSendFriendsJobDto merchantSendFriendsJobDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询商户发朋友圈任务信息
	 *
	 * @param findMerchantSendFriendsJobPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<MerchantSendFriendsJobDto> findMerchantSendFriendsJobPage(FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage) throws TsfaServiceException;
	

}
