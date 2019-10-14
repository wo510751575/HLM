package com.lj.business.member.service.impl.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dao.IPmTypeDao;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.cc.clientintf.IJob;

/**
 * 
 * 
 * 类说明：未分组任务处理：未分组人员自动分组到意向未到店，每日凌晨跑批
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年10月14日
 */
@Service
public class UngroupProcessServiceImpl implements IJob {
	private static final Logger logger = LoggerFactory.getLogger(UngroupProcessServiceImpl.class);

	@Resource
	private IGuidMemberService guidMemberService;

	@Resource
	private IMerchantService merchantService;

	@Resource
	private IPmTypeService pmTypeService;
	
	@Resource
	private IPmTypeDao pmTypeDao;
	@Autowired
	private IPersonMemberService personMemberService;
	@Resource
	private IManagerMemberService managerMemberService;

	@Override
	public void runJob() {
		this.triggerUngroupProcess();
	}

	public synchronized void triggerUngroupProcess() throws TsfaServiceException {
		logger.debug("【未分组处理】triggerUngroupProcess() - start"); //$NON-NLS-1$
		/*try {
			*//**
			 * 要在先关闭初次介绍任务的调度后面执行。
			 * 1.关闭未分组任务。非邀约版现在没有任务体系。
			 * 2.未分组客户自动分组到意向未到店并产生初次介绍任务。非邀约版也有未分组客户。
			 *//*
			
			//查询所有邀约型商户信息
			FindMerchantPage findMerchantPage = new FindMerchantPage();
			findMerchantPage.setProductType(ProductType.INVITE.toString());
			List<FindMerchantPageReturn> merchantList = merchantService.findMerchants(findMerchantPage );
			if (CollectionUtils.isEmpty(merchantList)) {
				return;
			}
			//商户处理
			CloseGroup closeGroup = new CloseGroup();
			for (FindMerchantPageReturn findMerchantPageReturn : merchantList) {
				try {
					//关闭未分组任务
					String merchantNo = findMerchantPageReturn.getMerchantNo();
					logger.debug("关闭未分组任务,merchantNo:" + merchantNo);
					closeGroup.setMerchantNo(merchantNo);
					comTaskService.closeGroup(closeGroup );
					
					logger.debug("未分组客户自动分组到意向未到店并产生初次介绍任务。");
					//获取目标类型CODE
//					PmType pmTypeQuery = new PmType();
//					pmTypeQuery.setMerchantNo(merchantNo);
//					pmTypeQuery.setPmTypeType(PmTypeType.INTENTION_N.toString());
//					PmType pmTypeResult = pmTypeDao.selectByParamKey(pmTypeQuery );
					//获取待分组信息
					Map<String, String> map = new HashMap<String, String>();
					map.put("merchantNo", merchantNo);
					List<Map<String, String>> listResult = pmTypeDao.selectUngroupInfo(map);
					logger.debug("循环处理未分组用户，移动到 意向未到店");
					for (Map<String, String> mapResult : listResult) {
						UpdatePersonMember updatePersonMember = new UpdatePersonMember();
						updatePersonMember.setCode(mapResult.get("code"));
						updatePersonMember.setPmTypeCode(pmTypeResult.getCode());
						personMemberService.updatePmType(updatePersonMember);
					}
				} catch (Exception e) {
					logger.error("未分组处理错误", e); 
				}
			}
		} catch (Exception e) {
			logger.error("未分组处理JOB错误", e); 
		}*/

		logger.debug("【未分组处理】triggerUngroupProcess() - end"); //$NON-NLS-1$
	}

}
