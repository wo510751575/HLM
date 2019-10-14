package com.lj.business.cm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IMaterialVariableDao;
import com.lj.business.cm.domain.MaterialVariable;
import com.lj.business.cm.dto.MaterialVariableDto;
import com.lj.business.cm.service.IMaterialVariableService;
/**
 * 
 * 类说明：操作朋友圈素材变量表的服务类实现
 * <p>
 * 详细描述：MATERIAL_VARIABLE
 *   
 * @Company: 扬恩科技有限公司
 * @author 李端强
 *   
 * CreateDate: 2017年12月21日15:46:50
 */
@Service
public class MaterialVariableServiceImpl implements IMaterialVariableService {
	private static final Logger logger = LoggerFactory.getLogger(MaterialVariableServiceImpl.class);
	@Resource
	private IMaterialVariableDao materialVariableDao;

	@Override
	public Page<MaterialVariableDto> findMaterialVariablePage(MaterialVariableDto dto) {
		AssertUtils.notNull(dto);
		long count = 0 ;
		Page<MaterialVariableDto> retPage = null;
		try {
			List<MaterialVariableDto> retList = materialVariableDao.findMaterialVariablePage(dto);
			count = materialVariableDao.findMaterialVariablePageCount(dto);
			if(count>0) {
				retPage = new Page<MaterialVariableDto>(retList,count,dto);
			}else {
				retPage = new Page<MaterialVariableDto>(new ArrayList<MaterialVariableDto>(),0,dto);//空
			}
		} catch (Exception e) {
			logger.error("查询朋友圈素材变量异常",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_VARIABLE_FIND_ERROR,"查询朋友圈素材变量表信息错误！",e);
		}
		return retPage;
	}

	@Override
	public int addMaterialVariable(MaterialVariableDto dto) {
		AssertUtils.notNull(dto);
		AssertUtils.notNull(dto.getMerchantNo(),"朋友圈素材变量新增商户编号不能为空");
		AssertUtils.notNull(dto.getVarName(),"朋友圈素材变量新增变量名不能为空");
		AssertUtils.notNull(dto.getVarContent(),"朋友圈素材变量新增变量内容不能为空");
		try {
			MaterialVariable record =  new MaterialVariable();
			BeanUtils.copyProperties(dto, record);
			return materialVariableDao.insert(record);
		} catch (Exception e) {
			logger.error("新增朋友圈素材变量异常:",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_VARIABLE_ADD_ERROR,"新增朋友圈素材变量表信息错误！",e); 
		}
	}

	@Override
	public int updateMaterialVariable(MaterialVariableDto dto) {
		AssertUtils.notNull(dto);
		AssertUtils.notNull(dto.getCode(),"朋友圈素材变量修改,主键不能为空");
		try {
			MaterialVariable record =  new MaterialVariable();
			BeanUtils.copyProperties(dto, record);
			record.setUpateDate(new Date());//更新修改时间
			return materialVariableDao.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			logger.error("修改朋友圈素材变量异常:",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_VARIABLE_UPDATE_ERROR,"修改朋友圈素材变量表信息错误！",e);
		}
	}

	@Override
	public int delMaterialVariable(MaterialVariableDto dto) {
		AssertUtils.notNull(dto);
		AssertUtils.notNull(dto.getCode(),"朋友圈素材变量删除,主键不能为空");
		try {
			return materialVariableDao.deleteByPrimaryKey(dto.getCode());
		} catch (Exception e) {
			logger.error("删除朋友圈素材变量异常:",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_VARIABLE_DEL_ERROR,"删除朋友圈素材变量表信息错误！",e);
		}
	}

	@Override
	public List<MaterialVariableDto> findMaterialVariableList(MaterialVariableDto dto) {
		AssertUtils.notNull(dto);
		List<MaterialVariableDto> retList = null;
		try {
			retList = materialVariableDao.findMaterialVariableList(dto);
		} catch (Exception e) {
			logger.error("查询朋友圈素材变量异常",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_VARIABLE_FIND_ERROR,"查询朋友圈素材变量表信息错误！",e);
		}
		return retList;
	}

	@Override
	public MaterialVariableDto findMaterialVariableByKey(String code) {
		AssertUtils.notNull(code,"朋友圈素材变量单条查询,主键不能为空");
		MaterialVariable retBean = materialVariableDao.selectByPrimaryKey(code);
		MaterialVariableDto retDto = new MaterialVariableDto();
		if(retBean!=null) {
			BeanUtils.copyProperties(retBean, retDto);
			return retDto;
		}
		return retDto;
	}

}
