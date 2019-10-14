package com.lj.oms.ad;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;
import com.ye.business.ad.dto.AdvertiseTypeDto;
import com.ye.business.ad.dto.FindAdvertiseTypePage;
import com.ye.business.ad.service.IAdvertiseTypeService;

/**
 * 
 * *类说明：广告类型
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月8日
 */
@Controller
@RequestMapping(value = "${adminPath}/ad/advertiseType")
public class AdvertiseTypeController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdvertiseTypeController.class);

	@Autowired
	private IAdvertiseTypeService advertiseTypeService;

	/**
	 * 
	 * *方法说明：广告类型-分页
	 *
	 * @param findAdvertisePage
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("ad:advertiseType:view")
	@RequestMapping(value = { "list", "" })
	public String list(FindAdvertiseTypePage findAdvertiseTypePage, AdvertiseTypeDto param, Integer pageNo, Integer pageSize, Model model) {
		try {

			if (pageNo != null) {
				findAdvertiseTypePage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findAdvertiseTypePage.setLimit(pageSize);
			}

			findAdvertiseTypePage.setParam(param);

			Page<AdvertiseTypeDto> pageDto = advertiseTypeService.findAdvertiseTypePage(findAdvertiseTypePage);

			List<AdvertiseTypeDto> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());

			com.ape.common.persistence.Page<AdvertiseTypeDto> page = new com.ape.common.persistence.Page<AdvertiseTypeDto>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
					pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findAdvertiseTypePage", findAdvertiseTypePage);

		} catch (Exception e) {
			logger.error("获取广告类型信息错误！", e);
		}
		return "modules/ad/advertiseTypeList";
	}

	/**
	 * 
	 * *方法说明：查看广告类型信息
	 *
	 * @param param
	 * @param model
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("ad:advertiseType:view")
	@RequestMapping(value = "form")
	public String form(AdvertiseTypeDto param, Model model) {
		try {
			if (param != null && param.getCode() != null) {
				if (StringUtils.isNotBlank(param.getCode())) {
					AdvertiseTypeDto data = advertiseTypeService.findAdvertiseType(param);
					model.addAttribute("data", data);
				} else {
					// 回显添加失败时的信息
					model.addAttribute("data", param);
				}
			}
		} catch (Exception e) {
			logger.error("获取广告类型信息错误！", e);
		}

		return "modules/ad/advertiseTypeForm";
	}

	/**
	 * 
	 * *方法说明：
	 *
	 * @param param
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("ad:advertiseType:edit")
	@RequestMapping(value = "save")
	public String save(AdvertiseTypeDto param, Model model, RedirectAttributes redirectAttributes) {
		try {

			AssertUtils.notNullAndEmpty(param);
			AssertUtils.notNullAndEmpty(param.getTypeName(), "类型名称不能为空");
			AssertUtils.notNullAndEmpty(param.getStatus(), "状态不能为空");

			param.setMerchantNo(UserUtils.getMerchantNo());

			Date now = new Date();
			String loginName = UserUtils.getUser().getLoginName();

			param.setCreateDate(now);
			param.setUpdateTime(now);
			param.setCreateId(loginName);

			advertiseTypeService.addAdvertiseType(param);
			addMessage(redirectAttributes, "保存广告类型成功");
		} catch (Exception e) {
			logger.error("保存广告类型信息错误！", e);
		}
		return "redirect:" + adminPath + "/ad/advertiseType/";
	}

	/**
	 * 
	 * *方法说明：编辑
	 *
	 * @param param
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("ad:advertiseType:edit")
	@RequestMapping(value = "edit")
	public String edit(AdvertiseTypeDto param, Model model, RedirectAttributes redirectAttributes) {
		try {

			AssertUtils.notNullAndEmpty(param);
			AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");
			AssertUtils.notNullAndEmpty(param.getTypeName(), "类型名称不能为空");
			AssertUtils.notNullAndEmpty(param.getStatus(), "状态不能为空");

			Date now = new Date();

			param.setUpdateTime(now);

			advertiseTypeService.updateAdvertiseType(param);

			addMessage(redirectAttributes, "编辑广告类型成功");
		} catch (Exception e) {
			logger.error("编辑广告类型信息错误！", e);
		}
		return "redirect:" + adminPath + "/ad/advertiseType/";
	}

	/**
	 * 
	 * *方法说明：删除
	 *
	 * @param code
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("ad:advertiseType:edit")
	@RequestMapping(value = "delete")
	public String delete(String code, RedirectAttributes redirectAttributes) {
		try {
			AssertUtils.notNullAndEmpty(code);
			advertiseTypeService.removeByPrimaryKey(code);

			addMessage(redirectAttributes, "删除广告类型成功");

		} catch (Exception e) {
			logger.error("删除广告类型信息错误！", e);
		}
		return "redirect:" + adminPath + "/ad/advertiseType/";
	}

}
