package com.lj.oms.hx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;
import com.ye.business.hx.constant.HxConstant;
import com.ye.business.hx.dto.FindServerInfoPage;
import com.ye.business.hx.dto.FindShopConfigPage;
import com.ye.business.hx.dto.FindShopOrderPage;
import com.ye.business.hx.dto.FindShopServerDetailPage;
import com.ye.business.hx.dto.FindShopServerPage;
import com.ye.business.hx.dto.ServerInfoDto;
import com.ye.business.hx.dto.ShopConfigDto;
import com.ye.business.hx.dto.ShopOrderDto;
import com.ye.business.hx.dto.ShopServerDetailDto;
import com.ye.business.hx.dto.ShopServerDto;
import com.ye.business.hx.emus.Status;
import com.ye.business.hx.service.IServerInfoService;
import com.ye.business.hx.service.IShopConfigService;
import com.ye.business.hx.service.IShopOrderService;
import com.ye.business.hx.service.IShopServerDetailService;
import com.ye.business.hx.service.IShopServerService;

/**
 * 
 * 
 * 类说明：开通服务Controller
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author sjiying
 * 
 *         CreateDate: 2019年03月18日
 */
@Controller
@RequestMapping(value = "${adminPath}/hx/shop/server")
public class ShopServerController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopServerController.class);
	
	/** 服务列表页面 **/
	private final static String PAGE_VIEW_HX_SHOPSERVER_LIST = "modules/hx/shopServerList";
	/** 服务提交页面 **/
	private final static String PAGE_VIEW_HX_SHOPSERVER_FORM = "modules/hx/shopServerForm";
	/** 商户服务 */
	@Autowired
	private IShopServerService shopServerService;
	/** 商户服务明细 */
	@Autowired
	private IShopServerDetailService shopServerDetailService;
	@Autowired
	private IShopOrderService shopOrderService;
	/** 服务 */
	@Autowired
	private IServerInfoService serverInfoService;
	/** 门店配置信息 */
	@Autowired
	private IShopConfigService shopConfigService;
	/** 查询所有商户（门诊）集合 */
	@Autowired
	private OfficeService officeService;
	
	/**
	 * 非超级管理员不可操作该模块
	 * @param redirectAttributes
	 */
	@ModelAttribute
	public void checkIsAdmin(RedirectAttributes redirectAttributes) {
		String userId = UserUtils.getUser().getId();
		if(!User.isAdmin(userId)) {
			throw new UnauthorizedException("msg:非超级管理员无权限操作！");
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：服务列表
	 *
	 * @param model
	 * @param findPersonMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月4日
	 *
	 */
	@RequiresPermissions("hx:shopserver:view")
	@RequestMapping(value = { "list", "" })
	public String list(Model model, ShopServerDto findShopServer, Integer pageNo, Integer pageSize) {
		try {
			
			FindShopServerPage findShopServerPage = new FindShopServerPage();
			
			// 组装分页参数
			findShopServerPage.setLimit(pageSize != null ? pageSize : 10);
			findShopServerPage.setStart(pageNo != null ? (pageNo - 1) * findShopServerPage.getLimit() : 0);
			findShopServerPage.setParam(findShopServer);
			
			Page<ShopServerDto> findShopServerPageReturn = shopServerService.findShopServerPage(findShopServerPage);
			
			List<ShopServerDto> list = Lists.newArrayList();
			list.addAll(findShopServerPageReturn.getRows());
			
			// 服务对应明细
			List<String> serverCodes = list.stream().map(ShopServerDto::getCode).collect(Collectors.toList());
			if (!serverCodes.isEmpty()) {
				
				FindShopServerDetailPage findShopServerDetailPage = new FindShopServerDetailPage();
				ShopServerDetailDto shopServerDetailDto = new ShopServerDetailDto();
				shopServerDetailDto.setShopServerCodes(serverCodes);
				findShopServerDetailPage.setParam(shopServerDetailDto);
				List<ShopServerDetailDto> findShopServerDetails = shopServerDetailService.findShopServerDetails(findShopServerDetailPage);
				
				Map<String, List<ShopServerDetailDto>> findShopServerDetailsMap = findShopServerDetails.stream().collect(Collectors.groupingBy(ShopServerDetailDto::getShopServerCode));
				list.forEach(action -> {
					if (StringUtils.isNotBlank(action.getCode()) && findShopServerDetailsMap.containsKey(action.getCode())) {
						action.setServerDetails(findShopServerDetailsMap.get(action.getCode()));
					}
				});
				
				// 获取客户类型
				Map<String, ShopConfigDto> shopConfigMap = new HashMap<>();
				List<ShopConfigDto> findShopConfigs = findShopConfigs();
				if (findShopConfigs != null && findShopConfigs.size() > 0) {
					shopConfigMap = findShopConfigs.stream().collect(Collectors.toMap(ShopConfigDto::getCode, ShopConfigDto -> ShopConfigDto));
				}
				model.addAttribute("shopConfigMap", shopConfigMap);
			}
			
			// 对应订单
			List<String> orderNos = list.stream().map(ShopServerDto::getOrderNo).collect(Collectors.toList());
			if (!orderNos.isEmpty()) {
				
				FindShopOrderPage findShopOrderPage = new FindShopOrderPage();
				ShopOrderDto param = new ShopOrderDto();
				param.setCodes(orderNos);
				findShopOrderPage.setParam(param);
				List<ShopOrderDto> findShopOrderReturn = shopOrderService.findShopOrders(findShopOrderPage);
				Map<String, ShopOrderDto> findShopOrderReturnMap = findShopOrderReturn.stream().collect(Collectors.toMap(ShopOrderDto::getCode, ShopOrderDto -> ShopOrderDto));
				list.forEach(temp -> {
					if (findShopOrderReturnMap.containsKey(temp.getOrderNo())) {
						ShopOrderDto order = findShopOrderReturnMap.get(temp.getOrderNo());
						temp.setMemberNameGuid(order.getMemberNameGuid());
						temp.setMemberNoGuid(order.getMemberNoGuid());
					}
				});
			}
			
			com.ape.common.persistence.Page<ShopServerDto> page = new com.ape.common.persistence.Page<ShopServerDto>(pageNo != null ? pageNo : 1, findShopServerPage.getLimit(), findShopServerPageReturn.getTotal(), list);
			page.initialize();
			
			model.addAttribute("page", page);
			model.addAttribute("paramShopServer", findShopServer);
			
		} catch (Exception e) {
			logger.error("获取服务列表错误！", e);
		}

		return PAGE_VIEW_HX_SHOPSERVER_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：服务列表
	 *
	 * @param model
	 * @param findPersonMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月4日
	 *
	 */
	@RequiresPermissions("hx:shopserver:view")
	@RequestMapping(value = { "form" })
	public String form(Model model, ShopServerDto findShopServer) {
		try {

			// 获取所有商户
			List<Office> officeList = officeService.findMerchantsByOffice(new Office());
			model.addAttribute("officeList", officeList);
			
			// 获取所有服务列表
			FindServerInfoPage findServerInfoPage = new FindServerInfoPage();
			ServerInfoDto param = new ServerInfoDto();
			param.setStatus(Status.use.toString());
			findServerInfoPage.setParam(param);
			List<ServerInfoDto> serverInfoList = serverInfoService.findServerInfos(findServerInfoPage);
			model.addAttribute("serverInfoList", serverInfoList);

		} catch (Exception e) {
			logger.error("获取服务信息错误！", e);
		}

		return PAGE_VIEW_HX_SHOPSERVER_FORM;
	}
	
	/**
	 * 获取客户类型List
	 * @return
	 */
	private List<ShopConfigDto> findShopConfigs() {
		ShopConfigDto param = new ShopConfigDto();
		param.setMerchantNo(Office.ROOT_ID);
		param.setLableNo(HxConstant.CONFIG_LABLE_NO_USER_TYPE);

		FindShopConfigPage findShopConfigPage = new FindShopConfigPage();
		findShopConfigPage.setParam(param);

		List<ShopConfigDto> list = shopConfigService.findShopConfigs(findShopConfigPage);

		ShopConfigDto findConfig = new ShopConfigDto();
		if (list != null && list.size() > 0) {
			findConfig.setParentCode(list.get(0).getCode());
			findConfig.setMerchantNo(Office.ROOT_ID);

			FindShopConfigPage findPage = new FindShopConfigPage();
			findPage.setParam(findConfig);
			return shopConfigService.findShopConfigs(findPage);
		}

		return new ArrayList<ShopConfigDto>();
	}
	
	/**
	 * 方法说明：保存服务
	 * @param model
	 * @param findServerInfo
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hx:shopserver:edit")
	@RequestMapping(value="save")
	public String save(Model model, ShopServerDto findShopServer, RedirectAttributes redirectAttributes) {
		try {
			
			AssertUtils.notNullAndEmpty(findShopServer);
			
			AssertUtils.notNullAndEmpty(findShopServer.getMerchantNo(), "商户不能为空");
			AssertUtils.notNullAndEmpty(findShopServer.getServerCode(), "服务不能为空");

			// 查找对应商户信息
			Office office = officeService.findOffice(findShopServer.getMerchantNo());
			AssertUtils.notNullAndEmpty(office);
			
			findShopServer.setMerchantName(office.getName());
			
			findShopServer.setShopNo(office.getId());
			findShopServer.setShopName(office.getName());
			
			
			Date now = new Date();
			User user = UserUtils.getUser();
			
			findShopServer.setCreateDate(now);
			findShopServer.setUpdateDate(now);
			findShopServer.setCreateId(user.getName());
			findShopServer.setUpdateId(user.getName());
			
			String rs = shopServerService.addShopServerRecord(findShopServer);
			
			addMessage(redirectAttributes, "保存服务信息" + rs + "成功");
			
		} catch (Exception e) {
			logger.error("保存客户服务信息错误!", e);
		}
		return "redirect:" + adminPath + "/hx/shop/server";
	}

}
