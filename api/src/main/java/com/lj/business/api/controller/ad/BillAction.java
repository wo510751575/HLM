package com.lj.business.api.controller.ad;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.ad.dto.BillDto;
import com.ye.business.ad.dto.FindBillPage;
import com.ye.business.ad.service.IBillService;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.FindArticlePage;
import com.ye.business.rw.service.IArticleService;

@RestController
@RequestMapping("/ad/bill/")
public class BillAction extends Action {

	@Resource
	private IBillService billService;

	/**
	 * 文章
	 */
	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "list.do")
	@ResponseBody
	public GeneralResponse list(FindBillPage findBillPage, BillDto param) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		findBillPage.setParam(param);

		findBillPage.setSortBy("dateDesc");

		Page<BillDto> page = billService.findBillPage(findBillPage);
		return GeneralResponse.generateSuccessResponse(page);

	}

	/**
	 * 
	 * *方法说明：
	 *
	 * @param findBillPage
	 * @param param
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月24日
	 */
	@RequestMapping(value = "listPage.do")
	@ResponseBody
	public GeneralResponse listPage(FindBillPage findBillPage, BillDto param) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		findBillPage.setParam(param);

		findBillPage.setSortBy("dateDesc");

		Page<BillDto> page = billService.findBillPage(findBillPage);

		List<String> arcodeList = page.getRows().stream().filter(pd -> StringUtils.isNotEmpty(pd.getArticleCode())).map(BillDto::getArticleCode).collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(arcodeList)) {

			ArticleDto article = new ArticleDto();
			article.setCodeList(arcodeList);

			FindArticlePage findArticlePage = new FindArticlePage();
			findArticlePage.setParam(article);

			List<ArticleDto> findArticlePageReturn = articleService.findArticles(findArticlePage);
			if (!CollectionUtils.isEmpty(findArticlePageReturn)) {

				Map<String, String> articleMap = findArticlePageReturn.stream().collect(Collectors.toMap(ArticleDto::getCode, ArticleDto::getTitle));
				page.getRows().forEach(action -> {
					if (StringUtils.isNotEmpty(action.getArticleCode()) && articleMap.containsKey(action.getArticleCode())) {
						action.setTitle(articleMap.get(action.getArticleCode()));
					}
				});
			}
		}

		return GeneralResponse.generateSuccessResponse(page);
	}
}
