package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.domain.FriendsImageMaterial;
import com.lj.business.cm.dto.FriendsImageMaterialDto;

/**
 * 
 * 类说明：操作朋友圈图片素材DAO
 * <p>
 * 详细描述：
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2017年12月20日
 */
public interface IFriendsImageMaterialDao {
    /**
     * 方法说明：根据主键删除
     * @param code
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
     */
    int deleteByPrimaryKey(String code);

    /**
     * 方法说明：新增朋友圈素材-全字段
     * @param record
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
     *
     */
    int insert(FriendsImageMaterial record);

    /**
     * 方法说明：新增朋友圈素材-空字段放弃插入
     * @param record
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
     */
    int insertSelective(FriendsImageMaterial record);

    /**
     * 方法说明：根据主键查询
     * @param code
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
     *
     */
    FriendsImageMaterial selectByPrimaryKey(String code);

    /**
     *
     * 方法说明：根据主键对各个字段为非空时修改
     *
     * @param record FriendsImageMaterial
     * @return
     *
     * @author 李端强 CreateDate: 2017年12月20日
     *
     */
    int updateByPrimaryKeySelective(FriendsImageMaterial record);

    /**
    *
    * 方法说明：根据主键对各个字段进行修改
    *
    * @param record FriendsImageMaterial
    * @return
    *
    * @author 李端强 CreateDate: 2017年12月20日
    *
    */
    int updateByPrimaryKey(FriendsImageMaterial record);
    
    /**
     *
     * 方法说明：分页查询FriendsImageMaterialDto集合
     * @param dto
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
     */
    List<FriendsImageMaterialDto> findFriendsImageMaterialPage(FriendsImageMaterialDto dto);
    
    /**
     *
     * 方法说明：条件筛选FriendsImageMaterialDto总数
     * @param dto
     * @return
     * @author 李端强 CreateDate: 2017年12月20日
     *
     */
    Long findFriendsImageMaterialPageCount(FriendsImageMaterialDto dto);
}