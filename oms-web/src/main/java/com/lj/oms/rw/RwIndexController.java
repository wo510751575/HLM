package com.lj.oms.rw;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lj.base.core.util.StringUtils;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.ITokenService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.IDistributeCache;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;
import com.ye.business.rw.constant.RwConstant;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 
 * *类说明：热文路由
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年6月24日
 */
@Controller
@RequestMapping(value = "${adminPath}/rw/")
public class RwIndexController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(RwIndexController.class);

	@Resource
	private ITokenService tokenService;

	@Resource
	private IDistributeCache distributeCache;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	/**
	 * 焕新路由。 1.员工则跳转到焕新的H5主页 2.非员工则跳转到服务审核。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "index" })
	public String listHtml5(String host, HttpServletRequest request) {
		logger.debug("listHtml5(HttpServletRequest request={}) - start", request);
		try {
			Token token = tokenService.generateToken(UserUtils.getUser().getId(), "RW_H5", Token.TOKEN_TIMEOUT_SECONDS);
			JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			String h5Host = host;
			if (StringUtils.isEmpty(host)) {
				h5Host = localCacheSystemParams.getSystemParam(RwConstant.systemAliasName, RwConstant.group_h5, RwConstant.host_h5);
			}
			String h5Url = null;
			if (h5Host.endsWith("/")) {
				h5Url = h5Host;// + "login";
			} else {
				h5Url = h5Host;// + "/" + "login";
			}

			// token 加密
			String tokenss = token.getAccessToken();
			String tokenEnd = tokenss.substring(0, 8) + "f1b3" + tokenss.substring(8, 12) + "e34b" + tokenss.substring(12);

			// 如果是门诊员工则跳转到H5
			String returnString = "redirect:" + h5Url + "?token=" + tokenEnd;

			logger.debug("listHtml5(HttpServletRequest) - end - return value={}", returnString);
			return returnString;
		} catch (Exception e) {
			logger.error("listHtml5(HttpServletRequest)", e);
		}
		logger.debug("listHtml5(HttpServletRequest) - end - return value={}", "");
		return "";
	}
}
