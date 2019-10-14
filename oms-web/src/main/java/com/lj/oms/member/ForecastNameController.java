//package com.lj.oms.member;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.lj.base.core.pagination.Page;
//import com.lj.base.core.util.StringUtils;
//import com.lj.business.cf.emus.FollowType;
//import com.lj.business.cf.emus.KeepType;
//import com.lj.business.cf.emus.MemerSourceType;
//import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
//import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
//import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeApiReturn;
//import com.lj.business.cm.service.IMerchantParamsService;
//import com.lj.business.cm.service.IVrMaterialTypeService;
//import com.lj.business.common.CommonConstant;
//import com.lj.business.member.dto.FindGuidMember;
//import com.lj.business.member.dto.FindGuidMemberPage;
//import com.lj.business.member.dto.FindGuidMemberPageReturn;
//import com.lj.business.member.dto.FindGuidMemberReturn;
//import com.lj.business.member.dto.FindImIndexPage;
//import com.lj.business.member.dto.FindImIndexPageReturn;
//import com.lj.business.member.dto.FindPersonMemberPage;
//import com.lj.business.member.dto.FindPersonMemberPageReturn;
//import com.lj.business.member.dto.FindPmTypePageReturn;
//import com.lj.business.member.dto.forecastName.FindForecastName;
//import com.lj.business.member.dto.forecastName.FindForecastNameReturn;
//import com.lj.business.member.dto.forecastName.UpdateForecastName;
//import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
//import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
//import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
//import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
//import com.lj.business.member.emus.Gender;
//import com.lj.business.member.emus.GuidStatus;
//import com.lj.business.member.emus.PmTypeTimeFlag;
//import com.lj.business.member.service.IForecastNameService;
//import com.lj.business.member.service.IGmAssistantShopService;
//import com.lj.business.member.service.IGuidMemberService;
//import com.lj.business.member.service.IPersonMemberService;
//import com.lj.business.member.service.IPmTypeService;
//import com.lj.business.member.service.IShopTerminalService;
//import com.lj.oms.common.BaseController;
//import com.lj.oms.utils.UserUtils;
//
//
///**
// * 
// * 
// * 类说明：预报名管理
// *  
// * 
// * <p>
// * 详细描述：
// *   
// * @Company: 扬恩科技有限公司
// * @author 彭俊霖
// * 
// * CreateDate: 2018年03月20日
// */
//
//@Controller
//@RequestMapping(value = "${adminPath}/member/forecastName")
//public class ForecastNameController  extends BaseController{
//	
//	/**  导购助手-预报名首页 **/
//	private final static String PAGE_VIEW_PRE_REGIST= "modules/im/preRegist";	
//	/**  导购助手-预报名聊天页面 **/
//	private final static String PAGE_VIEW_PRECHAT= "modules/im/prechat";	
//	
//	@Resource
//	private IForecastNameService forecastNameService;
//	@Resource
//    private IPmTypeService pmTypeService; // 客户分类服务
//	@Resource
//    private IGuidMemberService guidMemberService; // 导购服务
//	@Resource
//    private IGmAssistantShopService gmAssistantShopService;
//    @Resource
//    private IShopTerminalService shopTerminalService;
//    @Resource
//    private IPersonMemberService personMemberService;
////    @Resource
////    private IShopService shopService;
//    @Resource
//    private IVrMaterialTypeService vrMaterialTypeService;
//    @Autowired
//    private IMerchantParamsService merchantParamsService;
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：预报名修改
//	 *
//	 *
//	 * @author 彭俊霖 CreateDate: 2018年03月20日
//	 *
//	 */
//	@RequestMapping(value="form")
//	public FindForecastNameReturn form(FindForecastName findForecastName){
//		FindForecastNameReturn findForecastNameReturn=null;
//		try {
//			if(findForecastName !=null && StringUtils.isNotEmpty(findForecastName.getCode())){
//				findForecastNameReturn= forecastNameService.findForecastName(findForecastName);
//			}
//		} catch (Exception e) {
//			logger.error("获取预报名信息错误！",e);
//		}
//		return findForecastNameReturn;
//	}
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：编辑保存方法
//	 *
//	 * @param model
//	 * @param redirectAttributes
//	 * @param updateForecastName
//	 * @return 保存修改编辑后的数据后跳转页面
//	 *
//	 * @author 彭俊霖 CreateDate: 2018年03月20日
//	 *
//	 */
//	@ResponseBody
//	@RequestMapping("edit")
//	public String edit(UpdateForecastName updateForecastName){
//		try {
//			forecastNameService.updateForecastName(updateForecastName);
//			return "true";
//		} catch (Exception e) {
//			logger.error("保存预报名信息错误！",e);
//			return "false";
//		}
//	}
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：导购助手-预报名首页
//	 *
//	 * @param model
//	 * @param pageNo
//	 * @param pageSize
//	 * @param findShopTidFromWeb
//	 * @param request
//	 * @return
//	 *
//	 * @author 许新龙 CreateDate: 2018年3月21日
//	 *
//	 */
//	@RequestMapping(value = {"list", ""})
//    public String list(Model model,Integer pageNo,Integer pageSize,FindShopTidFromWeb findShopTidFromWeb,HttpServletRequest request){
//        try {
//            request.getSession().setAttribute("user_photo", UserUtils.getUser().getPhoto());//登录用户的头像
//            indexData(model, findShopTidFromWeb);
//        } catch (Exception e) {
//            logger.error("查询首页信息错误：" + e);
//        }
//        return PAGE_VIEW_PRE_REGIST;
//    }
//	
//	private void indexData(Model model, FindShopTidFromWeb findShopTidFromWeb) {
//        String assistantNo=UserUtils.getUser().getId();//导购助手编号
//        String merchantNo=UserUtils.getMerchantNo();//商户编号
//        
//        //客户分组查询
//        FindPmTypePageReturn findPmTypePageReturn=new FindPmTypePageReturn();
//        findPmTypePageReturn.setStatus(CommonConstant.Y);
//        findPmTypePageReturn.setMerchantNo(merchantNo);
//        List<FindPmTypePageReturn> pmType=pmTypeService.findPmTypePages(findPmTypePageReturn);
//        //新增 今日新增/7天内新增/30天内新增 客户类型
//        addTimePmType(pmType);
//        model.addAttribute("pmType",pmType);    
//        //终端信息查询
//        FindGmAssistantShop findGmAssistantShop=new FindGmAssistantShop();
//        findGmAssistantShop.setAssistantNo(assistantNo);
//        findGmAssistantShop.setFilterNoTerminal(Boolean.TRUE);  // 过滤掉没有添加终端的终端
//        List<FindGmAssistantShopReturn> findGmAssistantShopList = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
//        model.addAttribute("shops",findGmAssistantShopList);
//        //导购信息查询
////        List<String> shopNos=Lists.newArrayList();
////        if(StringUtils.isNotEmpty(findShopTidFromWeb.getShopNo())){
////            shopNos.add(findShopTidFromWeb.getShopNo());
////        }else{
////            for (FindGmAssistantShopReturn findGmAssistantShopReturn : findGmAssistantShopList) {
////                shopNos.add(findGmAssistantShopReturn.getShopNo());
////            }
////            
////        }
//        FindGuidMemberPage findGuidMemberPage=new FindGuidMemberPage();
//        findGuidMemberPage.setLimit(500);
//        findGuidMemberPage.setMerchantNo(merchantNo);
////        findGuidMemberPage.setShopNos(shopNos);
//        Page<FindGuidMemberPageReturn> pageDto = guidMemberService.findGuidMemberPage(findGuidMemberPage);
//        List<FindGuidMemberPageReturn> guidMembers = Lists.newArrayList();
//        guidMembers.addAll(pageDto.getRows());
//        model.addAttribute("guidMembers",guidMembers);
//        //导购助手管理的终端列表查询
//        findShopTidFromWeb.setMerchantNo(merchantNo);
//        findShopTidFromWeb.setAssistantNo(assistantNo);
//        findShopTidFromWeb.setQueryOnlineBool(Boolean.TRUE); // 查询终端是否在线
//        List<FindShopTidFromWebReturn> shopTids = shopTerminalService.findShopTerminalForecastName(findShopTidFromWeb);
//        model.addAttribute("shopTids",shopTids);
//        //查询的参数
//        model.addAttribute("ShopTidFromWeb",findShopTidFromWeb);
//        model.addAttribute("user_photo", UserUtils.getUser().getPhoto());
//    }
//	
//	/**
//     * 新增 今日新增/7天内新增/30天内新增 客户类型
//     * @author 彭俊霖
//     * @param pmType
//     */
//    public void addTimePmType(List<FindPmTypePageReturn> pmType) {
//        FindPmTypePageReturn todayFlag=new FindPmTypePageReturn();
//        todayFlag.setCode(PmTypeTimeFlag.TODAY.toString());
//        todayFlag.setTypeName(PmTypeTimeFlag.TODAY.getName());
//        FindPmTypePageReturn weekFlag=new FindPmTypePageReturn();
//        weekFlag.setCode(PmTypeTimeFlag.WEEK.toString());
//        weekFlag.setTypeName(PmTypeTimeFlag.WEEK.getName());
//        FindPmTypePageReturn monthFlag=new FindPmTypePageReturn();
//        monthFlag.setCode(PmTypeTimeFlag.MONTH.toString());
//        monthFlag.setTypeName(PmTypeTimeFlag.MONTH.getName());
//        pmType.add(0,todayFlag);
//        pmType.add(1,weekFlag);
//        pmType.add(2,monthFlag);
//    }
//    
//    /**
//     * 
//     *
//     * 方法说明：预报名
//     *
//     * @param model
//     * @param findImIndex
//     * @param pageNo
//     * @param pageSize
//     * @return
//     *
//     * @author 许新龙 CreateDate: 2018年3月21日
//     *
//     */
//    @RequestMapping(value={"personMemberList"})
//    @ResponseBody
//	public Map<String, Object> personMemberList(Model model, FindImIndexPage findImIndex, Integer pageNo, Integer pageSize) {
//        
////        if (StringUtils.isNotEmpty(findImIndex.getNoWx())) {
////        }
//        
//        //默认查询第一页
//        pageNo = pageNo == null ? 1 : pageNo;
//        
//        if (pageNo != null && pageNo > 0 && pageSize != null && pageSize > 0) {
//            findImIndex.setStart((pageNo - 1) * pageSize);
//            findImIndex.setLimit(pageSize);
//        }
//        
//        
//        List<FindImIndexPageReturn> list = personMemberService.findForecastNameIndexList(findImIndex);
//        Long count = personMemberService.findForecastNameIndexListCount(findImIndex);
//        
//        //今日新增客户数
//        findImIndex.setTypeCode(PmTypeTimeFlag.TODAY.toString());//查询今日数据
//        Long todayCount = personMemberService.findImIndexListCount(findImIndex);
//        
//        FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
//        findGuidMemberPage.setNoWx(findImIndex.getNoWx());
//        findGuidMemberPage.setStatus(GuidStatus.NORMAL.toString());//正常状态
//        findGuidMemberPage.setMerchantNo(UserUtils.getMerchantNo());
////        findGuidMemberPage.setShopNo(findImIndex.getShopNo());
//        findGuidMemberPage.setLimit(1000);
//        List<FindGuidMemberPageReturn> guidMembers = guidMemberService.findGuidMembers(findGuidMemberPage);
//        
//        com.ape.common.persistence.Page<FindImIndexPageReturn> page = new com.ape.common.persistence.Page<FindImIndexPageReturn>(pageNo == null ? 1 : pageNo, pageSize,
//                count, list);
//        page.initialize();
//        
//        Map<String,Object> resultMap=new HashMap<String, Object>();
//        resultMap.put("page", page);
//        resultMap.put("guidMembers", guidMembers);
//        resultMap.put("todayCount", todayCount);
//        
//        return resultMap;
//    }
//    
//    /**
//     * 判断字符串是否为枚举(PmTypeTimeFlag)中的值
//     * @param typeCode
//     */
//    private boolean inEnum(String typeCode)  {
//        PmTypeTimeFlag[] values = PmTypeTimeFlag.values();
//        for (PmTypeTimeFlag pmTypeTimeFlag : values) {
//            if(typeCode.equals(pmTypeTimeFlag.toString())){
//                return true;
//            }
//        }
//        return false;
//    }
//    
//    /**
//     * 
//     *
//     * 方法说明：预报名客户聊天
//     *
//     * @param model
//     * @param memberNoGm
//     * @param memberNo
//     * @param type
//     * @param merchantNo
//     * @param pmTypeCode
//     * @return
//     *
//     * @author 曾垂瑜 CreateDate: 2018年4月4日
//     *
//     */
//    @RequestMapping(value = {"chat"})
//    public String chat(Model model, String memberNoGm, String memberNo, String type,
//            String merchantNo,String pmTypeCode) {
//        try {
//            // 个人资料
//            FindPersonMemberPage findPersonMemberPage = new FindPersonMemberPage();
//            findPersonMemberPage.setMemberNo(memberNo);
//            findPersonMemberPage.setMemberNoGm(memberNoGm);
//            findPersonMemberPage.setMerchantNo(merchantNo);
//
//            // 导购个人信息findGuidMember
//            FindGuidMember findGuidMember = new FindGuidMember();
//            findGuidMember.setMemberNo(memberNoGm);
//            FindGuidMemberReturn findGuidMemberReturn =
//                    guidMemberService.findGuidMember(findGuidMember);
//            model.addAttribute("findGuidMemberReturn", findGuidMemberReturn);
//
//            Page<FindPersonMemberPageReturn> pageDto =
//                    personMemberService.queryPersonMemberPage(findPersonMemberPage);
//            List<FindPersonMemberPageReturn> list = Lists.newArrayList();
//            list.addAll(pageDto.getRows());
//
//            FindPersonMemberPageReturn memberPageReturn = list.get(0);
////            FindShop findShop = new FindShop();
////            findShop.setShopNo(memberPageReturn.getShopNo());
////            List<FindShopPageReturn> shopType = shopService.findShopType(findShop);
////            model.addAttribute("shopType", shopType.get(0).getShopType());
//
//            // 个人资料
//            model.addAttribute("personMember", memberPageReturn);
//
//            // VR素材类型
//            FindVrMaterialType findVrMaterialType = new FindVrMaterialType();
//            findVrMaterialType.setMerchantNo(merchantNo);
//            List<FindVrMaterialTypeApiReturn> typeApiReturns =
//                    vrMaterialTypeService.findVrMaterialTypeReturnList(findVrMaterialType);
//            model.addAttribute("vrMaterialType", typeApiReturns);
//
//            // 跟进记录枚举类
//            model.addAttribute("FollType", FollowType.values());
//            // 维护记录枚举
//            model.addAttribute("keepType", KeepType.values());
//            // 客户来源枚举
//            model.addAttribute("memerSources", MemerSourceType.values());
//            // 性别
//            model.addAttribute("genders", Gender.values());
//            
//            if(StringUtils.isNotEmpty(pmTypeCode)){
//				
//				//邀约版 && 非意向到店 && 非意向未到店 客户,加上 非意向客户标识
//				/*FindPmType findPmType=new FindPmType();
//				findPmType.setCode(pmTypeCode);
//				findPmType.setMerchantNo(merchantNo);
//				FindPmTypeReturn fpm = pmTypeService.findPmType(findPmType);*/
//					model.addAttribute("intentionFlag", CommonConstant.Y);//前端显示按钮
//			}
//
//            FindForecastName findForecastName = new FindForecastName();
//            findForecastName.setMemberNo(memberNo);
//            findForecastName.setMemberNoGm(memberNoGm);
//            List<FindForecastNameReturn> list2 = forecastNameService.findForecastNameByCondition(findForecastName);
//            if (CollectionUtils.isNotEmpty(list2)) {
//                model.addAttribute("forecastName", list2.get(0));
//            }
//
//        } catch (Exception e) {
//            logger.error("查询客户聊天错误：" + e);
//        }
//        return PAGE_VIEW_PRECHAT;
//    } 
//    
//    /**
//     * 
//     *
//     * 方法说明：判断是否显示预报名菜单
//     *
//     * @return
//     *
//     * @author 曾垂瑜 CreateDate: 2018年4月4日
//     *
//     */
//    @RequestMapping(value={"showForecastNameMenu"})
//    @ResponseBody
//    public Map<String,Object> showForecastNameMenu(){
//        HashMap<String, Object> resultMap = Maps.newHashMapWithExpectedSize(1);
//        
//        FindMerchantParams findMerchantParams = new FindMerchantParams();
//        String groupName = "company";//组名
//        findMerchantParams.setGroupName(groupName);
//        findMerchantParams.setMerchantNo(UserUtils.getMerchantNo());
//        Map<String, String> paramsMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);
//        
//        boolean tourismCompany = MapUtils.isNotEmpty(paramsMap) && CommonConstant.YES.equals(paramsMap.get("tourism_company"));
//        
//        resultMap.put("data", tourismCompany);
//        
//        return resultMap;
//    }
//	
//}
