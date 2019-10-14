package com.lj.business.api.controller.imh5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.core.util.WxOpenIdUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.utils.AddQrCodeUtils;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.common.KeyConstant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.addfriends.AddAddFriends;
import com.lj.business.member.dto.addfriends.AddAddFriendsReturn;
import com.lj.business.member.dto.addfriends.FindAddFriends;
import com.lj.business.member.dto.addfriends.FindAddFriendsPage;
import com.lj.business.member.dto.addfriends.FindAddFriendsPageReturn;
import com.lj.business.member.dto.addfriends.FindAddFriendsReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.contacts.FindWxInfoRequestDto;
import com.lj.business.supcon.dto.contacts.FindWxInfoReturnDto;
import com.lj.business.supcon.dto.contacts.ScanAddFriendRequestDto;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.distributecache.RedisCache;
import com.lj.oms.utils.Validator;


/**
 * 类说明：搜索并添加微信好友
 * <p>
 * 详细描述：搜索并添加微信好友
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2018年1月29日
 */
@Controller
@RequestMapping(value="/imh5/addFriend/")
public class ImAddFriendAction extends Action{
	private static final Logger logger = LoggerFactory.getLogger(ImAddFriendAction.class);
	@Autowired 
	private RedisCache redisCache;
	@Resource
	private IShopTerminalService shopTerminalService;
	@Autowired
	private IAddFriendsService addFriendsService;
	@Autowired
	private IMerchantParamsService merchantParamsService;
	
	@Resource
	private IGuidMemberService guidMemberService;
	
    @Autowired 
	ICommonService commonService;
	 /**
     * 
     * 方法说明：根据导购助手编号查询其管理的单个终端
     * @param assistantNo
     * @param userId
     * @return
     * @author 李端强 2018年3月6日10:33:32
     */
    @RequestMapping(value = "findShopTerminal.do")
    @ResponseBody
    public FindShopTidFromWebReturn findShopTerminal(
    		String merchantNo,String memberNoGm) {
        try {
        	FindShopTidFromWeb web = new FindShopTidFromWeb();
        	web.setMerchantNo(merchantNo);
        	web.setAssistantNo(memberNoGm);
        	web.setQueryOnlineBool(Boolean.TRUE); // 查询终端是否在线
            List<FindShopTidFromWebReturn> findShopTerminalFromWeb =
                    shopTerminalService.findShopTerminalFromWeb(web);
            if(findShopTerminalFromWeb!=null && findShopTerminalFromWeb.size()>0) {
            	for (FindShopTidFromWebReturn findShopTidFromWebReturn : findShopTerminalFromWeb) {
					if(findShopTidFromWebReturn.getOnlineFlag()!=null && findShopTidFromWebReturn.getOnlineFlag().intValue()==1) {
						return findShopTidFromWebReturn;//只取一个在线
					}
				}
            }
        } catch (Exception e) {
            logger.error("查询导购助手门店终端列表错误：" + e);
        }
        return new FindShopTidFromWebReturn();
    }
	
	/**
	 *
	 * 方法说明：搜索微信基本信息
	 * @param merchentNo
	 * @param merchentWxNo
	 * @param qrCode
	 * @return
	 * @author 李端强 CreateDate: 2018年1月11日
	 *
	 */
	@RequestMapping(value="doSearchFriend.do")
	@ResponseBody
	public GeneralResponse doSeachFriend(String merchantNo,String noWxGm,String qrCode,int count){
		logger.debug("doSeachFriend(String merchentNo={}, String merchentWxNo={}, String qrCode={}) - start", merchantNo, noWxGm, qrCode);
		String addFriendKey = noWxGm + qrCode;//商户微信号+QrCode
		String searchRet = redisCache.hget(addFriendKey,KeyConstant.OMS_SEARCH_ADD_RET_PREFIX);//搜索结果
		FindWxInfoReturnDto dto = null;
		Map<String, Object> msgMap = Maps.newHashMap();
		try {
			if(searchRet!=null) {
				dto = (FindWxInfoReturnDto) JsonUtils.objectFromJson(searchRet,FindWxInfoReturnDto.class);//搜索到结果
			}else {
				String hasSearch = redisCache.hget(addFriendKey,KeyConstant.OMS_SEARCH_ADD_PREFIX);
				if(hasSearch==null) { //首次搜索
					msgMap = privateSearch(merchantNo,noWxGm,qrCode);
					FindPersonMemberBaseReturn findDto = (FindPersonMemberBaseReturn) msgMap.get("data");//好友已添加,无需再次发送搜索,直接返回DB中数据
					if(findDto!=null) {
						dto = new FindWxInfoReturnDto();
						dto.setNickNameWx(findDto.getNickNameWx());
						dto.setHeadAddress(findDto.getHeadAddress());
					}
				}else {//搜索进行中
					if(count>3) {//长时间未返回允许客户再次调用搜索中控
						logger.info("doSeachFriend(String merchentNo={}, String merchentWxNo={}, String qrCode={}) - 允许商户再次调用中控进行搜索!", merchantNo, noWxGm, qrCode);
						redisCache.hdel(addFriendKey,KeyConstant.OMS_SEARCH_ADD_PREFIX);
						msgMap = privateSearch(merchantNo,noWxGm,qrCode);
					}
				}
			}
		} catch (TsfaServiceException e) {
			logger.error("doSeachFriend(String, String, String)", e);
		}
		
		FindWxInfoReturnDto finalDto = new FindWxInfoReturnDto();
		String msg =null !=msgMap.get("msg")?msgMap.get("msg").toString():"";
		Object data =msgMap.get("data");
		if(dto!=null && dto.getScanId()!=null) {//成功搜索完成
			if(StringUtils.isEmpty(dto.getNoWx()) && StringUtils.isEmpty(dto.getNickNameWx())){
				return GeneralResponse.generateFailureResponse("",msg);
			}else {
				BeanUtils.copyProperties(dto, finalDto);
				redisCache.hdel(addFriendKey,KeyConstant.OMS_SEARCH_ADD_PREFIX);
				redisCache.hdel(addFriendKey,KeyConstant.OMS_SEARCH_ADD_RET_PREFIX);
				return GeneralResponse.generateSuccessResponse(finalDto);
			}
		}else {
			return GeneralResponse.generateSuccessResponse(msg, data);
		}
		
	}
	
	/**
	 * 
	 * 方法说明：获取导购助手微信信息
	 * @param model
	 * @param findShopTidFromWeb
	 * @return
	 * @author 梅宏博  CreateDate: 2017年12月23日
	 */
	@RequestMapping(value={"getShopWx.do"})
	@ResponseBody
	public List<FindShopTidFromWebReturn> getShopWx(FindShopTidFromWeb findShopTidFromWeb){
		return shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);//导购助手编号+商户编号
	}
	
	/**
	 * 方法说明：异步加载添加好友列表
	 * @return 
	 * @author 李端强 CreateDate: 2018年1月11日
	 *
	 */
	@RequestMapping(value="applayFriendList.do")
	@ResponseBody
	public GeneralResponse applayFriendList(String merchantNo,Integer pageNo,Integer pageSize){
		AssertUtils.notAllNullAndEmpty(merchantNo, "商户号不能为空");
		try {
			FindAddFriendsPage findAddFriendsPage = new FindAddFriendsPage();
			findAddFriendsPage.setMerchantNo(merchantNo);
			List<Integer> wxAddTypes = Lists.newArrayList();
			wxAddTypes.add(5);
			wxAddTypes.add(6);
			wxAddTypes.add(7);
			findAddFriendsPage.setWxAddTypes(wxAddTypes);//加好友的方式
			if(pageSize!=null){
				findAddFriendsPage.setLimit(pageSize);//尺寸
			}
			if(pageNo!=null){
				findAddFriendsPage.setStart((pageNo-1)*findAddFriendsPage.getLimit());//起始
			}
			Page<FindAddFriendsPageReturn> hisAddFriend = addFriendsService.findAddFriendsPage(findAddFriendsPage);//分页查询
			return GeneralResponse.generateSuccessResponse(hisAddFriend);
		} catch (Exception e) {
			logger.warn("applayFriendList(Model, Integer, Integer) - exception ignored", e);
			return GeneralResponse.generateFailureResponse(e);
		}
		
	}
	
	/**
	 *
	 * 方法说明：已搜索到的微信基本信息执行加好友动作(调用次数校验)
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param dto 中控返回的微信基本信息实体
	 * @return
	 * @author 李端强 CreateDate: 2018年1月12日
	 */
	@RequestMapping(value="doApplayFriend.do")
	@ResponseBody
	public Map<String, Object> doApplayFriend(String merchantNo,FindWxInfoReturnDto dto,String noWxGm,String nickRemark,String validation,String mobile){
		Map<String, Object> retMap = Maps.newHashMap();
		if(AddQrCodeUtils.limited(merchantNo,noWxGm,redisCache,merchantParamsService,KeyConstant.ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_KEY)) {
			retMap.put("success", false);
			retMap.put("msg", "今天添加的好友数量已达上限");
			return retMap;
		};
		//add_friends表添加记录,并向中控发送添加好友请求.
		AddAddFriends addAddFriends = new AddAddFriends();
		//addAddFriends.setMemberNoGm(dto.getMemberNoGm()); //商户不指定导购编号
		addAddFriends.setNoWxGm(noWxGm);
		addAddFriends.setWxQrCode(dto.getWxQrCode());
		if(Validator.isMobile(dto.getWxQrCode())) {//手机号
			addAddFriends.setMobile(dto.getWxQrCode());
			addAddFriends.setWxAddType(5);
		}else if(com.lj.base.core.util.StringUtils.isNumeric(dto.getWxQrCode())) {//QQ号
			addAddFriends.setNoQq(dto.getWxQrCode());
			addAddFriends.setWxAddType(7);
		}else {
			addAddFriends.setNoWx(dto.getWxQrCode());//微信号
			addAddFriends.setWxAddType(6);
		}
		addAddFriends.setAlias(dto.getAlias());
		addAddFriends.setNickNameWx(dto.getNickNameWx());
		addAddFriends.setNickRemark(nickRemark);//昵称备注
		addAddFriends.setHeadAddress(dto.getHeadAddress());//头像
		addAddFriends.setSex(dto.getSex());
		addAddFriends.setValidation(validation);//验证信息
		addAddFriends.setWxOpenId(WxOpenIdUtils.generateWxOpenId(dto.getNoWx()));//OPENID
		addAddFriends.setNoWx(dto.getNoWx());//临时微信号
		if(StringUtils.isEmpty(addAddFriends.getMobile())) {
			addAddFriends.setMobile(mobile);
		}
		AddAddFriendsReturn addAddFriendsReturn = null;
		try {
			addAddFriendsReturn = addFriendsService.addAddFriends(addAddFriends);
			logger.info("doApplayFriend,已保存添加微信好友记录：{}", addAddFriendsReturn);
		} catch(Exception e) {
			logger.error("doApplayFriend,保存添加微信好友记录异常", e);
			if(e.getMessage().indexOf(ErrorCode.PERSON_MEMBER_MOBILE_EXIST)!=-1) {
				retMap.put("msg", "同门店下，手机号已存在该客户");
			}else {
				retMap.put("msg", "无法添加该微信为好友,客户信息重复");
			}
			return retMap;
		}
		//向中控发送请求
		ScanAddFriendRequestDto scanAddFriendRequestDto = new ScanAddFriendRequestDto();
		scanAddFriendRequestDto.setNoWxGm(noWxGm);//导购微信号
		scanAddFriendRequestDto.setScanId(dto.getScanId());
		scanAddFriendRequestDto.setAddCode(addAddFriendsReturn.getCode());//主键
		scanAddFriendRequestDto.setNoWx(dto.getNoWx());//微信号
		scanAddFriendRequestDto.setAlias(dto.getAlias());
		scanAddFriendRequestDto.setNickRemark(dto.getNickNameWx());
		scanAddFriendRequestDto.setValidation(validation);//验证信息
		IContactsService basic =  commonService.getHessianContactsService(scanAddFriendRequestDto.getNoWxGm());
		basic.sendScanAddNewFriendMessage(scanAddFriendRequestDto);
		retMap.put("success", true);
		return retMap;
	}
	
	
	/**
	 *
	 * 方法说明：再次发送执行加好友动作(调用次数校验)
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param dto 中控返回的微信基本信息实体
	 * @return
	 * @author 李端强 CreateDate: 2018年1月12日
	 */
	@RequestMapping(value="doAddFriendAgian.do")
	@ResponseBody
	public Map<String, Object> doAddFriendAgian(String merchantNo,FindWxInfoReturnDto dto,String noWxGm,String nickRemark,String validation){
        Map<String, Object> retMap = new HashMap<String, Object>();
		//向中控发送请求
		ScanAddFriendRequestDto scanAddFriendRequestDto = new ScanAddFriendRequestDto();
		scanAddFriendRequestDto.setNoWxGm(noWxGm);//导购微信号
		scanAddFriendRequestDto.setScanId(dto.getScanId());
		scanAddFriendRequestDto.setNoWx(dto.getNoWx());//微信号
		scanAddFriendRequestDto.setAlias(dto.getAlias());
		scanAddFriendRequestDto.setNickRemark(dto.getNickNameWx());
		scanAddFriendRequestDto.setValidation(validation);//验证信息
		
		IContactsService basic =  commonService.getHessianContactsService(scanAddFriendRequestDto.getNoWxGm());
		basic.sendScanAddNewFriendMessage(scanAddFriendRequestDto);
		retMap.put("success", true);
		return retMap;
	}

	
	
	/**
	 *
	 * 方法说明：好友列表已存在并执行加好友动作(调用次数校验)
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param code 主键
	 * @return
	 * @author 李端强 CreateDate: 2018年1月12日
	 */
	@RequestMapping(value="doListApplayFriend.do")
	@ResponseBody
	public Map<String, Object> doListApplayFriend(String merchantNo,String code){
		Map<String, Object> retMap = Maps.newHashMap();
		FindAddFriends findAddFriends = new FindAddFriends();
		findAddFriends.setCode(code);
		FindAddFriendsReturn retData = addFriendsService.findAddFriends(findAddFriends);
		FindWxInfoRequestDto findWxInfoRequestDto = new FindWxInfoRequestDto();
		findWxInfoRequestDto.setMemberNoGm(retData.getMemberNoGm());//导购编号,可以不传
		findWxInfoRequestDto.setWxQrCode(retData.getWxQrCode());//搜索条件
		findWxInfoRequestDto.setNoWxGM(retData.getNoWxGm());//中控端微信号
		findWxInfoRequestDto.setAddCode(code);//add_friends表主键
		if(AddQrCodeUtils.limited(merchantNo, retData.getNoWxGm(),redisCache, merchantParamsService,KeyConstant.ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_KEY)) {
			retMap.put("success", false);
			retMap.put("msg", "今天添加的好友数量已达上限");
			return retMap;
		};
		
		//获取导购对应的中控微信号
		FindGuidMember findGuidMember = new FindGuidMember();
		String loginAccountNo = "";
		if(!StringUtils.isEmpty(findWxInfoRequestDto.getMemberNoGm())) {//存在导购编号
			findGuidMember.setMemberNo(findWxInfoRequestDto.getMemberNoGm());//导购编号,查询WX号
			FindGuidMemberReturn guid = guidMemberService.findGuidMember(findGuidMember);
			loginAccountNo = guid.getNoWx();	// 客户微信对应的中控客户端登录账号
		}else {
			loginAccountNo = findWxInfoRequestDto.getNoWxGM();//直接使用中控微信号
		}
		
		IContactsService basic = commonService.getHessianContactsService(loginAccountNo);
		retMap = basic.sendAddNewFriendMessage(findWxInfoRequestDto, KeyConstant.OMS_FRIEND_LIST_SEARCH_ADD_PREFIX);
		logger.info("doListApplayFriend(String merchentNo={}, String merchentWxNo={}, String qrCode={}) - 进行首次搜索并自动发送添加申请!", merchantNo, retData.getNoWxGm(), retData.getWxQrCode());
		retMap.put("success", true);
		return retMap;
	}
	
	/**
	 *
	 * 方法说明：向中控发送搜索请求
	 * @param merchentNo
	 * @param merchentWxNo
	 * @param qrCode
	 * @author 李端强 CreateDate: 2018年1月11日
	 */
	private Map<String, Object> privateSearch(String merchantNo,String merchantWxNo,String qrCode) {
		FindWxInfoRequestDto findWxInfoRequestDto = new FindWxInfoRequestDto();
		findWxInfoRequestDto.setMemberNoGm("");//导购编号,可以不传
		findWxInfoRequestDto.setWxQrCode(qrCode);//搜索条件
		findWxInfoRequestDto.setNoWxGM(merchantWxNo);//中控端微信号
		
		//获取导购对应的中控微信号
		FindGuidMember findGuidMember = new FindGuidMember();
		String loginAccountNo = "";
		if(!StringUtils.isEmpty(findWxInfoRequestDto.getMemberNoGm())) {//存在导购编号
			findGuidMember.setMemberNo(findWxInfoRequestDto.getMemberNoGm());//导购编号,查询WX号
			FindGuidMemberReturn guid = guidMemberService.findGuidMember(findGuidMember);
			loginAccountNo = guid.getNoWx();	// 客户微信对应的中控客户端登录账号
		}else {
			loginAccountNo = findWxInfoRequestDto.getNoWxGM();//直接使用中控微信号
		}
		//通过校验字符串判断搜索号码的类型
		IContactsService basic = commonService.getHessianContactsService(loginAccountNo);
		Map<String, Object> msgMap = basic.sendAddNewFriendMessage(findWxInfoRequestDto, KeyConstant.OMS_SEARCH_ADD_PREFIX);
		logger.info("doSeachFriend(String merchentNo={}, String merchentWxNo={}, String qrCode={}) - 进行首次搜索!", merchantNo, merchantWxNo, qrCode);
		return msgMap;
	}
}
