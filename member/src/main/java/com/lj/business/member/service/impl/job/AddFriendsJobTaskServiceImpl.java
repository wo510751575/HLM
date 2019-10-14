package com.lj.business.member.service.impl.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.StringUtils;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.AddFriendsTaskDetailDto;
import com.lj.business.member.dto.AddFriendsTaskDto;
import com.lj.business.member.dto.FindAddFriendsTaskDetailPage;
import com.lj.business.member.emus.AddfriendsTaskStatus;
import com.lj.business.member.service.IAddFriendsTaskDetailService;
import com.lj.business.member.service.IAddFriendsTaskService;
import com.lj.business.supcon.dto.contacts.AddFriendsByPhoneMessage;
import com.lj.business.supcon.dto.contacts.UserInfo;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.cc.clientintf.IJob;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.GroupName;
import com.lj.cc.enums.SystemAliasName;

@Service
public class AddFriendsJobTaskServiceImpl implements IJob{
	
	private static final Logger logger = LoggerFactory.getLogger(AddFriendsJobTaskServiceImpl.class);
	@Autowired
	private IAddFriendsTaskDetailService addFriendsTaskDetailService;
	@Autowired
	private IAddFriendsTaskService addFriendsTaskService;
	@Autowired 
    private ICommonService commonService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	/**
	 * 定时任务执行
	 */
	/*@Override
	public void runJob() {
		logger.info("**********定时加粉任务开始*******************");
		try {
			
			String numStr = localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(), GroupName.SYSPARAM.toString(), "num");
			int num =StringUtils.isEmpty(numStr)?20:Integer.valueOf(numStr);
			
			
			List<AddFriendsTaskDetailDto> list =addFriendsTaskDetailService.findJobResult(num);
			
			//java 8 List 以noWxGM分组 Map<String,List<AddFriendsTaskDetailDto>>
			Map<String, List<AddFriendsTaskDetailDto>> groupBy = list.stream().collect(Collectors.groupingBy(AddFriendsTaskDetailDto::getNoWxGm));
			//需更新详情的状态以及执行时间
			AddFriendsTaskDetailDto addFriendsTaskDetailDto = new AddFriendsTaskDetailDto();
			
			for (Map.Entry<String, List<AddFriendsTaskDetailDto>> entry: groupBy.entrySet()) {
				List<AddFriendsTaskDetailDto> detailList= entry.getValue();
				String noWxGm = entry.getKey();
				List<UserInfo> userInfos = new ArrayList<UserInfo>();
				
				for (AddFriendsTaskDetailDto detail : detailList) {
					UserInfo userInfo = new UserInfo();
					userInfo.setMobile(detail.getPhone());
					userInfo.setNickRemark(detail.getUsername());
					userInfo.setValidation(detail.getSendMessage());
					userInfos.add(userInfo);
					addFriendsTaskDetailDto.addCodes(detail.getCode());
				}
				
				logger.info("**********本次定时加粉手机号：" + userInfos);
				if(userInfos.size()>0) {
					//开始推报文给中控执行
					IContactsService basic = commonService.getHessianContactsService(noWxGm);
					AddFriendsByPhoneMessage addFriendsByPhoneMessage = new AddFriendsByPhoneMessage();
					addFriendsByPhoneMessage.setWxNoGm(noWxGm);	
					addFriendsByPhoneMessage.setList(userInfos);
					basic.sendPhoneNumberToZKMessage(addFriendsByPhoneMessage);
				}
			}
			
			//更新详情状态
			if(addFriendsTaskDetailDto.getCodes().size()>0) {
				addFriendsTaskDetailDto.setStatus(AddfriendsTaskStatus.EXECUTING.getCode());
				addFriendsTaskDetailDto.setExecuteDate(new Date());
				addFriendsTaskDetailService.updateByCond(addFriendsTaskDetailDto);
			}
			logger.info("**********定时加粉任务结束*******************");
		}catch(Exception e) {
			logger.error("手机号定时任务加粉错误：", e);
		}
		
	}*/
	
	
	/**
	 * 定时任务执行
	 */
	@Override
	public void runJob() {
		try {
				logger.info("**********定时加粉任务开始*******************");
				String numStr = localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(), GroupName.SYSPARAM.toString(), "num");
				int num =StringUtils.isEmpty(numStr)?20:Integer.valueOf(numStr);
				
				AddFriendsTaskDto addFriendsTaskDto = new AddFriendsTaskDto();
				addFriendsTaskDto.setStatus(String.valueOf(CommonConstant.I_NO));//未完成
				String noWxGms =addFriendsTaskService.selectDistinctGroupByNoWxGms(addFriendsTaskDto);
				
				if(StringUtils.isNotEmpty(noWxGms)) {
					
					FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage = new FindAddFriendsTaskDetailPage();
					findAddFriendsTaskDetailPage.setStart(0);
					findAddFriendsTaskDetailPage.setLimit(num); //每次向中控推送20个人
					AddFriendsTaskDetailDto paramDetail = new AddFriendsTaskDetailDto();
//					paramDetail.setMerchantNo(task.getMerchantNo());
					paramDetail.setStatus(AddfriendsTaskStatus.START.getCode());//已启动
					
					
					String noWxArrays[] =noWxGms.split(",");
					//需更新详情的状态以及执行时间
					AddFriendsTaskDetailDto addFriendsTaskDetailDto = new AddFriendsTaskDetailDto();
			        
			      //遍历该任务里面的所有微信
					for(String wx : noWxArrays) {
//						paramDetail.setTaskCode(task.getCode());
						paramDetail.setNoWxGm(wx);
						findAddFriendsTaskDetailPage.setParam(paramDetail);
						
						Page<AddFriendsTaskDetailDto> detailPage = addFriendsTaskDetailService.findAddFriendsTaskDetailPage(findAddFriendsTaskDetailPage);
						if(detailPage.getRows().isEmpty()) {
							logger.info("该终端={}启动的任务加粉已全部执行：",wx);
							continue;
						}
						List<AddFriendsTaskDetailDto> detailList = (List<AddFriendsTaskDetailDto>)detailPage.getRows();
						
						List<UserInfo> userInfos = new ArrayList<UserInfo>();
				            
						for(AddFriendsTaskDetailDto detail : detailList) {
							UserInfo userInfo = new UserInfo();
							userInfo.setMobile(detail.getPhone());
							userInfo.setNickRemark(detail.getUsername());
							userInfo.setValidation(detail.getSendMessage());
							userInfos.add(userInfo);
							addFriendsTaskDetailDto.addCodes(detail.getCode());
						}
						logger.info("**********本次定时加粉手机号：" + userInfos);
						if(userInfos.size()>0) {
							//开始推报文给中控执行
							IContactsService basic = commonService.getHessianContactsService(wx);
							AddFriendsByPhoneMessage addFriendsByPhoneMessage = new AddFriendsByPhoneMessage();
							addFriendsByPhoneMessage.setWxNoGm(wx);	
							addFriendsByPhoneMessage.setList(userInfos);
							basic.sendPhoneNumberToZKMessage(addFriendsByPhoneMessage);
						}
					}
					//更新详情状态
					if(addFriendsTaskDetailDto.getCodes().size()>0) {
						addFriendsTaskDetailDto.setStatus(AddfriendsTaskStatus.EXECUTING.getCode());
						addFriendsTaskDetailDto.setExecuteDate(new Date());
						addFriendsTaskDetailService.updateByCond(addFriendsTaskDetailDto);
					}
				}
			logger.info("**********定时加粉任务结束*******************");
		}catch(Exception e) {
			logger.error("手机号定时任务加粉错误：", e);
		}
		
	}
}
