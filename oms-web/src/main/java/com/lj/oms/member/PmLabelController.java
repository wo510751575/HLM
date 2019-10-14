package com.lj.oms.member;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.AddPmLabel;
import com.lj.business.member.dto.DelPmLabel;
import com.lj.business.member.dto.FindPmLabel;
import com.lj.business.member.dto.FindPmLabelPage;
import com.lj.business.member.dto.FindPmLabelPageReturn;
import com.lj.business.member.dto.FindPmLabelReturn;
import com.lj.business.member.dto.UpdatePmLabel;
import com.lj.business.member.service.IPmLabelService;
import com.lj.oms.common.BaseController;
import com.lj.oms.dto.CommonRepsonseDto;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：客户标签Controller
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 邹磊
 * 
 *         CreateDate: 2017年6月24日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/pmLabel")
public class PmLabelController extends BaseController {

	@Resource
	private IPmLabelService pmLabelService; // 客户标签服务

	/**
	 * DESCRIBE
	 *
	 * 方法说明：客户标签列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findPmLabelPage
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("member:pmLabel:view")
	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, FindPmLabelPage findPmLabelPage) {
		try {
			if (pageNo != null) {
				findPmLabelPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findPmLabelPage.setLimit(pageSize);
			}
			findPmLabelPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindPmLabelPageReturn> pageDto = pmLabelService.findPmLabelPage(findPmLabelPage);
			ArrayList<FindPmLabelPageReturn> basePageReturns = Lists.newArrayList();
			basePageReturns.addAll(pageDto.getRows());
			com.ape.common.persistence.Page<FindPmLabelPageReturn> page = new com.ape.common.persistence.Page<FindPmLabelPageReturn>(
					pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), basePageReturns);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findPmLabelPage", findPmLabelPage);
		} catch (Exception e) {
			logger.error("获取客户标签信息错误！", e);
		}
		return "modules/member/pmLabelList";

	}

	/**
	 * 
	 *
	 * 方法说明：Form表单提交
	 *
	 * @param findPmLabel
	 * @param model
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月26日
	 *
	 */
	@RequiresPermissions("member:pmLabel:view")
	@RequestMapping(value = "form")
	public String form(FindPmLabel findPmLabel, Model model) {
		try {
			if (findPmLabel != null && findPmLabel.getCode() != null) {
				FindPmLabelReturn findPmLabelReturn = pmLabelService.findPmLabel(findPmLabel);
				model.addAttribute("data", findPmLabelReturn);
			}
		} catch (Exception e) {
			logger.error("获取客户标签信息错误！", e);

		}
		return "modules/member/pmLabelForm";

	}

	/**
	 * 
	 *
	 * 方法说明：保存客户标签
	 *
	 * @param addPmLabel
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月24日
	 *
	 */
	@RequiresPermissions("member:pmLabel:edit")
	@RequestMapping(value = "save")
	public String save(AddPmLabel addPmLabel, Model model, RedirectAttributes redirectAttributes) {
		try {
			addPmLabel.setMerchantNo(UserUtils.getMerchantNo());
			int count = pmLabelService.selectCountByMerchantNo(addPmLabel);
			if (count > 0) {
				addMessage(redirectAttributes, "保存客户标签'" + addPmLabel.getLabelName() + "'失败,标签已存在!");
				return "redirect:" + adminPath + "/member/pmLabel/";
			}
			addPmLabel.setCreateId(UserUtils.getUser().getCompany().getName());
			pmLabelService.addPmLabel(addPmLabel);
			addMessage(redirectAttributes, "保存客户标签'" + addPmLabel.getLabelName() + "'成功");
		} catch (Exception e) {
			logger.error("保存客户标签信息错误!", e);
		}
		return "redirect:" + adminPath + "/member/pmLabel/";

	}

	/**
	 * 
	 *
	 * 方法说明：编辑客户标签
	 *
	 * @param updatePmLabel
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月24日
	 *
	 */
	@RequiresPermissions("member:pmLabel:edit")
	@RequestMapping(value = "edit")
	public String edit(UpdatePmLabel updatePmLabel, Model model, RedirectAttributes redirectAttributes) {
		try {
			pmLabelService.updatePmLabel(updatePmLabel);
			addMessage(redirectAttributes, "编辑客户标签'" + updatePmLabel.getLabelName() + "'成功");
		} catch (Exception e) {
			logger.error("修改客户标签信息错误！", e);
		}
		return "redirect:" + adminPath + "/member/pmLabel/";
	}

	@RequiresPermissions("member:pmLabel:edit")
	@RequestMapping(value = "del")
	@ResponseBody
	public CommonRepsonseDto del(String[] codes, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (null == codes || codes.length <= 0) {
				return CommonRepsonseDto.generateFailureResponse("参数错误！");
			}
			DelPmLabel delPmLabel = new DelPmLabel();
			for (String string : codes) {
				delPmLabel.setCode(string);
				pmLabelService.delPmLabel(delPmLabel);
			}

			return CommonRepsonseDto.generateSuccessResponse("操作成功！");
		} catch (Exception e) {
			logger.error("修改客户标签信息错误！", e);
			return CommonRepsonseDto.generateFailureResponse("操作失败！");
		}
	}
}
