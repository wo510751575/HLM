package com.lj.business.member.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindGmLabelPage;
import com.lj.business.member.dto.GmLabelDto;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 *         CreateDate: 2017.12.14
 */
public interface IGmLabelService {

	/**
	 * 
	 *
	 * 方法说明：添加个人标签信息
	 *
	 * @param gmLabelDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public GmLabelDto addGmLabel(GmLabelDto gmLabelDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找个人标签信息
	 *
	 * @param findGmLabel
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public GmLabelDto findGmLabel(GmLabelDto gmLabelDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询个人标签信息
	 *
	 * @param findGmLabelPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<GmLabelDto> findGmLabels(FindGmLabelPage findGmLabelPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改个人标签信息
	 *
	 * @param updateGmLabel
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateGmLabel(GmLabelDto gmLabelDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询个人标签信息
	 *
	 * @param findGmLabelPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<GmLabelDto> findGmLabelPage(FindGmLabelPage findGmLabelPage) throws TsfaServiceException;

	/**
	 * 
	 * @Title: delGmLabel @Description: 删除 @param: @param gmLabelDto @param: @throws
	 * TsfaServiceException @return: void @throws
	 */
	public void delGmLabel(GmLabelDto gmLabelDto) throws TsfaServiceException;
}
