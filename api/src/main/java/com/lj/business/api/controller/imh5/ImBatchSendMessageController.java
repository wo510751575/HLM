package com.lj.business.api.controller.imh5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.StringUtils;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaException;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.service.IBatchSendMessageService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.weixin.dto.FindImGroupChatInfoPage;
import com.lj.business.weixin.dto.FindImGroupChatJobPage;
import com.lj.business.weixin.dto.ImGroupChatInfoDto;
import com.lj.business.weixin.dto.ImGroupChatJobDto;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.ChatRoomType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImGroupChatInfoService;
import com.lj.business.weixin.service.IImGroupChatJobService;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.business.weixin.service.IWxSmallProgramService;
/**
 * 群发消息
 * @author zlh
 *
 */
@Controller
@RequestMapping(value = "/imh5/batchSendMessage")
public class ImBatchSendMessageController extends Action{
    
	@Resource
	private IBatchSendMessageService batchSendMessageService;
	
	@Resource
	private IImChatInfoService imChatInfoService;
	
	@Resource
	private IPersonMemberService personMemberService;
	
	@Resource
	private IImGroupChatInfoService iImGroupChatInfoService;
	
	@Resource
	private IImGroupChatJobService iImGroupChatJobService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private IWxPublicAccountService wxPublicAccountService;
	
	@Resource
	private ICouponRuleService couponRuleService;
	
	@Autowired
	private IWxSmallProgramService wxSmallProgramService;
	
	@RequestMapping(value = { "/getBatchSendRecordList.do" })
	@ResponseBody
	public Page<ImGroupChatInfoDto> listJson(String content,Integer pageNo,Integer pageSize,String merchantNo ) {
		AssertUtils.notAllNullAndEmpty(merchantNo, "商户号不能为空");
		try {
			FindImGroupChatInfoPage findImGroupChatInfoPage =new FindImGroupChatInfoPage();
			if (pageNo != null) {
				findImGroupChatInfoPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findImGroupChatInfoPage.setLimit(pageSize);
			}
			ImGroupChatInfoDto param = new ImGroupChatInfoDto();
			param.setMerchantNo(merchantNo);
			if(StringUtils.isNotBlank(content)){
				param.setContent(content);
			}
			findImGroupChatInfoPage.setParam(param);
			Page<ImGroupChatInfoDto> pages = iImGroupChatInfoService.findImGroupChatInfoPage(findImGroupChatInfoPage);
			return pages;
		} catch (Exception e) {
			logger.error("查询群发设置失败！", e);
			return null;
		}
	}
	

	/**
	 * 新增群发设置
	 * 
	 * @param imGroupChatInfoDto
	 * @return
	 */
	@RequestMapping(value = "save.do")
	@ResponseBody
	public GeneralResponse save(ImGroupChatInfoDto imGroupChatInfoDto,String paramJson) {
		AssertUtils.notAllNullAndEmpty(imGroupChatInfoDto.getMerchantNo(), "商户号不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatInfoDto.getMerchantName(), "商户名称不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatInfoDto.getType(), "消息类型不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatInfoDto.getMemberNos(), "会员编号不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatInfoDto.getMemberNames(), "会员名称不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatInfoDto.getMemberNoWxs(), "会员微信不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatInfoDto.getNoWxGm(), "终端微信不能为空");
		try {
			
			if(imGroupChatInfoDto.getType().equals(ChatInfoType.IMG.getCode())){
				if(StringUtils.isEmpty(imGroupChatInfoDto.getResourcesPath())){
					return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR, "没上传图片");
				}
			}
			
			if(imGroupChatInfoDto.getType().equals(ChatInfoType.TEXT.getCode())){
				if(StringUtils.isEmpty(imGroupChatInfoDto.getContent())){
					return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR, "文本缺少内容");
				}
			}
			

//			imGroupChatInfoDto.setChatAssistantCode(UserUtils.getUser().getId()); // 导购助手编号
			
			FindPersonMember findPersonMember = new FindPersonMember();
			findPersonMember.setMerchantNo(imGroupChatInfoDto.getMerchantNo());
			findPersonMember.setMemberNos(imGroupChatInfoDto.getMemberNos());
			findPersonMember.setShopWx(imGroupChatInfoDto.getNoWxGm());
			List<FindPersonMemberReturn> list = personMemberService.findPersonMemberByMemberNoAndMerchantNo(findPersonMember);
			Map<String,FindPersonMemberReturn> map = new HashMap<>();
			if(null != list && list.size()>0){
				for (FindPersonMemberReturn findPersonMemberReturn : list) {
					map.put(findPersonMemberReturn.getMemberNo(), findPersonMemberReturn);
				}
				
			}
			
			String[] memberNosArray = imGroupChatInfoDto.getMemberNos().split(",");
			StringBuilder memberNoGm = new StringBuilder("");
			StringBuilder memberNameGm = new StringBuilder("");
			for (String string : memberNosArray) {
				memberNoGm = memberNoGm.append(map.get(string).getMemberNoGm()).append(",");
				memberNameGm = memberNameGm.append(map.get(string).getMemberNameGm()).append(",");
			}
			if(memberNoGm.length()>0){
				memberNoGm.deleteCharAt(memberNoGm.length()-1);
				memberNameGm.deleteCharAt(memberNameGm.length()-1);
			}
			imGroupChatInfoDto.setMemberNoGm(memberNoGm.toString());
			imGroupChatInfoDto.setMemberNameGm(memberNameGm.toString());
			//发图片（多张，分开发）
			if(imGroupChatInfoDto.getResourcesPath() !=null && !imGroupChatInfoDto.getResourcesPath().equals("")) {
			   String imagePathArray[] = imGroupChatInfoDto.getResourcesPath().split(",");
			   for(String s : imagePathArray) {
				   if(s != null && !s.equals("")) {
					   imGroupChatInfoDto.setResourcesPath(s);
					   String code =iImGroupChatInfoService.addImGroupChatInfo(imGroupChatInfoDto);
					   sendGroupChatInfo(code);
					    
				   }
			   }
			   return GeneralResponse.generateSuccessResponse();
			}
			//发文字
			String code =iImGroupChatInfoService.addImGroupChatInfo(imGroupChatInfoDto);
			sendGroupChatInfo(code);
			return GeneralResponse.generateSuccessResponse(); 
		} catch (TsfaException e) {
			logger.error("新增群发设置失败 {}", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}
	
	
	@RequestMapping(value = { "/batchSendJobList.do" })
	@ResponseBody
	public GeneralResponse batchSendJobList(FindImGroupChatJobPage page,Integer pageNo,Integer pageSize,
			String merchantNo,String status,String repetition,String content ){
		AssertUtils.notAllNullAndEmpty(merchantNo, "商户号不能为空");
		if (pageNo != null) {
			page.setStart((pageNo - 1) * pageSize);
		}
		if (pageSize != null) {
			page.setLimit(pageSize);
		}
		ImGroupChatJobDto param = new ImGroupChatJobDto();
		param.setMerchantNo(merchantNo);
		if(StringUtils.isNotBlank(content)){
			param.setContent(content);
		}
		if(StringUtils.isNotBlank(repetition)){
			param.setRepetition(repetition);
		}
		if(StringUtils.isNotBlank(status)){
			param.setStatus(status);
		}
		page.setParam(param);
		Page<ImGroupChatJobDto> pages = iImGroupChatJobService.findImGroupChatJobPage(page);
		
		return GeneralResponse.generateSuccessResponse(pages);
	}
	
	/**
	 * 
	 * @Title: addBatchSendJob   
	 * @Description: TODO(新增群发任务)   
	 * @param: @return      
	 * @return: GeneralResponse      
	 * @throws
	 */
	@RequestMapping(value = "addBatchSendJob.do")
	@ResponseBody
	public GeneralResponse addBatchSendJob(ImGroupChatJobDto imGroupChatJobDto){
		AssertUtils.notAllNullAndEmpty(imGroupChatJobDto.getMerchantNo(), "商户号不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatJobDto.getMerchantName(), "商户名称不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatJobDto.getType(), "消息类型不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatJobDto.getMemberNos(), "会员编号不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatJobDto.getMemberNames(), "会员名称不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatJobDto.getMemberNoWxs(), "会员微信不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatJobDto.getNoWxGm(), "终端微信不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatJobDto.getRepetition(), "重复周期不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatJobDto.getSendDate().toString(), "发送时间不能为空");
		AssertUtils.notAllNullAndEmpty(imGroupChatJobDto.getSourceCode(), "资源code不能为空");
		try {
			//发送公众号,小程序,优惠券时把名称存到content
			if(StringUtils.isNotEmpty(imGroupChatJobDto.getSourceCode())&&StringUtils.isNotEmpty(imGroupChatJobDto.getType())){
				switch (imGroupChatJobDto.getType()) {
				case "42":
					//设置公众号名称
					FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
					findWxPublicAccount.setCode(imGroupChatJobDto.getSourceCode());
					FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountService.findWxPublicAccount(findWxPublicAccount);
					imGroupChatJobDto.setContent(findWxPublicAccountReturn.getPaName());
					break;
					
				case "491":
					FindCouponRule findCouponRule = new FindCouponRule();
					findCouponRule.setCode(imGroupChatJobDto.getSourceCode());
					FindCouponRuleReturn findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
					imGroupChatJobDto.setContent(findCouponRuleReturn.getCouponName());
					break;
					
				case "494":
					FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
					findWxSmallProgram.setCode(imGroupChatJobDto.getSourceCode());
					FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramService.findWxSmallProgram(findWxSmallProgram);
					imGroupChatJobDto.setContent(findWxSmallProgramReturn.getSpName());
					break;

				default:
					break;
				}
			}
			
			FindPersonMember findPersonMember = new FindPersonMember();
			findPersonMember.setMerchantNo(imGroupChatJobDto.getMerchantNo());
			findPersonMember.setMemberNos(imGroupChatJobDto.getMemberNos());
			findPersonMember.setShopWx(imGroupChatJobDto.getNoWxGm());
			List<FindPersonMemberReturn> list = personMemberService.findPersonMemberByMemberNoAndMerchantNo(findPersonMember);
			Map<String,FindPersonMemberReturn> map = new HashMap<>();
			if(null != list && list.size()>0){
				for (FindPersonMemberReturn findPersonMemberReturn : list) {
					map.put(findPersonMemberReturn.getMemberNo(), findPersonMemberReturn);
				}
				
			}
			
			String[] memberNosArray = imGroupChatJobDto.getMemberNos().split(",");
			StringBuilder memberNoGm = new StringBuilder("");
			StringBuilder memberNameGm = new StringBuilder("");
			for (String string : memberNosArray) {
				memberNoGm = memberNoGm.append(map.get(string).getMemberNoGm()).append(",");
				memberNameGm = memberNameGm.append(map.get(string).getMemberNameGm()).append(",");
			}
			if(memberNoGm.length()>0){
				memberNoGm.deleteCharAt(memberNoGm.length()-1);
				memberNameGm.deleteCharAt(memberNameGm.length()-1);
			}
			imGroupChatJobDto.setMemberNoGm(memberNoGm.toString());
			imGroupChatJobDto.setMemberNameGm(memberNameGm.toString());
			//添加到群发任务表
			String code = iImGroupChatJobService.addImGroupChatJob(imGroupChatJobDto);
			//新增群发定时任务
			
			
			
		} catch (Exception e) {
			logger.error("新增群发任务错误", e);
			return GeneralResponse.generateFailureResponse();
		}
		return GeneralResponse.generateSuccessResponse(); 
		
	}
	

	/**
	 * 再发一条
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = { "sendChatMessage.do" })
	@ResponseBody
	public GeneralResponse sendChatMessage(Model model,String code) {
		
		if (StringUtils.isEmpty(code)) {
			return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR,"参数错误！");
		}

		try {
			 sendGroupChatInfo(code);
			 return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	private void sendGroupChatInfo(String code) {
		ImGroupChatInfoDto imGroupChatInfoDto = new ImGroupChatInfoDto();
		imGroupChatInfoDto.setCode(code);
		ImGroupChatInfoDto groupChatInfoDto = iImGroupChatInfoService.findImGroupChatInfo(imGroupChatInfoDto);
		AssertUtils.notNull(groupChatInfoDto,"群发信息不存在！");

		String[] memberNos = groupChatInfoDto.getMemberNos().split(",");
		String[] memberNoGms = groupChatInfoDto.getMemberNoGm().split(",");
		for (int i = 0; i < memberNos.length; i++) {
			
			String str = memberNos[i];
			String memberNoGm = memberNoGms[i];
			logger.info("循环群发消息客户：{}，导购：{}",str,memberNoGm);
			taskExecutor.execute(new Runnable() { // 通过线程池发送
				@Override
				public void run() {
					logger.info("向客户{}发送群发消息", str);
					/**
					 * 线程沉睡5秒
					 */
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						logger.error("沉睡当前线程失败", e);
					}

					SendImChatInfo sendImChatInfo = new SendImChatInfo();
					sendImChatInfo.setType(groupChatInfoDto.getType());
					sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
					sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
					sendImChatInfo.setChatroomType(ChatRoomType.PERSONAL.getCode());
					sendImChatInfo.setMemberNo(str);
					sendImChatInfo.setMemberNoGm(memberNoGm);
					sendImChatInfo.setNoWxGm(groupChatInfoDto.getNoWxGm());
					switch (sendImChatInfo.getType()) {
					case "1": // 文本 文本消息内容不转义
						sendImChatInfo.setContent(StringEscapeUtils.unescapeHtml4(groupChatInfoDto.getContent()).toString());
						imChatInfoService.sendChat(sendImChatInfo);
						break;
					default: // 其他：语音、图片等
						sendImChatInfo.setResources(groupChatInfoDto.getResourcesPath());
						imChatInfoService.sendChat(sendImChatInfo);
						break;
					}

				}
			});
		}
	}

}
