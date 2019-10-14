package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.HxClueOrder;
import com.ye.business.hx.dto.FindHxClueOrderPage;
import com.ye.business.hx.dto.HxClueOrderDto;
import com.ye.business.hx.dto.params.ClueParams;
import com.ye.business.hx.dto.vo.ClueListVo;

public interface IHxClueOrderDao {
    int deleteByPrimaryKey(String code);

    int insert(HxClueOrder record);

    int insertSelective(HxClueOrder record);

    HxClueOrder selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(HxClueOrder record);

    int updateByPrimaryKey(HxClueOrder record);
    
    List<HxClueOrderDto> findHxClueOrders(FindHxClueOrderPage findHxClueOrderPage);
    
    List<HxClueOrderDto> findHxClueOrderPage(FindHxClueOrderPage findHxClueOrderPage);
    
    int findHxClueOrderPageCount(FindHxClueOrderPage findHxClueOrderPage);
    
    HxClueOrder selectByClueCode(String code);
    
	int queryAcceptListCount(ClueParams params);

	List<ClueListVo> queryAcceptList(ClueParams params);
	
	/**
	 * 查询播报
	 * @return
	 */
	List<ClueListVo> broadCastList(String status);
	
	/**
	 * 获取今日客户推荐数量
	 * @return
	 */
	int queryClientCount(ClueParams params);
}