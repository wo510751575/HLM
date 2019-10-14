package com.lj.business.api.controller.rw;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.supcon.dto.token.MemberLoginCache;
import com.lj.business.supcon.service.ITokenService;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.ISystemService;
import com.ye.business.rw.dto.ArticleComplaintDto;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.FindArticleComplaintPage;
import com.ye.business.rw.enums.Complaint;
import com.ye.business.rw.enums.RwState;
import com.ye.business.rw.service.IArticleComplaintService;
import com.ye.business.rw.service.IArticleService;

/**
 * 热文投诉记录
 * 
 * @author sjiying
 *
 */
@Controller
@RequestMapping("/rw/articleComplaint")
public class ArticleComplaintAction extends Action {
	/**
	 * 文章投诉
	 */
	@Autowired
	private IArticleComplaintService articleComplaintService;

	@Resource
	private ITokenService tokenService;

	@Resource
	private ISystemService systemService;

	/**
	 * 文章
	 */
	@Autowired
	private IArticleService articleService;

	/**
	 * 
	 * *方法说明：添加投诉记录
	 *
	 * @param param
	 * @param token
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年6月27日
	 */
	@RequestMapping(value = "save.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse save(ArticleComplaintDto param, String token, HttpServletRequest request) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getArticleCode(), "文章code不能为空");
		AssertUtils.notNullAndEmpty(param.getComplaintContent(), "投诉内容不能为空");
		AssertUtils.notNullAndEmpty(param.getComplaintEvidence(), "投诉截图不能为空");
		AssertUtils.notNullAndEmpty(param.getComplaintUsername(), "用户名不能为空");
		AssertUtils.notNullAndEmpty(param.getComplaintMobile(), "联系方式不能为空");

		// 写入IP地址
		param.setRemark(this.getRemoteHost(request));
		param.setCreateDate(new Date());
		param.setCreateId(param.getComplaintUsername());
		param.setAuditorStatus(Complaint.noverify.toString());

		String rs = articleComplaintService.addArticleComplaint(param);

		return GeneralResponse.generateSuccessResponse(rs);
	}

	/**
	 * 
	 * *方法说明：分页列表
	 *
	 * @param param
	 * @param token
	 * @param page
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年6月27日
	 */
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse list(ArticleComplaintDto param, String token, FindArticleComplaintPage findArticleComplaintPage) throws TsfaServiceException {

		findArticleComplaintPage.setParam(param);
		findArticleComplaintPage.setSortBy("numOrder");

		Page<ArticleComplaintDto> page = articleComplaintService.findArticleComplaintPage(findArticleComplaintPage);

		return GeneralResponse.generateSuccessResponse(page);
	}

	/**
	 * 
	 * *方法说明：获取详情
	 *
	 * @param param
	 * @param token
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年6月27日
	 */
	@RequestMapping(value = "info.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse info(ArticleComplaintDto param, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);

		AssertUtils.notNullAndEmpty(param.getCode(), "code不能为空");

		ArticleComplaintDto rs = articleComplaintService.findArticleComplaint(param);

		return GeneralResponse.generateSuccessResponse(rs);
	}

	/**
	 * 
	 * *方法说明：审核
	 *
	 * @param param
	 * @param token
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年6月27日
	 */
	@RequestMapping(value = "auditor.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse auditor(ArticleComplaintDto param, String token) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);

		AssertUtils.notNullAndEmpty(param.getCode(), "code不能为空");

		AssertUtils.notNullAndEmpty(param.getAuditorContent(), "审核不能为空");
		AssertUtils.notNullAndEmpty(param.getAuditorStatus(), "审核状态不能为空");
		AssertUtils.notNullAndEmpty(param.getAuditorResult(), "审核结果不能为空");

		MemberLoginCache memberLoginCache = tokenService.parseMember(token);

		param.setAuditorDate(new Date());
		param.setAuditorCode(memberLoginCache.getCode());

		User user = systemService.getUser(memberLoginCache.getCode());
		if (user == null) {
			return GeneralResponse.generateFailureResponse("000000", "用户不存在");
		}
		param.setAuditorName(user.getName());

		articleComplaintService.updateArticleComplaint(param);

		// 文章下架
		if (Complaint.forbid.toString().equals(param.getAuditorResult())) {

			ArticleComplaintDto articleComplaintReturn = articleComplaintService.findArticleComplaint(param);

			ArticleDto article = new ArticleDto();
			article.setCode(articleComplaintReturn.getArticleCode());
			article.setRwState(RwState.forbid.toString());

			articleService.updateArticle(article);
		}

		return GeneralResponse.generateSuccessResponse();
	}

}
