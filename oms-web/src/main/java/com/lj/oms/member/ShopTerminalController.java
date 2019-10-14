package com.lj.oms.member;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.CommonConstant;
import com.lj.business.common.KeyConstant;
import com.lj.business.member.dto.UpdatePersonMember;
import com.lj.business.member.dto.UpdatePmChatBehavior;
import com.lj.business.member.dto.addfriends.UpdateAddFriends;
import com.lj.business.member.dto.chatroom.UpdateChatRoom;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMember;
import com.lj.business.member.dto.gmAssistantShop.UpdateGmAssistantShop;
import com.lj.business.member.dto.pushconfig.UpdateWxPushConfig;
import com.lj.business.member.dto.shopterminal.AddShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPage;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPageReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.PmFlowQcode;
import com.lj.business.member.dto.shopterminal.UpdateShopTerminal;
import com.lj.business.member.dto.shopterminal.UpdateTerminalWxPwd;
import com.lj.business.member.dto.terminalimstatus.UpdateTerminalImStatus;
import com.lj.business.member.emus.Gender;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmChatBehaviorService;
import com.lj.business.member.service.IPmLabelService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.member.service.ITerminalImStatusService;
import com.lj.business.member.service.IWxPushConfigService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImGroupChatInfoDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.dto.WxJobRedPackInfoDto;
import com.lj.business.weixin.dto.friendsjob.AddSendFriendsJob;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfo;
import com.lj.business.weixin.dto.publicaccount.UpdateWxPublicAccount;
import com.lj.business.weixin.dto.smallprogram.UpdateWxSmallProgram;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImCommentInfoService;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.IImGroupChatInfoService;
import com.lj.business.weixin.service.IImLikeInfoService;
import com.lj.business.weixin.service.ISendFriendsJobService;
import com.lj.business.weixin.service.IWxJobRedPackInfoService;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.business.weixin.service.IWxSmallProgramService;
//import com.lj.business.recruit.emus.WeiXinType;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.GroupName;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.RedisCache;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.QrcodeUtils;
import com.lj.oms.utils.UserUtils;

import net.sf.json.JSONArray;
/**
 * 
 * 
 * 类说明：终端终端信息Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年11月02日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/shopTerminal")
public class ShopTerminalController  extends BaseController{
	
	/** 终端终端列表页面 **/
	private static final String PAGE_VIEW_SHOP_TERMINAL_LIST = "modules/member/shopTerminalList";
	/** 终端终端编辑页面 **/
	private static final String PAGE_VIEW_SHOP_TERMINAL_FORM = "modules/member/shopTerminalForm";
	/** 重定向到终端终端列表页面 **/
	private static final String PAGE_VIEW_REDIRECT_SHOP_TERMINAL_LIST = "redirect:" + Global.getAdminPath() + "/member/shopTerminal/";
	
	@Resource
	private  IShopTerminalService shopTerminalService;
	
	@Resource
	public IImChatInfoService imChatInfoService;
	
	@Resource
	private LocalCacheSystemParamsFromCC  localCacheSystemParams;
	
    @Autowired 
	ICommonService commonService;
    @Resource
    private IChatRoomService chatRoomService;
    @Resource
    private IChatRoomMemberService chatRoomMemberService; 
    @Resource
    private IAddFriendsService addFriendsService;
    @Resource
    private IGmAssistantShopService gmAssistantShopService;
    @Resource
    private IPersonMemberService personMemberService;
    @Resource
    private IPmChatBehaviorService pmChatBehaviorService;
    @Resource
    private IPmLabelService pmLabelService;
    @Resource
    private ITerminalImStatusService terminalImStatusService;
    @Resource
    private IWxPushConfigService wxPushConfigService;
    @Resource
    private IImCommentInfoService imCommentInfoService;
    @Resource
    private IImGroupChatInfoService imGroupChatInfoService;
    @Resource
    private IImLikeInfoService imLikeInfoService;
    @Resource
    private ISendFriendsJobService sendFriendsJobService;
    @Resource
    private IWxJobRedPackInfoService wxJobRedPackInfoService;
    @Resource
    private IWxPublicAccountService wxPublicAccountService;
    @Resource
    private IWxSmallProgramService wxSmallProgramService;
    @Resource
    private IImFriendsInfoService imFriendsInfoService;
    @Resource
	private ThreadPoolTaskExecutor taskExecutor;
    @Resource
    RedisCache redisCache;
    
	/**
	 * TODO
	 *
	 * 方法说明：终端终端查询
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findShopTerminalPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	@RequiresPermissions("member:shopTerminal:view")
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindShopTerminalPage findShopTerminalPage){
		try {
			if(pageNo!=null){
				findShopTerminalPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findShopTerminalPage.setLimit(pageSize);
			}
		  
		   findShopTerminalPage.setMerchantNo(UserUtils.getMerchantNo());
//		   findShopTerminalPage.setShopNos(CcUtils.getShopNoList());
		   Page<FindShopTerminalPageReturn> pages=shopTerminalService.findShopTerminalPage(findShopTerminalPage);
		   List<FindShopTerminalPageReturn> list=Lists.newArrayList();
		   list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindShopTerminalPageReturn> page 
			= new com.ape.common.persistence.Page<FindShopTerminalPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findShopTerminalPage", findShopTerminalPage);
		} catch (Exception e) {
			logger.error("终端终端信息获取异常！", e);
		}
		return PAGE_VIEW_SHOP_TERMINAL_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：去到终端终端编辑页面
	 *
	 * @param model
	 * @param findShopTerminal
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	@RequiresPermissions("member:shopTerminal:view")
	@RequestMapping(value="form")
	public String form(Model model,FindShopTerminal findShopTerminal){
		try {
			if(findShopTerminal!=null && findShopTerminal.getCode()!=null){
				if(StringUtils.isNotBlank(findShopTerminal.getCode())){
					FindShopTerminalReturn findShopTerminalReturn= shopTerminalService.findShopTerminal(findShopTerminal);
					model.addAttribute("data", findShopTerminalReturn);
				}else{
					//回显添加失败时的信息
					model.addAttribute("data", findShopTerminal);
				}
			}
		
			model.addAttribute("genders",Gender.values());
		} catch (Exception e) {
			logger.error("终端终端信息获取异常！", e);
		}
		return PAGE_VIEW_SHOP_TERMINAL_FORM;
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：新增终端终端
	 *
	 * @param addShopTerminal
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	@RequiresPermissions("member:shopTerminal:edit")
	@RequestMapping(value="save")
	public String save(AddShopTerminal addShopTerminal,Model model,RedirectAttributes  redirectAttributes){
		try {
			FindShopTerminal findShopTerminal=new FindShopTerminal();
			if(shopTerminalService.hasNoWx(addShopTerminal.getNoWx())>0){
				model.addAttribute("repMsg", "保存终端终端信息失败，微信号'" + addShopTerminal.getNoWx() + "'已存在");
				BeanUtils.copyProperties(addShopTerminal, findShopTerminal);
				return form(model, findShopTerminal);
			}
			addShopTerminal.setMerchantNo(UserUtils.getMerchantNo());
			addShopTerminal.setMerchantName(UserUtils.getMerchantName());
			addShopTerminal.setCreateId(addShopTerminal.getMerchantName());
			shopTerminalService.addShopTerminal(addShopTerminal);
			addMessage(redirectAttributes, "保存终端终端成功");
			
		} catch (Exception e) {
			logger.error("保存终端终端信息错误！", e);
		}
		return PAGE_VIEW_REDIRECT_SHOP_TERMINAL_LIST;
	}

	/**
	 * 
	 *
	 * 方法说明：编辑终端终端
	 *
	 * @param redirectAttributes
	 * @param model
	 * @param updateShopTerminal
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	@RequiresPermissions("member:shopTerminal:edit")
	@RequestMapping(value="edit")
	public String edit(RedirectAttributes redirectAttributes,Model model,UpdateShopTerminal updateShopTerminal){
		try {
			shopTerminalService.updateShopTerminal(updateShopTerminal);
			addMessage(redirectAttributes, "修改终端终端信息成功");
		} catch (TsfaServiceException e) {
			addMessage(redirectAttributes, e.getExceptionInfo());
			logger.error("修改终端终端信息错误！", e);
		} catch (Exception e) {
			addMessage(redirectAttributes, "修改终端终端信息失败！");
			logger.error("修改终端终端信息错误！", e);
		}
	   return PAGE_VIEW_REDIRECT_SHOP_TERMINAL_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据微信号查找终端终端对象,返回其二维码地址
	 *
	 * @param noWx
	 * @return qcord
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月12日
	 *
	 */
	@RequestMapping(value ="getByNoWx" )
	@ResponseBody
	public String getByNoWx(String noWx){
		String qcord="";
		
		FindShopTerminal findShopTerminal=new FindShopTerminal();
		findShopTerminal.setNoWx(noWx);
		List<FindShopTerminalReturn> sts = shopTerminalService.findShopTerminalSelect(findShopTerminal);
		if (sts!=null&&sts.size()>0) {
			qcord=StringUtils.isNotBlank(sts.get(0).getQcord())?sts.get(0).getQcord():"";
		}
		return qcord;
	}
	
	/**
	 * 
	 *
	 * 方法说明：设置终端微信支付密码
	 *
	 * @param updateTerminalWxPwd
	 * @return resultMap
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月27日
	 *
	 */
	@RequiresPermissions("member:shopTerminal:WxPwd:edit")
	@RequestMapping(value ="updateWxPwd" )
	@ResponseBody
	public Map<String,Object> updateWxPwd(UpdateTerminalWxPwd updateTerminalWxPwd){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		try{
			shopTerminalService.updateTerminalWxPwd(updateTerminalWxPwd);
			resultMap.put("state", 1);
			resultMap.put("msg", "设置终端微信支付密码成功!");
		} catch (TsfaServiceException ex) {
			resultMap.put("state", 2);
			resultMap.put("msg", "设置终端微信支付密码失败:"+ex.getExceptionInfo());
			logger.error("设置终端微信支付密码错误！",ex);
		} catch (Exception e) {
			resultMap.put("state", 2);
			resultMap.put("msg", "设置终端微信支付密码失败:"+e);
			logger.error("设置终端微信支付密码错误！",e);
		}
		return resultMap;
	}
	
	/**
	 * 
	 *
	 * 方法说明：向终端下发签到命令
	 *
	 * @param code
	 * @return resultMap
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月29日
	 *
	 */
	@RequiresPermissions("member:shopTerminal:sendSign")
	@RequestMapping(value ="sendSign" )
	@ResponseBody
	public Map<String,Object> sendSign(String code){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		try{
			shopTerminalService.sendTerminalSignCommand(code);
			resultMap.put("state", 1);
			resultMap.put("msg", "向终端下发签到命令成功!");
		} catch (TsfaServiceException ex) {
			resultMap.put("state", 2);
			resultMap.put("msg", "向终端下发签到命令失败:"+ex.getExceptionInfo());
			logger.error("向终端下发签到命令错误！",ex);
		} catch (Exception e) {
			resultMap.put("state", 2);
			resultMap.put("msg", "向终端下发签到命令失败:"+e);
			logger.error("向终端下发签到命令错误！",e);
		}
		return resultMap;
	}
	
	/**
	 * 
	 *
	 * 方法说明：打开或关闭朋友圈上传功能
	 *
	 * @param redirectAttributes
	 * @param code
	 * @param uploadFriendsFlag
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月15日
	 *
	 */
	@RequiresPermissions("member:shopTerminal:uploadFriendsFlag")
	@RequestMapping(value ="updateUploadFriendsFlag" )
	public String updateUploadFriendsFlag(RedirectAttributes redirectAttributes, String code, Integer uploadFriendsFlag){
		String flagStr = CommonConstant.I_YES == uploadFriendsFlag ? "打开" : "关闭";
		try{
			UpdateShopTerminal updateShopTerminal = new UpdateShopTerminal();
			updateShopTerminal.setCode(code);
			updateShopTerminal.setUploadFriendsFlag(uploadFriendsFlag);
			shopTerminalService.updateUploadFriendsFlag(updateShopTerminal);
			addMessage(redirectAttributes, flagStr + "朋友圈上传功能成功");
		} catch (TsfaServiceException ex) {
			addMessage(redirectAttributes, flagStr + "朋友圈上传功能失败:"+ex.getExceptionInfo());
			logger.error("向终端下发签到命令错误！",ex);
		} catch (Exception e) {
			addMessage(redirectAttributes, flagStr + "朋友圈上传功能失败");
			logger.error(flagStr + "朋友圈上传功能错误！",e);
		}
		return PAGE_VIEW_REDIRECT_SHOP_TERMINAL_LIST;
	}
	
	
	/**
	 * 添加中控二维码，输出流
	 * @param redirectAttributes
	 * @param code
	 * @param uploadFriendsFlag
	 * @return
	 */
	@RequestMapping(value ="zkLoginToQcode" )
	public void zkLoginToQcode(HttpServletResponse response){
		try{
			
			String url = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(),GroupName.API_LOGIN.toString(), "zkLoginUrl");
			url = String.format(url, UserUtils.getMerchantNo());
			logger.debug("生成中控二维码地址：{}",url);
			response.setHeader("Content-Type","image/jpeg");
			QrcodeUtils.writeToStream(url, "jpg", response.getOutputStream());
		} catch (IOException ex) {
			logger.error("生成中控二维码错误！",ex);
		}
	}
	
	
	
	/**
	 * 跳转到分流页面
	 */
	@RequiresPermissions("member:shopTerminal:view")
	@RequestMapping(value ="/toAddShopTerminalFlowPage" )
	public String selectShopTerminal(Model model) {
		
		return "modules/member/flowForm";
	}
	
	
	/**
	 * 根据商户号查询终端
	 */
	@ResponseBody
	@RequiresPermissions("member:shopTerminal:view")
	@RequestMapping(value ="/findShopTermianlToFlow" )
    public String findShopTermianlToFlow(Model model,String merchantNo) {
		if(StringUtils.isEmpty(merchantNo)) {
			merchantNo = UserUtils.getMerchantNo();
		}
    	
		List<FindShopTerminalReturn> list = shopTerminalService.findAllShopTerminalByMerchantNo(merchantNo);
		model.addAttribute("list", list);
		JSONArray json = JSONArray.fromObject(list);     
        return json.toString();
    }
	
	/**
	 * 根据商户号查询已分组和参与分流的微信组
	 * @param model
	 * @return
	 */
	@RequiresPermissions("member:shopTerminal:view")
	@RequestMapping(value ="/selectFlowWx" )
	public String selectFlowWx(Model model) {
		String merchantNo = UserUtils.getMerchantNo();
		List<PmFlowQcode> list = shopTerminalService.findFlowWxByMerchantNo(merchantNo);
		model.addAttribute("list", list);
		
		return "modules/member/flowList";
	}
	
	/**
	 * 增加分流二维码组
	 * @param model
	 */
	@ResponseBody
	@RequiresPermissions("member:shopTerminal:edit")
	@RequestMapping(value ="/addFlowWxPm" )
	public void addFlowWxPm(Model model,String pmName,String flowWxNo) {
		try {
			PmFlowQcode pmFlowQcode = new PmFlowQcode();
			
			pmFlowQcode.setNum(0);
			if(flowWxNo != null && !flowWxNo.equals("")) {
				String array[] =flowWxNo.split(",");
				pmFlowQcode.setNum(array.length);
			}
			pmFlowQcode.setCode(GUID.generateByUUID());
			
			//生成二维码图片
			Map<String, String> map = exportFlowOpenCode(UserUtils.getMerchantNo(), pmFlowQcode.getCode());
			
			String qcodeContent = map.get("qcodeCentent");
			
			pmFlowQcode.setFlowQcode(map.get("imageAddress"));
			pmFlowQcode.setFlowWxNo(flowWxNo);
			pmFlowQcode.setQcodeContent(qcodeContent);
			pmFlowQcode.setPmName(pmName);
			pmFlowQcode.setMerchantNo(UserUtils.getMerchantNo());
			shopTerminalService.addFlowWxByMerchantNo(pmFlowQcode);
		}catch(Exception e) {
			logger.error("生成二维码失败", e);
		}
        
	}
	
	
	
	/**
	 * 查询分流二维码组
	 * @param model
	 */
	@RequiresPermissions("member:shopTerminal:view")
	@RequestMapping(value ="/selectFlowWxByCode")
	public String selectFlowWxByCode(Model model,String code) {
		try {
			List<PmFlowQcode> list = shopTerminalService.findFlowWxByCode(code);
			PmFlowQcode pmFlowQcode = null;
			if(list != null && list.size() > 0) {
				pmFlowQcode = list.get(0);
				model.addAttribute("pmFlowQcode", pmFlowQcode);
			}
			return "modules/member/flowForm";
		}catch(Exception e) {
			logger.error("生成二维码失败", e);
			return "modules/member/flowForm";
		}
        
	}
	
	
	/**
	 * 编辑分流二维码组
	 * @param model
	 */
	@ResponseBody
	@RequiresPermissions("member:shopTerminal:edit")
	@RequestMapping(value ="/editFlowWxPm")
	public void deleteFlowWxPm(Model model,String code, String flowWxNo, String pmName) {
		try {
			PmFlowQcode pmFlowQcode = new PmFlowQcode();
			pmFlowQcode.setCode(code);
			pmFlowQcode.setFlowWxNo(flowWxNo);
			pmFlowQcode.setPmName(pmName);
			if(flowWxNo != null && !flowWxNo.equals("")) {
				String array[] =flowWxNo.split(",");
				pmFlowQcode.setNum(array.length);
			}
			shopTerminalService.updateFlowWxByMerchantNo(pmFlowQcode);
		}catch(Exception e) {
			logger.error("生成二维码失败", e);
		}
        
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：生成商户分流二位码
	 * 
	 * @param shopNo
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */

	public Map<String, String> exportFlowOpenCode(String merchantNo, String code) throws UnsupportedEncodingException {
		String fileName = null;

		fileName = "Flow_" + code + ".jpg";
	    //获取二维码链接前缀
		String codePre = localCacheSystemParams.getSystemParam(SystemAliasName.ms.name(), "upload", "uploadUrl");
		String readUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.name(), "omsfile", "uploadUrl");
		String uploadPath = localCacheSystemParams.getSystemParam(SystemAliasName.ms.name(), "upload", "uploadPath");
		try {
			Map<String, String> map = new HashMap<String, String>();
			File file = new File(uploadPath+fileName);
			String url =codePre+"/api/h5/selectZKQcode.do?merchantNo="+merchantNo+"&code="+code;
			QrcodeUtils.writeToFile(url, "jpg", file);
			map.put("imageAddress", readUrl+fileName);  //二维码图片地址
			map.put("qcodeCentent", url);  //二维码图片内容
			return map;
		} catch (Exception e) {
			logger.error("生成二维码失败", e);
			return null;
		}
	}
	
	/**
	 * 删除终端
	 * @param model
	 */
	@RequiresPermissions("member:shopTerminal:edit")
	@RequestMapping(value ="/delete")
	public String delete(Model model,RedirectAttributes redirectAttributes,String code, String noWx) {
		try {
			shopTerminalService.delete(code);
			addMessage(redirectAttributes, "删除终端成功!");
			String merchantNo = UserUtils.getUser().getCompany().getId();
			//子线程处理耗时操作
			deleteRelative(merchantNo,noWx);
		}catch(Exception e) {
			logger.error("删除终端失败!", e);
			addMessage(redirectAttributes, "删除终端失败!");
		}
		return PAGE_VIEW_REDIRECT_SHOP_TERMINAL_LIST;
	}

	/**
	 *@Desc 创建两个个子线程删除其他关联数据
	 *@return void
	 *@author 贾光朝
	 * @param noWx 
	 * @param merchantNo 
	 *@createDate 2019年5月6日下午4:02:24
	 */
	private void deleteRelative(String merchantNo, String noWx) {
		//删除member库相关数据
		taskExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				//删除add_friends
				UpdateAddFriends updateAddFriends = new UpdateAddFriends();
				updateAddFriends.setMerchantNo(merchantNo);
				updateAddFriends.setNoWxGm(noWx);
				addFriendsService.delete(updateAddFriends);
				//删除chat_room
				UpdateChatRoom updateChatRoom = new UpdateChatRoom();
				updateChatRoom.setMerchantNo(merchantNo);
				updateChatRoom.setNoWxZk(noWx);
				updateChatRoom.setStatus(2);
				chatRoomService.delete(updateChatRoom);
				//删除chat_room_member
				UpdateChatRoomMember updateChatRoomMember = new UpdateChatRoomMember();
				updateChatRoomMember.setMerchantNo(merchantNo);
				updateChatRoomMember.setNoWxZk(noWx);
				updateChatRoomMember.setStatus(2);
				chatRoomMemberService.delete(updateChatRoomMember);
				//删除gm_assistant_shop
				UpdateGmAssistantShop updateGmAssistantShop = new UpdateGmAssistantShop();
				updateGmAssistantShop.setMerchantNo(merchantNo);
				updateGmAssistantShop.setNoWx(noWx);
				gmAssistantShopService.delete(updateGmAssistantShop);
				//删除person_member
				UpdatePersonMember updatePersonMember = new UpdatePersonMember();
				updatePersonMember.setMerchantNo(merchantNo);
				updatePersonMember.setShopWx(noWx);
				personMemberService.delete(updatePersonMember);
				//删除pm_chat_behavior
				UpdatePmChatBehavior updatePmChatBehavior = new UpdatePmChatBehavior();
				updatePmChatBehavior.setNoWxGm(noWx);
				pmChatBehaviorService.delete(updatePmChatBehavior);
				//删除terminal_im_status
				UpdateTerminalImStatus updateTerminalImStatus = new UpdateTerminalImStatus();
				updateTerminalImStatus.setMerchantNo(merchantNo);
				updateTerminalImStatus.setNoWx(noWx);
				terminalImStatusService.delete(updateTerminalImStatus);
				//删除terminal_login_log
				terminalImStatusService.deleteLoginLog(merchantNo,noWx);
				//删除wx_push_config
				UpdateWxPushConfig updateWxPushConfig = new UpdateWxPushConfig();
				updateWxPushConfig.setMerchantNo(merchantNo);
				updateWxPushConfig.setNoWx(noWx);
				wxPushConfigService.delete(updateWxPushConfig);
				
			}
		});
		
		//删除weixin库相关数据
		taskExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				//删除im_chat_info
				UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
				updateImChatInfo.setMerchantNo(merchantNo);
				updateImChatInfo.setNoWxGm(noWx);
				imChatInfoService.delete(updateImChatInfo);
				//删除im_chat_info_temp
				imChatInfoService.deleteTemp(merchantNo,noWx);
				//刪除im_comment_info
				ImCommentInfoDto imCommentInfoDto = new ImCommentInfoDto();
				imCommentInfoDto.setMerchantNo(merchantNo);
				imCommentInfoDto.setNoWxShop(noWx);
				imCommentInfoService.delete(imCommentInfoDto);
				//刪除im_friends_info
				imFriendsInfoService.delete(merchantNo,noWx);
				//刪除im_group_chat_info
				ImGroupChatInfoDto imGroupChatInfoDto = new ImGroupChatInfoDto();
				imGroupChatInfoDto.setMerchantNo(merchantNo);
				imGroupChatInfoDto.setNoWxGm(noWx);
				imGroupChatInfoService.delete(imGroupChatInfoDto);
				//刪除im_like_info
				ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
				imLikeInfoDto.setMerchantNo(merchantNo);
				imLikeInfoDto.setNoWxShop(noWx);
				imLikeInfoService.delete(imLikeInfoDto);
				//刪除send_friends_job
				AddSendFriendsJob addSendFriendsJob = new AddSendFriendsJob();
				addSendFriendsJob.setMerchantNo(merchantNo);
				addSendFriendsJob.setNoWxs(noWx);
				sendFriendsJobService.delete(addSendFriendsJob);
				//刪除wx_public_account
				UpdateWxPublicAccount updateWxPublicAccount = new UpdateWxPublicAccount();
				updateWxPublicAccount.setMerchantNo(merchantNo);
				updateWxPublicAccount.setNoWxZk(noWx);
				wxPublicAccountService.delete(updateWxPublicAccount);
				//刪除wx_small_program
				UpdateWxSmallProgram updateWxSmallProgram = new UpdateWxSmallProgram();
				updateWxSmallProgram.setMerchantNo(merchantNo);
				updateWxSmallProgram.setNoWxZk(noWx);
				wxSmallProgramService.delete(updateWxSmallProgram);
				//刪除wx_job_redpack_info
				WxJobRedPackInfoDto wxJobRedPackInfoDto = new WxJobRedPackInfoDto();
				wxJobRedPackInfoDto.setMerchantNo(merchantNo);
				wxJobRedPackInfoDto.setWxNoShop(noWx);
				wxJobRedPackInfoService.delete(wxJobRedPackInfoDto);
			}
		});
	}

	/**
	 * 设置朋友圈图片自动上传
	 * @param model
	 * @param val
	 * @param noWx
	 * @return
	 */
	@RequiresPermissions("member:shopTerminal:edit")
	@RequestMapping(value ="/setAutoDownFriends")
	public String setAutoDownFriends(String val, String noWx,RedirectAttributes redirectAttributes) {
		try {
			if(StringUtils.isEmpty(noWx) || StringUtils.isEmpty(val)) {
				addMessage(redirectAttributes, "参数错误!");
				return PAGE_VIEW_REDIRECT_SHOP_TERMINAL_LIST;
			}
			redisCache.set(KeyConstant.ZK_FRIENDS_AUTO+noWx, val);
			addMessage(redirectAttributes, "设置成功!");
		}catch(Exception e) {
			logger.error("设置朋友圈图片自动上传失败!", e);
			addMessage(redirectAttributes, "设置朋友圈图片自动上传失败!");
		}
		return PAGE_VIEW_REDIRECT_SHOP_TERMINAL_LIST;
	}
}
