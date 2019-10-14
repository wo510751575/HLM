package com.lj.business.weixin.service;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaException;
import com.lj.business.weixin.dto.FindSendFriendsJobPage;
import com.lj.business.weixin.dto.FindSendFriendsJobPageReturn;
import com.lj.business.weixin.dto.friendsjob.AddSendFriendsJob;
import com.lj.business.weixin.dto.friendsjob.UpdateSendFriendsJob;

/**
 * 
 * 
 * 类说明：发送朋友圈任务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月21日
 */
public interface ISendFriendsJobService {

    void addSendFriendsJob(AddSendFriendsJob addSendFriendsJob) throws TsfaException;
    
    void delSendFriendsJob(AddSendFriendsJob addSendFriendsJob) throws TsfaException;

    /**
     * 查看朋友圈记录
     * @param findSendFriendsJobPage
     * @return
     *
     * @author 彭俊霖
     * @CreateDate 2018年6月21日下午3:23:45
     */
	Page<FindSendFriendsJobPageReturn> findSendFriendsJobPage(FindSendFriendsJobPage findSendFriendsJobPage) throws TsfaException;
	/**
	 * 查询朋友圈记录
	 * @param code
	 * @return
	 */
	void repeat(String code) throws TsfaException;
	
	/**
	 * 修改朋友圈任务
	 * @param updateSendFriendsJob
	 * @throws TsfaException
	 */
	void updateSendFriendsJob(UpdateSendFriendsJob updateSendFriendsJob) throws TsfaException;

	/**
	 *@Desc 
	 *@param addSendFriendsJob
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午6:22:01
	 */
	void delete(AddSendFriendsJob addSendFriendsJob)throws TsfaException;
	/**
	 * 获取对象
	 * @param findSendFriendsJobPage
	 * @return
	 * @throws TsfaException
	 */
	FindSendFriendsJobPageReturn findSendFriendsJob(FindSendFriendsJobPage findSendFriendsJobPage) throws TsfaException;
}
