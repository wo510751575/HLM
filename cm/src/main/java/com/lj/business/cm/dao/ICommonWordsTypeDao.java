package com.lj.business.cm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.domain.WordsType;
import com.lj.business.cm.dto.wordsInfo.FindWordsAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoApp;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWeb;
import com.lj.business.cm.dto.wordsType.FindWordsTypePage;
import com.lj.business.cm.dto.wordsType.FindWordsTypePageReturn;
import com.lj.business.cm.dto.wordsType.FindWordsTypeSelectReturn;

public interface ICommonWordsTypeDao {
    int deleteByPrimaryKey(String code);

    int insert(WordsType record);

    int insertSelective(WordsType record);

    WordsType selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WordsType record);

    int updateByPrimaryKey(WordsType record);

    /**
	 * 
	 *
	 * 方法说明：话术管理-app
	 *
	 * @param findWordsInfoApp
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	List<FindWordsAppReturn> findWords(FindWordsInfoApp findWordsInfoApp);

	/**
	 * 
	 *
	 * 方法说明：话术管理-web-更多-查询商户话术类型
	 *
	 * @param findWordsInfoApp
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	List<FindWordsTypeSelectReturn> findWordsTypes(FindWordsInfoWeb findWordsInfoWeb);

	List<FindWordsTypePageReturn> findWordsTypePage(FindWordsTypePage findWordsTypePage);

	int findWordsTypePageCount(FindWordsTypePage findWordsTypePage);

	/**
	 * 
	 *
	 * 方法说明：话术类型-类型名称重复校验
	 *
	 * @param merchantNo
	 * @param typeName
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月11日
	 *
	 */
	int hasTypeName(@Param("merchantNo")String merchantNo,@Param("typeName")String typeName);
	
	/**
	 * 
	 *
	 * 方法说明：话术类型-排序重复校验
	 *
	 * @param merchantNo
	 * @param seq
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月11日
	 *
	 */
	int hasSeq(@Param("merchantNo")String merchantNo,@Param("seq")Integer seq);

	/**
	 * 方法说明: 增加类型数量
	 * @param typeCode
	 * @param i
	 * @return
	 */
	int incrementTypeCountByPrimaryKey(@Param("code") String code, @Param("increment") Integer increment);


}