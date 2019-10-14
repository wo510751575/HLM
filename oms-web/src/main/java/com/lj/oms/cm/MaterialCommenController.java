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
import com.lj.business.cm.dto.AddMaterialCommen;
import com.lj.business.cm.dto.DelMaterialCommen;
import com.lj.business.cm.dto.FindMaterialCommen;
import com.lj.business.cm.dto.FindMaterialCommenPage;
import com.lj.business.cm.dto.FindMaterialCommenPageReturn;
import com.lj.business.cm.dto.FindMaterialCommenReturn;
import com.lj.business.cm.dto.FindMaterialTypePage;
import com.lj.business.cm.dto.UpdateMaterialCommen;
import com.lj.business.cm.service.ICommonMaterialTypeService;
import com.lj.business.cm.service.IMaterialCommenService;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Dict;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.DictService;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：公用素材服务类
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
@RequestMapping(value = "${adminPath}/business/materialcommen")
public class MaterialCommenController  extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialCommenController.class);
	
    /**
     * 公用素材服务
     */
	@Resource
	private IMaterialCommenService materialcommenService;
	/**
	 * 公司素材类型服务
	 */
	@Resource
	private ICommonMaterialTypeService commonMaterialTypeService;
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
	/**
	 * 终端服务
	 */
//	@Autowired
//	private IShopService shopService;
	
	@Autowired
	private DictService dictService;		//字典
	
	private static LocalCacheSystemParamsFromCC localCacheSystemParams = SpringContextHolder.getBean(LocalCacheSystemParamsFromCC.class);
	
	private static final String PATH = "oms-web/a/business/materialcommen/viewH5?code=";
	
	/**
	 * 
	 *
	 * 方法说明：公用素材分页数据转换
	 *
	 * @param findMaterialCommenPage
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return  返回分页数据,OMS展现分页数据
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value = {"list", ""})
	public String list(FindMaterialCommenPage findMaterialCommenPage, Model model,Integer pageNo,Integer pageSize) {
		//FindShop findShop=new FindShop();
	  try {
		  if(pageNo!=null){
				 findMaterialCommenPage.setStart((pageNo-1)*pageSize);
			}
		  if(pageSize!=null){
				 findMaterialCommenPage.setLimit(pageSize);
			 }
		  //分页转换
		  findMaterialCommenPage.setMerchantNo(UserUtils.getMerchantNo());
		  findMaterialCommenPage.setSortDir(PageSortType.desc);
		  Page<FindMaterialCommenPageReturn> pages = materialcommenService.findMaterialCommenPage(findMaterialCommenPage);
		  List<FindMaterialCommenPageReturn> list = Lists.newArrayList();
		  list.addAll(pages.getRows());
		  //如果当前用户角色为总管账号,商户号为空,要显示创建机构,创建人信息
			if(!list.isEmpty()){
				for (FindMaterialCommenPageReturn findMaterialCommenPageReturn : list) {
					Office office = officeService.findOffice(findMaterialCommenPageReturn.getMerchantNo());
					if(null != office){
						findMaterialCommenPageReturn.setOfficeName(office.getName());
					}
				}
			} 
		 
		  com.ape.common.persistence.Page<FindMaterialCommenPageReturn> page=new com.ape.common.persistence.Page<FindMaterialCommenPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);	
		  page.initialize();
		  model.addAttribute("page",page);
		  model.addAttribute("merchantNo",findMaterialCommenPage.getMerchantNo());
		  
		  /*获取素材类型下拉数据*/
		  FindMaterialTypePage findMaterialTypePage = new FindMaterialTypePage();
		  findMaterialTypePage.setMerchantNo(UserUtils.getUser().getCompany().getId());
		  model.addAttribute("materialType",commonMaterialTypeService.findMateriaTypeList(findMaterialTypePage));
			
		  model.addAttribute("findMaterialCommenPage",findMaterialCommenPage);
	 } catch (Exception e) {
		 logger.error("获取公用素材信息错误！");
	 } 		
		return "modules/business/materialcommenList";
	}
	
	/**
	 * 
	 *
	 * 方法说明：公用素材编辑页面数据展现
	 *
	 * @param findMaterialCommen
	 * @param model
	 * @return 返回编辑页面数据
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("business:materialcommen:view")
	@RequestMapping(value = "form")
	public String form(FindMaterialCommen findMaterialCommen,String tempId, Model model) {
		try {
		  if(findMaterialCommen!=null && findMaterialCommen.getCode()!=null){
			FindMaterialCommenReturn findMaterialCommenReturn= materialcommenService.findMaterialCommen(findMaterialCommen);
			model.addAttribute("data",findMaterialCommenReturn);
			}		
			//分店与地址下拉列表
//		  	FindShop findShop= CcUtils.shopFilter();
//			List<FindShopPageReturn> shops= shopService.findShops(findShop);
//			 List<FindShopPageReturn> shopType= shopService.findShopType(findShop);
//			 model.addAttribute("shops",shops);
//			 model.addAttribute("shopTypes",shopType);
			  /*获取素材类型下拉数据*/
			  FindMaterialTypePage findMaterialTypePage = new FindMaterialTypePage();
			  findMaterialTypePage.setMerchantNo(UserUtils.getMerchantNo());
			  model.addAttribute("materialType",commonMaterialTypeService.findMateriaTypeList(findMaterialTypePage));
			if(StringUtils.isEmpty(tempId)){
				return "modules/business/materialcommenForm";
			}else{
				model.addAttribute("temp",dictService.get(tempId));
				return "modules/business/materialcommenTempForm";
			}
		} catch (Exception e) {
			 logger.error("获取公用素材信息错误！");
		}
		return "modules/business/materialcommenForm";
	}
	
//	@RequestMapping(value="froms")
//	@ResponseBody
//	public List<FindShopPageReturn> froms(String shopTypes){
//		if(shopTypes!=null){
//			FindShop findShop = new FindShop();
//			findShop.setMemberNoMerchant(UserUtils.getUser().getCompany().getId());	
//			findShop.setShopType(shopTypes);
//			List<FindShopPageReturn> shops= shopService.findShops(findShop);
//			  return shops;
//		}
//		return null;
//	}
	
	/**
	 * 
	 *
	 * 方法说明：公用素材新增数据保存方法
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
	public String save(AddMaterialCommen addMaterialCommen, Model model, RedirectAttributes redirectAttributes) {
		try {
			long start = addMaterialCommen.getStartTime().getTime();
			long end = addMaterialCommen.getEndTime().getTime();
			if(start>end){
				addMessage(redirectAttributes, "保存素材'" + addMaterialCommen.getTitle() + "'失败");
				return "redirect:" + adminPath + "/business/materialcommen/";
			}
			addMaterialCommen.setCode(GUID.getPreUUID());
			//如果链接地址为空,自动生成一个链接
			if(StringUtils.isEmpty(addMaterialCommen.getLinkUrl())){
				String uploadPath =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
				String linkUrl = uploadPath + PATH + addMaterialCommen.getCode();
				addMaterialCommen.setLinkUrl(linkUrl);
			}
			addMaterialCommen.setMerchantNo(UserUtils.getMerchantNo());
			addMaterialCommen.setMerchantName(UserUtils.getUser().getCompany().getName());
			addMaterialCommen.setCreateId(UserUtils.getUser().getName());
			materialcommenService.addMaterialCommen(addMaterialCommen);
			addMessage(redirectAttributes, "保存素材'" + addMaterialCommen.getTitle() + "'成功");
		} catch (Exception e) {
			 logger.error("保存公用素材信息错误！");
		}
		return "redirect:" + adminPath + "/business/materialcommen/";
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑修改公用素材信息保存
	 *
	 * @param updateMaterial
	 * @param model
	 * @param redirectAttributes
	 * @return 保存成功跳转页面
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("business:materialcommen:edit")
	@RequestMapping(value = "edit")
	public String edit(UpdateMaterialCommen updateMaterial, Model model, RedirectAttributes redirectAttributes) {
		try {
			long start = updateMaterial.getStartTime().getTime();
			long end = updateMaterial.getEndTime().getTime();
			if(start>end){
				addMessage(redirectAttributes, "保存素材'" + updateMaterial.getTitle() + "'失败");
				return "redirect:" + adminPath + "/business/materialcommen/";
			}
			materialcommenService.updateMaterialCommen(updateMaterial);
			addMessage(redirectAttributes, "修改素材'" + updateMaterial.getTitle() + "'成功");
		} catch (Exception e) {
			 logger.error("修改公用素材信息错误！");
		}	
		return "redirect:" + adminPath + "/business/materialcommen/";
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
	public String view(FindMaterialCommen findMaterialCommen, Model model) {
		try {
			if(findMaterialCommen!=null && findMaterialCommen.getCode()!=null){
				FindMaterialCommenReturn findMaterialCommenReturn= materialcommenService.findMaterialCommen(findMaterialCommen);
				model.addAttribute("data",findMaterialCommenReturn);
				/*获取模版*/
				if(StringUtils.isNotEmpty(findMaterialCommenReturn.getTempId())){
					model.addAttribute("temp",dictService.get(findMaterialCommenReturn.getTempId()));
				}
				//luoshuming 2017/10/14  产品要求隐藏
	/*			if(!findMaterialCommenReturn.getMerchantNo().isEmpty()){
					Office company= officeService.get(officeService.get(findMaterialCommenReturn.getMerchantNo()));
					//公司名
					model.addAttribute("companyName",company.getName());
					//公司LOGO
					model.addAttribute("companyLogo",company.getLogo());
					//公司简介
					model.addAttribute("companyRemarks",company.getRemarks());
				}*/
			}
			
			//地址 	TODO获取附近终端
//			model.addAttribute("addr","深圳市龙华新区民治大道785号");
			//电话	TODO
//			model.addAttribute("tel","400-8008001");
		} catch (Exception e) {
			 logger.error("获取公用素材信息错误！");
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
	public String viewH5(FindMaterialCommen findMaterialCommen, Model model) {
		try {
			if(findMaterialCommen!=null && findMaterialCommen.getCode()!=null){
				FindMaterialCommenReturn findMaterialCommenReturn= materialcommenService.findMaterialCommen(findMaterialCommen);
				model.addAttribute("data",findMaterialCommenReturn);
				
				/*获取模版*/
				if(StringUtils.isNotEmpty(findMaterialCommenReturn.getTempId())){
					model.addAttribute("temp",dictService.get(findMaterialCommenReturn.getTempId()));
				}
	/*			
				if(!findMaterialCommenReturn.getMerchantNo().isEmpty()){
					Office company= officeService.get(officeService.get(findMaterialCommenReturn.getMerchantNo()));
					//公司名
					model.addAttribute("companyName",company.getName());
					//公司LOGO
					model.addAttribute("companyLogo",company.getLogo());
					//公司简介
					model.addAttribute("companyRemarks",company.getRemarks());
				}
				*/
				/*增加阅读量*/
				Integer readCount = findMaterialCommenReturn.getRespondNum();
				UpdateMaterialCommen updateMaterialCommen = new UpdateMaterialCommen();
				updateMaterialCommen.setCode(findMaterialCommenReturn.getCode());
				updateMaterialCommen.setRespondNum(readCount==null?0:readCount+1); 
				materialcommenService.updateMaterialCommen(updateMaterialCommen);
				
				//地址 	TODO获取附近终端
//				model.addAttribute("addr",company.get);
				//电话	TODO
//				model.addAttribute("tel","400-8008001");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "modules/business/materialcommenH5";
	}
	
	
	@RequestMapping(value = "setting")
	public String setting(Model model) {
		try {
			Dict dict = new Dict();
			dict.setType("material_temp");
			model.addAttribute("list", dictService.findList(dict));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "modules/business/materialcommenSetting";
	}
	
	@RequestMapping(value = "settingForm")
	public String settingForm(String id,Model model) {
		try {
			model.addAttribute("data", dictService.get(id));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "modules/business/materialcommenSettingForm";
	}
	
	@RequestMapping(value = "settingSave")
	public String settingSave(Dict dict, Model model, RedirectAttributes redirectAttributes) {
		try {
			dict.setType("material_temp");
			dictService.save(dict);
			addMessage(redirectAttributes, "保存素材模版'" + dict.getLabel() + "'成功");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "redirect:" + adminPath + "/business/materialcommen/setting/";
	}
	
	@RequestMapping(value = "delete")
	public String delete(String code,RedirectAttributes redirectAttributes){
		try {
			DelMaterialCommen delMaterialCommen = new DelMaterialCommen();
			delMaterialCommen.setCode(code);
			materialcommenService.delMaterialCommen(delMaterialCommen);
		} catch (Exception e) {
			logger.error("删除素材错误!", e);
		}
		return "redirect:" + adminPath + "/business/materialcommen/";
	}
	
}
