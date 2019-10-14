package com.lj.business.cf.service.impl.job.unfinish;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cf.service.IWorkTaskService;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.dto.merchantParams.FindMerchantParamsReturn;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.emus.MemberType;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.cc.clientintf.IJob;
import com.lj.messagecenter.emus.SendType;
import com.lj.messagecenter.msg.dto.AddNotifyInfo;
import com.lj.messagecenter.msg.enums.MsgSystemType;
import com.lj.messagecenter.msg.service.INotifyService;

/**
 * 
 * 
 * 类说明：未完成任务调度提醒
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 梅宏博
 *   
 * CreateDate: 2017年10月24日
 */
@Service
public class UnfinishTaskRemindJobServiceImpl implements IJob {
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(UnfinishTaskRemindJobServiceImpl.class);
	
	@Resource
	private IMerchantParamsService merchantParamsService;
	
	@Resource
	private IWorkTaskService workTaskService;
	
	@Resource
	private IGuidMemberService guidMemberService;
	
	@Resource
	private INotifyService notifyService;
	
	
	private final static String TASK_REMIND_TEMP = "taskRemindTemp";//模板分组名
	
	@PostConstruct
	public void initRaskRemindEnum(){
		UnfinishTaskRemindEnum.setService(merchantParamsService, workTaskService);
	}
	
	@Override
	public void runJob() {
		
		logger.info("unfinishTaskRemindJob start");
		
		/** 参数key格式 ：（key + 小时数）  **/
		//获取当前时间小时数
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		
		//查询商户未完成任务模板
		FindMerchantParams findMerchantParams = new FindMerchantParams();
		findMerchantParams.setGroupName(TASK_REMIND_TEMP + hour);
		List<FindMerchantParamsReturn> merchantParams = merchantParamsService.findMerchantParamsByGN(findMerchantParams);
		
		loop : for (FindMerchantParamsReturn merchantParam : merchantParams) {
			
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMerchantNo(merchantParam.getMerchantNo());
			List<FindGuidMemberPageReturn> guidMembers = guidMemberService.findGuidMembers(findGuidMemberPage);
			for (FindGuidMemberPageReturn guidMember : guidMembers) {
				
				Map<String, Boolean> flag = new HashMap<>();
				flag.put(UnfinishTaskRemindEnum.PUSH_FLAG_KEY, false);//推送标志，默认不推送
				
				//导购发送信息
				String message = merchantParam.getSysParamValue();
				UnfinishTaskRemindEnum[] remindEnums = UnfinishTaskRemindEnum.values();
				for (UnfinishTaskRemindEnum unfinishTaskRemindEnum : remindEnums) {
					if (unfinishTaskRemindEnum.isContains(message)) {
						message = unfinishTaskRemindEnum.excute(message, merchantParam.getMerchantNo(), guidMember.getMemberNo(), hour, flag);
					}
				}
				
				//
				if (!flag.get(UnfinishTaskRemindEnum.PUSH_FLAG_KEY)) {
					continue;
				}
				
				if (message.contains("${")) {
					logger.error("商户{}模板参数配置错误", merchantParam.getMerchantName());
					continue loop;
				}
				
				//发送推送
				AddNotifyInfo addNotifyInfo = new AddNotifyInfo();
				addNotifyInfo.setSendType(SendType.SINGLE.toString());
				addNotifyInfo.setMerchantNo(merchantParam.getMerchantNo());
				addNotifyInfo.setMemberNo(guidMember.getMemberNo());
				addNotifyInfo.setMemberName(guidMember.getMemberName());
				addNotifyInfo.setMemberType(MemberType.GUID.toString());
				addNotifyInfo.setMobile(guidMember.getMobile());
				addNotifyInfo.setSysType(MsgSystemType.ALL.toString());
				
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("unfinishTaskMessage", message);
				addNotifyInfo.setContent(JsonUtils.jsonFromObject(paramMap));
				notifyService.sendCustomMsgInfo(addNotifyInfo);
			}
			
		}
	}

}
