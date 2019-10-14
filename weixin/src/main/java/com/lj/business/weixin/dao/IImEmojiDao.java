package com.lj.business.weixin.dao;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.domain.ImEmoji;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPageReturn;

public interface IImEmojiDao {
    int deleteByPrimaryKey(String code);

    int insert(ImEmoji record);

    int insertSelective(ImEmoji record);

    ImEmoji selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ImEmoji record);

    int updateByPrimaryKey(ImEmoji record);
    
    /**
     * 
     *
     * 方法说明：查询IM表情列表
     *
     * @param parmMap
     * @return
     *
     * @author 彭俊霖 CreateDate: 2017年11月01日
     *
     */
    List<FindImEmojiPageReturn> findImEmojiPage(FindImEmojiPage findImEmojiPage);
    /**
     * 
     *
     * 方法说明：查询IM表情数量
     *
     * @param parmMap
     * @return
     *
     * @author 彭俊霖 CreateDate: 2017年11月01日
     *
     */
	int findImEmojiPageCount(FindImEmojiPage findImEmojiPage);

	/**
	 *
	 * 方法说明：查询该表情包表情所有显示序号
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月20日
	 * @param packageCode 
	 *
	 */
	List<Integer> findAllShowIndex(String packageCode);
}