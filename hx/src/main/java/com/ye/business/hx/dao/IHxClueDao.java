package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.HxClue;
import com.ye.business.hx.dto.FindHxCluePage;
import com.ye.business.hx.dto.HxClueDto;
import com.ye.business.hx.dto.params.ClueParams;
import com.ye.business.hx.dto.vo.ClueListVo;

public interface IHxClueDao {
	int deleteByPrimaryKey(String code);

	int insert(HxClue record);

	int insertSelective(HxClue record);

	HxClue selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(HxClue record);

	int updateByPrimaryKey(HxClue record);

	List<HxClueDto> findHxClues(FindHxCluePage findHxCluePage);

	List<HxClueDto> findHxCluePage(FindHxCluePage findHxCluePage);

	int findHxCluePageCount(FindHxCluePage findHxCluePage);

	int clueQuantity(HxClue clue);

	int queryClueCount(ClueParams params);

	List<ClueListVo> queryClueList(ClueParams params);

	/**
	 * 根据工单号查询线索
	 * 
	 * @param code
	 * @return
	 */
	HxClue selectByOrderNo(String orderno);

}