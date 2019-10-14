package com.lj.business.api.controller.rw;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.rw.constant.ErrorCodeArticle;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.ArticleShareDto;
import com.ye.business.rw.dto.ArticleViewDto;
import com.ye.business.rw.dto.FindArticlePage;
import com.ye.business.rw.enums.RwState;
import com.ye.business.rw.kit.HttpKit;
import com.ye.business.rw.service.IArticleService;
import com.ye.business.rw.service.IArticleShareService;
import com.ye.business.rw.service.IArticleViewService;

/**
 * 热文
 * 
 * @author sjiying
 *
 */
@RestController
@RequestMapping("/rw/article")
public class ArticleAction extends Action {

	/**
	 * 文章
	 */
	@Autowired
	private IArticleService articleService;
	/**
	 * 文章分享
	 */
	@Autowired
	private IArticleShareService articleShareService;
	@Autowired
	private IArticleViewService articleViewService;

	/**
	 * 列表
	 * 
	 * @param param
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<ArticleDto> list(ArticleDto param, FindArticlePage findArticlePage) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);

		param.setRwState(RwState.normal.toString());

		findArticlePage.setParam(param);

		findArticlePage.setSortBy("dateDesc");

		Page<ArticleDto> rs = articleService.findArticlePage(findArticlePage);

		if (!rs.getRows().isEmpty()) {
			rs.getRows().forEach(action -> action.setTitle(StringEscapeUtils.unescapeHtml4(action.getTitle())));
		}

		return rs;
	}

	/**
	 * H5列表
	 * 
	 * @param param
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "articleList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse articleList(ArticleDto param, FindArticlePage findArticlePage, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);

		// 验证权限
		verifyTokenPermission(token, "rw:article:view");

		findArticlePage.setParam(param);

		findArticlePage.setSortBy("dateDesc");

		Page<ArticleDto> rs = articleService.findArticlePage(findArticlePage);

		if (!rs.getRows().isEmpty()) {
			rs.getRows().forEach(action -> action.setTitle(StringEscapeUtils.unescapeHtml4(action.getTitle())));
		}

		return GeneralResponse.generateSuccessResponse(rs);
	}

	/**
	 * 
	 * @param param
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "articleForm.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse articleForm(ArticleDto param, FindArticlePage findArticlePage) throws TsfaServiceException {
		if (param != null && param.getCode() != null) {
			if (StringUtils.isNotBlank(param.getCode())) {
				ArticleDto data = articleService.findArticle(param);
				return GeneralResponse.generateSuccessResponse(data);
			}
		}
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 编辑
	 * 
	 * @param param
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "articleEdit.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse articleEdit(ArticleDto param, String token) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");

		String userid = getLoginUserByToken(token);

		param.setCreateId(userid);
		param.setCreateDate(new Date()); // 重置创建日期

		articleService.updateArticle(param);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 保存
	 * 
	 * @param param
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "articleSave.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse articleSave(ArticleDto param, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
//		AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");

		String userid = getLoginUserByToken(token);

		param.setCreateDate(new Date());
		param.setLikeNum(0);
		param.setReadNum(0);
		param.setCreateId(userid);

		articleService.addArticle(param);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 删除
	 * 
	 * @param param
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "articleDelete.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse articleDelete(ArticleDto param, String token) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param.getCode());

		getLoginUserByToken(token);

		articleService.removeByPrimaryKey(param.getCode());
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 文章阅读量
	 * 
	 * @param param
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "info.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ArticleDto info(ArticleDto param, HttpServletRequest request) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "CODE不能为空");

		// 获取明细，阅读量新增一次
		articleService.updateArticleForReadNum(param.getCode());

		ArticleDto rs = articleService.findArticle(param);

		AssertUtils.notNullAndEmpty(rs);

		if (StringUtils.isNotBlank(rs.getTitle())) {
			rs.setTitle(StringEscapeUtils.unescapeHtml4(rs.getTitle()));
		}

		// 根据来源写入点击量
		String memberNoGuidSource = param.getMemberNoGuidSource();

		if (StringUtils.isNotBlank(memberNoGuidSource)) {

			Date now = new Date();

			ArticleViewDto view = new ArticleViewDto();
			view.setArticleCode(param.getCode());
			view.setCreateId(param.getMemberNoGuid());
			view.setMemberNoGuid(memberNoGuidSource);
			view.setShopNo(param.getShopNoSource());
			view.setMerchantNo(param.getMerchantNoSource());
			view.setRemark(this.getRemoteHost(request));
			view.setRemark2("article");
			
			view.setMainImage(rs.getMainImage());
			view.setTitle(rs.getTitle());

			// 点击用户
			view.setMemberNameGuidView(param.getMemberNoGuid());
			view.setShopNoView(param.getShopNo());
			view.setMerchantNoView(param.getMerchantNo());

			view.setCreateDate(now);
			view.setUpdateTime(now);

			articleViewService.addArticleViewByOnly(view);
		}

		return rs;
	}

	/**
	 * 文章分享量
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "share.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse share(ArticleDto param, HttpServletRequest request) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "CODE不能为空");
		AssertUtils.notNullAndEmpty(param.getMemberNoGuidSource(), "分享源用户不能为空");

		Date now = new Date();

		ArticleShareDto share = new ArticleShareDto();
		share.setArticleCode(param.getCode());
		share.setCreateId(param.getMemberNoGuid());
		share.setMemberNoGuid(param.getMemberNoGuidSource());
		share.setShopNo(param.getShopNoSource());
		share.setMerchantNo(param.getMerchantNoSource());
		share.setRemark(this.getRemoteHost(request));
//		share.setRemark4(RwConstant.SHARE);
		
		share.setMainImage(param.getMainImage());
		share.setTitle(param.getTitle());

		// 点击用户
		share.setMemberNameGuidView(param.getMemberNoGuid());
		share.setShopNoView(param.getShopNo());
		share.setMerchantNoView(param.getMerchantNo());

		share.setCreateDate(now);
		share.setUpdateTime(now);

		articleShareService.addArticleShare(share);

		return GeneralResponse.generateSuccessResponse();

	}

	/**
	 * 添加
	 * 
	 * @param param
	 * @param paramJson
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "add.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(ArticleDto param, String paramJson) throws TsfaServiceException {

		// paramJson 对象转换出现问题，手动转换获取
		@SuppressWarnings("unchecked")
		Map<String, String> record = (Map<String, String>) JSON.parse(paramJson);

		param.setMainImage(record.getOrDefault("mainImage", null));
		param.setTitle(record.getOrDefault("title", null));
		param.setType(record.getOrDefault("type", null));
		param.setSource(record.getOrDefault("source", null));
		param.setRemark(record.getOrDefault("remark", null));
		param.setCreateId(record.getOrDefault("createId", null));
		param.setArticleHtml(record.getOrDefault("articleHtml", null));

		AssertUtils.notNullAndEmpty(param);
//		AssertUtils.notNullAndEmpty(param.getType(), "文章类型不能为空");
		AssertUtils.notNullAndEmpty(param.getMainImage(), "文章主图不能为空");
		AssertUtils.notNullAndEmpty(param.getTitle(), "文章标题不能为空");
		AssertUtils.notNullAndEmpty(param.getArticleHtml(), "文章内容不能为空");
//		AssertUtils.notNullAndEmpty(param.getCreateId(), "创建人不能为空");

		// 验证是否字符串转码
		if ("1".equals(record.getOrDefault("state", "-1"))) {
			try {
				param.setArticleHtml(URLDecoder.decode(param.getArticleHtml(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				throw new TsfaServiceException(ErrorCodeArticle.ARTICLE_ADD_ERROR, "文章内容解码失败");
			}
		}

		param.setCreateDate(new Date());
		param.setReadNum(0);
		param.setLikeNum(0);

		return articleService.addArticle(param);
	}

	/**
	 * 抓取链接中的文章内容
	 * 
	 * @param uri
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "climb.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse climb(String uri) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(uri, "获取地址不能为空");
		String rs = HttpKit.doGet(uri);

		return GeneralResponse.generateSuccessResponse(rs);

	}

}
