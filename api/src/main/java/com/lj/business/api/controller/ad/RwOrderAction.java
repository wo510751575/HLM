package com.lj.business.api.controller.ad;

import java.util.Calendar;
import java.util.Date;
import com.lj.business.api.common.ErrorCode;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.exception.TsfaWebException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.dto.FindMemberLoginInfoReturn;
import com.lj.business.member.dto.GuidMemberRwDto;
import com.lj.business.member.service.IGuidMemberRwService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMemberLoginInfoService;
import com.lj.distributelock.IDistributeLock;
import com.lj.distributelock.RedisLock;
import com.lj.messagecenter.emus.SendType;
import com.lj.messagecenter.msg.dto.AddNotifyInfo;
import com.lj.messagecenter.msg.service.INotifyService;
import com.ye.business.ad.dto.BeansOrderDto;
import com.ye.business.ad.dto.BillDto;
import com.ye.business.ad.dto.FindBeansOrderPage;
import com.ye.business.ad.dto.FindRwOrderPage;
import com.ye.business.ad.dto.RwOrderDto;
import com.ye.business.ad.dto.RwUserBeansDto;
import com.ye.business.ad.dto.ServerInfoDto;
import com.ye.business.ad.enums.OrderType;
import com.ye.business.ad.enums.Status;
import com.ye.business.ad.kit.OrderNoUtil;
import com.ye.business.ad.service.IBeansOrderService;
import com.ye.business.ad.service.IBillService;
import com.ye.business.ad.service.IRwOrderService;
import com.ye.business.ad.service.IRwUserBeansService;
import com.ye.business.ad.service.IServerInfoService;

/**
 * 
 * *类说明：服务
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月8日
 */
@RestController
@RequestMapping("/ad/rwOrder")
public class RwOrderAction extends Action {

	@Autowired
	private IRwOrderService rwOrderService;
	@Autowired
	private IGuidMemberService guidMemberService;
	@Autowired
	private IServerInfoService serverInfoService;
	@Autowired
	private IBeansOrderService beansOrderService;
	@Autowired
	private IBillService billService;
	@Resource 
	private IDistributeLock redisLock;
	@Resource 
	private IRwUserBeansService rwUserBeansService;
	@Resource 
	private IGuidMemberRwService guidMemberRwService;
	@Resource
	private INotifyService notifyService;
	@Resource
	private IMemberLoginInfoService memberLoginInfoService;

	/**
	 * 
	 * *方法说明：新增订单
	 *
	 * @param param
	 * @param token
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	@RequestMapping(value = "add.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse add(RwOrderDto param, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getMobile(), "手机号为空");
		AssertUtils.notNullAndEmpty(param.getServeCode(), "服务code为空");

		ServerInfoDto serverInfoDto = new ServerInfoDto();
		serverInfoDto.setCode(param.getServeCode());
		ServerInfoDto info = serverInfoService.findServerInfo(serverInfoDto);
		AssertUtils.notNullAndEmpty(info, "服务不存在");
		AssertUtils.isTrue(Status.use.toString().equals(info.getStatus()), "服务不存在");

		String userid = getLoginUserByToken(token);
		GuidMember guidMember = new GuidMember();
		guidMember.setMemberNo(userid);
		GuidMember gm = guidMemberService.findSingleGuidMember(guidMember);

		param.setMerchantNo(gm.getMerchantNo());
		param.setMerchantName(gm.getMerchantName());
		param.setLoginName(gm.getLoginName());
		param.setMemberNoGuid(gm.getMemberNo());
		param.setMemberNameGuid(gm.getMemberName());
		

		Date now = new Date();
		param.setCreateDate(now);
		param.setUpdateDate(now);
		param.setCreateId(userid);
		param.setUpdateId(userid);

		param.setAmount(info.getPrice()*100);
		param.setServeName(info.getServerName());

		param.setStatus(Status.WAIT.toString());
		param.setOrderType(OrderType.SERVER.toString());

		String code = rwOrderService.addRwOrder(param);

		return GeneralResponse.generateSuccessResponse(code);
	}

	/**
	 * 
	 * *方法说明：
	 *
	 * @param param
	 * @param findRwOrderPage
	 * @param token
	 * @return
	 * @throws TsfaWebException
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	@RequestMapping(value = "info.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse info(RwOrderDto param, String token) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "code为空");

		getLoginUserByToken(token);

		RwOrderDto rs = rwOrderService.findRwOrder(param);

		return GeneralResponse.generateSuccessResponse(rs);
	}

	/**
	 * 
	 * *方法说明：
	 *
	 * @param param
	 * @param findRwOrderPage
	 * @param token
	 * @return
	 * @throws TsfaWebException
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse list(RwOrderDto param, FindRwOrderPage findRwOrderPage, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);

		String userid = getLoginUserByToken(token);
		param.setMemberNoGuid(userid);

		findRwOrderPage.setParam(param);

		Page<RwOrderDto> page = rwOrderService.findRwOrderPage(findRwOrderPage);

		return GeneralResponse.generateSuccessResponse(page);
	}
	
	/**
	 * 财务审核列表
	 * @param param
	 * @param findRwOrderPage
	 * @param token
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "orderList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse orderList(RwOrderDto param, FindRwOrderPage findRwOrderPage, String token)throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);

		findRwOrderPage.setParam(param);
		
		findRwOrderPage.setSortBy("dateDesc");

		Page<RwOrderDto> page = rwOrderService.findRwOrderPage(findRwOrderPage);

		return GeneralResponse.generateSuccessResponse(page);
		
	}
	
	/**
	 * 充值
	 * @param param
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "recharge.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse recharge(BeansOrderDto beansOrderDto)throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(beansOrderDto);
		beansOrderDto.setCode(GUID.generateByUUID());
		//充值不用审核直接生成一个充值订单
		beansOrderDto.setStatus("PASS");
		beansOrderDto.setCredits(Integer.parseInt(beansOrderDto.getAmount()+""));
		beansOrderDto.setPayTime(new Date());
		beansOrderDto.setCreateDate(new Date());
		beansOrderService.addBeansOrder(beansOrderDto);
		//给用户充值(加锁)
		String lockName = RedisLock.LOCK_NAME_PREFIX + beansOrderDto.getMemberNoGuid();
		String lockValue = null;
		try {
			lockValue = redisLock.getLockNoWait(lockName, 10);
		} catch (Exception e) {
			logger.error("操作正在处理中...", e);
			return GeneralResponse.generateFailureResponse();
		}
		try {
			//更新总充值豆子,剩余可用豆子值
			RwUserBeansDto rwUserBeansDtoReturn = rwUserBeansService.findRwUserBeans(beansOrderDto.getMemberNoGuid());
			//首次充值
			if(null == rwUserBeansDtoReturn){
				RwUserBeansDto rwUserBeansDto = new RwUserBeansDto();
//				rwUserBeansDto.setCode(GUID.generateByUUID());
				rwUserBeansDto.setCode(beansOrderDto.getMemberNoGuid());
				//rwUserBeansDto.setBeansFreeze(beansFreeze);
				rwUserBeansDto.setBeansNormal(Integer.parseInt(beansOrderDto.getAmount()+""));
				rwUserBeansDto.setBeansSum(Integer.parseInt(beansOrderDto.getAmount()+""));
				rwUserBeansDto.setBeansUse(0);
				rwUserBeansDto.setChangeBeans(0);
				rwUserBeansDto.setCreateDate(new Date());
				//rwUserBeansDto.setCreateId(createId);
				//rwUserBeansDto.setCreateName(createName);
				rwUserBeansDto.setMemberName(beansOrderDto.getMemberNameGuid());
				rwUserBeansDto.setMemberNo(beansOrderDto.getMemberNoGuid());
				//rwUserBeansDto.setMemberNoList(memberNoList);
				rwUserBeansDto.setMerchantName(beansOrderDto.getMerchantName());
				rwUserBeansDto.setMerchantNo(beansOrderDto.getMerchantNo());
				rwUserBeansDto.setRemark(beansOrderDto.getRemark());
				rwUserBeansDto.setRemark2(beansOrderDto.getRemark2());
				rwUserBeansDto.setRemark3(beansOrderDto.getRemark3());
				//rwUserBeansDto.setRemark4(remark4);
				rwUserBeansDto.setShopName(beansOrderDto.getShopName());
				rwUserBeansDto.setShopNo(beansOrderDto.getShopNo());
				//rwUserBeansDto.setUpdateDate(updateDate);
				//rwUserBeansDto.setUpdateId(updateId);
				//rwUserBeansDto.setUpdateName(updateName);
				rwUserBeansService.addRwUserBeans(rwUserBeansDto);
				
				//通知APP更新积分
				RwUserBeansDto record = new RwUserBeansDto();
				record.setCode(rwUserBeansDto.getCode());
//				record.setChangeBeans(rwUserBeansDto.getBeansNormal());
//				record.setBeansSum(rwUserBeansDto.getBeansSum());
				rwUserBeansService.updateIncreaseAmountByPrimaryKey(record );
				
			}else{
//				rwUserBeansDtoReturn.setBeansSum(Integer.parseInt(rwUserBeansDtoReturn.getBeansSum()+beansOrderDto.getAmount()+""));
//				rwUserBeansDtoReturn.setBeansNormal(Integer.parseInt(rwUserBeansDtoReturn.getBeansNormal()+beansOrderDto.getAmount()+""));
				rwUserBeansDtoReturn.setUpdateDate(new Date());
				rwUserBeansService.updateRwUserBeans(rwUserBeansDtoReturn);
				//通知APP更新积分
				RwUserBeansDto record = new RwUserBeansDto();
				record.setCode(beansOrderDto.getMemberNoGuid());
				record.setChangeBeans(Integer.parseInt(beansOrderDto.getAmount()+""));
				record.setBeansSum(Integer.parseInt(beansOrderDto.getAmount()+""));
				rwUserBeansService.updateIncreaseAmountByPrimaryKey(record );
			}
			
			
		} catch (Exception e) {
			logger.error("操作失败", e);
			return GeneralResponse.generateFailureResponse();
		}finally {
			//操作完成,释放锁
			redisLock.releaseLock(lockName, lockValue);
		}
		
		
		//插入一条充值流水
		BillDto billDto = new BillDto();
		billDto.setCode(GUID.generateByUUID());
		billDto.setAmount(Integer.parseInt(beansOrderDto.getAmount().toString()));
		billDto.setCreateId(beansOrderDto.getCreateId());
		billDto.setCreateTime(new Date());
		billDto.setLoginName(beansOrderDto.getRemark2());
		billDto.setMemberName(beansOrderDto.getMemberNameGuid());
		billDto.setMemberNo(beansOrderDto.getMemberNoGuid());
		//billDto.setMerchantName(beansOrderDto.getMerchantName());
		billDto.setMerchantNo(beansOrderDto.getMerchantNo());
		billDto.setTradeNo(OrderNoUtil.getOrderNo());//设置流水号
		billDto.setTradeType("1");//充值
		billService.addBill(billDto);
		
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 充值列表
	 * @param page
	 * @param param
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "rechargePage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse rechargePage(FindBeansOrderPage findBeansOrderPage,BeansOrderDto param)throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		
		findBeansOrderPage.setSortBy("dateDesc");
		findBeansOrderPage.setParam(param);
		Page<BeansOrderDto> page = beansOrderService.findBeansOrderPage(findBeansOrderPage);
		
		return GeneralResponse.generateSuccessResponse(page);
		
	}
	
	/**
	 * 财务审核
	 * @param param
	 * @param findRwOrderPage
	 * @param token
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "update.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse update(RwOrderDto param)throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(),"审核Code不能为空");
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(),"会员ID不能为空");
		
		// 查询导购最后一次登录信息
		FindMemberLoginInfoReturn findMemberLoginInfoReturn = memberLoginInfoService.findLastMemberLoginInfo(param.getMemberNoGuid());
		
		//查询会员信息
		GuidMemberRwDto guidMemberRwDto = new GuidMemberRwDto();
		guidMemberRwDto.setCode(param.getMemberNoGuid());
		GuidMemberRwDto findGuidMemberRw = guidMemberRwService.findGuidMemberRw(guidMemberRwDto);
		//查询申请订单
		RwOrderDto rwOrderDto = new RwOrderDto();
		rwOrderDto.setCode(param.getCode());
		RwOrderDto findRwOrder = rwOrderService.findRwOrder(rwOrderDto );
		
		//userLevel:1.普通用户，2.推广用户，3.企业用户
		//服务CODE .1-推广会员;2-企业会员;3-提现
		//审核状态(WAIT:待审，PASS:通过，UNPASS：拒绝)
		if("PASS".equals(param.getStatus())){
			if("1".equals(findGuidMemberRw.getUserLevel())){
				if("1".equals(findRwOrder.getServeCode())){
					findGuidMemberRw.setUserLevel("2");
					Calendar ca = Calendar.getInstance();
					ca.add(Calendar.YEAR, 1);
					findGuidMemberRw.setOpenLevelDate(new Date());
					findGuidMemberRw.setEndLevelDate(ca.getTime());
					findGuidMemberRw.setUpdateTime(new Date());
					guidMemberRwService.updateGuidMemberRw(findGuidMemberRw);
					findGuidMemberRw.setRemark("开通推广会员成功!");
					//通知APP
					noticeApp(findMemberLoginInfoReturn, findGuidMemberRw);
					
				}else if("2".equals(findRwOrder.getServeCode())){
					findGuidMemberRw.setUserLevel("3");
					Calendar ca = Calendar.getInstance();
					ca.add(Calendar.YEAR, 1);
					findGuidMemberRw.setOpenLevelDate(new Date());
					findGuidMemberRw.setEndLevelDate(ca.getTime());
					findGuidMemberRw.setUpdateTime(new Date());
					guidMemberRwService.updateGuidMemberRw(findGuidMemberRw);
					findGuidMemberRw.setRemark("开通企业会员成功!");
					//通知APP
					noticeApp(findMemberLoginInfoReturn, findGuidMemberRw);
				}else{
					//提现逻辑(TODO)
				}
			}else if("2".equals(findGuidMemberRw.getUserLevel())){
				if("1".equals(findRwOrder.getServeCode())){
					if(findGuidMemberRw.getEndLevelDate().after(new Date())){
						Calendar ca = Calendar.getInstance();
						ca.setTime(findGuidMemberRw.getEndLevelDate());
						ca.add(Calendar.YEAR, 1);
						findGuidMemberRw.setEndLevelDate(ca.getTime());
						findGuidMemberRw.setRemark("续费推广会员成功!");
					}else{
						Calendar ca = Calendar.getInstance();
						ca.add(Calendar.YEAR, 1);
						findGuidMemberRw.setOpenLevelDate(new Date());
						findGuidMemberRw.setEndLevelDate(ca.getTime());
						findGuidMemberRw.setUpdateTime(new Date());
						findGuidMemberRw.setRemark("开通推广会员成功!");
					}
					guidMemberRwService.updateGuidMemberRw(findGuidMemberRw);
					//通知APP
					noticeApp(findMemberLoginInfoReturn, findGuidMemberRw);
				}else if("2".equals(findRwOrder.getServeCode())){
					Calendar ca = Calendar.getInstance();
					ca.add(Calendar.YEAR, 1);
					findGuidMemberRw.setOpenLevelDate(new Date());
					findGuidMemberRw.setEndLevelDate(ca.getTime());
					findGuidMemberRw.setUpdateTime(new Date());
					findGuidMemberRw.setUserLevel("3");
					findGuidMemberRw.setRemark("开通企业会员成功!");
					guidMemberRwService.updateGuidMemberRw(findGuidMemberRw);
					//通知APP
					noticeApp(findMemberLoginInfoReturn, findGuidMemberRw);
				}else{
					//提现逻辑(TODO)
				}
			}else{
				if("2".equals(findRwOrder.getServeCode())){
					if(findGuidMemberRw.getEndLevelDate().after(new Date())){
						Calendar ca = Calendar.getInstance();
						ca.setTime(findGuidMemberRw.getEndLevelDate());
						ca.add(Calendar.YEAR, 1);
						findGuidMemberRw.setEndLevelDate(ca.getTime());
						findGuidMemberRw.setRemark("续费企业会员成功!");
					}else{
						Calendar ca = Calendar.getInstance();
						ca.add(Calendar.YEAR, 1);
						findGuidMemberRw.setOpenLevelDate(new Date());
						findGuidMemberRw.setEndLevelDate(ca.getTime());
						findGuidMemberRw.setUpdateTime(new Date());
						findGuidMemberRw.setRemark("开通企业会员成功!");
					}
					guidMemberRwService.updateGuidMemberRw(findGuidMemberRw);
					//通知APP
					noticeApp(findMemberLoginInfoReturn, findGuidMemberRw);
				}
				//企业会员不允许降级开通推广会员
				if("1".equals(findRwOrder.getServeCode())){
					return GeneralResponse.generateFailureResponse(ErrorCode.ACCESS_VALID, "企业会员不允许降级开通推广会员");
				}
			}
		}
		
		
		
		param.setUpdateDate(new Date());
		rwOrderService.updateRwOrder(param);
		
		return GeneralResponse.generateSuccessResponse();
		
	}

	public void noticeApp(FindMemberLoginInfoReturn findMemberLoginInfoReturn, GuidMemberRwDto findGuidMemberRw) {
		AddNotifyInfo addNotifyInfo = new AddNotifyInfo();
		addNotifyInfo.setMerchantNo(findGuidMemberRw.getMerchantNo());
		addNotifyInfo.setMemberNo(findGuidMemberRw.getCode());
		addNotifyInfo.setMemberName(findGuidMemberRw.getMemberName());
		addNotifyInfo.setMemberType("GUID");
		addNotifyInfo.setSendType(SendType.SINGLE.name());
		addNotifyInfo.setSysType("IOS");
		addNotifyInfo.setMobile(findMemberLoginInfoReturn.getMac());
		//1.普通用户，2.推广用户，3.企业用户
		addNotifyInfo.setContent(findGuidMemberRw.getRemark());
		//热文会员服务
		addNotifyInfo.setType("member");
		addNotifyInfo.setRemark(findGuidMemberRw.getUserLevel());
		logger.info("向导购推送消息提醒：{}", addNotifyInfo);
		notifyService.sendMsgInfo(addNotifyInfo);
	}
	

}
