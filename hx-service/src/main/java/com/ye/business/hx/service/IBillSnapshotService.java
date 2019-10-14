package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.BillSnapshotDto;
import com.ye.business.hx.dto.FindBillSnapshotPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
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
 * CreateDate: 2019.02.19
 */
public interface IBillSnapshotService {
	
	/**
	 * 
	 *
	 * 方法说明：添加账单快照信息
	 *
	 * @param billSnapshotDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addBillSnapshot(BillSnapshotDto billSnapshotDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找账单快照信息
	 *
	 * @param findBillSnapshot
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public BillSnapshotDto findBillSnapshot(BillSnapshotDto billSnapshotDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询账单快照信息
	 *
	 * @param findBillSnapshotPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<BillSnapshotDto>  findBillSnapshots(FindBillSnapshotPage findBillSnapshotPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改账单快照信息
	 *
	 * @param updateBillSnapshot
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateBillSnapshot(BillSnapshotDto billSnapshotDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询账单快照信息
	 *
	 * @param findBillSnapshotPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<BillSnapshotDto> findBillSnapshotPage(FindBillSnapshotPage findBillSnapshotPage) throws TsfaServiceException;
	

}
