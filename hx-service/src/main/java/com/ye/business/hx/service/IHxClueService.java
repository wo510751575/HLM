package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.domain.HxClue;
import com.ye.business.hx.dto.FindHxCluePage;
import com.ye.business.hx.dto.HxClueDto;
import com.ye.business.hx.dto.params.ClueParams;
import com.ye.business.hx.dto.vo.ClueListVo;
import com.ye.business.hx.dto.vo.shopServerListVo;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 *         CreateDate: 2019.02.19
 */
public interface IHxClueService {

	/**
	 * 
	 *
	 * 方法说明：添加线索信息
	 *
	 * @param hxClueDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addHxClue(HxClueDto hxClueDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找线索信息
	 *
	 * @param findHxClue
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public HxClueDto findHxClue(HxClueDto hxClueDto)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询线索信息
	 *
	 * @param findHxCluePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<HxClueDto> findHxClues(FindHxCluePage findHxCluePage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改线索信息
	 *
	 * @param updateHxClue
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateHxClue(HxClueDto hxClueDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询线索信息
	 *
	 * @param findHxCluePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<HxClueDto> findHxCluePage(FindHxCluePage findHxCluePage)
			throws TsfaServiceException;

	/**
	 * 客户线索数量
	 * 
	 * @return
	 */
	public Map<String, Object> clueQuantity();

	/**
	 * 客户线索列表
	 * 
	 * @param params
	 * @return
	 */
	public Page<ClueListVo> list(ClueParams params);

	/**
	 * 已接诊客户列表
	 * 
	 * @param params
	 * @return
	 */
	public Page<ClueListVo> acceptlist(ClueParams params);

	/**
	 * 店铺服务列表
	 * 
	 * @param params
	 * @return
	 */
	public List<shopServerListVo> shopserverlist(ClueParams params);

	/**
	 * 接诊
	 * 
	 * @param params
	 * @return
	 */
	public String visiting(ClueParams params);

	/**
	 * 到店
	 * 
	 * @param params
	 * @return
	 */
	public String toshop(ClueParams params);

	/**
	 * 轮播
	 * 
	 * @return
	 */
	public List<ClueListVo> broadcast();

	/**
	 * 工单 调添加/编辑线索
	 * 
	 * @param clue
	 * @return
	 */
	public void createClue(HxClue clue);

	/**
	 * 工单 调查询店铺服务
	 * 
	 * @param params
	 * @return
	 */
	public Page<shopServerListVo> clinicServices(ClueParams params);
	
	/**
	 * 修改线索状态（转跟进）
	 * @param orderno
	 */
	public void upstatus(String orderno);
	
	/**
	 * 派单（工单）
	 * @param params
	 */
	public void visiting_order(ClueParams params);
	
	/**
	 * 确认/取消到店
	 * @param params
	 */
	public void confirmorcancel(ClueParams params);

	/**
	 * 推荐客户数量
	 * @return
	 */
	public int clientnum(ClueParams params);
}
