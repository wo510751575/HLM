package com.lj.business.api.controller.ad;

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
import com.ye.business.ad.dto.AdvertiseTypeDto;
import com.ye.business.ad.dto.FindAdvertiseTypePage;
import com.ye.business.ad.enums.Status;
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
@RestController
@RequestMapping("/ad/advertiseType")
public class AdvertiseTypeAction extends Action {

	/**
	 * 文章
	 */
	@Autowired
	private IAdvertiseTypeService advertiseTypeService;

	/**
	 * 列表
	 * 
	 * @param param
	 * @param findAdvertisePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<AdvertiseTypeDto> list(AdvertiseTypeDto param, FindAdvertiseTypePage findAdvertiseTypePage) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
//		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");

		param.setStatus(Status.Y.toString());

		findAdvertiseTypePage.setParam(param);

		findAdvertiseTypePage.setSortBy("numOrder");

		return advertiseTypeService.findAdvertiseTypes(findAdvertiseTypePage);
	}
	
	/**
	 * H5广告类型列表
	 * @param param
	 * @param findAdvertiseTypePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "adTypeList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse adTypeList(AdvertiseTypeDto param, FindAdvertiseTypePage findAdvertiseTypePage) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
//		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");


		findAdvertiseTypePage.setParam(param);

		findAdvertiseTypePage.setSortBy("numOrder");
		
		Page<AdvertiseTypeDto> pageDto = advertiseTypeService.findAdvertiseTypePage(findAdvertiseTypePage);

		return GeneralResponse.generateSuccessResponse(pageDto);
	}
	
	/**
	 * H5 广告类型From
	 * @param param
	 * @param findAdvertiseTypePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "adTypeForm.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse adTypeForm(AdvertiseTypeDto param, FindAdvertiseTypePage findAdvertiseTypePage) throws TsfaServiceException {
		if (param != null && param.getCode() != null) {
			if (StringUtils.isNotBlank(param.getCode())) {
				AdvertiseTypeDto data = advertiseTypeService.findAdvertiseType(param);
				return GeneralResponse.generateSuccessResponse(data);
			}
		}
		return GeneralResponse.generateSuccessResponse();
		
	}
	
	/**
	 * H5 广告类型保存
	 * @param param
	 * @param findAdvertiseTypePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "adTypeSave.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse adTypeSave(AdvertiseTypeDto param, FindAdvertiseTypePage findAdvertiseTypePage) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getTypeName(), "类型名称不能为空");
		AssertUtils.notNullAndEmpty(param.getStatus(), "状态不能为空");

		param.setMerchantNo(param.getMerchantNo());

		Date now = new Date();

		param.setCreateDate(now);
		param.setUpdateTime(now);
		param.setCreateId(param.getCreateId());

		advertiseTypeService.addAdvertiseType(param);
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * H5 广告类型编辑
	 * @param param
	 * @param findAdvertiseTypePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "adTypeEdit.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse adTypeEdit(AdvertiseTypeDto param, FindAdvertiseTypePage findAdvertiseTypePage) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");
		AssertUtils.notNullAndEmpty(param.getTypeName(), "类型名称不能为空");
		AssertUtils.notNullAndEmpty(param.getStatus(), "状态不能为空");

		Date now = new Date();

		param.setUpdateTime(now);

		advertiseTypeService.updateAdvertiseType(param);
		
		return GeneralResponse.generateSuccessResponse();
		
	}
	
	/**
	 * H5 广告类型删除
	 * @param param
	 * @param findAdvertiseTypePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "adTypeDelete.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse adTypeDelete(AdvertiseTypeDto param, FindAdvertiseTypePage findAdvertiseTypePage) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param.getCode());
		advertiseTypeService.removeByPrimaryKey(param.getCode());
		return GeneralResponse.generateSuccessResponse();
		
	}
	

}
