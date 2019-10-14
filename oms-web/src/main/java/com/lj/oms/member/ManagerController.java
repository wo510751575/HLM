package com.lj.oms.member;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.AddManagerMember;
import com.lj.business.member.dto.FindManagerMember;
import com.lj.business.member.dto.FindManagerMemberPage;
import com.lj.business.member.dto.FindManagerMemberPageReturn;
import com.lj.business.member.dto.FindManagerMemberReturn;
import com.lj.business.member.dto.UpdateManagerMember;
import com.lj.business.member.emus.Gender;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.emus.MemberType;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.Validator;
import com.lj.oms.utils.excel.ExportExcel;
import com.lj.oms.utils.excel.ImportExcel;
import com.lj.oms.utils.excel.dto.ManagerDto;
import com.lj.oms.utils.excel.dto.ManagerMemberAreaDto;

/**
 * 
 * 
 * 类说明：区域经理Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月14日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/manager")
public class ManagerController  extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

	/** 区域经理列表页面 **/
	private final static String PAGE_VIEW_MANAGER_MEMBER_LIST = "modules/member/managerList";
	/** 区域经理编辑页面 **/
	private final static String PAGE_VIEW_MANAGER_MEMBER_FORM = "modules/member/managerForm";
	/** 重定向到区域经理列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_MANAGER_MEMBER = "redirect:" + Global.getAdminPath() + "/member/manager";
	/** 重定向到区域经理列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_MANAGER_MEMBER_REPAGE = "redirect:" + Global.getAdminPath() + "/member/manager/?repage";

	@Resource
	private IManagerMemberService managerMemberService;		//管理人员服务
//	@Resource
//	private IShopService shopService;						//门店服务

	/**
	 * 
	 *
	 * 方法说明：区域经理人员列表
	 * SHOP ：店长
	 * BOSS	：BOSS
	 * AREA_MAN：区域经理
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findManagerMemberPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("member:manager:view")
	@RequestMapping(value = {"list", ""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindManagerMemberPage findManagerMemberPage) {
		try {
			findManagerMemberPage.setMemberNoMerchant(UserUtils.getMerchantNo());
			if(pageNo!=null){
				findManagerMemberPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findManagerMemberPage.setLimit(pageSize);
			}
			findManagerMemberPage.setStartTime(DateUtils.getDateByFirstSeconds(findManagerMemberPage.getStartTime()));
			findManagerMemberPage.setEndTime(DateUtils.getDateByLastSeconds(findManagerMemberPage.getEndTime()));
			findManagerMemberPage.setMemberType(MemberType.AREA_MAN.toString());
			Page<FindManagerMemberPageReturn> pageDto= managerMemberService.findManagerMemberPage(findManagerMemberPage);
			List<FindManagerMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
//		    System.out.println(pageDto);
			
		  	com.ape.common.persistence.Page<FindManagerMemberPageReturn> page	=new com.ape.common.persistence.Page<FindManagerMemberPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
		  	page.initialize();
			model.addAttribute("page",page);
			//参数
			model.addAttribute("memberStatus",MemberStatus.values());
			model.addAttribute("genders",Gender.values());
			model.addAttribute("paramManager",findManagerMemberPage);
			model.addAttribute("memberStatus",MemberStatus.values());
		} catch (Exception e) {
			logger.error("获取管理人员信息错误！", e);
		}
		return PAGE_VIEW_MANAGER_MEMBER_LIST;
	}
	  /**
	   * 
	   *
	   * 方法说明：获取编辑页面数据
	   *
	   * @param model
	   * @param findManagerMember
	   * @return
	   *
	   * @author 罗书明 CreateDate: 2017年7月19日
	   *
	   */
	@RequiresPermissions("member:manager:view")
	@RequestMapping(value="form")
	 public String form(Model model,FindManagerMember findManagerMember){
		if(findManagerMember!=null && StringUtils.isNotBlank(findManagerMember.getCode())){
			try {
				 FindManagerMemberReturn  findManagerMemberReturn =	managerMemberService.findManagerMember(findManagerMember);
				 model.addAttribute("data",findManagerMemberReturn);
				 
				 //分店与地址下拉列表
//				 FindShop findShop = CcUtils.shopFilter();
//				 List<FindShopPageReturn> shops= shopService.findShops(findShop);
//				 model.addAttribute("shops",shops);
				 model.addAttribute("genders",Gender.values());
			} catch (Exception e) {
				logger.error("获取管理人员信息错误！", e);
			}
		}	
		return PAGE_VIEW_MANAGER_MEMBER_FORM;
	 }
	/**
	 * 
	 *
	 * 方法说明：数据修改方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param updateManagerMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月19日
	 *
	 */
	@RequiresPermissions("member:manager:edit")
	@RequestMapping(value="edit")
	public String edit(Model model,RedirectAttributes redirectAttributes,UpdateManagerMember updateManagerMember){
		try {
			managerMemberService.updateManagerMember(updateManagerMember);
			addMessage(redirectAttributes, "修改区域经理'" + updateManagerMember.getMemberName() + "'成功");
		} catch (Exception e) {
			logger.error("修改管理人员信息错误！", e);
		}		
		return  PAGE_VIEW_REDIRECT_MANAGER_MEMBER;
	}
	/**
	 * 
	 *
	 * 方法说明：区域经理数据导出
	 *
	 * @param response
	 * @param findManagerMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月19日
	 *
	 */
	@RequiresPermissions("member:manager:view")
    @RequestMapping(value = "export")
    public String export(HttpServletResponse response,FindManagerMemberPage findManagerMemberPage, RedirectAttributes redirectAttributes) {
		try {
			findManagerMemberPage.setMemberNoMerchant(UserUtils.getMerchantNo());
    		findManagerMemberPage.setMemberType(MemberType.AREA_MAN.toString());
			findManagerMemberPage.setStartTime(DateUtils.getDateByFirstSeconds(findManagerMemberPage.getStartTime()));
			findManagerMemberPage.setEndTime(DateUtils.getDateByLastSeconds(findManagerMemberPage.getEndTime()));
    		List<FindManagerMemberPageReturn> list=managerMemberService.findManagerMemberExport(findManagerMemberPage);
    		managerMemberConvert(list);
    		String fileName = "区域经理数据导出.xlsx";
    		new ExportExcel("区域经理数据导出", ManagerMemberAreaDto.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			logger.error("区域经理数据导出失败！", e);
			addMessage(redirectAttributes, "区域经理数据导出失败！失败信息："+e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_MANAGER_MEMBER_REPAGE;
    }
	
	private void managerMemberConvert(List<FindManagerMemberPageReturn> list) {
		if(null != list && list.size() > 0){
			for(FindManagerMemberPageReturn mm : list){
				String sex = mm.getSex();
				if(StringUtils.isNoneBlank(sex)){
    				mm.setSex(Gender.valueOf(sex).getName());
  			  	}
				
				String memberType = mm.getMemberType();
				if(StringUtils.isNoneBlank(memberType)){
    				mm.setMemberType(MemberType.valueOf(memberType).getName());
  			  	}
			}
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：检查区域经理登录手机是否唯一
	 *
	 * @param oldMobile
	 * @param mobile
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月21日
	 *
	 */
	@ResponseBody
	@RequiresPermissions("member:manager:edit")
	@RequestMapping(value = "checkMobileShop")
	public String checkMobileManager(String oldMobile, String mobile) {
		try {
			if (mobile !=null && mobile.equals(oldMobile)) {
				return CommonConstant.TRUE;
			} 
			FindManagerMemberPage record = new FindManagerMemberPage();
			record.setMobile(mobile);			
			List<FindManagerMemberPageReturn> list= managerMemberService.findManagerMembers(record);
			if(list !=null && list.size()==0){
				return CommonConstant.TRUE;
			}
			return CommonConstant.FALSE;
		} catch (Exception e) {
			logger.error("检查区域经理登录手机是否唯一失败", e);
			return CommonConstant.TRUE;
		}
	}

	/**
	 * 
	 *
	 * 方法说明：区域经理数据导入
	 *
	 * @param file
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月21日
	 *
	 */
	@RequiresPermissions("member:manager:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importExcel(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);

			List<ManagerDto> list = ei.getDataList(ManagerDto.class);
			for (ManagerDto user : list) {
				try {
					/*
					 * if(user.getShopNoMerchant()==null||StringUtils.isBlank(user
					 * .getShopNoMerchant())){ continue; }
					 */
					// 处理excel转义的手机号码
					user.setMobile(DictUtils.excelChage(user.getMobile()));
					// user.setNoWx(DictUtils.excelChage(user.getNoWx()));

					/* 校验手机号格式 */
					if (!Validator.isMobile(user.getMobile())) {
						failureMsg.append("<br/>客户手机号： " + user.getMobile() + "格式不正确; ");
						failureNum++;
						continue;
					}

					if (CommonConstant.TRUE.equals(checkMobileManager("", user.getMobile()))) {
						// 默认密码手机后6位
						String pwd = user.getMobile().substring(user.getMobile().length() - 6);
						AddManagerMember member = new AddManagerMember();
						member.setMemberName(user.getMemberName());
						member.setPwd(MD5.encryptByMD5(pwd));
						member.setMobile(user.getMobile());
						member.setMemberNoMerchant(UserUtils.getMerchantNo());
						member.setMemberNameMerchant(UserUtils.getUser().getCompany().getName());

						/*
						 * //关联门店 duanzhipeng 2017-07-14 start FindShop
						 * findShop= new FindShop();
						 * findShop.setMemberNoMerchant
						 * (UserUtils.getMerchantNo());
						 * findShop.setShopNoMerchant(user.getShopNoMerchant());
						 * List<FindShopPageReturn> shopReturns=
						 * shopService.findShops(findShop); if(shopReturns!=null
						 * && shopReturns.size()>0){ FindShopPageReturn
						 * findShopReturn= shopReturns.get(0);
						 * member.setMemberNoShop(findShopReturn.getShopNo());
						 * member
						 * .setMemberNameShop(findShopReturn.getShopName());
						 * }else{
						 * failureMsg.append("<br/>： 门店代码"+user.getShopNoMerchant
						 * ()+"不存在！; "); failureNum++; continue; }
						 */
						member.setStatus(MemberStatus.NORMAL.toString());
						member.setSex(user.getSex().equals("男") ? Gender.MALE.toString() : Gender.FEMALE.toString());
						member.setWorkDate(DateUtils.parseDate(user.getWorkDate(), DateUtils.PATTERN_yyyy_MM_dd));
						member.setAreaCode(DictUtils.getDictValue(user.getAreaCode(), "erp_dict_1", ""));
						member.setAreaName(user.getAreaCode());
						member.setMemberNo(GUID.getPreUUID());
						FindManagerMemberPage record = new FindManagerMemberPage();
						record.setNoWx(user.getNoWx());
						List<FindManagerMemberPageReturn> lists = managerMemberService.findManagerMembers(record);
						if (lists.size() > 0) {
							String noWx = lists.get(0).getNoWx();
							if (noWx.equals(user.getNoWx())) {
								failureMsg.append("微信号已存在！");
								failureNum++;
								continue;
							}
						}
						member.setNoWx(user.getNoWx());
						member.setMemberType(MemberType.AREA_MAN);
						member.setCreateId(UserUtils.getUser().getId());
						member.setUpdateId(UserUtils.getUser().getId());
						member.setUpdateDate(new Date());
						managerMemberService.addManagerMember(member);
						successNum++;
					} else {
						failureMsg.append("<br/>手机号 " + user.getMobile() + " 已存在; ");
						failureNum++;
					}
				  }catch (Exception ex) {
					  failureMsg.append("<br/>手机号 "+user.getMobile()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条店员，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条店员" + failureMsg);
		} catch (Exception e) {
			logger.error("导入店员失败！", e);
			addMessage(redirectAttributes, "导入店员失败！失败信息：" + e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_MANAGER_MEMBER_REPAGE;
    }
	
	/**
	 * 
	 *
	 * 方法说明：区域经理数据导入模板
	 *
	 * @param response
	 * @param request
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月21日
	 *
	 */
	@RequiresPermissions("member:manager:edit")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "区域经理导入模版.xlsx";
    		List<ManagerDto> list = Lists.newArrayList(); 
    		ManagerDto managerDto = new ManagerDto();

    		managerDto.setMemberName("张三");
    		managerDto.setMobile("18612345123");
    		managerDto.setSex("男");
    		managerDto.setWorkDate("2017-01-01");
    		managerDto.setMemberType("区域经理");
    		managerDto.setAreaCode("华南地区");
    		list.add(managerDto);
    		
    		managerDto = new ManagerDto();
    		managerDto.setMemberName("李四");
    		managerDto.setMobile("18612345123");
    		managerDto.setSex("男");
    		managerDto.setWorkDate("2017-01-01");
    		managerDto.setMemberType("区域经理");
    		managerDto.setAreaCode("华南地区");
    		
    		list.add(managerDto);
    		new ExportExcel("区域经理导入模版", ManagerDto.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "区域经理导入模板下载失败！失败信息："+e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_MANAGER_MEMBER_REPAGE;
    }
}
