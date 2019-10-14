package com.ye.business.ad.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.ad.dto.AdvertiseDto;
import com.ye.business.ad.dto.AdvertiseTaskDto;
import com.ye.business.ad.dto.FindAdvertisePage;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author sjiying
 * 
 * 
 * CreateDate: 2019.04.18
 */
public interface IAdvertiseService {

	/**
	 * 
	 *
	 * 方法说明：添加广告信息
	 *
	 * @param advertiseDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addAdvertise(AdvertiseDto advertiseDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找广告信息
	 *
	 * @param findAdvertise
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public AdvertiseDto findAdvertise(AdvertiseDto advertiseDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告信息
	 *
	 * @param findAdvertisePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<AdvertiseDto> findAdvertises(FindAdvertisePage findAdvertisePage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改广告信息
	 *
	 * @param updateAdvertise
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateAdvertise(AdvertiseDto advertiseDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询广告信息
	 *
	 * @param findAdvertisePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<AdvertiseDto> findAdvertisePage(FindAdvertisePage findAdvertisePage) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：刪除
	 *
	 * @param code
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年5月8日
	 */
	public void removeByPrimaryKey(String code) throws TsfaServiceException;

	public List<AdvertiseDto> findOthersRandom(FindAdvertisePage findAdvertisePage) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：根据用户获取随机广告记录
	 * </p>
	 * 1.第一条返回所属商户的自助广告；
	 * </p>
	 * 2.其余随机返回其他商户的付费广告；
	 * </p>
	 * 3.插入广告跟踪记录（当天第一次有效）；
	 * </p>
	 * 4.扣除广告豆记录（当天第一次有效）；
	 * </p>
	 * 
	 * @param findAdvertisePage
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月12日
	 */
	public List<AdvertiseDto> findRandomRecordList(FindAdvertisePage findAdvertisePage) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：批量下架广告
	 *
	 * @param batchNum
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月11日
	 */
	public void batchUpdateAdvertiseForUpOrDown(String batchNum) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找广告信息，新增广告根据记录，扣除广告豆等
	 *
	 * @param findAdvertise
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public AdvertiseDto findAdvertiseForView(AdvertiseDto advertiseDto) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：上下架方法
	 *
	 * @param record
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月18日
	 */
	public void updateForTask(AdvertiseTaskDto record) throws TsfaServiceException;
}
