package com.lj.business.st.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IClientExpTotalDao;
import com.lj.business.st.domain.ClientExpTotal;
import com.lj.business.st.dto.AddClientExpTotal;
import com.lj.business.st.dto.FindClientExpReturn;
import com.lj.business.st.dto.FindClientExpTotal;
import com.lj.business.st.dto.FindClientExpTotalReturn;
import com.lj.business.st.service.IClientExpTotalService;

/**
 * 
 * 
 * 类说明：到店客户统计实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月28日
 */
@Service
public class ClientExpTotalServiceImp implements IClientExpTotalService{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ClientExpTotalServiceImp.class);
	
	@Resource
	private IClientExpTotalDao clientExpTotalDao;
     
	@Override
	public void insertSelective(AddClientExpTotal addClientExpTotal) {
		logger.debug("insertSelective(AddClientExpTotal addClientExpTotal={}) - start", addClientExpTotal); 
		AssertUtils.notNull(addClientExpTotal);
		try {
			ClientExpTotal clientExpTotal= new  ClientExpTotal();
			clientExpTotal.setCode(GUID.generateByUUID());
			clientExpTotal.setMerchantNo(addClientExpTotal.getMerchantNo());
			clientExpTotal.setMemberNoGm(addClientExpTotal.getMemberNoGm());
			clientExpTotal.setMemberNameGm(addClientExpTotal.getMemberNameGm());
			clientExpTotal.setShopNo(addClientExpTotal.getShopNo());
			clientExpTotal.setShopName(addClientExpTotal.getShopName());
			clientExpTotal.setStDate(addClientExpTotal.getStDate());
			clientExpTotal.setNumAdd(addClientExpTotal.getNumAdd());
			clientExpTotal.setCreateDate(new Date());
			clientExpTotalDao.insertSelective(clientExpTotal);
			logger.debug("addShopArea(addShopArea) - end - return"); 
		} catch (Exception e) {
			logger.error("新增到店客户体验统计错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_EXP_TOTAL_ADD_ERROR,"新增到店客户体验统计错误！",e);
		}
		return ;
	}
    
	  @Override
	  public List<FindClientExpReturn> findClientExpTotal(Map<String, Object> map) {
		logger.debug("findClientExpTotal((Map<String, Object> map={}) - start", map); 
		AssertUtils.notNull(map);
		List<FindClientExpReturn> list = null;
		try {
			list=clientExpTotalDao.findClientExpTotal(map);			
		} catch (Exception e) {
			logger.error("查询到店客户体验统计错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_EXP_TOTAL_FIND_ERROR,"查询门店区域分布统计错误！",e);
		}
		logger.debug("findClientExpTotal(map) - end - return"); 
		return list;
		
	}   

	@Override
	public FindClientExpTotalReturn findClientExpTotalList(FindClientExpTotal findClientExpTotal) {
		logger.debug("findClientExpTotalList(findClientExpTotal={}) - start", findClientExpTotal);

		AssertUtils.notNull(findClientExpTotal);
		AssertUtils.notNull(findClientExpTotal.getBeginDate(), "开始日期不能为空");
		AssertUtils.notNull(findClientExpTotal.getEndDate(), "结束日期不能为空");

		FindClientExpTotalReturn result = new FindClientExpTotalReturn();
		Map<String, Object> maxDatas = new HashMap<>();
		List<Map<String, Object>> datas = new ArrayList<>();
		Integer totalCustomerNum = 0;
		try {
			List<ClientExpTotal> clientExpTotalList = clientExpTotalDao.findClientExpTotalList(findClientExpTotal);
			if (!CollectionUtils.isEmpty(clientExpTotalList)) {
				ClientExpTotal maxClientExpTotal = null;
				for (ClientExpTotal each : clientExpTotalList) {
					totalCustomerNum += each.getNumAdd();

					Map<String, Object> item = new HashMap<>();
					item.put("date", DateUtils.formatDate(each.getStDate(), DateUtils.PATTERN_yyyy_MM_dd));
					item.put("number", each.getNumAdd());
					datas.add(item);

					if (maxClientExpTotal == null || each.getNumAdd() > maxClientExpTotal.getNumAdd()) {
						maxClientExpTotal = each;
					}
				}
				maxDatas.put("date", DateUtils.formatDate(maxClientExpTotal.getStDate(), DateUtils.PATTERN_yyyy_MM_dd));
				maxDatas.put("number", maxClientExpTotal.getNumAdd());
 
			}
			result.setDatas(datas);
			result.setMaxDatas(maxDatas);
			result.setTotalCustomerNum(totalCustomerNum);
			logger.debug("findClientExpTotalList(findClientExpTotal) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查询客户分类统计表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CLIENT_EXP_TOTAL_FIND_ERROR, "查询客户分类统计表信息错误！", e);
		}
		return result;
	}

}
