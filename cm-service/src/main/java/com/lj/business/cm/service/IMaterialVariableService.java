package com.lj.business.cm.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.business.cm.domain.MaterialVariable;
import com.lj.business.cm.dto.MaterialVariableDto;

/**
 * 
 * 类说明：操作朋友圈素材变量表的服务类
 * <p>
 * 详细描述：MATERIAL_VARIABLE
 *   
 * @Company: 扬恩科技有限公司
 * @author 李端强
 *   
 * CreateDate: 2017年12月21日
 */
public interface IMaterialVariableService {
	
	/**
	 * 方法说明：新增一条朋友圈素材变量
	 * @param dto
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	public int addMaterialVariable(MaterialVariableDto dto);
	/**
	 * 方法说明：修改一条朋友圈素材变量
	 * @param dto
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	public int updateMaterialVariable(MaterialVariableDto dto);
	/**
	 * 方法说明：删除一条朋友圈素材变量
	 * @param dto
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	public int delMaterialVariable(MaterialVariableDto dto);
	
	/**
	 * 方法说明：分页查询朋友圈素材变量MaterialVariableDto集合
	 * @param dto
	 * @return Page<MaterialVariableDto>
	 *
	 * @author 李端强 CreateDate: 2017年12月20日
	 *
	 */
	public Page<MaterialVariableDto> findMaterialVariablePage(MaterialVariableDto dto);
	
	/**
     *
     * 方法说明：查询素材变量MaterialVariableDto集合
     * @param dto
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
    */
	public List<MaterialVariableDto> findMaterialVariableList(MaterialVariableDto dto);
	
	/**
	 *
	 * 方法说明：查询素材变量MaterialVariableDto
	 * @param code 主键
	 * @return
	 * @author 李端强 CreateDate: 2017年12月21日
	 *
	 */
	public MaterialVariableDto findMaterialVariableByKey(String code);
}
