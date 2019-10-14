package com.lj.business.api.controller.msg;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.cm.emus.SendType;
import com.lj.business.member.emus.MemberType;
import com.lj.messagecenter.msg.dto.AddMessageInfo;
import com.lj.messagecenter.msg.dto.AddMessageInfoReturn;
import com.lj.messagecenter.msg.dto.FindMessageInfoPage;
import com.lj.messagecenter.msg.dto.FindMessageInfoPageReturn;
import com.lj.messagecenter.msg.dto.ReasonDto;
import com.lj.messagecenter.msg.dto.UpdateMessageInfo;
import com.lj.messagecenter.msg.dto.UpdateMessageInfoReturn;
import com.lj.messagecenter.msg.enums.MsgCheckStatus;
import com.lj.messagecenter.msg.enums.MsgType;
import com.lj.messagecenter.msg.service.IMessageInfoService;

/**
 * 
 * 
 * 类说明：消息信息处理action
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月3日
 */
@Controller
@RequestMapping(value="/msg")
public class MessageInfoAction extends Action {
	
	private static Logger logger = LoggerFactory.getLogger(MessageInfoAction.class);

	@Resource
	public IMessageInfoService messageInfoService;
	
	
	/**
	 * 
	 *
	 * 方法说明：最新工作消息
	 *个人中心-消息-首页
	 * @param mobile
	 * @param httpServletRequest
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年6月27日
	 *
	 */
	@RequestMapping(value="newMsg.do")
	@ResponseBody
	public List<FindMessageInfoPageReturn> newMsg(FindMessageInfoPage findMessageInfoPage) {
		return messageInfoService.newMsg(findMessageInfoPage);
	}
	
	/**
	 * 
	 *
	 * 方法说明：分页消息列表
	 *	消息管理-工作
	 *	消息管理-审批
	 *	消息管理-系统
	 * @param memberNo
	 * @param sendType
	 * @param limit
	 * @param start
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年6月27日
	 *
	 */
	@RequestMapping(value="listMsg.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindMessageInfoPageReturn> listMsg(FindMessageInfoPage findMessageInfoPage) {
		logger.debug("listMsg.do ------start");
		findMessageInfoPage.setSortDir(PageSortType.desc);
		Page<FindMessageInfoPageReturn> page= messageInfoService.listMsg(findMessageInfoPage);
		logger.debug("return:"+page.toString());
		 return page;
	}
	
	/**
	 * 
	 *
	 * 方法说明：TODO 决绝原因，后续读取配置
	 * @param personMemberLogin
	 * @param httpServletRequest
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年6月27日
	 *
	 */
	@RequestMapping(value="reasonList.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<ReasonDto> reasonList() {
		logger.debug("reasonList.do ------start");
		List<ReasonDto> list = new ArrayList<ReasonDto>();
		ReasonDto reasonDto = new ReasonDto();
		reasonDto.setValue("1");
		reasonDto.setLabel("还可以再跟进，加油吧！");
		list.add(reasonDto);
		reasonDto = new ReasonDto();
		reasonDto.setValue("2");
		reasonDto.setLabel("老客户不能放弃");
		list.add(reasonDto);
		reasonDto = new ReasonDto();
		reasonDto.setValue("3");
		reasonDto.setLabel(" 有成单迹象");
		list.add(reasonDto);
		logger.debug("return:"+list.toString());
		return list;
	}
	
	/**
	 * 
	 *
	 * 方法说明：审批操作
	 * checkStatus（PENDING：待审，PASS：通过，REFUSE：拒绝）
	 *
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年6月27日
	 *
	 */
	@RequestMapping(value="check.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse  check(UpdateMessageInfo updateMessageInfo) {
		logger.debug("reasonList.do ------start");
		UpdateMessageInfoReturn updateMessageInfoReturn=messageInfoService.check(updateMessageInfo);
		logger.debug("return:"+updateMessageInfoReturn.toString());
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 *
	 * 方法说明：添加审核消息
	 *
	 * @param addMessageInfo
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月3日
	 *	@deprecated   废弃-审核消息在新增放弃记录中添加
	 */
	@RequestMapping(value="addMsg.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public AddMessageInfoReturn addMsg(AddMessageInfo addMessageInfo) {
		logger.debug("reasonList.do ------start");
		addMessageInfo.setMsgType(MsgType.CHECK.toString());			//审核消息
		addMessageInfo.setSendType(SendType.SINGLE.toString());			//单人
		addMessageInfo.setMemberType(MemberType.SHOP.toString());		//接收人类型店长		
		addMessageInfo.setMemberTypeSender(MemberType.GUID.toString());	//导购发送
		addMessageInfo.setCheckStatus(MsgCheckStatus.PENDING.toString());//待审
		addMessageInfo.setMsgType(MsgType.CHECK.toString());			//消息类型
		AddMessageInfoReturn infoReturn=messageInfoService.addMessageInfo(addMessageInfo);
		logger.debug("return:"+infoReturn.toString());
		return infoReturn;
	}
	
	/**
	 * 
	 *
	 * 方法说明：添加提醒消息
	 *
	 * @param addMessageInfo
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月10日
	 *
	 */
	@RequestMapping(value="addRemindMsg.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse addRemindMsg(Integer errorCount,String memberNoGm,String memberNoSp,String merchantNo) {
		logger.debug("addRemindMsg.do ------start");
		AddMessageInfoReturn infoReturn=messageInfoService.addRemindMsg(errorCount, memberNoGm, memberNoSp, merchantNo);
		logger.debug("return:"+infoReturn.toString());
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取未读消息数量
	 *
	 * @param memberNo
	 * @param merchantNo
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年10月9日
	 *
	 */
	@RequestMapping(value="getUnReadNum.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public int getUnReadNum(String memberNo,String merchantNo) {
		logger.debug("getUnReadNum.do ------start");
		FindMessageInfoPage findMessageInfoPage = new FindMessageInfoPage();
		findMessageInfoPage.setMerchantNo(merchantNo);
		findMessageInfoPage.setMemberNo(memberNo);
		findMessageInfoPage.setIsRead("isRead");
		Integer count=messageInfoService.findMessageInfoPageCount(findMessageInfoPage);
		logger.debug("getUnReadNum return:"+count);
		return count;
	}
	
	
}
