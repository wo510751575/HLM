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

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.base.core.util.StringUtils;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPage;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPageReturn;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialReturn;
import com.lj.business.cm.service.IGuidIntroduceMaterialService;
import com.lj.business.cm.service.IMaterialTypeService;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.ManagerMemberDto;
import com.lj.business.member.dto.ManagerMemberReturnDto;
import com.lj.business.member.service.IGuidCardService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Dict;
import com.lj.oms.service.sys.DictService;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：导购个人介绍素材服务类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 梅宏博
 *   
 * CreateDate: 2017年9月19日
 */
@Controller
@RequestMapping(value = "${adminPath}/business/materialIntroduce")
public class MaterialIntroduceController  extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialIntroduceController.class);
	
    /**
     * 导购个人介绍素材服务
     */
	@Resource
	private IGuidIntroduceMaterialService guidIntroduceMaterialService;
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
	/**
	 * 终端服务
	 */
//	@Autowired
//	private IShopService shopService;
	
	@Autowired
	private DictService dictService;		//字典
	
	/**
	 * 导购名片服务
	 */
	@Resource
	IGuidCardService guidCardService;
	
	/**
	 * 管理人员服务
	 */
	@Resource
	IManagerMemberService managerMemberService;
	
	/**
	 * 
	 *
	 * 方法说明：导购个人介绍素材分页数据转换
	 *
	 * @param findGuidIntroduceMaterialPage
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return  返回分页数据,OMS展现分页数据
	 *
	 * @author 梅宏博 CreateDate: 2017年9月19日
	 *
	 */
	@RequestMapping(value = {"list", ""})
	public String list(FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage, Model model,Integer pageNo,Integer pageSize) {
	  try {
		  if(pageNo!=null){
			  findGuidIntroduceMaterialPage.setStart((pageNo-1)*pageSize);
			}
		  if(pageSize!=null){
			  findGuidIntroduceMaterialPage.setLimit(pageSize);
			 }
		  //分页转换
		  findGuidIntroduceMaterialPage.setMerchantNo(UserUtils.getUser().getCompany().getId());
		  findGuidIntroduceMaterialPage.setSortDir(PageSortType.desc);
		  Page<FindGuidIntroduceMaterialPageReturn> pages = guidIntroduceMaterialService.findGuidIntroduceMaterialPage(findGuidIntroduceMaterialPage);
		  List<FindGuidIntroduceMaterialPageReturn> list = Lists.newArrayList();
		  list.addAll(pages.getRows());	  
		  com.ape.common.persistence.Page<FindGuidIntroduceMaterialPageReturn> page=new com.ape.common.persistence.Page<FindGuidIntroduceMaterialPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);	
		  page.initialize();
		  model.addAttribute("page",page);
			
		  model.addAttribute("findGuidIntroduceMaterialPage",findGuidIntroduceMaterialPage);
	 } catch (Exception e) {
		logger.error("获取导购个人介绍素材信息错误！");
	 } 		
		return "modules/business/materialIntroduceList";
	}
	
	/**
	 * 
	 *
	 * 方法说明：导购个人素材编辑页面数据展现
	 *
	 * @param findGuidIntroduceMaterial
	 * @param model
	 * @return 返回编辑页面数据
	 *
	 * @author 梅宏博 CreateDate: 2017年9月19日
	 *
	 */
	@RequiresPermissions("business:materialIntroduce:view")
	@RequestMapping(value = "form")
	public String form(FindGuidIntroduceMaterial findGuidIntroduceMaterial,String tempId, Model model) {
		try {
		  if(findGuidIntroduceMaterial!=null && findGuidIntroduceMaterial.getCode()!=null){
			FindGuidIntroduceMaterialReturn findGuidIntroduceMaterialReturn = guidIntroduceMaterialService.findGuidIntroduceMaterial(findGuidIntroduceMaterial);
			model.addAttribute("data",findGuidIntroduceMaterialReturn);
			}		
			//获取终端下拉信息
			//分店与地址下拉列表
//			 FindShop findShop = CcUtils.shopFilter();
//			 List<FindShopPageReturn> shops= shopService.findShops(findShop);
//			 List<FindShopPageReturn> shopType= shopService.findShopType(findShop);
//			 model.addAttribute("shops",shops);
//			 model.addAttribute("shopTypes",shopType);
			if(StringUtils.isEmpty(tempId)){
				return "modules/business/materialIntroduceForm";
			}else{
				model.addAttribute("temp",dictService.get(tempId));
				return "modules/business/materialIntroduceTempForm";
			}
		} catch (Exception e) {
			logger.error("获取导购个人介绍素材信息错误！");
		}
		return "modules/business/materialIntroduceForm";
	}
	
	/**
	 * 
	 *
	 * 方法说明：静态页面数据返回
	 *
	 * @param findGuidIntroduceMaterial
	 * @param model
	 * @return 返回静态页面数据
	 *
	 * @author 梅宏博 CreateDate: 2017年9月19日
	 *
	 */
	@RequiresPermissions("business:materialIntroduce:view")
	@RequestMapping(value = "view")
	public String view(FindGuidIntroduceMaterial findGuidIntroduceMaterial, Model model) {
		try {
			if(findGuidIntroduceMaterial!=null && findGuidIntroduceMaterial.getCode()!=null){
				FindGuidIntroduceMaterialReturn findGuidIntroduceMaterialReturn = guidIntroduceMaterialService.findGuidIntroduceMaterial(findGuidIntroduceMaterial);
				model.addAttribute("data",findGuidIntroduceMaterialReturn);
				/*获取模版*/
				if(StringUtils.isNotEmpty(findGuidIntroduceMaterialReturn.getTemplateNo())){
					Dict dict = dictService.get(findGuidIntroduceMaterialReturn.getTemplateNo());
					model.addAttribute("temp",dict == null ? "1" : dict.getSort());
				}
			}
		} catch (Exception e) {
		  e.printStackTrace();
		}
		return "modules/business/materialIntrodeceView";
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
	 * @author 梅宏博 CreateDate: 2017年9月19日
	 *
	 */
	@RequestMapping(value = "viewH5")
	public String viewH5(FindGuidIntroduceMaterial findGuidIntroduceMaterial, Model model) {
		try {
			if(findGuidIntroduceMaterial!=null && findGuidIntroduceMaterial.getCode()!=null){
				FindGuidIntroduceMaterialReturn findGuidIntroduceMaterialReturn = guidIntroduceMaterialService.findGuidIntroduceMaterial(findGuidIntroduceMaterial);
				model.addAttribute("data",findGuidIntroduceMaterialReturn);
				
				/*获取模版*/
				if(StringUtils.isNotEmpty(findGuidIntroduceMaterialReturn.getTemplateNo())){
					Dict dict = dictService.get(findGuidIntroduceMaterialReturn.getTemplateNo());
					model.addAttribute("temp",dict == null ? "1" : dict.getSort());
				}
			}
		} catch (Exception e) {
			logger.error("获取导购个人介绍素材信息错误！");
		}		
		return "modules/business/materialIntroduceH5";
	}
	
	/**
	 * 
	 *
	 * 方法说明：中控名片
	 *
	 * @param memberNo 电商版-memberNo为终端微信
	 * @param model
	 * @return 返回静态页面数据
	 *
	 * @author 梅宏博 CreateDate: 2017年9月19日
	 *
	 */
	@RequestMapping(value = "viewZKH5")
	public String viewZKH5(String memberNo, Model model) {
		try {
//			if(findGuidMember!=null && findGuidMember.getMemberNo()!=null){
//				FindGuidMemberReturn gm = guidMemberService.findGuidMember(findGuidMember);
//				model.addAttribute("data",gm);
//				
//				//职位
//				if(gm!=null&&gm.getMobile()!=null){
//					ManagerMemberDto managerMemberDto = new ManagerMemberDto();
//					managerMemberDto.setMobile(gm.getMobile());
//					ManagerMemberReturnDto managerMember = managerMemberService.findManagerMemberByMobile(managerMemberDto);
//					String position=managerMember == null ? "GUID" : managerMember.getMemberType();
//					model.addAttribute("position",position);
//				}
//			}
		} catch (Exception e) {
			logger.error("获取导购个人介绍素材信息错误！");
		}		
		return "modules/business/materialZhongKong";
	}
	
	@RequestMapping(value = "setting")
	public String setting(Model model) {
		try {
			Dict dict = new Dict();
			dict.setType("guid_introduce_temp");
			model.addAttribute("list", dictService.findList(dict));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "modules/business/materialIntroduceSetting";
	}
	
	@RequestMapping(value = "settingForm")
	public String settingForm(String id,Model model) {
		try {
			model.addAttribute("data", dictService.get(id));
		} catch (Exception e) {
			logger.error("获取导购个人介绍素材信息错误！");
		}		
		return "modules/business/materialIntroduceSettingForm";
	}
	
	@RequestMapping(value = "settingSave")
	public String settingSave(Dict dict, Model model, RedirectAttributes redirectAttributes) {
		try {
			dict.setType("guid_introduce_temp");
			dictService.save(dict);
			addMessage(redirectAttributes, "保存素材模版'" + dict.getLabel() + "'成功");
		} catch (Exception e) {
			logger.error("保存导购个人介绍素材信息错误！");
		}	
		return "redirect:" + adminPath + "/business/materialIntroduce/setting/";
	}
}
