package com.lj.oms.member;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.StringUtils;
import com.lj.business.member.dto.FindGmLabelPage;
import com.lj.business.member.dto.GmLabelDto;
import com.lj.business.member.service.IGmLabelService;
import com.lj.oms.common.BaseController;
import com.lj.oms.dto.CommonRepsonseDto;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * @ClassName: GmLabelController
 * @Description:个人标签
 * @author: 段志鹏
 * @date: 2019-06-25 10:07
 * 
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved.
 *             注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value = "${adminPath}/member/gmLabel")
public class GmLabelController extends BaseController {

	@Resource
	private IGmLabelService gmLabelService;
	/** 设置好友数列表页面 **/
	private final static String PAGE_VIEW_LINE_LIST = "modules/member/gmLabel/list";
	/** 设置好友数编辑页面 **/
	private final static String PAGE_VIEW_LINE_FORM = "modules/member/gmLabel/form";
	/** 重定向到设置好友数列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_LINE = "redirect:" + Global.getAdminPath() + "/member/gmLabel";

	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, GmLabelDto param) {
		try {
			FindGmLabelPage findGmLabelPage = new FindGmLabelPage();

			if (pageNo != null) {
				findGmLabelPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findGmLabelPage.setLimit(pageSize);
			}
			if (null == param) {
				param = new GmLabelDto();
			}
			param.setMerchantNo(UserUtils.getMerchantNo());
			param.setMemberNoGm(UserUtils.getUser().getId());
			findGmLabelPage.setParam(param);

			Page<GmLabelDto> pageDto = gmLabelService.findGmLabelPage(findGmLabelPage);
			ArrayList<GmLabelDto> basePageReturns = Lists.newArrayList();
			basePageReturns.addAll(pageDto.getRows());
			com.ape.common.persistence.Page<GmLabelDto> page = new com.ape.common.persistence.Page<GmLabelDto>(
					pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), basePageReturns);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findGmLabelPage", findGmLabelPage);
		} catch (Exception e) {
			logger.error("获取个人标签信息错误！", e);
		}
		return PAGE_VIEW_LINE_LIST;

	}

	@RequestMapping(value = "form")
	public String form(GmLabelDto gmLabelDto, Model model) {
		try {
			if (gmLabelDto != null && StringUtils.isNotEmpty(gmLabelDto.getCode())) {
				GmLabelDto returnDto = gmLabelService.findGmLabel(gmLabelDto);
				model.addAttribute("data", returnDto);
			}
		} catch (Exception e) {
			logger.error("获取个人标签信息错误！", e);

		}
		return PAGE_VIEW_LINE_FORM;

	}

	@RequestMapping(value = "save")
	public String save(GmLabelDto gmLabelDto, Model model, RedirectAttributes redirectAttributes) {
		try {

			gmLabelDto.setCreateId(UserUtils.getUser().getName());
			gmLabelDto.setMerchantNo(UserUtils.getMerchantNo());
			gmLabelDto.setMemberNoGm(UserUtils.getUser().getId());
			gmLabelDto.setMemberNameGm(UserUtils.getUser().getName());
			gmLabelService.addGmLabel(gmLabelDto);
			addMessage(redirectAttributes, "保存个人标签'" + gmLabelDto.getLabelName() + "'成功");
		} catch (Exception e) {
			logger.error("保存个人标签信息错误!", e);
		}
		return PAGE_VIEW_REDIRECT_LINE;

	}

	@RequestMapping(value = "edit")
	public String edit(GmLabelDto gmLabelDto, Model model, RedirectAttributes redirectAttributes) {
		try {
			gmLabelService.updateGmLabel(gmLabelDto);
			addMessage(redirectAttributes, "编辑个人标签'" + gmLabelDto.getLabelName() + "'成功");
		} catch (Exception e) {
			logger.error("修改个人标签信息错误！", e);
		}
		return PAGE_VIEW_REDIRECT_LINE;
	}

	@RequestMapping(value = "del")
	@ResponseBody
	public CommonRepsonseDto del(String[] codes, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (null == codes || codes.length <= 0) {
				return CommonRepsonseDto.generateFailureResponse("参数错误！");
			}
			GmLabelDto gmLabelDto = new GmLabelDto();
			for (String string : codes) {
				gmLabelDto.setCode(string);
				gmLabelService.delGmLabel(gmLabelDto);
			}

			return CommonRepsonseDto.generateSuccessResponse("操作成功！");
		} catch (Exception e) {
			logger.error("修改个人标签信息错误！", e);
			return CommonRepsonseDto.generateFailureResponse("操作失败！");
		}
	}

}
