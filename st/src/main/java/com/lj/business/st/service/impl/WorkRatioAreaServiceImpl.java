package com.lj.business.st.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.ArithUtil;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IWorkRatioAreaDao;
import com.lj.business.st.domain.WorkRatioArea;
import com.lj.business.st.dto.AddWorkRatioArea;
import com.lj.business.st.dto.FindWorkRatioArea;
import com.lj.business.st.dto.FindWorkRatioAreaReturn;
import com.lj.business.st.dto.WorkRatioAreaDto;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.service.IWorkRatioAreaService;

/**
 * 
 * 
 * 类说明：区域工作统计服务实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月27日
 */
@Service
public class WorkRatioAreaServiceImpl implements IWorkRatioAreaService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PmTypeTotalServiceImpl.class);
	@Resource
	private IWorkRatioAreaDao workRatioAreaDao;
	
	@Override
	public void addWorkRatioArea(AddWorkRatioArea addWorkRatioArea) {
		logger.debug("addWorkRatioArea(AddWorkRatioArea addWorkRatioArea={}) - start", addWorkRatioArea); 
		AssertUtils.notNull(addWorkRatioArea);
		try {
			WorkRatioArea workRatioArea= new WorkRatioArea();
			//add数据录入
			workRatioArea.setCode(GUID.generateByUUID());
			workRatioArea.setMemberNoMerchant(addWorkRatioArea.getMemberNoMerchant());
			workRatioArea.setAreaCode(addWorkRatioArea.getAreaCode());
			workRatioArea.setAreaName(addWorkRatioArea.getAreaName());
			workRatioArea.setProvinceCode(addWorkRatioArea.getProvinceCode());
			workRatioArea.setProvinceName(addWorkRatioArea.getProvinceName());
			workRatioArea.setNumShop(addWorkRatioArea.getNumShop());
			workRatioArea.setRatioShop(addWorkRatioArea.getRatioShop());
			workRatioArea.setNumPm(addWorkRatioArea.getNumPm());
			workRatioArea.setRatioPm(addWorkRatioArea.getRatioPm());
			workRatioArea.setStDate(addWorkRatioArea.getStDate());
			workRatioArea.setDimensionSt(addWorkRatioArea.getDimensionSt());
			workRatioArea.setCreateDate(new Date());
			workRatioAreaDao.insertSelective(workRatioArea);
			logger.debug("addWorkRatioArea(addWorkRatioArea) - end - return"); 
		} catch (Exception e) {
			logger.error("新增区域工作统计错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_AREA_ADD_ERROR,"新增区域工作统计错误！",e);
		}

	}

	@Override
	public Page<Map<String, Object>> findWorkRatioAreaPage(
			Map<String, Object> parmMap) {
		logger.debug("findWorkRatioAreaPage(parmMap={}) - start", parmMap); 
		AssertUtils.notNull(parmMap);
		AssertUtils.notNull(parmMap.get("start"));
		AssertUtils.notNull(parmMap.get("limit"));
		int count=0;
		List<Map<String, Object>> list=null;
		try {
			list = workRatioAreaDao.findWorkRatioAreaPage(parmMap);
			count =workRatioAreaDao.findWorkRatioAreaCount(parmMap);
		} catch (Exception e) {
			logger.error("查询区域工作统计错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_AREA_FIND_ERROR,"查询区域工作统计错误！",e);
		}
		Page<Map<String, Object>> returnPage = new Page<Map<String, Object>>(list, count,Integer.valueOf(parmMap.get("start").toString()) , Integer.valueOf(parmMap.get("limit").toString()));
		logger.debug("findWorkRatioAreaPage(parmMap) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public List<Map<String, Object>> findWorkRatioAreas(Map<String, Object> parmMap) {
		logger.debug("findWorkRatioAreaPage(parmMap={}) - start", parmMap); 
		AssertUtils.notNull(parmMap);
		List<Map<String, Object>> list=null;
		try {
			list = workRatioAreaDao.findWorkRatioAreas(parmMap);
		} catch (Exception e) {
			logger.error("查询区域工作统计错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_AREA_FIND_ERROR,"查询区域工作统计错误！",e);
		}
		logger.debug("findWorkRatioAreaPage(parmMap) - end - return value={}", list);
		return list;
	}
	
	@Override
	public List<WorkRatioAreaDto> findWorkRatioAreas2(Map<String, Object> parmMap) {
		AssertUtils.notNull(parmMap);
		List<WorkRatioAreaDto> list=null;
		try {
			list = workRatioAreaDao.findWorkRatioAreas2(parmMap);
		} catch (Exception e) {
			logger.error("查询区域工作统计错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_AREA_FIND_ERROR,"查询区域工作统计错误！",e);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findBroupProvince(
			Map<String, Object> parmMap) {
		logger.debug("findBroupProvince(parmMap={}) - start", parmMap); 
		AssertUtils.notNull(parmMap);
		List<Map<String, Object>> list=null;
		try {
			list = workRatioAreaDao.findBroupProvince(parmMap);
		} catch (Exception e) {
			logger.error("查询区域工作统计错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_AREA_FIND_ERROR,"查询区域工作统计错误！",e);
		}
		logger.debug("findBroupProvince(parmMap) - end - return value={}", list);
		return list;
	}

	@Override
	public Map<String, Object> findShopGroupArea(Map<String, Object> parmMap) {
		logger.debug("findShopGroupArea(parmMap={}) - start", parmMap);
		AssertUtils.notNull(parmMap);
		AssertUtils.notNull(parmMap.get("memberNoMerchant"));
		Date now =new Date();
		parmMap.put("startTime",DateUtils.formatDate(DateUtils.getPreday(now), DateUtils.PATTERN_yyyy_MM_dd));
		parmMap.put("endTime",DateUtils.formatDate(now, DateUtils.PATTERN_yyyy_MM_dd));
		parmMap.put("dimensionSt", DimensionSt.AREA.toString());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			List<WorkRatioAreaDto> list = this.findWorkRatioAreas2(parmMap);
			List<String> areaNames = new ArrayList<String>();
			List<String> areaCodes = new ArrayList<String>();
			List<Integer> numShops = new ArrayList<Integer>();
			List<Double> ratioShops = new ArrayList<Double>();
			for (WorkRatioAreaDto dto : list) {
				areaNames.add(dto.getAreaName() == null ? "未命名区域" : dto.getAreaName());
				areaCodes.add(dto.getAreaCode());
				numShops.add(dto.getNumShop());
				ratioShops.add(dto.getRatioShop());
			}
			returnMap.put("areaNames", areaNames);
			returnMap.put("areaCodes", areaCodes);
			returnMap.put("numShops", numShops);
			returnMap.put("ratioShops", ratioShops);
		} catch (Exception e) {
			logger.error("门店分布统计错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_AREA_FIND_ERROR,"门店分布统计错误！",e);
		}
		logger.debug("findShopGroupArea(parmMap) - end - return value={}", returnMap);
		return returnMap;
	}

	@Override
	public List<Map<String, Object>> findShopByAreaCode(String areaCode,String merchantNo) {
		logger.debug("findShopByAreaCode(parmMap={}) - start", areaCode);
		AssertUtils.notNull(areaCode);
		AssertUtils.notNull(merchantNo);

		Map<String, Object> parmMap = new HashMap<>();
		Date now =new Date();
		parmMap.put("merchantNo",merchantNo);
		parmMap.put("dimensionSt", DimensionSt.PROVINCE.toString());
		parmMap.put("areaCode",areaCode);
		parmMap.put("startTime",DateUtils.formatDate(DateUtils.getPreday(now), DateUtils.PATTERN_yyyy_MM_dd));
		parmMap.put("endTime",DateUtils.formatDate(now, DateUtils.PATTERN_yyyy_MM_dd));
		List<Map<String,Object>> provinces = new ArrayList<Map<String,Object>>();
		try {
			provinces= this.findBroupProvince(parmMap);
			if(provinces != null && provinces.size() > 0){
				for (Map<String, Object> map : provinces) {
					Object n = map.get("PROVINCE_NAME");
					if(n != null){
						map.put("PROVINCE_NAME", map.get("PROVINCE_NAME").toString().replaceAll("市", "").replaceAll("省", "").replaceAll("特别行政区", "").replaceAll("自治区", ""));
					}else{
						map.put("PROVINCE_NAME","");
					}
				}
			}
		} catch (Exception e) {
			logger.error("门店分布统计错误！",e);
			throw new TsfaServiceException(ErrorCode.SHOP_AREA_FIND_ERROR,"门店分布统计错误！",e);
		}
		logger.debug("findShopByAreaCode(parmMap) - end - return value={}", provinces);
		return provinces == null?new ArrayList<Map<String,Object>>():provinces;
	}

	@Override
	public FindWorkRatioAreaReturn findWorkRatioAreaList(FindWorkRatioArea findWorkRatioArea) {
		logger.debug("findWorkRatioAreaList(findWorkRatioArea={}) - start", findWorkRatioArea);

		AssertUtils.notNull(findWorkRatioArea);
		//AssertUtils.notNull(findWorkRatioArea.getBeginDate(), "开始日期不能为空");
		//AssertUtils.notNull(findWorkRatioArea.getEndDate(), "结束日期不能为空");

		FindWorkRatioAreaReturn result = new FindWorkRatioAreaReturn();
		try {
			List<WorkRatioArea> workRatioAreaList = workRatioAreaDao.findWorkRatioAreaList(findWorkRatioArea);
			
			Map<String, Object> maxDatas = new HashMap<>();
			List<Map<String, Object>> provinceList = new ArrayList<>();

			Map<String, Long> areaNumMap = new HashMap<>();
			Map<String, String> areaNameMap = new HashMap<>();
			List<Map<String, Object>> areaList = new ArrayList<>();

			Long totalNum = 0L;
			
			if (!CollectionUtils.isEmpty(workRatioAreaList)) {
			
				for (WorkRatioArea each : workRatioAreaList) {
					totalNum += each.getNumPm();

					// 放置省数据
					Map<String, Object> provinceMap = new HashMap<>();
					provinceMap.put("name", removeProvinceOrCity(each.getProvinceName()));
					provinceMap.put("code", each.getProvinceCode());
					provinceMap.put("number", each.getNumPm());
					provinceList.add(provinceMap);

					// 计算区域总量
					Long areaNum = areaNumMap.get(each.getAreaCode());
					if (areaNum == null) {
						areaNum = 0L;
					}
					areaNumMap.put(each.getAreaCode(), areaNum + each.getNumPm());
					areaNameMap.put(each.getAreaCode(), each.getAreaName());
				}

				// 计算区域的比率
				Long maxNum = null;
				for (Map.Entry<String, Long> entry : areaNumMap.entrySet()) {
					
					if(!"0".equals(toString(totalNum, entry.getValue())) 
							&& !"0.0000".equals(toString(totalNum, entry.getValue()))){
						Map<String, Object> areaItem = new HashMap<>();
						areaItem.put("name", areaNameMap.get(entry.getKey()));
						areaItem.put("code", entry.getKey());
						areaItem.put("ratio", toString(totalNum, entry.getValue()));
						areaItem.put("number", entry.getValue());
						areaList.add(areaItem);
					}

					// 计算最大的数据
					if (maxNum == null || entry.getValue() > (Long) maxDatas.get("number")) {
						maxDatas.put("name", areaNameMap.get(entry.getKey()));
						maxDatas.put("code", entry.getKey());
						maxDatas.put("ratio", toString(totalNum, entry.getValue()));
						maxDatas.put("number", entry.getValue());
						maxNum = entry.getValue();
					}
				}
				
			}
			
			//获取客户最多区域                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
			findWorkRatioArea.setDimensionSt("AREA");
			List<WorkRatioArea> workRatioAreas = workRatioAreaDao.findWorkRatioAreaList(findWorkRatioArea);
			WorkRatioArea maxData = workRatioAreas.get(0);
			
			for (WorkRatioArea workRatioArea : workRatioAreas) {
				if (maxData.getNumPm() < workRatioArea.getNumPm()) {
					maxData = workRatioArea;
				}
			}
			
			maxDatas.put("name", maxData.getAreaName());
			maxDatas.put("code", maxData.getAreaCode());
			maxDatas.put("ratio", maxData.getRatioPm());
			maxDatas.put("number", maxData.getNumPm());
			
			result.setAreaList(areaList);
			result.setProvinceList(provinceList);
			result.setMaxDatas(maxDatas);

			logger.debug("findWorkRatioAreaList(findWorkRatioArea) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查询区域统计表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_AREA_FIND_ERROR, "查询区域统计表信息错误！", e);
		}
		return result;
	}

	private static String removeProvinceOrCity(String name) {
		if (name == null) {
			return "";
		}
		int index = name.lastIndexOf("省");
		if (index != -1) {
			return name.substring(0, index);
		}
		else {
			index = name.lastIndexOf("市");
			if (index != -1) {
				return name.substring(0, index);
			}
			return name;
		}
	}

	private String toString(Long totalNum, Long num) {
		if (totalNum == 0) {
			return "0";
		}
		Double div = ArithUtil.div(num, totalNum);
		BigDecimal bd = new BigDecimal(div);
		bd =  bd.setScale(4, RoundingMode.HALF_UP);
		return bd.toString();
	}
}
