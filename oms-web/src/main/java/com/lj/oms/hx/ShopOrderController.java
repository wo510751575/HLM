/**
 * 
 */
package com.lj.oms.hx;

import java.math.BigDecimal;
import java.util.List;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.User;
import com.lj.oms.utils.UserUtils;
import com.ye.business.hx.dto.FindServerInfoPage;
import com.ye.business.hx.dto.FindShopOrderPage;
import com.ye.business.hx.dto.ServerInfoDto;
import com.ye.business.hx.dto.ShopOrderDto;
import com.ye.business.hx.emus.OrderStatus;
import com.ye.business.hx.emus.OrderType;
import com.ye.business.hx.emus.PayType;
import com.ye.business.hx.emus.Status;
import com.ye.business.hx.service.IServerInfoService;
import com.ye.business.hx.service.IShopOrderService;
import com.ye.business.hx.util.ObjectUtil;

/**
 * 
 * 
 * 类说明：焕新服务订单。
 *  
 * 
 * <p>
 *   
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate:  2019年3月15日
 */
@Controller
@RequestMapping(value = "${adminPath}/hx/shop/order")
public class ShopOrderController  extends BaseController {

	/** 列表页面 **/
	private final static String PAGE_VIEW_SHOPORDER_LIST = "modules/hx/shopOrderList";
	/** 新增页面 **/
	private final static String PAGE_VIEW_SHOPORDER_FORM = "modules/hx/shopOrderForm";
	/**   重定向到列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_SHOPORDER = "redirect:"+Global.getAdminPath()+ "/hx/shop/order";
	
	@Autowired
	private IShopOrderService shopOrderService;
	@Autowired
	private IServerInfoService serverInfoService;
	
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
	 * 方法说明：门诊服务订单列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param dtoPageDto
	 * @return 服务提供分页，转换OMS分页列表
	 *
	 * @author 刘红艳 CreateDate: 2019.03.15
	 *
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, ShopOrderDto shopOrderDto, String  returnMessage,
			RedirectAttributes redirectAttributes ) {
		try {
			shopOrderDto.setOrderType(OrderType.SERVER.toString());
			
			FindShopOrderPage param = new FindShopOrderPage();
			param.setParam(shopOrderDto);
//			shopOrderDto.setMerchantNo(UserUtils.getMerchantNo());
			if (pageNo != null) {
				param.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				param.setLimit(pageSize);
			}
			Page<ShopOrderDto> pages = shopOrderService.findShopOrderPage(param);
			List<ShopOrderDto> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<ShopOrderDto> page = new com.ape.common.persistence.Page<ShopOrderDto>(
					pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("shopOrderDto", shopOrderDto);
			model.addAttribute("orderStatus",OrderStatus.values());
			model.addAttribute("payTypes",PayType.values());
		} catch (Exception e) {
			logger.error("获取门诊服务订单数据错误", e);
		}
		return PAGE_VIEW_SHOPORDER_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：form页面展现
	 *
	 * @param model
	 * @param dto
	 * @return 编辑页面数据
	 *
	 * @author 刘红艳 CreateDate: 2019.03.15
	 *
	 */
	@RequestMapping(value = "form")
	public String form(Model model,ShopOrderDto dto){
		try {
			if(dto !=null && dto.getCode()!= null){
				ShopOrderDto data =	shopOrderService.findShopOrder(dto);
				model.addAttribute("data", data);	
				model.addAttribute("payTypes",PayType.values());
				
				/**提供系统服务 用于更改*/
				ServerInfoDto param = new ServerInfoDto();
				FindServerInfoPage findPage=new FindServerInfoPage();
				param.setStatus(Status.use.toString());
				findPage.setParam(param);
				List<ServerInfoDto> serverinfos = serverInfoService.findServerInfos(findPage);
				
				model.addAttribute("serverinfos",serverinfos);
				
			}
		} catch (Exception e) {
			logger.error("查询门诊服务订单数据错误",e);
		}
	
		return PAGE_VIEW_SHOPORDER_FORM;
	}
	

	/**
	 * 
	 *
	 * 方法说明：门诊服务订单编辑
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param dto
	 * @return
	 *
	 * @author 刘红艳 CreateDate: 2019.03.15
	 *
	 */
	@RequestMapping(value="edit")
	public String edit(Model model,RedirectAttributes redirectAttributes,ShopOrderDto dto){
		try {
			if(StringUtils.isNotEmpty(dto.getAmountStr())) {
				BigDecimal d=new BigDecimal(dto.getAmountStr());
				dto.setAmount(ObjectUtil.toLong(d));
			}
			dto.setUpdateId(UserUtils.getUser().getName());
			shopOrderService.updateShopOrder(dto);
			addMessage(redirectAttributes, "修改门诊服务订单成功");
		} catch (Exception e) {
			if( e instanceof TsfaServiceException ) {
				TsfaServiceException tException=(TsfaServiceException)e;
				addMessage(redirectAttributes, "操作失败！"+tException.getExceptionInfo());
			}
			logger.debug("修改门诊服务订单错误",e);
		}	
		return PAGE_VIEW_REDIRECT_SHOPORDER;
		
	}
	
}
