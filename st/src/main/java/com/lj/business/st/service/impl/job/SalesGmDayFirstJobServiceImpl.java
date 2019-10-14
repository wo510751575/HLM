package com.lj.business.st.service.impl.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetailFirstList;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetailReturn;
import com.lj.business.st.dto.salesGmDayFirst.AddSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.FindSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.FindSalesGmDayFirstReturn;
import com.lj.business.st.dto.salesGmDayFirst.UpdateSalesGmDayFirst;
import com.lj.business.st.service.ISalesGmDayDetailService;
import com.lj.business.st.service.ISalesGmDayFirstJobService;
import com.lj.business.st.service.ISalesGmDayFirstService;
import com.lj.cc.clientintf.IJob;

@Service
public class SalesGmDayFirstJobServiceImpl implements ISalesGmDayFirstJobService,IJob{
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(SalesGmDayFirstJobServiceImpl.class);
	
	@Resource
	private ISalesGmDayDetailService salesGmDayDetailService;
	
	@Resource
	private ISalesGmDayFirstService salesGmDayFirstService;
	
	@Override
	public void runJob() {
		this.triggerWokTaskJob();
	}

	public synchronized void triggerWokTaskJob() throws TsfaServiceException {
		try {
			doSalesGmDayFirstJobService();
		} catch (Exception e) {
			logger.error("doSalesGmDayFirstJobService()", e); //$NON-NLS-1$
		}
	}

	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.ISalesGmDayFirstJobService#doSalesGmDayFirstJobService()
	 */
	@Override
	public void doSalesGmDayFirstJobService() throws TsfaServiceException {
		try{
			//查询昨天的导购销售日冠军明细表
			List<FindSalesGmDayDetailReturn> list = new ArrayList<FindSalesGmDayDetailReturn>();
			FindSalesGmDayDetailFirstList findSalesGmDayDetailFirstList = new FindSalesGmDayDetailFirstList();
			findSalesGmDayDetailFirstList.setDateSt(org.apache.commons.lang.time.DateUtils.truncate(DateUtils.getPreday(new Date()), Calendar.DAY_OF_MONTH));
			list = salesGmDayDetailService.findSalesGmDayDetailFirstList(findSalesGmDayDetailFirstList);
			//新增或修改导购日冠军表
			if(list != null && list.size() > 0){
				for(FindSalesGmDayDetailReturn findSalesGmDayDetailReturn : list){
					//判断是否存在 一个商户一个区域一天只有一条记录
					FindSalesGmDayFirst findSalesGmDayFirst = new FindSalesGmDayFirst();
					findSalesGmDayFirst.setAreaCode(findSalesGmDayDetailReturn.getAreaCode());
					findSalesGmDayFirst.setMerchantNo(findSalesGmDayDetailReturn.getMerchantNo());
					findSalesGmDayFirst.setDaySt(findSalesGmDayDetailFirstList.getDateSt());
					FindSalesGmDayFirstReturn findSalesGmDayFirstReturn = salesGmDayFirstService.findSalesGmDayFirstByMAD(findSalesGmDayFirst);
					if(findSalesGmDayFirstReturn == null){
						//新增
						AddSalesGmDayFirst addSalesGmDayFirst = new AddSalesGmDayFirst();
						addSalesGmDayFirst.setCode(GUID.generateCode());
						addSalesGmDayFirst.setMerchantNo(findSalesGmDayDetailReturn.getMerchantNo());
						addSalesGmDayFirst.setShopNo(findSalesGmDayDetailReturn.getShopNo());
						addSalesGmDayFirst.setShopName(findSalesGmDayDetailReturn.getShopName());
						addSalesGmDayFirst.setAreaCode(findSalesGmDayDetailReturn.getAreaCode());
						addSalesGmDayFirst.setAreaName(findSalesGmDayDetailReturn.getAreaName());
						addSalesGmDayFirst.setHeadAddress(findSalesGmDayDetailReturn.getHeadAddress());
						addSalesGmDayFirst.setMemberNoGm(findSalesGmDayDetailReturn.getMemberNoGm());
						addSalesGmDayFirst.setMemberNameGm(findSalesGmDayDetailReturn.getMemberNameGm());
						addSalesGmDayFirst.setNumSale(findSalesGmDayDetailReturn.getNumSale());
						addSalesGmDayFirst.setNumSaleTarget(findSalesGmDayDetailReturn.getNumSaleTarget());
						addSalesGmDayFirst.setDaySt(findSalesGmDayDetailReturn.getDaySt());
						addSalesGmDayFirst.setCreateDate(new Date());
						addSalesGmDayFirst.setUpdateDate(new Date());
						salesGmDayFirstService.addSalesGmDayFirst(addSalesGmDayFirst);
					}else{
						//修改
						UpdateSalesGmDayFirst updateSalesGmDayFirst = new UpdateSalesGmDayFirst();
						updateSalesGmDayFirst.setCode(findSalesGmDayDetailReturn.getCode());
						updateSalesGmDayFirst.setMerchantNo(findSalesGmDayDetailReturn.getMerchantNo());
						updateSalesGmDayFirst.setShopNo(findSalesGmDayDetailReturn.getShopNo());
						updateSalesGmDayFirst.setShopName(findSalesGmDayDetailReturn.getShopName());
						updateSalesGmDayFirst.setAreaCode(findSalesGmDayDetailReturn.getAreaCode());
						updateSalesGmDayFirst.setAreaName(findSalesGmDayDetailReturn.getAreaName());
						updateSalesGmDayFirst.setHeadAddress(findSalesGmDayDetailReturn.getHeadAddress());
						updateSalesGmDayFirst.setMemberNoGm(findSalesGmDayDetailReturn.getMemberNoGm());
						updateSalesGmDayFirst.setMemberNameGm(findSalesGmDayDetailReturn.getMemberNameGm());
						updateSalesGmDayFirst.setNumSale(findSalesGmDayDetailReturn.getNumSale());
						updateSalesGmDayFirst.setNumSaleTarget(findSalesGmDayDetailReturn.getNumSaleTarget());
						updateSalesGmDayFirst.setUpdateDate(new Date());
						salesGmDayFirstService.updateSalesGmDayFirst(updateSalesGmDayFirst);
					}
				}
			}
		}catch(Exception e){
			logger.error("导购销售日冠军定时任务执行错误！",e);
		}
		
	}
	
	
}
