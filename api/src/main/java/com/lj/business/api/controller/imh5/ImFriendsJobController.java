package com.lj.business.api.controller.imh5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.emus.PmTypeTimeFlag;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.dto.friendsjob.AddSendFriendsJob;
import com.lj.business.weixin.service.ISendFriendsJobService;
import com.lj.distributecache.RedisCache;
import com.lj.oms.entity.sys.User;

/**
 * 
 * 类说明：发送朋友圈任务
 * <p>
 * 详细描述：朋友圈任务以及朋友圈评论
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 * CreateDate: 2017年12月23日
 */
@Controller
@RequestMapping(value = "/imh5/friendsjob/")
public class ImFriendsJobController extends Action {
	private static Logger logger = LoggerFactory.getLogger(ImFriendsJobController.class);
	public static final String USER_MERCHANT_DEFAULT_PRODUCT_TYPE = "INVITE";//商户默认产品类型(邀约型)
    @Autowired
    private IShopTerminalService shopTerminalService;
    @Autowired 
    private ISendFriendsJobService sendFriendsJobService;
    @Resource
	private IPmTypeService pmTypeService; // 客户分类服务
    @Resource
	private IGmAssistantShopService gmAssistantShopService;
    @Resource
	private IGuidMemberService guidMemberService; // 导购服务
    @Resource
	private IMerchantService merchantService;
    @Autowired 
	ICommonService commonService;
    @Autowired 
	private RedisCache redisCache;
    

    /**
     * 
     * 方法说明：根据导购助手编号查询其管理的终端列表
     * @param assistantNo
     * @return
     * @author 许新龙 CreateDate: 2017年12月23日
     */
    @RequestMapping(value = "findShopTerminalList.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<FindShopTidFromWebReturn> findShopTerminalList(
            FindShopTidFromWeb findShopTidFromWeb) {
    	try {
        	findShopTidFromWeb.setQueryOnlineBool(Boolean.TRUE); // 查询终端是否在线
            List<FindShopTidFromWebReturn> findShopTerminalFromWeb =
                    shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
            return findShopTerminalFromWeb;
        } catch (Exception e) {
            logger.error("查询导购助手门店终端列表错误：" + e);
        }
        return new ArrayList<>(0);
    }
    
    /**
     * 方法说明：创建发朋友圈任务
     * @param addSendFriendsJob
     * @return
     * @author 李端强 CreateDate: 2018年1月29日
     */
    @RequestMapping(value = "addFriendsJob.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> addFriendsJob(AddSendFriendsJob addSendFriendsJob) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            sendFriendsJobService.addSendFriendsJob(addSendFriendsJob);
            resultMap.put("result", 1);
            resultMap.put("msg", "发送朋友圈任务添加成功");
        } catch (TsfaException e) {
            logger.error("添加朋友圈任务失败-{}", e);
            resultMap.put("result", 0);
            resultMap.put("msg", e.getExceptionInfo());
        } catch (Exception e) {
            logger.error("添加朋友圈任务失败-{}", e);
            resultMap.put("result", 0);
            resultMap.put("msg", "系统异常，请稍后重试");
        }

        return resultMap;
    }
    
    /**
     * 方法说明：进入朋友圈评论
     * @param pageNo
     * @param pageSize
     * @param findShopTidFromWeb
     * @param userId
     * @return
     * @author 李端强 CreateDate: 2018年2月6日
     *
     */
    @RequestMapping(value = {"friendList.do"})
    @ResponseBody
	public Map<String,Object> friendList(Integer pageNo,Integer pageSize,FindShopTidFromWeb findShopTidFromWeb,String userId){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			indexData(resultMap, findShopTidFromWeb,userId);
		} catch (Exception e) {
			logger.error("查询首页信息错误：" + e);
		}
		return resultMap;
	}
    
    /**
	 * 方法说明：首页信息组装
	 * @param retMap
	 * @param findShopTidFromWeb
	 * @author 李端强 CreateDate: 2018年1月23日
	 *
	 */
	private void indexData(Map<String, Object> retMap, FindShopTidFromWeb findShopTidFromWeb,String userId) {
		String assistantNo=userId;//导购助手编号
		User user = getUserByCache(userId);
		String merchantNo = user.getCompany().getId();//商户编号
		
		//客户分组查询
		FindPmTypePageReturn findPmTypePageReturn=new FindPmTypePageReturn();
		findPmTypePageReturn.setStatus("Y");
		findPmTypePageReturn.setMerchantNo(merchantNo);
		List<FindPmTypePageReturn> pmType=pmTypeService.findPmTypePages(findPmTypePageReturn);
		//新增 今日新增/7天内新增/30天内新增 客户类型
		addTimePmType(pmType);
		retMap.put("pmType", pmType);
		//门店信息查询
		FindGmAssistantShop findGmAssistantShop=new FindGmAssistantShop();
		findGmAssistantShop.setAssistantNo(assistantNo);
		findGmAssistantShop.setFilterNoTerminal(Boolean.TRUE);	// 过滤掉没有添加终端的门店
		List<FindGmAssistantShopReturn> findGmAssistantShopList = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
		retMap.put("shops", findGmAssistantShopList);
		//导购信息查询
		List<String> shopNos=Lists.newArrayList();
		FindGuidMemberPage findGuidMemberPage=new FindGuidMemberPage();
		findGuidMemberPage.setLimit(500);
		findGuidMemberPage.setMerchantNo(merchantNo);
		findGuidMemberPage.setShopNos(shopNos);
		Page<FindGuidMemberPageReturn> pageDto = guidMemberService.findGuidMemberPage(findGuidMemberPage);
		List<FindGuidMemberPageReturn> guidMembers = Lists.newArrayList();
		guidMembers.addAll(pageDto.getRows());
		retMap.put("guidMembers", findGmAssistantShopList);
		//导购助手管理的门店列表查询
		findShopTidFromWeb.setMerchantNo(merchantNo);
		findShopTidFromWeb.setAssistantNo(assistantNo);
		List<FindShopTidFromWebReturn> shopTids = shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
		retMap.put("shopTids", shopTids);
		//查询的参数
		retMap.put("ShopTidFromWeb", findShopTidFromWeb);
		retMap.put("ProductType", getProductType(user.getId()));
		retMap.put("user_photo", user.getPhoto());
	}
	
	/**
	 *
	 * 方法说明：批量获取门店微信的在线状态
	 * @param noWxShops	门店微信号列表，英文逗号隔开
	 * @return
	 * @author 曾垂瑜 CreateDate: 2018年2月1日
	 */
	@RequestMapping(value="getZkTerminalStatusList.do")
	@ResponseBody
	public Map<String, Boolean> getZkTerminalStatusList(String noWxShops){
		if(StringUtils.isEmpty(noWxShops)) {
			return null;
		}

		try {
			
	        List<String> noWxList= Arrays.asList(noWxShops.split(","));
			Map<String, Boolean> map = new HashMap<String, Boolean>();
	        for(String noWx : noWxList) {
	        	//集群环境下手动调用
	        	ICommonService basic= commonService.getHessianCommonService( noWx);
	            map.put(noWx, basic.getZkTerminalStatus(noWx));
	        }
	        return map;
		}catch(Exception e) {
			logger.error("获取门店微信的在线状态错误：" + e);
			return null;
		}
		
	}
	

	
    /**
	 *
	 * 方法说明：商户类型,邀约/非邀约
	 * @param userId
	 * @return
	 * @author 李端强 CreateDate: 2018年1月24日
	 */
	public String getProductType(String userId){
		User user = getUserByCache(userId);
		FindMerchantDto findMerchantDto=new FindMerchantDto();
		findMerchantDto.setMerchantNo(user.getCompany().getId());
		FindMerchantReturnDto merchant = merchantService.selectMerchant(findMerchantDto);
		String productType = merchant.getProductType();
		if(StringUtils.isEmpty(productType)){
			return USER_MERCHANT_DEFAULT_PRODUCT_TYPE;
		}
		return productType;
	}
    /**
	 * 新增 今日新增/7天内新增/30天内新增 客户类型
	 * @author 彭俊霖
	 * @param pmType
	 */
	public void addTimePmType(List<FindPmTypePageReturn> pmType) {
		FindPmTypePageReturn todayFlag=new FindPmTypePageReturn();
		todayFlag.setCode(PmTypeTimeFlag.TODAY.toString());
		todayFlag.setTypeName(PmTypeTimeFlag.TODAY.getName());
		FindPmTypePageReturn weekFlag=new FindPmTypePageReturn();
		weekFlag.setCode(PmTypeTimeFlag.WEEK.toString());
		weekFlag.setTypeName(PmTypeTimeFlag.WEEK.getName());
		FindPmTypePageReturn monthFlag=new FindPmTypePageReturn();
		monthFlag.setCode(PmTypeTimeFlag.MONTH.toString());
		monthFlag.setTypeName(PmTypeTimeFlag.MONTH.getName());
		pmType.add(0,todayFlag);
		pmType.add(1,weekFlag);
		pmType.add(2,monthFlag);
	}

    /**
	 * 方法说明：缓存获取用户登录信息
	 * @param userId
	 * @return
	 * @author 李端强 CreateDate: 2018年1月29日
	 *
	 */
	private User getUserByCache(String userId) {
		AssertUtils.notNullAndEmpty(userId,"登录用户ID不能为空");
		String userStr = redisCache.get(userId);
		User user = (User) JsonUtils.objectFromJson(userStr, User.class);
		return user;
	}
}
