/**
 * 
 */
package com.lj.business.api.controller.hx;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.common.ErrorCode;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.UserHessianService;
import com.ye.business.hx.constant.HxConstant;
import com.ye.business.hx.dto.FindServerDetailPage;
import com.ye.business.hx.dto.FindServerInfoPage;
import com.ye.business.hx.dto.FindShopOrderPage;
import com.ye.business.hx.dto.FindShopServerDetailPage;
import com.ye.business.hx.dto.FindShopServerPage;
import com.ye.business.hx.dto.ServerDetailDto;
import com.ye.business.hx.dto.ServerInfoDto;
import com.ye.business.hx.dto.ShopOrderDto;
import com.ye.business.hx.dto.ShopServerDetailDto;
import com.ye.business.hx.dto.ShopServerDto;
import com.ye.business.hx.emus.OrderStatus;
import com.ye.business.hx.emus.OrderType;
import com.ye.business.hx.emus.Status;
import com.ye.business.hx.service.IServerDetailService;
import com.ye.business.hx.service.IServerInfoService;
import com.ye.business.hx.service.IShopOrderService;
import com.ye.business.hx.service.IShopServerDetailService;
import com.ye.business.hx.service.IShopServerService;

/**
 * 
 * 类说明：门店服务。
 *  
 * <p>
 *   
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate:  2019年3月13日
 */
@Controller
@RequestMapping(value = "/hx/")
public class ShopServerAction {

	@Autowired
	private IShopServerService shopServerService;

	@Autowired
	private IShopServerDetailService shopServerDetailService;
	
	@Autowired
	private IServerInfoService serverInfoService;
	@Autowired
	private IServerDetailService serverDetailService;
	
	@Autowired
	private IShopOrderService shopOrderService;
	
	@Autowired
	private UserHessianService userHessianService;
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	/**
	 * 门诊已有的服务列表。
	 * @param param
	 * @param findShopSchedulePage
	 * @return
	 */
	@RequestMapping(value = { "/shopserver/list.do" })
	@ResponseBody
	public Page<ShopServerDto> findShopServerPage(ShopServerDto param,
			FindShopServerPage findPage) {
//		AssertUtils.notNullAndEmpty(param.getShopNo(),"门诊号不能为空！");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		
		findPage.setParam(param);
		Page<ShopServerDto> pages = shopServerService.findShopServerPage(findPage);
		if(pages.getTotal()>0) {
			ShopServerDetailDto detailParam=new ShopServerDetailDto();
			FindShopServerDetailPage findDetailPage=new FindShopServerDetailPage();
			findDetailPage.setParam(detailParam);
			for (Iterator<ShopServerDto> it = pages.getRows().iterator(); it.hasNext();) {
				ShopServerDto server = it.next();
				detailParam.setShopServerCode(server.getCode());
				List<ShopServerDetailDto> serverDetails= shopServerDetailService.findShopServerDetails(findDetailPage);
				server.setServerDetails(serverDetails);
			}
		}
		return pages;
	}
	
	/**
	 * 服务详情介绍。
	 * @param param
	 * @param findShopSchedulePage
	 * @return
	 */
	@RequestMapping(value = { "/server/detail.do" })
	@ResponseBody
	public ServerInfoDto findServerInfo(ShopServerDto param) {
		AssertUtils.notNullAndEmpty(param.getServerCode(),"服务code不能为空！");
		//1.服务
		ServerInfoDto findServerParam=new ServerInfoDto();
		findServerParam.setCode(param.getServerCode());
		ServerInfoDto serverInfoDto=serverInfoService.findServerInfo(findServerParam);
		
		// 2.服务详情
		FindServerDetailPage findServerDetailPage = new FindServerDetailPage();
		ServerDetailDto dParam = new ServerDetailDto();
		dParam.setServerCode(serverInfoDto.getCode());
		findServerDetailPage.setParam(dParam);
		List<ServerDetailDto> serverDetailDtos = serverDetailService.findServerDetails(findServerDetailPage);

		serverInfoDto.setServerDetails(serverDetailDtos);
		return serverInfoDto;
	}
	
	
	/**
	 * 系统的服务列表。
	 * @param param
	 * @param findShopSchedulePage
	 * @return
	 */
	@RequestMapping(value = { "/server/list.do" })
	@ResponseBody
	public Page<ServerInfoDto> findServerInfoPage(ServerInfoDto param,
			FindServerInfoPage findPage) {
		param.setStatus(Status.use.toString());
		findPage.setParam(param);
		Page<ServerInfoDto> pages = serverInfoService.findServerInfoPage(findPage);
		
		if(pages.getTotal()>0) {
			ServerDetailDto detailParam=new ServerDetailDto();
			FindServerDetailPage findDetailPage=new FindServerDetailPage();
			findDetailPage.setParam(detailParam);
			for (Iterator<ServerInfoDto> it = pages.getRows().iterator(); it.hasNext();) {
				ServerInfoDto server = it.next();
				detailParam.setServerCode(server.getCode());
				List<ServerDetailDto> serverDetails= serverDetailService.findServerDetails(findDetailPage);
				server.setServerDetails(serverDetails);
			}
		}
		return pages;
	}
	
	
	/**
	 * 购买服务-上传凭证。
	 * @param orderDto
	 * @return
	 */
	@RequestMapping(value = { "/server/buy.do" })
	@ResponseBody
	public ShopOrderDto buyServer(ShopOrderDto orderDto) {
//		AssertUtils.notNullAndEmpty(orderDto.getShopNo(),"门诊号不能为空！");
		AssertUtils.notNullAndEmpty(orderDto.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(orderDto.getServeCode(),"服务code不能为空！");
		AssertUtils.notNullAndEmpty(orderDto.getMemberNoGuid(),"下单人不能为空！");
//		AssertUtils.notNullAndEmpty(orderDto.getMemberNameGuid(),"下单人姓名不能为空！");
		AssertUtils.notNullAndEmpty(orderDto.getPayType(),"支付方式不能为空！");
		AssertUtils.notNullAndEmpty(orderDto.getPayCert(),"支付凭证不能为空！");
//		AssertUtils.notNullAndEmpty(orderDto.getPayTimeStr(),"支付时间不能为空！");
		
		
		Date payTime = null;
		if(StringUtils.isNotEmpty(orderDto.getPayTimeStr())) {
			payTime=DateUtils.parseDate(orderDto.getPayTimeStr(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss,null);
			if(payTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "支付时间格式异常！");
			}
			orderDto.setPayTime(payTime);
		}
		
		
		ServerInfoDto findServerParam=new ServerInfoDto();
		findServerParam.setCode(orderDto.getServeCode());
		ServerInfoDto serverInfoDto=serverInfoService.findServerInfo(findServerParam);
		if (serverInfoDto == null) {
			throw new TsfaServiceException(com.ye.business.hx.constant.ErrorCode.SERVER_INFO_NOT_EXIST_ERROR,
					"找不到购买的服务！");
		}
		orderDto.setServeName(serverInfoDto.getServerName());
		orderDto.setCreateId(orderDto.getMemberNoGuid());
		
		
		User user = userHessianService.findByUserId(orderDto.getMemberNoGuid());
		orderDto.setMemberNameGuid(user.getName());
		orderDto.setMobile(user.getMobile());
		orderDto.setStatus(OrderStatus.WAIT.toString());
		orderDto.setOrderType(OrderType.SERVER.toString());
		orderDto.setMerchantName(user.getCompany().getName());
//		orderDto.setShopName(user.getCompany().getName());//商户即门店
		
		ShopOrderDto rt = shopOrderService.addShopOrder(orderDto);
		return rt;
	}
	
	
	/**
	 * 购买服务的支付帐号。
	 * @return
	 */
	@RequestMapping(value = { "/server/payaccount.do" })
	@ResponseBody
	public Map<String, Object> payAccount() {
		
		Map<String,String> ccMap =  localCacheSystemParams.getSystemParamGroup(HxConstant.systemAliasName, HxConstant.GROUP_PAY_ACCT);
		String wxPayPic =  ccMap.get(HxConstant.PARAM_KEY_WXPAYPIC);
		String aliPayPic =  ccMap.get(HxConstant.PARAM_KEY_ALIPAYPIC);
		String bankName =  ccMap.get(HxConstant.PARAM_KEY_BANKNAME);
		String bankAcctNo =  ccMap.get(HxConstant.PARAM_KEY_BANKACCTNO);
		String payPhone =  ccMap.get(HxConstant.PARAM_KEY_PAYPHONE);
		String branchName=  ccMap.get(HxConstant.PARAM_KEY_BRANCH_NAME);
		String bankAcctName=  ccMap.get(HxConstant.PARAM_KEY_BANKACCT_NAME);
		
		Map<String, Object> map = new HashMap<>();
	 
		map.put("wxPayPic", wxPayPic);
		map.put("aliPayPic", aliPayPic);
		map.put("bankName", bankName);
		map.put("branchName", branchName);
		map.put("bankAcctNo", bankAcctNo);
		map.put("payPhone", payPhone);
		map.put("bankAcctName", bankAcctName);
		
		return map;
	}
	
	/**
	 * 分页查找门诊的服务订单
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/server/orderlist.do" })
	@ResponseBody
	public Page<ShopOrderDto> findShopOrderPage(ShopOrderDto param,FindShopOrderPage findPage){
//		AssertUtils.notNullAndEmpty(orderDto.getShopNo(),"门诊号不能为空！");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		param.setOrderType(OrderType.SERVER.toString());
		findPage.setParam(param);
		Page<ShopOrderDto> pages = shopOrderService.findShopOrderPage(findPage);
		
		return pages;
	}
	
	
	/**
	 * 门诊服务订单详情
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/server/orderDetail.do" })
	@ResponseBody
	public ShopOrderDto findShopOrderDetail(ShopOrderDto param) {
		AssertUtils.notNullAndEmpty(param.getCode(), "门诊号不能为空！");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户号不能为空！");
		ShopOrderDto data = shopOrderService.findShopOrder(param);
		//不是自己门诊的订单不让看
		if(data!=null && !param.getMerchantNo().equals(data.getMerchantNo())) {
			data = null;
		}
		return data;
	}
	
	
}
