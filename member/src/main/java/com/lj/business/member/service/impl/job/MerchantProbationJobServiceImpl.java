package com.lj.business.member.service.impl.job;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.dto.UpdateMerchant;
import com.lj.business.member.emus.MemberType;
import com.lj.business.member.emus.MerchantProbationStatusEnum;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.cc.clientintf.IJob;
import com.lj.messagecenter.emus.SendType;
import com.lj.messagecenter.msg.dto.AddNotifyInfo;
import com.lj.messagecenter.msg.enums.MsgSystemType;
import com.lj.messagecenter.msg.service.INotifyService;

/**
 * 
 * 
 * 类说明：商户剩余试用时间提醒
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
public class MerchantProbationJobServiceImpl implements IJob{
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MerchantProbationJobServiceImpl.class);
	
	private static final String REMAIN_DAYS = "60,30,7,0";//提醒剩余天数
	private static final String MESSAGE = "聚客APP试用时间剩余${day}天，欢迎续订！";//发送消息模板
	private static final String END_MESSAGE = "聚客APP试用期已结束，欢迎续订！";//发送消息模板
	
	@Resource
	private IMerchantService merchantService;
	
	@Resource
	private IGuidMemberService guidMemberService;

	@Resource
	private INotifyService notifyService;

	@Override
	public void runJob() {
		
		logger.info("merchantProbationJob start");
		
		List<FindMerchantPageReturn> merchants = merchantService.findAllMerchant();
		loop : for (FindMerchantPageReturn merchant : merchants) {
			
			if (merchant.getEndProbationTime() == null) {
				continue;
			}
			
			//剩余试用时间
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(merchant.getEndProbationTime());
			int endDay = endCal.get(Calendar.DAY_OF_YEAR);
			
			Calendar nowCal = Calendar.getInstance();
			int nowDay = nowCal.get(Calendar.DAY_OF_YEAR);
			
			int interval = endDay - nowDay;
			
			//当商户试用期已到修改试用状态
			if (interval == 0) {
				UpdateMerchant updateMerchant = new UpdateMerchant();
				updateMerchant.setCode(merchant.getCode());
				updateMerchant.setProbationStatus(MerchantProbationStatusEnum.END.toString());
				merchantService.updateMerchant(updateMerchant);
			}
			
			String[] days = REMAIN_DAYS.split(",");
			for (String day : days) {
				if (Integer.valueOf(day) == interval) {
					
					String message = interval == 0 ? END_MESSAGE : MESSAGE.replaceAll("\\$\\{day\\}", day);
					
					logger.info("商户{},{}", merchant.getMerchantName(), message);
					
					FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
					findGuidMemberPage.setMerchantNo(merchant.getMerchantNo());
					List<FindGuidMemberPageReturn> guidMembers = guidMemberService.findGuidMembers(findGuidMemberPage);
					
					for (FindGuidMemberPageReturn guidMember : guidMembers) {
						//发送推送
						AddNotifyInfo addNotifyInfo = new AddNotifyInfo();
						addNotifyInfo.setSendType(SendType.SINGLE.toString());
						addNotifyInfo.setMerchantNo(merchant.getMerchantNo());
						addNotifyInfo.setMemberNo(guidMember.getMemberNo());
						addNotifyInfo.setMemberName(guidMember.getMemberName());
						addNotifyInfo.setMemberType(MemberType.GUID.toString());
						addNotifyInfo.setMobile(guidMember.getMobile());
						addNotifyInfo.setSysType(MsgSystemType.ALL.toString());
						
						Map<String, String> paramMap = new HashMap<String, String>();
						paramMap.put("probationStatus", MerchantProbationStatusEnum.getStatus(day).toString());
						paramMap.put("message", message);
						addNotifyInfo.setContent(JsonUtils.jsonFromObject(paramMap));
						notifyService.sendCustomMsgInfo(addNotifyInfo);
					}
					
					continue loop;
				}
			}
		}
	}

}
