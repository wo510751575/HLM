package com.lj.oms.tmall;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindTmallBonusRecordPage;
import com.lj.business.member.dto.TmallBonusRecordDto;
import com.lj.business.member.emus.BonusStatus;
import com.lj.business.member.service.ITmallBonusRecordService;
import com.lj.business.member.service.ITmallOrderService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：天猫订单红包发送记录Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate: 2019年2月20日
 */
@Controller
@RequestMapping(value = "${adminPath}/tmall/bonusRecord")
public class TmallBonusRecordController extends BaseController {
	/** 红包配置列表页面 **/
	private final static String PAGE_VIEW_BONUS_RECORD_LIST = "modules/tmall/bonusRecordList";
	/**   重定向到红包配置列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_BONUS_RECORD = "redirect:"+Global.getAdminPath()+ "/tmall/bonusRecord";
	
	
	@Resource
	private ITmallBonusRecordService bonusRecordService;
	@Resource
	private ITmallOrderService tmallOrderService;
	
	/**
	 * 
	 *
	 * 方法说明：天猫订单红包记录列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param dtoPageDto
	 * @return 服务提供分页，转换OMS分页列表
	 *
	 * @author 刘红艳 CreateDate: 2019年2月20日
	 *
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, TmallBonusRecordDto tmallBonusRecordDto, String  returnMessage,
			RedirectAttributes redirectAttributes ) {
		try {
			FindTmallBonusRecordPage param = new FindTmallBonusRecordPage();
			param.setParam(tmallBonusRecordDto);
			tmallBonusRecordDto.setMerchantNo(UserUtils.getMerchantNo());
			if (pageNo != null) {
				param.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				param.setLimit(pageSize);
			}
			Page<TmallBonusRecordDto> pages = bonusRecordService.findTmallBonusRecordPage(param);
			List<TmallBonusRecordDto> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<TmallBonusRecordDto> page = new com.ape.common.persistence.Page<TmallBonusRecordDto>(
					pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("tmallBonusRecordDto", tmallBonusRecordDto);
			// 状态
			model.addAttribute("bonusStatus", BonusStatus.values());
		} catch (Exception e) {
			logger.error("获取订单红包记录数据错误", e);
		}
		return PAGE_VIEW_BONUS_RECORD_LIST;
	}

	
	
	/**
	 *  重发红包。
	 * @param model
	 * @param redirectAttributes
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="resend")
	public String resend(Model model,RedirectAttributes redirectAttributes,TmallBonusRecordDto dto){
		try {
			//todo 发红包的逻辑 
			tmallOrderService.repeatRedPack(dto.getCode());
			addMessage(redirectAttributes, "重发红包成功");
		}catch (TsfaServiceException e) {
			logger.debug("重发红包错误",e);
			addMessage(redirectAttributes, e.getExceptionInfo());
		}catch (Exception e) {
			logger.debug("重发红包错误",e);
		}	
		return PAGE_VIEW_REDIRECT_BONUS_RECORD;
		
	}
}
