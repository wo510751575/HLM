package com.lj.oms.im;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaException;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dto.FriendsImageMaterialDto;
import com.lj.business.cm.emus.FriendsMaterialType;
import com.lj.business.cm.emus.MaterialType;
import com.lj.business.cm.service.IFriendsImageMaterialService;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.dto.FindImFriendsInfoPage;
import com.lj.business.weixin.dto.FindSendFriendsJobPage;
import com.lj.business.weixin.dto.FindSendFriendsJobPageReturn;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.friendsjob.AddSendFriendsJob;
import com.lj.business.weixin.emus.FriendsSendStatus;
import com.lj.business.weixin.emus.SendFriendsJobStatus;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.ISendFriendsJobService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.oms.dto.CommonRepsonseDto;
import com.lj.oms.entity.sys.User;
import com.lj.oms.utils.CcUtils;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：发送朋友圈任务
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 * 
 *         CreateDate: 2017年12月23日
 */
@Controller
@RequestMapping(value = "${adminPath}/friendsjob")
public class FriendsJobController  extends BaseController{

    @Autowired
    private IShopTerminalService shopTerminalService;
    
    @Autowired 
    private ISendFriendsJobService sendFriendsJobService;
    
    @Autowired 
    private IImFriendsInfoService imFriendsInfoService;
    
    @Autowired 
    private IImFriendsFacade imFriendsFacade;
    
    @Autowired
	private IFriendsImageMaterialService friendsImageMaterialService;//朋友圈图片素材服务
    @Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
    
    private static final String DEFAULT_DELAY_TIMES = "60";//发送朋友圈默认延迟时间
    
    private static final String LIST="modules/im/moments";
    private static final String MOMENTS_VIEW="modules/im/momentsView";
    
    /**
     * 
     *
     * 方法说明：分页查询
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @return
     *
     * @author zhangting CreateDate: 2017年11月28日
     *
     */
    @RequestMapping(value = {"sendFriends", ""})
    public String list(Model model, Integer pageNo, Integer pageSize,
            FindShopTidFromWeb findShopTidFromWeb) {
        try {
            User user = UserUtils.getUser();
            String userId = user.getId();
            String companyId = UserUtils.getMerchantNo();
            model.addAttribute("assistantNo", userId);
            model.addAttribute("merchantNo", companyId);
            model.addAttribute("merchantName", user.getCompany().getName());
            
            String delayTimes = localCacheSystemParams.getSystemParam(SystemAliasName.weixin.name(), "min_delay", "friends_min_delay");
            if (org.apache.commons.lang.StringUtils.isBlank(delayTimes)) {
                delayTimes = DEFAULT_DELAY_TIMES;
            }
            model.addAttribute("delayTimes", delayTimes);
        } catch (Exception e) {
            logger.error("查询首页信息错误：" + e);
        }
        return "modules/im/sendFriendsList";
    }

    /**
     * 
     *
     * 方法说明：根据导购助手编号查询其管理的终端列表
     *
     * @param assistantNo
     * @return
     *
     * @author 许新龙 CreateDate: 2017年12月23日
     *
     */
    @RequestMapping(value = "findShopTerminalList.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<FindShopTidFromWebReturn> findShopTerminalList(
            FindShopTidFromWeb findShopTidFromWeb) {
        try {
        	findShopTidFromWeb.setMerchantNo(UserUtils.getMerchantNo());
        	findShopTidFromWeb.setAssistantNo(UserUtils.getUser().getId());
        	findShopTidFromWeb.setQueryOnlineBool(Boolean.TRUE); // 查询终端是否在线
            List<FindShopTidFromWebReturn> findShopTerminalFromWeb =
                    shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
            return findShopTerminalFromWeb;
        } catch (Exception e) {
            logger.error("查询导购助手终端终端列表错误：" + e);
        }
        return new ArrayList<>(0);
    }

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
            logger.error(e.getMessage());
            resultMap.put("result", 0);
            resultMap.put("msg", e.getExceptionInfo());
        } catch (Exception e) {
            logger.error("添加朋友圈任务失败-{}", e);
            logger.error(e.getMessage());
            resultMap.put("result", 0);
            resultMap.put("msg", "系统异常，请稍后重试");
        }

        return resultMap;
    }

    /**
     * 
     *
     * 方法说明：分页查询朋友圈图片素材
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param title	主题
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2017年12月25日
     *
     */
    @RequestMapping(value = "findImageMaterial.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FriendsImageMaterialDto> findImageMaterial(Model model,Integer pageNo,Integer pageSize, String title) {
    	FriendsImageMaterialDto friendsImageMaterial = new FriendsImageMaterialDto();
    	friendsImageMaterial.setMerchantNo(UserUtils.getMerchantNo());
    	friendsImageMaterial.setDeleteFlag(0);
    	friendsImageMaterial.setConditionStr(title);
		Page<FriendsImageMaterialDto> retPage = null;
		try {
			if(pageNo!=null){
				friendsImageMaterial.setStart((pageNo-1)*pageSize);//起始
			}
			if(pageSize!=null){
				friendsImageMaterial.setLimit(pageSize);//尺寸
			}
			retPage = friendsImageMaterialService.findFriendsImageMaterialPage(friendsImageMaterial);
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_FIND_ERROR,"查询朋友圈图片素材表信息错误！",e);
		}
		return retPage;
	}
    
    /**
     * OMS-朋友圈
     * @param redirectAttributes
     * @param findSendFriendsJobPage
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     *
     * @author 彭俊霖
     * @CreateDate 2018年6月21日下午2:53:57
     */
    @RequiresPermissions("im:sendFriendsjob:view")
	@RequestMapping(value={"moments"})
	public String moments(RedirectAttributes redirectAttributes,FindSendFriendsJobPage findSendFriendsJobPage,Integer pageNo,Integer pageSize, Model model){
		try {
			if(pageNo !=null){
				findSendFriendsJobPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findSendFriendsJobPage.setLimit(pageSize);
			}
			
			findSendFriendsJobPage.setMerchantNo(UserUtils.getMerchantNo());
			
			Page<FindSendFriendsJobPageReturn> pages=sendFriendsJobService.findSendFriendsJobPage(findSendFriendsJobPage);
			List<FindSendFriendsJobPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			
			com.ape.common.persistence.Page<FindSendFriendsJobPageReturn> page	
			=new com.ape.common.persistence.Page<FindSendFriendsJobPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			
			model.addAttribute("page", page);
			model.addAttribute("findSendFriendsJobPage", findSendFriendsJobPage);
			
			model.addAttribute("MaterialType", MaterialType.values());
			model.addAttribute("FriendsMaterialType", FriendsMaterialType.values());
			model.addAttribute("SendFriendsJobStatus", SendFriendsJobStatus.values());
			
		} catch (Exception e) {
			addMessage(redirectAttributes, "加载数据失败,系统出现异常！");
			logger.error(e.getMessage(),e);
		}
		return LIST;
    }
    
    /**
     * 查看朋友圈详情
     * @param jobCode
     * @param model
     * @return
     *
     * @author 彭俊霖
     * @CreateDate 2018年6月22日上午9:08:53
     */
    @RequiresPermissions("im:sendFriendsjob:view")
	@RequestMapping(value = "momentsView")
	public String momentsView(String jobCode, Model model) {
		try {
			FindImFriendsInfoPage findImFriendsInfoPage=new FindImFriendsInfoPage();
			findImFriendsInfoPage.setMerchantNo(UserUtils.getMerchantNo());
			findImFriendsInfoPage.setJobCode(jobCode);
			List<ImFriendsInfoDto> data = imFriendsInfoService.findImFriendsInfos(findImFriendsInfoPage);
			model.addAttribute("data", data);
			model.addAttribute("FriendsSendStatus", FriendsSendStatus.values());
		} catch (Exception e) {
			 logger.error("查看朋友圈详情异常！", e);
		}
		return MOMENTS_VIEW;
	}
    
    /**
     * 重发朋友圈
     * @param friendsCode
     * @return
     *
     * @author 彭俊霖
     * @CreateDate 2018年6月22日下午2:45:47
     */
    @RequiresPermissions("im:sendFriendsjob:view")
    @RequestMapping(value = "resend")
    @ResponseBody
    public CommonRepsonseDto resend(String friendsCode) {
    	CommonRepsonseDto commonRepsonseDto = null;
		try {
			imFriendsFacade.resendFriendsInfo(friendsCode);
			commonRepsonseDto = CommonRepsonseDto.generateSuccessResponse("重发朋友圈成功!");
		}catch (Exception e) {
			logger.error("重发朋友圈失败！", e);
			commonRepsonseDto = CommonRepsonseDto.generateFailureResponse("重发朋友圈失败！");
		}
		return commonRepsonseDto;
    }
}
