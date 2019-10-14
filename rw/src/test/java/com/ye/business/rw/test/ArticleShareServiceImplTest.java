package com.ye.business.rw.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.ye.business.rw.dto.ArticleShareDto;
import com.ye.business.rw.dto.FindArticleSharePage;
import com.ye.business.rw.service.IArticleShareService;

public class ArticleShareServiceImplTest extends SpringTestCase {

	@Resource
	private IArticleShareService articleShareService;

	@Test
	public void findList() throws TsfaServiceException {

		FindArticleSharePage findArticleSharePage = new FindArticleSharePage();
		ArticleShareDto param = new ArticleShareDto();

		param.setMemberNoGuid("aaa");

		param.setHasMemberNoGuidViewNotEmpty(param.getMemberNoGuid());

		findArticleSharePage.setParam(param);

		findArticleSharePage.setSortBy("dateDesc");

		Page<ArticleShareDto> rs = articleShareService.findArticleSharePageForGroup(findArticleSharePage);

		System.out.println(rs.toString());

		Page<ArticleShareDto> rss = articleShareService.findArticleSharePageForView(findArticleSharePage);

		System.out.println(rss.toString());
	}
}
