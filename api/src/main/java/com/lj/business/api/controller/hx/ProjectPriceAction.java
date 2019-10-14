/**
 * 
 */
package com.lj.business.api.controller.hx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.constant.HxConstant;
import com.ye.business.hx.dto.FindProjectPricePage;
import com.ye.business.hx.dto.FindShopConfigPage;
import com.ye.business.hx.dto.ProjectPriceDto;
import com.ye.business.hx.dto.ShopConfigDto;
import com.ye.business.hx.service.IProjectPriceService;
import com.ye.business.hx.service.IShopConfigService;

/**
 * 
 * 
 * 类说明：收費項目
 *  
 * 
 * <p>
 *   
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate:  2019年4月15日
 */
@Controller
@RequestMapping(value = "/hx/projectPrice/")
public class ProjectPriceAction  extends Action{
	
	@Autowired
	private IShopConfigService shopConfigService;
	
	@Autowired
	private IProjectPriceService projectPriceService;
	
	/**
	 * 收费类别列表
	 * @param configDto 
	 * <br/>ParentCode=null则查询大类列表
	 * <br/>ParentCode!=null则查询其子类列表
	 * @param findPage 分页参数
	 * @return
	 */
	@RequestMapping(value = { "/type/list.do" })
	@ResponseBody
	public Page<ShopConfigDto> findProjectPriceType(ShopConfigDto configDto,FindShopConfigPage findPage){
		AssertUtils.notNullAndEmpty(configDto.getMerchantNo(),"商户号不能为空！");
		Page<ShopConfigDto> datas = null;
		ShopConfigDto findConfig = new ShopConfigDto();
		findConfig.setMerchantNo(configDto.getMerchantNo());
		findPage.setParam(findConfig);
		
		if(StringUtils.isEmpty(configDto.getParentCode())) {//查收费项目类别一級
			ShopConfigDto topConfig = shopConfigService.getTopShopCofig(HxConstant.CONFIG_LABLE_NO_PROJECT_PRICE);
			findConfig.setParentCode(topConfig.getCode());
			datas = shopConfigService.findShopConfigPage(findPage);
		}else if("ALL".equals(configDto.getParentCode())){//查收费项目类别的全部二级
			ShopConfigDto topConfig = shopConfigService.getTopShopCofig(HxConstant.CONFIG_LABLE_NO_PROJECT_PRICE);
			findPage.setParentCode(topConfig.getCode());
			configDto.setParentCode(null);
			datas = shopConfigService.findSecondShopConfigPage(findPage);
		}else {//查指定收费项目类别的子集
			findConfig.setParentCode(configDto.getParentCode());
			datas = shopConfigService.findShopConfigPage(findPage);
		} 
		return datas;
	}
	
	/**
	 * 新增类别
	 * @param configDto
	 * @return
	 */
	@RequestMapping(value = { "/type/add.do" })
	@ResponseBody
	public ShopConfigDto addProjectPriceType(ShopConfigDto configDto) {
		AssertUtils.notNullAndEmpty(configDto.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(configDto.getLableName(),"名称不能为空！");
		
		if(StringUtils.isEmpty(configDto.getParentCode())) {//为空则代表新增一級大类
			ShopConfigDto topConfig = shopConfigService.getTopShopCofig(HxConstant.CONFIG_LABLE_NO_PROJECT_PRICE);
			configDto.setParentCode(topConfig.getCode());
		}
		ShopConfigDto rt = shopConfigService.addShopConfig(configDto);
		return rt;
	}
	
	/**
	 * 修改类别
	 * @param configDto
	 * @return
	 */
	@RequestMapping(value = { "/type/edit.do" })
	@ResponseBody
	public String editProjectPriceType(ShopConfigDto configDto) {
//		AssertUtils.notNullAndEmpty(configDto.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(configDto.getLableName(),"名称不能为空！");
		AssertUtils.notNullAndEmpty(configDto.getCode(),"类型code不能为空！");
		 
		shopConfigService.updateShopConfig(configDto);
		return configDto.getCode();
	}
	
	/**
	 * 批量修改排序（上移 & 下移 则使用该方法）
	 */
	@RequestMapping(value = { "/type/indexNoEdit.do" })
	@ResponseBody
	public GeneralResponse editProjectPriceTypeIndexNo(ShopConfigDto configDto,String typeJsons) {
		AssertUtils.notNullAndEmpty(configDto.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(typeJsons,"数据typeJsons不能为空！");
		List<ShopConfigDto> types = JSONArray.parseArray(typeJsons,ShopConfigDto.class);
		configDto.setChilds(types);
		shopConfigService.updateShopConfigIndexNo(configDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 删除收费项目类别
	 */
	@RequestMapping(value = { "/type/remove.do" })
	@ResponseBody
	public GeneralResponse removeProjectPriceType(ShopConfigDto configDto) {
		AssertUtils.notNullAndEmpty(configDto.getMerchantNo(), "商户号不能为空！");
		AssertUtils.notNullAndEmpty(configDto.getCode(), "code不能为空！");
		shopConfigService.deleteShopConfig(configDto);
		return GeneralResponse.generateSuccessResponse(configDto.getCode());
	}
	
	/**
	 * 收费项目分页列表。
	 * @param param
	 * @param findPage
	 * @return
	 */
	@RequestMapping(value = { "/list.do" })
	@ResponseBody
	public Page<ProjectPriceDto> findProjectPricePage(ProjectPriceDto param,FindProjectPricePage findPage){
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		if(StringUtils.isNotEmpty(param.getType2Code())) {//指定了二级，则一级置空
			param.setType1Code(null);
		}
		findPage.setParam(param);
	
		Page<ProjectPriceDto> datas = projectPriceService.findProjectPricePage(findPage);
		return datas;
	}
	
	/**
	 * 新增：收费项目
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/add.do" })
	@ResponseBody
	public ProjectPriceDto addProjectPrice(ProjectPriceDto param) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getType1Code(),"Type1Code不能为空！");
		AssertUtils.notNullAndEmpty(param.getType1Name(),"Type1Name不能为空！");
		AssertUtils.notNullAndEmpty(param.getType2Code(),"Type2Name不能为空！");
		AssertUtils.notNullAndEmpty(param.getType2Name(),"Type2Name不能为空！");
		AssertUtils.notNullAndEmpty(param.getProjectName(),"项目名称不能为空！");
		
		ProjectPriceDto rtdata = projectPriceService.addProjectPrice(param);
		return rtdata;
	}
	
	/**
	 * 修改收费项目
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/edit.do" })
	@ResponseBody
	public String editProjectPrice(ProjectPriceDto param) {
		AssertUtils.notNullAndEmpty(param.getCode(),"项目code不能为空！");
		projectPriceService.updateProjectPrice(param);
		return param.getCode();
	}
	
	/**
	 * 删除收费项目
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/remove.do" })
	@ResponseBody
	public String removeProjectPrice(ProjectPriceDto param) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(param.getCode(),"项目code不能为空！");
		projectPriceService.deleteProjectPrice(param);
		return param.getCode();
	}
	
	/**
	 * 支付方式列表
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/payType/list.do" })
	@ResponseBody
	public Page<ShopConfigDto> findPayTypePage(ShopConfigDto configDto,FindShopConfigPage findPage){
		AssertUtils.notNullAndEmpty(configDto.getMerchantNo(),"商户号不能为空！");
		Page<ShopConfigDto> datas = null;
		ShopConfigDto findConfig = new ShopConfigDto();
		findConfig.setMerchantNo(configDto.getMerchantNo());
		findPage.setParam(findConfig);
		ShopConfigDto topConfig = shopConfigService.getTopShopCofig(HxConstant.CONFIG_LABLE_NO_PAY_TYPE);
		findConfig.setParentCode(topConfig.getCode());
		datas = shopConfigService.findShopConfigPage(findPage);
		return datas;
	}
	
	/**
	 * 新增支付方式
	 * @param configDto
	 * @return
	 */
	@RequestMapping(value = { "/payType/add.do" })
	@ResponseBody
	public ShopConfigDto addPayType(ShopConfigDto configDto) {
		AssertUtils.notNullAndEmpty(configDto.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(configDto.getLableName(),"名称不能为空！");
		
		ShopConfigDto topConfig = shopConfigService.getTopShopCofig(HxConstant.CONFIG_LABLE_NO_PAY_TYPE);
		configDto.setParentCode(topConfig.getCode());
		ShopConfigDto rt = shopConfigService.addShopConfig(configDto);
		return rt;
	}
	
	/**
	 * 修改支付方式
	 * @param configDto
	 * @return
	 */
	@RequestMapping(value = { "/payType/edit.do" })
	@ResponseBody
	public String editPayType(ShopConfigDto configDto) {
//		AssertUtils.notNullAndEmpty(configDto.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(configDto.getLableName(),"名称不能为空！");
		AssertUtils.notNullAndEmpty(configDto.getCode(),"类型code不能为空！");
		 
		shopConfigService.updateShopConfig(configDto);
		return configDto.getCode();
	}
	
	/**
	 * 删除支付方式
	 */
	@RequestMapping(value = { "/payType/remove.do" })
	@ResponseBody
	public GeneralResponse removePayType(ShopConfigDto configDto) {
		AssertUtils.notNullAndEmpty(configDto.getMerchantNo(), "商户号不能为空！");
		AssertUtils.notNullAndEmpty(configDto.getCode(), "code不能为空！");
		shopConfigService.deleteShopConfig(configDto);
		return GeneralResponse.generateSuccessResponse(configDto.getCode());
	}
}
