package com.lj.oms.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.business.member.domain.BranchCompany;
import com.lj.business.member.dto.UpdateShop;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.dto.hx.CopyMenuDto;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.Role;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.AreaHessianService;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.service.sys.SystemService;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;

/**
 * 机构Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController  extends BaseController{
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(OfficeController.class);

	@Autowired
	private OfficeService officeService;
	
//	@Autowired
//	private IMerchantService merchantService;
	
//	@Autowired
//	private IShopService shopService;
	
	@Resource
	private AreaHessianService areaHessianService; // 地区服务
	
//	private static final String SHOP_SHOW_LIST = "modules/sys/officeShopBind";
	/**
	 * 终端集合参数名
	 */
//	private static final String RETURN_LIST = "list"; 
	
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute("office")
	public Office get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return officeService.get(id);
		}else{
			return new Office();
		}
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {""})
	public String index(Office office, Model model) {
		return "modules/sys/officeIndex";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"list"})
	public String list(Office office, Model model) {
		if(office==null || StringUtils.isEmpty(office.getId())){
			office = UserUtils.getUser().getCompany();
		}
//	    if (office == null || StringUtils.isEmpty(office.getId())) {
//            office = new Office();
//            office.setId(UserUtils.getMerchantNo());
//        }
        model.addAttribute("list", officeService.findList(office));
        model.addAttribute("office", officeService.findOffice(office.getId()));
		return "modules/sys/officeList";
	}
	
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "form")
	public String form(Office office, Model model) {
		User user = UserUtils.getUser();
		if (office.getParent()==null || office.getParent().getId()==null){
			office.setParent(user.getOffice());
		}
		office.setParent(officeService.get(office.getParent().getId()));
		if (office.getArea()==null){
			office.setArea(user.getOffice().getArea());
		}
		// 自动获取排序号
		if (StringUtils.isBlank(office.getId())&&office.getParent()!=null){
			int size = 0;
			List<Office> list = officeService.findAll();
			for (int i=0; i<list.size(); i++){
				Office e = list.get(i);
				if (e.getParent()!=null && e.getParent().getId()!=null
						&& e.getParent().getId().equals(office.getParent().getId())){
					size++;
				}
			}
			office.setCode(office.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size+1 : 1), 3, "0"));
		}
		
		model.addAttribute("office", office);
		return "modules/sys/officeForm";
	}
	
	/**
	 * 绑定终端列表
	 * @param findShop
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月13日上午8:23:05
	 */
	/*@RequestMapping(value = "shopShowList")
	public String shopShowList(FindShopOmsDto findShopOmsDto,Model model){
		String companyNo=com.lj.base.core.util.StringUtils.toString(findShopOmsDto.getCompanyNo());
		try {
			//查询当前商户未绑定companyNo的终端
//		    findShopOmsDto.setMemberNoMerchant(UserUtils.getMerchantNo());
//		    findShopOmsDto.setBindFlag(CommonConstant.N);//有值为未绑定
//		    findShopOmsDto.setCompanyNo(companyNo);
//			List<FindShopOmsReturn> list = shopService.findShopsByMerchantNoAndShopNos(findShopOmsDto);
			
			if(StringUtils.isNotEmpty(companyNo)){//如果是编辑,加上之前勾选的终端
			    String memberNoMerchant = findShopOmsDto.getMemberNoMerchant();
//			    findShopOmsDto = new FindShopOmsDto();
			    findShopOmsDto.setMemberNoMerchant(memberNoMerchant);
			    findShopOmsDto.setBindFlag(null);
			    findShopOmsDto.setCompanyNo(companyNo);
				List<FindShopOmsReturn> list2 = shopService.findShopsByMerchantNoAndShopNos(findShopOmsDto);
				
				if(!list2.isEmpty()){
					list.addAll(list2);
				}
				model.addAttribute(RETURN_LIST, list);
			}else{
				model.addAttribute(RETURN_LIST, list);
			}
			model.addAttribute(RETURN_LIST, list);
			//终端状态
			model.addAttribute("shopStatus",ShopStatus.values());
			//查询
			model.addAttribute("reqParam",findShopOmsDto);
		} catch (Exception e) {
			logger.error("查询终端信息错误!",e);
		}
		return SHOP_SHOW_LIST;
	}*/
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "save")
	public String save(Office office, Model model, RedirectAttributes redirectAttributes,String shopNos) {
        logger.debug("save(Office office={}, Model model={}, RedirectAttributes redirectAttributes={}, String shopNos={}) - start", office, model, redirectAttributes, shopNos); 
        boolean b = true; //新增或修改标识
        if(StringUtils.isNotBlank(office.getId())){
			b = false;
        }
        officeService.save(office);
        
    	//焕新内置角色 2019.03.14
		if(b && Office.ROOT_ID.equals(office.getParent().getId()) 
				&& Office.GRADE_2.equals(office.getGrade()) 
				&& Office.TYPE_COMPANY.equals(office.getType())
				) {
			createMerchantManager(office);
			createDoctor(office);
			createAdvisory(office);
			createNurse(office);
		}//焕新内置角色 end
		
		if(office.getChildDeptList()!=null){ 
			Office childOffice = null;
			for(String id : office.getChildDeptList()){
				childOffice = new Office();
				childOffice.setName(DictUtils.getDictLabel(id, "sys_office_common", "未知"));
				childOffice.setParent(office);
				childOffice.setArea(office.getArea());
				childOffice.setType(Office.TYPE_DEPARTMENT);
				childOffice.setGrade(String.valueOf(Integer.valueOf(office.getGrade())+1));
				childOffice.setUseable(Global.YES);
				officeService.save(childOffice);
			}
		}
		
		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
        String returnString = "redirect:" + adminPath + "/sys/office/list?id=" + id + "&parentIds=" + office.getParentIds();
        logger.debug("save(Office, Model, RedirectAttributes, String) - end - return value={}", returnString); 
		return returnString;
	}

	
	/**
	   *  创建商户管理员角色
	 * @param office 
	 * @author 焕新 刘红艳 2019.03.11 add 
	 */
	private void createMerchantManager(Office office) {
		Role roleManager = new Role();
		roleManager.setOffice(office);
		roleManager.setName(office.getName()+"管理员");
		roleManager.setEnname(Role.ENNAME_MERCHANT_MANAGER);//
		roleManager.setRoleType("user");//角色类型security-role、assignment、user 
		roleManager.setSysData("0");//是否系统数据 1是 0否  0代表允许非超管修改
		roleManager.setUseable("1");//是否可用 1是 0否
		roleManager.setDataScope(Role.DATA_SCOPE_COMPANY_AND_CHILD);//数据范围：2所在公司及以下数据
		roleManager.setRemarks("系统创建角色：门诊管理员"); 
		systemService.saveRole(roleManager);
		
		//2.赋权
		copyRoleMenu(roleManager.getId(), Role.ROLEID_MERCHANT_MANAGER);
	}
	
	/**
	   *  创建医生角色
	 * @param office 
	 * @author 焕新 刘红艳 2019.03.11 add 
	 */
	private void createDoctor(Office office) {
		Role roleManager = new Role();
		roleManager.setOffice(office);
		roleManager.setName("医生");
		roleManager.setEnname(Role.ENNAME_SHOP_DOCTOR);//
		roleManager.setRoleType("user");//角色类型security-role、assignment、user 
		roleManager.setSysData("0");//是否系统数据 1是 0否  0代表允许非超管修改
		roleManager.setUseable("1");//是否可用 1是 0否
		roleManager.setDataScope(Role.DATA_SCOPE_COMPANY_AND_CHILD);//数据范围：2所在公司及以下数据
		roleManager.setRemarks("系统创建角色：医生"); 
		systemService.saveRole(roleManager);
		
		//2.赋权
		copyRoleMenu(roleManager.getId(), Role.ROLEID_SHOP_DOCTOR);
	}
	
	/**
	   *  创建咨詢師角色
	 * @param office 
	 * @author 焕新 刘红艳 2019.03.11 add 
	 */
	private void createAdvisory(Office office) {
		Role roleManager = new Role();
		roleManager.setOffice(office);
		roleManager.setName("咨询师");
		roleManager.setEnname(Role.ENNAME_SHOP_ADVISORY);//
		roleManager.setRoleType("user");//角色类型security-role、assignment、user 
		roleManager.setSysData("0");//是否系统数据 1是 0否  0代表允许非超管修改
		roleManager.setUseable("1");//是否可用 1是 0否
		roleManager.setDataScope(Role.DATA_SCOPE_COMPANY_AND_CHILD);//数据范围：2所在公司及以下数据
		roleManager.setRemarks("系统创建角色：咨询师"); 
		systemService.saveRole(roleManager);
		
		//2.赋权
		copyRoleMenu(roleManager.getId(), Role.ROLEID_SHOP_ADVISORY);
	}
	
	/**
	 * 给角色赋权
	 * @param roleId 角色ID
	 * @param templeteId 模板角色ID
	 * @author lhy 2019.03.26 add by 好乐美
	 */
	private void copyRoleMenu(String roleId,String templeteId) {
		CopyMenuDto dto = new CopyMenuDto();
		dto.setNewRoleId(roleId);
		dto.setTemplateRoleId(templeteId);
		systemService.copyRoleMenu(dto);
	}
	
	
	/**
	   *  创建护士角色
	 * @param office 
	 * @author 焕新 刘红艳 2019.03.11 add 
	 */
	private void createNurse(Office office) {
		Role roleManager = new Role();
		roleManager.setOffice(office);
		roleManager.setName("护士");
		roleManager.setEnname(Role.ENNAME_SHOP_NURSE);//
		roleManager.setRoleType("user");//角色类型security-role、assignment、user 
		roleManager.setSysData("0");//是否系统数据 1是 0否 0代表允许非超管修改
		roleManager.setUseable("1");//是否可用 1是 0否
		roleManager.setDataScope(Role.DATA_SCOPE_COMPANY_AND_CHILD);//数据范围：2所在公司及以下数据
		roleManager.setRemarks("系统创建角色：护士"); 
		systemService.saveRole(roleManager);
		
		//2.赋权
		copyRoleMenu(roleManager.getId(), Role.ROLEID_SHOP_NURSE);
	}
	
	
	@SuppressWarnings("unchecked")
	public BranchCompany changeArea(String areaId) {
		BranchCompany branchCompany=new BranchCompany();
		Area area = areaHessianService.findArea(areaId);
		if(area!=null){
			Map<String, Object> areaMap=new HashMap<String, Object>();
			switch (area.getType()) {
			case "2":
				Area province = areaHessianService.findArea(areaId);
				branchCompany.setProvinceCode(com.lj.base.core.util.StringUtils.toString(province.getCode()));
				branchCompany.setProvinceName(com.lj.base.core.util.StringUtils.toString(province.getName()));
				break;
			case "3":
			case "4":
				areaMap = areaHessianService.selectCurrentLevelByCityId(areaId);
				branchCompany.setProvinceCode(com.lj.base.core.util.StringUtils.toString(areaMap.get("province_id")));
				branchCompany.setProvinceName(com.lj.base.core.util.StringUtils.toString(areaMap.get("province_name")));
				branchCompany.setCityCode(com.lj.base.core.util.StringUtils.toString(areaMap.get("city_id")));
				branchCompany.setCityName(com.lj.base.core.util.StringUtils.toString(areaMap.get("city_name")));
				break;
			}
		}
		return branchCompany;
	}

	private void updataShop(Office office, String shopNos) {
		if(StringUtils.isEmpty(shopNos)) {
			return;
		}
		UpdateShop updateShop;
		//修改终端信息
		String[] shopNo = shopNos.split(",");
		String companyNo = office.getId();
		String companyName = office.getName();
		/*for(String str:shopNo){
		    updateShop = new UpdateShop();
			updateShop.setShopNo(str);
			updateShop.setCompanyNo(companyNo);
			updateShop.setCompanyName(companyName);
			updateShop.setRemark4("update");
			shopService.updateShopCompanyNo(updateShop);
		}*/
	}
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {
		officeService.delete(office);
		addMessage(redirectAttributes, "删除机构成功");
		return "redirect:" + adminPath + "/sys/office/list?id="+office.getParentId()+"&parentIds="+office.getParentIds();
	}

	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Office> list = officeService.findList(isAll);
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
					&& (type == null || (type != null && (type.equals(Office.ROOT_ID) ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
					&& Global.YES.equals(e.getUseable())){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				if (type != null && Office.TYPE_GROUP.equals(type)){
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}
}
