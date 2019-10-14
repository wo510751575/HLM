package com.lj.business.api.controller.ad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.ye.business.ad.dto.FindServerInfoPage;
import com.ye.business.ad.dto.ServerInfoDto;
import com.ye.business.ad.enums.Status;
import com.ye.business.ad.service.IServerInfoService;

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
@RequestMapping("/ad/serverInfo")
public class ServerInfoAction extends Action {

	@Autowired
	private IServerInfoService serverInfoService;

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
	public List<ServerInfoDto> list(ServerInfoDto param, FindServerInfoPage findServerInfoPage, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);

		getLoginUserByToken(token);
		
		param.setStatus(Status.use.toString());

		findServerInfoPage.setParam(param);

		findServerInfoPage.setSortBy("numOrder");

		return serverInfoService.findServerInfos(findServerInfoPage);
	}

}
