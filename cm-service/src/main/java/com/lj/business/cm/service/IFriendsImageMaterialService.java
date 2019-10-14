package com.lj.business.cm.service;

import com.lj.base.core.pagination.Page;
import com.lj.business.cm.dto.FriendsImageMaterialDto;

/**
 * 
 * 类说明：操作朋友圈图片素材的服务类
 * <p>
 * 详细描述：FRIENDS_IMAGE_MATERIAL
 *   
 * @Company: 扬恩科技有限公司
 * @author 李端强
 *   
 * CreateDate: 2017年12月20日
 */
public interface IFriendsImageMaterialService {
	/**
	 * 方法说明：新增一条朋友圈图片素材
	 * @param dto
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	public int addFriendsImageMaterial(FriendsImageMaterialDto dto);
	/**
	 * 方法说明：修改一条朋友圈图片素材
	 * @param dto
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	public int updateFriendsImageMaterial(FriendsImageMaterialDto dto);
	/**
	 * 方法说明：逻辑删除一条朋友圈图片素材
	 * @param dto
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	public int delFriendsImageMaterial(FriendsImageMaterialDto dto);
	
	/**
	 *
	 * 方法说明：分页查询FriendsImageMaterialDto集合
	 *
	 * @param dto
	 * @return Page<FriendsImageMaterialDto>
	 *
	 * @author 李端强 CreateDate: 2017年12月20日
	 *
	 */
	public Page<FriendsImageMaterialDto> findFriendsImageMaterialPage(FriendsImageMaterialDto dto);
	
	/**
	 *
	 * 方法说明：主键单条查询FriendsImageMaterialDto
	 *
	 * @param dto
	 * @return FriendsImageMaterialDto
	 *
	 * @author 李端强 CreateDate: 2017年12月20日
	 *
	 */
	public FriendsImageMaterialDto findFriendsImageMaterialByKey(FriendsImageMaterialDto dto);
}
