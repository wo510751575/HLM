package com.lj.oms.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.company.AddBranchCompany;
import com.lj.business.member.dto.company.FindBranchCompany;
import com.lj.business.member.dto.company.FindBranchCompanyDetailReturn;
import com.lj.business.member.dto.company.FindBranchCompanyPage;
import com.lj.business.member.dto.company.FindBranchCompanyPageReturn;
import com.lj.business.member.dto.company.UpdateBranchCompany;
import com.lj.business.member.emus.CompanyType;
import com.lj.business.member.service.IBranchCompanyService;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.service.AreaHessianService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：经销商
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2018年05月08日
 */

@Controller
@RequestMapping(value="${adminPath}/member/branchCompany")
public class BranchCompanyController  extends BaseController{
	private static final String LIST="modules/member/branchCompanyList";
	private static final String FORM="modules/member/branchCompanyForm";
	private static final String VIEW="modules/member/branchCompanyView";
	private static final String REDIRECT_LIST="redirect:" +Global.getAdminPath() + "/member/branchCompany";
	
	@Resource
	private IBranchCompanyService branchCompanyService;
//	@Autowired
//	private IShopService shopService;
	@Resource
	private AreaHessianService areaHessianService; // 地区服务

	@RequiresPermissions("member:branchCompany:view")
	@RequestMapping(value={"list",""})
    public String list(Model model,RedirectAttributes redirectAttributes,FindBranchCompanyPage branchCompanyDto,Integer pageNo,Integer pageSize){
		try {
			if(pageNo !=null){
				branchCompanyDto.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				branchCompanyDto.setLimit(pageSize);
			}
			
			branchCompanyDto.setMerchantNo(UserUtils.getMerchantNo());
			setArea(model, branchCompanyDto);
			
			Page<FindBranchCompanyPageReturn> pages=branchCompanyService.findBranchCompanyPage(branchCompanyDto);
			List<FindBranchCompanyPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			
			com.ape.common.persistence.Page<FindBranchCompanyPageReturn> page	
			=new com.ape.common.persistence.Page<FindBranchCompanyPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			
			model.addAttribute("page", page);
			model.addAttribute("findBranchCompanyPage", branchCompanyDto);
			model.addAttribute("CompanyType", CompanyType.values());
			
		} catch (Exception e) {
			addMessage(redirectAttributes, "加载数据失败,系统出现异常！");
			logger.error(e.getMessage(),e);
		}
		return LIST;
    }
	
	/**
	 * 转换地区code
	 * @param model
	 * @param branchCompanyDto
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月14日上午10:42:40
	 */
	@SuppressWarnings("unchecked")
	private void setArea(Model model, FindBranchCompanyPage branchCompanyDto) {
		if(StringUtils.isNotBlank(branchCompanyDto.getAreaId()) || !"1".equals(branchCompanyDto.getAreaId())){
			Area area = areaHessianService.findArea(branchCompanyDto.getAreaId());
			if(area!=null){
				Map<String, Object> areaMap=new HashMap<String, Object>();
				switch (area.getType()) {
				case "2":
					Area province = areaHessianService.findArea(branchCompanyDto.getAreaId());
					branchCompanyDto.setProvince(com.lj.base.core.util.StringUtils.toString(province.getCode()));
					break;
				case "3":
					areaMap = areaHessianService.selectCurrentLevelByCityId(branchCompanyDto.getAreaId());
					branchCompanyDto.setProvince(com.lj.base.core.util.StringUtils.toString(areaMap.get("province_id")));
					branchCompanyDto.setCity(com.lj.base.core.util.StringUtils.toString(areaMap.get("city_id")));
					break;
				case "4":
					areaMap = areaHessianService.selectCurrentLevelByAreaId(branchCompanyDto.getAreaId());
					branchCompanyDto.setProvince(com.lj.base.core.util.StringUtils.toString(areaMap.get("province_id")));
					branchCompanyDto.setCity(com.lj.base.core.util.StringUtils.toString(areaMap.get("city_id")));
//					branchCompanyDto.setRegion(com.lj.base.core.util.StringUtils.toString(areaMap.get("country_id")));
					break;
				}
				model.addAttribute("areaId", branchCompanyDto.getAreaId());
				model.addAttribute("areaName", branchCompanyDto.getAreaName());
			}else{
				logger.info("查找地区信息{}不存在!",branchCompanyDto.getAreaId());
			}
		}
	}
  
	@RequiresPermissions("member:branchCompany:edit")
	@RequestMapping(value="form")
	public String form(Model model,FindBranchCompany branchCompanyDto, Integer pageNo,Integer pageSize){
	    try {
	    	String shopNoStr = null;
	    	String shopNameStr = null;
//	    	List<FindShopPageReturn> companyShop = null;
			if(branchCompanyDto !=null && branchCompanyDto.getCode()!=null){
			    FindBranchCompanyDetailReturn  adDto = branchCompanyService.findBranchCompanyDetail(branchCompanyDto);
				model.addAttribute("data", adDto);
				//查找分公司绑定的终端
//				if(!StringUtils.isEmpty(adDto.getCompanyNo())){
//					FindShop findShop = new FindShop();
//					findShop.setCompanyNo(adDto.getCompanyNo());
//					companyShop= shopService.findShops(findShop);
//					for(FindShopPageReturn shop:companyShop){
//						shopNoStr += shop.getShopNo()+",";
//						shopNameStr += shop.getShopName()+",";
//					}
//					//去除末位英文逗号
//					if(!companyShop.isEmpty()){
//						shopNoStr=shopNoStr.substring(0,shopNoStr.length()-1);
//						shopNameStr=shopNameStr.substring(0,shopNameStr.length()-1);
//					}
//				}
			}
			
			model.addAttribute("CompanyType", CompanyType.values());
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			
			model.addAttribute("shopNos",StringUtils.strip(shopNoStr, "null"));
			model.addAttribute("shopNames",StringUtils.strip(shopNameStr, "null"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	    return FORM;
	}
	
	@RequiresPermissions("member:branchCompany:edit")
	@RequestMapping(value="edit")
	public String edit(RedirectAttributes redirectAttributes,UpdateBranchCompany branchCompanyDto, Integer pageNo,Integer pageSize,String shopNos){
		try {
			
			if(StringUtils.isNotBlank(branchCompanyDto.getCode())){
				branchCompanyService.updateBranchCompany(branchCompanyDto);
				addMessage(redirectAttributes, "修改成功！");
				//绑定旗下终端
				bindShop(branchCompanyDto, shopNos);
			}
			redirectAttributes.addAttribute("pageNo", pageNo);
			redirectAttributes.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
		  logger.error(e.getMessage(),e);
		  addMessage(redirectAttributes, "修改失败,系统出现异常！");
		}
		return REDIRECT_LIST;
	}

	/**
	 * 绑定旗下终端
	 * @param branchCompanyDto
	 * @param shopNos
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月13日上午8:47:29
	 */
	public void bindShop(UpdateBranchCompany branchCompanyDto, String shopNos) {
		/*FindShop findShop = new FindShop();
		findShop.setMemberNoMerchant(UserUtils.getMerchantNo());
		findShop.setCompanyNo(branchCompanyDto.getCompanyNo());
		List<FindShopPageReturn> shops= shopService.findShops(findShop);
		for(FindShopPageReturn findShopPageReturn :shops){
			UpdateShop updateShop = new UpdateShop();
			updateShop.setShopNo(findShopPageReturn.getShopNo());
			updateShop.setRemark4("delect");
			shopService.updateShopCompanyNo(updateShop);
		}
		//终端绑定经销商编号
		if(StringUtils.isNotEmpty(shopNos)) {
			//修改终端信息
			String[] shopNo = shopNos.split(",");
			String companyNo = branchCompanyDto.getCompanyNo();
			String companyName = branchCompanyDto.getCompanyName();
			for(String str:shopNo){
				if(StringUtils.isNotBlank(str)){
					UpdateShop shop = new UpdateShop();
					shop.setShopNo(str);
					shop.setCompanyNo(companyNo);
					shop.setCompanyName(companyName);
					shop.setRemark4("update");
					shopService.updateShopCompanyNo(shop);
				}
			}
		}*/
	}
	
	/**
	 * 查看详情
	 * @param model
	 * @param branchCompanyDto
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年7月12日下午5:40:31
	 */
	@RequiresPermissions("member:branchCompany:view")
	@RequestMapping(value="view")
	public String view(Model model,FindBranchCompany branchCompanyDto, Integer pageNo,Integer pageSize){
	    try {
			if(branchCompanyDto !=null && branchCompanyDto.getCode()!=null){
				FindBranchCompanyDetailReturn  adDto = branchCompanyService.findBranchCompanyDetail(branchCompanyDto);
				model.addAttribute("data", adDto);
			}
			model.addAttribute("CompanyType", CompanyType.values());
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	    return VIEW;
	}
	
	@RequiresPermissions("member:branchCompany:edit")
	@RequestMapping(value="save")
	public String save(RedirectAttributes redirectAttributes,AddBranchCompany branchCompanyDto){
		try {
			branchCompanyDto.setMerchantNo(UserUtils.getMerchantNo());
			branchCompanyService.addBranchCompany(branchCompanyDto);
			addMessage(redirectAttributes, "保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			 addMessage(redirectAttributes, "保存失败,系统出现异常！");
		}
		return REDIRECT_LIST;
	}
}
