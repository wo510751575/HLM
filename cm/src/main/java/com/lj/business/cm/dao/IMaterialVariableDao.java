package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.domain.MaterialVariable;
import com.lj.business.cm.dto.MaterialVariableDto;
/**
 * 类说明：操作素材变量表DAO
 * <p>
 * 详细描述：
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2017年12月20日
 */
public interface IMaterialVariableDao {
	/**
     * 方法说明：根据主键删除
     * @param code
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
     */
    int deleteByPrimaryKey(String code);

    /**
     * 方法说明：新增素材变量表记录-全字段
     * @param record
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
     *
     */
    int insert(MaterialVariable record);

    /**
     * 方法说明：新增新增素材变量表记录-空字段放弃插入
     * @param record
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
     */
    int insertSelective(MaterialVariable record);

    /**
     * 方法说明：根据主键查询
     * @param code
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
     *
     */
    MaterialVariable selectByPrimaryKey(String code);

    /**
    *
    * 方法说明：根据主键对各个字段为非空时修改
    *
    * @param record MaterialVariable
    * @return
    *
    * @author 李端强 CreateDate: 2017年12月20日
    *
    */
    int updateByPrimaryKeySelective(MaterialVariable record);

    /**
    *
    * 方法说明：根据主键对各个字段进行修改
    *
    * @param record MaterialVariable
    * @return
    *
    * @author 李端强 CreateDate: 2017年12月20日
    *
    */
    int updateByPrimaryKey(MaterialVariable record);
    
    /**
    *
    * 方法说明：分页查询MaterialVariableDto集合
    * @param dto
    * @return
    * @author 李端强 CreateDate: 2017年12月20日
    */
   List<MaterialVariableDto> findMaterialVariablePage(MaterialVariableDto dto);
   
   /**
    *
    * 方法说明：条件筛选MaterialVariableDto总数
    * @param dto
    * @return
    * @author 李端强 CreateDate: 2017年12月20日
    *
    */
   Long findMaterialVariablePageCount(MaterialVariableDto dto);
   
   /**
   *
   * 方法说明：查询素材变量MaterialVariableDto集合
   * @param dto
   * @return
   * @author 李端强 CreateDate: 2017年12月20日
   */
  List<MaterialVariableDto> findMaterialVariableList(MaterialVariableDto dto);
   
}