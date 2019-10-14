package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 * <p>
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.lj.base.core.util.ArithUtil;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IPmTypeTotalDao;
import com.lj.business.st.domain.PmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.AddPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotalReturn;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.service.IPmTypeTotalService;

/**
 * 类说明：实现类
 *
 *
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 *
 *
 * CreateDate: 2017-06-14
 */
@Service
public class PmTypeTotalServiceImpl implements IPmTypeTotalService {


    /** Logger for this class. */
    private static final Logger logger = LoggerFactory.getLogger(PmTypeTotalServiceImpl.class);


    @Resource
    private IPmTypeTotalDao pmTypeTotalDao;
    
    @Resource
	private IPmTypeService pmTypeService;
	
	@Resource
	private IMerchantService merchantService;



    @Override
    public void addPmTypeTotal(
            AddPmTypeTotal addPmTypeTotal) throws TsfaServiceException {
        logger.debug("addPmTypeTotal(AddPmTypeTotal addPmTypeTotal={}) - start", addPmTypeTotal);

        AssertUtils.notNull(addPmTypeTotal);
        try {
            PmTypeTotal pmTypeTotal = new PmTypeTotal();
            //add数据录入
            pmTypeTotal.setCode(GUID.getPreUUID());
            pmTypeTotal.setMerchantNo(addPmTypeTotal.getMerchantNo());
            pmTypeTotal.setShopNo(addPmTypeTotal.getShopNo());
            pmTypeTotal.setShopName(addPmTypeTotal.getShopName());
            pmTypeTotal.setMemberNoGm(addPmTypeTotal.getMemberNoGm());
            pmTypeTotal.setMemberNameGm(addPmTypeTotal.getMemberNameGm());
            pmTypeTotal.setAreaCode(addPmTypeTotal.getAreaCode());
            pmTypeTotal.setAreaName(addPmTypeTotal.getAreaName());
            pmTypeTotal.setProvinceCode(addPmTypeTotal.getProvinceCode());
            pmTypeTotal.setCityCode(addPmTypeTotal.getCityCode());
            pmTypeTotal.setCityAreaCode(addPmTypeTotal.getCityAreaCode());
            pmTypeTotal.setPmTypeCode(addPmTypeTotal.getPmTypeCode());
            pmTypeTotal.setTypeName(addPmTypeTotal.getTypeName());
            pmTypeTotal.setPmTypeType(addPmTypeTotal.getPmTypeType());
            pmTypeTotal.setNumPm(addPmTypeTotal.getNumPm());
            pmTypeTotal.setNumAdd(addPmTypeTotal.getNumAdd());
            pmTypeTotal.setRatioPm(addPmTypeTotal.getRatioPm());
            pmTypeTotal.setDaySt(addPmTypeTotal.getDaySt());
            pmTypeTotal.setDimensionSt(addPmTypeTotal.getDimensionSt());
            pmTypeTotal.setCreateDate(addPmTypeTotal.getCreateDate());
            pmTypeTotalDao.insertSelective(pmTypeTotal);
            logger.debug("addPmTypeTotal(AddPmTypeTotal) - end - return");
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("新增客户分类统计表信息错误！", e);
            throw new TsfaServiceException(ErrorCode.PM_TYPE_TOTAL_ADD_ERROR, "新增客户分类统计表信息错误！", e);
        }
    }

    @Override
    public PmTypeTotal selectByPrimaryKey(String code) {
        return pmTypeTotalDao.selectByPrimaryKey(code);
    }

    @Override
    public List<FindPmTypeTotalReturn> findPmTypeTotalList(FindPmTypeTotal findPmTypeTotal) throws TsfaServiceException {
        logger.debug("findPmTypeTotalList(findPmTypeTotal={}) - start", findPmTypeTotal);

        AssertUtils.notNull(findPmTypeTotal);
        //AssertUtils.notNull(findPmTypeTotal.getBeginDate(), "开始日期不能为空");
        //AssertUtils.notNull(findPmTypeTotal.getEndDate(), "结束日期不能为空");

        List<FindPmTypeTotalReturn> result = new ArrayList<>();
        try {
            List<PmTypeTotal> pmTypeTotalList = pmTypeTotalDao.findPmTypeTotalList(findPmTypeTotal);
            if (!CollectionUtils.isEmpty(pmTypeTotalList)) {
                // 查询总的数量
                Long totalNum = 0L;
                for (PmTypeTotal each : pmTypeTotalList) {
//                	if (!PmTypeType.REPEAT.toString().equals(each.getPmTypeType())) {
                		totalNum += each.getNumPm();
//					}
                }

                for (PmTypeTotal each : pmTypeTotalList) {
                    FindPmTypeTotalReturn item = new FindPmTypeTotalReturn();
                    item.setTypeName(each.getTypeName());
                    item.setPmTypeCode(each.getPmTypeCode());
                    item.setNumPm(each.getNumPm());
                    item.setMerchantNo(each.getMerchantNo());
                    item.setDimensionSt(each.getDimensionSt());
                    item.setShopName(each.getShopName());
                    item.setShopNo(each.getShopNo());
                    item.setRatioPm(mulHundredToString(totalNum, each.getNumPm()));
                    item.setDaySt(each.getDaySt());
                    item.setPmTypeType(each.getPmTypeType());
                    result.add(item);
                }
            }
            logger.debug("addPmTypeTotal(AddPmTypeTotal) - end - return");
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("查询客户分类统计表信息错误！", e);
            throw new TsfaServiceException(ErrorCode.PM_TYPE_TOTAL_FIND_ERROR, "查询客户分类统计表信息错误！", e);
        }

        return result;
    }
    @Override
    public List<FindPmTypeTotalReturn> findPmTypeTotalListApp(FindPmTypeTotal findPmTypeTotal) throws TsfaServiceException {
    	logger.debug("findPmTypeTotalList(findPmTypeTotal={}) - start", findPmTypeTotal);
    	
    	AssertUtils.notNull(findPmTypeTotal);
    	List<FindPmTypeTotalReturn> pmTypeTotalList = new ArrayList<FindPmTypeTotalReturn>();
        try {
            pmTypeTotalList = pmTypeTotalDao.findPmTypeTotalListApp(findPmTypeTotal);
            if (!CollectionUtils.isEmpty(pmTypeTotalList)) {
                // 查询总的数量
                Long totalNum = 0L;
                for (FindPmTypeTotalReturn each : pmTypeTotalList) {
                    totalNum += each.getNumPm();
                }
                
                //2018-04-19修改，如果totalNum为0,设置setRatioPm为0
                if (totalNum == 0L) {
                    for (FindPmTypeTotalReturn each : pmTypeTotalList) {
                        each.setRatioPm("0");
                    }
                } else {
                    for (FindPmTypeTotalReturn each : pmTypeTotalList) {
                        each.setRatioPm(toString(totalNum, each.getNumPm()));
                    }
                }

            }
            logger.debug("addPmTypeTotal(AddPmTypeTotal) - end - return");
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("查询客户分类统计表信息错误！", e);
            throw new TsfaServiceException(ErrorCode.PM_TYPE_TOTAL_FIND_ERROR, "查询客户分类统计表信息错误！", e);
        }
    	return pmTypeTotalList;
    }

    private String toString(Long totalNum, Integer num) {
        Double div = ArithUtil.div(num, totalNum);
        BigDecimal bd = new BigDecimal(div);
        bd =  bd.setScale(4, RoundingMode.HALF_UP);
        return bd.toString();
    }

    private String mulHundredToString(Long totalNum, Integer num) {
        Double div = ArithUtil.div(num, totalNum);
        BigDecimal bd = new BigDecimal(div * 100);
        bd =  bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }

	@Override
	public List<Map<String, Object>> findIntentionPmList(Map<String, Object> parmMap) {
		try {
			return pmTypeTotalDao.findIntentionPmList(parmMap);
		} catch (Exception e) {
			logger.error("查询客户分类统计表信息错误！", e);
            throw new TsfaServiceException(ErrorCode.PM_TYPE_TOTAL_FIND_ERROR, "查询客户分类统计表信息错误！", e);
		}
	}

	@Override
	public List<Map<String, Object>> findPmTypeList(Map<String, Object> parmMap) {
		logger.debug("参数输出:"+parmMap);
		try {
			List<Map<String, Object>> list = pmTypeTotalDao.findPmTypeList(parmMap);
			logger.debug("return :"+list.size());
			return list;
		} catch (Exception e) {
			logger.error("查询客户分类统计表信息错误！", e);
            throw new TsfaServiceException(ErrorCode.PM_TYPE_TOTAL_FIND_ERROR, "查询客户分类统计表信息错误！", e);
		}
	}

	@Override
	public FindPmTypeTotalReturn findPmTypeMaxList(FindPmTypeTotal findPmTypeTotal) {
		AssertUtils.notNull(findPmTypeTotal);
		AssertUtils.notNullAndEmpty(findPmTypeTotal.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findPmTypeTotal.getMemberNoGm(),"导购编号不能为空");
		try {
			FindPmTypeTotalReturn findPmTypeTotalReturn = pmTypeTotalDao.findPmTypeMaxList(findPmTypeTotal);
			return findPmTypeTotalReturn;
		} catch (Exception e) {
			logger.error("查询客户分类统计表信息错误！", e);
            throw new TsfaServiceException(ErrorCode.PM_TYPE_TOTAL_FIND_ERROR, "查询客户分类统计表信息错误！", e);
		}
	}

	@Override
	public List<FindPmTypeTotalReturn> findPmTypeListSum(
			FindPmTypeTotal findPmTypeTotal) {
		AssertUtils.notNull(findPmTypeTotal);
		AssertUtils.notNullAndEmpty(findPmTypeTotal.getMerchantNo(),"商户号不能为空");
		List<FindPmTypeTotalReturn> list=null;
		try {
			list=pmTypeTotalDao.findPmTypeListSum(findPmTypeTotal);
		} catch (Exception e) {
			logger.error("查询客户分类统计表信息错误！", e);
            throw new TsfaServiceException(ErrorCode.PM_TYPE_TOTAL_FIND_ERROR, "查询客户分类统计表信息错误！", e);
		}
 		
		return list;
	}

	@Override
	public List<FindPmTypeTotalReturn> findPmTypeDaySt(
			FindPmTypeTotal findPmTypeTotal) {
		AssertUtils.notNullAndEmpty(findPmTypeTotal.getMerchantNo(),"商户编号不能为空");
		List<FindPmTypeTotalReturn>  list = null ;
		try {
			list = pmTypeTotalDao.findPmTypeDaySt(findPmTypeTotal);
		} catch (Exception e) {
			logger.error("查询客户分类统计表信息错误！", e);
            throw new TsfaServiceException(ErrorCode.PM_TYPE_TOTAL_FIND_ERROR, "查询客户分类统计表信息错误！", e);
		}
		
		return list;
	}

	@Override
	public List<FindPmTypeTotalReturn> queryPmType(FindPmTypeTotal findPmTypeTotal) {
		return pmTypeTotalDao.queryPmType(findPmTypeTotal);
	}

	@Override
	public boolean initializePmTypeTota() throws TsfaServiceException {
		boolean b = true ;
	  	AddPmTypeTotal addPmTypeTotal = new AddPmTypeTotal();
    	//循环所有商户统计的数据
    	try {
    		List<FindMerchantPageReturn> merchants = merchantService.findMerchants(new FindMerchantPage());
    		for (FindMerchantPageReturn merchantPageReturn:merchants) {
    		    String merchantNo = merchantPageReturn.getMerchantNo();
    			//查询时间维度，按时间维度分组
    	    	FindPmTypeTotal findPmTypeTotal = new FindPmTypeTotal();
    	    	findPmTypeTotal.setMerchantNo(merchantNo);
    	    	findPmTypeTotal.setDimensionSt(DimensionSt.MERCHANT.toString());
    	    	List<FindPmTypeTotalReturn>  list = pmTypeTotalDao.findPmTypeDaySt(findPmTypeTotal);
    		    //初始化数据
    		    for(FindPmTypeTotalReturn findPmTypeTotalReturn : list){
    		    	findPmTypeTotal = new FindPmTypeTotal();
    		    	findPmTypeTotal.setMerchantNo(merchantNo);
    		    	findPmTypeTotal.setDimensionSt(DimensionSt.MERCHANT.toString());
    		    	findPmTypeTotal.setDaySt(findPmTypeTotalReturn.getDaySt());
    		    	//查找当天的数据统计
    		    	List<FindPmTypeTotalReturn>  totalReturn = pmTypeTotalDao.queryPmType(findPmTypeTotal);
    		       	FindPmTypePageReturn findPmTypePageReturn = new FindPmTypePageReturn();
    		   	    findPmTypePageReturn.setMerchantNo(merchantNo);
    		   	    //获取客户分类
    			    List<FindPmTypePageReturn> pmList = pmTypeService.findPmTypePages(findPmTypePageReturn);
    		    	int num1 = totalReturn.size();
    		    	int pmTypeNum = pmList.size();
    		    	List<FindPmTypePageReturn> addList = Lists.newArrayList();
    		    	if(pmTypeNum > num1){
    			    		for(FindPmTypeTotalReturn pmTypeTotalReturn :totalReturn){
    			    			for(FindPmTypePageReturn pageReturn : pmList){
    			    			 if(pageReturn.getPmTypeType().equals(pmTypeTotalReturn.getPmTypeType())){
    			    				 addList.add(pageReturn);
    			    			 }
    			    		}
    			    	}
    			    		pmList.removeAll(addList);
    			    		//初始化为空的数据
    			    		
    			    		for(FindPmTypePageReturn pageReturn : pmList){
    			    		    addPmTypeTotal = new AddPmTypeTotal();
    			    		    addPmTypeTotal.setMerchantNo(pageReturn.getMerchantNo());
    							addPmTypeTotal.setPmTypeCode(pageReturn.getCode());
    						    addPmTypeTotal.setTypeName(pageReturn.getTypeName());
    							addPmTypeTotal.setPmTypeType(pageReturn.getPmTypeType());
    							addPmTypeTotal.setDaySt(findPmTypeTotalReturn.getDaySt());
    							addPmTypeTotal.setCreateDate(new Date());
    							addPmTypeTotal.setDimensionSt(DimensionSt.MERCHANT.toString());
    							addPmTypeTotal(addPmTypeTotal);
    			    		}
    		    	}
    		    	
    		    }
			
		}
		} catch (Exception e) {
			b = false;
			logger.error("初始化数据失败",e);
		}
    	
    	return b;
	}

}
