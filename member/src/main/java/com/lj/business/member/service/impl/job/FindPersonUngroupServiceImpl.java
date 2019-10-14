package com.lj.business.member.service.impl.job;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.dto.ManagerMemberDto;
import com.lj.business.member.dto.ManagerMemberReturnDto;
import com.lj.business.member.emus.MemberType;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.cc.clientintf.IJob;
import com.lj.messagecenter.emus.SendType;
import com.lj.messagecenter.msg.dto.AddMessageInfo;
import com.lj.messagecenter.msg.enums.MsgType;
import com.lj.messagecenter.msg.service.IMessageInfoService;

/**
 * 
 * 
 * 类说明：获取每个导购未分组的人数
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 杨杰
 * 
 *         CreateDate: 2017年9月7日
 */
@Service
public class FindPersonUngroupServiceImpl implements IJob {
	private static final Logger logger = LoggerFactory.getLogger(FindPersonUngroupServiceImpl.class);

	@Resource
	private IGuidMemberService guidMemberService;

	@Resource
	private IMessageInfoService messageInfoService;

	@Resource
	private IManagerMemberService managerMemberService;

	@Override
	public void runJob() {
		this.triggerFindPersonUngroupJob();
	}

	//XXX LEOPENG 没有配置调度，是否已经测试？只是忘记上线?后面需要调整为分页的形式？
	//废弃
	@Deprecated
	public synchronized void triggerFindPersonUngroupJob() throws TsfaServiceException {
		/*GuidMember query = new GuidMember();
		List<GuidMember> list = guidMemberService.findGuidMember(query);
		logger.debug("当前共有" + list.size() + "导购.");
		for (GuidMember guidMember : list) {
			query.setMemberNo(guidMember.getMemberNo());
			// 根据memberNo获取当前没有分组用户count
			int count = guidMemberService.findPersonUngroupCount(query);
			if (count > 0) {
				AddMessageInfo addMessageInfo = new AddMessageInfo();
				addMessageInfo.setMsgType(MsgType.WORK.toString()); // 工作消息
				addMessageInfo.setSendType(SendType.SINGLE.toString()); // 单人
				addMessageInfo.setMemberTypeSender(MemberType.GUID.toString()); // 导购发送
				// addMessageInfo.setCheckStatus(MsgCheckStatus..toString());// 待审
				addMessageInfo.setMerchantNo(guidMember.getMerchantNo());
				addMessageInfo.setContent("辛苦了，您今天还有" + count + "个客户木有分组，赶紧去完成吧......"); // 消息内容
				addMessageInfo.setTitle("未分组客户提醒"); // 消息标题
				
				ManagerMemberDto managerMemberDto = new ManagerMemberDto();
				managerMemberDto.setMobile(guidMember.getMobile());
				ManagerMemberReturnDto managerMemberReturn = managerMemberService.findManagerMemberByMobile(managerMemberDto);
				
				if (managerMemberReturn != null) { // 是店长
					addMessageInfo.setMemberType(MemberType.SHOP.toString()); // 店长发送
					addMessageInfo.setMemberNo(managerMemberReturn.getMemberNo()); // 接收人
				} else {
					addMessageInfo.setMemberNo(guidMember.getMemberNo()); // 接收人
					addMessageInfo.setMemberType(MemberType.GUID.toString()); // 导购发送
				}
				try {
					messageInfoService.addMessageInfo(addMessageInfo);
				} catch (TsfaServiceException e) {
					logger.error("消息发送失败：{}", addMessageInfo.toString());
				}
			}
		}*/
	}

}
