package com.lj.oms.member;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.encryption.MD5;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.AddGuidMember;
import com.lj.business.member.dto.AddManagerMember;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindManagerMemberPage;
import com.lj.business.member.dto.FindManagerMemberPageReturn;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.ManagerMemberDto;
import com.lj.business.member.dto.ManagerMemberReturnDto;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.dto.UpdateManagerMember;
import com.lj.business.member.dto.guidCard.UpdateGuidCard;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.emus.Gender;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.service.IGuidCardService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.contacts.ForcedLogoutMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.business.supcon.service.ITokenService;
import com.lj.oms.common.BaseController;
import com.lj.oms.dto.CommonRepsonseDto;
import com.lj.oms.entity.sys.Dict;
import com.lj.oms.service.sys.DictService;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.Validator;
import com.lj.oms.utils.excel.ExportExcel;
import com.lj.oms.utils.excel.ImportExcel;
import com.lj.oms.utils.excel.dto.GuidExportDto;
import com.lj.oms.utils.excel.dto.GuidImportDto;

/**
 * 
 * 
 * 类说明：导购Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月14日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/guid")
public class GuidController  extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(GuidController.class);
	
	/**  导购列表页面 **/
	private final static String PAGE_VIEW_GUID_LIST = "modules/member/guidList";	
	/**  导购编辑页面 **/
	private final static String PAGE_VIEW_GUID_FORM = "modules/member/guidForm";	
	/**  重定向到导购列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_GUID = "redirect:" +Global.getAdminPath() + "/member/guid";	
	/**  重定向到导购列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_GUID_REPAGE = "redirect:"+Global.getAdminPath()+"/member/guid/?repage";	
	
	@Resource
	private IGuidMemberService guidMemberService;			//导购服务
	@Resource
	private IManagerMemberService managerMemberService;		//管理人员服务
//	@Resource
//	private IShopService shopService;						//终端服务
	@Resource
	private ITokenService tokenService;
	@Resource
	private DictService  dictService;
	@Resource
	private IGuidCardService guidCardService; 
	@Resource
	private IPersonMemberService personMemberService; // 客户信息服务
	@Resource
	private IShopTerminalService shopTerminalService;	//终端终端服务
	
//    @Autowired 
//	private ISystemInfoService systemInfo;
//    
//    @Autowired 
//	private RedisCache redisCache;

    @Autowired 
	ICommonService commonService;
	/**
	 * 
	 *
	 * 方法说明：导购列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findGuidMemberPage
	 * @param request
	 * @param response
	 * @return	服务层分页转OMS分页列表
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 * @param addMessage 
	 *
	 */
	@RequiresPermissions("member:guid:view")
	@RequestMapping(value = {"list", ""})
	public String list( Model model,Integer pageNo,Integer pageSize,FindGuidMemberPage findGuidMemberPage, String  returnMessage,
			RedirectAttributes redirectAttributes,String code) {
		try {
			findGuidMemberPage.setMerchantNo(UserUtils.getMerchantNo());
//			findGuidMemberPage.setShopNos(CcUtils.getShopNoList());
			if(pageNo!=null){
				findGuidMemberPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findGuidMemberPage.setLimit(pageSize);
			}
			findGuidMemberPage.setStartTime(DateUtils.getDateByFirstSeconds(findGuidMemberPage.getStartTime()));
			findGuidMemberPage.setEndTime(DateUtils.getDateByLastSeconds(findGuidMemberPage.getEndTime()));
			Page<FindGuidMemberPageReturn> pageDto = guidMemberService.findGuidMemberPage(findGuidMemberPage);
			List<FindGuidMemberPageReturn> list = Lists.newArrayListWithExpectedSize(pageDto.getRows().size());
			list.addAll(pageDto.getRows());
			
			//暂时注释  UpdateDate By 彭俊霖 2018.06.14  BUG#7470
			//分店下拉列表
//			FindShop findShop =CcUtils.shopFilter();
//			List<FindShopPageReturn> shops= shopService.findShops(findShop);
//			model.addAttribute("shops",shops);
			
			//同步店名  
//			Map<String,String> shopMap=new HashMap<String, String>();
//			for (FindShopPageReturn shop : shops) {
//				if(shop!=null){
//					shopMap.put(shop.getShopNo(), shop.getShopName());
//				}
//			}
//			for(FindGuidMemberPageReturn guidPageReturn:pageDto.getRows()){
//				guidPageReturn.setShopName(shopMap.get(guidPageReturn.getShopNo()));
//			}
//			if (StringUtils.isNoneBlank(findGuidMemberPage.getShopNo())) {
//				findGuidMemberPage.setShopName(shopMap.get(findGuidMemberPage.getShopNo()));
//			}
			
			com.ape.common.persistence.Page<FindGuidMemberPageReturn> page	=new com.ape.common.persistence.Page<FindGuidMemberPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			
			//参数
			model.addAttribute("memberStatus",MemberStatus.values());
			model.addAttribute("genders",Gender.values());
			model.addAttribute("paramGuid",findGuidMemberPage);
			if(StringUtils.isNotBlank(code)){
//				model.addAttribute("returnMessage", returnMessage);
				model.addAttribute("repMsg", returnMessage);
			}
		} catch (Exception e) {
			logger.error("获取导购信息异常！", e);
		}
		
		return PAGE_VIEW_GUID_LIST;
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：获取编辑页面数据
	 *
	 * @param model
	 * @param findGuidMemberPage
	 * @return
	 *  findGuidMemberByCode
	 * @author 罗书明 CreateDate: 2017年7月18日
	 *
	 */
	@RequestMapping(value="form")
	public String form(Model model,UpdateGuidMember updateGuidMember ){
		try {
			if(updateGuidMember!=null && updateGuidMember.getCode()!=null){
		     GuidMemberReturnDto guidMemberReturnDto = guidMemberService.findGuidMemberByCode(updateGuidMember);	
		     guidMemberReturnDto.setPageNo(updateGuidMember.getPageNo());
			 model.addAttribute("data",guidMemberReturnDto);
			 //分店与地址下拉列表
//			 FindShop findShop = CcUtils.shopFilter();
//			 List<FindShopPageReturn> shops= shopService.findShops(findShop);
			 //终端(工作)微信下拉列表
			 List<FindShopTerminalReturn> shopTerminals= shopTerminalService.findShopTerminalByShopNo("");
			 
			 model.addAttribute("shopTerminals",shopTerminals);		    
//			 model.addAttribute("shops",shops);		    
			 model.addAttribute("genders",Gender.values());
			}
		} catch (Exception e) {
			logger.error("获取导购信息异常！", e);
		}		
		return PAGE_VIEW_GUID_FORM;
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑修改数据保存方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param updateGuidMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月18日
	 * FindShopReturnAreaCode findShopMerchantName(FindShopDto findShopDto) 
	 */
	@RequestMapping(value="edit")
	public String edit(Model model,UpdateGuidMember updateGuidMember,RedirectAttributes redirectAttributes){
		try {
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(updateGuidMember.getMemberNo());
			FindGuidMemberReturn guidMember= guidMemberService.findGuidMember(findGuidMember);
			//如果店员微信已绑定有微信号客户就不可以修改微信号
			if(!MemberStatus.LEAVE.toString().equals(updateGuidMember.getStatus())){
				FindPersonMember findPersonMember=new FindPersonMember();
				findPersonMember.setMemberNoGm(guidMember.getMemberNo());
				List<FindPersonMemberReturn> personMemberReturns = personMemberService.findPersonMemberByNoWx(findPersonMember);
				if(StringUtils.isNotBlank(guidMember.getNoWx()) && !guidMember.getNoWx().equals(updateGuidMember.getNoWx()) && personMemberReturns.size()>0){
					 model.addAttribute("returnMassage", "微信号"+guidMember.getNoWx()+"已绑定有关联客户,不可以修改该微信号！");
					 return form(model, updateGuidMember);
				}
                //手机号码唯一
				FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
				findGuidMemberPage.setMobile(updateGuidMember.getMobile());
				List<FindGuidMemberPageReturn> guidMemberPages= guidMemberService.findGuidMembers(findGuidMemberPage);
				if(!guidMember.getMobile().equals(updateGuidMember.getMobile()) && guidMemberPages.size()>0){
					model.addAttribute("returnMassage", "手机号码" + updateGuidMember.getMobile() + "已存在！请重新输入手机号码！");
					return form(model, updateGuidMember);
				}
				
				
			}
			
/*			ManagerMemberDto record = new ManagerMemberDto();
			record.setMobile(guidMember.getMobile());	
			ManagerMemberReturnDto list = managerMemberService.findManagerMemberByMobile(record);*/
			
//			findGuidMember=new FindGuidMember();
			findGuidMember.setMemberNo(updateGuidMember.getMemberNo());
			FindGuidMemberReturn guidMemberPage= guidMemberService.findGuidMember(findGuidMember);
			//判断是否有店长身份
//			ManagerMemberDto record = new ManagerMemberDto();
//			record.setMobile(guidMemberPage.getMobile());	
//			ManagerMemberReturnDto list = managerMemberService.findManagerMemberByMobile(record);
			
			//离职清空登录token
			if(MemberStatus.LEAVE.toString().equals(updateGuidMember.getStatus())){
				List<Dict> dict =dictService.getList(UserUtils.LOGIN_TOKEN_TYPE);
				for(Dict dictList :dict){
					tokenService.removeToken(updateGuidMember.getMemberNo(),dictList.getValue());	
					logger.info("已删除{}对应token", updateGuidMember.getMemberNo());
				}
			}
			
			//离职
		    if(MemberStatus.LEAVE.toString().equals(updateGuidMember.getStatus())){
		    	//强制退出APP登录状态
	    		ForcedLogoutMessage forcedLogoutMessage = new ForcedLogoutMessage();
	    		forcedLogoutMessage.setMemberNoGm(updateGuidMember.getMemberNo());
	    		forcedLogoutMessage.setMessage("您的操作权限不足，即将退出登录！");
	    		
	    		IContactsService basic = commonService.getHessianContactsService(forcedLogoutMessage.getMemberNoGm());
	    		basic.sendForcedLogoutMessage(forcedLogoutMessage);
//		    	if(list != null){
//		    		ManagerDimission managerDimission=new ManagerDimission();
//		    		managerDimission.setCode(list.getCode());
//		    		managerMemberService.memberDimission(managerDimission);
//		    	}
		    	if(MemberStatus.FREEZE.toString().equals(updateGuidMember.getStatus())){
		    		forcedLogoutMessage = new ForcedLogoutMessage();
		    		forcedLogoutMessage.setMemberNoGm(updateGuidMember.getMemberNo());
		    		forcedLogoutMessage.setMessage("您的操作权限不足，即将退出登录！");
		    		IContactsService basics = commonService.getHessianContactsService(forcedLogoutMessage.getMemberNoGm());
		    		basics.sendForcedLogoutMessage(forcedLogoutMessage);
		    	}
		    }
			//同步更新店长信息    中控版本1.01
			/*if(list !=null){
				UpdateManagerMember updateManagerMember = new UpdateManagerMember();
				updateManagerMember.setCode(list.getCode());
				updateManagerMember.setMemberNo(list.getMemberNo());
				updateManagerMember.setMobile(updateGuidMember.getMobile());
				updateManagerMember.setNoWx(updateGuidMember.getNoWx());
				updateManagerMember.setAge(updateGuidMember.getAge());
				updateManagerMember.setImei(updateGuidMember.getImei());
//				updateManagerMember.setMemberNoShop(updateGuidMember.getShopNo());
//				updateManagerMember.setMemberNameShop(updateGuidMember.getShopName());
				updateManagerMember.setStatus(updateGuidMember.getStatus());
				updateManagerMember.setWorkDate(updateGuidMember.getWorkDate());
				updateManagerMember.setAreaCode(updateGuidMember.getAreaCode());
				updateManagerMember.setAreaName(updateGuidMember.getAreaName());
				updateManagerMember.setSex(updateGuidMember.getGender());
				updateManagerMember.setMemberName(updateGuidMember.getMemberName());
				managerMemberService.updateManagerMember(updateManagerMember);
			}*/
			
			//更新个人名片
			if(!MemberStatus.LEAVE.toString().equals(updateGuidMember.getStatus())){
				UpdateGuidCard updateGuidCard=new UpdateGuidCard();
				updateGuidCard.setMobile(updateGuidMember.getMobile());
				updateGuidCard.setMemberNameGm(updateGuidMember.getMemberName());
				updateGuidCard.setAge(updateGuidMember.getAge());
//				updateGuidCard.setShopName(updateGuidMember.getShopName());
//				updateGuidCard.setShopNo(updateGuidMember.getShopNo());
				updateGuidCard.setGender(updateGuidMember.getGender());
				updateGuidCard.setQcord(updateGuidMember.getQcord());
				updateGuidCard.setMemberNoGm(updateGuidMember.getMemberNo());
				guidCardService.updateGuidCardByShop(updateGuidCard);
			}
//            if(list !=null){
//            	updateGuidMember.setManagerCode(list.getCode());
//            }
			guidMemberService.updateGuidMember(updateGuidMember);
			String returnMessage = null;
			if(StringUtils.isNotBlank(updateGuidMember.getCode())){
				 returnMessage="修改导购资料成功！";
			}
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMerchantNo(UserUtils.getMerchantNo());
			return list(model,updateGuidMember.getPageNo(),10,findGuidMemberPage,returnMessage,null,updateGuidMember.getCode());
		} catch (TsfaServiceException e) {
			logger.error("修改导购资料失败！",e);
			String returnMessage = "修改导购资料失败！";
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMerchantNo(UserUtils.getMerchantNo());			
			return list(model,updateGuidMember.getPageNo(),10,findGuidMemberPage,returnMessage,null,updateGuidMember.getCode());
		}		
	}
	
	

	
	/**
	 * 
	 *
	 * 方法说明：修改导购状态
	 *
	 * @param model
	 * @param updateGuidMember
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月4日
	 *
	 */
	@RequestMapping(value="status") 
	public String guidStatus(Model model,UpdateGuidMember updateGuidMember,RedirectAttributes redirectAttributes){
		try {
			List<Dict> dict =dictService.getList(UserUtils.LOGIN_TOKEN_TYPE);
			for(Dict dictList :dict){
				tokenService.removeToken(updateGuidMember.getMemberNo(),dictList.getValue());	
				logger.info("已删除{}对应token", updateGuidMember.getMemberNo());
			}
			
			//冻结状态将强制退出APP登录状态   解冻将不做操作
			if(MemberStatus.FREEZE.toString().equals(updateGuidMember.getStatus())){
				ForcedLogoutMessage forcedLogoutMessage = new ForcedLogoutMessage();
	    		forcedLogoutMessage.setMemberNoGm(updateGuidMember.getMemberNo());
	    		forcedLogoutMessage.setMessage("您的操作权限不足，即将退出登录！");
	    		
	    		IContactsService basic = commonService.getHessianContactsService(forcedLogoutMessage.getMemberNoGm());
	    		basic.sendForcedLogoutMessage(forcedLogoutMessage);
			}
    		
			guidMemberService.updateGuidMember(updateGuidMember);
			addMessage(redirectAttributes, "冻结操作成功！");
		} catch (Exception e) {
			logger.error("冻结导购资料失败！", e);
			addMessage(redirectAttributes, "冻结导购资料失败！");
		}
		return PAGE_VIEW_REDIRECT_GUID;
	}
	
	/**
	 * 
	 *
	 * 方法说明：员工数据导出
	 *
	 *
	 * @param response
	 * @param findGuidMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月19日
	 *
	 */
    @RequestMapping(value = "export")
    public String export(HttpServletResponse response,FindGuidMemberPage findGuidMemberPage,Integer pageNo,Integer pageSize, RedirectAttributes redirectAttributes) {
		try {
			findGuidMemberPage.setMerchantNo(UserUtils.getMerchantNo());
//			findGuidMemberPage.setShopNos(CcUtils.getShopNoList());
			findGuidMemberPage.setStartTime(DateUtils.getDateByFirstSeconds(findGuidMemberPage.getStartTime()));
			findGuidMemberPage.setEndTime(DateUtils.getDateByLastSeconds(findGuidMemberPage.getEndTime()));
    		List<FindGuidMemberPageReturn>  list=guidMemberService.findGuidMemberExport(findGuidMemberPage);
    		if(null != list && list.size() > 0){
	    		for(FindGuidMemberPageReturn gm : list){
	    			//update by Peng Junlin 导出状态
	    			gm.setStatus(MemberStatus.valueOf(gm.getStatus()).getName());
	    			
	    			gm.setMemberType("导购");
	    			if(StringUtils.isNoneBlank(gm.getGender())){
	    				gm.setGender(Gender.valueOf(gm.getGender()).getName());
	  			  	}
	    			try {
//	    				FindShop findShop = new FindShop();
//		    			findShop.setMemberNoMerchant(UserUtils.getMerchantNo());
//		    			findShop.setShopNo(gm.getShopNo());
//		    			FindShopReturn findShopReturn= shopService.findShopByShopNo(findShop);
//		    			gm.setShopNoMerchant(findShopReturn.getShopNoMerchant());
					} catch (TsfaServiceException e) {
						continue;
					}
	    			
	    		}
    		}
    		String fileName = "员工数据导出.xlsx";
    		new ExportExcel("员工数据导出", GuidExportDto.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			logger.error("员工数据导出失败！", e);
			addMessage(redirectAttributes, "员工数据导出失败！失败信息："+e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_GUID_REPAGE;
    }
	
	/**
	 * 
	 *
	 * 方法说明：检查导购登录手机是否唯一
	 *
	 * @param oldMobile
	 * @param mobile
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@ResponseBody
	@RequiresPermissions("member:guid:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldMobile, String mobile) {
		try {
			if (mobile !=null && mobile.equals(oldMobile)) {
				return CommonConstant.TRUE;
			}     
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMobile(mobile);
			List<FindGuidMemberPageReturn> guidMemberPages= guidMemberService.findGuidMembers(findGuidMemberPage);
			if(guidMemberPages !=null && guidMemberPages.size()==0){
				return CommonConstant.TRUE;
			}
			return CommonConstant.FALSE;
		}catch (Exception e) {
			logger.error("检查导购手机信息失败！", e);
			return CommonConstant.TRUE;
		}
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：导购导入
	 * @param file
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年6月27日
	 *
	 */
	@RequiresPermissions("member:guid:edit")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importExcel(MultipartFile file, RedirectAttributes redirectAttributes,Model model) {
		logger.debug("importExcel(MultipartFile file={}, RedirectAttributes redirectAttributes={}, Model model={}) - start", file, redirectAttributes, model); 

		try {
			int successNum = 0;		// 成功数量
			int failureNum = 0;		// 失败数量
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);

			List<GuidImportDto> list = ei.getDataList(GuidImportDto.class);
			for (GuidImportDto user : list) {
				if (StringUtils.isBlank(user.getShopNoMerchant())) {
					continue;
				}
				try {
					/* 校验手机号格式 */
					if (!Validator.isMobile(user.getMobile())) {
						failureMsg.append("<br/>店员手机号： " + user.getMobile() + "格式不正确; ");
						failureNum++;
						continue;
					}
					
					// 处理excel转义的手机号码
					user.setMobile(DictUtils.excelChage(user.getMobile()));
//					user.setImei(DictUtils.excelChage(user.getImei()));
					user.setNoWx(DictUtils.excelChage(user.getNoWx()));

					if (CommonConstant.TRUE.equals(checkLoginName("", user.getMobile()))) {
						String pwd = user.getMobile().substring(user.getMobile().length() - 6);
						AddGuidMember addGuidMember = new AddGuidMember();
						addGuidMember.setMemberName(user.getMemberName());
						addGuidMember.setPwd(MD5.encryptByMD5(pwd)); // 默认手机后6位
						addGuidMember.setMobile(user.getMobile());

						addGuidMember.setMerchantNo(UserUtils.getMerchantNo());
						addGuidMember.setMerchantName(UserUtils.getUser().getCompany().getName());

						// 关联终端 duanzhipeng 2017-07-14 start
//						FindShop findShop = new FindShop();
//						findShop.setMemberNoMerchant(UserUtils.getMerchantNo());
//						findShop.setShopNoMerchant(user.getShopNoMerchant());
//						List<FindShopPageReturn> shopReturns = shopService.findShops(findShop);
//						if (shopReturns != null && shopReturns.size() > 0) {
//							FindShopPageReturn findShopReturn = shopReturns.get(0);
//							addGuidMember.setShopNo(findShopReturn.getShopNo());
//							addGuidMember.setShopName(findShopReturn.getShopName());
//						} else {
//							failureMsg.append("<br/>： 终端代码" + user.getShopNoMerchant() + "不存在！");
//							failureNum++;
//							continue;
//						}

						/**
						 * 当前版本敏华移动终端不再使用中控，所以不用对中控微信进行有效性校验
						 * update by 	zengchuiyu
						 * update date	2018-06-04
						 */
						/* 校验工作微信号是否为终端有效微信号 */
						/*if (StringUtils.isNotBlank(user.getNoWx())) {
							FindShopTerminalPage findShopTerminalPage = new FindShopTerminalPage();
							findShopTerminalPage.setMerchantNo(UserUtils.getMerchantNo());
							findShopTerminalPage.setNoWx(user.getNoWx());
							findShopTerminalPage.setShopNo(shopReturns.get(0).getShopNo());
							if (!shopTerminalService.validNoWx(findShopTerminalPage)) {
								failureMsg.append("<br/>店员微信号： " + user.getNoWx() + "不是终端" + shopReturns.get(0).getShopNoMerchant() + "有效微信");
								failureNum++;
								continue;
							}
						}*/
						if (StringUtils.isEmpty(user.getAreaCode())) {
							failureMsg.append("区域为空！");
							failureNum++;
							continue;
						}

						addGuidMember.setAreaCode(DictUtils.getDictValue(user.getAreaCode(), "erp_dict_1", ""));
						if (StringUtils.isEmpty(user.getAreaCode())) {
							failureMsg.append("<br/>： 区域" + user.getAreaCode() + "不正确！");
							failureNum++;
							continue;
						}
						addGuidMember.setAreaName(user.getAreaCode());
						FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
						if (StringUtils.isNotBlank(user.getNoWx())) {
//	                        findGuidMemberPage.setShopNo(addGuidMember.getShopNo());
	                        findGuidMemberPage.setNoWx(user.getNoWx());
	                        List<FindGuidMemberPageReturn> guidMemberPage = guidMemberService.findGuidMemberNoWx(findGuidMemberPage);
	                        if (guidMemberPage.size() > 0) {
	                            failureMsg.append("导购已绑定该微信,请修改为对应终端的微信号！");
	                            failureNum++;
	                            continue;
	                        }
                        }
						
						String wx = user.getNoWx().trim();
						addGuidMember.setNoWx(wx);

						// 根据工作微信号填入终端终端二维码
						if (StringUtils.isNotBlank(user.getNoWx())) {
						    FindShopTerminal findShopTerminal = new FindShopTerminal();
	                        findShopTerminal.setNoWx(wx);
	                        List<FindShopTerminalReturn> sts = shopTerminalService.findShopTerminalSelect(findShopTerminal);
	                        if (sts != null && sts.size() > 0) {
	                            addGuidMember.setQcord(StringUtils.isNotBlank(sts.get(0).getQcord()) ? sts.get(0).getQcord() : "");
	                        }
                        }
						

						addGuidMember.setStatus(MemberStatus.NORMAL.toString());
//						findGuidMemberPage = new FindGuidMemberPage();
//						findGuidMemberPage.setImei(user.getImei());
//						List<FindGuidMemberPageReturn> guidMemberPages = guidMemberService.findGuidMembers(findGuidMemberPage);
//						if (guidMemberPages != null && guidMemberPages.size() > 0) {
//							failureMsg.append("<br/>： 手机串号" + user.getImei() + "已存在");
//							failureNum++;
//							continue;
//						}
//						addGuidMember.setImei(user.getImei());
						addGuidMember.setGender(user.getGender().contains("男") ? Gender.MALE.toString() : Gender.FEMALE.toString());
						try {
							addGuidMember.setWorkDate(DateUtils.parseDate(user.getWorkDate(), DateUtils.PATTERN_yyyy_MM_dd));
						} catch (ParseException pe) {
							logger.error("importExcel(MultipartFile, RedirectAttributes, Model)", pe); 

							failureMsg.append("<br/>： 日期格式错误");
							failureNum++;
							continue;
						}
						addGuidMember.setMemberNo(GUID.getPreUUID());
						addGuidMember.setCreateId(UserUtils.getUser().getId());
						addGuidMember.setUpdateId(UserUtils.getUser().getId());
						
						if(StringUtils.isNotEmpty(user.getAge())) {
							if(StringUtils.isNumeric(user.getAge())) {
								addGuidMember.setAge(Integer.valueOf(user.getAge()));
							} else {
								failureMsg.append("<br/>： 年龄" + user.getAge() + "格式错误");
								failureNum++;
								continue;
							}
						}

						guidMemberService.addGuidMember(addGuidMember);
						successNum++;
					} else {
						failureMsg.append("<br/>手机号 " + user.getMobile() + " 已存在; ");
						failureNum++;
					}
				} catch (Exception ex) {
					logger.error("importExcel(MultipartFile, RedirectAttributes, Model)", ex); 

					failureMsg.append("<br/>手机号 " + user.getMobile() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条店员，导入信息如下：");
			}
//			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条店员" + failureMsg);
			model.addAttribute("importMsg", "已成功导入 " + successNum + " 条店员" + failureMsg);
			logger.debug("failureMsg:" + failureMsg);
		} catch (Exception e) {
			logger.error("导入店员失败！", e);
//			addMessage(redirectAttributes, "导入店员失败！失败信息：" + e.getMessage());
			model.addAttribute("importMsg", "导入店员失败！失败信息：" + e.getMessage());
		}
//		return PAGE_VIEW_REDIRECT_GUID_REPAGE;
		String returnString = list(model, null, null, new FindGuidMemberPage(), "", redirectAttributes, null);
		logger.debug("importExcel(MultipartFile, RedirectAttributes, Model) - end - return value={}", returnString); 
		return returnString;
	}
	
	/**
	 * 
	 *
	 * 方法说明：店员导入模板下载
	 *
	 * @param response
	 * @param request
	 * @param redirectAttributes
	 * @return	excel
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("member:guid:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "店员导入模版.xlsx";
    		List<GuidImportDto> list = Lists.newArrayList(); 
    		GuidImportDto guidImportDto = new GuidImportDto();

    		guidImportDto.setShopNoMerchant("SZ11");
    		guidImportDto.setMemberName("张三");
    		guidImportDto.setMobile("18612345123");
//    		guidImportDto.setImei("35328007971");
    		guidImportDto.setNoWx("df121");
    		guidImportDto.setGender("男");
    		guidImportDto.setWorkDate("2017-01-01");
    		guidImportDto.setStatus("NORMAL");
    		guidImportDto.setMemberType("导购");
    		guidImportDto.setAreaCode("华南地区");
    		guidImportDto.setAge("24");
    		list.add(guidImportDto);
    		
    		guidImportDto = new GuidImportDto();
    		guidImportDto.setShopNoMerchant("SZ11");
    		guidImportDto.setMemberName("李四");
    		guidImportDto.setMobile("18612345123");
//    		guidImportDto.setImei("32800797153");
    		guidImportDto.setNoWx("df121");
    		guidImportDto.setGender("女");
    		guidImportDto.setWorkDate("2017-01-01");
    		guidImportDto.setStatus("NORMAL");
    		guidImportDto.setMemberType("导购");
    		guidImportDto.setAreaCode("华南地区");
    		guidImportDto.setAge("28");
    		list.add(guidImportDto);
    		new ExportExcel("店员导入模版", GuidImportDto.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
           logger.error("店员导入模板下载失败！", e);
			addMessage(redirectAttributes, "店员导入模板下载失败！失败信息："+e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_GUID_REPAGE;
    }
	
	/**
	 * 
	 *
	 * 方法说明：导购职位调动
	 *
	 * @param model
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年11月6日
	 *
	 */
	@RequestMapping(value="guidPromotion")
	public String guidPromotion(Model model,AddManagerMember addManagerMember,String shopNo,RedirectAttributes redirectAttributes){
		try {
			
			FindManagerMemberPage findManagerMemberPage=new FindManagerMemberPage();
			
			findManagerMemberPage.setMemberNoShop(shopNo);
			List<FindManagerMemberPageReturn> returnShopNo=managerMemberService.findMemberNoShop(findManagerMemberPage);
			if(returnShopNo != null && returnShopNo.size() >0){
				addMessage(redirectAttributes, "店长职位已存在,需要对原店长进行职位调动！");
			}else{
				//店员升职
				if(MemberStatus.LEAVE.toString().equals(addManagerMember.getStatus())){
					addMessage(redirectAttributes, "店员升职失败,状态必须为正常才可升职！");
				}else{
					UpdateGuidMember updateGuidMember=new UpdateGuidMember();
					updateGuidMember.setCode(addManagerMember.getCode());
					updateGuidMember.setStatus(MemberStatus.NORMAL.toString());
					guidMemberService.updateGuidMember(updateGuidMember);
					
					managerMemberService.guidPromotion(addManagerMember);
					
					//清空登录token
					List<Dict> dict =dictService.getList(UserUtils.LOGIN_TOKEN_TYPE);
					for(Dict list :dict){
						tokenService.removeToken(addManagerMember.getMemberNo(),list.getValue());	
						logger.info("已删除{}对应token", addManagerMember.getMemberNo());
					}
					
					//强制退出APP登录状态
					ForcedLogoutMessage forcedLogoutMessage = new ForcedLogoutMessage();
		    		forcedLogoutMessage.setMemberNoGm(addManagerMember.getMemberNo());
		    		forcedLogoutMessage.setMessage("您的操作权限已变更，请重新登录！");
		    		
		    		IContactsService basic = commonService.getHessianContactsService(forcedLogoutMessage.getMemberNoGm());
		    		basic.sendForcedLogoutMessage(forcedLogoutMessage);
					
					//更新个人名片
					UpdateGuidCard updateGuidCard=new UpdateGuidCard();
					updateGuidCard.setPosition("SHOP");
					updateGuidCard.setMemberNoGm(addManagerMember.getMemberNo());
					guidCardService.updateGuidCardByShop(updateGuidCard);
					addMessage(redirectAttributes, "店员升职完成！");
				}
			}
		} catch (Exception e) {
           logger.error("店员职位调动失败！", e);
           addMessage(redirectAttributes, "店员职位调动失败！,"+e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_GUID;
	
	}
	
	/**
	 * 批量删除
	 * @param ids memberNo集
	 * @return
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月21日上午10:47:29
	 */
	@RequiresPermissions("member:guid:batchDelete")
	@RequestMapping(value ="batchDelete" )
	@ResponseBody
	public CommonRepsonseDto batchDelete(@RequestParam(value = "ids[]")String[] ids){
		CommonRepsonseDto commonRepsonseDto = null;
		Integer count=null;
		try {
//			guidMemberService.batchDelete(ids);
			String merchantNo=UserUtils.getMerchantNo();
			for (String id : ids) {
				//判断此导购是否有关联客户
				count = personMemberService.findCountByMemberNoGm(merchantNo, id);
				if(count>0){
					FindGuidMember findGm=new FindGuidMember();
					findGm.setMemberNo(id);
					FindGuidMemberReturn gm = guidMemberService.findGuidMember(findGm);
					commonRepsonseDto = CommonRepsonseDto.generateFailureResponse("批量删除员工失败！"+"员工 : "+gm.getMemberName()+" 关联了"+count+"个客户,无法删除!");
					return commonRepsonseDto;
				}
				guidMemberService.delGuidMemberByMemberNo(id);
			}
			commonRepsonseDto = CommonRepsonseDto.generateSuccessResponse("成功删除"+ids.length+"个员工!");
		} catch (TsfaServiceException ex) {
			logger.error("批量删除员工失败！", ex.getExceptionInfo());
			commonRepsonseDto = CommonRepsonseDto.generateFailureResponse("批量删除员工失败！");
		} catch (Exception e) {
			logger.error("批量删除员工失败！", e);
			commonRepsonseDto = CommonRepsonseDto.generateFailureResponse("批量删除员工失败！");
		}
		return commonRepsonseDto;
	}
}
