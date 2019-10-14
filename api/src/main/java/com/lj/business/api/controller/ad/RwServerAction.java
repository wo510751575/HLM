package com.lj.business.api.controller.ad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.ye.business.ad.dto.FindRwServerPage;
import com.ye.business.ad.dto.RwServerDto;
import com.ye.business.ad.service.IRwServerService;

/**
 * 
 * *类说明：服务
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月8日
 */
@RestController
@RequestMapping("/ad/rwServer")
public class RwServerAction extends Action {

	@Autowired
	private IRwServerService rwServerService;

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
	public List<RwServerDto> list(RwServerDto param, FindRwServerPage findRwServerPage) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);

		findRwServerPage.setParam(param);

		findRwServerPage.setSortBy("numOrder");

		return rwServerService.findRwServers(findRwServerPage);
	}

}
