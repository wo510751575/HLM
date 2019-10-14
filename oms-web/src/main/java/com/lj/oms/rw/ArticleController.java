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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.oms.common.BaseController;
import com.lj.oms.dto.CommonRepsonseDto;
import com.lj.oms.utils.UserUtils;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.ArticleTypeDto;
import com.ye.business.rw.dto.FindArticlePage;
import com.ye.business.rw.dto.FindArticleTypePage;
import com.ye.business.rw.kit.HttpKit;
import com.ye.business.rw.service.IArticleService;
import com.ye.business.rw.service.IArticleTypeService;

/**
 * 
 * *类说明：文章内容
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月5日
 */
@Controller
@RequestMapping(value = "${adminPath}/rw/article")
public class ArticleController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private IArticleService articleService;
	@Autowired
	private IArticleTypeService articleTypeService;

	/**
	 * 
	 * *方法说明：热文分享-分页
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
	@RequiresPermissions("rw:article:view")
	@RequestMapping(value = { "list", "" })
	public String list(FindArticlePage findArticlePage, ArticleDto param, Integer pageNo, Integer pageSize, Model model) {
		try {

			// 分类列表
			getArticleTypeList(model);

			if (pageNo != null) {
				findArticlePage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findArticlePage.setLimit(pageSize);
			}

			findArticlePage.setParam(param);
			findArticlePage.setSortBy("dateDesc");

			Page<ArticleDto> pageDto = articleService.findArticlePage(findArticlePage);

			List<ArticleDto> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());

			com.ape.common.persistence.Page<ArticleDto> page = new com.ape.common.persistence.Page<ArticleDto>(pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(),
					list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findArticlePage", findArticlePage);

		} catch (Exception e) {
			logger.error("获取热文分享信息错误！", e);
		}
		return "modules/rw/articleList";
	}

	/**
	 * 
	 * *方法说明：查看热文信息
	 *
	 * @param param
	 * @param model
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("rw:article:view")
	@RequestMapping(value = "form")
	public String form(ArticleDto param, Model model) {
		try {

			// 分类列表
			getArticleTypeList(model);

			if (param != null && param.getCode() != null) {
				if (StringUtils.isNotBlank(param.getCode())) {
					ArticleDto data = articleService.findArticle(param);
					model.addAttribute("data", data);
				} else {
					// 回显添加失败时的信息
					model.addAttribute("data", param);
				}
			}
		} catch (Exception e) {
			logger.error("获取热文分享信息错误！", e);
		}

		return "modules/rw/articleForm";
	}

	/**
	 * 
	 * *方法说明：封装广告分类列表
	 *
	 * @param model
	 * @author sjiying
	 * @CreateDate 2019年5月14日
	 */
	private void getArticleTypeList(Model model) {
		// 分类列表
		FindArticleTypePage findArticleTypePage = new FindArticleTypePage();
		findArticleTypePage.setSortBy("numOrder");
		List<ArticleTypeDto> findArticleTypeList = articleTypeService.findArticleTypes(findArticleTypePage);
		model.addAttribute("findArticleTypeList", findArticleTypeList);
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
	@RequiresPermissions("rw:article:edit")
	@RequestMapping(value = "save")
	public String save(ArticleDto param, Model model, RedirectAttributes redirectAttributes) {
		try {
			param.setMerchantNo(UserUtils.getMerchantNo());

			param.setCreateDate(new Date());
			param.setLikeNum(0);
			param.setReadNum(0);
			param.setCreateId(UserUtils.getUser().getLoginName());

			articleService.addArticle(param);
			addMessage(redirectAttributes, "保存热文分享成功");
		} catch (Exception e) {
			logger.error("保存热文分享信息错误！", e);
		}
		return "redirect:" + adminPath + "/rw/article/";
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
	@RequiresPermissions("rw:article:edit")
	@RequestMapping(value = "edit")
	public String edit(ArticleDto param, Model model, RedirectAttributes redirectAttributes) {
		try {

			AssertUtils.notNullAndEmpty(param);
			AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");

			param.setCreateId(UserUtils.getUser().getLoginName());
			param.setCreateDate(new Date()); // 重置创建日期

			articleService.updateArticle(param);

			addMessage(redirectAttributes, "编辑热文分享成功");
		} catch (Exception e) {
			logger.error("编辑热文分享信息错误！", e);
		}
		return "redirect:" + adminPath + "/rw/article/";
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
	@RequiresPermissions("rw:article:edit")
	@RequestMapping(value = "delete")
	public String delete(String code, RedirectAttributes redirectAttributes) {
		try {
			AssertUtils.notNullAndEmpty(code);
			articleService.removeByPrimaryKey(code);

			addMessage(redirectAttributes, "删除热文分享成功");

		} catch (Exception e) {
			logger.error("删除热文分享信息错误！", e);
		}
		return "redirect:" + adminPath + "/rw/article/";
	}

	/**
	 * 
	 * *方法说明：根据URL获取HTML内容
	 *
	 * @param uri
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("rw:article:edit")
	@RequestMapping(value = "climb")
	@ResponseBody
	public CommonRepsonseDto climb(String uri) {
		try {
			AssertUtils.notNullAndEmpty(uri, "获取地址不能为空");

			String rs = HttpKit.doGet(uri);

//			String rs = HtmlKit.doGet(uri);

//			if (StringUtils.isBlank(rs)) {
//				// HtmlUnit 未获取到内容，HttpClient 重新获取
//				rs = HttpKit.doGet(uri);
//			}

			return CommonRepsonseDto.generateSuccessResponse(rs);

		} catch (Exception e) {
			return CommonRepsonseDto.generateFailureResponse("获取内容失败");
		}
	}
}
