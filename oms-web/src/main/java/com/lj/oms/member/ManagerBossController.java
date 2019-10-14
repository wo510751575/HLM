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
import com.lj.oms.common.BaseController;
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
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.Validator;
import com.lj.oms.utils.excel.ExportExcel;
import com.lj.oms.utils.excel.ImportExcel;
import com.lj.oms.utils.excel.dto.ManagerBossDto;
import com.lj.oms.utils.excel.dto.ManagerMemberAreaDto;

/**
 * 
 * 
 * 类说明：总监级别以上人员导入导出
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年9月5日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/managerBoss")
public class ManagerBossController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerBossController.class);

	/** 总监级别以上人员列表页面 **/
	private final static String PAGE_VIEW_MANAGER_MEMBER_BOSS_LIST = "modules/member/managermemberBossList";
	/** 总监级别以上人员编辑页面 **/
	private final static String PAGE_VIEW_MANAGER_MEMBER_BOSS_FORM = "modules/member/managermemberBossForm";
	/** 重定向到总监级别以上人员列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_MANAGER_MEMBER_BOSS = "redirect:" + Global.getAdminPath() + "/member/managerBoss";
	/** 重定向到总监级别以上人员列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_MANAGER_MEMBER_BOSS_REPAGE = "redirect:" + Global.getAdminPath() + "/member/managerBoss/?repage";
	
	@Resource
	private IManagerMemberService managerMemberService;
	/**
	 * 
	 *
	 * 方法说明：boss列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findManagerMemberPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月5日
	 *
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindManagerMemberPage findManagerMemberPage){
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
			findManagerMemberPage.setMemberType(MemberType.BOSS.toString());
			Page<FindManagerMemberPageReturn> pageDto= managerMemberService.findManagerMemberPage(findManagerMemberPage);
			List<FindManagerMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			
		  	com.ape.common.persistence.Page<FindManagerMemberPageReturn> page	=new com.ape.common.persistence.Page<FindManagerMemberPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
		  	page.initialize();
			model.addAttribute("page",page);
			//参数
			model.addAttribute("memberStatus",MemberStatus.values());
			model.addAttribute("genders",Gender.values());
			model.addAttribute("paramManager",findManagerMemberPage);
			model.addAttribute("memberStatus",MemberStatus.values());
			
			
		} catch (Exception e) {
			logger.error("获取管理员信息错误！", e);
		}
		return PAGE_VIEW_MANAGER_MEMBER_BOSS_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑页面
	 *
	 * @param model
	 * @param findManagerMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月5日
	 *
	 */
	@RequestMapping(value="form")
	 public String form(Model model,FindManagerMember findManagerMember){
		if(findManagerMember!=null && StringUtils.isNotBlank(findManagerMember.getCode())){
			try {
				 FindManagerMemberReturn  findManagerMemberReturn =	managerMemberService.findManagerMember(findManagerMember);
				 model.addAttribute("data",findManagerMemberReturn);
			} catch (Exception e) {
				logger.error("获取管理员信息错误！", e);
			}	
		}
		model.addAttribute("genders",Gender.values());
		return PAGE_VIEW_MANAGER_MEMBER_BOSS_FORM;
	 }
	
	/**
	 * 
	 *
	 * 方法说明：修改方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param updateManagerMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月5日
	 *
	 */
	@RequestMapping(value="edit")
	public String edit(Model model,RedirectAttributes redirectAttributes,UpdateManagerMember updateManagerMember){
		try {
			managerMemberService.updateManagerMember(updateManagerMember);
			addMessage(redirectAttributes, "修改总监" + updateManagerMember.getMemberName() + "信息成功");
		} catch (Exception e) {
			logger.error("新增管理员信息错误！", e);
		}		
		return PAGE_VIEW_REDIRECT_MANAGER_MEMBER_BOSS;
	}
	
	/**
	 * 
	 *
	 * 方法说明：总监数据导出
	 *
	 * @param response
	 * @param findManagerMemberPage
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月5日
	 *
	 */
    @RequestMapping(value = "export")
    public String export(HttpServletResponse response,FindManagerMemberPage findManagerMemberPage, RedirectAttributes redirectAttributes) {
		try {
			findManagerMemberPage.setMemberNoMerchant(UserUtils.getMerchantNo());
    		findManagerMemberPage.setMemberType(MemberType.BOSS.toString());
			findManagerMemberPage.setStartTime(DateUtils.getDateByFirstSeconds(findManagerMemberPage.getStartTime()));
			findManagerMemberPage.setEndTime(DateUtils.getDateByLastSeconds(findManagerMemberPage.getEndTime()));	
    		List<FindManagerMemberPageReturn> list=managerMemberService.findManagerMemberExport(findManagerMemberPage);
    		managerMemberConvert(list);
    		String fileName = "总监数据导出.xlsx";
    		new ExportExcel("总监数据导出", ManagerMemberAreaDto.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "总监数据导出失败！失败信息："+e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_MANAGER_MEMBER_BOSS_REPAGE;
    }

	
	private void managerMemberConvert(List<FindManagerMemberPageReturn> list) {
		if (null != list && list.size() > 0) {
			for (FindManagerMemberPageReturn mm : list) {

				String sex = mm.getSex();
				if (StringUtils.isNoneBlank(sex)) {
					mm.setSex(Gender.valueOf(sex).getName());
				}

				String memberType = mm.getMemberType();
				if (StringUtils.isNoneBlank(memberType)) {
					mm.setMemberType(MemberType.valueOf(memberType).getName());
				}

			}
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：检查总监登录手机号码唯一性
	 *
	 * @param oldMobile
	 * @param mobile
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月5日
	 *
	 */
	@ResponseBody
	@RequiresPermissions("member:guid:edit")
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
			logger.error("检查总监登录手机号码唯一性！", e);
			return CommonConstant.TRUE;
		}
	}

	/**
	 * 
	 *
	 * 方法说明：总监数据导入
	 *
	 * @param file
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月5日
	 *
	 */
	@RequiresPermissions("member:guid:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importExcel(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);

			List<ManagerBossDto> list = ei.getDataList(ManagerBossDto.class);
			for (ManagerBossDto user : list) {
				try {

					/*
					 * if(!user.getMemberType().equals(MemberType.BOSS.toString()
					 * )){ failureMsg.append("职位错误,应是总监级别以上的员工！"); failureNum++;
					 * continue; }
					 */

					// 处理excel转义的手机号码
					user.setMobile(DictUtils.excelChage(user.getMobile()));
					user.setImei(DictUtils.excelChage(user.getImei()));
					/* 校验手机号格式 */
					if (!Validator.isMobile(user.getMobile())) {
						failureMsg.append("<br/>总监手机号： " + user.getMobile() + "格式不正确; ");
						failureNum++;
						continue;
					}

					if ("true".equals(checkMobileManager("", user.getMobile()))) {
						// 默认密码手机后6位
						String pwd = user.getMobile().substring(user.getMobile().length() - 6);
						AddManagerMember member = new AddManagerMember();
						member.setMemberName(user.getMemberName());
						member.setPwd(MD5.encryptByMD5(pwd));
						member.setMobile(user.getMobile());
						member.setMemberNoMerchant(UserUtils.getMerchantNo());
						member.setMemberNameMerchant(UserUtils.getUser().getCompany().getName());
						member.setStatus(MemberStatus.NORMAL.toString());
						member.setSex(user.getSex().equals("男") ? Gender.MALE.toString() : Gender.FEMALE.toString());
						member.setWorkDate(DateUtils.parseDate(user.getWorkDate(), DateUtils.PATTERN_yyyy_MM_dd));
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
						FindManagerMemberPage records = new FindManagerMemberPage();
						records.setImei(user.getImei());
						List<FindManagerMemberPageReturn> pageReturns = managerMemberService.findManagerMembers(records);
						if (lists != null && lists.size() > 0) {
							String imei = pageReturns.get(0).getImei();
							if (imei.equals(user.getImei())) {
								failureMsg.append("<br/>： 手机串号" + user.getImei() + "已存在");
								failureNum++;
								continue;
							}
						}
						member.setImei(user.getImei());
						member.setNoWx(user.getNoWx());
						member.setMemberType(MemberType.BOSS);
						member.setCreateId(UserUtils.getUser().getId());
						member.setUpdateId(UserUtils.getUser().getId());
						member.setUpdateDate(new Date());
						managerMemberService.addManagerMember(member);
						successNum++;
					} else {
						failureMsg.append("<br/>手机号 " + user.getMobile() + " 已存在; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>手机号 " + user.getMobile() + " 导入失败：");
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条数据，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条总监" + failureMsg);
		} catch (Exception e) {
			logger.error("导入总监失败！", e);
			addMessage(redirectAttributes, "导入总监失败！失败信息：" + e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_MANAGER_MEMBER_BOSS_REPAGE;
    }
	/**
	 * 
	 *
	 * 方法说明：总监数据导入模板
	 *
	 * @param response
	 * @param request
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月5日
	 *
	 */
	@RequiresPermissions("member:guid:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "总监导入模版.xlsx";
    		List<ManagerBossDto> list = Lists.newArrayList(); 
    		ManagerBossDto managerBossDto = new ManagerBossDto();

    		managerBossDto.setMemberName("张三");
    		managerBossDto.setMobile("18612345123");
    		managerBossDto.setImei("08615115844");
    		managerBossDto.setSex("男");
    		managerBossDto.setWorkDate("2017-01-01");
    		managerBossDto.setMemberType("总监");
    		managerBossDto.setNoWx("abc360dd");
    		list.add(managerBossDto);
    		
    		managerBossDto = new ManagerBossDto();
    		managerBossDto.setMemberName("李四");
    		managerBossDto.setMobile("18612345123");
    		managerBossDto.setImei("08615115844");
    		managerBossDto.setSex("男");
    		managerBossDto.setWorkDate("2017-01-01");
    		managerBossDto.setMemberType("总监");
    		managerBossDto.setNoWx("abc360dd");
    		
    		list.add(managerBossDto);
    		new ExportExcel("总监数据导入模版", ManagerBossDto.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			logger.error("总监数据导入模板下载失败！", e);
			addMessage(redirectAttributes, "总监数据导入模板下载失败！失败信息："+e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_MANAGER_MEMBER_BOSS_REPAGE;
    }
	
}
