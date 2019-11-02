package com.ye.business.hx.service.impl;

import java.util.ArrayList;
/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;

import com.ye.business.hx.dto.OrthodonticsTemplateDto;
import com.ye.business.hx.dto.OrthodonticsTemplateVo;
import com.ye.business.hx.dto.FindOrthodonticsTemplatePage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IOrthodonticsTemplateDao;
import com.ye.business.hx.domain.OrthodonticsTemplate;
import com.ye.business.hx.service.IOrthodonticsTemplateService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
@Service
public class OrthodonticsTemplateServiceImpl implements IOrthodonticsTemplateService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(OrthodonticsTemplateServiceImpl.class);
	

	@Resource
	private IOrthodonticsTemplateDao orthodonticsTemplateDao;
	
	
	@Override
	public Boolean addOrthodonticsTemplate(
			OrthodonticsTemplateDto orthodonticsTemplateDto) throws TsfaServiceException {
		logger.debug("addOrthodonticsTemplate(AddOrthodonticsTemplate addOrthodonticsTemplate={}) - start", orthodonticsTemplateDto); 

		AssertUtils.notNull(orthodonticsTemplateDto);
		try {
			OrthodonticsTemplate orthodonticsTemplate = new OrthodonticsTemplate();
			//add数据录入
			OrthodonticsTemplate parent = orthodonticsTemplateDao.selectByPrimaryKey(orthodonticsTemplateDto.getParentCode());
			//第四层只允许建模板
			if(parent.getLevelCode()==3&&orthodonticsTemplateDto.getType()==1) {
				return false;
			}
			orthodonticsTemplate.setCode(orthodonticsTemplateDto.getCode());
			orthodonticsTemplate.setName(orthodonticsTemplateDto.getName());
			if(parent.getParentCodes()==null) {
				orthodonticsTemplate.setParentCode("1");
				orthodonticsTemplate.setParentCodes("1");
			}else {
				orthodonticsTemplate.setParentCode(parent.getCode());
				orthodonticsTemplate.setParentCodes(parent.getParentCodes()+","+parent.getCode());
			}
			orthodonticsTemplate.setParentName(orthodonticsTemplateDto.getParentName());
			orthodonticsTemplate.setOrderNo(orthodonticsTemplateDto.getOrderNo());
			orthodonticsTemplate.setCreater(orthodonticsTemplateDto.getCreater());
			orthodonticsTemplate.setCreateDate(orthodonticsTemplateDto.getCreateDate());
			orthodonticsTemplate.setLevelCode(parent.getLevelCode()+1);
			orthodonticsTemplate.setType(orthodonticsTemplateDto.getType());
			orthodonticsTemplate.setCheck(orthodonticsTemplateDto.getCheck());
			orthodonticsTemplate.setManagement(orthodonticsTemplateDto.getManagement());
			orthodonticsTemplate.setDoctorAdvice(orthodonticsTemplateDto.getDoctorAdvice());
			orthodonticsTemplate.setRemark(orthodonticsTemplateDto.getRemark());
			orthodonticsTemplate.setRemark2(orthodonticsTemplateDto.getRemark2());
			orthodonticsTemplate.setRemark3(orthodonticsTemplateDto.getRemark3());
			orthodonticsTemplate.setRemark4(orthodonticsTemplateDto.getRemark4());
			orthodonticsTemplateDao.insertSelective(orthodonticsTemplate);
			logger.debug("addOrthodonticsTemplate(OrthodonticsTemplateDto) - end - return");
			return true;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增正畸过程模板信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_TEMPLATE_ADD_ERROR,"新增正畸过程模板信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸过程模板信息
	 *
	 * @param findOrthodonticsTemplatePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<OrthodonticsTemplateVo>  findOrthodonticsTemplates(FindOrthodonticsTemplatePage findOrthodonticsTemplatePage)throws TsfaServiceException{
		AssertUtils.notNull(findOrthodonticsTemplatePage);
		List<OrthodonticsTemplateVo> returnList=null;
		List<OrthodonticsTemplateVo> oneList = new ArrayList<>();
		try {
			returnList = orthodonticsTemplateDao.findOrthodonticsTemplates(findOrthodonticsTemplatePage);
			if(returnList!=null) {
				//组装第一层
				for (OrthodonticsTemplateVo oneDto : returnList) {
					if(oneDto.getLevelCode()==1) {
						oneList.add(oneDto);
					}
				}
				if(oneList!=null) {
					//组装第二层
					for (OrthodonticsTemplateVo orthodonticsTemplateVo : oneList) {
						List<OrthodonticsTemplateVo> twoList = new ArrayList<>();
						for(OrthodonticsTemplateVo oneDto : returnList) {
							if(oneDto.getLevelCode()==2&&oneDto.getParentCode().equals(orthodonticsTemplateVo.getCode())) {
								twoList.add(oneDto);
							}
						}
						orthodonticsTemplateVo.setChildren(twoList);
						if(twoList!=null) {
							//组装第三层
							for(OrthodonticsTemplateVo twoVo : twoList) {
								List<OrthodonticsTemplateVo> threeList = new ArrayList<>();
								for(OrthodonticsTemplateVo oneDto : returnList) {
									if(oneDto.getLevelCode()==3&&oneDto.getParentCode().equals(twoVo.getCode())) {
										threeList.add(oneDto);
									}
								}
								orthodonticsTemplateVo.setChildren(threeList);
								if(threeList!=null) {
									//组装第四层
									for(OrthodonticsTemplateVo threeVo : threeList) {
										List<OrthodonticsTemplateVo> fourList = new ArrayList<>();
										for(OrthodonticsTemplateVo oneDto : returnList) {
											if(oneDto.getLevelCode()==4&&oneDto.getParentCode().equals(threeVo.getCode())) {
												fourList.add(oneDto);
											}
										}
										threeVo.setChildren(fourList);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("查找正畸过程模板信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_TEMPLATE_NOT_EXIST_ERROR,"正畸过程模板信息不存在");
		}
		return oneList;
	}
	

	@Override
	public void updateOrthodonticsTemplate(
			OrthodonticsTemplateDto orthodonticsTemplateDto)
			throws TsfaServiceException {
		logger.debug("updateOrthodonticsTemplate(OrthodonticsTemplateDto orthodonticsTemplateDto={}) - start", orthodonticsTemplateDto); 

		AssertUtils.notNull(orthodonticsTemplateDto);
		AssertUtils.notNullAndEmpty(orthodonticsTemplateDto.getCode(),"Code不能为空");
		try {
			OrthodonticsTemplate orthodonticsTemplate = new OrthodonticsTemplate();
			//update数据录入
			orthodonticsTemplate.setCode(orthodonticsTemplateDto.getCode());
			orthodonticsTemplate.setName(orthodonticsTemplateDto.getName());
			orthodonticsTemplate.setParentCode(orthodonticsTemplateDto.getParentCode());
			orthodonticsTemplate.setParentCodes(orthodonticsTemplateDto.getParentCodes());
			orthodonticsTemplate.setParentName(orthodonticsTemplateDto.getParentName());
			orthodonticsTemplate.setOrderNo(orthodonticsTemplateDto.getOrderNo());
			orthodonticsTemplate.setCreater(orthodonticsTemplateDto.getCreater());
			orthodonticsTemplate.setCreateDate(orthodonticsTemplateDto.getCreateDate());
			orthodonticsTemplate.setLevelCode(orthodonticsTemplateDto.getLevelCode());
			orthodonticsTemplate.setType(orthodonticsTemplateDto.getType());
			orthodonticsTemplate.setCheck(orthodonticsTemplateDto.getCheck());
			orthodonticsTemplate.setManagement(orthodonticsTemplateDto.getManagement());
			orthodonticsTemplate.setDoctorAdvice(orthodonticsTemplateDto.getDoctorAdvice());
			orthodonticsTemplate.setRemark(orthodonticsTemplateDto.getRemark());
			orthodonticsTemplate.setRemark2(orthodonticsTemplateDto.getRemark2());
			orthodonticsTemplate.setRemark3(orthodonticsTemplateDto.getRemark3());
			orthodonticsTemplate.setRemark4(orthodonticsTemplateDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(orthodonticsTemplateDao.updateByPrimaryKeySelective(orthodonticsTemplate));
			logger.debug("updateOrthodonticsTemplate(OrthodonticsTemplateDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("正畸过程模板信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_TEMPLATE_UPDATE_ERROR,"正畸过程模板信息更新信息错误！",e);
		}
	}

	

	@Override
	public OrthodonticsTemplateDto findOrthodonticsTemplate(
			OrthodonticsTemplateDto orthodonticsTemplateDto) throws TsfaServiceException {
		logger.debug("findOrthodonticsTemplate(FindOrthodonticsTemplate findOrthodonticsTemplate={}) - start", orthodonticsTemplateDto); 

		AssertUtils.notNull(orthodonticsTemplateDto);
		AssertUtils.notAllNull(orthodonticsTemplateDto.getCode(),"Code不能为空");
		try {
			OrthodonticsTemplate orthodonticsTemplate = orthodonticsTemplateDao.selectByPrimaryKey(orthodonticsTemplateDto.getCode());
			if(orthodonticsTemplate == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.ORTHODONTICS_TEMPLATE_NOT_EXIST_ERROR,"正畸过程模板信息不存在");
			}
			OrthodonticsTemplateDto findOrthodonticsTemplateReturn = new OrthodonticsTemplateDto();
			//find数据录入
			findOrthodonticsTemplateReturn.setCode(orthodonticsTemplate.getCode());
			findOrthodonticsTemplateReturn.setName(orthodonticsTemplate.getName());
			findOrthodonticsTemplateReturn.setParentCode(orthodonticsTemplate.getParentCode());
			findOrthodonticsTemplateReturn.setParentCodes(orthodonticsTemplate.getParentCodes());
			findOrthodonticsTemplateReturn.setParentName(orthodonticsTemplate.getParentName());
			findOrthodonticsTemplateReturn.setOrderNo(orthodonticsTemplate.getOrderNo());
			findOrthodonticsTemplateReturn.setCreater(orthodonticsTemplate.getCreater());
			findOrthodonticsTemplateReturn.setCreateDate(orthodonticsTemplate.getCreateDate());
			findOrthodonticsTemplateReturn.setLevelCode(orthodonticsTemplate.getLevelCode());
			findOrthodonticsTemplateReturn.setType(orthodonticsTemplate.getType());
			findOrthodonticsTemplateReturn.setCheck(orthodonticsTemplate.getCheck());
			findOrthodonticsTemplateReturn.setManagement(orthodonticsTemplate.getManagement());
			findOrthodonticsTemplateReturn.setDoctorAdvice(orthodonticsTemplate.getDoctorAdvice());
			findOrthodonticsTemplateReturn.setRemark(orthodonticsTemplate.getRemark());
			findOrthodonticsTemplateReturn.setRemark2(orthodonticsTemplate.getRemark2());
			findOrthodonticsTemplateReturn.setRemark3(orthodonticsTemplate.getRemark3());
			findOrthodonticsTemplateReturn.setRemark4(orthodonticsTemplate.getRemark4());
			
			logger.debug("findOrthodonticsTemplate(OrthodonticsTemplateDto) - end - return value={}", findOrthodonticsTemplateReturn); 
			return findOrthodonticsTemplateReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找正畸过程模板信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_TEMPLATE_FIND_ERROR,"查找正畸过程模板信息信息错误！",e);
		}


	}

	
	@Override
	public Page<OrthodonticsTemplateDto> findOrthodonticsTemplatePage(
			FindOrthodonticsTemplatePage findOrthodonticsTemplatePage)
			throws TsfaServiceException {
		logger.debug("findOrthodonticsTemplatePage(FindOrthodonticsTemplatePage findOrthodonticsTemplatePage={}) - start", findOrthodonticsTemplatePage); 

		AssertUtils.notNull(findOrthodonticsTemplatePage);
		List<OrthodonticsTemplateDto> returnList=null;
		int count = 0;
		try {
			returnList = orthodonticsTemplateDao.findOrthodonticsTemplatePage(findOrthodonticsTemplatePage);
			count = orthodonticsTemplateDao.findOrthodonticsTemplatePageCount(findOrthodonticsTemplatePage);
		}  catch (Exception e) {
			logger.error("正畸过程模板信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_TEMPLATE_FIND_PAGE_ERROR,"正畸过程模板信息不存在错误.！",e);
		}
		Page<OrthodonticsTemplateDto> returnPage = new Page<OrthodonticsTemplateDto>(returnList, count, findOrthodonticsTemplatePage);

		logger.debug("findOrthodonticsTemplatePage(FindOrthodonticsTemplatePage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public void delete(OrthodonticsTemplateDto orthodonticsTemplateDto) throws TsfaServiceException {
		logger.debug("delete(OrthodonticsTemplateDto orthodonticsTemplateDto = {})-start",orthodonticsTemplateDto);
		try {
			orthodonticsTemplateDao.deleteByPrimaryKey(orthodonticsTemplateDto.getCode());
		} catch (Exception e) {
			logger.error("删除正畸过程模板信息错误");
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_TEMPLATE_DELETE_ERROR,"删除正畸过程模板信息错误！",e);
		}
	} 


}
