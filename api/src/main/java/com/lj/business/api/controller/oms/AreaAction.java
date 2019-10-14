package com.lj.business.api.controller.oms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.StringUtils;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.dto.HotCityAndPinyinCityDto;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.Dict;
import com.lj.oms.service.AreaHessianService;



/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：省市区接口
 *   
 * @Company: 扬恩科技有限公司
 * @author 冯辉
 *   
 * CreateDate: 2017年7月12日
 */

@Controller
@RequestMapping(value="/area/")
public class AreaAction extends Action {
	
	private static Logger logger = LoggerFactory.getLogger(AreaAction.class);
	
	@Autowired
	private AreaHessianService areaHessianService;
	
	/**
	 * 
	 *
	 * 方法说明：获取省
	 *
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月12日
	 *
	 */
	@RequestMapping(value="province.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Area> province() {
		logger.debug("province() - start"); 

		List<Area> returnList = areaHessianService.selectProvince();
		logger.debug("province() - end"); 
		return returnList;
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取市、区
	 *
	 * @param parentId
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月12日
	 *
	 */
	@RequestMapping(value="cityarea.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Area> cityarea(String parentId) {
		logger.debug("cityarea(String parentId={}) - start", parentId); 
		List<Area> returnList = areaHessianService.selectAreaByParentId(parentId);
		logger.debug("cityarea(String parentId={}) - end", parentId); 
		return returnList;
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取所有省市区
	 *
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月4日
	 *
	 */
	@RequestMapping(value="findAllList.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Area> findAllList() {
		logger.debug("FindProvinceAndCityarea() - start"); 
		List<Area> returnList = areaHessianService.findAllList();
		logger.debug("cityarea(String parentId={}) - end"); 
		return returnList;
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：获取省市区版本号
	 *
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月4日
	 *
	 */
	@RequestMapping(value="getAreaVersion.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public int getAreaVersion() {
		return areaHessianService.getAreaVersion();
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取所在区域列表
	 *
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年10月9日
	 *
	 */
	@RequestMapping(value="findAreaCodeList.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Dict> findAreaCodeList() {
		logger.debug("findAreaCodeList() - start"); 

		List<Dict> returnList = areaHessianService.selectAreaCode();
		logger.debug("findAreaCodeList() - end"); 
		return returnList;
	}
	
	/**
	 * 
	 *
	 * 方法说明：H5获取终端城市接口
	 *
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月9日
	 *
	 */
	@RequestMapping(value="findShopCity.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String, Object>> findShopCity(String merchantNo) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("merchantNo", merchantNo);
		return areaHessianService.findShopCity(parmMap);
	}
	
	/**
	 * 查询热门城市和拼音城市
	 * @return
	 */
	@RequestMapping(value="findHotCityAndPinyinCity.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public HotCityAndPinyinCityDto findHotCityAndPinyinCity() {
		logger.debug("findHotCityAndPinyinCity() - start"); 
		HotCityAndPinyinCityDto hotCityAndPinyinCityDto = new HotCityAndPinyinCityDto();
		List<Area> hotList = areaHessianService.findHotList();
		Map<String,String> pinyinMap = areaHessianService.findPinyinMap();
		hotCityAndPinyinCityDto.setHotAreaList(hotList);
		hotCityAndPinyinCityDto.setPinyinCityMap(pinyinMap);
		logger.debug("findHotCityAndPinyinCity() - end"); 
		return hotCityAndPinyinCityDto;
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据市名找地区
	 * 
	 * @param cityName 城市名称 必填
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月12日
	 *
	 */
	@RequestMapping(value="getCityAreaByCityName.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse getCityAreaByCityName(String cityName) {
		logger.debug("getCityAreaByCityName(String cityName={}) - start", cityName); 
		if(StringUtils.isEmpty(cityName))
		{
			return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR,"请求参数错误！");
		}
		Area area = areaHessianService.findAreaByCityName(cityName);
		List<Area> returnList =null;
		if(area != null){
			returnList = areaHessianService.selectAreaByParentId(area.getId());
		}
		logger.debug("getCityAreaByCityName(List<Area> returnList={}) - end", returnList); 
		return GeneralResponse.generateSuccessResponse(returnList);
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据市名找市
	 *
	 * @param cityName
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年3月28日
	 *
	 */
	@RequestMapping(value="findCityAreaByCityName.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse findCityAreaByCityName(String cityName) {
		logger.debug("findCityAreaByCityName(String cityName={}) - start", cityName); 
		if(StringUtils.isEmpty(cityName))
		{
			return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR,"请求参数错误！");
		}
		Area area = areaHessianService.findAreaByCityName(cityName);
		logger.debug("findCityAreaByCityName(List<Area> returnList={}) - end", area); 
		return GeneralResponse.generateSuccessResponse(area);
	}
	
}
