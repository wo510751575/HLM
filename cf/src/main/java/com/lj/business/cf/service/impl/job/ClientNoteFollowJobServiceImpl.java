package com.lj.business.cf.service.impl.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.DateUtils;
import com.lj.business.cf.domain.ClientFollow;
import com.lj.business.cf.domain.ClientNoteInfo;
import com.lj.business.cf.dto.clientFollow.AddClientFollow;
import com.lj.business.cf.dto.clientFollow.FindClientFollow;
import com.lj.business.cf.dto.clientFollow.UpdateClientFollow;
import com.lj.business.cf.dto.clientFollowSummary.FindClientFollowSummary;
import com.lj.business.cf.dto.clientFollowSummary.FindClientFollowSummaryReturn;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoList;
import com.lj.business.cf.emus.KeepType;
import com.lj.business.cf.emus.Status;
import com.lj.business.cf.service.IClientFollowService;
import com.lj.business.cf.service.IClientFollowSummaryService;
import com.lj.business.cf.service.IClientNoteInfoService;
import com.lj.business.cf.service.impl.WorkTaskUnfinishServiceImpl;
import com.lj.business.cf.utils.DateUtil;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.cc.clientintf.IJob;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;

@Service
public class ClientNoteFollowJobServiceImpl implements IJob {
	
	@Resource
	private IClientNoteInfoService clientNoteInfoService;
	
	@Resource
	private IMerchantService merchantService;
	
	@Resource
	private IGuidMemberService guidMemberService;
	
	@Resource
	private IPersonMemberBaseService personMemberBaseService;
	
	@Resource
	private IClientFollowSummaryService clientFollowSummaryService;
	
	@Resource
	private IClientFollowService clientFollowService;
	
	@Resource
	 private  LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	private static final Logger logger = LoggerFactory.getLogger(WorkTaskUnfinishServiceImpl.class);


	@Override
	public void runJob() {
		
		//获取导购
		List<FindClientNoteInfoList> list=clientNoteInfoService.findClientInfoMemberNoGm();
		
		//获取刷新频率
		String msgRecordRate =  localCacheSystemParams.getSystemParam(SystemAliasName.cf.toString(),
				SystemParamConstant.MSG_RECORD, SystemParamConstant.MSG_RECORD_RATE);
             
		     msgRecordRate = msgRecordRate == null ? "6" : msgRecordRate;
			for (FindClientNoteInfoList noteInfo : list) {//查询改商户下的所有导购
				
				FindGuidMember findGuidMember = new FindGuidMember();
				findGuidMember.setMemberNo(noteInfo.getMemberNoGm());
				FindGuidMemberReturn guidMemberReturn =null;
				//返回异常不处理
				try {
					 guidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("导购信息不存在！");
				}
				
				if(guidMemberReturn != null){
				Map<String, Object> param = new HashMap<>();
				
				param.put("beginTime", DateUtils.addHours(new Date(), -Integer.valueOf(msgRecordRate)).getTime());
				param.put("endTime", new Date().getTime());
				param.put("memberNoGm", noteInfo.getMemberNoGm());
				List<Map<String, Object>> noteCountMap = clientNoteInfoService.findCountNoteByGm(param);
				
				for (Map<String, Object> map : noteCountMap) {

					FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
					findPersonMemberBase.setMobile(map.get("mobile").toString());
					FindPersonMemberBaseReturn memberBaseReturn = personMemberBaseService.findByMobile(findPersonMemberBase);
					
					if (memberBaseReturn == null) {
						continue;
					}
					
					FindClientFollowSummary findClientFollowSummary = new FindClientFollowSummary();
					findClientFollowSummary.setMerchantNo(guidMemberReturn.getMerchantNo());
					findClientFollowSummary.setMemberNoGm(guidMemberReturn.getMemberNo());
					findClientFollowSummary.setMemberNo(memberBaseReturn.getMemberNo());
					FindClientFollowSummaryReturn summaryLast = clientFollowSummaryService.findClientFollowSummaryLast(findClientFollowSummary);//查询跟进记录
					
					if (summaryLast != null) {
						Map<String, Object> dateMap = DateUtil.getDayBeginAndEnd(new Date());
						
						FindClientFollow findClientFollow = new FindClientFollow();
						findClientFollow.setCfNo(summaryLast.getCfNo());
						findClientFollow.setComTaskType(KeepType.SMS.toString());
						findClientFollow.setBeginDate((Date)dateMap.get("beginTime"));
						findClientFollow.setEndDate((Date)dateMap.get("endTime"));
						ClientFollow clientFollow = clientFollowService.findClientFollowSelect(findClientFollow);
						
						if (clientFollow != null) {
							UpdateClientFollow updateClientFollow = new UpdateClientFollow();
							updateClientFollow.setCode(clientFollow.getCode());
							String followInfo = clientFollow.getFollowInfo();
							long number = Long.valueOf(followInfo.substring(4, followInfo.length() - 1)) +
									(long)map.get("number");
							updateClientFollow.setFollowInfo("发送短信" + number + "条");
							clientFollowService.updateClientFollow(updateClientFollow);
						} else {
							FindClientNoteInfo findClientNoteInfo = new FindClientNoteInfo();
							findClientNoteInfo.setMemberNoGm(guidMemberReturn.getMemberNo());
							findClientNoteInfo.setMobile(map.get("mobile").toString());
							findClientNoteInfo.setStartTime(((Date)dateMap.get("beginTime")).getTime() + "");
							findClientNoteInfo.setEndTime(((Date)dateMap.get("endTime")).getTime() + "");
							ClientNoteInfo clientNoteInfo = clientNoteInfoService.findFristNoteInfo(findClientNoteInfo);
							
							AddClientFollow addClientFollow = new AddClientFollow();
							addClientFollow.setCfNo(summaryLast.getCfNo());
							addClientFollow.setMerchantNo(guidMemberReturn.getMerchantNo());
							addClientFollow.setMemberNo(memberBaseReturn.getMemberNo());
							addClientFollow.setMemberName(memberBaseReturn.getMemberName());
							addClientFollow.setMemberNoGm(guidMemberReturn.getMemberNo());
							addClientFollow.setMemberNameGm(guidMemberReturn.getMemberName());
							addClientFollow.setFollowTime(clientNoteInfo == null ? new Date() : new Date(Long.valueOf(clientNoteInfo.getSendTime())));
							addClientFollow.setFollowType(KeepType.SMS.toString());
							addClientFollow.setFollowInfo("发送短信" + map.get("number").toString() + "条");
							addClientFollow.setDeal("N");
							addClientFollow.setStatus(Status.NORMAL);
							addClientFollow.setComTaskType(KeepType.SMS.toString());
							addClientFollow.setComTaskTypeCf(KeepType.SMS.toString());
							addClientFollow.setCreateId(guidMemberReturn.getMemberNo());
							clientFollowService.addCFOrder(addClientFollow, "0", false, false);
						}
					}
					
				}
			}
				
		}

	}

}
