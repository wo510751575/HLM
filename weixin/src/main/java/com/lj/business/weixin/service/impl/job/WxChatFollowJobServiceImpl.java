package com.lj.business.weixin.service.impl.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.DateUtils;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.weixin.domain.ImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.cc.clientintf.IJob;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;

@Service
public class WxChatFollowJobServiceImpl implements IJob {
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WxChatFollowJobServiceImpl.class);
	
	@Resource
	private IImChatInfoService imChatInfoService;
	
	@Resource
	private IGuidMemberService guidMemberService;
	
	@Resource
	private IPersonMemberBaseService personMemberBaseService;
	
//	@Resource
//	private IClientFollowSummaryService clientFollowSummaryService;
//	
//	@Resource
//	private IClientFollowService clientFollowService;
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Override
	public void runJob() {
		
		logger.info("微信生产跟进记录job  ===============  >>>   start");
		
		//获取刷新频率
		String msgRecordRate = localCacheSystemParams.getSystemParam(SystemAliasName.cf.toString(),
				SystemParamConstant.MSG_RECORD, SystemParamConstant.MSG_RECORD_RATE);
		
		msgRecordRate = msgRecordRate == null ? "6" : msgRecordRate;
		
		Map<String, Object> param = new HashMap<>();
		
		param.put("beginTime", DateUtils.addHours(new Date(), -Integer.valueOf(msgRecordRate)));
		param.put("endTime", new Date());
		List<String> memberNos = imChatInfoService.findImByDate(param);
		
		for (String memberNo : memberNos) {//导购
			
			try {
				logger.info("产生导购:" + memberNo + "  微信生产跟进记录");
				
				FindGuidMember findGuidMember = new FindGuidMember();
				findGuidMember.setMemberNo(memberNo);
				FindGuidMemberReturn guidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
				
				param.put("memberNoGm", guidMemberReturn.getMemberNo());
				List<Map<String, Object>> wxCountMap = imChatInfoService.findCountImChatByGM(param);//查询六小时内改导购的聊天条数
				
				for (Map<String, Object> map : wxCountMap) {
					FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
					findPersonMemberBase.setNoWx(map.get("noWx").toString());
					FindPersonMemberBaseReturn personMember = null;
					try {
						personMember = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
					} catch (Exception e) {
						continue;
					}
					if (personMember == null) {
						continue;
					}
					
					/*FindClientFollowSummary findClientFollowSummary = new FindClientFollowSummary();
					findClientFollowSummary.setMerchantNo(guidMemberReturn.getMerchantNo());
					findClientFollowSummary.setMemberNoGm(guidMemberReturn.getMemberNo());
					findClientFollowSummary.setMemberNo(personMember.getMemberNo());
					FindClientFollowSummaryReturn summaryLast = clientFollowSummaryService.findClientFollowSummaryLast(findClientFollowSummary);//查询跟进记录
					
					if (summaryLast != null) {
						Map<String, Object> dateMap = DateUtil.getDayBeginAndEnd(new Date());
						
						FindClientFollow findClientFollow = new FindClientFollow();
						findClientFollow.setCfNo(summaryLast.getCfNo());
						findClientFollow.setComTaskType(KeepType.WECHAT.toString());
						findClientFollow.setBeginDate((Date)dateMap.get("beginTime"));
						findClientFollow.setEndDate((Date)dateMap.get("endTime"));
						ClientFollow clientFollow = clientFollowService.findClientFollowSelect(findClientFollow);
						
						if (clientFollow != null) {
							logger.info("增加导购:" + guidMemberReturn.getMemberNo() + " 和客户:" + personMember.getMemberNo() + " 的聊天条数");
							
							UpdateClientFollow updateClientFollow = new UpdateClientFollow();
							updateClientFollow.setCode(clientFollow.getCode());
							String followInfo = clientFollow.getFollowInfo();
							int number = Integer.valueOf(followInfo.substring(4, followInfo.length() - 1)) +
									(int)map.get("number");
							updateClientFollow.setFollowInfo("微信聊天" + number + "条");
							clientFollowService.updateClientFollow(updateClientFollow);
						} else {*/
							
							logger.info("新增导购:" + guidMemberReturn.getMemberNo() + " 和客户:" + personMember.getMemberNo() + " 的聊天条数");
							
							FindImChatInfo findImChatInfo = new FindImChatInfo();
							findImChatInfo.setMemberNoGm(guidMemberReturn.getMemberNo());
							findImChatInfo.setNoWx(map.get("noWx").toString());
//							findImChatInfo.setStartTime(DateUtils.formatDate((Date)dateMap.get("beginTime"), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
//							findImChatInfo.setEndTime(DateUtils.formatDate((Date)dateMap.get("endTime"), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
							ImChatInfo imChatInfo = imChatInfoService.findImFristInfo(findImChatInfo);
							
							/*AddClientFollow addClientFollow = new AddClientFollow();
							addClientFollow.setCfNo(summaryLast.getCfNo());
							addClientFollow.setMerchantNo(guidMemberReturn.getMerchantNo());
							addClientFollow.setMemberNo(personMember.getMemberNo());
							addClientFollow.setMemberName(personMember.getMemberName());
							addClientFollow.setMemberNoGm(guidMemberReturn.getMemberNo());
							addClientFollow.setMemberNameGm(guidMemberReturn.getMemberName());
							addClientFollow.setTaskName(KeepType.WECHAT.getName());
							addClientFollow.setFollowTime(imChatInfo == null ? new Date() :imChatInfo.getChatTime());
							addClientFollow.setFollowType(KeepType.WECHAT.toString());
							addClientFollow.setFollowInfo("微信聊天" + map.get("number").toString() + "条");
							addClientFollow.setDeal("N");
							addClientFollow.setStatus(Status.NORMAL);
							addClientFollow.setComTaskType(KeepType.WECHAT.toString());
							addClientFollow.setComTaskTypeCf(KeepType.WECHAT.toString());
							addClientFollow.setCreateId(guidMemberReturn.getMemberNo());
							clientFollowService.addCFOrder(addClientFollow, "0", false, false);
						}
					}*/
					
				}
				
			} catch (Exception e) {
				logger.error("产生导购" + memberNo + "微信跟进记录错误", e);
			}
		}

	}

}
