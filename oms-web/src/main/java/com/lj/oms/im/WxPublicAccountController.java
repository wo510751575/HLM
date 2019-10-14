/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.im;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.emus.PmTypeTimeFlag;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPage;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPageReturn;
import com.lj.business.weixin.dto.publicaccount.UpdateWxPublicAccount;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.oms.dto.ResponseDto;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 类说明：微信公众号
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月3日
 */
@Controller
@RequestMapping(value = "${adminPath}/im/publicaccount/")
public class WxPublicAccountController {

    private static final Logger logger = LoggerFactory.getLogger(WxPublicAccountController.class);
    
    private static final String LIST = "modules/im/publicAccount";
	
	@Resource
	private IWxPublicAccountService wxPublicAccountService;
	
	@Resource
	private IPmTypeService pmTypeService; // 客户分类服务
	
	@Resource
	private IGmAssistantShopService gmAssistantShopService;
	
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	
	@Resource
	private IShopTerminalService shopTerminalService;

	/**
	 * 
	 *
	 * 方法说明：分页查询
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月3日
	 *
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public ResponseDto list(RedirectAttributes redirectAttributes, FindWxPublicAccountPage findWxPublicAccountPage, Integer pageNo, Integer pageSize, Model model) {
		ResponseDto dto = new ResponseDto();
		try {
			if (pageNo != null) {
				findWxPublicAccountPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findWxPublicAccountPage.setLimit(pageSize);
			}
			
			findWxPublicAccountPage.setMerchantNo(UserUtils.getMerchantNo());
//			findWxPublicAccountPage.setShopNos(CcUtils.getShopNoList());

			Page<FindWxPublicAccountPageReturn> pages = wxPublicAccountService.findWxPublicAccountPage(findWxPublicAccountPage);
			List<FindWxPublicAccountPageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindWxPublicAccountPageReturn> page = new com.ape.common.persistence.Page<FindWxPublicAccountPageReturn>(pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
//			model.addAttribute("page", page);
//			model.addAttribute("pageParam", findWxPublicAccountPage);
			dto.setData(page);
			dto.setResult(true);
		} catch (Exception e) {
//			addMessage(redirectAttributes, "加载数据失败,系统出现异常！");
			logger.error(e.getMessage(), e);
			dto.setMsg("加载数据失败");
		}
		return dto;
//		return LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改公众号
	 *
	 * @param updateWxPublicAccount
 	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月5日
	 *
	 */
	@RequestMapping(value="edit")
	@ResponseBody
    public ResponseDto edit(UpdateWxPublicAccount updateWxPublicAccount){
		ResponseDto dto = new ResponseDto();
		try {
			wxPublicAccountService.updateWxPublicAccount(updateWxPublicAccount);
			dto.setResult(true);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			dto.setMsg("编辑数据失败");
		}
		return dto;
	}
	
	/**
	 * 
	 *
	 * 方法说明：公众号素材页面
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author zhangting CreateDate: 2017年11月28日
	 *
	 */
	@RequestMapping(value = {"publicAccountList"})
	public String friendList( Model model, FindShopTidFromWeb findShopTidFromWeb){
		indexData(model,findShopTidFromWeb);
		return LIST;
	}
	
	private void indexData(Model model, FindShopTidFromWeb findShopTidFromWeb) {
		String assistantNo=UserUtils.getUser().getId();//导购助手编号
		String merchantNo = UserUtils.getMerchantNo();//商户编号
		
		//客户分组查询
		FindPmTypePageReturn findPmTypePageReturn=new FindPmTypePageReturn();
		findPmTypePageReturn.setStatus(CommonConstant.Y);
		findPmTypePageReturn.setMerchantNo(merchantNo);
		List<FindPmTypePageReturn> pmType=pmTypeService.findPmTypePages(findPmTypePageReturn);
		//新增 今日新增/7天内新增/30天内新增 客户类型
		addTimePmType(pmType);
		model.addAttribute("pmType",pmType);	
		//终端信息查询
		FindGmAssistantShop findGmAssistantShop=new FindGmAssistantShop();
		findGmAssistantShop.setAssistantNo(assistantNo);
		findGmAssistantShop.setFilterNoTerminal(Boolean.TRUE);	// 过滤掉没有添加终端的终端
		List<FindGmAssistantShopReturn> findGmAssistantShopList = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
		model.addAttribute("shops",findGmAssistantShopList);
		//导购信息查询
//		List<String> shopNos=Lists.newArrayList();
//		if(StringUtils.isNotBlank(findShopTidFromWeb.getShopNo())){
//			shopNos.add(findShopTidFromWeb.getShopNo());
//		}else{
//			for (FindGmAssistantShopReturn findGmAssistantShopReturn : findGmAssistantShopList) {
//				shopNos.add(findGmAssistantShopReturn.getShopNo());
//			}
//			
//		}
		FindGuidMemberPage findGuidMemberPage=new FindGuidMemberPage();
		findGuidMemberPage.setLimit(500);
		findGuidMemberPage.setMerchantNo(merchantNo);
//		findGuidMemberPage.setShopNos(shopNos);
		Page<FindGuidMemberPageReturn> pageDto = guidMemberService.findGuidMemberPage(findGuidMemberPage);
		List<FindGuidMemberPageReturn> guidMembers = Lists.newArrayList();
		guidMembers.addAll(pageDto.getRows());
		model.addAttribute("guidMembers",guidMembers);
		//导购助手管理的终端列表查询
		findShopTidFromWeb.setMerchantNo(merchantNo);
		findShopTidFromWeb.setAssistantNo(assistantNo);
		findShopTidFromWeb.setQueryOnlineBool(Boolean.TRUE); // 查询终端是否在线
		List<FindShopTidFromWebReturn> shopTids = shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
		model.addAttribute("shopTids",shopTids);
		//查询的参数
		model.addAttribute("ShopTidFromWeb",findShopTidFromWeb);
		model.addAttribute("user_photo", UserUtils.getUser().getPhoto());
	}
	
	/**
	 * 新增 今日新增/7天内新增/30天内新增 客户类型
	 * @author 彭俊霖
	 * @param pmType
	 */
	public void addTimePmType(List<FindPmTypePageReturn> pmType) {
		FindPmTypePageReturn todayFlag=new FindPmTypePageReturn();
		todayFlag.setCode(PmTypeTimeFlag.TODAY.toString());
		todayFlag.setTypeName(PmTypeTimeFlag.TODAY.getName());
		FindPmTypePageReturn weekFlag=new FindPmTypePageReturn();
		weekFlag.setCode(PmTypeTimeFlag.WEEK.toString());
		weekFlag.setTypeName(PmTypeTimeFlag.WEEK.getName());
		FindPmTypePageReturn monthFlag=new FindPmTypePageReturn();
		monthFlag.setCode(PmTypeTimeFlag.MONTH.toString());
		monthFlag.setTypeName(PmTypeTimeFlag.MONTH.getName());
		pmType.add(0,todayFlag);
		pmType.add(1,weekFlag);
		pmType.add(2,monthFlag);
	}
}
