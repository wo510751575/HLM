package com.lj.oms.member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.WxOpenIdUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.service.IBomService;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.common.KeyConstant;
import com.lj.business.member.dto.ChangePmLabel;
import com.lj.business.member.dto.ChangePmTypeHc;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindPersonMemberPage;
import com.lj.business.member.dto.FindPersonMemberPageReturn;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.FindPersonMemberReturnList;
import com.lj.business.member.dto.FindPmLabel;
import com.lj.business.member.dto.FindPmType;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.FindUnchatMemberPage;
import com.lj.business.member.dto.FindUnchatMemberPageReturn;
import com.lj.business.member.dto.PersonMemberExtDto;
import com.lj.business.member.dto.PmLabelDto;
import com.lj.business.member.dto.UpdatePersonMember;
import com.lj.business.member.dto.addfriends.AddAddFriends;
import com.lj.business.member.dto.addfriends.FindAddFriendsPage;
import com.lj.business.member.dto.addfriends.FindAddFriendsPageReturn;
import com.lj.business.member.dto.addfriends.FindAllotGuidMemberReturn;
import com.lj.business.member.dto.addfriends.FindOtherAllotGuidMember;
import com.lj.business.member.dto.addfriends.UpdateAddFriends;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.emus.Gender;
import com.lj.business.member.emus.MemerSourceType;
import com.lj.business.member.emus.SpruceUpType;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMemLineService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberExtService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmLabelService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.contacts.FindWxInfoRequestDto;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.business.weixin.service.IWxFriendsDataService;
import com.lj.distributecache.RedisCache;
import com.lj.oms.common.BaseController;
import com.lj.oms.dto.CancleBingFriendsDto;
import com.lj.oms.dto.CommonRepsonseDto;
import com.lj.oms.dto.ResponseDto;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.Role;
import com.lj.oms.service.AreaHessianService;
import com.lj.oms.utils.AddQrCodeUtils;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.Validator;
import com.lj.oms.utils.excel.ExportExcel;
import com.lj.oms.utils.excel.ImportExcel;
import com.lj.oms.utils.excel.dto.AddWxFriendsDto;
import com.lj.oms.utils.excel.dto.AddWxFriendsExportDto;
import com.lj.oms.utils.excel.dto.EMemberExportDto;
import com.lj.oms.utils.excel.dto.MemberExportDto;
import com.lj.oms.utils.excel.dto.NoInvitePersonMemberDto;
import com.lj.oms.utils.excel.dto.PersonMemberDto;

import net.sf.json.JSONArray;

/**
 * 
 * 
 * 类说明：客户Controller
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 * 
 *         CreateDate: 2017年7月14日
 */
@Controller
@RequestMapping(value = "${adminPath}/member")
public class MemberController  extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	/** 客户列表页面 **/
	private final static String PAGE_VIEW_MEMBER_LIST = "modules/member/memberList";
	/** 重定向到客户列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_MEMBER = "redirect:" + Global.getAdminPath() + "/member/?repage";
	/** 重定向到客户列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_MEMBER_REPAGE = "redirect:" + Global.getAdminPath() + "/member/guid/?repage";
	/** 标签选择页面 **/
	private static final String PAGE_VIEW_TAG_VIEW = "modules/member/tagView";
	/** 客户详情页面 **/
	private static final String PAGE_VIEW_MEMBER_VIEW = "modules/member/memberView";
	/** 客户转移页面 **/
	private static final String PAGE_VIEW_CLIENT_TRANS_LIST = "modules/member/clientTransList";
	/** 可分配导购列表页面 **/
	private static final String PAGE_VIEW_MEMBER_ALLOT_VIEW = "modules/member/memberAllotView";
	/** 添加微信好友列表页面 **/
	private static final String PAGE_VIEW_ADD_WX_FRIENDS_LIST = "modules/member/addWxFriendsList";
	/** 添加微信好友编辑页面 **/
	private static final String PAGE_VIEW_ADD_WX_FRIENDS_EDIT_VIEW = "modules/member/addWxFriendsEditView";
	/** 查询客户分组页面 **/
	private static final String PAGE_VIEW_BATCH_CHANGE_PM_TYPE_SELECT = "modules/member/batchChangePmTypeSelect";
	/** 今日客户列表页面 **/
	private static final String PAGE_VIEW_MEMBER_TODAY_LIST = "modules/member/memberTodayList";
	/** 未联系客户列表页面 **/
	private static final String PAGE_VIEW_MEMBER_UNCHAT_LIST = "modules/member/memberUnChatList";
	
	@Resource
	private IPersonMemberService personMemberService; // 客户信息服务
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	@Resource
	private IPmTypeService pmTypeService; // 客户分类服务
	@Resource
	private IPmLabelService pmLabelService; // 客户标签服务
	@Resource
	private IPersonMemberBaseService personMemberBaseService; // 客户基础信息服务
	@Resource
	private IBomService bomService; // 产品服务
	@Resource
	private IMemLineService memLineService; // 职业服务
	@Resource
	private AreaHessianService areaHessianService; // 地区服务
	@Resource
    private  IAddFriendsService addFriendsService;
	@Autowired
    private IShopTerminalService shopTerminalService;
	@Autowired 
	private RedisCache redisCache; //记录并区分添加方式
	@Autowired
	private IMerchantParamsService merchantParamsService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Resource
	private IWxFriendsDataService wxFriendsDataService;
	@Autowired
	private IChatRoomService chatRoomService;
	@Autowired
	private IGmAssistantShopService gmAssistantShopService;
	
    @Autowired 
	ICommonService commonService;

	@Autowired
	private IPersonMemberExtService personMemberExtService;

	/**
	 * 
	 *
	 * 方法说明：客户列表
	 *
	 * @param model
	 * @param findPersonMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月4日
	 *
	 */
	@RequiresPermissions("member:member:view")
	@RequestMapping(value = { "list", "" })
	public String list(Model model, FindPersonMemberPage findPersonMemberPage, Integer pageNo, Integer pageSize) {
		try {
			findPersonMemberPage.setMerchantNo(UserUtils.getMerchantNo());
			//4.商户总账户看客户列表所有，商户看该商户所有，店长角色看终端所有，店员角色看已认领
			if(UserUtils.getUser().getRoleList()!=null && UserUtils.getUser().getRoleList().size()>0) {
				List<Role> list = UserUtils.getUser().getRoleList();
				for (Role role : list) {
					//如果角色是总管帐号，则上述条件都去掉
					if(Role.DATA_SCOPE_COMPANY_AND_CHILD.equals(role.getId())) {
						findPersonMemberPage.setMemberNoGm(null);
						findPersonMemberPage.setShopWxs(null);
						break;
					}
					//非店员角色看终端所有
					if(!Role.SYS_CLERK.equals(role.getEnname())) {
						String shopWxs = gmAssistantShopService.findGroupConcatByAssNo(UserUtils.getUser().getId());
						if(StringUtils.isNoneEmpty(shopWxs) && shopWxs.length()>0) {
							findPersonMemberPage.setShopWxs(shopWxs.split(","));
							findPersonMemberPage.setMemberNoGm(null);
						}
						break;
					}
					//店员角色看已认领
					if(Role.SYS_CLERK.equals(role.getEnname())) {
						findPersonMemberPage.setMemberNoGm(UserUtils.getUser().getId());
					}
				}
			}
			
			findPersonMemberPage.setStart(pageNo == null? 0:(pageNo - 1) * pageSize);
			findPersonMemberPage.setLimit(pageSize == null? 10:pageSize);
			findPersonMemberPage.setStartTime(DateUtils.getDateByFirstSeconds(findPersonMemberPage.getStartTime()));
			findPersonMemberPage.setEndTime(DateUtils.getDateByLastSeconds(findPersonMemberPage.getEndTime()));
			setArea(model, findPersonMemberPage);
			//1.04更改接口  拆分多表关联
			Page<FindPersonMemberPageReturn> pageDto = null; 
			pageDto = personMemberService.queryPersonMemberPage(findPersonMemberPage);
			List<FindPersonMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			
			
			for(FindPersonMemberPageReturn memberPageReturn:list){
				// 商户指定客户标签
                String customerLabel = "";
                FindPmLabel findPmLabel = new FindPmLabel();
				findPmLabel.setMemberNo(memberPageReturn.getMemberNo());//客户编号
				findPmLabel.setMerchantNo(memberPageReturn.getMerchantNo());//商户编号
				findPmLabel.setShopWx(memberPageReturn.getShopWx());
				List<PmLabelDto> labelList = pmLabelService.findPmLabelByMemberNoAndMerchantNo(findPmLabel);
				for (PmLabelDto label : labelList) {
					customerLabel += ","+label.getLabelName();
				}
                memberPageReturn.setRemark(customerLabel.replaceFirst(",", ""));
				
			}
			List<FindPmTypePageReturn> pmTypeList = findPmTypeList();
			model.addAttribute("pmTypeList", pmTypeList);
			

			com.ape.common.persistence.Page<FindPersonMemberPageReturn> page = new com.ape.common.persistence.Page<FindPersonMemberPageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
					pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("merchantNo", findPersonMemberPage.getMerchantNo());
			
             
			// 客户来源
			model.addAttribute("memerSources", MemerSourceType.values());
			model.addAttribute("paramMember", findPersonMemberPage);
			
		} catch (Exception e) {
			logger.error("获取客户信息错误！", e);
		}

		return PAGE_VIEW_MEMBER_LIST;
	}

	/**
	 * 
	 *
	 * 方法说明：转换所在地区
	 *
	 * @param model
	 * @param findPersonMemberPage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@SuppressWarnings("unchecked")
	private void setArea(Model model, FindPersonMemberPage findPersonMemberPage) {
		//转换所在地区 update by Peng Junlin
		if(StringUtils.isNotBlank(findPersonMemberPage.getAreaId()) || !"1".equals(findPersonMemberPage.getAreaId())){
			Area area = areaHessianService.findArea(findPersonMemberPage.getAreaId());
			if(area!=null){
				Map<String, Object> areaMap=new HashMap<String, Object>();
				switch (area.getType()) {
				case "2":
					Area province = areaHessianService.findArea(findPersonMemberPage.getAreaId());
					findPersonMemberPage.setProvince(com.lj.base.core.util.StringUtils.toString(province.getCode()));
					break;
				case "3":
					areaMap = areaHessianService.selectCurrentLevelByCityId(findPersonMemberPage.getAreaId());
					findPersonMemberPage.setProvince(com.lj.base.core.util.StringUtils.toString(areaMap.get("province_id")));
					findPersonMemberPage.setCity(com.lj.base.core.util.StringUtils.toString(areaMap.get("city_id")));
					break;
				case "4":
					areaMap = areaHessianService.selectCurrentLevelByAreaId(findPersonMemberPage.getAreaId());
					findPersonMemberPage.setProvince(com.lj.base.core.util.StringUtils.toString(areaMap.get("province_id")));
					findPersonMemberPage.setCity(com.lj.base.core.util.StringUtils.toString(areaMap.get("city_id")));
					findPersonMemberPage.setRegion(com.lj.base.core.util.StringUtils.toString(areaMap.get("country_id")));
					break;
				}
				model.addAttribute("areaId", findPersonMemberPage.getAreaId());
				model.addAttribute("areaName", findPersonMemberPage.getAreaName());
			}else{
				logger.info("查找地区信息{}不存在!",findPersonMemberPage.getAreaId());
			}
		}
	}


	/**
	 *
	 * 方法说明：分页查询客户信息
	 *
	 * @param model
	 * @param findPersonMemberPage
	 * @param pageNo
	 * @param pageSize
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 * 
	 */
	private List<FindPersonMemberPageReturn> findPersonMemberPage(Model model, FindPersonMemberPage findPersonMemberPage, Integer pageNo, Integer pageSize) {
		findPersonMemberPage.setMerchantNo(UserUtils.getMerchantNo());
		if (pageNo != null) {
			findPersonMemberPage.setStart((pageNo - 1) * pageSize);
		}
		if (pageSize != null) {
			findPersonMemberPage.setLimit(pageSize);
		}
		findPersonMemberPage.setStartTime(DateUtils.getDateByFirstSeconds(findPersonMemberPage.getStartTime()));
		findPersonMemberPage.setEndTime(DateUtils.getDateByLastSeconds(findPersonMemberPage.getEndTime()));
		setArea(model, findPersonMemberPage);
		Page<FindPersonMemberPageReturn> pageDto = personMemberService.findPersonMemberPage(findPersonMemberPage);
		List<FindPersonMemberPageReturn> list = Lists.newArrayList();
		list.addAll(pageDto.getRows());
		com.ape.common.persistence.Page<FindPersonMemberPageReturn> page = new com.ape.common.persistence.Page<FindPersonMemberPageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
				pageDto.getTotal(), list);
		page.initialize();
		
		model.addAttribute("page", page);
		model.addAttribute("memerSources", MemerSourceType.values());	// 客户来源
		model.addAttribute("paramMember", findPersonMemberPage);
		return list;
	}
	
	/**
     * 
     *
     * 方法说明：客户列表-标签选择
     *
     * @param model
     * @param single
     * @param merchantNo
     * @param memberNos
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月12日
     *
     */
    @RequestMapping(value={"tagView"})
    public String tagView(Model model, Integer single, String memberNos,String shopWx){
        try {
            List<PmLabelDto> labels = pmLabelService.findPmLabelByMerchantNo(UserUtils.getMerchantNo());
            model.addAttribute("labels", labels);
            model.addAttribute("memberNos", memberNos);
            model.addAttribute("single", single);
            model.addAttribute("shopWx", shopWx);
        } catch (Exception e) {
            logger.error("获取商户下的标签列表失败！",e);
        }
        return PAGE_VIEW_TAG_VIEW;
    }
    
    /**
     * 
     *
     * 方法说明：查询某客户的标签列表
     *
     * @param model
     * @param findPmLabel
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月12日
     *
     */
    @RequestMapping(value={"findTagsByMemberNo"})
    @ResponseBody
    public List<PmLabelDto> findTagsByMemberNo(Model model, FindPmLabel findPmLabel){
        try {
        	findPmLabel.setMerchantNo(UserUtils.getMerchantNo());
            List<PmLabelDto> labels = pmLabelService.findPmLabelByMemberNoAndMerchantNo(findPmLabel);
            return labels;
        } catch (Exception e) {
            logger.error("获取客户标签列表失败！",e);
            return new ArrayList<>();
        }
    }
    
    /**
     * 
     *
     * 方法说明：批量添加标签（memberNos用逗号分割，兼容单个客户）
     *
     * @param model
     * @param merchantNo
     * @param tags
     * @param memberNos
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月12日
     *
     */
    @RequestMapping(value={"addTags"})
    @ResponseBody
    public CommonRepsonseDto addTags(Model model, String tags,String names, String memberNos,String shopWx){
        CommonRepsonseDto commonRepsonseDto = null;
        try {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(memberNos)) {
                List<PmLabelDto> labels = new ArrayList<>();
                if (org.apache.commons.lang3.StringUtils.isNotBlank(tags)) {
                    String[] labelCodeArr = tags.split(",");
                    String[] labelNameArr = names.split(",");
                    for (int i = 0; i < labelCodeArr.length; i++) {
                    	PmLabelDto pmLabelDto = new PmLabelDto();
                        pmLabelDto.setCode(labelCodeArr[i]);
                        pmLabelDto.setLabelName(labelNameArr[i]);
                        labels.add(pmLabelDto );
					}
                }
                
                
                String[] memberNoArr = memberNos.split(",");
                
                ChangePmLabel changePmLabel = new ChangePmLabel();
                for (String memberNo : memberNoArr) {
                    changePmLabel.setMemberNo(memberNo);
                    changePmLabel.setMerchantNo(UserUtils.getMerchantNo());
                    changePmLabel.setLabels(labels);
                    changePmLabel.setShopWx(shopWx);
                    pmLabelService.changePmLabel(changePmLabel );
                }
                
                commonRepsonseDto = CommonRepsonseDto.generateSuccessResponse("添加标签成功");
            }
        } catch (Exception e) {
            logger.error("获取商户下的标签列表失败！",e);
            commonRepsonseDto = CommonRepsonseDto.generateFailureResponse("给客户添加标签异常");
        }
        return commonRepsonseDto;
    }


	


	/**
	 * 
	 *
	 * 方法说明：客户详情
	 *
	 * @param model
	 * @param code
	 * @param pmTypeType
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value = { "view" })
	public String view(Model model, String code) {
		try {
			Map<String, String> param = new HashMap<>();
			param.put("code", code);
			FindPersonMemberPageReturn findPersonMemberReturn = personMemberService.getByCond(param);
			model.addAttribute("member", findPersonMemberReturn);
			// 商户标签
			
			FindPmLabel findPmLabel = new FindPmLabel();
			findPmLabel.setMemberNo(findPersonMemberReturn.getMemberNo());
			findPmLabel.setMerchantNo(UserUtils.getMerchantNo());
			findPmLabel.setShopWx(findPersonMemberReturn.getShopWx());
			List<PmLabelDto> labelList = pmLabelService.findPmLabelByMemberNoAndMerchantNo(findPmLabel);
			model.addAttribute("merchantLabel", labelList);

			// 客户来源、性别、装修进度枚举
			model.addAttribute("memerSources", MemerSourceType.values());
			model.addAttribute("genders", Gender.values());
//			model.addAttribute("spruceUpTypes", SpruceUpType.values());

			/* 获取职业名称 */
//			if (StringUtils.isNoneBlank(findPersonMemberReturn.getJob())) {
//				MemLineDto memLine = new MemLineDto();
//				memLine.setCode(findPersonMemberReturn.getJob());
//				MemLineDto memLineDto = memLineService.findMemLine(memLine);
//				findPersonMemberReturn.setJobName(memLineDto.getName());
//			}
			
			
			/*获取客户扩展信息*/
			PersonMemberExtDto personMemberExtDto= personMemberExtService.findByMemberNo(findPersonMemberReturn.getMemberNo());
			if(personMemberExtDto !=null) {
				model.addAttribute("pmExt", personMemberExtDto);
			}

		} catch (Exception e) {
			logger.error("客户详情查询失败", e);
		}

		return PAGE_VIEW_MEMBER_VIEW;
	}

	/**
	 * 
	 *
	 * 方法说明：导出客户数据
	 *
	 * @param response
	 * @param limit
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月4日
	 *
	 */
	@RequestMapping(value = "export")
	public String export(HttpServletResponse response, FindPersonMemberPage findPersonMemberPage, Integer pageNo, Integer pageSize, RedirectAttributes redirectAttributes) {
		try {
			findPersonMemberPage.setMerchantNo(UserUtils.getMerchantNo());
//			findPersonMemberPage.setShopNos(CcUtils.getShopNoList());
			findPersonMemberPage.setSortDir(PageSortType.desc);
			findPersonMemberPage.setStart(0);
			findPersonMemberPage.setMaxLimit();
			findPersonMemberPage.setStartTime(DateUtils.getDateByFirstSeconds(findPersonMemberPage.getStartTime()));
			findPersonMemberPage.setEndTime(DateUtils.getDateByLastSeconds(findPersonMemberPage.getEndTime()));
			//转换所在地区 update by Peng Junlin
			//省份
			String province = findPersonMemberPage.getProvince();
			if(StringUtils.isNotBlank(province)){
				List<Area> provinces = areaHessianService.selectProvince();
				for (Area p : provinces) {
					if(StringUtils.contains(p.getName(), province)){
						findPersonMemberPage.setProvince(p.getCode());
					}
				}
			}
			//城市
			String city = findPersonMemberPage.getCity();
			if(StringUtils.isNotBlank(city)){
				List<Area> citys = areaHessianService.selectCity();
				for (Area c : citys) {
					if(StringUtils.contains(c.getName(), city)){
						findPersonMemberPage.setCity(c.getCode());
					}
				}
			}
			
			Page<FindPersonMemberPageReturn> pageDto = personMemberService.findPersonMemberPage(findPersonMemberPage);
			List<FindPersonMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			for (FindPersonMemberPageReturn findPersonMemberPageReturn : list) {
				//update by Peng Junlin 导出微信号/微信号别名(默认)
				findPersonMemberPageReturn.setNoWx(com.lj.base.core.util.StringUtils.toString(findPersonMemberPageReturn.getNoWxAlias(),findPersonMemberPageReturn.getNoWx()));
				//update by Peng Junlin 导出所在地区
				String area=UserUtils.getAreaName(findPersonMemberPageReturn.getProvinceCode())+UserUtils.getAreaName(findPersonMemberPageReturn.getCityCode())+UserUtils.getAreaName(findPersonMemberPageReturn.getCityAreaCode());
				findPersonMemberPageReturn.setAreaCode(area);
				// 导出所属终端
				findPersonMemberPageReturn.setBomName(findPersonMemberPageReturn.getShopWx());
				if (StringUtils.isNoneBlank(findPersonMemberPageReturn.getSex())) {
					findPersonMemberPageReturn.setSex(Gender.valueOf(findPersonMemberPageReturn.getSex()).getName());
				}else {
					findPersonMemberPageReturn.setSex(Gender.UNKNOWN.getName());
				}
				if (StringUtils.isNoneBlank(findPersonMemberPageReturn.getMemberSrc())) {
					findPersonMemberPageReturn.setMemberSrc(MemerSourceType.valueOf(findPersonMemberPageReturn.getMemberSrc()).getName());
				}
			}
			String fileName = "客户数据导出.xlsx";
			new ExportExcel("客户数据导出", MemberExportDto.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			logger.error("导出客户数据失败", e);
			addMessage(redirectAttributes, "客户数据导出失败！失败信息：" + e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_MEMBER_REPAGE;
	}
	
	/**
	 * 
	 *
	 * 方法说明：导出今日客户数据
	 *
	 * @param response
	 * @param limit
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年07月19日
	 *
	 */
	@RequestMapping(value = "todayExport")
	public String todayExport(HttpServletResponse response, FindPersonMemberPage findPersonMemberPage, Integer pageNo, Integer pageSize, RedirectAttributes redirectAttributes) {
		try {
			findPersonMemberPage.setMerchantNo(UserUtils.getMerchantNo());
//			findPersonMemberPage.setShopNos(CcUtils.getShopNoList());
			findPersonMemberPage.setSortDir(PageSortType.desc);
			findPersonMemberPage.setStart(0);
			findPersonMemberPage.setMaxLimit();
			Date now =new Date();
			findPersonMemberPage.setStartTime(DateUtils.getDateByFirstSeconds(now));
			findPersonMemberPage.setEndTime(DateUtils.getDateByLastSeconds(now));
			//转换所在地区 update by Peng Junlin
			//省份
			String province = findPersonMemberPage.getProvince();
			if(StringUtils.isNotBlank(province)){
				List<Area> provinces = areaHessianService.selectProvince();
				for (Area p : provinces) {
					if(StringUtils.contains(p.getName(), province)){
						findPersonMemberPage.setProvince(p.getCode());
					}
				}
			}
			//城市
			String city = findPersonMemberPage.getCity();
			if(StringUtils.isNotBlank(city)){
				List<Area> citys = areaHessianService.selectCity();
				for (Area c : citys) {
					if(StringUtils.contains(c.getName(), city)){
						findPersonMemberPage.setCity(c.getCode());
					}
				}
			}
			
			Page<FindPersonMemberPageReturn> pageDto = personMemberService.findPersonMemberPage(findPersonMemberPage);
			List<FindPersonMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			for (FindPersonMemberPageReturn findPersonMemberPageReturn : list) {
				//update by Peng Junlin 导出微信号/微信号别名(默认)
				findPersonMemberPageReturn.setNoWx(com.lj.base.core.util.StringUtils.toString(findPersonMemberPageReturn.getNoWxAlias(),findPersonMemberPageReturn.getNoWx()));
				//update by Peng Junlin 导出所在地区
				String area=UserUtils.getAreaName(findPersonMemberPageReturn.getProvinceCode())+UserUtils.getAreaName(findPersonMemberPageReturn.getCityCode())+UserUtils.getAreaName(findPersonMemberPageReturn.getCityAreaCode());
				findPersonMemberPageReturn.setAreaCode(area);
				
				if (StringUtils.isNoneBlank(findPersonMemberPageReturn.getSex())) {
					findPersonMemberPageReturn.setSex(Gender.valueOf(findPersonMemberPageReturn.getSex()).getName());
				}else {
					findPersonMemberPageReturn.setSex(Gender.UNKNOWN.getName());
				}
				if (StringUtils.isNoneBlank(findPersonMemberPageReturn.getMemberSrc())) {
					findPersonMemberPageReturn.setMemberSrc(MemerSourceType.valueOf(findPersonMemberPageReturn.getMemberSrc()).getName());
				}
			}
			String fileName = "客户数据导出.xlsx";
					new ExportExcel("客户数据导出", EMemberExportDto.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			logger.error("导出客户数据失败", e);
			addMessage(redirectAttributes, "客户数据导出失败！失败信息：" + e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_MEMBER_REPAGE;
	}

	/**
	 * 
	 *
	 * 方法说明：检查客户手机号唯一
	 *
	 * @param oldMobile
	 * @param mobile
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@ResponseBody
	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "checkMobile")
	public boolean checkMobile(String oldMobile, String mobile) {
		try {
			if (mobile != null && mobile.equals(oldMobile)) {
				return true;
			}
			FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
			findPersonMemberBase.setMobile(mobile);
			FindPersonMemberBaseReturn baseReturn = personMemberBaseService.findByMobile(findPersonMemberBase);
			if (baseReturn == null) {
				return true;
			}
			return false;
		} catch (TsfaServiceException e) {
			logger.error("检查客户手机唯一性异常", e);
			return true;
		}

	}
	
	/**
	 * 
	 *
	 * 方法说明：检查微信号唯一性
	 *
	 * @param oldNoWx
	 * @param noWx
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@ResponseBody
    @RequiresPermissions("member:member:edit")
    @RequestMapping(value = "checkNoWx")
    public boolean checkNoWx(String oldNoWx, String noWx) {
        try {
            if (noWx != null && noWx.equals(oldNoWx)) {
                return true;
            }
            FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
            findPersonMemberBase.setNoWx(noWx);
            FindPersonMemberBaseReturn baseReturn = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
            if (baseReturn == null) {
                return true;
            }
            return false;
        } catch (TsfaServiceException e) {
			logger.error("检查客户微信异常", e);
            return true;
        }
    }

	
	
	/**
	 * 
	 *
	 * 方法说明：客户数据导入模板下载
	 *
	 * @param response
	 * @param request
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("member:guid:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "客户导入模版.xlsx";
			List<PersonMemberDto> list = Lists.newArrayList();
			PersonMemberDto dto = new PersonMemberDto();
			dto.setAge(18);
			dto.setBomName("功能沙发");
			dto.setHouses("龙华1号");
			dto.setJob("IT");
			dto.setMemberName("张晓强");
			dto.setMemberSrc(MemerSourceType.SHOP_SACN.getName());
			dto.setMobile("18577655123");
//			dto.setPmTypeType(PmTypeType.INTENTION.getName());
			dto.setSex(Gender.MALE.getName());
			dto.setSpruceUp(SpruceUpType.CHANGE.getName());
			dto.setMobileGm("18665435140");
			dto.setLabelName("优质客户");
			list.add(dto);

			dto = new PersonMemberDto();
			dto.setAge(28);
			dto.setBomName("床垫");
			dto.setHouses("莱蒙水榭春天六期");
			dto.setJob("医生");
			dto.setMemberName("王六");
			dto.setMemberSrc(MemerSourceType.NET.getName());
			dto.setMobile("18577456723");
//			dto.setPmTypeType(PmTypeType.INTENTION.getName());
			dto.setSex(Gender.FEMALE.getName());
			dto.setSpruceUp(SpruceUpType.DOING.getName());
			dto.setMobileGm("18665435140");
			dto.setLabelName("优质客户,高频客户");
			list.add(dto);

			new ExportExcel("客户导入模版", PersonMemberDto.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			logger.error("客户导入模板下载失败", e);
			addMessage(redirectAttributes, "客户导入模板下载失败！失败信息：" + e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_MEMBER;
	}
	
	/**
	 *
	 * 方法说明：客户数据导入模板下载-非邀约型-LSS
	 * @param response
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @author 李端强 CreateDate: 2017年12月8日17:09:23
	 *
	 */
	@RequiresPermissions("member:guid:view")
	@RequestMapping(value = "import/templateLss")
	public String importFileTemplateLss(HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			// 绑定商户的客户分类 -----start
			List<FindPmTypePageReturn> pmTypeList = this.findPmTypeList();
			String typeType = "";//客户分类
			if(pmTypeList!=null && pmTypeList.size()>0) {
				for (FindPmTypePageReturn re : pmTypeList) {
					if(re.getTypeName().indexOf("未分组")!=-1) {//用户不可选择未分组
						continue;
					}
					typeType += "/" + re.getTypeName();
				}
				typeType = typeType.replaceFirst("/", "");
			}
			// 绑定商户的客户分类 -----end
			
			String fileName = "客户导入模版.xlsx";
			List<NoInvitePersonMemberDto> list = Lists.newArrayList();
			NoInvitePersonMemberDto dto = new NoInvitePersonMemberDto();
			dto.setAge(18);
			dto.setBomName("牙科");
			dto.setHeadAddress("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3551563550,2594280103&fm=117&gp=0.jpg");
			dto.setHouses("龙华1号");
			dto.setJob("IT");
			dto.setMemberName("张晓强");
			dto.setMemberSrc(MemerSourceType.SHOP_SACN.getName());
			dto.setMobile("18577655123");
			dto.setPmTypeType(typeType);
			dto.setSex(Gender.MALE.getName());
			dto.setSpruceUp(SpruceUpType.CHANGE.getName());
			dto.setMobileGm("18665435140");
			dto.setCity("深圳市");
			dto.setTitle("种植牙");
			list.add(dto);

			dto = new NoInvitePersonMemberDto();
			dto.setAge(28);
			dto.setBomName("牙科");
			dto.setHeadAddress("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3551563550,2594280103&fm=117&gp=0.jpg");
			dto.setHouses("莱蒙水榭春天六期");
			dto.setJob("医生");
			dto.setMemberName("王六");
			dto.setMemberSrc(MemerSourceType.NET.getName());
			dto.setMobile("18577456723");
			dto.setPmTypeType(typeType.split("/")[0]);
			dto.setSex(Gender.FEMALE.getName());
			dto.setSpruceUp(SpruceUpType.DOING.getName());
			dto.setMobileGm("18665435140");
			dto.setCity("岳阳市");
			dto.setTitle("正畸");
			list.add(dto);

			new ExportExcel("客户导入模版", NoInvitePersonMemberDto.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			logger.error("客户导入模板下载失败", e);
			addMessage(redirectAttributes, "客户导入模板下载失败！失败信息：" + e.getMessage());
		}
		return PAGE_VIEW_REDIRECT_MEMBER;
	}

	
	/**
	 * 
	 *
	 * 方法说明：客户转移页面
	 *
	 * @param model
	 * @param findPersonMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author zlh CreateDate: 2017年7月21日
	 *
	 */
	@RequestMapping(value = { "toClientTrans" })
	public String toClientTrans(Model model, HttpServletResponse response, HttpServletRequest request) {
		try {
			List<FindShopTerminalReturn> list =shopTerminalService.findAllShopTerminalByMerchantNo(UserUtils.getMerchantNo());
//			String  merchantNo= UserUtils.getMerchantNo();
//			List<Map<String, Object>> findShopList = shopService.findShopNoByMerchant(merchantNo );
			model.addAttribute("list", list);
		} catch (Exception e) {
			logger.error("查询商户所有终端", e);
		}

		return PAGE_VIEW_CLIENT_TRANS_LIST;
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：根据终端查询所有导购
	 *
	 * @param model
	 * @param findPersonMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author zlh CreateDate: 2017年7月21日
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "findGuidMemberByShop" })
	public String findGuidMemberByShop(Model model, String noWx) {
		try {
//			FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();	
//			List<GuidMemberReturnDto> guidMemberList = guidMemberService.findGuidMemberSelective(findGuidMemberDto );
			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
			findGmAssistantShop.setNoWx(noWx);
			List<FindGmAssistantShopReturn> list =gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
			JSONArray json = JSONArray.fromObject(list);     
			logger.info(json.toString());
	         return json.toString();
		} catch (Exception e) {
			logger.error("查询商户所有终端", e);
			return null;
		}

	
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：根据导购查询所有客户
	 *
	 * @param model
	 * @param findPersonMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author zlh CreateDate: 2017年7月21日
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "findMemberByGuid" })
	public String findMemberByGuid(Model model, String gmNo,String noWxGm) {
		try {
			List<FindPersonMemberReturn> personMemberList = personMemberService.findPersonMember(noWxGm,gmNo);
			model.addAttribute("personMemberList", personMemberList);
			
			JSONArray json = JSONArray.fromObject(personMemberList);     
	         return json.toString();
		} catch (Exception e) {
			logger.error("查询商户所有终端", e);
			return null;
		}

	}
	
	
	/**
	 * 
	 * 方法说明：转移客户
	 *
	 * @param model
	 * @param sourceGmCode 导购编号
	 * @param newGmCode 新导购
	 * @param redirectAttributes 需要转移
	 * @return
	 *
	 * @author zlh CreateDate: 2017年11月14日
	 *
	 */
	@ResponseBody
	@RequestMapping(value={"transfer"})
	public CommonRepsonseDto transfer(Model model,String  sourceGmNo, String gmName, String newGmNo,String memberNos,String noWxGm){
		CommonRepsonseDto commonRepsonseDto = null;
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			if(sourceGmNo.equals(newGmNo)) {
				return CommonRepsonseDto.generateFailureResponse("同一导购还需转移？");
			}
			if("0".equals(sourceGmNo)||"0".equals(newGmNo)){
				return CommonRepsonseDto.generateFailureResponse("导购不能为空!");
			}
			String[] vals = memberNos.split(",");
			for (String no : vals) {
				try {
					if(no != null && !no.equals("")) {
						//清空朋友圈信息
			            wxFriendsDataService.updateCancleFriendsCommentData(sourceGmNo, null, no, noWxGm);
			            //转移客户信息
						personMemberService.updateFriendsWithTransfer(sourceGmNo, gmName, newGmNo, no,noWxGm);
						successNum++;
					}
				} catch (TsfaServiceException ex) {
					failureMsg.append("<br/>").append(ex.getExceptionInfo());
					failureNum++;
					continue;
				} catch (Exception ex) {
					failureMsg.append("<br/>").append(ex.getMessage());
					failureNum++;
					continue;
				}
				
			}
			//通知APP新导购更新数据
			personMemberService.sendTransSuccess(newGmNo, memberNos, "1",noWxGm,null);
			//通知APP老导购更新数据,2移除客户
			personMemberService.sendTransSuccess(sourceGmNo, memberNos, "2",noWxGm,null);
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条客户，失败信息如下：");
			}
			commonRepsonseDto = CommonRepsonseDto.generateSuccessResponse("成功转移"+successNum+"个客户"+failureMsg);
		} catch (Exception e) {
			logger.error("转移客户失败！", e);
			commonRepsonseDto = CommonRepsonseDto.generateFailureResponse("转移客户失败！");
		}
		return  commonRepsonseDto;
	}
	
	
	/**
	 * 
	 * 方法说明：分配导购列表
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月23日
	 *
	 */
	@RequestMapping(value={"allotView"})
	public String allotView(Model model,FindOtherAllotGuidMember findOtherAllotGuidMember,String code){
		try {
			List<FindAllotGuidMemberReturn> allotList=guidMemberService.findOtherAllotGuidMember(findOtherAllotGuidMember);
			model.addAttribute("allotList", allotList);
			model.addAttribute("personMemberCode", code);
		} catch (Exception e) {
			logger.error("查询可分配导购列表失败", e);
		}
		return PAGE_VIEW_MEMBER_ALLOT_VIEW;
	}
	
	/**
	 * 
	 * 方法说明：客户更改所分配导购
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月24日
	 *
	 */
	@RequiresPermissions("member:member:edit")
	@ResponseBody
	@RequestMapping(value={"allot"})
	public CommonRepsonseDto allot(Model model,UpdatePersonMember updatePersonMember, RedirectAttributes redirectAttributes){
		CommonRepsonseDto responseDto = null;
		try {
			personMemberService.updatePersonMember(updatePersonMember);
			addMessage(redirectAttributes, "客户分配成功!");
			responseDto = CommonRepsonseDto.generateSuccessResponse("客户分配成功!");
		} catch (Exception e) {
			logger.error("重新分配导购失败", e);
			addMessage(redirectAttributes, e.getMessage());
			responseDto = CommonRepsonseDto.generateFailureResponse("客户分配失败!");
		}
		return responseDto;
	}
	
	/**
	 * 
	 *
	 * 方法说明：添加微信好友列表
	 *
	 * @param model
	 * @param findAddFriendsPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月13日
	 *
	 */
	@RequiresPermissions("member:addWxFriends:view")
    @RequestMapping(value={"addWxFriends/list"})
    public String addWxFriendsList(Model model, FindAddFriendsPage findAddFriendsPage, Integer pageNo, Integer pageSize) {
        try {
            findAddFriendsPage.setMerchantNo(UserUtils.getMerchantNo());
            findAddFriendsPage.setSortDir(PageSortType.desc);
            findAddFriendsPage.setStart(pageNo == null? 0:(pageNo - 1) * pageSize);
            findAddFriendsPage.setLimit(pageSize == null? 10:pageSize);
            
            Page<FindAddFriendsPageReturn> pageDto = addFriendsService.findAddFriendsPage(findAddFriendsPage);
            
            List<FindAddFriendsPageReturn> list = Lists.newArrayList();
            list.addAll(pageDto.getRows());
            
            com.ape.common.persistence.Page<FindAddFriendsPageReturn> page = new com.ape.common.persistence.Page<FindAddFriendsPageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
                    pageDto.getTotal(), list);
            page.initialize();
            model.addAttribute("page", page);
            
            // 客户来源
//            model.addAttribute("memerSources", MemerSourceType.values());
            model.addAttribute("paramMember", findAddFriendsPage);
        } catch (Exception e) {
            logger.error("获取添加微信好友列表错误！", e);
        }

        return PAGE_VIEW_ADD_WX_FRIENDS_LIST;
    }
	
	/**
	 * 
	 *
	 * 方法说明：添加微信好友-修改页面
	 *
	 * @param model
	 * @param code
	 * @param merchantNo
	 * @param noWxGm
	 * @param wxAddType
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月13日
	 *
	 */
	@RequiresPermissions("member:addWxFriends:view")
    @RequestMapping(value={"addWxFriends/editView"})
    public String addWxFriendsEditView(Model model, String code, String merchantNo, String noWxGm, Integer wxAddType) {
        try {
            FindShopTerminal findShopTerminal = new FindShopTerminal();
            findShopTerminal.setMerchantNo(merchantNo);
            findShopTerminal.setStatus(1); // 只查询有效的终端
            List<FindShopTerminalReturn> shopTerminalList = shopTerminalService.findShopTerminalSelect(findShopTerminal);
            
            model.addAttribute("shopTerminalList", shopTerminalList);
            model.addAttribute("code", code);
            model.addAttribute("noWxGm", noWxGm);
            model.addAttribute("wxAddType", wxAddType);
        } catch (Exception e) {
            logger.error("添加微信好友-查询商户下的终端微信列表错误！", e);
        }

        return PAGE_VIEW_ADD_WX_FRIENDS_EDIT_VIEW;
    }
	
	/**
	 * 
	 *
	 * 方法说明：添加微信好友-修改
	 *
	 * @param model
	 * @param code
	 * @param noWxGm
	 * @param wxAddType
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月13日
	 *
	 */
	@RequiresPermissions("member:addWxFriends:edit")
    @RequestMapping(value={"addWxFriends/edit"})
	@ResponseBody
    public CommonRepsonseDto addWxFriendsEdit(Model model, String code, String noWxGm, Integer wxAddType) {
		CommonRepsonseDto commonRepsonseDto = null;
	    try {
	    	// 查询终端微信
            FindShopTerminalReturn shopTerminalReturn = shopTerminalService.findShopTerminalNormal(noWxGm);
	        
            UpdateAddFriends updateAddFriends = new UpdateAddFriends();
            updateAddFriends.setCode(code);
            updateAddFriends.setNoWxGm(noWxGm);
            updateAddFriends.setWxAddType(wxAddType);
            
            //更新终端
//            updateAddFriends.setShopNo(shopTerminalReturn.getShopNo());
//            updateAddFriends.setShopName(shopTerminalReturn.getShopName());
            
            addFriendsService.updateAddFriends(updateAddFriends);
            
            commonRepsonseDto = CommonRepsonseDto.generateSuccessResponse("保存成功!");
        } catch (Exception e) {
            logger.error("添加微信好友修改失败", e);
            commonRepsonseDto = CommonRepsonseDto.generateFailureResponse("保存失败");
        }
	    return commonRepsonseDto;
    }
	
	/**
	 * 
	 *
	 * 方法说明：添加微信好友导入模版
	 *
	 * @param response
	 * @param request
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月13日
	 *
	 */
    @RequiresPermissions("member:addWxFriends:view")
    @RequestMapping(value = "import/templateAddWxFriends")
    public String importFileTemplateAddWxFriends(HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "添加微信好友导入模版.xlsx";
            List<AddWxFriendsDto> list = Lists.newArrayList();
            AddWxFriendsDto dto = new AddWxFriendsDto();
            dto.setMemberName("张晓强");
            dto.setNoWx("wxid_9zow7lnqi1hp22");
            dto.setMobile("18577655123");
            dto.setNoQQ("1026561254");
            dto.setSex(Gender.MALE.toString());
            dto.setLabelName("意向客户");
            dto.setNoWxGm("wxid_omw4kf3qhhsr22");
            dto.setMemberSrc("百度");
            list.add(dto);

            dto = new AddWxFriendsDto();
            dto.setMemberName("李丹");
            dto.setNoWx("wxid_owbtqcfv9mn122");
            dto.setMobile("18577651234");
            dto.setNoQQ("857398391");
            dto.setSex(Gender.FEMALE.toString());
            dto.setLabelName("优质客户");
            dto.setNoWxGm("wxid_2twvlcah8ll941");
            dto.setMemberSrc("到店");
            list.add(dto);

            new ExportExcel("添加微信好友导入模版", AddWxFriendsDto.class, 2).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
        	logger.error("添加微信好友导入模板下载失败！", e);
            addMessage(redirectAttributes, "添加微信好友导入模板下载失败！失败信息：" + e.getMessage());
        }
        return PAGE_VIEW_REDIRECT_MEMBER;
    }
    
    /**
     * 
     *
     * 方法说明：微信好友列表导入（功能以被隐藏）
     *
     * @param file
     * @param redirectAttributes
     * @param request
     * @param model
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月25日
     *
     */
    @RequiresPermissions("member:addWxFriends:view")
    @RequestMapping(value = "importAddWxFriends", method = RequestMethod.POST)
    public String importExcelAddWxFriends(MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder("");
            ImportExcel ei = new ImportExcel(file, 1, 0);
            
            List<PmLabelDto> pmLabelDtos = pmLabelService.findPmLabelByMerchantNo(UserUtils.getMerchantNo());// 商户下的所有标签
			List<String> labelNameList = new ArrayList<>(pmLabelDtos.size());// 商户下的所有标签名的集合
			if (CollectionUtils.isNotEmpty(pmLabelDtos)) {
				for (PmLabelDto dto2 : pmLabelDtos) {
					labelNameList.add(dto2.getLabelName());
				}
			}

            List<AddWxFriendsDto> list = ei.getDataList(AddWxFriendsDto.class);
            for (AddWxFriendsDto dto : list) {
                try {
                    if (org.apache.commons.lang3.StringUtils.isBlank(dto.getMemberName())) {
                        failureMsg.append("<br/>客户名称不能为空 ");
                        failureNum++;
                        continue;
                    }
                    if (org.apache.commons.lang3.StringUtils.isBlank(dto.getSex())) {
                        failureMsg.append("<br/>性别不能为空 ");
                        failureNum++;
                        continue;
                    }
                    if (org.apache.commons.lang3.StringUtils.isBlank(dto.getMemberSrc())) {
                        failureMsg.append("<br/>客户来源不能为空 ");
                        failureNum++;
                        continue;
                    }
                    if (org.apache.commons.lang3.StringUtils.isBlank(dto.getNoWxGm())) {
                        failureMsg.append("<br/>终端微信号不能为空 ");
                        failureNum++;
                        continue;
                    }
                    
                    /* 处理excel数据转码 */
                    dto.setMobile(DictUtils.excelChage(dto.getMobile()));
                    /* 校验手机号格式 */
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(dto.getMobile()) && !Validator.isMobile(dto.getMobile())) {
                        failureMsg.append("<br/>客户手机号： ").append(dto.getMobile()).append("格式不正确; ");
                        failureNum++;
                        continue;
                    }
                    
                    /*检验标签*/
					if (!com.lj.base.core.util.StringUtils.isNullOrEmpty(dto.getLabelName())) {
						String[] labels = dto.getLabelName().split(",");// 多个标签进行拆分
						// 最终要保留的标签名称集合
						List<String> finalLabelNameList = new ArrayList<>();
						for (String labelName : labels) {
							if (labelName.length() < 20 && labelNameList.contains(labelName)) {
								finalLabelNameList.add(labelName);
							}
						}
						StringBuilder finalLabelName = new StringBuilder("");
						int len = finalLabelNameList.size() > 4 ? 4 : finalLabelNameList.size();
						for (int i = 0; i < len; i++) {
							finalLabelName.append(finalLabelNameList.get(i) + ",");
						}
						if (len > 0) {
							finalLabelName.deleteCharAt(finalLabelName.length() - 1);
						}
						dto.setLabelName(finalLabelName.toString());
					}

                    /* 校验性别 */
                    List<String> items = Lists.newArrayList();
                    for (Gender gender : Gender.values()) {
                        items.add(gender.toString());
                    }
                    if (!items.contains(dto.getSex().trim())) {
                        failureMsg.append("<br/>性别填写错误 ：").append(dto.getSex());
                        failureNum++;
                        continue;
                    }
                    
                    //校验AddFriends表中的重复数据
                    AddAddFriends addAddFriends = new AddAddFriends();
//                    addAddFriends.setHeadAddress("http://" + address.getHostAddress() + request.getContextPath() + "/static/admin/images/introduce/file.png");
                    addAddFriends.setMemberName(dto.getMemberName());
                    addAddFriends.setSex(dto.getSex());
                    addAddFriends.setLabelName(dto.getLabelName());
                    addAddFriends.setMemberSrc(dto.getMemberSrc());
                    addAddFriends.setNoWxGm(dto.getNoWxGm());
                    
                    boolean wxAvailable = false;
                    boolean mobileAvailable = false;
                    boolean qqAvailable = false;
                    
                    //微信号
                    if(!org.apache.commons.lang3.StringUtils.isBlank(dto.getNoWx())) {
                        FindAddFriendsPage findAddFriendsPage = new FindAddFriendsPage();
                        findAddFriendsPage.setNoWx(dto.getNoWx());
                        int findAddWxFriendsCount = addFriendsService.findAddWxFriendsCount(findAddFriendsPage);
                        
                        if (findAddWxFriendsCount > 0) {//已存在该微信
                            failureMsg.append("<br/>添加微信好友列表中已存在该微信号： ").append(dto.getNoWx());
                            failureNum++;
                            
                            continue;
                        }
                        addAddFriends.setNoWx(dto.getNoWx());
                        addAddFriends.setAlias(dto.getNoWx());
                        addAddFriends.setWxOpenId(WxOpenIdUtils.generateWxOpenId(dto.getNoWx()));
                        addAddFriends.setWxQrCode(dto.getNoWx());
                        addAddFriends.setWxAddType(6);
                        if (!checkNoWx("", dto.getNoWx())) {
                            addAddFriends.setRemark4("添加好友失败");
                        }
                        wxAvailable = true;
                    }
                    //手机号
                    if(!org.apache.commons.lang3.StringUtils.isBlank(dto.getMobile())) {
                        FindAddFriendsPage findAddFriendsPage = new FindAddFriendsPage();
                        findAddFriendsPage.setMobile(dto.getMobile());
                        int findAddWxFriendsCount = addFriendsService.findAddWxFriendsCount(findAddFriendsPage);
                        
                        if (findAddWxFriendsCount > 0) {//已存在该手机号
                            failureMsg.append("<br/>添加微信好友列表中已存在该手机号： ").append(dto.getMobile());
                            failureNum++;
                            
                            continue;
                        }
                        
                        addAddFriends.setMobile(dto.getMobile());
                        if (!wxAvailable) {
                            addAddFriends.setWxQrCode(dto.getMobile());
                            addAddFriends.setWxAddType(5);
                            if (!checkMobile("", dto.getMobile())) {
                                addAddFriends.setRemark4("添加好友失败");
                            }
                            mobileAvailable = true;
                        }
                    }
                    //QQ号
                    if(!org.apache.commons.lang3.StringUtils.isBlank(dto.getNoQQ())) {
                        FindAddFriendsPage findAddFriendsPage = new FindAddFriendsPage();
                        findAddFriendsPage.setNoQQ(dto.getNoQQ());
                        int findAddWxFriendsCount = addFriendsService.findAddWxFriendsCount(findAddFriendsPage);
                        
                        if (findAddWxFriendsCount > 0) {//已存在该QQ号
                            failureMsg.append("<br/>添加微信好友列表中已存在该QQ号： ").append(dto.getNoQQ());
                            failureNum++;
                            
                            continue;
                        }
                        addAddFriends.setNoQq(dto.getNoQQ());
                        if (!mobileAvailable) {
                            addAddFriends.setWxQrCode(dto.getNoQQ());
                            addAddFriends.setWxAddType(7);
                            qqAvailable = true;
                        }
                    }
                    
                    if (!wxAvailable && !mobileAvailable && !qqAvailable) {
                        failureMsg.append("<br/>微信号，手机号，QQ号三者至少一个有效 ");
                        failureNum++;
                        
                        continue;
                    }
                    
                    addFriendsService.addAddWxFriends(addAddFriends);
                    successNum++;
                } catch (Exception ex) {
					logger.error("添加微信好友失败", ex);
                    String title = "";
                    if (org.apache.commons.lang3.StringUtils.isBlank(dto.getNoWx())) {
                        if (org.apache.commons.lang3.StringUtils.isBlank(dto.getMobile())) {
                            title = "QQ号" + dto.getNoQQ();
                        } else {
                            title = "手机号" + dto.getMobile();
                        }
                    } else {
                        title = "微信号" + dto.getNoWx();
                    }
                    failureMsg.append("<br/>").append(title).append("导入失败：").append(ex.getMessage());
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "失败 " + failureNum + " 条记录");
            }
//            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条记录" + failureMsg);
            model.addAttribute("repMsg", "已成功导入 " + successNum + " 条记录" + failureMsg);
        } catch (Exception e) {
        	logger.error("导入添加微信好友失败！", e);
//            addMessage(redirectAttributes, "导入添加微信好友失败！失败信息：" + e.getMessage());
            model.addAttribute("repMsg", "导入添加微信好友失败！失败信息：" + e.getMessage());
        }
//        return "redirect:" + Global.getAdminPath() + "/member/addWxFriends/list?addWxFriends=1";
        
        FindAddFriendsPage findAddFriendsPage = new FindAddFriendsPage();
        findAddFriendsPage.setAddWxFriends(1);
        return addWxFriendsList(model, findAddFriendsPage , 1, 10);
    }
    
    /**
     * 
     *
     * 方法说明：导出添加微信好友数据
     *
     * @param response
     * @param findAddFriendsPage
     * @param pageNo
     * @param pageSize
     * @param redirectAttributes
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年4月9日
     *
     */
    @RequiresPermissions("member:addWxFriends:view")
    @RequestMapping(value = "exportAddWxFriends")
    public String exportAddWxFriends(HttpServletResponse response, FindAddFriendsPage findAddFriendsPage, Integer pageNo, Integer pageSize, RedirectAttributes redirectAttributes) {
        try {
            findAddFriendsPage.setMerchantNo(UserUtils.getMerchantNo());
            
            findAddFriendsPage.setSortDir(PageSortType.desc);
            findAddFriendsPage.setStart(0);
            findAddFriendsPage.setMaxLimit();
            
            Page<FindAddFriendsPageReturn> pageDto = addFriendsService.findAddFriendsPage(findAddFriendsPage);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (FindAddFriendsPageReturn return1 : pageDto.getRows()) {
                String addStatus = return1.getAddStatus().equals("Y") ? "已添加" : "未添加";
                return1.setAddStatus(addStatus);
                return1.setRemark(sdf.format(return1.getCreateDate()));
            }
            
            List<FindAddFriendsPageReturn> list = Lists.newArrayList();
            list.addAll(pageDto.getRows());
            
            String fileName = "添加微信好友数据导出.xlsx";
            new ExportExcel("添加微信好友数据导出", AddWxFriendsExportDto.class, 2).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
        	logger.error("客户数据导出失败！", e);
            addMessage(redirectAttributes, "客户数据导出失败！失败信息：" + e.getMessage());
        }
        return PAGE_VIEW_REDIRECT_MEMBER_REPAGE;
    }
    
    /**
	 *
	 * 方法说明：已指定导购的客户列表执行加好友动作(做调用次数校验)
	 * @param model
	 * @param code PMB主键
	 * @param memberNoGM 导购编号
	 * @return
	 * @author 李端强 CreateDate: 2018年1月12日
	 */
	@RequestMapping(value="doListApplayFriend")
	@ResponseBody
	public Map<String, Object> doListApplayFriend(Model model,String code,String memberNoGM){
		Map<String, Object> retMap = Maps.newHashMap();
		String merchantNo = UserUtils.getMerchantNo();//商户编号
		FindPersonMemberBase findBase = new FindPersonMemberBase();
		findBase.setCode(code);
		FindPersonMemberBaseReturn pmb = personMemberBaseService.findPersonMemberBase(findBase);
		FindWxInfoRequestDto findWxInfoRequestDto = new FindWxInfoRequestDto();
		findWxInfoRequestDto.setMemberNoGm(pmb.getMemberNoGm());//导购编号
		if(!com.lj.base.core.util.StringUtils.isEmpty(pmb.getNoWx())) {
			findWxInfoRequestDto.setWxQrCode(pmb.getNoWx());//搜索条件微信号
		}else if(!com.lj.base.core.util.StringUtils.isEmpty(pmb.getMobile())) {
			findWxInfoRequestDto.setWxQrCode(pmb.getMobile());//搜索条件手机号
		}else if(!com.lj.base.core.util.StringUtils.isEmpty(pmb.getNoQQ())) {
			findWxInfoRequestDto.setWxQrCode(pmb.getNoQQ());//搜索条件QQ
		}
		FindGuidMember findGuid = new FindGuidMember();
		findGuid.setMemberNo(memberNoGM);//导购编号
		FindGuidMemberReturn guid = guidMemberService.findGuidMember(findGuid);//导购信息
		findWxInfoRequestDto.setNoWxGM(guid.getNoWx());//中控端微信号
		findWxInfoRequestDto.setAddCode(KeyConstant.OMS_PMB_LIST_SEARCH_ADD_PREFIX+pmb.getCode()+","+memberNoGM);//pmb表主键+","+导购编号,带前缀
		if(AddQrCodeUtils.limited(merchantNo,guid.getNoWx(), redisCache, merchantParamsService, KeyConstant.ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_KEY)) {
			retMap.put("success", false);
			retMap.put("msg", "今天添加的好友数量已达上限");
			return retMap;
		};
		

		//获取导购对应的中控微信号
		FindGuidMember findGuidMember = new FindGuidMember();
		String loginAccountNo = "";
		if(!StringUtils.isEmpty(findWxInfoRequestDto.getMemberNoGm())) {//存在导购编号
			findGuidMember.setMemberNo(findWxInfoRequestDto.getMemberNoGm());//导购编号,查询WX号
			FindGuidMemberReturn guida = guidMemberService.findGuidMember(findGuidMember);
			loginAccountNo = guida.getNoWx();	// 客户微信对应的中控客户端登录账号
		}else {
			loginAccountNo = findWxInfoRequestDto.getNoWxGM();//直接使用中控微信号
		}
		
		IContactsService basic = commonService.getHessianContactsService(loginAccountNo);
		retMap = basic.sendAddNewFriendMessage(findWxInfoRequestDto, KeyConstant.OMS_PMB_LIST_SEARCH_ADD_PREFIX);
		logger.info("doListApplayFriend(String merchentNo={}, String merchentWxNo={}, String qrCode={}) - 进行首次搜索并自动发送添加申请!", merchantNo, findWxInfoRequestDto.getNoWxGM(), findWxInfoRequestDto.getWxQrCode());
		retMap.put("success", true);
		return retMap;
	}
	
	

	
	/**
	 * 
	 *
	 * 方法说明：批量分组-查询商户下的分类列表
	 *
	 * @param model
	 * @param pmCodes
	 * @param merchantNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
    @RequestMapping(value={"batchChangePmTypeSelect"})
    public String batchChangePmTypeSelect(Model model, String pmCodes, String merchantNo) {
        try {
            List<FindPmType> pmTypeList = pmTypeService.inqueryMemberOutOffGroupInfo(merchantNo, null, null, "1");
            
            model.addAttribute("pmTypeList", pmTypeList);
            model.addAttribute("pmCodes", pmCodes);
            model.addAttribute("merchantNo", merchantNo);
        } catch (Exception e) {
            logger.error("批量分组-查询商户下的分类列表错误！", e);
        }

        return PAGE_VIEW_BATCH_CHANGE_PM_TYPE_SELECT;
    }
    
	/**
	 * 
	 *
	 * 方法说明：客户批量分组
	 *
	 * @param model
	 * @param pmCodes
	 * @param pmTypeCode
	 * @param merchantNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
    @RequiresPermissions("member:member:edit")
    @RequestMapping(value={"batchChangePmType"})
    @ResponseBody
    public CommonRepsonseDto batchChangePmType(Model model, String pmCodes, String pmTypeCode, String merchantNo) {
    	CommonRepsonseDto commonRepsonseDto = null;
        try {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(pmCodes)) {
                String[] pmCodeArr = pmCodes.split(",");
                ChangePmTypeHc[] changePmTypeHcArr = new ChangePmTypeHc[pmCodeArr.length];
                for (int i = 0; i < pmCodeArr.length; i++) {
                    ChangePmTypeHc changePmTypeHc = new ChangePmTypeHc();
                    changePmTypeHc.setCodePm(pmCodeArr[i]);
                    changePmTypeHc.setPmTypeCode(pmTypeCode);
                    changePmTypeHc.setMerchantNo(merchantNo);
                    
                    changePmTypeHcArr[i] = changePmTypeHc;
                }
                
                for (ChangePmTypeHc changePmTypeHc : changePmTypeHcArr) {
                    pmTypeService.changePmTypeHc(changePmTypeHc);
                }
                
                commonRepsonseDto = CommonRepsonseDto.generateSuccessResponse("分组成功!");
            } else {
                commonRepsonseDto = CommonRepsonseDto.generateFailureResponse("请至少选择一个客户");
            }
        } catch (Exception e) {
            logger.error("批量分组错误！", e);
            commonRepsonseDto = CommonRepsonseDto.generateFailureResponse("批量分组错误！");
        }
        return commonRepsonseDto;
    }
	
	/**
	 * 
	 *
	 * 方法说明：今日客户列表
	 *
	 * @param model
	 * @param findPersonMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月23日
	 *
	 */
	@RequestMapping(value = { "todayList"})
	public String todayList(Model model, FindPersonMemberPage findPersonMemberPage, Integer pageNo, Integer pageSize) {
		try {
			findPersonMemberPage.setMerchantNo(UserUtils.getMerchantNo());
//			findPersonMemberPage.setShopNos(CcUtils.getShopNoList());
			findPersonMemberPage.setSortDir(PageSortType.desc);
			findPersonMemberPage.setStart(pageNo == null? 0:(pageNo - 1) * pageSize);
			findPersonMemberPage.setLimit(pageSize == null? 10:pageSize);
			
			Date now = new Date();
			findPersonMemberPage.setStartTime(DateUtils.getDateByFirstSeconds(now));
			findPersonMemberPage.setEndTime(DateUtils.getDateByLastSeconds(now));
			
			setArea(model, findPersonMemberPage);
			//1.04更改接口  拆分多表关联
			Page<FindPersonMemberPageReturn> pageDto = null; 
				pageDto = personMemberService.queryPersonMemberPage(findPersonMemberPage);
			List<FindPersonMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			
			
			FindGuidMember guidMember= null;
//			FindShop shop= null ;
			FindPersonMember findPersonMember = null ;
			for(FindPersonMemberPageReturn memberPageReturn:list){
				//同步名字
				guidMember=new FindGuidMember();
				guidMember.setMemberNo(memberPageReturn.getMemberNoGm());
				FindGuidMemberReturn guidMemberReturn= guidMemberService.findGuidMember(guidMember);
				memberPageReturn.setMemberNameGm(guidMemberReturn.getMemberName());
				
//				shop=new FindShop();
//				shop.setShopNo(memberPageReturn.getShopNo());
//				FindShopReturn shopReturn= shopService.findShopByShopNo(shop);
//				memberPageReturn.setShopName(shopReturn == null ? null:shopReturn.getShopName());
				
				// 获取成单次数findBuySuccessNum
				// 如果是非邀约型商户
					//查询客户分类
					findPersonMember = new FindPersonMember();
					findPersonMember.setMemberNo(memberPageReturn.getMemberNo());
					findPersonMember.setMemberNoGm(memberPageReturn.getMemberNoGm());
					FindPersonMemberReturnList returnList=personMemberService.queryPersonMemberPmType(findPersonMember);
					if(returnList != null){
						memberPageReturn.setPmTypeType(returnList.getPmTypeType());
						memberPageReturn.setPmTypeName(returnList.getTypeName());//页面显示客户分类名称
					}
					
					// 商户指定客户标签
                    String customerLabel = "";  
                    FindPmLabel findPmLabel = new FindPmLabel();
					findPmLabel.setMemberNo(memberPageReturn.getMemberNo());//客户编号
					findPmLabel.setMerchantNo(memberPageReturn.getMerchantNo());//商户编号
					findPmLabel.setShopWx(memberPageReturn.getShopWx());
					List<PmLabelDto> labelList = pmLabelService.findPmLabelByMemberNoAndMerchantNo(findPmLabel);
					for (PmLabelDto label : labelList) {
						customerLabel += ","+label.getLabelName();
					}
                    memberPageReturn.setRemark(customerLabel.replaceFirst(",", ""));
				}
				
//			}
			List<FindPmTypePageReturn> pmTypeList = findPmTypeList();
			model.addAttribute("pmTypeList", pmTypeList);
			
			com.ape.common.persistence.Page<FindPersonMemberPageReturn> page = new com.ape.common.persistence.Page<FindPersonMemberPageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
					pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("merchantNo", findPersonMemberPage.getMerchantNo());
			
			
			// 客户来源
			model.addAttribute("memerSources", MemerSourceType.values());
			model.addAttribute("paramMember", findPersonMemberPage);
		} catch (Exception e) {
			logger.error("获取今日客户信息错误！", e);
		}

		return PAGE_VIEW_MEMBER_TODAY_LIST;
	}


	/**
	 *
	 * 方法说明：获取商户客户分类
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 * 
	 */
	private List<FindPmTypePageReturn> findPmTypeList() {
		FindPmTypePageReturn findPmTypePageReturn = new FindPmTypePageReturn();
		findPmTypePageReturn.setMerchantNo(UserUtils.getMerchantNo());
		return pmTypeService.findPmTypePages(findPmTypePageReturn);
	}
	
	/**
	 * 
	 *
	 * 方法说明：未联系客户列表
	 *
	 * @param model
	 * @param findUnchatMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年07月17日
	 *
	 */
	@RequestMapping(value = { "unChatlist" })
	public String unChatlist(Model model, FindUnchatMemberPage findUnchatMemberPage, Integer pageNo, Integer pageSize) {
		try {
			findUnchatMemberPage.setMerchantNo(UserUtils.getMerchantNo());
//			findUnchatMemberPage.setShopNos(CcUtils.getShopNoList());
			findUnchatMemberPage.setStart(pageNo == null? 0:(pageNo - 1) * pageSize);
			findUnchatMemberPage.setLimit(pageSize == null? 10:pageSize);
			findUnchatMemberPage.setRegisterBeginTime(DateUtils.getDateByFirstSeconds(findUnchatMemberPage.getRegisterBeginTime()));
			findUnchatMemberPage.setRegisterEndTime(DateUtils.getDateByLastSeconds(findUnchatMemberPage.getRegisterEndTime()));
			findUnchatMemberPage.setChatBeginTime(DateUtils.getDateByFirstSeconds(findUnchatMemberPage.getChatBeginTime()));
			findUnchatMemberPage.setChatEndTime(DateUtils.getDateByLastSeconds(findUnchatMemberPage.getChatEndTime()));
			
			findUnchatMemberPage.setLeChatCount(2);	// 小于等于聊天记录数，暂定为2
			int flag = findUnchatMemberPage.getFlag()==null?0:findUnchatMemberPage.getFlag();//默认查询24小时内
			switch (flag) {
			case 4:		// 超过一个月（30天）未联系客户
				findUnchatMemberPage.setChatBeginTime(DateUtils.getDateByFirstSeconds(DateUtils.addDays(new Date(), -29)));
				findUnchatMemberPage.setRegisterEndTime(findUnchatMemberPage.getChatBeginTime());
				break;
			case 3:		// 超过14天未联系客户
				findUnchatMemberPage.setChatBeginTime(DateUtils.getDateByFirstSeconds(DateUtils.addDays(new Date(), -13)));
				findUnchatMemberPage.setRegisterEndTime(findUnchatMemberPage.getChatBeginTime());
				break;
			case 2:		// 超过7天未联系客户
				findUnchatMemberPage.setChatBeginTime(DateUtils.getDateByFirstSeconds(DateUtils.addDays(new Date(), -6)));
				findUnchatMemberPage.setRegisterEndTime(findUnchatMemberPage.getChatBeginTime());
				break;
			case 1:		// 超过24小时未联系客户
				findUnchatMemberPage.setChatBeginTime(DateUtils.addHours(new Date(), -24));
				findUnchatMemberPage.setRegisterEndTime(findUnchatMemberPage.getChatBeginTime());
				break;
			case 0:		// 24小时未联系新客户，即24小时内新增且未联系的客户
			default:
				findUnchatMemberPage.setChatBeginTime(DateUtils.addHours(new Date(), -24));
				findUnchatMemberPage.setRegisterBeginTime(findUnchatMemberPage.getChatBeginTime());
				break;
			}
			
			findUnchatMemberPage.setMemberName(StringEscapeUtils.unescapeHtml4(findUnchatMemberPage.getMemberName()));//HTML转义
			
//			IPage<FindUnchatMemberPageReturn> pageDto = personMemberService.findUnchatMember(findUnchatMemberPage);
//			List<FindUnchatMemberPageReturn> list = Lists.newArrayList();
//			list.addAll(pageDto.getRows());
			
			findUnchatMemberPage.setLimit(9999999);
			List<FindUnchatMemberPageReturn> list = personMemberService.findUnchatMemberList(findUnchatMemberPage);
			if(list == null) {
				list = new ArrayList<>();
			}
			
			int size = list.size();
			pageSize = size == 0 ? 10 : size;
			com.ape.common.persistence.Page<FindUnchatMemberPageReturn> page = 
					new com.ape.common.persistence.Page<FindUnchatMemberPageReturn>(pageNo == null ? 1 : pageNo, pageSize, size, list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("paramMember", findUnchatMemberPage);
			
//			FindShopOmsDto findShopOmsDto = new FindShopOmsDto();
//			FindShop fs= CcUtils.shopFilter();
//			findShopOmsDto.setCompanyNo(fs.getCompanyNo());
//			findShopOmsDto.setMemberNoMerchant(fs.getMemberNoMerchant());
//			findShopOmsDto.setShopNos(fs.getShopNos());
//			model.addAttribute("shops",shopService.findShopsByMerchantNoAndShopNos(findShopOmsDto));//所属终端
			model.addAttribute("memerSources", MemerSourceType.values());// 客户来源
			
		} catch (Exception e) {
			logger.error("获取客户信息错误！", e);
		}

		return PAGE_VIEW_MEMBER_UNCHAT_LIST;
	}
	
	
	
	/**
	 * 批量取消认领
	 */
	@RequestMapping(value="/cancleBingFriends")
	@ResponseBody
    public ResponseDto cancleBingFriends(Model model, CancleBingFriendsDto cancleBingFriendsDto){
        logger.info("cancleBindFriends :" + cancleBingFriendsDto);
		try {
			if(null == cancleBingFriendsDto || null == cancleBingFriendsDto.getList() || cancleBingFriendsDto.getList().size()<=0) {
				return ResponseDto.failureResp("0", "至少选择一个客户");
			}
			
			taskExecutor.execute(new Runnable() {	// 通过线程池通知,应改为异步消息 
				@Override
				public void run() {
					try {
						for (Map<String, String> map : cancleBingFriendsDto.getList()) {
							String wxNo = map.get("wxNo");
					        String gmNo = map.get("gmNo");
					        String noWxGm = map.get("noWxGm");
					        if(StringUtils.isNotBlank(wxNo) && StringUtils.isNotBlank(gmNo) && StringUtils.isNotBlank(noWxGm)) {
					        	//置空记录
					            personMemberService.updateCanclePersonMember(gmNo, wxNo,noWxGm);
					            //清空friend导购信息
					            addFriendsService.updateCancleAddFriendsData(gmNo, wxNo,noWxGm);
					            //清空朋友圈信息
					            wxFriendsDataService.updateCancleFriendsCommentData(gmNo, wxNo, null, noWxGm);
					            //通知APP取消认领成功
					            personMemberService.sendTransSuccess(gmNo, null, "2", noWxGm,wxNo);
					        }
						}
					} catch(Exception e) {
						e.printStackTrace();
						logger.error("取消客户绑定异常",e);
					}
				}
			});
			
            
            logger.debug(" data cancleBingFriends success :{}", "void");
            return ResponseDto.successResp(null);
		} catch (TsfaServiceException e){
			logger.error("取消客户绑定异常",e);
			return ResponseDto.failureResp(e.getExceptionCode(), "取消客户绑定异常");
		}
		
	}
}
