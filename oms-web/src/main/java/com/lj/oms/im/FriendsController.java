/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.im;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.oms.dto.CommonRepsonseDto;

/**
 * 
 * 类说明：朋友圈
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2018年7月31日
 */
@Controller
@RequestMapping(value = "${adminPath}/im")
public class FriendsController {

	private static final Logger logger = LoggerFactory.getLogger(FriendsController.class);

	@Resource
	private IImFriendsInfoService imFriendsInfoService;

	/**
	 * 
	 *
	 * 方法说明：删除朋友圈 <br>
	 * 1、删除朋友圈需要请求中控客户，是一个异步操作 <br>
	 * 2、此方法只能同步得到请求是否成功 <br>
	 * 3、要得到删除结果，客户端需异步请求查询结果的方法findFriendsDeleteStatus，建议每隔5秒查询一次，最多查询6次
	 *
	 * @param friendsCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月31日
	 *
	 */
	@RequestMapping(value = "delectFriends")
	@ResponseBody
	public CommonRepsonseDto delectFriends(String friendsCode, String memberNoGm) {
		try {
			if (StringUtils.isBlank(friendsCode)) {
				return CommonRepsonseDto.generateFailureResponse("朋友圈编号为空");
			}
			imFriendsInfoService.sendDeleteFriendsInfo(friendsCode, memberNoGm);
			return CommonRepsonseDto.generateSuccessResponse("已向中控请求删除朋友圈");
		} catch (TsfaServiceException e) {
			logger.error("删除朋友圈失败！", e);
			return CommonRepsonseDto.generateFailureResponse(e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("删除朋友圈失败！", e);
			return CommonRepsonseDto.generateFailureResponse("删除朋友圈失败");
		}
	}

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈是否已成功删除
	 *
	 * @param friendsCode
	 * @return 0-正在删除、1-删除成功、2-查询结果失败
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月31日
	 *
	 */
	@RequestMapping(value = "findFriendsDeleteStatus")
	@ResponseBody
	public int findFriendsDeleteStatus(String friendsCode) {
		try {
			ImFriendsInfoDto imFriendsInfoDto = new ImFriendsInfoDto();
			imFriendsInfoDto.setCode(friendsCode);
			imFriendsInfoDto = imFriendsInfoService.findImFriendsInfo(imFriendsInfoDto);
			if (imFriendsInfoDto == null) { // 没有找到则表示删除成功
				return 1;
			} else { // 找到，则表示正在删除
				return 0;
			}
		} catch (TsfaServiceException e) {
			logger.error("查询删除朋友圈结果失败！", e);
			return 2;
		} catch (Exception e) {
			logger.error("查询删除朋友圈结果失败！", e);
			return 2;
		}
	}

}
