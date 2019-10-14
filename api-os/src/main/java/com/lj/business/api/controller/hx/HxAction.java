/**
 * 
 */
package com.lj.business.api.controller.hx;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.business.api.domain.GeneralResponse;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.OfficeHessianService;
import com.ye.business.hx.constant.HxConstant;
import com.ye.business.hx.domain.HxClue;
import com.ye.business.hx.dto.FindShopConfigPage;
import com.ye.business.hx.dto.ShopConfigDto;
import com.ye.business.hx.dto.params.ClueParams;
import com.ye.business.hx.service.IHxClueService;
import com.ye.business.hx.service.IShopConfigService;

/**
 * 
 * 
 * 类说明：焕新对接工单API。
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年3月16日
 */
@Controller
@RequestMapping(value = "/hx/")
public class HxAction {

	private static Logger logger = LoggerFactory.getLogger(HxAction.class);

	@Resource
	private OfficeHessianService officeHessianService;
	@Resource
	private IShopConfigService shopConfigService;
	@Resource
	private IHxClueService hxClueService;

	/**
	 * 查询所有门诊信息。
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllMerchants.do")
	@ResponseBody
	public GeneralResponse findAllMerchants() {
		try {
			List<Office> datas = officeHessianService.findMerchantsByOffice(null);
			return GeneralResponse.generateSuccessResponse(datas);
		} catch (Exception e) {
			logger.error("获取门诊信息错误！！e={}", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	/**
	 * 查找客户类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "getUserType.do")
	@ResponseBody
	public GeneralResponse findUserType() {
		List<ShopConfigDto> datas = null;
		ShopConfigDto findConfig = new ShopConfigDto();
		// 根据类别查父级CODE
		ShopConfigDto param = new ShopConfigDto();
		param.setMerchantNo(Office.ROOT_ID);
		param.setLableNo(HxConstant.CONFIG_LABLE_NO_USER_TYPE);

		FindShopConfigPage findShopConfigPage = new FindShopConfigPage();
		findShopConfigPage.setParam(param);

		List<ShopConfigDto> parentDtos = shopConfigService
				.findShopConfigs(findShopConfigPage);
		if (parentDtos != null && parentDtos.size() > 0) {
			findConfig.setParentCode(parentDtos.get(0).getCode());

			FindShopConfigPage findPage = new FindShopConfigPage();
			findPage.setParam(findConfig);
			findConfig.setMerchantNo(Office.ROOT_ID);
			findConfig.setShopNo(null);
			datas = shopConfigService.findShopConfigs(findPage);
		}
		return GeneralResponse.generateSuccessResponse(datas);
	}

	/**
	 * 创建/编辑线索
	 * 
	 * @return
	 */
	@RequestMapping(value = "createClue.do")
	@ResponseBody
	public GeneralResponse createClue(HxClue clue) {
		try {
			hxClueService.createClue(clue);
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("创建/编辑线索错误！！e={}", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	/**
	 * 诊所服务列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "clinicServices.do")
	@ResponseBody
	public GeneralResponse clinicServices(@RequestBody ClueParams params) {
		try {
			return GeneralResponse.generateSuccessResponse(hxClueService
					.clinicServices(params));
		} catch (Exception e) {
			logger.error("获取诊所服务列表错误！！e={}", e);
			return GeneralResponse.generateFailureResponse();
		}

	}

	/**
	 * 修改线索状态（转跟进）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "upstatus.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse upstatus(String orderno) {
		try {
			hxClueService.upstatus(orderno);
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("修改线索状态（转跟进）错误！！e={}", e);
			return GeneralResponse.generateFailureResponse();
		}

	}

	/**
	 * 派单（工单）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "visiting.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse visiting_order(ClueParams params) {
		try {
			hxClueService.visiting_order(params);
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("派单（工单）错误！！e={}", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	/**
	 * 确认/取消到店
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "confirmorcancel.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse confirmorcancel(ClueParams params) {
		try {
			hxClueService.confirmorcancel(params);
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("确认/取消到店！！e={}", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

}
