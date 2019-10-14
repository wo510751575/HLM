package com.lj.oms.service;

import java.util.List;
import java.util.Map;

import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.Dict;

/**
 * 
 * 
 * 类说明：省市区开放接口
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月11日
 */
public interface AreaHessianService {
		 
	/**
	 * 获取所有的省份
	 * @return
	 */
	public List<Area> selectProvince();
	
	/**
	 * 获取所有的城市
	 * @author 彭俊霖 CreateDate: 2017年10月13日
	 * @return
	 */
	public List<Area> selectCity();
	
	/**
	 * 
	 *
	 * 方法说明：获取所有的区
	 *
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月17日
	 *
	 */
    public List<Area> selectRegion();
	
	/**
	 * 
	 *
	 * 方法说明：查询所有省市区信息
	 *
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年8月3日
	 *
	 */
	public List<Area> FindProvinceAndCityarea();  
	
	/**
	 * 获取字节点
	 * @param parentId 父节点ID
	 * @return
	 */
	public List<Area> selectAreaByParentId(String parentId);
	
	/**
	 * 查询当前区域同一级别的其它区域信息
	 * @param areaId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map selectCurrentLevelByAreaId(String areaId);
	
	/**
	 * 查询当前城市同一级别的其它城市信息
	 * @param cityId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map selectCurrentLevelByCityId(String cityId);
	       
	/**
	 * 
	 *
	 * 方法说明：获取所在区域列表
	 *
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月27日
	 *
	 */
	public List<Dict> selectAreaCode();    
	      
	/**
	 * 
	 *
	 * 方法说明：根据区域Code获取显示值
	 *
	 * @param code
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月3日
	 *
	 */
	public String getAreaNameByCode(String code);
	
	/**
	 * 
	 *
	 * 方法说明：获取所有
	 *
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月4日
	 *
	 */
	public List<Area> findAllList();  
	
	/**
	 * 
	 *
	 * 方法说明：获取区域版本号，每次修改区域版本+1
	 *
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月4日
	 *
	 */
	public int getAreaVersion();
	
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
	public List<Map<String,Object>> findShopCity(Map<String,Object> parmMap);
	
	/**
	 * 
	 *
	 * 方法说明：根据模板类型查询模板地址
	 *
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月20日
	 *
	 */
	public List<Dict> findMaterialTemp(String tempType);
	
	/**
	 * 方法说明：查找热门城市
	 * @return
	 */
	public List<Area> findHotList();
	
	/**
	 * 方法说明：查找拼音城市
	 * @return
	 */
	public Map<String,String> findPinyinMap();
	
	/**
	 * 方法说明：根据城市名称查找area
	 * @return
	 */
	public Area findAreaByCityName(String cityName);

	/**
	 * 
	 *
	 * 方法说明：根据省Code查询城市列表
	 *
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月23日
	 *
	 */
    public List<Area> findCityByProvinceCode(String provinceCode);

    /**
     * 
     *
     * 方法说明：根据城市Code查询区列表
     *
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月23日
     *
     */
    public List<Area> findRegionByCityCode(String cityCode);
    
    /**
     * 
     *
     * 方法说明：根据id查询Area
     *
     * @return
     *
     * @author 彭俊霖 CreateDate: 2018年04月11日
     *
     */
    public Area findArea(String id);
    
    /**
     * 
     *
     * 方法说明：根据code查询Area
     *
     * @return
     *
     * @author 彭俊霖 CreateDate: 2018年06月07日
     *
     */
    public Area findAreaByCode(String code);
    
    /**
     * 
     *
     * 方法说明：根据省code获取大区名称
     *
     * @param provinceCode
     * @return 集合第一个元素是zoneCode，第二个元素是名称，查询不到省返回空
     *
     * @author 许新龙 CreateDate: 2018年6月4日
     *
     */
    List<String> getAreaNameByProvinceCode(String provinceCode);

    Area get(String parentId);
    

	/**
	 * 方法说明：根据ID集合查多个地址。
	 * @return
	 */
	public List<Area> findAreaListByIds(List<String> ids);
}
