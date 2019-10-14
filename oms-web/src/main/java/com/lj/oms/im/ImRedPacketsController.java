package com.lj.oms.im;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.CacheUtils;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaException;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IWxAccountService;
import com.lj.business.weixin.dto.FindWxRedpackDetailInfoPage;
import com.lj.business.weixin.dto.WxJobInfoDto;
import com.lj.business.weixin.dto.WxJobRedPackInfoDto;
import com.lj.business.weixin.dto.WxRedpackDetailInfoDto;
import com.lj.business.weixin.emus.RedPackTypeEnum;
import com.lj.business.weixin.service.IWXJobHandler;
import com.lj.business.weixin.service.IWxJobInfoService;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.cc.service.ISystemInfoService;
import com.lj.distributecache.RedisCache;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.sys.SystemService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 类说明：导购助手群发红包H5接口
 * <p>
 * 详细描述：导购助手红包H5接口
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2018年1月29日
 */
@Controller
@RequestMapping(value = "${adminPath}/im/redPackets")
public class ImRedPacketsController extends BaseController{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ImRedPacketsController.class);
	@Autowired 
	ICommonService commonService;
	@Resource
	IWxJobInfoService wxJobInfoService;
	@Resource
	IWXJobHandler wxJobHandler;//重发
	@Resource
	IWxRedpackDetailInfoService wxRedpackDetailInfoService;
	@Resource
	private IPmTypeService pmTypeService; // 客户分类服务
	@Resource
	private IGmAssistantShopService gmAssistantShopService;
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	@Resource
	private IShopTerminalService shopTerminalService;
	@Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
    @Autowired 
	private ISystemInfoService systemInfo;
    
    @Autowired 
	private RedisCache redisCache;
	private static final String DEFAULT_DELAY_TIMES = "60";//发送红包默认延迟时间
	
	 /**
     * 方法说明：跳转红包任务页面
     * @param model
     * @return
     * @author 李端强 CreateDate: 2018年1月24日
     */
	@RequiresPermissions("im:redPackets:view")
    @RequestMapping(value={"show"})
    public String show(Model model) {
        try {
            User user = UserUtils.getUser();
            String userId = user.getId();
            String companyId = UserUtils.getMerchantNo();
            model.addAttribute("assistantNo", userId);
            model.addAttribute("merchantNo", companyId);
            String delayTimes = localCacheSystemParams.getSystemParam(SystemAliasName.weixin.name(), "min_delay", "redpackage_min_delay");
            if (org.apache.commons.lang.StringUtils.isBlank(delayTimes)) {
                delayTimes = DEFAULT_DELAY_TIMES;
            }
            model.addAttribute("delayTimes", delayTimes);
        } catch (Exception e) {
            logger.error("跳转红包任务页面错误：",e);
        }
        return "modules/im/sendRedEnvelopes";
    }
    
    
    /**
     * 方法说明：跳转红包记录列表
     * @param model
     * @return
     * @author 李端强 CreateDate: 2018年1月24日
     */
    @RequestMapping(value = "toRedRecordList")
    public String toRedRecordList(Model model,Integer pageNo,Integer pageSize,HttpServletRequest request,FindShopTidFromWeb findShopTidFromWeb) {
		try {
			User user = UserUtils.getUser();
			String userId = user.getId();
			String companyId = UserUtils.getMerchantNo();
			model.addAttribute("assistantNo", userId);
			model.addAttribute("merchantNo", companyId);
			request.getSession().setAttribute("user_photo", UserUtils.getUser().getPhoto());//登录用户的头像
			indexData(model, findShopTidFromWeb);
		} catch (Exception e) {
			logger.error("跳转红包记录列表错误：",e);
		}
        return "modules/im/redRecordList";
    }
    /**
     *
     * 方法说明：组装红包创建页信息
     * @param model
     * @param findShopTidFromWeb
     * @author 李端强 CreateDate: 2018年1月31日
     */
    private void indexData(Model model, FindShopTidFromWeb findShopTidFromWeb) {
		String assistantNo=UserUtils.getUser().getId();//导购助手编号
		String merchantNo = UserUtils.getMerchantNo();//商户编号
		
		//客户分组查询
		FindPmTypePageReturn findPmTypePageReturn=new FindPmTypePageReturn();
		findPmTypePageReturn.setStatus(CommonConstant.Y);
		findPmTypePageReturn.setMerchantNo(merchantNo);
		List<FindPmTypePageReturn> pmType=pmTypeService.findPmTypePages(findPmTypePageReturn);
		//新增一个 今日新增 类型
		FindPmTypePageReturn todayFlag=new FindPmTypePageReturn();
		todayFlag.setCode("1");
		todayFlag.setTypeName("今日新增");
		pmType.add(0,todayFlag);
		model.addAttribute("pmType",pmType);	
		//终端信息查询
		FindGmAssistantShop findGmAssistantShop=new FindGmAssistantShop();
		findGmAssistantShop.setAssistantNo(assistantNo);
		findGmAssistantShop.setFilterNoTerminal(Boolean.TRUE);	// 过滤掉没有添加终端的终端
		List<FindGmAssistantShopReturn> findGmAssistantShopList = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
		model.addAttribute("shops",findGmAssistantShopList);
		//导购信息查询
//		List<String> shopNos=Lists.newArrayList();
//		if(StringUtils.isNotBlank(findShopTidFromWeb.getShopNo())){
//			shopNos.add(findShopTidFromWeb.getShopNo());
//		}else{
//			for (FindGmAssistantShopReturn findGmAssistantShopReturn : findGmAssistantShopList) {
//				shopNos.add(findGmAssistantShopReturn.getShopNo());
//			}
//			
//		}
		FindGuidMemberPage findGuidMemberPage=new FindGuidMemberPage();
		findGuidMemberPage.setLimit(500);
		findGuidMemberPage.setMerchantNo(merchantNo);
//		findGuidMemberPage.setShopNos(shopNos);
		Page<FindGuidMemberPageReturn> pageDto = guidMemberService.findGuidMemberPage(findGuidMemberPage);
		List<FindGuidMemberPageReturn> guidMembers = Lists.newArrayList();
		guidMembers.addAll(pageDto.getRows());
		model.addAttribute("guidMembers",guidMembers);
		//导购助手管理的终端列表查询
		findShopTidFromWeb.setMerchantNo(merchantNo);
		findShopTidFromWeb.setAssistantNo(assistantNo);
		findShopTidFromWeb.setQueryOnlineBool(Boolean.TRUE); // 查询终端是否在线
		List<FindShopTidFromWebReturn> shopTids = shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
		model.addAttribute("shopTids",shopTids);
		//已发红包年份
		List<String> years = wxRedpackDetailInfoService.findWxRedpackDetailYears();
		model.addAttribute("redpackDetailYears", years);
		//查询的参数
		model.addAttribute("ShopTidFromWeb",findShopTidFromWeb);
		model.addAttribute("user_photo", UserUtils.getUser().getPhoto());
	}
    
    /**
     *
     * 方法说明：刷新终端微信的最新余额
     * @param noWxGMs 多个终端微信逗号,分隔
     * @return
     * @author 李端强 CreateDate: 2018年2月2日
     */
    @RequestMapping(value = "refreshRedPacketBalance")
    @ResponseBody
    public boolean refreshRedPacketBalance(String noWxGMs) {
    	try {
    		String[] noWxArr = noWxGMs.split(",");//多个微信号拆分
    		boolean result = Boolean.FALSE;
    		for (String noWx : noWxArr) {
	    		IWxAccountService basic = commonService.getHessianIWxAccountService(noWx);
	    		basic.sendFindWxAccountBalanceMessage(noWx);
    		}
		} catch (Exception e) {
			logger.error("刷新终端微信的最新余额错误：",e);
			return false;
		}
    	return true;
    }
    
    

    /**
     *
     * 方法说明：请求红包列表数据
     * @param model
     * @return
     * @author 李端强 CreateDate: 2018年1月30日
     */
    @RequestMapping(value = "getRedRecordList")
    @ResponseBody
    public Map<String, Object> getRedRecordList(Model model,String noWxShop,String searchYear,Integer pageNo, Integer pageSize) {
    	Map<String, Object> retMap = Maps.newHashMap();
		try {
			FindWxRedpackDetailInfoPage find = new FindWxRedpackDetailInfoPage();
			//默认查询第一页
	        pageNo = pageNo == null ? 1 : pageNo;
	        if (pageNo != null && pageNo > 0 && pageSize != null && pageSize > 0) {
	        	find.setStart((pageNo - 1) * pageSize);
	        	find.setLimit(pageSize);
	        }
			find.setNoWxShop(noWxShop);//终端微信号
			if(StringUtils.isEmpty(searchYear) ) {
				searchYear = DateUtils.formatDate(new Date(), "yyyy");
			}
			find.setYear(searchYear);//指定查询年份
			Page<WxRedpackDetailInfoDto> originalPage = wxRedpackDetailInfoService.findWxRedpackDetailInfoPage(find);
			find.setStatus("9");//发送中,成功
			Long totalMoney = wxRedpackDetailInfoService.findWxRedpackDetailTotalMoney(find);
			if(totalMoney==null) totalMoney = 0l;
			retMap.put("successMoney", totalMoney);//已发送的红包总额
			retMap.put("data", originalPage);
			retMap.put("success", true);
		} catch (Exception e) {
			logger.error("请求红包记录列表错误：",e);
			retMap.put("success", false);
			return retMap;
		}
        return retMap;
    }
    
    /**
     *
     * 方法说明：重发红包
     * @param model
     * @param code
     * @return
     * @author 李端强 CreateDate: 2018年1月30日
     *
     */
    @RequestMapping(value = "reHandler")
    @ResponseBody
    public boolean reHandler(Model model,String code) {
		logger.debug("reHandler(Model model={}, String code={}) - start", model, code);
    	boolean ret = true;
		try {
			wxJobHandler.reHandler(code);
		} catch (Exception e) {
			logger.error("重发红包错误：",e);
			return false;
		}
		logger.debug("reHandler(Model, String) - end - return value={}", ret);
        return ret;
    }
    
    /**
     *
     * 方法说明：校验登录密码是否正确
     * @param password
     * @return success=true校验通过
     * msg="提示内容"
     * @author 李端强 CreateDate: 2018年1月30日
     *
     */
    @RequestMapping(value = "verifyLoginPsw")
    @ResponseBody
    public Map<String, Object> verifyLoginPsw(String password) {
		logger.debug("verifyLoginPsw(String password={}) - start", password);
    	Map<String, Object> ret = Maps.newHashMap();
    	ret.put("success", false);//返回结果,false表示验证失败
    	try {
			User user = UserUtils.getUser();
			String psw = user.getPassword();
			boolean isTrue = SystemService.validatePassword(password, psw);
			ret.put("success", isTrue);//返回结果,true表示验证通过
			if(isTrue) {//验证通过
				verifyLoginPswTimes(user.getLoginName(), false, true);
			}else {//验证不通过
				if(verifyLoginPswTimes(user.getLoginName(), true, false)) {//连续失败
					ret.put("msg", "输入密码错误次数已达3次，请重新登录");
					ret.put("logout", true);//前台跳转,退出登录
					verifyLoginPswTimes(user.getLoginName(), false, true);//清除累积计数
				}
			}
			logger.debug("verifyLoginPsw(String) - end - return value={}", ret);
			return ret;
		} catch (Exception e) {
			logger.error("verifyLoginPsw(String)", e);
			return ret;
		}
    }
    
    /**
     *
     * 方法说明：同一用户记录尝试次数
     * @param useruame
     * @param isFail
     * @param clean
     * @return
     * @author 李端强 CreateDate: 2018年1月31日
     *
     */
    public boolean verifyLoginPswTimes(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)CacheUtils.get("verifyLoginPswMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("verifyLoginPswMap", loginFailMap);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		return loginFailNum >= 3;
	}
    
    /**
     *
     * 方法说明：创建红包任务
     * @param wxJobInfoDto
     * @param noWxMapping 终端微信和客户对应，字符拼接noWx#num#noWxs;noWx#num#noWxs
     * @param redpackContent 红包内容
     * @param count 红包个数
     * @param amount 单个红包金额
     * @return
     * @author 李端强 CreateDate: 2018年1月29日
     */
    @RequestMapping(value = "addRedPackets")
    @ResponseBody
    public Map<String, Object> addRedPackets(WxJobInfoDto wxJobInfoDto,String noWxMapping,String redpackContent,long amount) {
		logger.debug("addRedPackets(WxJobInfoDto wxJobInfoDto={}, String noWxMapping={}, String redpackContent={}, long amount={}) - start", wxJobInfoDto, noWxMapping, redpackContent, amount);
        Map<String, Object> resultMap = new HashMap<String, Object>(2);
        try {
            if (StringUtils.isNotBlank(redpackContent)) {
                redpackContent = StringEscapeUtils.unescapeHtml4(redpackContent).toString();
            }
        	//执行时间判断,2立即执行  1定时执行
        	if(wxJobInfoDto.getExecuteType().equals("1") && wxJobInfoDto.getExecuteTime().getTime() <= new Date().getTime()) {
        		 resultMap.put("success", false);
                 resultMap.put("msg", "执行时间不能在当前时间之前");

				logger.debug("addRedPackets(WxJobInfoDto, String, String, long) - end - return value={}", resultMap); 
                 return resultMap;
        	}
        	wxJobInfoDto.setCreateDate(new Date());
        	wxJobInfoDto.setCreateUser(UserUtils.getUser().getId());
        	wxJobInfoDto.setUpdateDate(new Date());
        	wxJobInfoDto.setJobLevel("1");
        	wxJobInfoDto.setJobName("addRedPackets-"+GUID.generateCode());
        	wxJobInfoDto.setJobService("redPackJobHandler");
        	//wxJobInfoDto.setJobDelayTime(1);
        	wxJobInfoDto.setJobType(RedPackTypeEnum.NORMAL.getType());
        	List<WxJobRedPackInfoDto> aList = addWxJobRedPackInfoDtoList(wxJobInfoDto,redpackContent,noWxMapping,amount);
        	wxJobInfoDto.setWxJobRedPackInfoDtoList(aList);
        	wxJobInfoService.addWxJobInfo(wxJobInfoDto);
            resultMap.put("success", true);
            resultMap.put("msg", "创建红包群发任务成功");
        } catch (TsfaException e) {
            logger.error("创建红包群发任务添加失败-{}", e);
            logger.error(e.getMessage());
            resultMap.put("success", false);
            resultMap.put("msg", e.getExceptionInfo());
        }
		logger.debug("addRedPackets(WxJobInfoDto, String, String, long) - end - return value={}", resultMap);
        return resultMap;
    }
    
    /**
     * 方法说明：校验多个终端剩余金额是否足够
     * @param tidMoney =  多条逗号分隔 (code终端主键#num红包个数#amount红包金额)
     * @return tidMoney_msg=提示信息
     * @author 李端强 CreateDate: 2018年1月31日
     */
    @RequestMapping(value = "verifyMoney")
    @ResponseBody
    public Map<String, Object> verifyMoney(String tidMoney,String noWxGMs){
    	Map<String, Object> retMap = Maps.newHashMap();
    	String[] tidMoneyArr = tidMoney.split(",");//余额校验
    	String moneyEnough = "";//终端余额提醒
    	for (String tid : tidMoneyArr) {
    		String[] tidArr = tid.split("#");
    		FindShopTerminal find = new FindShopTerminal();
        	find.setCode(tidArr[0]);//终端表主键
        	FindShopTerminalReturn dto = shopTerminalService.findShopTerminal(find);
        	if(dto==null) {
        		continue;
        	}else {
        		if(StringUtils.isEmpty(dto.getWxPwd())) {//支付密码为空
        			moneyEnough += "," + dto.getWxNickname()+" 未设置支付密码.请先设置微信支付密码!";
        			retMap.put("tidMoney_msg", moneyEnough.replaceFirst(",", ""));
        			return retMap;//支付密码校验优先级最高
        		}
        		Long wxBalance = dto.getWxBalance();
        		if(wxBalance==null) {
        			continue;
        		}else {
        			long wxlong = wxBalance.longValue();
        			if(wxlong < Integer.valueOf(tidArr[1])*Long.valueOf(tidArr[2]) ) {
        				moneyEnough += "," + dto.getWxNickname()+"("+wxlong*1.0/100+"元)";
        			}else {
        				continue;//余额充足
        			}
        		}
        	}
		}
    	if(StringUtils.isEmpty(moneyEnough)) {
    		retMap.put("tidMoney_msg", "");
    	}else {//存在余额不足的终端微信
    		retMap.put("tidMoney_msg", moneyEnough.replaceFirst(",", "")+" 终端微信余额不足,请向该网点微信号充值");
    	}
    	return retMap;
    }
    
    /**
     * 方法说明：校验多个终端剩余金额是否足够,多个中控微信当日已发红包总金额校验
     * @param tidMoney =  多条逗号分隔 (code终端主键#num红包个数#amount红包金额)
     * @param noWxGMs  =  多条逗号分隔
     * @return tidMoney_msg=提示信息
     * @return noWxGMs_noWxGM=当日已发金额
     * @author 李端强 CreateDate: 2018年1月31日
     */
    @RequestMapping(value = "verifyMoneySumAmt")
    @ResponseBody
    public Map<String, Object> verifyMoneySumAmt(String tidMoney,String noWxGMs){
    	Map<String, Object> retMap = Maps.newHashMap();
    	String[] tidMoneyArr = tidMoney.split(",");//余额校验
    	String moneyEnough = "";//终端余额提醒
    	for (String tid : tidMoneyArr) {
    		String[] tidArr = tid.split("#");
    		FindShopTerminal find = new FindShopTerminal();
        	find.setCode(tidArr[0]);//终端表主键
        	FindShopTerminalReturn dto = shopTerminalService.findShopTerminal(find);
        	if(dto==null) {
        		continue;
        	}else {
        		if(StringUtils.isEmpty(dto.getWxPwd())) {//支付密码为空
        			moneyEnough += "," + dto.getWxNickname()+" 未设置支付密码.请先设置微信支付密码!";
        			retMap.put("tidMoney_msg", moneyEnough.replaceFirst(",", ""));
        			return retMap;//支付密码校验优先级最高
        		}
        		Long wxBalance = dto.getWxBalance();
        		if(wxBalance==null) {
        			continue;
        		}else {
        			long wxlong = wxBalance.longValue();
        			if(wxlong < Integer.valueOf(tidArr[1])*Long.valueOf(tidArr[2]) ) {
        				moneyEnough += "," + dto.getWxNickname()+"("+wxlong*1.0/100+"元)";
        			}else {
        				continue;//余额充足
        			}
        		}
        	}
		}
    	if(StringUtils.isEmpty(moneyEnough)) {
    		retMap.put("tidMoney_msg", "");
    	}else {//存在余额不足的终端微信
    		retMap.put("tidMoney_msg", moneyEnough.replaceFirst(",", "")+" 终端微信余额不足,请向该网点微信号充值");
    	}
    	
    	String[] noWxGMArr = noWxGMs.split(",");//单个中控微信当日已发金额校验
    	for (String noWxGM : noWxGMArr) {
    		Long sumAmt = wxRedpackDetailInfoService.getSumAmtByNoWxToday(noWxGM);
			if(sumAmt!=null) {
				retMap.put("noWxGMs_"+noWxGM, sumAmt);
			}else {
				retMap.put("noWxGMs_"+noWxGM, 0);
			}
		}
    	return retMap;
    }
    
    /**
     * 方法说明：多个中控微信当日已发红包总金额校验
     * @param noWxGM
     * @return
     * @author 李端强 CreateDate: 2018年1月31日
     */
    @RequestMapping(value = "getSumAmtByNoWxToDay")
    @ResponseBody
    public Map<String, Object> getSumAmtByNoWxToDay(String noWxGMs){
    	Map<String, Object> retMap = Maps.newHashMap();
    	String[] noWxGMArr = noWxGMs.split(",");//单个中控微信当日已发金额校验
    	for (String noWxGM : noWxGMArr) {
    		Long sumAmt = wxRedpackDetailInfoService.getSumAmtByNoWxToday(noWxGM);
			if(sumAmt!=null) {
				retMap.put("noWxGMs_"+noWxGM, sumAmt);
			}else {
				retMap.put("noWxGMs_"+noWxGM, 0);
			}
		}
    	return retMap;
    }
    
    /**
     * 方法说明：构造商户微信集合
     * @param wxJobInfoDto
     * @param amount 单个红包金额分
     * @return
     * @author 李端强 CreateDate: 2018年1月30日
     */
    private List<WxJobRedPackInfoDto> addWxJobRedPackInfoDtoList(WxJobInfoDto wxJobInfoDto,String redpackContent,String noWxMapping,long amount) {
    	String merchantNo = UserUtils.getUser().getCompany().getId();//商户编号
    	List<WxJobRedPackInfoDto> list = Lists.newArrayList();//中控微信集合
    	String[] noWxMappingArr = noWxMapping.split(";");//分隔   meilihome2017#2#wxid_z6f202n4rmry21,wxid_0vvlcekoxdjh21;fanfan558989#0#;zhsozc3595#0#
    	Set<String> noReplayWx = Sets.newHashSet();//总的客户微信号去重复
    	for (String noDto : noWxMappingArr) {
    		String[] split = noDto.split("#");
    		String wxNoShop = split[0];//商户微信号
    		String num = split[1]; //红包总数
    		if(Integer.valueOf(num).intValue() == 0) {
    			continue;//该导购无对应客户,则过滤
    		}
    		String cnoWxs = split[2];//客户微信集合
    		List<String> noWxs = Lists.newArrayList();//导购各自客户微信号集合
    		for (String cnoWx : cnoWxs.split(",")) {
    			if(!noReplayWx.contains(cnoWx)) {//剔除交叉客户
    				noWxs.add(cnoWx);
        			noReplayWx.add(cnoWx);//客户微信号
    			}
    		}
    		WxJobRedPackInfoDto wxJobRedPackInfoDto  = new WxJobRedPackInfoDto();
			wxJobRedPackInfoDto.setCreateDate(new Date());
			wxJobRedPackInfoDto.setRedpackContent(redpackContent);
			wxJobRedPackInfoDto.setRedpackAmount( amount );//单个红包金额
			wxJobRedPackInfoDto.setRedpackCount( Integer.parseInt(num) );//该商户微信发的红包个数
			wxJobRedPackInfoDto.setWxNoShop(wxNoShop);//商户微信号
			wxJobRedPackInfoDto.setRedPackList(noWxs);//客户微信号集合
			wxJobRedPackInfoDto.setMerchantNo(merchantNo);//商户编号
			if(noWxs.size()>0) {//交叉客户,导致客户微信号为空,则放弃保存
				list.add(wxJobRedPackInfoDto);
			}
		}
		return list;
    }
    
}
