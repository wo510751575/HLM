package com.lj.oms.cm;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.business.cm.dto.vrMaterialCommen.AddVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPage;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPageReturn;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenReturn;
import com.lj.business.cm.dto.vrMaterialCommen.UpdateVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommenCategory.AddVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategoryReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryReturn;
import com.lj.business.cm.emus.ShowChannel;
import com.lj.business.cm.service.IVrMaterialCommenCategoryService;
import com.lj.business.cm.service.IVrMaterialCommenService;
import com.lj.business.cm.service.IVrMaterialTypeCategoryService;
import com.lj.business.cm.service.IVrMaterialTypeService;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Dict;
import com.lj.oms.service.sys.DictService;
import com.lj.oms.utils.UserUtils;
/**
 * 
 * 
 * 类说明：VR素材
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年12月11日
 */
@Controller
@RequestMapping(value="${adminPath}/cm/vrMaterialCommen")
public class VrMaterialCommenController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(VrMaterialCommenController.class);
	
	@Resource
	private IVrMaterialCommenService vrMaterialCommenService;

//	@Autowired
//	private IShopService shopService;
	
	@Resource
	private IVrMaterialTypeService vrMaterialTypeService;
	
	@Resource
	private IVrMaterialTypeCategoryService vrMaterialTypeCategoryService;
	
	@Resource
	private IVrMaterialCommenCategoryService vrMaterialCommenCategoryService;

	@Resource
	private DictService dictService;
	
	
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo ,Integer pageSize,FindVrMaterialCommenPage findVrMaterialCommenPage){
		try {
			if(pageNo!=null){
				findVrMaterialCommenPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findVrMaterialCommenPage.setLimit(pageSize);
			}
			findVrMaterialCommenPage.setMerchantNo(UserUtils.getUser().getCompany().getId());
			Page<FindVrMaterialCommenPageReturn> pages = vrMaterialCommenService.findVrMaterialCommenReturnPage(findVrMaterialCommenPage);
			List<FindVrMaterialCommenPageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			String typeNames= null;
			for(FindVrMaterialCommenPageReturn materialCommenPageReturn :list){
				FindVrMaterialCommenCategory findVrMaterialCommenCategory = new FindVrMaterialCommenCategory();
				findVrMaterialCommenCategory.setMaterialCode(materialCommenPageReturn.getCode());
				List<FindVrMaterialCommenCategoryReturn> categoryReturns = vrMaterialCommenCategoryService.findVrMaterialCommenCategoryList(findVrMaterialCommenCategory);
				 if(categoryReturns.size()>1){
					  typeNames=categoryReturns.get(0).getTypeCategoryName()+"  "+"||"+"  "+categoryReturns.get(1).getTypeCategoryName();
				 }
				 materialCommenPageReturn.setRemark(typeNames);
//		        if(materialCommenPageReturn.getDimensionSt().equals(DimensionSt.MERCHANT.toString())){
		        	materialCommenPageReturn.setDimensionSt("商户");
//		        }else{
//		        	materialCommenPageReturn.setDimensionSt("终端");
//			  }
			}
			 com.ape.common.persistence.Page<FindVrMaterialCommenPageReturn> page
			    =new com.ape.common.persistence.Page<FindVrMaterialCommenPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			 page.initialize();
			 model.addAttribute("page",page);
			 model.addAttribute("findVrMaterialCommenPage", findVrMaterialCommenPage);
			 
			 model.addAttribute("ShowChannel", ShowChannel.values());//显示渠道
			 
			 /**
			 * 销售渠道
			 */
			Dict dict = new Dict();
			dict.setType("shop_channel");
			dict.setMerchantNo(UserUtils.getMerchantNo());
			List<Dict> mecShopChannels = dictService.findList(dict);
			model.addAttribute("mecShopChannel",mecShopChannels);
			 
		} catch (Exception e) {
			logger.error("获取VR素材信息错误！");
		} 
		return "modules/cm/vrMaterialCommenList";
	}
	
	@RequestMapping(value="from")
	public String from(Model model,FindVrMaterialCommen findVrMaterialCommen){
		try {
			if(findVrMaterialCommen != null && findVrMaterialCommen.getCode() !=null){
			FindVrMaterialCommenReturn findVrMaterialCommenReturn = vrMaterialCommenService.findVrMaterialCommen(findVrMaterialCommen);
			FindVrMaterialCommenCategory findVrMaterialCommenCategory = new FindVrMaterialCommenCategory();
			findVrMaterialCommenCategory.setMaterialCode(findVrMaterialCommen.getCode());
			List<FindVrMaterialCommenCategoryReturn> categoryReturns = vrMaterialCommenCategoryService.findVrMaterialCommenCategoryList(findVrMaterialCommenCategory);
			List<String> returnList = Lists.newArrayList();
			for(FindVrMaterialCommenCategoryReturn categoryReturn:categoryReturns){
				returnList.add(StringUtils.strip(categoryReturn.getTypeCategoryCode().trim()));
			}
		    model.addAttribute("categoryReturns", returnList);
			model.addAttribute("data", findVrMaterialCommenReturn);
			}
			
//			//分店与地址下拉列表
//		  	FindShop findShop= CcUtils.shopFilter();
//			List<FindShopPageReturn> shops= shopService.findShops(findShop);
//			model.addAttribute("shops",shops);
//			 
//			List<FindShopPageReturn> shopType= shopService.findShopType(CcUtils.shopFilter());
//			model.addAttribute("shopType", shopType);
			
			FindVrMaterialType findVrMaterialType = new FindVrMaterialType();
			findVrMaterialType.setMerchantNo(UserUtils.getUser().getCompany().getId());
			List<FindVrMaterialTypeReturn> list = vrMaterialTypeService.findVrMaterialTypeList(findVrMaterialType);
			model.addAttribute("vrType", list);
			
			model.addAttribute("ShowChannel", ShowChannel.values());//显示渠道
			
			/**
			 * 销售渠道
			 */
			Dict dict = new Dict();
			dict.setType("shop_channel");
			dict.setMerchantNo(UserUtils.getMerchantNo());
			List<Dict> mecShopChannels = dictService.findList(dict);
			model.addAttribute("mecShopChannel",mecShopChannels);
			
		} catch (Exception e) {
			logger.error("获取VR素材信息错误！");
		}
		return "modules/cm/vrMaterialCommenFrom";
	}
	
	@RequiresPermissions("cm:vrMaterialCommen:edit")
	@RequestMapping(value="edit")
	public String edit(RedirectAttributes redirectAttributes,UpdateVrMaterialCommen updateVrMaterialCommen,String vrInfo){
		try {
			if(updateVrMaterialCommen !=null && updateVrMaterialCommen.getCode()!=null){
				//不可修改类型
//				UpdateVrMaterialCommenCategory updateVrMaterialCommenCategory = new UpdateVrMaterialCommenCategory();
				/*String[] str = vrInfo.split(";");
				for(String string:str){
					String[] typeCodes = string.split(",");
					FindVrMaterialCommenCategory findVrMaterialCommenCategory = new FindVrMaterialCommenCategory();
					findVrMaterialCommenCategory.setMaterialCode(updateVrMaterialCommen.getCode());
					findVrMaterialCommenCategory.setMaterialTypeCode(typeCodes[0]);
					FindVrMaterialCommenCategoryReturn findVrMaterialCommenCategoryReturn = vrMaterialCommenCategoryService.findByPrimaryKey(findVrMaterialCommenCategory);
					updateVrMaterialCommenCategory.setCodes(typeCodes);
					updateVrMaterialCommenCategory.setCode(findVrMaterialCommenCategoryReturn.getCode());
					vrMaterialCommenCategoryService.updateVrMaterialCommenCategory(updateVrMaterialCommenCategory);
				}*/
				vrMaterialCommenService.updateVrMaterialCommen(updateVrMaterialCommen);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + adminPath + "/cm/vrMaterialCommen/";
	}
	
	
	@RequiresPermissions("cm:vrMaterialCommen:edit")
	@RequestMapping(value="save")
	public String save(RedirectAttributes redirectAttributes,AddVrMaterialCommen addVrMaterialCommen,String vrInfo){
		try {
			
			//需求待定
	/*		Map<String,Object>  map=UserUtils.jurisdiction();
			if(map.get("shopNos")!=null){
				String[] string=map.get("shopNos").toString().split(",");
				int i =string.length;
				if(i == 1){
					addVrMaterialCommen.setShopNo(StringUtils.strip(string[0].trim(),"[]"));
					FindShop shop=new FindShop();
					shop.setShopNo(addVrMaterialCommen.getShopNo());
					FindShopReturn shopReturn= shopService.findShopByShopNo(shop);
					addVrMaterialCommen.setShopName(shopReturn.getShopName());
					addVrMaterialCommen.setDimensionSt(DimensionSt.SHOP.toString());
				}
			}else{
				addVrMaterialCommen.setDimensionSt(DimensionSt.MERCHANT.toString());
			}*/
			addVrMaterialCommen.setCode(GUID.getPreUUID());
			String[] str=vrInfo.split(";");
			for(String vrInfos : str){
			 String[] typeCode = vrInfos.split(",");
			 AddVrMaterialCommenCategory addVrMaterialCommenCategory = new AddVrMaterialCommenCategory();
			 addVrMaterialCommenCategory.setMaterialCode(addVrMaterialCommen.getCode());
			 addVrMaterialCommenCategory.setCodes(typeCode);
			 addVrMaterialCommenCategory.setCreateDate(new Date());
			 vrMaterialCommenCategoryService.addVrMaterialCommenCategory(addVrMaterialCommenCategory);
			}
			addVrMaterialCommen.setMerchantNo(UserUtils.getUser().getCompany().getId());
			addVrMaterialCommen.setMerchantName(UserUtils.getUser().getName());
			addVrMaterialCommen.setDimensionSt("MERCHANT");
			vrMaterialCommenService.addVrMaterialCommen(addVrMaterialCommen);
		} catch (Exception e) {
			logger.error("新增VR素材信息错误！");
		}
		return "redirect:" + adminPath + "/cm/vrMaterialCommen/";
	}

  @RequestMapping(value="findType")
  @ResponseBody
  public List<FindVrMaterialTypeCategoryReturn> findtTypeCategoryType(FindVrMaterialTypeCategory findVrMaterialTypeCategory){
	  List<FindVrMaterialTypeCategoryReturn > list ;
	  try {
		list  = vrMaterialTypeCategoryService.findVrMaterialTypeCategoryReturnList(findVrMaterialTypeCategory);
		return list;
	} catch (Exception e) {
		logger.error("获取VR素材信息错误！");
	}
	 return null;
  }
}
