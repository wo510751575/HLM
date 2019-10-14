package com.lj.oms.rw;

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
import com.ye.business.rw.dto.ArticleTypeDto;
import com.ye.business.rw.dto.FindArticleTypePage;
import com.ye.business.rw.service.IArticleTypeService;

/**
 * 
 * *类说明：文章类型
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月8日
 */
@Controller
@RequestMapping(value = "${adminPath}/rw/articleType")
public class ArticleTypeController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleTypeController.class);

	@Autowired
	private IArticleTypeService articleTypeService;

	/**
	 * 
	 * *方法说明：文章类型-分页
	 *
	 * @param findArticlePage
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("rw:articleType:view")
	@RequestMapping(value = { "list", "" })
	public String list(FindArticleTypePage findArticleTypePage, ArticleTypeDto param, Integer pageNo, Integer pageSize, Model model) {
		try {

			if (pageNo != null) {
				findArticleTypePage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findArticleTypePage.setLimit(pageSize);
			}

			findArticleTypePage.setParam(param);

			Page<ArticleTypeDto> pageDto = articleTypeService.findArticleTypePage(findArticleTypePage);

			List<ArticleTypeDto> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());

			com.ape.common.persistence.Page<ArticleTypeDto> page = new com.ape.common.persistence.Page<ArticleTypeDto>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
					pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findArticleTypePage", findArticleTypePage);

		} catch (Exception e) {
			logger.error("获取文章类型信息错误！", e);
		}
		return "modules/rw/articleTypeList";
	}

	/**
	 * 
	 * *方法说明：查看文章类型信息
	 *
	 * @param param
	 * @param model
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("rw:articleType:view")
	@RequestMapping(value = "form")
	public String form(ArticleTypeDto param, Model model) {
		try {
			if (param != null && param.getCode() != null) {
				if (StringUtils.isNotBlank(param.getCode())) {
					ArticleTypeDto data = articleTypeService.findArticleType(param);
					model.addAttribute("data", data);
				} else {
					// 回显添加失败时的信息
					model.addAttribute("data", param);
				}
			}
		} catch (Exception e) {
			logger.error("获取文章类型信息错误！", e);
		}

		return "modules/rw/articleTypeForm";
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
	@RequiresPermissions("rw:articleType:edit")
	@RequestMapping(value = "save")
	public String save(ArticleTypeDto param, Model model, RedirectAttributes redirectAttributes) {
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

			articleTypeService.addArticleType(param);
			addMessage(redirectAttributes, "保存文章类型成功");
		} catch (Exception e) {
			logger.error("保存文章类型信息错误！", e);
		}
		return "redirect:" + adminPath + "/rw/articleType/";
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
	@RequiresPermissions("rw:articleType:edit")
	@RequestMapping(value = "edit")
	public String edit(ArticleTypeDto param, Model model, RedirectAttributes redirectAttributes) {
		try {

			AssertUtils.notNullAndEmpty(param);
			AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");
			AssertUtils.notNullAndEmpty(param.getTypeName(), "类型名称不能为空");
			AssertUtils.notNullAndEmpty(param.getStatus(), "状态不能为空");

			Date now = new Date();

			param.setUpdateTime(now);

			articleTypeService.updateArticleType(param);

			addMessage(redirectAttributes, "编辑文章类型成功");
		} catch (Exception e) {
			logger.error("编辑文章类型信息错误！", e);
		}
		return "redirect:" + adminPath + "/rw/articleType/";
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
	@RequiresPermissions("rw:articleType:edit")
	@RequestMapping(value = "delete")
	public String delete(String code, RedirectAttributes redirectAttributes) {
		try {
			AssertUtils.notNullAndEmpty(code);
			articleTypeService.removeByPrimaryKey(code);

			addMessage(redirectAttributes, "删除文章类型成功");

		} catch (Exception e) {
			logger.error("删除文章类型信息错误！", e);
		}
		return "redirect:" + adminPath + "/rw/articleType/";
	}

}
