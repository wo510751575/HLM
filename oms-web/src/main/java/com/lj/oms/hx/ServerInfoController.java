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
import com.lj.base.exception.TsfaServiceException;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.User;
import com.lj.oms.utils.UserUtils;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.constant.HxConstant;
import com.ye.business.hx.dto.FindServerDetailPage;
import com.ye.business.hx.dto.FindServerInfoPage;
import com.ye.business.hx.dto.FindShopConfigPage;
import com.ye.business.hx.dto.ServerDetailDto;
import com.ye.business.hx.dto.ServerInfoDto;
import com.ye.business.hx.dto.ShopConfigDto;
import com.ye.business.hx.emus.ServerDetailShop;
import com.ye.business.hx.service.IServerDetailService;
import com.ye.business.hx.service.IServerInfoService;
import com.ye.business.hx.service.IShopConfigService;

/**
 * 
 * 
 * 类说明：客户Controller
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author sjiying
 * 
 *         CreateDate: 2019年03月15日
 */
@Controller
@RequestMapping(value = "${adminPath}/hx/serverInfo")
public class ServerInfoController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServerInfoController.class);
	
	/** 服务列表页面 **/
	private final static String PAGE_VIEW_HX_SERVERINFO_LIST = "modules/hx/serverInfoList";
	/** 服务提交页面 **/
	private final static String PAGE_VIEW_HX_SERVERINFO_FORM = "modules/hx/serverInfoForm";
	
	/** 服务 */
	@Autowired
	private IServerInfoService serverInfoService;
	/** 服务明细 */
	@Autowired
	private IServerDetailService serverDetailService;
	/** 门店配置信息 */
	@Autowired
	private IShopConfigService shopConfigService;
	
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
	@RequiresPermissions("hx:serverinfo:view")
	@RequestMapping(value = { "list", "" })
	public String list(Model model, ServerInfoDto findServerInfo, Integer pageNo, Integer pageSize) {
		try {
			
			FindServerInfoPage findServerInfoPage = new FindServerInfoPage();
			
			// 组装分页参数
			findServerInfoPage.setLimit(pageSize != null ? pageSize : 10);
			findServerInfoPage.setStart(pageNo != null ? (pageNo - 1) * findServerInfoPage.getLimit() : 0);
			findServerInfoPage.setParam(findServerInfo);
			
			Page<ServerInfoDto> findServerInfoPageReturn = serverInfoService.findServerInfoPage(findServerInfoPage);
			
			List<ServerInfoDto> list = Lists.newArrayList();
			list.addAll(findServerInfoPageReturn.getRows());
			
			// 服务对应明细
			List<String> serverCodes = list.stream().map(ServerInfoDto::getCode).collect(Collectors.toList());
			if (!serverCodes.isEmpty()) {
				
				FindServerDetailPage findServerDetailPage = new FindServerDetailPage();
				ServerDetailDto serverDetailDto = new ServerDetailDto();
				serverDetailDto.setServerCodes(serverCodes);
				findServerDetailPage.setParam(serverDetailDto);
				List<ServerDetailDto> findServerDetails = serverDetailService.findServerDetails(findServerDetailPage);
				
				Map<String, List<ServerDetailDto>> findServerDetailsMap = findServerDetails.stream().collect(Collectors.groupingBy(ServerDetailDto::getServerCode));
				list.forEach(action -> {
					if (findServerDetailsMap.containsKey(action.getCode())) {
						action.setServerDetails(findServerDetailsMap.get(action.getCode()));
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
			
			com.ape.common.persistence.Page<ServerInfoDto> page = new com.ape.common.persistence.Page<ServerInfoDto>(pageNo != null ? pageNo : 1, findServerInfoPage.getLimit(), findServerInfoPageReturn.getTotal(), list);
			page.initialize();
			
			model.addAttribute("page", page);
			model.addAttribute("paramServerInfo", findServerInfo);
			
		} catch (Exception e) {
			logger.error("获取服务列表错误！", e);
		}

		return PAGE_VIEW_HX_SERVERINFO_LIST;
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
	@RequiresPermissions("hx:serverinfo:view")
	@RequestMapping(value = { "form" })
	public String form(Model model, ServerInfoDto findServerInfo) {
		try {
			
			List<ShopConfigDto> findShopConfigs = findShopConfigs();
			model.addAttribute("findShopConfigs", findShopConfigs);
			
			if (findServerInfo != null && StringUtils.isNotBlank(findServerInfo.getCode())) {
				
				ServerInfoDto data = serverInfoService.findServerInfo(findServerInfo);
				
				if (data != null) {
					
					FindServerDetailPage findServerDetailPage = new FindServerDetailPage();
					ServerDetailDto serverDetailDto = new ServerDetailDto();
					serverDetailDto.setServerCode(data.getCode());
					findServerDetailPage.setParam(serverDetailDto);
					List<ServerDetailDto> serverDetails = serverDetailService.findServerDetails(findServerDetailPage);
					data.setServerDetails(serverDetails);
					
					// 获取客户类型
					Map<String, ShopConfigDto> shopConfigMap = new HashMap<>();
					if (findShopConfigs != null && findShopConfigs.size() > 0) {
						shopConfigMap = findShopConfigs.stream().collect(Collectors.toMap(ShopConfigDto::getCode, ShopConfigDto -> ShopConfigDto));
					}
					model.addAttribute("shopConfigMap", shopConfigMap);
					
					model.addAttribute("data", data);
				}
			}
			
		} catch (Exception e) {
			logger.error("获取服务信息错误！", e);
		}

		return PAGE_VIEW_HX_SERVERINFO_FORM;
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
	 * 获取到店codes
	 * @return
	 */
	private List<String> findShopConfigsCodesByYes() {
		List<ShopConfigDto> findShopConfigs = findShopConfigs();
		if (findShopConfigs != null && findShopConfigs.size() > 0) {
			return findShopConfigs.stream().filter(temp -> {
				return StringUtils.contains(temp.getLableName(), "到店");
			}).map(ShopConfigDto::getCode).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
	
	/**
	 * 方法说明：保存服务
	 * @param model
	 * @param findServerInfo
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hx:serverinfo:edit")
	@RequestMapping(value="save")
	public String save(Model model, ServerInfoDto findServerInfo, RedirectAttributes redirectAttributes) {
		try {
			
			AssertUtils.notNullAndEmpty(findServerInfo);
			
			AssertUtils.notNullAndEmpty(findServerInfo.getServerName(), "产品名称不能为空");
			AssertUtils.notNullAndEmpty(findServerInfo.getPrice(), "价格不能为空");
			AssertUtils.notNullAndEmpty(findServerInfo.getCtx(), "产品说明不能为空");
			AssertUtils.notNullAndEmpty(findServerInfo.getStatus(), "状态不能为空");
			AssertUtils.notNullAndEmpty(findServerInfo.getServerDetails(), "服务项不能为空");
			
			boolean flag = findServerInfo.getServerDetails().stream().anyMatch(temp -> {
				return temp.getServerNum() == null || temp.getServerNum().intValue() <= 0 || StringUtils.isBlank(temp.getUserType());
			});
			
			if (flag) {
				throw new TsfaServiceException(ErrorCode.SERVER_INFO_ADD_ERROR, "新增系统服务信息错误-参数错误！");
			}
			
			Date now = new Date();
			User user = UserUtils.getUser();
			
			findServerInfo.setCreateDate(now);
			findServerInfo.setUpdateDate(now);
			findServerInfo.setCreateId(user.getId());
			findServerInfo.setUpdateId(user.getId());
			
			// 客户类型是否到店：
			List<String> configs = findShopConfigsCodesByYes();
			findServerInfo.getServerDetails().forEach(action -> {
				if (configs.contains(action.getUserType())) {
					action.setIsShop(ServerDetailShop.YES.toString());
				} else {
					action.setIsShop(ServerDetailShop.NO.toString());
				}
			});
			
			serverInfoService.addServerInfoRecord(findServerInfo);
			
			addMessage(redirectAttributes, "保存服务信息" + findServerInfo.getServerName() + "成功");
			
		} catch (Exception e) {
			logger.error("保存客户服务信息错误!", e);
		}
		return "redirect:" + adminPath + "/hx/serverInfo";
	}
	
	/**
	 * 方法说明：保存服务
	 * @param model
	 * @param findServerInfo
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hx:serverinfo:edit")
	@RequestMapping(value="edit")
	public String edit(Model model, ServerInfoDto findServerInfo, RedirectAttributes redirectAttributes) {
		try {
			
			AssertUtils.notNullAndEmpty(findServerInfo);
			AssertUtils.notNullAndEmpty(findServerInfo.getCode(), "服务code不能为空");
			AssertUtils.notNullAndEmpty(findServerInfo.getServerName(), "产品名称不能为空");
			AssertUtils.notNullAndEmpty(findServerInfo.getPrice(), "价格不能为空");
			AssertUtils.notNullAndEmpty(findServerInfo.getCtx(), "产品说明不能为空");
			AssertUtils.notNullAndEmpty(findServerInfo.getStatus(), "状态不能为空");
			AssertUtils.notNullAndEmpty(findServerInfo.getServerDetails(), "服务项不能为空");
			
			boolean flag = findServerInfo.getServerDetails().stream().anyMatch(temp -> {
				return temp.getServerNum() == null || temp.getServerNum().intValue() <= 0 || StringUtils.isBlank(temp.getUserType());
			});
			
			if (flag) {
				throw new TsfaServiceException(ErrorCode.SERVER_INFO_ADD_ERROR, "保存系统服务信息错误-参数错误！");
			}
			
			Date now = new Date();
			User user = UserUtils.getUser();
			
			findServerInfo.setUpdateDate(now);
			findServerInfo.setUpdateId(user.getId());
			
			// 客户类型是否到店：
			List<String> configs = findShopConfigsCodesByYes();
			findServerInfo.getServerDetails().forEach(action -> {
				if (configs.contains(action.getUserType())) {
					action.setIsShop(ServerDetailShop.YES.toString());
				} else {
					action.setIsShop(ServerDetailShop.NO.toString());
				}
			});
			
			serverInfoService.updateServerInfoRecord(findServerInfo);
			
			addMessage(redirectAttributes, "保存服务信息" + findServerInfo.getServerName() + "成功");
			
		} catch (Exception e) {
			logger.error("保存客户服务信息错误!", e);
		}
		return "redirect:" + adminPath + "/hx/serverInfo";
	}
	
	/**
	 * 方法说明：保存服务
	 * @param model
	 * @param findServerInfo
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hx:serverinfo:edit")
	@RequestMapping(value="editByStatus")
	public String editByStatus(Model model, ServerInfoDto findServerInfo, RedirectAttributes redirectAttributes) {
		try {
			
			AssertUtils.notNullAndEmpty(findServerInfo);
			AssertUtils.notNullAndEmpty(findServerInfo.getCode(), "服务code不能为空");
			AssertUtils.notNullAndEmpty(findServerInfo.getStatus(), "状态不能为空");
			
			Date now = new Date();
			User user = UserUtils.getUser();
			
			ServerInfoDto updateInfo = new ServerInfoDto();
			updateInfo.setCode(findServerInfo.getCode());
			updateInfo.setStatus(findServerInfo.getStatus());
			updateInfo.setUpdateDate(now);
			updateInfo.setUpdateId(user.getId());
			
			serverInfoService.updateServerInfo(updateInfo);
			
			addMessage(redirectAttributes, "更改服务状态成功");
			
		} catch (Exception e) {
			logger.error("保存客户服务信息错误!", e);
		}
		return "redirect:" + adminPath + "/hx/serverInfo";
	}

}
