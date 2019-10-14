package com.lj.business.api.controller.cm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.utils.FileUtil;
import com.lj.business.cm.dto.AddMaterial;
import com.lj.business.cm.dto.AddMaterialCommen;
import com.lj.business.cm.dto.AddMaterialCommenReturn;
import com.lj.business.cm.dto.AddMaterialReturn;
import com.lj.business.cm.dto.AddMaterialType;
import com.lj.business.cm.dto.AddMaterialTypeReturn;
import com.lj.business.cm.dto.FindMaterialAppPage;
import com.lj.business.cm.dto.FindMaterialAppPageReturn;
import com.lj.business.cm.dto.FindMaterialCommenPageReturn;
import com.lj.business.cm.dto.FindMaterialPageReturn;
import com.lj.business.cm.dto.FindMaterialTypePage;
import com.lj.business.cm.dto.FindMaterialTypePageReturn;
import com.lj.business.cm.dto.FindMaterialTypesApp;
import com.lj.business.cm.dto.FindMaterialTypesAppReturn;
import com.lj.business.cm.dto.guidIntroduceMaterial.AddGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.DelGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPage;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPageReturn;
import com.lj.business.cm.dto.guidIntroduceMaterial.UpdateGuidIntroduceMaterial;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPage;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPageReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeApiReturn;
import com.lj.business.cm.service.IGuidIntroduceMaterialService;
import com.lj.business.cm.service.IMaterialCommenService;
import com.lj.business.cm.service.IMaterialService;
import com.lj.business.cm.service.IMaterialTypeService;
import com.lj.business.cm.service.IVrMaterialCommenService;
import com.lj.business.cm.service.IVrMaterialTypeService;
import com.lj.oms.entity.sys.Dict;
import com.lj.oms.service.AreaHessianService;

/**
 * 
 * 
 * 类说明：素材中心/VR素材
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年6月23日
 */
@Controller
@RequestMapping(value="/material/")
public class MaterialAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(MaterialAction.class);
	
	@Resource
	private IMaterialService materialService;

	@Resource
	private IMaterialTypeService materialTypeService;
	
	@Resource
	private IMaterialCommenService materialCommenService;
	
	@Resource
	private IGuidIntroduceMaterialService guidIntroduceMaterialService;
	
	@Resource
	private AreaHessianService areaHessianService;
	
	@Resource
	private IVrMaterialTypeService vrMaterialTypeService;
	
	@Resource
	private IVrMaterialCommenService vrMaterialCommenService;
	
//	@Resource
//	private IShopService shopService;

	/**
	 * 
	 *
	 * 方法说明：素材类型列表_APP用
	 *
	 * @param findMaterialTypesApp
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月15日
	 *
	 */
	@RequestMapping(value = "findMaterialTypesApp.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public  List<FindMaterialTypesAppReturn>  findMaterialTypesApp(FindMaterialTypesApp findMaterialTypesApp) {
		return materialTypeService.findMaterialTypesApp(findMaterialTypesApp);
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：分页查询素材类型列表
	 *
	 * @param findMaterialTypePage
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月20日
	 *
	 */
	@RequestMapping(value = "findMaterialTypePage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindMaterialTypePageReturn> findMaterialTypes(FindMaterialTypePage findMaterialTypePage) {
		return materialTypeService.findMaterialTypePage(findMaterialTypePage);
	}

	/**
	 *
	 *
	 * 方法说明：分页查询素材公式和个人类型列表
	 *
	 * @param findMaterialTypePage
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月20日
	 *
	 */
	@RequestMapping(value = "findMaterialTypeForMemberPage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindMaterialTypePageReturn> findMaterialTypeForMemberPage(FindMaterialTypePage findMaterialTypePage) {
		return materialTypeService.findMaterialTypeForMemberPage(findMaterialTypePage);
	}

	/**
	 * 
	 *
	 * 方法说明：根据素材编码查询个人素材
	 *
	 * @param code 素材编码
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月20日
	 *
	 */
	@RequestMapping(value = "findMaterialByCode.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public FindMaterialPageReturn findMaterialByCode(String code) {
		return materialService.findMaterialByCode(code);
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据素材编码查询公共素材
	 *
	 * @param code 素材编码
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月20日
	 *
	 */
	@RequestMapping(value = "findMaterialCommenByCode.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public FindMaterialCommenPageReturn findMaterialCommenByCode(String code) {
		return materialCommenService.findMaterialCommenByCode(code);
	}

	/**
	 * 
	 *
	 * 方法说明：根据素材类型编码查询素材明细列表_APP用
	 *
	 * @param findMaterialAppPage
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月15日
	 *
	 */
	@RequestMapping(value = "findMaterialAppPage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public  Page<FindMaterialAppPageReturn>  findMaterialAppPage(FindMaterialAppPage findMaterialAppPage) {
		findMaterialAppPage.setSortDir(PageSortType.desc);
		return materialService.findMaterialAppPage(findMaterialAppPage);
	}
	
	
	/**
	 * 
	 *
	 * 方法说明： 添加素材
	 * 
	 * @param addMaterial
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年6月22日
	 *
	 */
	@RequestMapping(value = "addMaterial.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AddMaterialReturn addMaterial(List<MultipartFile> imgAddrs, AddMaterial addMaterial) throws Exception {
		logger.debug("addMaterial(List<MultipartFile> imgAddrs={}, AddMaterial addMaterial={}) - start", imgAddrs, addMaterial); 

		StringBuilder imgs =new StringBuilder("");
		String ROOT ="/www/temp/output";
		String IMG_PATH ="/oms/image/material/";
		if(!CollectionUtils.isEmpty(imgAddrs)){
			for (MultipartFile each : imgAddrs) {
				String imageFolder =  ROOT + IMG_PATH;
				String fileInputName= FileUtil.saveFile(imageFolder, each);
				imgs.append(IMG_PATH).append(fileInputName).append(",");
			}
			imgs.deleteCharAt(imgs.length() - 1);
		}
		addMaterial.setImgAddr(imgs.toString());
		AddMaterialReturn returnAddMaterialReturn = materialService.addMaterial(addMaterial);
		logger.debug("addMaterial(List<MultipartFile>, AddMaterial) - end - return value={}", addMaterial); 
		logger.debug("addMaterial(List<MultipartFile>, AddMaterial) - end - return value={}", returnAddMaterialReturn); 
		return returnAddMaterialReturn;
	}
	
	/**
	 * 
	 *
	 * 方法说明： 添加公用素材
	 * 
	 * @param addMaterialCommen
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月20日
	 *
	 */
	@RequestMapping(value = "addMaterialCommen.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AddMaterialCommenReturn addMaterialCommen(AddMaterialCommen addMaterialCommen) {
		return materialCommenService.addMaterialCommen(addMaterialCommen);
	}
	
	/**
	 * 
	 *
	 * 方法说明：新增素材类型
	 *
	 * @param addMaterialType
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年6月22日
	 *
	 */
	@RequestMapping(value = "addMaterialType.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AddMaterialTypeReturn addMaterialType(AddMaterialType addMaterialType) {
		return materialTypeService.addMaterialType(addMaterialType);
	}
	
	/**
	 * 
	 *
	 * 方法说明：新增导购个人素材
	 *
	 * @param addGuidIntroduceMaterial
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月18日
	 *
	 */
	@RequestMapping(value = "addGuidIntroduceMaterial.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse addGuidIntroduceMaterial(AddGuidIntroduceMaterial addGuidIntroduceMaterial) {
		logger.debug("addGuidIntroduceMaterial(AddGuidIntroduceMaterial addGuidIntroduceMaterial={}) - start", addGuidIntroduceMaterial); 
		
		guidIntroduceMaterialService.addGuidIntroduceMaterial(addGuidIntroduceMaterial);
		GeneralResponse returnGeneralResponse = GeneralResponse.generateSuccessResponse();
		logger.debug("addGuidIntroduceMaterial() - end - return value={}", returnGeneralResponse); 
		return returnGeneralResponse;
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购个人素材
	 *
	 * @param findGuidIntroduceMaterial
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月18日
	 *
	 */
	@RequestMapping(value = "findGuidIntroduceMaterial.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FindGuidIntroduceMaterialPageReturn> findGuidIntroduceMaterial(FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage) {
		logger.debug("findGuidIntroduceMaterial(FindGuidIntroduceMaterial findGuidIntroduceMaterial={}) - start", findGuidIntroduceMaterialPage); 
		
		Map<String, Integer> tempMap = new HashMap<>();
		List<Dict> dicts = areaHessianService.findMaterialTemp("guid_introduce_temp");//获取导购个人素材模板类型
		for (Dict dict : dicts) {
			tempMap.put(dict.getId(), dict.getSort());
		}
		List<FindGuidIntroduceMaterialPageReturn> list = guidIntroduceMaterialService.findGuidIntroduceMaterialList(findGuidIntroduceMaterialPage);
		
		for (FindGuidIntroduceMaterialPageReturn findGuidIntroduceMaterialPageReturn : list) {
			findGuidIntroduceMaterialPageReturn.setSort(tempMap.get(findGuidIntroduceMaterialPageReturn.getTemplateNo()) + "");
		}
		logger.debug("findGuidIntroduceMaterial() - end - return value={}", list); 
		return list;
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改导购个人素材
	 *
	 * @param updateGuidIntroduceMaterial
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月19日
	 *
	 */
	@RequestMapping(value = "updateGuidIntroduceMaterial.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse updateGuidIntroduceMaterial(UpdateGuidIntroduceMaterial updateGuidIntroduceMaterial) {
		logger.debug("updateGuidIntroduceMaterial(UpdateGuidIntroduceMaterial updateGuidIntroduceMaterial={}) - start", updateGuidIntroduceMaterial); 
		
		guidIntroduceMaterialService.updateGuidIntroduceMaterial(updateGuidIntroduceMaterial);
		GeneralResponse returnGeneralResponse = GeneralResponse.generateSuccessResponse();
		logger.debug("updateGuidIntroduceMaterial() - end - return value={}", returnGeneralResponse); 
		return returnGeneralResponse;
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除导购个人素材
	 *
	 * @param delGuidIntroduceMaterial
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月19日
	 *
	 */
	@RequestMapping(value = "delGuidIntroduceMaterial.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse delGuidIntroduceMaterial(DelGuidIntroduceMaterial delGuidIntroduceMaterial) {
		logger.debug("delGuidIntroduceMaterial(DelGuidIntroduceMaterial delGuidIntroduceMaterial={}) - start", delGuidIntroduceMaterial); 
		
		guidIntroduceMaterialService.delGuidIntroduceMaterial(delGuidIntroduceMaterial);
		GeneralResponse returnGeneralResponse = GeneralResponse.generateSuccessResponse();
		logger.debug("delGuidIntroduceMaterial() - end - return value={}", returnGeneralResponse); 
		return returnGeneralResponse;
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取导购个人介绍素材模板地址
	 *
	 * @param findGuidIntroduceMaterialTemp
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月20日
	 *
	 */
	@RequestMapping(value = "findGuidIntroduceMaterialTemp.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Dict> findGuidIntroduceMaterialTemp(String tempType) {
		logger.debug("findGuidIntroduceMaterialTemp(String tempType={}) - start", tempType); 
		List<Dict> list = areaHessianService.findMaterialTemp(tempType);
		logger.debug("findGuidIntroduceMaterialTemp() - end - return value={}", list); 
		return list;
	}
	
	

	/**
	 * 
	 *
	 * 方法说明：查询VR素材类型
	 *
	 * @param merchantNo	商户编号
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月13日
	 *
	 */
	@RequestMapping(value = "findVrMaterialType.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FindVrMaterialTypeApiReturn> findVrMaterialType(String merchantNo) {
		FindVrMaterialType findVrMaterialType = new FindVrMaterialType();
		findVrMaterialType.setMerchantNo(merchantNo);
		List<FindVrMaterialTypeApiReturn> list=vrMaterialTypeService.findVrMaterialTypeReturnList(findVrMaterialType);
		logger.debug("findVrMaterialType() - end - return value={}", list); 
		return list;
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：VR素材分页查询
	 * 所属商户以及所属终端的所有VR素材
	 *
	 * @param merchantNo
	 * @param shopNo
	 * @param categoryCodeList	素材类型分类CODE列表
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月13日
	 *
	 */
	@RequestMapping(value = "findVrMaterial.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindVrMaterialCommenPageReturn> findVrMaterial(FindVrMaterialCommenPage  findVrMaterialCommenPage) {
		if(StringUtils.isNotBlank(findVrMaterialCommenPage.getCodes())){
			List<String> list = Lists.newArrayList();
			String[] string = findVrMaterialCommenPage.getCodes().split(",");
			for(String str:string){
				list.add(str);
			}
			findVrMaterialCommenPage.setTypeCodes(list);
			
		}
		// 查询终端信息，获取终端销售渠道
//		FindShopReturn shop = shopService.findshop(findVrMaterialCommenPage.getShopNo());
//		if(StringUtils.isNotEmpty(shop.getMecShopChannel())) {
//			findVrMaterialCommenPage.setShopTypes(shop.getMecShopChannel().split(","));
//		}
		return vrMaterialCommenService.findVrMaterialCommenPage(findVrMaterialCommenPage);
	}
	
}
