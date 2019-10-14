package com.lj.business.weixin.dao;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.domain.ImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackagePage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackagePageReturn;
import com.lj.business.weixin.dto.imemoji.NewEmojiPackageReturn;

public interface IImEmojiPackageDao {
    int deleteByPrimaryKey(String code);

    int insert(ImEmojiPackage record);

    int insertSelective(ImEmojiPackage record);

    ImEmojiPackage selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ImEmojiPackage record);

    int updateByPrimaryKey(ImEmojiPackage record);
    
    /**
     * 
     *
     * 方法说明：查询IM表情包列表
     *
     * @param parmMap
     * @return
     *
     * @author 彭俊霖 CreateDate: 2017年11月01日
     *
     */
    List<FindImEmojiPackagePageReturn> findImEmojiPackagePage(FindImEmojiPackagePage findImEmojiPackagePage);
    /**
     * 
     *
     * 方法说明：查询IM表情包数量
     *
     * @param parmMap
     * @return
     *
     * @author 彭俊霖 CreateDate: 2017年11月01日
     *
     */
	int findImEmojiPackagePageCount(FindImEmojiPackagePage findImEmojiPackagePage);

	/**
     * 
     *
     * 方法说明：APP查找表情包信息
     *
     * @param version
     * @return
     *
     * @author 彭俊霖 CreateDate: 2017年11月02日
     *
     */
	List<NewEmojiPackageReturn> findNewEmojiPackage(Long version);
	
	/**
	 * 
	 *
	 * 方法说明：查询表情包最大版本号
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月2日
	 *
	 */
	public long findMaxVersion();

	/**
	 *
	 * 方法说明：查询表情包所有显示序号
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月20日
	 *
	 */
	List<Integer> findAllShowIndex(); 
}