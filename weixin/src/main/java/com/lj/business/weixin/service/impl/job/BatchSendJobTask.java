/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  BatchSendJob.java   
 * @Package com.lj.business.weixin.service.impl.job   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019年6月26日   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.lj.business.weixin.service.impl.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.business.weixin.service.IImGroupChatJobService;
import com.lj.cc.clientintf.IParamJob;

/**   
 * @ClassName:  BatchSendJob   
 * @Description:TODO(群发消息任务)   
 * @author: 贾光朝 
 * @date:   2019年6月26日   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
public class BatchSendJobTask implements IParamJob {
	
	private static final Logger logger = LoggerFactory.getLogger(BatchSendJobTask.class);
	
	@Resource
	private IImGroupChatJobService iImGroupChatJobService;

	/**   
	 * <p>Title: runParamJob</p>   
	 * <p>Description: </p>   
	 * @param param   
	 * @see com.lj.cc.clientintf.IParamJob#runParamJob(java.lang.String)   
	 */
	@Override
	public void runParamJob(String param) {
		logger.debug("开始群发任务,编号:{}", param);
		iImGroupChatJobService.doImGroupChat(param);
		
	}

}
