/**
 * 
 */
package com.lj.oms.hx;

import java.util.List;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.User;
import com.lj.oms.utils.UserUtils;
import com.ye.business.hx.dto.FestivalPosterDto;
import com.ye.business.hx.dto.FindFestivalPosterPage;
import com.ye.business.hx.service.IFestivalPosterService;

/**
 * 
 * 
 * 类说明：节日海报。
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年7月22日
 */
@Controller
@RequestMapping(value = "${adminPath}/hx/fp")
public class FestivalPosterController extends BaseController {

	/** 列表页面 **/
	private final static String PAGE_VIEW_HX_FP_LIST = "modules/hx/fpList";
	/** 提交页面 **/
	private final static String PAGE_VIEW_HX_FP_FORM = "modules/hx/fpForm";
	/** 重定向到列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_FP = "redirect:" + Global.getAdminPath() + "/hx/fp";

	@Autowired
	private IFestivalPosterService festivalPosterService;

	/**
	 * 非超级管理员不可操作该模块
	 * 
	 * @param redirectAttributes
	 */
	@ModelAttribute
	public void checkIsAdmin(RedirectAttributes redirectAttributes) {
		String userId = UserUtils.getUser().getId();
		if (!User.isAdmin(userId)) {
			throw new UnauthorizedException("msg:非超级管理员无权限操作！");
		}
	}

	/**
	 * 
	 *
	 * 方法说明：节日海报列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param dtoPageDto
	 * @return 服务提供分页，转换OMS分页列表
	 *
	 * @author 刘红艳 CreateDate: 2019.03.15
	 *
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, FestivalPosterDto festivalPosterDto,
			String returnMessage, RedirectAttributes redirectAttributes) {
		try {
			FindFestivalPosterPage param = new FindFestivalPosterPage();
			param.setParam(festivalPosterDto);
//			festivalPosterDto.setMerchantNo(UserUtils.getMerchantNo());
			if (pageNo != null) {
				param.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				param.setLimit(pageSize);
			}
			Page<FestivalPosterDto> pages = festivalPosterService.findFestivalPosterPage(param);
			List<FestivalPosterDto> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FestivalPosterDto> page = new com.ape.common.persistence.Page<FestivalPosterDto>(
					pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("festivalPosterDto", festivalPosterDto);
		} catch (Exception e) {
			logger.error("获取节日海报数据错误", e);
		}
		return PAGE_VIEW_HX_FP_LIST;
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
	 * @author 刘红艳 CreateDate: 2019.03.15
	 *
	 */
	@RequestMapping(value = "form")
	public String form(Model model, FestivalPosterDto dto) {
		try {
			if (dto != null && dto.getCode() != null) {
				FestivalPosterDto data = festivalPosterService.findFestivalPoster(dto);
				model.addAttribute("data", data);
			}
		} catch (Exception e) {
			logger.error("查询节日海报数据错误", e);
		}

		return PAGE_VIEW_HX_FP_FORM;
	}

	/**
	 * 方法说明：保存节日问候
	 * 
	 * @param model
	 * @param findServerInfo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(Model model, FestivalPosterDto dto, RedirectAttributes redirectAttributes) {
		try {
			AssertUtils.notNullAndEmpty(dto);

			AssertUtils.notNullAndEmpty(dto.getTypeName(), "节日类型不能为空");
			AssertUtils.notNullAndEmpty(dto.getImgs(), "素材图片不能为空");

			dto.setCreateId(UserUtils.getUser().getName());
			festivalPosterService.addFestivalPoster(dto);

			addMessage(redirectAttributes, "保存节日问候" + dto.getTypeName() + "成功");
		} catch (Exception e) {
			logger.error("保存客户服务信息错误!", e);
		}
		return PAGE_VIEW_REDIRECT_FP;
	}

	/**
	 * 
	 *
	 * 方法说明：节日海报编辑
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param dto
	 * @return
	 *
	 * @author 刘红艳 CreateDate: 2019.03.15
	 *
	 */
	@RequestMapping(value = "edit")
	public String edit(Model model, RedirectAttributes redirectAttributes, FestivalPosterDto dto) {
		try {
			dto.setUpdateId(UserUtils.getUser().getName());
			festivalPosterService.updateFestivalPoster(dto);
			addMessage(redirectAttributes, "修改节日海报成功");
		} catch (Exception e) {
			if (e instanceof TsfaServiceException) {
				TsfaServiceException tException = (TsfaServiceException) e;
				addMessage(redirectAttributes, "操作失败！" + tException.getExceptionInfo());
			}
			logger.debug("修改节日海报错误", e);
		}
		return PAGE_VIEW_REDIRECT_FP;

	}

	/**
	 *
	 * 方法说明：节日海报删除
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param dto
	 * @return
	 *
	 * @author 刘红艳 CreateDate: 2019.03.15
	 *
	 */
	@RequestMapping(value = "remove")
	public String remove(Model model, RedirectAttributes redirectAttributes, FestivalPosterDto dto) {
		try {
			festivalPosterService.deleteFestivalPoster(dto);
			addMessage(redirectAttributes, "删除节日海报成功");
		} catch (Exception e) {
			if (e instanceof TsfaServiceException) {
				TsfaServiceException tException = (TsfaServiceException) e;
				addMessage(redirectAttributes, "操作失败！" + tException.getExceptionInfo());
			}
			logger.debug("删除节日海报错误", e);
		}
		return PAGE_VIEW_REDIRECT_FP;
	}

}
