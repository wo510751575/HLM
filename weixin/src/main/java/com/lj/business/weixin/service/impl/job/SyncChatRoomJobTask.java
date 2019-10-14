package com.lj.business.weixin.service.impl.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.chatroom.SyncChatRoomMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.cc.clientintf.IJob;
import com.lj.cc.service.ISystemInfoService;
import com.lj.distributecache.RedisCache;

/**
 * 同步所有微信群聊
 * @author wo510
 *
 */
@Service
public class SyncChatRoomJobTask implements IJob {
	private static final Logger logger = LoggerFactory.getLogger(SyncChatRoomJobTask.class);

	@Autowired 
	ICommonService commonService;
	@Autowired
	private IShopTerminalService shopTerminalService; 
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired 
	private ISystemInfoService systemInfo;
	    
	@Autowired 
	private RedisCache redisCache;

	@Override
	public void runJob() {
		logger.info("自动同步群聊 --------start");
		/**
		 * 获取所有中控微信
		 */
		List<String> list= shopTerminalService.findAllNoWx();
		for (int i = 0; i < list.size(); i++) {
			final String str = list.get(i);
			logger.info("自动同步群聊  nowx={}--------end",str);
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					SyncChatRoomMessage syncChatRoomMessage = new SyncChatRoomMessage();
					syncChatRoomMessage.setNoWxZk(str);
					syncChatRoomMessage.setNowSync(true);//立即同步
					try {
						Thread.sleep(10000);//休眠
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					 IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(syncChatRoomMessage.getNoWxZk());
					 basic.sendSyncChatRoom(syncChatRoomMessage);
				}
			});
		}
		
		logger.info("自动同步群聊  --------end");
	}


}
