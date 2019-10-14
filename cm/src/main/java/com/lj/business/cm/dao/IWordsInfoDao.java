package com.lj.business.cm.dao;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.domain.WordsInfo;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoApp;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPage;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPageReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWeb;
import com.lj.business.cm.dto.wordsInfo.UpdateWordsInfo;

public interface IWordsInfoDao {
    int deleteByPrimaryKey(String code);

    int insert(WordsInfo record);

    int insertSelective(WordsInfo record);

    WordsInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WordsInfo record);

    int updateByPrimaryKey(WordsInfo record);

    /**
	 * 
	 * 方法说明：话术选择
	 *
	 * @param findWordsInfoApp
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	List<FindWordsInfoAppReturn> wordsSelect(FindWordsInfoApp findWordsInfoApp);

	List<FindWordsInfoPageReturn> findWordsInfoPage(FindWordsInfoPage findWordsInfoPage);

	int findWordsInfoPageCount(FindWordsInfoPage findWordsInfoPage);

	/**
	 * 
	 *
	 * 方法说明：话术搜索-app
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoApp
	 * @return
	 */
	List<FindWordsInfoAppReturn> wordsSearch(FindWordsInfoApp findWordsInfoApp);

	/**
	 * 
	 *
	 * 方法说明：话术-更多-web
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoWeb
	 * @return
	 */
	List<FindWordsInfoReturn> moreWords(FindWordsInfoWeb findWordsInfoWeb);

	/**
	 * 
	 *
	 * 方法说明：查询默认话术数量
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-12
	 * 
	 * @param merchantNo
	 * @return
	 */
	Integer findDefaultCount(String memberNoGm);

	/**
	 * 
	 *
	 * 方法说明：根据类型CODE更新类型名称
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-13
	 * 
	 * @param updateWordsInfo
	 * @return
	 */
	void updateTypeName(UpdateWordsInfo updateWordsInfo);

	/**
	 * 
	 *
	 * 方法说明：同步删除属于该话术类型的所有话术信息
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-13
	 * 
	 * @param typeCode
	 * @return
	 */
	void deleteWordsInfoByTypeCode(String typeCode);

	/**
	 * 
	 *
	 * 方法说明：检验话术是否存在
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-17
	 * 
	 * @param code
	 * @return
	 */
	Integer checkWords(String code);

	/**
	 * 
	 *
	 * 方法说明：查询个人话术信息
	 *
	 * @author 
	 *   
	 * CreateDate: 
	 * 
	 * @param 
	 * @return
	 */
	List<FindWordsInfoAppReturn> wordsPersonSearch(FindWordsInfoPage findWordsInfoPage);

	/**
	 * 查询个人默认话术
	 * @param findWordsInfoPage
	 * @return
	 */
	List<FindWordsInfoAppReturn> wordsPersonSelect(FindWordsInfoPage findWordsInfoPage);

	/**
	 * 连表查询默认话术
	 * @param findWordsInfoPage
	 * @return
	 */
	List<FindWordsInfoAppReturn> findDefaultWords(FindWordsInfoPage findWordsInfoPage);

	/**
	 * 连表查询默认话术不限制条数-H5页面专用
	 * @param findWordsInfoPage
	 * @return 
	 */
	List<FindWordsInfoAppReturn> findDefaultWordsH5(FindWordsInfoPage findWordsInfoPage);
	
}