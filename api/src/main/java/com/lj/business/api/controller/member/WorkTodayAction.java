package com.lj.business.api.controller.member;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.dto.FindNewPmTotalReturn;
import com.lj.business.api.dto.FindWorkTodayInfo;
import com.lj.business.api.dto.FindWorkTodayInfoReturn;
import com.lj.business.api.dto.FindWorkTodayInfoShop;
import com.lj.business.api.dto.FindWorkTodayInfoShopReturn;
import com.lj.business.cm.dto.AddGreetClient;
import com.lj.business.cm.dto.FindGreetClientForDayDto;
import com.lj.business.cm.dto.FindGreetClientReturnDto;
import com.lj.business.cm.dto.activity.FindActivityPage;
import com.lj.business.cm.dto.activity.FindActivityPageReturn;
import com.lj.business.cm.dto.activity.UpdateActivity;
import com.lj.business.cm.dto.activity.UpdateActivityForReadDto;
import com.lj.business.cm.dto.textInfo.FindTextInfo;
import com.lj.business.cm.dto.textInfo.FindTextInfoPageReturn;
import com.lj.business.cm.service.IActivityService;
import com.lj.business.cm.service.IGreetClientService;
import com.lj.business.cm.service.ITextInfoService;
import com.lj.business.member.dto.FindGroupedUnContactMemberReturn;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindNewPmCountDto;
import com.lj.business.member.dto.FindNewPmPage;
import com.lj.business.member.dto.FindNewPmPageReturn;
import com.lj.business.member.dto.FindTodayManageShop;
import com.lj.business.member.dto.FindTodayManageShopReturn;
import com.lj.business.member.dto.FindUnContactMember;
import com.lj.business.member.dto.FindUnContactMemberReturn;
import com.lj.business.member.dto.UpdateFirstIntroduce;
import com.lj.business.member.dto.UpdatePersonMember;
import com.lj.business.member.dto.guidMemberIntegral.GuidMemberIntegralDto;
import com.lj.business.member.dto.guidMemberIntegralDay.FindGuidMemberIntegralDay;
import com.lj.business.member.dto.guidMemberIntegralDay.FindGuidMemberIntegralDayReturn;
import com.lj.business.member.emus.IntegralType;
import com.lj.business.member.service.IGuidMemberIntegralDayService;
import com.lj.business.member.service.IGuidMemberIntegralService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.messagecenter.msg.dto.FindMessageInfoPage;
import com.lj.messagecenter.msg.enums.MsgCheckStatus;
import com.lj.messagecenter.msg.enums.MsgType;
import com.lj.messagecenter.msg.service.IMessageInfoService;

/**
 * 
 * 
 * 类说明：今日工作action
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
@Controller
@RequestMapping(value="/work/")
public class WorkTodayAction extends Action {

	private static Logger logger = LoggerFactory.getLogger(WorkTodayAction.class);

	@Resource
	public IPersonMemberService personMemberService;


	@Resource
	private IGreetClientService greetClientService;

	@Resource
	private IActivityService activityService;
	
	@Resource
	private IMessageInfoService messageInfoService;
	
	@Resource
	private IGuidMemberIntegralDayService guidMemberIntegralDayService;
	
	@Resource
	private ITextInfoService textInfoService;
	
	@Resource
	private IGuidMemberService guidMemberService;
	
	@Resource
	private IGuidMemberIntegralService guidMemberIntegralService;
	
	/**
	 * 
	 *
	 * 方法说明：获取导购今日工作首页详情
	 *
	 * @param findWorkTodayInfo
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年6月27日
	 *
	 */
	@RequestMapping(value="todayGm.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public FindWorkTodayInfoReturn todayGm(FindWorkTodayInfo findWorkTodayInfo) {
		logger.debug("todayGm(FindWorkTodayInfo findWorkTodayInfo={}) - start", findWorkTodayInfo); 
		String memberNoGm = findWorkTodayInfo.getMemberNoGm();
		AssertUtils.notNull(findWorkTodayInfo.getMemberNoGm(),"导购编号不能为空");
		String merchantNo = findWorkTodayInfo.getMerchantNo();
		AssertUtils.notNull(merchantNo,"商户号不能为空");
		FindWorkTodayInfoReturn findWorkTodayInfoReturn = new FindWorkTodayInfoReturn();

		Date workDate =  org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);

		FindGuidMemberIntegralDay findGuidMemberIntegralDay = new FindGuidMemberIntegralDay();
		findGuidMemberIntegralDay.setMemberNo(memberNoGm);
		findGuidMemberIntegralDay.setDaySt(workDate);
		List<FindGuidMemberIntegralDayReturn> list = guidMemberIntegralDayService.findByGMDayList(findGuidMemberIntegralDay);
		if(list != null && list.size() > 0){
			Double total = 0.0;
			for(FindGuidMemberIntegralDayReturn findGuidMemberIntegralDayReturn : list){
				total = total + findGuidMemberIntegralDayReturn.getIntegralScore();
			}
			
			BigDecimal big = new BigDecimal(total);
			findWorkTodayInfoReturn.setRatioWork(big.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		
		
		FindTextInfo findTextInfo=new FindTextInfo();
		findTextInfo.setTextType("GM_WORK_ANALYZE");
		findTextInfo.setCount(findWorkTodayInfoReturn.getRatioWork() == null ? 0 : findWorkTodayInfoReturn.getRatioWork().intValue());
		findTextInfo.setMerchantNo(merchantNo);
		FindTextInfoPageReturn socialAnalyzeContent = textInfoService.findTextInfoReturnContent(findTextInfo);
		String content = socialAnalyzeContent == null ? "" : socialAnalyzeContent.getContent();
		findWorkTodayInfoReturn.setRemarkIndex(content);
		
		logger.debug("todayGm(FindWorkTodayInfo) - end - return value={}", findWorkTodayInfoReturn); 
		return findWorkTodayInfoReturn;
	}



	/**
	 * 
	 *
	 * 方法说明：获取店长今日工作首页详情，含：我的工作，待办事项，客户提醒
	 *
	 * @param findWorkTodayInfo
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年6月27日
	 *
	 */
	@RequestMapping(value="todayShop.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public FindWorkTodayInfoShopReturn todayShop(FindWorkTodayInfoShop findWorkTodayInfoShop) {
		logger.debug("todayShop(FindWorkTodayInfoShop findWorkTodayInfoShop={}) - start", findWorkTodayInfoShop); 

		String memberNoGm = findWorkTodayInfoShop.getMemberNoGm();
		AssertUtils.notNull(memberNoGm,"导购编号不能为空");
		String memberNoShop = findWorkTodayInfoShop.getMemberNoShop();
		AssertUtils.notNull(memberNoShop,"店长编号不能为空");
		String merchantNo = findWorkTodayInfoShop.getMerchantNo();
		AssertUtils.notNull(merchantNo,"商户编号不能为空");

		FindWorkTodayInfoShopReturn findWorkTodayInfoShopReturn = new FindWorkTodayInfoShopReturn();
		Date workDate =  org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		
		FindGuidMemberIntegralDay findGuidMemberIntegralDay = new FindGuidMemberIntegralDay();
		findGuidMemberIntegralDay.setMemberNo(memberNoGm);
		findGuidMemberIntegralDay.setDaySt(workDate);
		List<FindGuidMemberIntegralDayReturn> list = guidMemberIntegralDayService.findByGMDayList(findGuidMemberIntegralDay);
		if(list != null && list.size() > 0){
			Double total = 0.0;
			for(FindGuidMemberIntegralDayReturn findGuidMemberIntegralDayReturn : list){
				total = total + findGuidMemberIntegralDayReturn.getIntegralScore();
			}
			BigDecimal big = new BigDecimal(total);
			findWorkTodayInfoShopReturn.setRatioWork(big.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		
		FindTextInfo findTextInfo=new FindTextInfo();
		findTextInfo.setTextType("SHOP_WORK_ANALYZE");
		findTextInfo.setCount(findWorkTodayInfoShopReturn.getRatioWork() == null ? 0 : findWorkTodayInfoShopReturn.getRatioWork().intValue());
		findTextInfo.setMerchantNo(merchantNo);
		FindTextInfoPageReturn socialAnalyzeContent = textInfoService.findTextInfoReturnContent(findTextInfo);
		String content = socialAnalyzeContent == null ? "" : socialAnalyzeContent.getContent();
		findWorkTodayInfoShopReturn.setRemarkIndex(content);
		


		//待办事项-审批
		FindMessageInfoPage findMessageInfoPage = new FindMessageInfoPage();
		findMessageInfoPage.setMemberNo(memberNoShop);
		findMessageInfoPage.setMerchantNo(merchantNo);
		findMessageInfoPage.setCheckStatus(MsgCheckStatus.PENDING.toString());
		findMessageInfoPage.setMsgType(MsgType.CHECK.toString());
		int todoNum = messageInfoService.findMessageInfoPageCount(findMessageInfoPage);
		
		logger.debug("todayShop(FindWorkTodayInfoShop) - end - return value={}", findWorkTodayInfoShopReturn); 
		return findWorkTodayInfoShopReturn;
	}

	/**
	 * 
	 *
	 * 方法说明：管理工作
	 *
	 * @param findTodayManageShop
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年10月9日
	 *
	 */
	@RequestMapping(value="todayManageShopNew.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<FindTodayManageShopReturn> todayManageShopNew(FindTodayManageShop findTodayManageShop) {
		logger.debug("todayManageShopNew(FindTodayManageShop findTodayManageShop={}) - start", findTodayManageShop); 

		Date workDate =  org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		findTodayManageShop.setDaySt(workDate);
		List<FindTodayManageShopReturn> list = personMemberService.todayManageShopNew(findTodayManageShop);

		logger.debug("todayManageShopNew() - end - return value={}", list); 
		return list;

	}

	/**
	 * 
	 *
	 * 方法说明：今日工作-当日新增客户统计信息查询_APP
	 *
	 * @param findComTaskChooseIndex
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月17日
	 *
	 */
	@RequestMapping(value="findNewPmTotal.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public FindNewPmTotalReturn findNewPmTotal(FindNewPmPage findNewPmPage) {
		FindNewPmTotalReturn findNewPmTotalReturn = new FindNewPmTotalReturn();
		Page<FindNewPmPageReturn> page = personMemberService.findNewPmPage(findNewPmPage);
		//findNewPmPage.setLimit(ApiConstans.LIMIT);
		findNewPmTotalReturn.setDetail(page.getRows());
		int total = (int) page.getTotal();
		findNewPmTotalReturn.setNumFinish(total);
		
		//计算月初到今天添加的总人
		Date monthFirstDate = DateUtils.getMonthFirstDay();//月初
		Date now = new Date();//今天
	
		int monthTotal = 0;
		
		FindNewPmCountDto findNewPmCountDto = new FindNewPmCountDto();
		findNewPmCountDto.setMemberNoGm(findNewPmPage.getMemberNoGm());
		findNewPmCountDto.setMerchantNo(findNewPmPage.getMerchantNo());
		findNewPmCountDto.setBeginDate(monthFirstDate);
		findNewPmCountDto.setEndDate(now);
		monthTotal =  personMemberService.findNewPmCount(findNewPmCountDto);
		
		//int numDis = monthNeedAdd - monthTotal;
		findNewPmTotalReturn.setNumDis(monthTotal);
		findNewPmTotalReturn.setTotal(page.getTotal());
		findNewPmTotalReturn.setLimit(page.getLimit());
		return findNewPmTotalReturn;
	}


	/**
	 * 
	 *
	 * 方法说明：今日工作-当日新增客户查询_APP
	 *
	 * @param findComTaskChooseIndex
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月17日
	 * 
	 */
	@RequestMapping(value="findNewPmPage.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindNewPmPageReturn> findNewPmPage(FindNewPmPage findNewPmPage) {
		//findNewPmPage.setLimit(ApiConstans.LIMIT);
		return personMemberService.findNewPmPage(findNewPmPage);
	}

	/**
	 * 
	 *
	 * 方法说明：今日工作-初次介绍状态变更
	 *
	 * @param updateFirstIntroduce
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年8月3日
	 *
	 */
	@RequestMapping(value="updateFirstIntroduce.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse updateFirstIntroduce(UpdateFirstIntroduce updateFirstIntroduce) {
		UpdatePersonMember updatePersonMember = new UpdatePersonMember();
		updatePersonMember.setMemberNo(updateFirstIntroduce.getMemberNo());
		updatePersonMember.setMerchantNo(updateFirstIntroduce.getMerchantNo());
		updatePersonMember.setMemberNoGm(updateFirstIntroduce.getMemberNoGm());
		updatePersonMember.setFirstIntroduce(updateFirstIntroduce.getFirstIntroduce());
		personMemberService.updatePersonMemberByMGM(updatePersonMember );
		
		return GeneralResponse.generateSuccessResponse();
	}


	/**
	 * 
	 *
	 * 方法说明：查找当日问候信息(个人中心)
	 *
	 * @param findGreetClientDto
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年7月20日
	 *
	 */
	@RequestMapping(value="findGreetClientForDay.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public FindGreetClientReturnDto findGreetClientForDay(FindGreetClientForDayDto findGreetClientForDayDto) {
		logger.debug("findGreetClientDto(findGreetClientDto findGreetClientDto={}) - start", findGreetClientForDayDto);
		AssertUtils.notNull(findGreetClientForDayDto);
		FindGreetClientReturnDto findGreetClientForDay = greetClientService.findGreetClientForDay(findGreetClientForDayDto);
		logger.debug("findGreetClientDto() - end - return value={}", findGreetClientForDay); 
		return findGreetClientForDay;
	}



	/**
	 *
	 *
	 * 方法说明：分页查询未联系的客户
	 *
	 * @param findUnContactMember
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月22日
	 *
	 */
	@RequestMapping(value = "findUnContactMember.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindUnContactMemberReturn> findUnContactMember(FindUnContactMember findUnContactMember) {
		//findUnContactMember.setLimit(ApiConstans.LIMIT);
		return personMemberService.findUnContactMemberPage(findUnContactMember);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询分组过的未联系客户
	 *
	 * @param findUnContactMember
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2017年12月6日
	 *
	 */
	@RequestMapping(value = "findGroupedUnContactMember.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public FindGroupedUnContactMemberReturn findGroupedUnContactMember(FindUnContactMember findUnContactMember) {
        FindGroupedUnContactMemberReturn findGroupedUnContactMember = personMemberService.findGroupedUnContactMember(findUnContactMember);
        return findGroupedUnContactMember;
    }

	/**
	 * 
	 *
	 * 方法说明：添加客户问候消息
	 *
	 * @param addGreetClient
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年7月24日
	 *
	 */
	@RequestMapping(value="addGreetClient.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse addGreetClient(AddGreetClient addGreetClient) throws TsfaServiceException {
		logger.debug("addGreetClient(AddGreetClient addGreetClient={}) - start", addGreetClient); 
		AssertUtils.notNull(addGreetClient);
		greetClientService.addGreetClient(addGreetClient);
		logger.debug("addGreetClient() - end - return value={}", addGreetClient); 
		return GeneralResponse.generateSuccessResponse();
	}

	
	/**
	 * 
	 *
	 * 方法说明：查看所有历史活动列表
	 *
	 * @param findActivityDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年7月24日
	 *
	 */
	@RequestMapping(value="findActivitys.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<FindActivityPageReturn> findActivitys(String merchantNo) throws TsfaServiceException {
		logger.debug("findActivitys(String merchantNo={}) - start", merchantNo); 
		AssertUtils.notNull(merchantNo);
		FindActivityPage findActivityPage = new FindActivityPage();
		findActivityPage.setMerchantNo(merchantNo);
		List<FindActivityPageReturn> activitys = activityService.findActivitys(findActivityPage);
		logger.debug("findActivitys() - end - return value={}", activitys); 
		return activitys;
	}

	/**
	 * 
	 *
	 * 方法说明：增加点击量
	 *
	 * @param updateActivityForReadDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年7月25日
	 * @deprecated
	 */
	@RequestMapping(value="updateActivityForRead.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse updateActivityForRead(UpdateActivityForReadDto updateActivityForReadDto) throws TsfaServiceException {
		logger.debug("updateActivityForReadDto(updateActivityForReadDto updateActivityForReadDto={}) - start", updateActivityForReadDto); 
		AssertUtils.notNull(updateActivityForReadDto);
		activityService.updateActivityForRead(updateActivityForReadDto);
		logger.debug("updateActivityForReadDto() - end - return value={}", updateActivityForReadDto); 
		return GeneralResponse.generateSuccessResponse();

	}
	

	/**
	 * 
	 *
	 * 方法说明：分享活动积分
	 *
	 * @param guidMemberIntegralDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 刘培 CreateDate: 2017年8月16日
	 *
	 */
	@RequestMapping(value="activeIntegral.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse activeIntegral(GuidMemberIntegralDto guidMemberIntegralDto) throws TsfaServiceException {
		logger.debug("activeIntegral(GuidMemberIntegralDto guidMemberIntegralDto={}) - start", guidMemberIntegralDto); 
		AssertUtils.notNull(guidMemberIntegralDto);
		AssertUtils.notNull(guidMemberIntegralDto.getMerchantNo(),"商户号不能为空");
//		AssertUtils.notNull(guidMemberIntegralDto.getShopNo(),"店长编号不能为空");
		AssertUtils.notNull(guidMemberIntegralDto.getMemberNo(), "导购编号不能为空");
		
		// 添加积分
		FindGuidMember findGuidMember = new FindGuidMember();
		findGuidMember.setMemberNo(guidMemberIntegralDto.getMemberNo());
		FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
		if(findGuidMemberReturn != null){
			guidMemberIntegralDto.setAreaCode(findGuidMemberReturn.getAreaCode());
		}
		guidMemberIntegralDto.setIntegralType(IntegralType.ACTIVE.toString());
		guidMemberIntegralDto.setAmount(1d);
		guidMemberIntegralService.doIntegral(guidMemberIntegralDto);
		
		logger.debug("activeIntegral() - end - return value={}", guidMemberIntegralDto); 
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送素材积分
	 *
	 * @param guidMemberIntegralDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 刘培 CreateDate: 2017年8月18日
	 *
	 */
	@RequestMapping(value="sendMetIntegral.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse sendMetIntegral(GuidMemberIntegralDto guidMemberIntegralDto) throws TsfaServiceException {
		logger.debug("sendMetIntegral(GuidMemberIntegralDto guidMemberIntegralDto={}) - start", guidMemberIntegralDto); 
		AssertUtils.notNull(guidMemberIntegralDto);
		AssertUtils.notNull(guidMemberIntegralDto.getMerchantNo(),"商户号不能为空");
//		AssertUtils.notNull(guidMemberIntegralDto.getShopNo(),"店长编号不能为空");
		AssertUtils.notNull(guidMemberIntegralDto.getMemberNo(), "导购编号不能为空");
		
		// 添加积分
		FindGuidMember findGuidMember = new FindGuidMember();
		findGuidMember.setMemberNo(guidMemberIntegralDto.getMemberNo());
		FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
		if(findGuidMemberReturn != null){
			guidMemberIntegralDto.setAreaCode(findGuidMemberReturn.getAreaCode());
		}
		guidMemberIntegralDto.setIntegralType(IntegralType.SEND_MET.toString());
		guidMemberIntegralDto.setAmount(1d);
		guidMemberIntegralService.doIntegral(guidMemberIntegralDto);
		
		logger.debug("sendMetIntegral() - end - return value={}", guidMemberIntegralDto); 
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 *
	 * 方法说明：增加活动分享量
	 *
	 * @param updateActivityForReadDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年8月15日
	 *
	 */
	@RequestMapping(value="addActivityForShare.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse addActivityForShare(String merchantNo, String code) throws TsfaServiceException {
		logger.debug("addActivityForShare(String mercharNo={}, String code={}) - start", merchantNo,code); 
		UpdateActivity updateActivity = new UpdateActivity();
		updateActivity.setMerchantNo(merchantNo);
		updateActivity.setCode(code);
		activityService.addActivityForShare(updateActivity);
		return GeneralResponse.generateSuccessResponse();

	}
	
}
