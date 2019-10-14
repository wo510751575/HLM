package com.lj.oms.member;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.MemLineDto;
import com.lj.business.member.service.IMemLineService;

/**
 * 
 * 
 * 类说明：行业信息
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月4日
 */
@Controller
@RequestMapping(value = "${adminPath}/baseConfig/line")
public class LineController  extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(LineController.class);
	
	/** 行业列表页面 **/
	private final static String PAGE_VIEW_LINE_LIST = "modules/baseConfig/lineList";
	/** 行业编辑页面 **/
	private final static String PAGE_VIEW_LINE_FORM = "modules/baseConfig/lineForm";
	/** 重定向到行业列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_LINE = "redirect:" + Global.getAdminPath() + "/baseConfig/line";

	@Resource
	private IMemLineService memLineService;

	/**
	 * 
	 *
	 * 方法说明：查询行业列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param memLineDto
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月4日
	 *
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, MemLineDto memLineDto) {
		try {
			if (pageNo != null) {
				memLineDto.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				memLineDto.setLimit(pageSize);
			}
			Page<MemLineDto> pages = memLineService.findMemLinePage(memLineDto);
			List<MemLineDto> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<MemLineDto> page = new com.ape.common.persistence.Page<MemLineDto>(pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findMemLinePage", memLineDto);
		} catch (Exception e) {
			logger.error("查新行业信息错误！", e);
		}
		return PAGE_VIEW_LINE_LIST;
	}

	/**
	 * 
	 *
	 * 方法说明：去到行业编辑页面
	 *
	 * @param model
	 * @param memLineDto
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月4日
	 *
	 */
	@RequestMapping(value = "form")
	public String form(Model model, MemLineDto memLineDto) {
		if (memLineDto != null && memLineDto.getCode() != null) {
			try {
				MemLineDto lineDto = memLineService.findMemLine(memLineDto);
				model.addAttribute("data", lineDto);
			} catch (Exception e) {
				logger.error("查新行业信息错误！", e);
			}
		}
		return PAGE_VIEW_LINE_FORM;

	}

	/**
	 * 
	 *
	 * 方法说明：保存行业信息
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param memLineDto
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月4日
	 *
	 */
	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, MemLineDto memLineDto) {
		try {
			String name = memLineDto.getName();
			MemLineDto dto = memLineService.selectByName(memLineDto);
			if (dto == null) {
				memLineService.addMemLine(memLineDto);
				addMessage(redirectAttributes, "保存行业'" + memLineDto.getName() + "'成功");
			} else if (dto.getName().equals(name)) {
				addMessage(redirectAttributes, "你添加的行业已存在,请重新添加其他行业信息！");
			}
		} catch (Exception e) {
			logger.error("保存行业信息错误！", e);
		}
		return PAGE_VIEW_REDIRECT_LINE;

	}

	/**
	 * 
	 *
	 * 方法说明：编辑行业信息
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param memLineDto
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月4日
	 *
	 */
	@RequestMapping(value = "edit")
	public String edit(Model model, RedirectAttributes redirectAttributes, MemLineDto memLineDto) {
		try {
			memLineService.updateMemLine(memLineDto);
			addMessage(redirectAttributes, "修改行业'" + memLineDto.getName() + "'成功");
		} catch (Exception e) {
			logger.error("修改行业信息错误！", e);
		}
		return PAGE_VIEW_REDIRECT_LINE;
	}
}
