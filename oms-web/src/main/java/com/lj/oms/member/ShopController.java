// package com.lj.oms.member;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.math.BigDecimal;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.ape.common.config.Global;
//import com.ape.common.utils.CacheUtils;
//import com.ape.common.utils.StringUtils;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.lj.base.core.pagination.Page;
//import com.lj.base.core.util.DateUtils;
//import com.lj.base.core.util.GUID;
////import com.lj.business.mec.dto.FindMcProductCategoryPage;
////import com.lj.business.mec.dto.McProductCategoryDto;
////import com.lj.business.mec.service.IMcProductCategoryService;
//import com.lj.business.member.dto.AddShop;
//import com.lj.business.member.dto.FindGuidMemberPage;
//import com.lj.business.member.dto.FindGuidMemberPageReturn;
//import com.lj.business.member.dto.FindManagerMemberPage;
//import com.lj.business.member.dto.FindManagerMemberPageReturn;
//import com.lj.business.member.dto.FindShop;
//import com.lj.business.member.dto.FindShopPage;
//import com.lj.business.member.dto.FindShopPageReturn;
//import com.lj.business.member.dto.FindShopReturn;
//import com.lj.business.member.dto.UpdateShop;
//import com.lj.business.member.dto.company.BranchCompanyDto;
//import com.lj.business.member.dto.shopterminal.UpdateShopTerminal;
//import com.lj.business.member.emus.MecShopType;
//import com.lj.business.member.emus.MemberStatus;
//import com.lj.business.member.emus.MemberType;
//import com.lj.business.member.emus.ProductType;
//import com.lj.business.member.emus.ShopLife;
//import com.lj.business.member.emus.ShopStatus;
//import com.lj.business.member.service.IGuidMemberService;
//import com.lj.business.member.service.IManagerMemberService;
//import com.lj.business.member.service.IShopService;
//import com.lj.business.member.service.IShopTerminalService;
////import com.lj.business.sap.dto.mec.FindSapShopCommonPage;
////import com.lj.business.sap.dto.mec.FindSapShopInfoPage;
////import com.lj.business.sap.dto.mec.SapShopCommonDto;
////import com.lj.business.sap.dto.mec.SapShopInfoDto;
////import com.lj.business.sap.emus.DealerTrueEmus;
////import com.lj.business.sap.service.mec.ISapShopAreaService;
////import com.lj.business.sap.service.mec.ISapShopCommonService;
////import com.lj.business.sap.service.mec.ISapShopInfoService;
//import com.lj.business.st.domain.WorkRatioShop;
//import com.lj.business.st.emus.DimensionSt;
//import com.lj.business.st.service.IWorkRatioAreaService;
//import com.lj.business.st.service.IWorkRatioShopService;
//import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
//import com.lj.cc.enums.SystemAliasName;
//import com.lj.oms.common.BaseController;
//import com.lj.oms.emus.AreaType;
//import com.lj.oms.entity.sys.Area;
//import com.lj.oms.entity.sys.Office;
//import com.lj.oms.entity.sys.Role;
//import com.lj.oms.service.AreaHessianService;
//import com.lj.oms.service.sys.AreaService;
//import com.lj.oms.utils.DictUtils;
//import com.lj.oms.utils.JsonPage;
//import com.lj.oms.utils.QrcodeUtils;
//import com.lj.oms.utils.UserUtils;
//import com.lj.oms.utils.Validator;
//import com.lj.oms.utils.excel.ExportExcel;
//import com.lj.oms.utils.excel.ImportExcel;
//import com.lj.oms.utils.excel.dto.ShopExportDto;
//import com.lj.oms.utils.excel.dto.ShopImportDto;
//
///**
// * 
// * 
// * 类说明：门店Controller
// *  
// * 
// * <p>
// * 详细描述：
// *   
// * @Company: 扬恩科技有限公司
// * @author 段志鹏
// *   
// * CreateDate: 2017年7月6日
// */
//@Controller
//@RequestMapping(value = "${adminPath}/member/shop")
//public class ShopController  extends BaseController{
//	
//	private static final String PROVINCE_NAME = "PROVINCE_NAME";
//	/** 门店编辑页面 **/
//	private static final String PAGE_VIEW_SHOP_FORM = "modules/shop/shopForm";
//	/** 门店列表页面 **/
//	private static final String PAGE_VIEW_SHOP_LIST = "modules/shop/shopList";
//	/** 重定向到门店列表页面 **/
//	private static final String PAGE_VIEW_REDIRECT_SHOP_LIST = "redirect:" + Global.getAdminPath() + "/member/shop/";
//	/** 门店分布页面 **/
//	private static final String PAGE_VIEW_SHOP_INDEX = "modules/shop/shopIndex";
//	/** 门店排行页面 **/
//	private static final String PAGE_VIEW_SHOP_RANKING = "modules/shop/shopRanking";
//	/** 门店详情页面 **/
//	private static final String PAGE_VIEW_SHOP_VIEW = "modules/shop/shopView";
//	/** 重定向到门店列表页面 **/
//	private static final String PAGE_VIEW_REDIRECT_SHOP_REPAGE = "redirect:" + Global.getAdminPath() + "/member/shop/?repage";
//
//	/**
//	 * Logger for this class
//	 */
//	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
//
////	@Autowired
////	private IShopService shopService;					//门店服务
//	@Autowired	
//	private AreaService areaservice;					//区域服务 
//	@Resource
//	private AreaHessianService areaHessianService; // 地区服务
//	@Autowired
//	private IManagerMemberService managerMemberService; //管理人员服务
//	@Autowired
//	private IGuidMemberService guidMemberService; 		//导购服务
////	@Autowired
////	private IClientFollowSummaryService clientFollowSummaryService; 		//跟踪总服务
//	@Autowired
//	private IWorkRatioAreaService workRatioAreaService;			//门店区域分布统计服务
//	@Autowired
//	private IWorkRatioShopService workRatioShopService;			//门店工作统计服务
//	 
//	@Resource
//	private IShopTerminalService shopTerminalService;
// 
//	@Resource
//	private LocalCacheSystemParamsFromCC localCacheSystemParams;
//	
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：门店列表
//	 *
//	 * @param model
//	 * @param pageNo
//	 * @param pageSize
//	 * @param findShopPage
//	 * @param shopLife
//	 * @return
//	 *
//	 * @author 段志鹏 CreateDate: 2017年7月14日
//	 *
//	 */
//	@RequiresPermissions("member:shop:view")
//	@RequestMapping(value = { "list", "" })
//	public String list(Model model,Integer pageNo,Integer pageSize,FindShopPage findShopPage,ShopLife shopLife) {
//		try {
//			findShopPage.setMemberNoMerchant(UserUtils.getMerchantNo());
//			if(pageNo!=null){
//				findShopPage.setStart((pageNo-1)*pageSize);
//			}
//			if(pageSize!=null){
//				findShopPage.setLimit(pageSize);
//			}
//			findShopPage.setStartTime(DateUtils.getDateByFirstSeconds(findShopPage.getStartTime()));
//			findShopPage.setEndTime(DateUtils.getDateByLastSeconds(findShopPage.getEndTime()));
//			if(shopLife!=null){	//开店年限计算
//				findShopPage.setEndTime(DateUtils.addMonths(new Date(), -shopLife.getBeginMonth()));
//			}
//			
////			Page<FindShopPageReturn> pageDto = shopService.findShopPage(findShopPage);
//			List<FindShopPageReturn> list = Lists.newArrayList();
////			list.addAll(pageDto.getRows());
//			
//			
//			//查询店铺的店长
//			FindGuidMemberPage findManagerGuid = new FindGuidMemberPage();
//			findManagerGuid.setRoleType(Role.ENNAME_SHOP_MANAGER);
//			findManagerGuid.setMerchantNo(UserUtils.getMerchantNo());
//			findManagerGuid.setStatus(MemberStatus.NORMAL.toString());
////			FindConsumeAmtTotal findConsumeAmtTotal = new FindConsumeAmtTotal();
////			findConsumeAmtTotal.setMerchantNo(findManagerMemberPage.getMemberNoMerchant());
//			
//			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
//			for (FindShopPageReturn findShopPageReturn : list) {
//				findManagerGuid.setShopNo(findShopPageReturn.getShopNo());//每個店的店長
//				 
//				// 设置店员数量 导购
//				findShopPageReturn.setClerkNum(findShopPageReturn.getGuidNum());
//				
//				List<FindGuidMemberPageReturn> managerGuids = guidMemberService.findGuidMembers(findManagerGuid);
//				if (null != managerGuids && managerGuids.size() > 0) {
//					findShopPageReturn.setMemberNameShop(managerGuids.get(0).getMemberName());
//					findShopPageReturn.setMemberMobileShop(managerGuids.get(0).getMobile());
//				}
//				findGuidMemberPage.setShopNo(findShopPageReturn.getShopNo());
//				List<FindGuidMemberPageReturn> gmList = guidMemberService.findGuidMembers(findGuidMemberPage);
//				if (gmList != null && gmList.size() > 0) {
//					List<String> strList = Lists.newArrayList();
//					for (FindGuidMemberPageReturn guid : gmList) {
//						strList.add(guid.getMemberNo());
//					}
//				} else {
//					findShopPageReturn.setOrderAmount(0L);
//				}
//			}
////			com.ape.common.persistence.Page<FindShopPageReturn> page = new com.ape.common.persistence.Page<FindShopPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
////			page.initialize();
////			model.addAttribute("page",page);
//			
//			//门店状态
//			model.addAttribute("shopStatus",ShopStatus.values());
//			//开店年限
//			model.addAttribute("shopLifes",ShopLife.values());
//			
//			//参数
//			model.addAttribute("param",findShopPage);
//			model.addAttribute("shopLife",shopLife);
//			
//		} catch (Exception e) {
//			logger.error("获取门店信息错误！", e);
//		}
//		
//		return PAGE_VIEW_SHOP_LIST;
//	}
//
//	/**
//	 * 
//	 *
//	 * 方法说明：门店详情
//	 *
//	 * @param findShop
//	 * @param model
//	 * @return
//	 *
//	 * @author 段志鹏 CreateDate: 2017年7月14日
//	 *
//	 */
//	@RequiresPermissions("member:shop:view")
//	@RequestMapping(value = "form")
//	public String form(FindShop findShop, Model model) {
//		try {
//			if (findShop != null && findShop.getCode() != null) {
//				FindShopReturn findShopReturn = shopService.findShop(findShop);
//				findShopReturn.setPageNo(findShop.getPageNo());
//				model.addAttribute("data", findShopReturn);
//			}
//			
//			findShop.setMemberNoMerchant(UserUtils.getMerchantNo());
//			List<FindShopPageReturn> shops= shopService.findShops(findShop);
//			model.addAttribute("shops", shops);
//			model.addAttribute("shopStatus", ShopStatus.values());
//			model.addAttribute("mecShopType",MecShopType.values());
//		} catch (Exception e) {
//			logger.error("获取门店详情信息错误！", e);
//		}
//		return PAGE_VIEW_SHOP_FORM;
//	}
//	 /**
//	  * 
//	  *
//	  * 方法说明：输入框校验
//	  *
//	  * @param shopNoMerchant
//	  * @return
//	  *
//	  * @author 罗书明 CreateDate: 2017年11月21日
//	  *
//	  */
//	@RequestMapping(value="verify")
//	@ResponseBody
//    public boolean verify(String shopNoMerchant,String oldShopNoMerchant){
//		if (StringUtils.isNotBlank(shopNoMerchant) && shopNoMerchant.equals(oldShopNoMerchant)) {
//			return true;
//		} 
//		FindShop findShop= new FindShop();
//		findShop.setMemberNoMerchant(UserUtils.getMerchantNo());
//		findShop.setShopNoMerchant(shopNoMerchant);
//		List<FindShopPageReturn> shopReturns= shopService.findShops(findShop);
//		
//		if (StringUtils.isNotBlank(shopNoMerchant) && shopReturns.size()<=0) {
//			return true;
//		}
//		return false;
//    }
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：新增门店
//	 *
//	 * @param addShop
//	 * @param lonLat
//	 * @param model
//	 * @param redirectAttributes
//	 * @return
//	 *
//	 * @author 段志鹏 CreateDate: 2017年7月14日
//	 *
//	 */
//	@RequiresPermissions("member:shop:edit")
//	@RequestMapping(value = "save")
//	public String save(AddShop addShop,String lonLat, Model model,RedirectAttributes redirectAttributes) {
//		try {
//			addShop.setMemberNoMerchant(UserUtils.getMerchantNo());
//			addShop.setMemberNameMerchant(UserUtils.getUser().getCompany().getName());
//			addShop.setShopNo(GUID.getPreUUID());
//			// 排除重复门店
//			FindShop findShops = new FindShop();
//			findShops.setMemberNoMerchant(UserUtils.getMerchantNo());
//			findShops.setShopNoMerchant(addShop.getShopNoMerchant());
//			List<FindShopPageReturn> findShopPageReturn = shopService.findShopNoByCode(findShops);
//			if (findShopPageReturn.size() > 0) {
//				addMessage(redirectAttributes, "门店代码已存在！");
//				return PAGE_VIEW_REDIRECT_SHOP_LIST;
//			}
//			// 根据ID获取CODE
//			Area area = areaservice.get(addShop.getCityAreaCode());
//			addShop.setCityAreaCode(area.getCode());
//			// 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
//			if (AreaType.AREA.getValue().equals(area.getType())) {
//				Area city = areaservice.get(area.getParentId());
//				addShop.setCityCode(city.getCode()); // 市
//				Area province = areaservice.get(city.getParentId());
//				addShop.setProvinceCode(province.getCode());// 省
//			} else if (AreaType.CITY.getValue().equals(area.getType())) {
//				Area province = areaservice.get(area.getParentId());
//				addShop.setProvinceCode(province.getCode());// 省
//			}
//			// 处理经纬度
//			if (lonLat != null && !lonLat.trim().equals(",") && lonLat.contains(",")) {
//				String[] str = StringUtils.split(lonLat, ",");
//				if (str.length > 0) {
//					addShop.setLongitude(str[0]);
//					addShop.setLatitude(str[1]);
//				}
//			}
//			
//			 //经销商级别则自动绑定到当前门店
//			 if(Office.GRADE_3.equals( UserUtils.getUser().getOffice().getGrade())){
//				 addShop.setCompanyNo( UserUtils.getUser().getOffice().getId());
//				 addShop.setCompanyName( UserUtils.getUser().getOffice().getName());
//			 }
//			 
//			// 新增用户管理的门店
////			List<String> shopList = CcUtils.getShopNoList();
////			if (null != shopList && shopList.size() > 0) {
////				String shopNo = null, shopNos = null;
////				int shopLength = shopList.size();
////				if (shopLength > 1) {
////					for (String str : shopList) {
////						shopNo += str + ",";
////					}
////					shopNos = shopNo + addShop.getShopNo();
////					User user = new User();
////					user.setId(UserUtils.getUser().getId());
////					user.setStatus(CommonConstant.YES);
////					systemService.updateUserInfo(user);
////				}
////
////			}
//
//			// addShop.setOpenDate(new Date());
//			shopService.addShop(addShop);
//			addMessage(redirectAttributes, "保存门店'" + addShop.getShopName() + "'成功");
//		} catch (Exception e) {
//			logger.error("新增门店信息错误！", e);
//		}
//
//		return PAGE_VIEW_REDIRECT_SHOP_LIST;
//
//	}
//
//	/**
//	 * 
//	 *
//	 * 方法说明：编辑门店
//	 *
//	 * @param updateShop
//	 * @param lonLat
//	 * @param model
//	 * @param redirectAttributes
//	 * @return
//	 *
//	 * @author 段志鹏 CreateDate: 2017年7月14日
//	 *
//	 */
//	@RequiresPermissions("member:shop:edit")
//	@RequestMapping(value = "edit")
//	public String edit(UpdateShop updateShop,String lonLat, Model model,RedirectAttributes redirectAttributes) {
//		try {
//			//根据ID获取CODE
//			Area area =areaservice.get(updateShop.getCityAreaCode());
//			if(area!=null){
//				updateShop.setCityAreaCode(area.getCode());
//				//区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
//				if(AreaType.AREA.getValue().equals(area.getType())){
//					Area city =areaservice.get(area.getParentId());
//					updateShop.setCityCode(city.getCode());		//市
//					Area province =areaservice.get(city.getParentId());
//					updateShop.setProvinceCode(province.getCode());//省
//				}else if(AreaType.CITY.getValue().equals(area.getType())){
//					Area province =areaservice.get(area.getParentId());
//					updateShop.setProvinceCode(province.getCode());//省
//					updateShop.setCityCode("");//市
//				}
//			}
//			
//			//处理经纬度
//			if(lonLat!=null && lonLat.contains(",")){
//				String[] str= StringUtils.split(lonLat, ",");
//				if(str.length>0){
//					updateShop.setLongitude(str[0]);
//					updateShop.setLatitude(str[1]);
//				}
//			}
//			//同步修改门店信息到终端门店管理
//			UpdateShopTerminal updateShopTerminal = new UpdateShopTerminal();
//			updateShopTerminal.setShopNo(updateShop.getShopNo());
//			updateShopTerminal.setShopName(updateShop.getShopName());
//			shopTerminalService.updateShopTerminalShopName(updateShopTerminal);
//			shopService.updateShop(updateShop);
//			addMessage(redirectAttributes, "保存门店'" + updateShop.getShopName()+ "'成功");
////			FindShopPage findShopPage = new FindShopPage();
////			findShopPage.setMemberNoMerchant(UserUtils.getMerchantNo());
////			return list(model, updateShop.getPageNo(), 10, findShopPage, null);
//		} catch (Exception e) {
//			logger.error("编辑门店信息错误！", e);
//		}
//		
//		return PAGE_VIEW_REDIRECT_SHOP_LIST;
//	}
//
//	/**
//	 * 
//	 *
//	 * 方法说明：门店首页-门店分布
//	 *
//	 * @return
//	 *
//	 * @author 段志鹏 CreateDate: 2017年7月27日
//	 *
//	 */
//	@RequestMapping(value = { "index" })
//	public String index(Model model, Integer pageNo,Integer pageSize,String provinceCode,String areaCode,Date startTime,Date endTime) {
//		FindShopPage findShopPage = new FindShopPage();
//		try {
//			findShopPage.setMemberNoMerchant(UserUtils.getMerchantNo());
//			//门店分布列表初始化全国数据	段志鹏	2017-07-31	start
//			Page<FindShopPageReturn> pageDto = shopService.findShopPage(findShopPage);
//			List<FindShopPageReturn> list = Lists.newArrayList();
//			list.addAll(pageDto.getRows());
//			List<Map<String,Object>> returnList = Lists.newArrayList();
//			Map<String,Object> returnmap = null;
//			for (FindShopPageReturn findShopPageReturn : list) {
//				returnmap = Maps.newHashMap();
//				returnmap.put("shopNo", findShopPageReturn.getShopNo());
//				returnmap.put("shopName", findShopPageReturn.getShopName());
//				returnList.add(returnmap);
//			}
//			JsonPage<Map<String,Object>> page = new JsonPage<Map<String,Object>>(0, pageDto.getLimit(), pageDto.getTotal(), returnList);
//		  	page.initialize();
//			model.addAttribute("page",page);	
//			//门店分布列表初始化数据	段志鹏	2017-07-31	end
//			
//			//地图数据 	段志鹏	2017-07-31	start
//			Map<String,Object> parmMap = new HashMap<String,Object>();
//			parmMap.put("merchantNo",findShopPage.getMemberNoMerchant());
//			parmMap.put("dimensionSt",DimensionSt.PROVINCE.toString());
//			Date now = new Date();
//			Date yesterday = DateUtils.addDays(now, -1);
//			parmMap.put("startTime", DateUtils.getDateByFirstSeconds(yesterday));
//			parmMap.put("endTime", DateUtils.getDateByLastSeconds(yesterday));
//			List<Map<String,Object>> provinces= workRatioAreaService.findBroupProvince(parmMap);
//			for (Map<String, Object> map : provinces) {
//				if(map.get(PROVINCE_NAME)!=null){
//					map.put(PROVINCE_NAME, map.get(PROVINCE_NAME).toString().replaceAll("省", "").replaceAll("市", "").replaceAll("特别行政区", "").replaceAll("自治区", ""));	
//				}
//				
//			}
//			model.addAttribute("provinces",provinces);
//			//地图数据 	段志鹏	2017-07-31	end
//			
//			//开店年限饼图数据	段志鹏	2017-07-31	start
//			List<Map<String,Object>> shopLifes = Lists.newArrayList();
//			parmMap.clear();
//			parmMap.put("merchantNo",findShopPage.getMemberNoMerchant());
//			parmMap.put("dimensionSt",DimensionSt.SHOP.toString());
//			//开店年限计算
//			shopLifes.add(buildShopLife(parmMap, now, ShopLife.MARCH));		// 三个月-六个月
//			shopLifes.add(buildShopLife(parmMap, now, ShopLife.JUNE));		// 六个月-12个月
//			shopLifes.add(buildShopLife(parmMap, now, ShopLife.ONEYEAR));	// 一年-二年
//			shopLifes.add(buildShopLife(parmMap, now, ShopLife.TWOYEARS));	// 二年-三年
//			model.addAttribute("shopLifes",shopLifes);
//			//开店年限饼图数据	段志鹏	2017-07-31	end
//			
//		} catch (Exception e) {
//			logger.error("门店数据统计异常！", e);
//		}
//		return PAGE_VIEW_SHOP_INDEX;
//	}
//
//	/**
//	 * 
//	 *
//	 * 方法说明：获取门店开店年限统计数据
//	 *
//	 * @param parmMap
//	 * @param now
//	 * @param shopLife
//	 * @return
//	 *
//	 * @author 曾垂瑜 CreateDate: 2018年4月9日
//	 *
//	 */
//	private Map<String,Object> buildShopLife(Map<String, Object> parmMap, Date now, ShopLife shopLife) {
//		Map<String,Object> shopLifeMap = Maps.newHashMap();
//		shopLifeMap.put("shopLife", shopLife);
//		shopLifeMap.put("name", shopLife.getBeginMonth() + "-" + shopLife.getEndMonth() + "个月");
//		parmMap.put("openDateStart",DateUtils.addMonths(now, -shopLife.getBeginMonth()));
//		parmMap.put("openDateEnd",DateUtils.addMonths(now, -shopLife.getEndMonth()));
//		shopLifeMap.put("value", workRatioShopService.findWorkRatioShopCount(parmMap));
//		return shopLifeMap;
//	}
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：门店分布Json分页
//	 *	门店分布数据维度 按 省份统计
//	 *	开店年限数据按商户统计
//	 * @param findShopPage
//	 * @param model
//	 * @param pageNo
//	 * @param pageSize
//	 * @return
//	 *
//	 * @author 段志鹏 CreateDate: 2017年7月27日
//	 *
//	 */
//	@RequestMapping(value = { "shopPageJson" })
//	@ResponseBody
//	public JsonPage<Map<String,Object>> shopPageJson(Integer pageNo,Integer pageSize,FindShopPage findShopPage,ShopLife shopLife) {
//		JsonPage<Map<String, Object>> page = null;
//		try {
//			findShopPage.setMemberNoMerchant(UserUtils.getMerchantNo());
////			findShopPage.setShopNos(CcUtils.getShopNoList());
//			if (pageNo != null) {
//				findShopPage.setStart((pageNo - 1) * pageSize);
//			}
//			if (pageSize != null) {
//				findShopPage.setLimit(pageSize);
//			}
//			if (shopLife != null) {
//				Date now = new Date();
//				// 开店年限计算
//				findShopPage.setStartTime(DateUtils.addMonths(now, -shopLife.getEndMonth()));
//				findShopPage.setEndTime(DateUtils.addMonths(now, -shopLife.getBeginMonth()));
//			}
//
//			Page<FindShopPageReturn> pageDto = shopService.findShopPage(findShopPage);
//			List<FindShopPageReturn> list = Lists.newArrayList();
//			list.addAll(pageDto.getRows());
//			List<Map<String, Object>> returnList = Lists.newArrayList();
//			Map<String, Object> map = null;
//			for (FindShopPageReturn findShopPageReturn : list) {
//				map = Maps.newHashMap();
//				map.put("shopNo", findShopPageReturn.getShopNo());
//				map.put("shopName", findShopPageReturn.getShopName());
//				returnList.add(map);
//			}
//			page = new JsonPage<Map<String, Object>>(pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), returnList);
//			page.initialize();
//		} catch (Exception e) {
//			logger.error("门店统计异常！", e);
//		}
//		return page;
//	}
//	
//
//	/**
//	 * 
//	 *
//	 * 方法说明：月度新开门店
//	 *
//	 * @return
//	 *
//	 * @author 段志鹏 CreateDate: 2017年7月1日
//	 *
//	 */
//	@RequestMapping(value = { "opening" })
//	public String opening() {
//		return "modules/shop/shopOpening";
//	}
//
//	/**
//	 * 
//	 *
//	 * 方法说明：门店排行
//	 *
//	 * @return
//	 *
//	 * @author 段志鹏 CreateDate: 2017年7月14日
//	 *
//	 */
//	@RequestMapping(value = { "ranking" })
//	public String ranking(Model model,Integer pageNo,Integer pageSize,Date startTime,Date endTime,String areaCode,String shopName) {
//		Map<String,Object> parmMap = new HashMap<String,Object>();
//		try {
//			parmMap.put("merchantNo",UserUtils.getMerchantNo());	
////		    parmMap.put("shopNos", CcUtils.getShopNoList());
//			parmMap.put("limit",pageSize!=null?pageSize:10);
//			parmMap.put("start",pageNo!=null?(pageNo-1)*pageSize:0);
//			parmMap.put("dimensionSt",DimensionSt.SHOP.toString());
//			if(StringUtils.isNotBlank(areaCode)){
//				parmMap.put("areaCode",areaCode);
//			}
//			if(StringUtils.isNotBlank(shopName)){
//				parmMap.put("shopName",shopName);
//			}
//			if(startTime !=null){
//				parmMap.put("startTime",startTime);
//			}else{			
//				//获取当前时间的前一天
//				Date data=DateUtils.getPreday(org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH) );
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//截取日期
//			 	String date=sdf.format(data);
//				parmMap.put("startTime",date);
//			}
//			if(endTime !=null){
//				parmMap.put("endTime",endTime);
//			}
//			Page<WorkRatioShop> pageDto= workRatioShopService.findWorkRatioShopPage(parmMap);
//			List<WorkRatioShop> list = Lists.newArrayList();
//			list.addAll(pageDto.getRows());
//			for(WorkRatioShop ratioShop:list){
//				if(ratioShop.getNumSale() != null){
//					ratioShop.setNumSale(ratioShop.getNumSale()/100L);
//				}
//			}
//			
//			for(WorkRatioShop workRatioShop:list){
//				if(workRatioShop.getNumOrder()!= 0){
//					// 非邀约型成单率 = 成单数 / 总客户数
//					if(ProductType.NOINVITE.name().equals(CacheUtils.get(UserUtils.USER_MERCHANT_PRODUCT_TYPE+UserUtils.getUser().getName()))) {
//						if(workRatioShop.getNumPm() == 0) {
//							workRatioShop.setOrderRate(0D);
//						} else {
//							workRatioShop.setOrderRate(new BigDecimal(workRatioShop.getNumOrder()).divide(new BigDecimal(workRatioShop.getNumPm())).doubleValue());// 订单成交率
//						}
//					} else {	// 邀约型商户成单率 = 成单数 / （意向到店客户数 + 当日成单客户数）
//						if(workRatioShop.getNumPmIntention() == 0 && workRatioShop.getNumPmOrder() == 0) {
//							workRatioShop.setOrderRate(0D);
//						} else {
//							workRatioShop.setOrderRate(new BigDecimal(workRatioShop.getNumOrder()).divide(new BigDecimal(workRatioShop.getNumPmIntention() + workRatioShop.getNumPmOrder())).doubleValue());// 订单成交率
//						}
//					}
//				}else{
//					workRatioShop.setOrderRate(0.0D);
//				}
//			}
//		    com.ape.common.persistence.Page<WorkRatioShop>  page=new com.ape.common.persistence.Page<WorkRatioShop>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
//		  	page.initialize();
//		  	model.addAttribute("page", page);
//		  	model.addAllAttributes(parmMap);
//		} catch (Exception e) {
//			 logger.error("门店排行统计异常！", e);
//		}
//	
//		return PAGE_VIEW_SHOP_RANKING;
//	}
//
//	/**
//	 * 
//	 *
//	 * 方法说明：门店详情Json
//	 *
//	 * @param model
//	 * @param code
//	 * @return
//	 *
//	 * @author 段志鹏 CreateDate: 2017年7月14日  
//	 *
//	 */
//	@RequestMapping(value = { "viewJson" })
//	@ResponseBody
//	public FindShopPageReturn viewJson(Model model, String code,String shopNo) {
//		try {
//			FindShop findShop = new FindShop();
//			findShop.setCode(code);
//			findShop.setShopNo(shopNo);
//		
//			List<FindShopPageReturn> list = shopService.findShops(findShop);
//			FindShopPageReturn findShopReturn =null;
//			if(list!=null &&list.size()>0){
//				findShopReturn = list.get(0);
//			}
//			//获取店长名称，存在多个
//			FindGuidMemberPage findManagerGuid = new FindGuidMemberPage();
//			findManagerGuid.setRoleType(Role.ENNAME_SHOP_MANAGER);
//			findManagerGuid.setMerchantNo(UserUtils.getMerchantNo());
//			findManagerGuid.setStatus(MemberStatus.NORMAL.toString());
//			findManagerGuid.setShopNo(findShopReturn.getShopNo());//每個店的店長
//			List<FindGuidMemberPageReturn> shops = guidMemberService.findGuidMembers(findManagerGuid);
//			if(shops!=null && shops.size()>0) {
//				findShopReturn.setMemberNameShop(shops.get(0).getMemberName());
//			}
//			
//			findShopReturn.setStatus(ShopStatus.valueOf(findShopReturn.getStatus()).getName());
//			findShopReturn.setAreaCode(DictUtils.getDictLabel(findShopReturn.getAreaCode(), "erp_dict_1", ""));
//			
//			findShopReturn.setProvinceCode(UserUtils.getAreaName(findShopReturn.getProvinceCode()));
//			findShopReturn.setCityCode(UserUtils.getAreaName(findShopReturn.getCityCode()));
//			findShopReturn.setCityAreaCode(UserUtils.getAreaName(findShopReturn.getCityAreaCode()));
//			return findShopReturn;
//		} catch (Exception e) {
//			 logger.error("门店详情异常！", e);
//		}
//		return null;
//	}
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：门店详情
//	 *
//	 * @param code
//	 * @param shopNo
//	 * @param model
//	 * @param shopNoMerchant
//	 * @return
//	 *
//	 * @author 曾垂瑜 CreateDate: 2018年4月9日
//	 *
//	 */
//	@RequiresPermissions("member:shop:view")
//	@RequestMapping(value = "view")
//	public String view(String code,String shopNo, Model model,String shopNoMerchant) {
//		try {
//			FindShop findShop = new FindShop();
//			findShop.setCode(code);
//			findShop.setShopNo(shopNo);
//			findShop.setShopNoMerchant(shopNoMerchant);
//			List<FindShopPageReturn> list = shopService.findShops(findShop);
//			FindShopPageReturn findShopReturn =null;
//			if(list!=null &&list.size()>0){
//				findShopReturn = list.get(0);
//			}
//			
//			//获取店长名称，存在多个
//			FindGuidMemberPage findManagerGuid = new FindGuidMemberPage();
//			findManagerGuid.setRoleType(Role.ENNAME_SHOP_MANAGER);
//			findManagerGuid.setMerchantNo(UserUtils.getMerchantNo());
//			findManagerGuid.setStatus(MemberStatus.NORMAL.toString());
//			findManagerGuid.setShopNo(findShopReturn.getShopNo());//每個店的店長
//			List<FindGuidMemberPageReturn> shops = guidMemberService.findGuidMembers(findManagerGuid);
//				
//			StringBuilder str = new StringBuilder("");
//			for (FindGuidMemberPageReturn item : shops) {
//				str.append(item.getMemberName());
//			}
//			
//			findShopReturn.setMemberNameShop(str.toString());
//			model.addAttribute("data", findShopReturn);
//			model.addAttribute("shopStatus", ShopStatus.values());
//		} catch (Exception e) {
//			 logger.error("门店详情异常！", e);
//		}
//		return PAGE_VIEW_SHOP_VIEW;
//	}
//    
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：门店数据导出
//	 *
//	 * @param response
//	 * @param findGuidMemberPage
//	 * @param pageNo
//	 * @param pageSize
//	 * @param redirectAttributes
//	 * @return
//	 *
//	 * @author 罗书明 CreateDate: 2017年7月19日
//	 *
//	 */
//	@RequiresPermissions("member:shop:view")
//    @RequestMapping(value = "export")
//    public String export(HttpServletResponse response,FindShopPage findShopPage,Integer pageNo,Integer pageSize, RedirectAttributes redirectAttributes) {
//		try {
//			findShopPage.setMemberNoMerchant(UserUtils.getMerchantNo());
////			findShopPage.setShopNos(CcUtils.getShopNoList());
//			findShopPage.setStartTime(DateUtils.getDateByFirstSeconds(findShopPage.getStartTime()));
//			findShopPage.setEndTime(DateUtils.getDateByLastSeconds(findShopPage.getEndTime()));
//
//			// 开店年限计算
//			if (StringUtils.isNotBlank(findShopPage.getShopLife())) {
//				Date now = new Date();
//				ShopLife shopLife = ShopLife.valueOf(findShopPage.getShopLife());
//				findShopPage.setStartTime(DateUtils.addMonths(now, -shopLife.getEndMonth()));
//				findShopPage.setEndTime(DateUtils.addMonths(now, -shopLife.getBeginMonth()));
//			}
//
//			List<FindShopPageReturn> list = shopService.findShopsExport(findShopPage);
//			
//			//查询店铺的店长
//			FindGuidMemberPage findManagerGuid = new FindGuidMemberPage();
//			findManagerGuid.setRoleType(Role.ENNAME_SHOP_MANAGER);
//			findManagerGuid.setMerchantNo(UserUtils.getMerchantNo());
//			findManagerGuid.setStatus(MemberStatus.NORMAL.toString());
//			// 获取店长,一家分店可能多个店长
//			for (FindShopPageReturn findShopPageReturn : list) {
//				String status = findShopPageReturn.getStatus();
//
//				if (StringUtils.isNoneBlank(status)) {
//					findShopPageReturn.setStatus(ShopStatus.valueOf(status).getName());
//				}
//				// 设置店员数量 导购
//				findShopPageReturn.setClerkNum(findShopPageReturn.getGuidNum());
//				
//				// 获取店长名称，存在多个
//				findManagerGuid.setShopNo(findShopPageReturn.getShopNo());//每個店的店長
//				List<FindGuidMemberPageReturn> managerGuids = guidMemberService.findGuidMembers(findManagerGuid);
//				if (null != managerGuids && managerGuids.size() > 0) {
//					findShopPageReturn.setMemberMobileShop(managerGuids.get(0).getMobile());//店长电话
//				}
//				StringBuilder str = new StringBuilder();
//				for (FindGuidMemberPageReturn item : managerGuids) {
//					str.append(item.getMemberName()).append(",");
//				}
//				
//				if(str.length()>0){
//					findShopPageReturn.setMemberNameShop(str.toString().substring(0,str.length()-1));
//				}else{
//					findShopPageReturn.setMemberNameShop("");
//				}
//
//				// 统计门店成单金额
//				// 非邀约型商户查询Client_Consume表
//				/*if (UserUtils.getProductType().equals(ProductType.NOINVITE.toString())) {
//					FindConsumeAmtTotal findConsumeAmtTotal = new FindConsumeAmtTotal();
//					findConsumeAmtTotal.setMerchantNo(UserUtils.getMerchantNo());
//					findConsumeAmtTotal.setShopNo(findShopPageReturn.getShopNo());
//					FindConsumeAmtTotalReturn total = clientConsumeService.findConsumeAmtTotal(findConsumeAmtTotal);
//					findShopPageReturn.setOrderAmount(total.getTradeAmt() / 100L);
//				} else {*/ // 邀约型商户查询跟踪总表
//					FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
//					findGuidMemberPage.setShopNo(findShopPageReturn.getShopNo());
//					List<FindGuidMemberPageReturn> gmList = guidMemberService.findGuidMembers(findGuidMemberPage);
//					if (gmList != null && gmList.size() > 0) {
//						List<String> strList = Lists.newArrayList();
//						for (FindGuidMemberPageReturn guid : gmList) {
//							strList.add(guid.getMemberNo());
//						}
////						findShopPageReturn.setOrderAmount(clientFollowSummaryService.sumAmountByShop(strList) / 100L);
//					} else {
//						findShopPageReturn.setOrderAmount(0L);
//					}
////				}
//			}
//
//			String fileName = "门店数据导出.xlsx";
//			// 邀约型导出
//			new ExportExcel("门店数据导出", ShopExportDto.class, 1).setDataList(list).write(response, fileName).dispose();
//			return null;
//		} catch (Exception e) {
//			logger.error("门店数据导出失败！", e);
//			addMessage(redirectAttributes, "门店数据导出失败！失败信息：" + e.getMessage());
//		}
//		return PAGE_VIEW_REDIRECT_SHOP_REPAGE;
//    }
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：检查手机号码是否唯一
//	 *
//	 * @param oldMobile
//	 * @param mobile
//	 * @return
//	 *
//	 * @author 罗书明 CreateDate: 2017年7月22日
//	 *
//	 */
//	@ResponseBody
//	@RequiresPermissions("member:shop:edit")
//	@RequestMapping(value = "checkMobileShop")
//	public boolean mobileShop(String oldMobile, String mobile) {
//		try {
//			if (StringUtils.isNotBlank(mobile) && mobile.equals(oldMobile)) {
//				return true;
//			} 
//			FindManagerMemberPage record = new FindManagerMemberPage();
//			record.setMobile(mobile);			
//			List<FindManagerMemberPageReturn> list= managerMemberService.findManagerMembers(record);
//			if(list !=null && list.size()==0){
//				return true;
//			}
//			return false;
//		} catch (Exception e) {
//			logger.error("检查手机唯一性错误", e);
//			return true;
//		}
//		
//	}
//	
//    /**
//     * 
//     *
//     * 方法说明：门店数据导入
//     *
//     * @param file
//     * @param redirectAttributes
//     * @return
//     *
//     * @author 罗书明 CreateDate: 2017年7月22日
//     *
//     */
//	@RequiresPermissions("member:shop:edit")
//    @RequestMapping(value = "import", method=RequestMethod.POST)
//    public String importExcel(MultipartFile file, RedirectAttributes redirectAttributes,Model model) {
//		try {
//			int successNum = 0;
//			int failureNum = 0;
//			StringBuilder failureMsg = new StringBuilder();
//			ImportExcel ei = new ImportExcel(file, 1, 0);
//
//			List<ShopImportDto> list = ei.getDataList(ShopImportDto.class);
//			for (ShopImportDto user : list) {
//				try {
//					AddShop addShop = new AddShop();
//
//					/* 校验手机号格式 */
//					if (!Validator.isMobile(user.getTelephone()) && !Validator.isTelephone(user.getTelephone())) {
//						failureMsg.append("<br/>联系电话： ").append(user.getTelephone()).append("格式不正确; ");
//						failureNum++;
//						continue;
//					}
//					// 关联商户
//					addShop.setMemberNoMerchant(UserUtils.getMerchantNo());
//					addShop.setMemberNameMerchant(UserUtils.getUser().getCompany().getName());
//
//					addShop.setShopNoMerchant(user.getShopNoMerchant());
//					addShop.setShopName(user.getShopName());
//
//					// 排除重复门店
//					FindShop findShops = new FindShop();
//					findShops.setMemberNoMerchant(UserUtils.getMerchantNo());
//					findShops.setShopNoMerchant(user.getShopNoMerchant());
//					List<FindShopPageReturn> findShopPageReturn = shopService.findShopNoByCode(findShops);
//					if (findShopPageReturn.size() > 0) {
//						failureMsg.append(user.getShopNoMerchant()).append("代码已存在!");
//						failureNum++;
//						continue;
//					}
//					for (ShopStatus status : ShopStatus.values()) {
//						if (status.getName().equals(user.getStatus())) {
//							addShop.setStatus(status.toString());
//							break;
//						}
//					}
//					addShop.setOpenDate(DateUtils.parseDate(user.getOpenDate(), DateUtils.PATTERN_yyyy_MM_dd));
//					addShop.setTelephone(user.getTelephone());
//					addShop.setBizScope(user.getBizScope());
//					addShop.setShopType(user.getShopType());
//					addShop.setAreaCode(DictUtils.getDictValue(user.getAreaCode(), "erp_dict_1", ""));
//					addShop.setAreaName(user.getAreaCode());
//					addShop.setAddrInfo(user.getAddrInfo());
//					addShop.setLongitude(user.getLongitude());
//					addShop.setLatitude(user.getLatitude());
//					addShop.setShopNo(GUID.getPreUUID());
//					shopService.addShop(addShop);
//					successNum++;
//
//				} catch (Exception ex) {
//					failureMsg.append("<br/>门店 ").append(user.getShopName()).append(" 导入失败：").append(ex.getMessage());
//				}
//			}
//			if (failureNum > 0) {
//				failureMsg.insert(0, "，失败 " + failureNum + " 条门店，导入信息如下：");
//			}
////			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条门店" + failureMsg);
//			model.addAttribute("importMsg", "已成功导入 " + successNum + " 条门店" + failureMsg);
//		} catch (Exception e) {
//			logger.error("导入门店失败", e);
////			addMessage(redirectAttributes, "导入门店失败！失败信息：" + e.getMessage());
//			model.addAttribute("importMsg", "导入门店失败！失败信息：" + e.getMessage());
//		}
////		return PAGE_VIEW_REDIRECT_SHOP_REPAGE;
//		return list(model, null, null, new FindShopPage(), null);
//    }
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：数据导入模型
//	 *
//	 * @param response
//	 * @param request
//	 * @param redirectAttributes
//	 * @return
//	 *
//	 * @author 罗书明 CreateDate: 2017年7月22日
//	 *
//	 */
//	@RequiresPermissions("member:shop:view")
//    @RequestMapping(value = "import/template")
//    public String importFileTemplate(HttpServletResponse response,HttpServletRequest request, RedirectAttributes redirectAttributes) {
//		try {
//            String fileName = "门店导入模版.xlsx";
//    		List<ShopImportDto> list = Lists.newArrayList(); 
//    		ShopImportDto shopImportDto = new ShopImportDto();
//    		shopImportDto.setShopNoMerchant("SZ01");
//    		shopImportDto.setShopName("南山分店");
//    		shopImportDto.setShopType("时尚店");
//    		shopImportDto.setStatus("已经开业");
//    		shopImportDto.setOpenDate(""+DateUtils.formatDate(new Date(), DateUtils.PATTERN_yyyy_MM_dd));
//    		shopImportDto.setTelephone("07558645652");
//            shopImportDto.setBizScope("家具");        
//            shopImportDto.setAreaCode("东北地区");
//            shopImportDto.setAddrInfo("深圳南山区高新六道七号楼");
//            shopImportDto.setLongitude("114.066856");
//            shopImportDto.setLatitude("22.531378");
//    		list.add(shopImportDto);
//    		new ExportExcel("门店导入模版(请使用文本格式)", ShopImportDto.class, 2).setDataList(list).write(response, fileName).dispose();
//    		return null;
//		} catch (Exception e) {
//			addMessage(redirectAttributes, "门店导入模板下载失败！失败信息："+e.getMessage());
//		}
//		return PAGE_VIEW_REDIRECT_SHOP_REPAGE;
//    }
//	
//	/**
//	 * 方法说明：查询当前用户下的所有店铺
//	 * @return SHOP_NO SHOP_NAME
//	 * @author 李端强 CreateDate: 2017年12月12日
//	 */
//	@RequestMapping(value = "findShopsByUser")
//	@ResponseBody
//	public List<Map<String, Object>> findShopsByUser(){
//		logger.info("findShopsByUser() - start");
//		String memberNomerchant = UserUtils.getMerchantNo(); //当前用户对应的商户ID
//		List<Map<String, Object>> list = shopService.findShopNoByMerchant(memberNomerchant);
//
//		logger.info("findShopsByUser() - end - return value={}", list);
//		return list;
//	} 
//	/**
//	 * 
//	 *
//	 * 方法说明：生成二维码图片并输出到客户端
//	 *
//	 * @author 彭俊霖
//	 *   
//	 * CreateDate: 2018-06-01
//	 * 
//	 * @param shopNo
//	 * @param response
//	 * @throws UnsupportedEncodingException 
//	 */
//	@RequestMapping(value = "qrcode")
//	public void qrcode(String shopNo, HttpServletResponse response) throws UnsupportedEncodingException {
//		FindShopReturn shop = shopService.findshop(shopNo);
//		String fileName = null;
//		if(StringUtils.isNotEmpty(shop.getShopNoMerchant())) {
//			fileName = shop.getShopNoMerchant() + "-" + shop.getShopName() + ".jpg";
//		} else {
//			fileName = shop.getShopName() + ".jpg";
//		}
//		response.addHeader("Content-Disposition", "filename=" + new String(URLEncoder.encode(fileName,"utf-8")));
//		//获取二维码链接前缀
//		String codePre = localCacheSystemParams.getSystemParam(SystemAliasName.ms.name(), "qrcode", "url_pre");
//		try {
//			String url =codePre+"?shopNo="+shopNo;
//			QrcodeUtils.writeToStream(url, "jpg", response.getOutputStream());
//		} catch (IOException e) {
//			logger.error("生成二维码失败", e);
//		}
//	}
//	
//	
//	
//
//	
//
//	
//
//	
//}
