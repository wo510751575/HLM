package com.lj.oms.tmall;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.FindTmallBonusConfigPage;
import com.lj.business.member.dto.TmallBonusConfigDto;
import com.lj.business.member.service.ITmallBonusConfigService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 
 * 类说明：天猫订单红包配置Controller
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
@RequestMapping(value = "${adminPath}/tmall/bonuscfg")
public class TmallBonusConfigController extends BaseController {
	/** 红包配置列表页面 **/
	private final static String PAGE_VIEW_BONUS_LIST = "modules/tmall/bonuscfgList";
	/** 红包配置新增页面 **/
	private final static String PAGE_VIEW_BONUS_FORM = "modules/tmall/bonuscfgForm";
	/**   重定向到红包配置列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_BONUS = "redirect:"+Global.getAdminPath()+ "/tmall/bonuscfg";
	
	@Resource
	private ITmallBonusConfigService bonusConfigService;

	
	/**
	 * 
	 *
	 * 方法说明：天猫订单红包配置列表
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
	public String list(Model model, Integer pageNo, Integer pageSize, TmallBonusConfigDto tmallBonusConfigDto, String  returnMessage,
			RedirectAttributes redirectAttributes ) {
		try {
			tmallBonusConfigDto.setOrdAmtMin(toLong(tmallBonusConfigDto.getOrdAmtMinDec()));
			tmallBonusConfigDto.setOrdAmtMax(toLong(tmallBonusConfigDto.getOrdAmtMaxDec()));
			tmallBonusConfigDto.setBonuxMax(toLong(tmallBonusConfigDto.getBonuxMaxDec()));
			tmallBonusConfigDto.setBonuxMin(toLong(tmallBonusConfigDto.getBonuxMinDec()));
			
			FindTmallBonusConfigPage param = new FindTmallBonusConfigPage();
			param.setParam(tmallBonusConfigDto);
			tmallBonusConfigDto.setMerchantNo(UserUtils.getMerchantNo());
			if (pageNo != null) {
				param.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				param.setLimit(pageSize);
			}
			Page<TmallBonusConfigDto> pages = bonusConfigService.findTmallBonusConfigPage(param);
			List<TmallBonusConfigDto> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<TmallBonusConfigDto> page = new com.ape.common.persistence.Page<TmallBonusConfigDto>(
					pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("tmallBonusConfigDto", tmallBonusConfigDto);
		} catch (Exception e) {
			logger.error("获取订单红包配置数据错误", e);
		}
		return PAGE_VIEW_BONUS_LIST;
	}

	
	/**
	 * 
	 *
	 * 方法说明：form页面展现
	 *
	 * @param model
	 * @param dto
	 * @return 编辑页面数据
	 *
	 * @author 刘红艳 CreateDate: 2019年2月20日
	 *
	 */
	@RequestMapping(value = "form")
	public String Form(Model model,TmallBonusConfigDto dto){
		try {
			if(dto !=null && dto.getCode()!= null){
				TmallBonusConfigDto data =	bonusConfigService.findTmallBonusConfig(dto);
				model.addAttribute("data", data);		
			}
		} catch (Exception e) {
			logger.error("查询订单红包配置数据错误",e);
		}
	
		return PAGE_VIEW_BONUS_FORM;
	}
	
	/**
	 * 
	 *
	 * 方法说明：订单红包配置保存
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param dto
	 * @return
	 *
	 * @author 刘红艳 CreateDate: 2019年2月20日
	 *
	 */
	@RequestMapping(value="save")
	public String save(Model model,RedirectAttributes redirectAttributes,TmallBonusConfigDto dto ){
		try {
			dto.setMerchantNo(UserUtils.getMerchantNo());
			dto.setCreateId(UserUtils.getUser().getName());
			
			dto.setOrdAmtMin(toLong(dto.getOrdAmtMinDec()));
			dto.setOrdAmtMax(toLong(dto.getOrdAmtMaxDec()));
			dto.setBonuxMax(toLong(dto.getBonuxMaxDec()));
			dto.setBonuxMin(toLong(dto.getBonuxMinDec()));
			
			bonusConfigService.addTmallBonusConfig(dto);
			addMessage(redirectAttributes, "保存订单红包配置成功");
		} catch (Exception e) {
			logger.error("保存订单红包配置错误",e);
		}
		return PAGE_VIEW_REDIRECT_BONUS;
		
	}
	
	/**
	 * BigDecimal 保留两位小数且*100的整数
	 * @param d
	 * @return
	 */
	private Long toLong(BigDecimal d) {
		if(d!=null) {
			d=d.setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			return d.longValue();
		}
		return null;
	}
	
	public static void main(String[] args) {
		BigDecimal d=new BigDecimal("111.153");
		d=d.setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		System.out.println(d.longValue());
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：订单红包配置编辑
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param dto
	 * @return
	 *
	 * @author 刘红艳 CreateDate: 2019年2月20日
	 *
	 */
	@RequestMapping(value="edit")
	public String edit(Model model,RedirectAttributes redirectAttributes,TmallBonusConfigDto dto){
		try {
			dto.setOrdAmtMin(toLong(dto.getOrdAmtMinDec()));
			dto.setOrdAmtMax(toLong(dto.getOrdAmtMaxDec()));
			dto.setBonuxMax(toLong(dto.getBonuxMaxDec()));
			dto.setBonuxMin(toLong(dto.getBonuxMinDec()));
			
			bonusConfigService.updateTmallBonusConfig(dto);
			addMessage(redirectAttributes, "修改订单红包配置成功");
		} catch (Exception e) {
			logger.debug("修改订单红包配置错误",e);
		}	
		return PAGE_VIEW_REDIRECT_BONUS;
		
	}
	
	/**
	 *  删除订单红包配置。
	 * @param model
	 * @param redirectAttributes
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="remove")
	public String remove(Model model,RedirectAttributes redirectAttributes,TmallBonusConfigDto dto){
		try {
			bonusConfigService.deleteTmallBonusConfig(dto);
			addMessage(redirectAttributes, "删除订单红包配置成功");
		} catch (Exception e) {
			logger.debug("修改订单红包配置错误",e);
		}	
		return PAGE_VIEW_REDIRECT_BONUS;
		
	}
}
