package com.lj.oms.weixin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.emus.MaterialType;
import com.lj.business.common.CommonConstant;
import com.lj.business.weixin.dto.FindMerchantSendFriendsJobPage;
import com.lj.business.weixin.dto.MerchantSendFriendsJobDto;
import com.lj.business.weixin.emus.SendFriendsJobStatus;
import com.lj.business.weixin.service.IMerchantSendFriendsJobService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
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
@RequestMapping(value = "${adminPath}/weixin/merchantSendFriendsJob")
public class MerchantSendFriendsJobController  extends BaseController{

    @Autowired
    private IMerchantSendFriendsJobService merchantSendFriendsJobService;
    @Autowired
    private OfficeService officeService;
    @Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
    /** 列表页面 **/
	private final static String PAGE_VIEW_LINE_LIST = "modules/weixin/merchantSendFriendsJob/list";
	/** 编辑页面 **/
	private final static String PAGE_VIEW_LINE_FORM = "modules/weixin/merchantSendFriendsJob/form";
	/** 获取商户列表 **/
	private final static String PAGE_VIEW_GET_MERCHANT = "modules/weixin/merchantSendFriendsJob/selectMerchant";
	/** 重定向到列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_LINE = "redirect:" + Global.getAdminPath() + "/weixin/merchantSendFriendsJob";
    
    @RequestMapping(value = {"list", ""})
    public String list(Model model, Integer pageNo, Integer pageSize,FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage,String startTime, String endTime) {
        try {
        	
        	if(pageNo !=null){
        		findMerchantSendFriendsJobPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findMerchantSendFriendsJobPage.setLimit(pageSize);
			}
			
			Page<MerchantSendFriendsJobDto> pages=merchantSendFriendsJobService.findMerchantSendFriendsJobPage(findMerchantSendFriendsJobPage);
			List<MerchantSendFriendsJobDto> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			
			com.ape.common.persistence.Page<MerchantSendFriendsJobDto> page	
			=new com.ape.common.persistence.Page<MerchantSendFriendsJobDto>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			
			model.addAttribute("page", page);
			model.addAttribute("findPage", findMerchantSendFriendsJobPage);
			model.addAttribute("materialType", MaterialType.values());
			
        } catch (Exception e) {
            logger.error("查询首页信息错误：" + e);
        }
        return PAGE_VIEW_LINE_LIST;
    }
    
    @RequestMapping(value = "form")
	public String form(Model model, MerchantSendFriendsJobDto merchantSendFriendsJobDto) {
		try {
			if (merchantSendFriendsJobDto != null && merchantSendFriendsJobDto.getCode() != null) {
				MerchantSendFriendsJobDto data = merchantSendFriendsJobService.findMerchantSendFriendsJob(merchantSendFriendsJobDto);
				model.addAttribute("data", data);
			}
			model.addAttribute("materialType", MaterialType.values());
			List<Office> merchants= officeService.findMerchants();
			model.addAttribute("merchants", merchants);
		} catch (Exception e) {
			logger.error("查询发送朋友圈任务错误！", e);
		}
		return PAGE_VIEW_LINE_FORM;

	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, MerchantSendFriendsJobDto merchantSendFriendsJobDto) {
		try {
			merchantSendFriendsJobDto.setCreateId(UserUtils.getUser().getId());
			merchantSendFriendsJobDto.setCreateName(UserUtils.getUser().getName());
			merchantSendFriendsJobDto.setJobState(SendFriendsJobStatus.INIT.getCode());
			merchantSendFriendsJobDto.setCreateUserLevel(String.valueOf(CommonConstant.I_YES));
			if(StringUtils.isNotEmpty(merchantSendFriendsJobDto.getLinkUrl()) ) {
				if(merchantSendFriendsJobDto.getLinkUrl().startsWith(",")) {
					merchantSendFriendsJobDto.setLinkUrl(merchantSendFriendsJobDto.getLinkUrl().replaceFirst(",", ""));
				}else if(merchantSendFriendsJobDto.getLinkUrl().endsWith(",")) {
					merchantSendFriendsJobDto.setLinkUrl(merchantSendFriendsJobDto.getLinkUrl().substring(0, merchantSendFriendsJobDto.getLinkUrl().length()-1));
				}
				
			}
			merchantSendFriendsJobService.addMerchantSendFriendsJob(merchantSendFriendsJobDto);
			addMessage(redirectAttributes, "保存成功");
		}catch (TsfaServiceException e) {
			logger.error("发送朋友圈任务信息错误！", e);
			addMessage(redirectAttributes,e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("发送朋友圈任务信息错误！", e);
		}
		return PAGE_VIEW_REDIRECT_LINE;

	}
	
	@RequestMapping(value = "getMerchant")
	public String getMerchant(Model model, RedirectAttributes redirectAttributes) {
		try {
			List<Office> merchants= officeService.findMerchants();
			model.addAttribute("merchants", merchants);
		} catch (Exception e) {
			logger.error("获取商户列表错误！", e);
		}
		return PAGE_VIEW_GET_MERCHANT;

	}
	
}
