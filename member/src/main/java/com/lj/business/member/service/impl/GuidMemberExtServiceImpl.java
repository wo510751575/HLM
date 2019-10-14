package com.lj.business.member.service.impl;

import java.util.HashSet;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IGuidMemberExtDao;
import com.lj.business.member.domain.GuidMemberExt;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberExtPage;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.GuidMemberExtDto;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.service.IGuidMemberExtService;
import com.lj.business.member.service.IGuidMemberService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 林进权
 * 
 * 
 * CreateDate: 2017-08-22
 */
@Service
public class GuidMemberExtServiceImpl implements IGuidMemberExtService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GuidMemberExtServiceImpl.class);
	

	@Resource
	private IGuidMemberExtDao guidMemberExtDao;
	
	@Resource
	private IGuidMemberService guidMemberService;

//	@Resource
//	private IShopService shopService;
	
	@Override
	public void addGuidMemberExt(
			GuidMemberExtDto guidMemberExtDto) throws TsfaServiceException {
		logger.debug("addGuidMemberExt(AddGuidMemberExt addGuidMemberExt={}) - start", guidMemberExtDto); 

		AssertUtils.notNull(guidMemberExtDto);
		try {
//			FindShopDto findShopDto = new FindShopDto();
//			findShopDto.setShopNoMerchant(guidMemberExtDto.getShopNoMerchant());
//			findShopDto.setMemberNoMerchant(guidMemberExtDto.getMemberNoMerchant());
//			FindShopPageReturn findShopPageReturn = shopService.findShopByShopNoMerchant(findShopDto);
			
			GuidMemberExt guidMemberExt = new GuidMemberExt();
			//add数据录入
			guidMemberExt.setCode(GUID.getPreUUID());
			guidMemberExt.setMemberNoMerchant(guidMemberExtDto.getMemberNoMerchant());
			guidMemberExt.setMemberNameMerchant(guidMemberExtDto.getMemberNameMerchant());
//			guidMemberExt.setShopNo(findShopPageReturn.getShopNo());
//			guidMemberExt.setShopName(findShopPageReturn.getShopName());
			guidMemberExt.setShopNoMerchant(guidMemberExtDto.getShopNoMerchant());
			guidMemberExt.setMemberName(guidMemberExtDto.getMemberName());
			guidMemberExt.setJobNum(guidMemberExtDto.getJobNum());
			guidMemberExt.setBirthday(guidMemberExtDto.getBirthday());
			guidMemberExt.setMobile(guidMemberExtDto.getMobile());
			guidMemberExt.setAge(guidMemberExtDto.getAge());
			guidMemberExt.setGender(guidMemberExtDto.getGender());
			guidMemberExt.setNoWx(guidMemberExtDto.getNoWx());
			guidMemberExt.setNoWxPsw(guidMemberExtDto.getNoWxPsw());
			guidMemberExt.setWorkDate(guidMemberExtDto.getWorkDate());
			guidMemberExt.setPosition(guidMemberExtDto.getPosition());
			guidMemberExt.setAreaName(guidMemberExtDto.getAreaName());
			guidMemberExt.setStatus(guidMemberExtDto.getStatus());
			guidMemberExt.setCreateTime(guidMemberExtDto.getCreateTime());
			guidMemberExt.setUpdateTime(guidMemberExtDto.getUpdateTime());
			guidMemberExtDao.insert(guidMemberExt);
			
			updateSourceGuidMember(guidMemberExtDto);
			logger.debug("addGuidMemberExt(GuidMemberExtDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购开放平台扩展信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_EXT_ADD_ERROR,"新增导购开放平台扩展信息错误！",e);
		}
	}
	
	
	/**
	 * 更新导购源数据
	 * 方法说明：
	 *
	 * @param @param guidMemberExtDto    设定文件 
	 * @return void    返回类型 
	 * @throws Exception
	 *
	 * @author 林进权
	 *         CreateDate: 2018年1月31日
	 */
 	private void updateSourceGuidMember(GuidMemberExtDto guidMemberExtDto) {
 		FindGuidMember findGuidMember = new FindGuidMember();
 		findGuidMember.setMerchantNo(guidMemberExtDto.getMemberNoMerchant());
 		findGuidMember.setMobile(guidMemberExtDto.getMobile());
// 		findGuidMember.setShopNo(guidMemberExtDto.getShopNo());
 		try {
 			FindGuidMemberReturn findGuidMemberReturn  = guidMemberService.findGuidMember(findGuidMember);
 			if(null!=findGuidMemberReturn) {
 	 			UpdateGuidMember updateGuidMember = new UpdateGuidMember();
 	 			BeanUtils.copyProperties(guidMemberExtDto, updateGuidMember, getNullPropertyNames(guidMemberExtDto));
 	 			updateGuidMember.setCode(findGuidMemberReturn.getCode());
 	 			updateGuidMember.setMerchantName(null);
 	 			updateGuidMember.setMerchantNo(null);
// 	 			updateGuidMember.setShopName(null);
// 	 			updateGuidMember.setShopNo(null);
 	 			updateGuidMember.setStatus(null);
 	 			guidMemberService.updateGuidMember(updateGuidMember);
 	 		}
 		} catch (TsfaServiceException e) {
			logger.error("更新导购源数据失败：", e);
		}
 		
	}
 	
 	public static void main(String[] args) {
		
	}
 	
	public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if(org.springframework.util.StringUtils.isEmpty(srcValue)) {
            	emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


	/**
	 * 
	 *
	 * 方法说明：不分页查询导购开放平台扩展信息
	 *
	 * @param findGuidMemberExtPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017年07月10日
	 *
	 */
	public List<GuidMemberExtDto>  findGuidMemberExts(FindGuidMemberExtPage findGuidMemberExtPage)throws TsfaServiceException{
		AssertUtils.notNull(findGuidMemberExtPage);
		List<GuidMemberExtDto> returnList=null;
		try {
//			returnList = guidMemberExtDao.findGuidMemberExts(findGuidMemberExtPage);
		} catch (Exception e) {
			logger.error("查找导购开放平台扩展信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_EXT_NOT_EXIST_ERROR,"导购开放平台扩展信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateGuidMemberExt(
			GuidMemberExtDto guidMemberExtDto)
			throws TsfaServiceException {
		logger.debug("updateGuidMemberExt(GuidMemberExtDto guidMemberExtDto={}) - start", guidMemberExtDto); 

		AssertUtils.notNull(guidMemberExtDto);
		AssertUtils.notNullAndEmpty(guidMemberExtDto.getCode(),"Code不能为空");
		try {
//			FindShopDto findShopDto = new FindShopDto();
//			findShopDto.setShopNoMerchant(guidMemberExtDto.getShopNoMerchant());
//			findShopDto.setMemberNoMerchant(guidMemberExtDto.getMemberNoMerchant());
//			FindShopPageReturn findShopPageReturn = shopService.findShopByShopNoMerchant(findShopDto);
			
			GuidMemberExt guidMemberExt = new GuidMemberExt();
			//update数据录入
			guidMemberExt.setCode(guidMemberExtDto.getCode());
			guidMemberExt.setMemberNoMerchant(guidMemberExtDto.getMemberNoMerchant());
			guidMemberExt.setMemberNameMerchant(guidMemberExtDto.getMemberNameMerchant());
//			guidMemberExt.setShopNo(findShopPageReturn.getShopNo());
//			guidMemberExt.setShopName(findShopPageReturn.getShopName());
			guidMemberExt.setShopNoMerchant(guidMemberExtDto.getShopNoMerchant());
			guidMemberExt.setMemberName(guidMemberExtDto.getMemberName());
			guidMemberExt.setJobNum(guidMemberExtDto.getJobNum());
			guidMemberExt.setBirthday(guidMemberExtDto.getBirthday());
			guidMemberExt.setMobile(guidMemberExtDto.getMobile());
			guidMemberExt.setAge(guidMemberExtDto.getAge());
			guidMemberExt.setGender(guidMemberExtDto.getGender());
			guidMemberExt.setNoWx(guidMemberExtDto.getNoWx());
			guidMemberExt.setNoWxPsw(guidMemberExtDto.getNoWxPsw());
			guidMemberExt.setWorkDate(guidMemberExtDto.getWorkDate());
			guidMemberExt.setPosition(guidMemberExtDto.getPosition());
			guidMemberExt.setAreaName(guidMemberExtDto.getAreaName());
			guidMemberExt.setStatus(guidMemberExtDto.getStatus());
			guidMemberExt.setCreateTime(guidMemberExtDto.getCreateTime());
			guidMemberExt.setUpdateTime(guidMemberExtDto.getUpdateTime());
			AssertUtils.notUpdateMoreThanOne(guidMemberExtDao.updateByPrimaryKeySelective(guidMemberExt));
			
			updateSourceGuidMember(guidMemberExtDto);
			logger.debug("updateGuidMemberExt(GuidMemberExtDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("导购开放平台扩展信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_EXT_UPDATE_ERROR,"导购开放平台扩展信息更新信息错误！",e);
		}
	}

	

	@Override
	public GuidMemberExtDto findGuidMemberExt(
			GuidMemberExtDto guidMemberExtDto) throws TsfaServiceException {
		logger.debug("findGuidMemberExt(FindGuidMemberExt findGuidMemberExt={}) - start", guidMemberExtDto); 

		AssertUtils.notNull(guidMemberExtDto);
		AssertUtils.notAllNull(guidMemberExtDto.getCode(),"Code不能为空");
		try {
			GuidMemberExt guidMemberExt = guidMemberExtDao.selectByPrimaryKey(guidMemberExtDto.getCode());
			if(guidMemberExt == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.GUID_MEMBER_EXT_NOT_EXIST_ERROR,"导购开放平台扩展信息不存在");
			}
			GuidMemberExtDto findGuidMemberExtReturn = new GuidMemberExtDto();
			//find数据录入
			findGuidMemberExtReturn.setCode(guidMemberExt.getCode());
			findGuidMemberExtReturn.setMemberNoMerchant(guidMemberExt.getMemberNoMerchant());
			findGuidMemberExtReturn.setMemberNameMerchant(guidMemberExt.getMemberNameMerchant());
//			findGuidMemberExtReturn.setShopNo(guidMemberExt.getShopNo());
//			findGuidMemberExtReturn.setShopName(guidMemberExt.getShopName());
			findGuidMemberExtReturn.setShopNoMerchant(guidMemberExt.getShopNoMerchant());
			findGuidMemberExtReturn.setMemberName(guidMemberExt.getMemberName());
			findGuidMemberExtReturn.setJobNum(guidMemberExt.getJobNum());
			findGuidMemberExtReturn.setBirthday(guidMemberExt.getBirthday());
			findGuidMemberExtReturn.setMobile(guidMemberExt.getMobile());
			findGuidMemberExtReturn.setAge(guidMemberExt.getAge());
			findGuidMemberExtReturn.setGender(guidMemberExt.getGender());
			findGuidMemberExtReturn.setNoWx(guidMemberExt.getNoWx());
			findGuidMemberExtReturn.setNoWxPsw(guidMemberExt.getNoWxPsw());
			findGuidMemberExtReturn.setWorkDate(guidMemberExt.getWorkDate());
			findGuidMemberExtReturn.setPosition(guidMemberExt.getPosition());
			findGuidMemberExtReturn.setAreaName(guidMemberExt.getAreaName());
			findGuidMemberExtReturn.setStatus(guidMemberExt.getStatus());
			findGuidMemberExtReturn.setCreateTime(guidMemberExt.getCreateTime());
			findGuidMemberExtReturn.setUpdateTime(guidMemberExt.getUpdateTime());
			
			logger.debug("findGuidMemberExt(GuidMemberExtDto) - end - return value={}", findGuidMemberExtReturn); 
			return findGuidMemberExtReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购开放平台扩展信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_EXT_FIND_ERROR,"查找导购开放平台扩展信息信息错误！",e);
		}


	}

	
	@Override
	public Page<GuidMemberExtDto> findGuidMemberExtPage(
			FindGuidMemberExtPage findGuidMemberExtPage)
			throws TsfaServiceException {
		logger.debug("findGuidMemberExtPage(FindGuidMemberExtPage findGuidMemberExtPage={}) - start", findGuidMemberExtPage); 

		AssertUtils.notNull(findGuidMemberExtPage);
		List<GuidMemberExtDto> returnList=null;
		int count = 0;
		try {
//			returnList = guidMemberExtDao.findGuidMemberExtPage(findGuidMemberExtPage);
//			count = guidMemberExtDao.findGuidMemberExtPageCount(findGuidMemberExtPage);
		}  catch (Exception e) {
			logger.error("导购开放平台扩展信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_EXT_FIND_PAGE_ERROR,"导购开放平台扩展信息不存在错误.！",e);
		}
		Page<GuidMemberExtDto> returnPage = new Page<GuidMemberExtDto>(returnList, count, findGuidMemberExtPage);

		logger.debug("findGuidMemberExtPage(FindGuidMemberExtPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public GuidMemberExtDto findGuidMemberExtByMobile(GuidMemberExtDto guidMemberExtDto) {
		logger.debug("findShopExtByJobNum(FindShopExt findShopExt={}) - start", guidMemberExtDto); 

		AssertUtils.notNull(guidMemberExtDto);
//		AssertUtils.notAllNull(guidMemberExtDto.getShopNo(),"终端编号不能为空");
		AssertUtils.notAllNull(guidMemberExtDto.getMemberNoMerchant(),"商户编号不能为空");
		AssertUtils.notAllNull(guidMemberExtDto.getMobile(),"手机号不能为空");
		try {
			GuidMemberExt guidMemberExt = guidMemberExtDao.findGuidMemberExtByMobile(guidMemberExtDto);
			if(guidMemberExt == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.GUID_MEMBER_EXT_NOT_EXIST_ERROR,"导购开放平台扩展信息不存在");
			}
			GuidMemberExtDto findGuidMemberExtReturn = new GuidMemberExtDto();
			//find数据录入
			findGuidMemberExtReturn.setCode(guidMemberExt.getCode());
			findGuidMemberExtReturn.setMemberNoMerchant(guidMemberExt.getMemberNoMerchant());
			findGuidMemberExtReturn.setMemberNameMerchant(guidMemberExt.getMemberNameMerchant());
//			findGuidMemberExtReturn.setShopNo(guidMemberExt.getShopNo());
//			findGuidMemberExtReturn.setShopName(guidMemberExt.getShopName());
			findGuidMemberExtReturn.setMemberName(guidMemberExt.getMemberName());
			findGuidMemberExtReturn.setJobNum(guidMemberExt.getJobNum());
			findGuidMemberExtReturn.setBirthday(guidMemberExt.getBirthday());
			findGuidMemberExtReturn.setMobile(guidMemberExt.getMobile());
			findGuidMemberExtReturn.setAge(guidMemberExt.getAge());
			findGuidMemberExtReturn.setGender(guidMemberExt.getGender());
			findGuidMemberExtReturn.setNoWx(guidMemberExt.getNoWx());
			findGuidMemberExtReturn.setNoWxPsw(guidMemberExt.getNoWxPsw());
			findGuidMemberExtReturn.setWorkDate(guidMemberExt.getWorkDate());
			findGuidMemberExtReturn.setPosition(guidMemberExt.getPosition());
			findGuidMemberExtReturn.setAreaName(guidMemberExt.getAreaName());
			findGuidMemberExtReturn.setStatus(guidMemberExt.getStatus());
			findGuidMemberExtReturn.setCreateTime(guidMemberExt.getCreateTime());
			findGuidMemberExtReturn.setUpdateTime(guidMemberExt.getUpdateTime());
			
			logger.debug("findShopExtByJobNum(GuidMemberExtDto) - end - return value={}", findGuidMemberExtReturn); 
			return findGuidMemberExtReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购开放平台扩展信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_EXT_FIND_ERROR,"查找导购开放平台扩展信息信息错误！",e);
		}
	} 


}
