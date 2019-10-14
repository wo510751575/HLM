package com.lj.business.api.controller.rw;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.rw.dto.ArticleTypeDto;
import com.ye.business.rw.dto.FindArticleTypePage;
import com.ye.business.rw.enums.Status;
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
@RestController
@RequestMapping("/rw/articleType")
public class ArticleTypeAction extends Action {

	/**
	 * 文章
	 */
	@Autowired
	private IArticleTypeService articleTypeService;

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
	public List<ArticleTypeDto> list(ArticleTypeDto param, FindArticleTypePage findArticleTypePage) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
//		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");

		param.setStatus(Status.Y.toString());

		findArticleTypePage.setParam(param);

		findArticleTypePage.setSortBy("numOrder");

		return articleTypeService.findArticleTypes(findArticleTypePage);
	}
	
	/**
	 * H5文章类型List
	 * 
	 * @param param
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "articleTypeList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse articleTypeList(ArticleTypeDto param, FindArticleTypePage findArticleTypePage) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
//		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");

		findArticleTypePage.setParam(param);

		findArticleTypePage.setSortBy("numOrder");
		
		Page<ArticleTypeDto> pageDto = articleTypeService.findArticleTypePage(findArticleTypePage);

		return GeneralResponse.generateSuccessResponse(pageDto);
	}
	
	/**
	 * H5文章类型Form
	 * 
	 * @param param
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "articleTypeForm.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse articleTypeForm(ArticleTypeDto param) throws TsfaServiceException {
		if (param != null && param.getCode() != null) {
			if (StringUtils.isNotBlank(param.getCode())) {
				ArticleTypeDto data = articleTypeService.findArticleType(param);
				return GeneralResponse.generateSuccessResponse(data);
			} else {
				return GeneralResponse.generateSuccessResponse();
			}
		}
		return GeneralResponse.generateFailureResponse();
	}
	
	/**
	 * H5文章类型Edit
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "articleTypeEdit.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse articleTypeEdit(ArticleTypeDto param) {
		try {
			AssertUtils.notNullAndEmpty(param);
			AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");
			AssertUtils.notNullAndEmpty(param.getTypeName(), "类型名称不能为空");
			AssertUtils.notNullAndEmpty(param.getStatus(), "状态不能为空");
			Date now = new Date();

			param.setUpdateTime(now);

			articleTypeService.updateArticleType(param);
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("编辑文章类型信息错误！", e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	/**
	 * H5文章类型Save
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "articleTypeSave.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse articleTypeSave(ArticleTypeDto param){
		try {
			AssertUtils.notNullAndEmpty(param);
			AssertUtils.notNullAndEmpty(param.getTypeName(), "类型名称不能为空");
			AssertUtils.notNullAndEmpty(param.getStatus(), "状态不能为空");
			param.setMerchantNo(param.getMerchantNo());
			
			Date now = new Date();
			param.setCreateDate(now);
			param.setUpdateTime(now);
			param.setCreateId(param.getCreateId());

			articleTypeService.addArticleType(param);
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("保存文章类型错误!", e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	/**
	 * H5文章类型Delete
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "articleTypeDelete.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse articleTypeDelete(ArticleTypeDto param){
		try {
			AssertUtils.notNullAndEmpty(param.getCode());
			articleTypeService.removeByPrimaryKey(param.getCode());
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("删除文章类型信息错误！", e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	
	

}
