package com.lj.oms.cm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.utils.SpringContextHolder;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.business.cm.dto.AddMaterial;
import com.lj.business.cm.dto.DelMaterial;
import com.lj.business.cm.dto.FindMaterial;
import com.lj.business.cm.dto.FindMaterialPage;
import com.lj.business.cm.dto.FindMaterialPageReturn;
import com.lj.business.cm.dto.FindMaterialReturn;
import com.lj.business.cm.dto.FindMaterialTypePage;
import com.lj.business.cm.dto.UpdateMaterial;
import com.lj.business.cm.service.IMaterialService;
import com.lj.business.cm.service.IMaterialTypeService;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;
/**
 * 
 * 
 * 类说明：  个人素材服务类
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
@RequestMapping(value = "${adminPath}/business/material")
public class MaterialController  extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);
   /**
    * 素材中心服务
    */
	@Resource
	private IMaterialService materialService;
	/**
	 * 素材类型服务
	 */
	@Resource
	private IMaterialTypeService materialTypeService;
	/**
	 * 导购服务
	 */
	@Resource
	private IGuidMemberService guidMemberService;
	
	/**
	 * 机构服务
	 */
	@Autowired
	private OfficeService officeService;
	
	private static LocalCacheSystemParamsFromCC localCacheSystemParams = SpringContextHolder.getBean(LocalCacheSystemParamsFromCC.class);
	
	private static final String PATH = "oms-web/a/business/material/viewH5?code=";
	
	/**
	 * 
	 *
	 * 方法说明：商户服务分页数据转换
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findMaterialPage
	 * @return 返回分页数据，OMS进行分页数据展现
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value = {"list", ""})
	public String list( Model model,Integer pageNo,Integer pageSize,FindMaterialPage findMaterialPage) {
		try {
		 if(pageNo!=null){
			findMaterialPage.setStart((pageNo-1)*pageSize);	
		  }
		 if(pageSize!=null){
			findMaterialPage.setLimit(pageSize);
			}
		 findMaterialPage.setMerchantNo(UserUtils.getMerchantNo());
		 if(null != findMaterialPage.getMerchantNo()){
			 findMaterialPage.setMemberNoGm(UserUtils.getUser().getId());
		 }
		 findMaterialPage.setSortDir(PageSortType.desc);
		  Page<FindMaterialPageReturn> pages = materialService.findMaterialPage(findMaterialPage);
		  List<FindMaterialPageReturn> list = Lists.newArrayList();
		  list.addAll(pages.getRows());
		  //如果当前用户角色为总管账号,商户号为空,要显示创建机构,创建人信息
			if(!list.isEmpty()){
				for (FindMaterialPageReturn findMaterialPageReturn : list) {
					Office office = officeService.findOffice(findMaterialPageReturn.getMerchantNo());
					if(null != office){
						findMaterialPageReturn.setOfficeName(office.getName());
					}
				}
			} 
		  com.ape.common.persistence.Page<FindMaterialPageReturn> page=new com.ape.common.persistence.Page<FindMaterialPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);	
		  page.initialize();
		  model.addAttribute("page",page);
		  model.addAttribute("findMaterialPage",findMaterialPage);
		  model.addAttribute("merchantNo",findMaterialPage.getMerchantNo());
		} catch (Exception e) {
		  logger.error("获取素材中心信息错误！");   
		}
	  return "modules/business/materialList";
	}
	
	/**
	 * 
	 *
	 * 方法说明：商户素材编辑页面数据
	 *
	 * @param findMaterial
	 * @param model
	 * @return 返回编辑页面数据
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("business:materialcommen:view")
	@RequestMapping(value = "form")
	public String form(FindMaterial findMaterial, Model model) {
		try {
			if(findMaterial!=null && findMaterial.getCode()!=null){
				FindMaterialReturn findMaterialReturn= materialService.findMaterial(findMaterial);
				model.addAttribute("data",findMaterialReturn);
			}			
			/*获取素材下拉数据*/
			FindMaterialTypePage findMaterialTypePage = new FindMaterialTypePage();
			findMaterialTypePage.setMerchantNo(UserUtils.getMerchantNo());
			findMaterialTypePage.setIsPublic(true);
			model.addAttribute("materialType",materialTypeService.findMaterialTypes(findMaterialTypePage));
			/*获取导购下拉数据(该商户下所有状态为正常的)*/
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMerchantNo(UserUtils.getMerchantNo());
			findGuidMemberPage.setStatus(MemberStatus.NORMAL.toString());
			model.addAttribute("guids",guidMemberService.findGuidMembers(findGuidMemberPage));
		} catch (Exception e) {
			 logger.error("获取素材中心信息错误！");  
		}	
		return "modules/business/materialForm";
	}
	
	/**
	 * 
	 *
	 * 方法说明：商户素材新增保存方法
	 *
	 * @param addMaterial
	 * @param model
	 * @param redirectAttributes
	 * @return 保存成功跳转页面
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("business:materialcommen:edit")
	@RequestMapping(value = "save")
	public String save(AddMaterial addMaterial, Model model, RedirectAttributes redirectAttributes) {
		try {
			addMaterial.setCode(GUID.getPreUUID());
			long start = addMaterial.getStartTime().getTime();
			long end = addMaterial.getEndTime().getTime();
			if(start>end){
				addMessage(redirectAttributes, "保存素材'" + addMaterial.getTitle() + "'失败");
				return "redirect:" + adminPath + "/business/material/";
			}
			//如果链接地址为空,自动生成一个链接
			if(StringUtils.isEmpty(addMaterial.getLinkUrl())){
				String uploadPath =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
				String linkUrl = uploadPath + PATH + addMaterial.getCode();
				addMaterial.setLinkUrl(linkUrl);
			}
			addMaterial.setCreateId(UserUtils.getUser().getName());
			addMaterial.setMerchantNo(UserUtils.getMerchantNo());
			addMaterial.setMemberNoGm(UserUtils.getUser().getId());
			addMaterial.setMemberNameGm(UserUtils.getUser().getName());
			materialService.addMaterial(addMaterial);
			addMessage(redirectAttributes, "保存素材'" + addMaterial.getTitle() + "'成功");
		} catch (Exception e) {
			 logger.error("保存素材中心信息错误！");  
		}
		return "redirect:" + adminPath + "/business/material/";
	}
	
	/**
	 * 
	 *
	 * 方法说明：商户素材编辑修改方法
	 *
	 * @param updateMaterial
	 * @param model
	 * @param redirectAttributes
	 * @return 保存编辑修改数据成功后跳转页面
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("business:materialcommen:edit")
	@RequestMapping(value = "edit")
	public String edit(UpdateMaterial updateMaterial, Model model, RedirectAttributes redirectAttributes) {
		try {
			long start = updateMaterial.getStartTime().getTime();
			long end = updateMaterial.getEndTime().getTime();
			if(start>end){
				addMessage(redirectAttributes, "保存素材'" + updateMaterial.getTitle() + "'失败");
				return "redirect:" + adminPath + "/business/material/";
			}
			materialService.updateMaterial(updateMaterial);
			addMessage(redirectAttributes, "保存素材'" + updateMaterial.getTitle() + "'成功");
		} catch (Exception e) {
			 logger.error("修改素材中心信息错误！");  
		}
		return "redirect:" + adminPath + "/business/material/";
	}
	
	/**
	 * 
	 *
	 * 方法说明：静态页面数据返回
	 *
	 * @param findMaterialCommen
	 * @param model
	 * @return 返回静态页面数据
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value = "view")
	public String view(FindMaterial findMaterial, Model model) {
		try {
				if(findMaterial!=null && findMaterial.getCode()!=null){
					FindMaterialPageReturn FindMaterialReturn= materialService.findMaterialByCode(findMaterial.getCode());
					model.addAttribute("data",FindMaterialReturn);
					
					if(!FindMaterialReturn.getMerchantNo().isEmpty()){
						Office company= officeService.get(officeService.get(FindMaterialReturn.getMerchantNo()));
						//公司名
						model.addAttribute("companyName",company.getName());
						//公司LOGO
						model.addAttribute("companyLogo",company.getLogo());
						//公司简介
						model.addAttribute("companyRemarks",company.getRemarks());
					}
				}
			
		} catch (Exception e) {
			 logger.error("获取素材中心静态页面信息错误！");  
		}
		return "modules/business/materialcommenView";
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：
	 *
	 * @param findMaterialCommen
	 * @param model
	 * @return 返回静态页面数据
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value = "viewH5")
	public String viewH5(FindMaterial findMaterial, Model model) {
		try {
			if(findMaterial!=null && findMaterial.getCode()!=null){
				FindMaterialPageReturn findMaterialPageReturn= materialService.findMaterialByCode(findMaterial.getCode());
				model.addAttribute("data",findMaterialPageReturn);
				
				if(!findMaterialPageReturn.getMerchantNo().isEmpty()){
					Office company= officeService.get(officeService.get(findMaterialPageReturn.getMerchantNo()));
					//公司名
					model.addAttribute("companyName",company.getName());
					//公司LOGO
					model.addAttribute("companyLogo",company.getLogo());
					//公司简介
					model.addAttribute("companyRemarks",company.getRemarks());
				}
				/*增加阅读量*/
				Integer readCount = findMaterialPageReturn.getReadCount();
				UpdateMaterial updateMaterial = new UpdateMaterial();
				updateMaterial.setCode(findMaterialPageReturn.getCode());
				updateMaterial.setRespondNum(readCount==null?0:readCount+1); 
				materialService.updateMaterial(updateMaterial);
			}
		} catch (Exception e) {
			logger.error("获取素材中心静态页面信息错误！"); 
		}		
		return "modules/business/materialcommenH5";
	}
	
	@RequestMapping(value = "delete")
	public String delete(String code,RedirectAttributes redirectAttributes){
		try {
			DelMaterial delMaterial = new DelMaterial();
			delMaterial.setCode(code);
			materialService.delMaterial(delMaterial);
		} catch (Exception e) {
			logger.error("删除素材错误!", e);
		}
		return "redirect:" + adminPath + "/business/material/";
	}
}
