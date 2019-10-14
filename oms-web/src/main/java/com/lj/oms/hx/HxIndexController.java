/**
 * 
 */
package com.lj.oms.hx;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.IDistributeCache;
import com.lj.oms.common.BaseController;
import com.lj.base.core.util.StringUtils;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.ITokenService;
import com.lj.oms.utils.UserUtils;
import com.ye.business.hx.constant.HxConstant;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 
 * 
 * 类说明：焕新系统路由。
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年3月6日
 */
@Controller
@RequestMapping(value = "${adminPath}/hx/")
public class HxIndexController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(HxIndexController.class);

	@Resource
	private ITokenService tokenService;

	@Resource
	private IDistributeCache distributeCache;

	@Resource
	private LocalCacheSystemParamsFromCC  localCacheSystemParams;
	
	
	/**
	 * 焕新路由。
	 * 1.员工则跳转到焕新的H5主页
	 * 2.非员工则跳转到服务审核。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "index" })
	public String listHtml5(String host,HttpServletRequest request) {
		logger.debug("listHtml5(HttpServletRequest request={}) - start", request);
		try {
			Token token = tokenService.generateToken(UserUtils.getUser().getId(), "HLM_H5",
					Token.TOKEN_TIMEOUT_SECONDS);
			JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			String h5Host = host;
			if(StringUtils.isEmpty(host)) {
				h5Host = localCacheSystemParams.getSystemParam(HxConstant.systemAliasName,HxConstant.group_h5, HxConstant.host_h5);
			}
			String h5Url=null;
			if(h5Host.endsWith("/")) {
				h5Url=h5Host+"login";
			}else {
				h5Url=h5Host+"/"+"login";
			}
			//如果是门诊员工则跳转到H5
			String returnString = "redirect:" + h5Url + "?token=" + token.getAccessToken();
			
			logger.debug("listHtml5(HttpServletRequest) - end - return value={}", returnString);
			return returnString;
		} catch (Exception e) {
			logger.error("listHtml5(HttpServletRequest)", e);
		}
		logger.debug("listHtml5(HttpServletRequest) - end - return value={}", "");
		return "";
	}

	

}
