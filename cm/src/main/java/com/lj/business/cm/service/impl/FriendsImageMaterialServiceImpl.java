package com.lj.business.cm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IFriendsImageMaterialDao;
import com.lj.business.cm.domain.FriendsImageMaterial;
import com.lj.business.cm.dto.FriendsImageMaterialDto;
import com.lj.business.cm.service.IFriendsImageMaterialService;

/**
 * 
 * 类说明：操作朋友圈图片素材的服务类
 * <p>
 * 详细描述：实现类
 *   
 * @Company: 扬恩科技有限公司
 * @author 李端强
 *   
 * CreateDate: 2017年12月20日
 */
@Service
public class FriendsImageMaterialServiceImpl implements IFriendsImageMaterialService{
	private static final Logger logger = LoggerFactory.getLogger(FriendsImageMaterialServiceImpl.class);
	@Resource
	IFriendsImageMaterialDao friendsImageMaterialDao;

	@Override
	public int addFriendsImageMaterial(FriendsImageMaterialDto dto) {
		AssertUtils.notNull(dto);
		AssertUtils.notNull(dto.getMerchantNo(),"朋友圈图片素材新增商户编号不能为空");
		AssertUtils.notNull(dto.getTitle(),"朋友圈图片素材新增主题不能为空");
		AssertUtils.notNull(dto.getContent(),"朋友圈图片素材新增内容描述不能为空");
		try {
			FriendsImageMaterial record =  new FriendsImageMaterial();
			BeanUtils.copyProperties(dto, record);
			return friendsImageMaterialDao.insert(record);
		} catch (Exception e) {
			logger.error("新增朋友圈图片素材异常:",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_ADD_ERROR,"新增朋友圈图片素材表信息错误！",e); 
		}
	}

	@Override
	public int updateFriendsImageMaterial(FriendsImageMaterialDto dto) {
		AssertUtils.notNull(dto);
		AssertUtils.notNull(dto.getCode(),"朋友圈图片素材修改,主键不能为空");
		try {
			FriendsImageMaterial record =  new FriendsImageMaterial();
			BeanUtils.copyProperties(dto, record);
			return friendsImageMaterialDao.updateByPrimaryKeySelective(record);
		} catch (BeansException e) {
			logger.error("修改朋友圈图片素材异常:",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_UPDATE_ERROR,"修改朋友圈图片素材表信息错误！",e);
		}
	}

	@Override
	public int delFriendsImageMaterial(FriendsImageMaterialDto dto) {
		AssertUtils.notNull(dto);
		AssertUtils.notNull(dto.getCode(),"朋友圈图片素材删除,主键不能为空");
		AssertUtils.notNull(dto.getDeleteFlag(),"朋友圈图片素材删除,删除标识不能为空");
		try {
			FriendsImageMaterial record =  new FriendsImageMaterial();
			BeanUtils.copyProperties(dto, record);
			return friendsImageMaterialDao.updateByPrimaryKeySelective(record);
		} catch (BeansException e) {
			logger.error("删除朋友圈图片素材异常:",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_DEL_ERROR,"删除朋友圈图片素材表信息错误！",e);
		}
	}

	@Override
	public Page<FriendsImageMaterialDto> findFriendsImageMaterialPage(FriendsImageMaterialDto dto) {
		AssertUtils.notNull(dto);
		long count = 0 ;
		Page<FriendsImageMaterialDto> retPage = null;
		try {
			List<FriendsImageMaterialDto> retList = friendsImageMaterialDao.findFriendsImageMaterialPage(dto);
			count = friendsImageMaterialDao.findFriendsImageMaterialPageCount(dto);
			if(count>0) {
				retPage = new Page<FriendsImageMaterialDto>(retList,count,dto);
			}else {
				retPage = new Page<FriendsImageMaterialDto>(new ArrayList<FriendsImageMaterialDto>(),0,dto);//空
			}
		} catch (Exception e) {
			logger.error("查询朋友圈图片素材异常",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_FIND_ERROR,"查询朋友圈图片素材表信息错误！",e);
		}
		return retPage;
	}

	@Override
	public FriendsImageMaterialDto findFriendsImageMaterialByKey(FriendsImageMaterialDto dto) {
		AssertUtils.notNull(dto);
		FriendsImageMaterialDto retdto = new FriendsImageMaterialDto();
		try {
			FriendsImageMaterial retBean = friendsImageMaterialDao.selectByPrimaryKey(dto.getCode());//主键单条查询
			retdto = new FriendsImageMaterialDto();
			if(retBean!=null) {
				BeanUtils.copyProperties(retBean, retdto);
			}
		} catch (BeansException e) {
			logger.error("查询朋友圈图片素材异常",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_FIND_ERROR,"查询朋友圈图片素材表信息错误！",e);
		}
		return retdto;
	}

}
